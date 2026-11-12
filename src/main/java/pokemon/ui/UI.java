package pokemon.ui;

import pokemon.pokemon.ListaEstados;
import pokemon.pokemon.Tipo;
import pokemon.tienda.Objeto;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Utilidades de interfaz: carga de imágenes, botones con el estilo del juego, iconos dibujados, etc.
 */
public final class UI {

    /** Fuente que usa todo el juego. */
    public static final String FUENTE = "Bahnschrift";
    public static final String AMARILLO = "#FFCB05";
    public static final String AZUL = "#2A75BB";
    public static final String AZUL_OSCURO = "#1D3F7A";

    private static final Map<String, Image> cache = new HashMap<>();

    private UI() {
    }

    // ------------------------------------------------------------------------------------------
    // Recursos
    // ------------------------------------------------------------------------------------------

    public static String css(String nombre) {
        URL url = UI.class.getResource("/" + nombre);
        return url != null ? url.toExternalForm() : nombre;
    }

    /**
     * Carga una imagen de los recursos (con caché). Si no existe, devuelve una imagen transparente
     * en lugar de romper el juego.
     */
    public static Image imagen(String ruta) {
        if (ruta == null || ruta.isBlank()) return vacia();
        return cache.computeIfAbsent(ruta, r -> {
            URL url = UI.class.getResource(r.startsWith("/") ? r : "/" + r);
            if (url == null) {
                System.err.println("Imagen no encontrada: " + r);
                return vacia();
            }
            return new Image(url.toExternalForm());
        });
    }

    /** Sprite de espaldas del Pokémon; si no existe, el de frente. */
    public static Image spriteEspalda(String espalda, String frente) {
        if (espalda != null && UI.class.getResource(espalda) != null) return imagen(espalda);
        return imagen(frente);
    }

    private static Image vacia() {
        return cache.computeIfAbsent("__vacia__", k -> new javafx.scene.image.WritableImage(1, 1));
    }

    public static ImageView vistaImagen(String ruta, double ancho, double alto) {
        ImageView iv = new ImageView(imagen(ruta));
        iv.setFitWidth(ancho);
        iv.setFitHeight(alto);
        iv.setPreserveRatio(true);
        iv.setSmooth(false);
        return iv;
    }

    static MediaPlayer reproductorVideo(String ruta) {
        URL url = UI.class.getResource(ruta);
        if (url == null) return null;
        try {
            MediaPlayer mp = new MediaPlayer(new Media(url.toExternalForm()));
            mp.setCycleCount(MediaPlayer.INDEFINITE);
            mp.setMute(true);
            mp.setOnError(() -> System.err.println("Error en el vídeo " + ruta + ": " + mp.getError()));
            return mp;
        } catch (RuntimeException e) {
            System.err.println("No se pudo cargar el vídeo " + ruta + ": " + e.getMessage());
            return null;
        }
    }

    // ------------------------------------------------------------------------------------------
    // Botones y textos
    // ------------------------------------------------------------------------------------------

    /** Botón que es solo una imagen (como los botones pixel art del juego). */
    public static Button botonImagen(String ruta, double ancho, double alto) {
        Button b = new Button();
        b.getStyleClass().add("boton-imagen");
        ImageView iv = vistaImagen(ruta, ancho, alto);
        iv.setSmooth(true);
        b.setGraphic(iv);
        b.setPrefSize(ancho, alto);
        b.setMinSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        b.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        return b;
    }

    /** Botón azul con letras amarillas, el estilo de los botones del menú principal. */
    public static Button boton(String texto) {
        Button b = new Button(texto);
        b.getStyleClass().add("boton-juego");
        return b;
    }

    /** Botón claro (acción secundaria: cancelar, volver...). */
    public static Button botonClaro(String texto) {
        Button b = new Button(texto);
        b.getStyleClass().addAll("boton-juego", "claro");
        return b;
    }

    /** Botón rojo (acción peligrosa: liberar, rendirse...). */
    public static Button botonRojo(String texto) {
        Button b = new Button(texto);
        b.getStyleClass().addAll("boton-juego", "rojo");
        return b;
    }

    public static Label etiqueta(String texto, String... clases) {
        Label l = new Label(texto);
        l.getStyleClass().addAll(clases);
        return l;
    }

