package pokemon.ui;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;

/**
 * Una pantalla del juego que se adapta a cualquier tamaño de ventana.
 * <p>
 * Todo se diseña sobre un "escenario" fijo de 1080x650 (como siempre ha tenido el juego) y ese escenario
 * se escala para ocupar la ventana sin deformarse. El fondo (imagen o vídeo) va por detrás y cubre toda la
 * ventana, así que nunca quedan bandas vacías aunque la ventana tenga otra proporción.
 */
public class Pantalla extends StackPane {

    public static final double ANCHO = 1080;
    public static final double ALTO = 650;

    private final StackPane fondo = new StackPane();
    /** Capa de contenido. Es un StackPane para que el CSS antiguo con translate-x/y siga funcionando. */
    private final StackPane contenido = new StackPane();
    /** Capa libre para colocar cosas con coordenadas exactas (x, y) dentro de los 1080x650. */
    private final Pane lienzo = new Pane();
    private final StackPane capaDialogos = new StackPane();
    private final StackPane escenario = new StackPane();
    private final List<MediaPlayer> reproductores = new ArrayList<>();
    private Runnable alSalir;

    public Pantalla() {
        getStyleClass().add("pantalla");
        setStyle("-fx-background-color: #10131c;");
        getStylesheets().add(UI.css("Comun.css"));

        fondo.setMouseTransparent(true);
        fondo.setAlignment(Pos.CENTER);
        // El fondo nunca debe hacer crecer la pantalla: se recorta al tamaño de la ventana.
        fondo.setMinSize(0, 0);
        fondo.setManaged(false);
        Rectangle recorteFondo = new Rectangle();
        recorteFondo.widthProperty().bind(widthProperty());
        recorteFondo.heightProperty().bind(heightProperty());
        fondo.setClip(recorteFondo);

        fijarTamano(contenido);
        fijarTamano(lienzo);
        fijarTamano(capaDialogos);
        fijarTamano(escenario);
        lienzo.setPickOnBounds(false);
        capaDialogos.setPickOnBounds(false);
        contenido.setPickOnBounds(false);

        escenario.getChildren().addAll(contenido, lienzo, capaDialogos);
        Rectangle recorte = new Rectangle(ANCHO, ALTO);
        escenario.setClip(recorte);

        DoubleBinding escala = Bindings.createDoubleBinding(
                () -> Math.min(getWidth() / ANCHO, getHeight() / ALTO), widthProperty(), heightProperty());
        escenario.scaleXProperty().bind(escala);
        escenario.scaleYProperty().bind(escala);

        getChildren().addAll(fondo, escenario);
        setMinSize(0, 0);
    }

    @Override
    protected void layoutChildren() {
        super.layoutChildren();
        fondo.resizeRelocate(0, 0, getWidth(), getHeight());
    }

    private static void fijarTamano(javafx.scene.layout.Region r) {
        r.setMinSize(ANCHO, ALTO);
        r.setPrefSize(ANCHO, ALTO);
        r.setMaxSize(ANCHO, ALTO);
    }

    // ------------------------------------------------------------------------------------------
    // Fondo
    // ------------------------------------------------------------------------------------------

    /** Pone una imagen de fondo que cubre toda la ventana (recortando lo que sobre, sin deformar). */
    public Pantalla fondoImagen(String ruta) {
        ImageView iv = new ImageView(UI.imagen(ruta));
        iv.setPreserveRatio(true);
        cubrir(iv, Math.max(1, iv.getImage().getWidth()), Math.max(1, iv.getImage().getHeight()));
        fondo.getChildren().add(iv);
        return this;
    }

    /**
     * Fondo desenfocado: útil cuando la imagen principal va dentro del escenario (por ejemplo la Pokédex)
     * y el fondo solo tiene que rellenar los bordes de la ventana.
     */
    public Pantalla fondoDesenfocado(String ruta) {
        fondoImagen(ruta);
        fondo.getChildren().get(fondo.getChildren().size() - 1).setEffect(new javafx.scene.effect.GaussianBlur(28));
        javafx.scene.layout.Region oscuro = new javafx.scene.layout.Region();
        oscuro.setStyle("-fx-background-color: rgba(0,0,0,0.25);");
        fondo.getChildren().add(oscuro);
        return this;
    }

    /** Pone un vídeo en bucle de fondo que cubre toda la ventana. */
    public Pantalla fondoVideo(String ruta) {
        MediaView vista = new MediaView();
        vista.setPreserveRatio(true);
        cubrir(vista, 1920, 1080);
        fondo.getChildren().add(vista);
        cargarVideo(vista, ruta, 0);
        return this;
    }

    /**
     * El decodificador de vídeo de Windows a veces falla al abrir un vídeo (sobre todo justo al cambiar
     * de pantalla). Si pasa, se reintenta un par de veces antes de rendirse.
     */
    private void cargarVideo(MediaView vista, String ruta, int intento) {
        MediaPlayer reproductor = UI.reproductorVideo(ruta);
        if (reproductor == null) return;
        reproductores.add(reproductor);
        vista.setMediaPlayer(reproductor);
        reproductor.setOnReady(() -> {
            double w = reproductor.getMedia().getWidth();
            double h = reproductor.getMedia().getHeight();
            if (w > 0 && h > 0) cubrir(vista, w, h);
        });
        reproductor.setOnError(() -> {
            reproductores.remove(reproductor);
            reproductor.dispose();
            if (intento < 2 && getScene() != null) {
                javafx.animation.PauseTransition espera = new javafx.animation.PauseTransition(javafx.util.Duration.millis(400));
                espera.setOnFinished(e -> cargarVideo(vista, ruta, intento + 1));
                espera.play();
            } else {
                System.err.println("No se pudo reproducir el vídeo " + ruta + ": " + reproductor.getError());
            }
        });
        reproductor.play();
    }

    /** Añade una capa encima del fondo (por ejemplo un degradado oscuro) que también cubre toda la ventana. */
    public Pantalla capaFondo(Node capa) {
        if (capa instanceof javafx.scene.layout.Region r) {
            r.prefWidthProperty().bind(widthProperty());
            r.prefHeightProperty().bind(heightProperty());
        }
        fondo.getChildren().add(capa);
        return this;
    }

    private void cubrir(Node nodo, double anchoOriginal, double altoOriginal) {
        DoubleBinding escala = Bindings.createDoubleBinding(
                () -> Math.max(getWidth() / anchoOriginal, getHeight() / altoOriginal), widthProperty(), heightProperty());
        if (nodo instanceof ImageView iv) {
            iv.fitWidthProperty().bind(escala.multiply(anchoOriginal));
            iv.fitHeightProperty().bind(escala.multiply(altoOriginal));
        } else if (nodo instanceof MediaView mv) {
            mv.fitWidthProperty().bind(escala.multiply(anchoOriginal));
            mv.fitHeightProperty().bind(escala.multiply(altoOriginal));
        }
    }

    // ------------------------------------------------------------------------------------------
    // Contenido
    // ------------------------------------------------------------------------------------------

    /** Capa tipo StackPane (centrada) de 1080x650. */
    public StackPane getContenido() {
        return contenido;
    }

    /** Capa con coordenadas absolutas de 1080x650. */
    public Pane getLienzo() {
        return lienzo;
    }

    public StackPane getCapaDialogos() {
        return capaDialogos;
    }

    /** Coloca un nodo en el lienzo en la posición (x, y). */
    public <T extends Node> T poner(T nodo, double x, double y) {
        nodo.relocate(x, y);
        lienzo.getChildren().add(nodo);
        return nodo;
    }

    /** Añade una hoja de estilos de la pantalla (además de Comun.css). */
    public Pantalla estilo(String css) {
        getStylesheets().add(UI.css(css));
        return this;
    }

    /** Registra un reproductor (por ejemplo de un efecto) para liberarlo al salir de la pantalla. */
    public void registrar(MediaPlayer reproductor) {
        if (reproductor != null) reproductores.add(reproductor);
    }

    /** Código que se ejecuta al salir de la pantalla (parar animaciones, etc.). */
    public void setAlSalir(Runnable alSalir) {
        this.alSalir = alSalir;
    }

    /** Libera los vídeos y lo que haya que parar. Lo llama App al cambiar de pantalla. */
    void liberar() {
        if (alSalir != null) {
            try {
                alSalir.run();
            } catch (RuntimeException e) {
                e.printStackTrace();
            }
        }
        List<MediaPlayer> aLiberar = new ArrayList<>(reproductores);
        reproductores.clear();
        for (MediaPlayer r : aLiberar) {
            try {
                r.stop();
            } catch (RuntimeException ignorada) {
                // El reproductor ya estaba parado.
            }
        }
        // Se liberan un poco después: si se liberan justo mientras arranca el vídeo de la pantalla
        // nueva, el decodificador de Windows a veces falla y el vídeo nuevo no se ve.
        javafx.animation.PauseTransition espera = new javafx.animation.PauseTransition(javafx.util.Duration.seconds(1.5));
        espera.setOnFinished(e -> aLiberar.forEach(r -> {
            try {
                r.dispose();
            } catch (RuntimeException ignorada) {
                // Ya estaba liberado.
            }
        }));
        espera.play();
    }

    public boolean hayDialogoAbierto() {
        return !capaDialogos.getChildren().isEmpty();
    }
}