    /** Título grande amarillo con borde azul, como el logo del juego. */
    public static Text titulo(String texto, double tamano) {
        Text t = new Text(texto);
        t.setFont(Font.font(FUENTE, FontWeight.BLACK, tamano));
        t.setFill(Color.web(AMARILLO));
        t.setStroke(Color.web(AZUL_OSCURO));
        t.setStrokeWidth(Math.max(2, tamano / 18));
        DropShadow sombra = new DropShadow(0, 3, 4, Color.web("#0b1a3a"));
        t.setEffect(sombra);
        return t;
    }

    public static void tooltip(Node nodo, String texto) {
        Tooltip t = new Tooltip(texto);
        t.setShowDelay(Duration.millis(250));
        t.setWrapText(true);
        t.setMaxWidth(320);
        Tooltip.install(nodo, t);
    }

    // ------------------------------------------------------------------------------------------
    // Chips de tipo y estado
    // ------------------------------------------------------------------------------------------

    public static Label chipTipo(Tipo tipo) {
        Label l = new Label(tipo == null ? "???" : tipo.getNombre().toUpperCase());
        l.getStyleClass().add("chip-tipo");
        l.setStyle("-fx-background-color: " + (tipo == null ? "#888" : tipo.getColor()) + ";");
        return l;
    }

    public static HBox chipsTipos(Tipo t1, Tipo t2) {
        HBox h = new HBox(5, chipTipo(t1));
        if (t2 != null) h.getChildren().add(chipTipo(t2));
        h.setAlignment(Pos.CENTER_LEFT);
        return h;
    }

    public static Label chipEstado(ListaEstados estado) {
        Label l = new Label(estado == null ? "" : estado.getAbreviatura());
        l.getStyleClass().add("chip-estado");
        if (estado != null) {
            l.setStyle("-fx-background-color: " + estado.getColor() + ";");
            tooltip(l, estado.getNombre() + ": " + estado.getDescripcion());
        }
        l.setVisible(estado != null);
        return l;
    }

    // ------------------------------------------------------------------------------------------
    // Iconos dibujados (la tienda no tenía imágenes de objetos)
    // ------------------------------------------------------------------------------------------

    /** Icono de un objeto dibujado con formas. */
    public static Node iconoObjeto(Objeto objeto, double tam) {
        return switch (objeto.getCategoria()) {
            case BALL -> iconoBall(objeto.getId(), tam);
            case CURACION -> iconoFrasco(objeto.getValor() >= 80 ? "#B04CE0" : "#E0485C", tam);
            case ESTAMINA -> iconoFrasco("#3C9CE8", tam);
            case REVIVIR -> iconoRevivir(tam);
        };
    }

    /** Poké Ball (1), Super Ball (2) o Ultra Ball (3). */
    public static Node iconoBall(int clase, double tam) {
        double r = tam / 2;
        Color arriba = switch (clase) {
            case 2 -> Color.web("#2F6FD8");
            case 3 -> Color.web("#2B2B2B");
            default -> Color.web("#E3350D");
        };
        Arc sup = new Arc(r, r, r, r, 0, 180);
        sup.setType(ArcType.CHORD);
        sup.setFill(arriba);
        Arc inf = new Arc(r, r, r, r, 180, 180);
        inf.setType(ArcType.CHORD);
        inf.setFill(Color.web("#F4F4F4"));
        Rectangle banda = new Rectangle(0, r - tam * 0.05, tam, tam * 0.1);
        banda.setFill(Color.web("#222"));
        Circle borde = new Circle(r, r, r);
        borde.setFill(Color.TRANSPARENT);
        borde.setStroke(Color.web("#222"));
        borde.setStrokeWidth(Math.max(1.5, tam * 0.06));
        Circle boton = new Circle(r, r, tam * 0.16);
        boton.setFill(Color.WHITE);
        boton.setStroke(Color.web("#222"));
        boton.setStrokeWidth(Math.max(1.2, tam * 0.05));
        Group g = new Group(sup, inf, banda, borde, boton);
        if (clase == 2) {
            Rectangle m1 = new Rectangle(tam * 0.18, tam * 0.2, tam * 0.16, tam * 0.08);
            Rectangle m2 = new Rectangle(tam * 0.66, tam * 0.2, tam * 0.16, tam * 0.08);
            m1.setFill(Color.web("#E3350D"));
            m2.setFill(Color.web("#E3350D"));
            g.getChildren().addAll(2, java.util.List.of(m1, m2));
        } else if (clase == 3) {
            Rectangle m1 = new Rectangle(tam * 0.2, tam * 0.12, tam * 0.14, tam * 0.3);
            Rectangle m2 = new Rectangle(tam * 0.66, tam * 0.12, tam * 0.14, tam * 0.3);
            m1.setFill(Color.web(AMARILLO));
            m2.setFill(Color.web(AMARILLO));
            g.getChildren().addAll(2, java.util.List.of(m1, m2));
        }
        return contenedorIcono(g, tam);
    }

    private static Node iconoFrasco(String color, double tam) {
        Rectangle cuerpo = new Rectangle(tam * 0.2, tam * 0.3, tam * 0.6, tam * 0.66);
        cuerpo.setArcWidth(tam * 0.3);
        cuerpo.setArcHeight(tam * 0.3);
        cuerpo.setFill(new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE,
                new Stop(0, Color.web(color).brighter()), new Stop(1, Color.web(color).darker())));
        cuerpo.setStroke(Color.web("#222"));
        cuerpo.setStrokeWidth(Math.max(1.5, tam * 0.05));
        Rectangle cuello = new Rectangle(tam * 0.36, tam * 0.12, tam * 0.28, tam * 0.2);
        cuello.setFill(Color.web("#DDE6F0"));
        cuello.setStroke(Color.web("#222"));
        cuello.setStrokeWidth(Math.max(1.2, tam * 0.04));
        Rectangle tapon = new Rectangle(tam * 0.32, tam * 0.04, tam * 0.36, tam * 0.1);
        tapon.setFill(Color.web("#8a5a2b"));
        tapon.setStroke(Color.web("#222"));
        tapon.setStrokeWidth(Math.max(1, tam * 0.03));
        Rectangle brillo = new Rectangle(tam * 0.3, tam * 0.42, tam * 0.08, tam * 0.36);
        brillo.setFill(Color.rgb(255, 255, 255, 0.55));
        brillo.setArcWidth(tam * 0.08);
        brillo.setArcHeight(tam * 0.08);
        return contenedorIcono(new Group(cuerpo, cuello, tapon, brillo), tam);
    }

    private static Node iconoRevivir(double tam) {
        javafx.scene.shape.Polygon rombo = new javafx.scene.shape.Polygon(
                tam * 0.5, tam * 0.04, tam * 0.92, tam * 0.5, tam * 0.5, tam * 0.96, tam * 0.08, tam * 0.5);
        rombo.setFill(new LinearGradient(0, 0, 1, 1, true, CycleMethod.NO_CYCLE,
                new Stop(0, Color.web("#FFF59D")), new Stop(1, Color.web("#F2B705"))));
        rombo.setStroke(Color.web("#7a5a00"));
        rombo.setStrokeWidth(Math.max(1.5, tam * 0.05));
        Rectangle v = new Rectangle(tam * 0.44, tam * 0.28, tam * 0.12, tam * 0.44);
        Rectangle h = new Rectangle(tam * 0.28, tam * 0.44, tam * 0.44, tam * 0.12);
        v.setFill(Color.WHITE);
        h.setFill(Color.WHITE);
        return contenedorIcono(new Group(rombo, v, h), tam);
    }

    private static Node contenedorIcono(Group g, double tam) {
        StackPane sp = new StackPane(g);
        sp.setMinSize(tam, tam);
        sp.setPrefSize(tam, tam);
        sp.setMaxSize(tam, tam);
        return sp;
    }

    /** Moneda para mostrar junto al dinero. */
    public static Node iconoMoneda(double tam) {
        Circle c = new Circle(tam / 2, tam / 2, tam / 2 - 1);
        c.setFill(new LinearGradient(0, 0, 1, 1, true, CycleMethod.NO_CYCLE,
                new Stop(0, Color.web("#FFE680")), new Stop(1, Color.web("#E0A800"))));
        c.setStroke(Color.web("#8a6500"));
        c.setStrokeWidth(2);
        Text p = new Text("₽");
        p.setFont(Font.font("Segoe UI", FontWeight.BOLD, tam * 0.62));
        p.setFill(Color.web("#8a6500"));
        StackPane sp = new StackPane(new Group(c), p);
        sp.setMinSize(tam, tam);
        sp.setMaxSize(tam, tam);
        return sp;
    }

    /** Separación vacía que crece (para empujar elementos en HBox/VBox). */
    public static Region espacio() {
        Region r = new Region();
        HBox.setHgrow(r, javafx.scene.layout.Priority.ALWAYS);
        javafx.scene.layout.VBox.setVgrow(r, javafx.scene.layout.Priority.ALWAYS);
        return r;
    }

    public static Insets margen(double v) {
        return new Insets(v);
    }
}
