package pokemon.menus;

import pokemon.database.PokemonCRUD;
import pokemon.entrenador.Entrenador;
import pokemon.entrenador.Mochila;
import pokemon.funcionalidad.Funcion;
import pokemon.pokemon.Pokemon;
import pokemon.tienda.MenuTienda;
import pokemon.tienda.Objeto;
import pokemon.ui.App;
import pokemon.ui.Comunes;
import pokemon.ui.Dialogo;
import pokemon.ui.Musica;
import pokemon.ui.Pantalla;
import pokemon.ui.UI;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.ParallelTransition;
import javafx.animation.PathTransition;
import javafx.animation.PauseTransition;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.QuadCurveTo;
import javafx.util.Duration;

/** Encuentro con un Pokémon salvaje: lanzar Poké Balls para capturarlo o huir. */
public class Encuentro {

    /** Centro del sprite del Pokémon salvaje en la pantalla. */
    private static final double CENTRO_X = 700, CENTRO_Y = 300;

    private final Pantalla pantalla = new Pantalla();
    private final Pokemon salvaje;
    private final ImageView sprite;
    private final Label mensaje = UI.etiqueta("", "mensaje-encuentro");
    private final HBox acciones = new HBox(12);
    private int intentosFallidos;
    private boolean ocupado;

    public static void mostrar(Pokemon salvaje) {
        new Encuentro(salvaje).abrir();
    }

    private Encuentro(Pokemon salvaje) {
        this.salvaje = salvaje;
        // Nivel parecido al del equipo del jugador.
        PokemonCRUD.recargarEquipos();
        int nivel = (int) Math.round(Entrenador.equipo1.stream().mapToInt(Pokemon::getNivel).average().orElse(1));
        nivel = Math.max(1, nivel - Funcion.random(0, 2));
        salvaje.setNivel(nivel);
        for (int i = 1; i < nivel; i++) salvaje.subirStats();
        this.sprite = UI.vistaImagen(salvaje.getFoto(), 230, 210);
    }

    private void abrir() {
        Musica.reproducir("/aud/Base.wav");
        pantalla.fondoImagen("/img/Login.gif").estilo("Encuentro.css");

        // Pokémon salvaje sobre su sombra, flotando un poco
        Ellipse sombra = new Ellipse(CENTRO_X, CENTRO_Y + 112, 120, 28);
        sombra.setFill(Color.rgb(0, 0, 0, 0.35));
        pantalla.getLienzo().getChildren().add(sombra);
        StackPane cajaSprite = new StackPane(sprite);
        cajaSprite.setPrefSize(260, 230);
        cajaSprite.setAlignment(Pos.BOTTOM_CENTER);
        pantalla.poner(cajaSprite, CENTRO_X - 130, CENTRO_Y - 120);
        TranslateTransition flotar = new TranslateTransition(Duration.millis(1300), sprite);
        flotar.setByY(-10);
        flotar.setAutoReverse(true);
        flotar.setCycleCount(TranslateTransition.INDEFINITE);
        flotar.play();
        pantalla.setAlSalir(flotar::stop);

        // Ficha del Pokémon
        Label nombre = UI.etiqueta("¡Un " + salvaje.getNombre() + " salvaje!", "titulo-panel");
        nombre.setStyle("-fx-font-size: 26px;");
        Label nivel = UI.etiqueta("Nv. " + salvaje.getNivel() + "   ·   Nº " + String.format("%03d", salvaje.getIdPokedex()), "texto");
        Label dificultad = UI.etiqueta("Dificultad: " + estrellas(), "texto");
        UI.tooltip(dificultad, "Cuanto más fuerte es un Pokémon, más cuesta capturarlo. Las Super Ball y Ultra Ball ayudan.");
        VBox ficha = new VBox(8, nombre, UI.chipsTipos(salvaje.getTipo1(), salvaje.getTipo2()), nivel, dificultad);
        ficha.getStyleClass().add("panel");
        ficha.setPrefWidth(360);
        pantalla.poner(ficha, 30, 30);

        // Mensajes y acciones abajo
        mensaje.setWrapText(true);
        mensaje.setMaxWidth(1000);
        acciones.setAlignment(Pos.CENTER);
        VBox abajo = new VBox(12, mensaje, acciones);
        abajo.setAlignment(Pos.CENTER);
        abajo.getStyleClass().add("panel-oscuro");
        abajo.setPrefSize(1040, 150);
        pantalla.poner(abajo, 20, 484);

        pantalla.poner(Comunes.botonSilencio(), 998, 16);

        mensaje.setText("¿Qué vas a hacer?");
        prepararAcciones();
        App.mostrar(pantalla);
    }

    private String estrellas() {
        double base = probabilidadBase();
        int n = base > 0.65 ? 1 : base > 0.5 ? 2 : base > 0.35 ? 3 : base > 0.2 ? 4 : 5;
        return "★".repeat(n) + "☆".repeat(5 - n);
    }

    /** Probabilidad de captura con una Poké Ball normal: los Pokémon fuertes cuestan más. */
    private double probabilidadBase() {
        double p = 1.25 - salvaje.sumaStats() / 450.0 - (salvaje.getNivel() - 1) * 0.01;
        return Math.max(0.08, Math.min(0.9, p));
    }

    private double probabilidad(Objeto ball) {
        return Math.min(0.97, probabilidadBase() * ball.getValor() + intentosFallidos * 0.03);
    }

    private void prepararAcciones() {
        acciones.getChildren().clear();
        Mochila mochila = PokemonCRUD.getMochila();
        PokemonCRUD.recargarEquipos();
        boolean haySitio = Entrenador.equipo1.size() < Entrenador.TAMANO_EQUIPO
                || Entrenador.equipo2.size() < Entrenador.TAMANO_EQUIPO
                || Entrenador.caja.size() < Entrenador.TAMANO_CAJA;

        for (Objeto o : PokemonCRUD.getArticulos()) {
            if (o.getCategoria() != Objeto.Categoria.BALL) continue;
            int cantidad = mochila.cantidad(o);
            Label nombre = UI.etiqueta(o.getNombre(), "nombre-ball");
            Label datos = UI.etiqueta("x" + cantidad + "  ·  " + Math.round(probabilidad(o) * 100) + "%", "datos-ball");
            VBox textos = new VBox(0, nombre, datos);
            HBox contenido = new HBox(10, UI.iconoObjeto(o, 34), textos);
            contenido.setAlignment(Pos.CENTER_LEFT);
            Button b = UI.boton("");
            b.setGraphic(contenido);
            b.setDisable(cantidad == 0 || !haySitio);
            b.setOnAction(e -> lanzar(o));
            StackPane envoltorio = new StackPane(b);
            UI.tooltip(envoltorio, cantidad == 0 ? "No te quedan. Puedes comprarlas en la Tienda."
                    : "Probabilidad aproximada de captura: " + Math.round(probabilidad(o) * 100) + "%");
            acciones.getChildren().add(envoltorio);
        }

        if (mochila.totalBalls() == 0) {
            Button tienda = UI.boton("IR A LA TIENDA");
            tienda.setOnAction(e -> MenuTienda.mostrar());
            acciones.getChildren().add(tienda);
            mensaje.setText("No te quedan Poké Balls. ¡Compra más en la Tienda!");
        } else if (!haySitio) {
            mensaje.setText("No te queda sitio: tus equipos y tu caja están llenos. Libera algún Pokémon en Perfil > Equipos.");
        }

        Button huir = UI.botonClaro("HUIR");
        huir.setOnAction(e -> {
            if (!ocupado) MenuExplorador.mostrar();
        });
        acciones.getChildren().add(huir);
    }

    // =====================================================================================
    // Lanzamiento
    // =====================================================================================

    private void lanzar(Objeto ball) {
        if (ocupado) return;
        ocupado = true;
        acciones.setDisable(true);
        PokemonCRUD.cambiarCantidad(ball, -1);
        mensaje.setText(Entrenador.getNombre() + " lanzó una " + ball.getNombre() + ".");

        double p = probabilidad(ball);
        // Se calcula como en los juegos: tres sacudidas y en cada una puede escaparse.
        double porSacudida = Math.cbrt(p);
        int sacudidas = 0;
        while (sacudidas < 3 && Funcion.probabilidad(porSacudida)) sacudidas++;
        boolean capturado = sacudidas == 3;

        Node bola = UI.iconoObjeto(ball, 46);
        bola.setMouseTransparent(true);
        pantalla.poner(bola, 0, 0);
        bola.setLayoutX(-23);
        bola.setLayoutY(-23);

        Path trayectoria = new Path(new MoveTo(120, 520), new QuadCurveTo(380, 40, CENTRO_X, CENTRO_Y - 20));
        PathTransition vuelo = new PathTransition(Duration.millis(650), trayectoria, bola);
        RotateTransition giro = new RotateTransition(Duration.millis(650), bola);
        giro.setByAngle(720);
        ParallelTransition lanzamiento = new ParallelTransition(vuelo, giro);

        // El Pokémon entra en la ball
        ScaleTransition encoger = new ScaleTransition(Duration.millis(260), sprite);
        encoger.setToX(0);
        encoger.setToY(0);
        FadeTransition desvanecer = new FadeTransition(Duration.millis(260), sprite);
        desvanecer.setToValue(0);
        ParallelTransition entrar = new ParallelTransition(encoger, desvanecer);

        // La ball cae al suelo
        TranslateTransition caer = new TranslateTransition(Duration.millis(300), bola);
        caer.setByY(115);
        caer.setInterpolator(Interpolator.EASE_IN);

        SequentialTransition secuencia = new SequentialTransition(lanzamiento, entrar, caer);
        for (int i = 0; i < Math.min(sacudidas + 1, 3); i++) {
            secuencia.getChildren().add(new PauseTransition(Duration.millis(450)));
            if (i < sacudidas) secuencia.getChildren().add(sacudida(bola));
        }
        secuencia.getChildren().add(new PauseTransition(Duration.millis(400)));
        secuencia.setOnFinished(e -> {
            if (capturado) {
                capturado(bola);
            } else {
                escapado(bola);
            }
        });
        secuencia.play();
    }

    private static SequentialTransition sacudida(Node bola) {
        RotateTransition a = new RotateTransition(Duration.millis(110), bola);
        a.setByAngle(-28);
        RotateTransition b = new RotateTransition(Duration.millis(220), bola);
        b.setByAngle(56);
        RotateTransition c = new RotateTransition(Duration.millis(110), bola);
        c.setByAngle(-28);
        return new SequentialTransition(a, b, c);
    }

    private void escapado(Node bola) {
        intentosFallidos++;
        Musica.efecto("/aud/PikachuNoCaptura.wav");
        pantalla.getLienzo().getChildren().remove(bola);
        sprite.setScaleX(1);
        sprite.setScaleY(1);
        FadeTransition volver = new FadeTransition(Duration.millis(200), sprite);
        volver.setToValue(1);
        volver.play();

        double huye = Math.min(0.5, 0.12 + intentosFallidos * 0.08);
        if (Funcion.probabilidad(huye)) {
            mensaje.setText("¡Oh, no! ¡El Pokémon se ha escapado de la ball!");
            PauseTransition p = new PauseTransition(Duration.millis(900));
            p.setOnFinished(e -> huir());
            p.play();
        } else {
            mensaje.setText(frasesFallo[Funcion.random(0, frasesFallo.length - 1)]);
            ocupado = false;
            acciones.setDisable(false);
            prepararAcciones();
        }
    }

    private final String[] frasesFallo = {
            "¡Oh, no! ¡El Pokémon se ha escapado de la ball!",
            "¡Vaya! ¡Parecía que lo tenías!",
            "¡Uf! ¡Casi lo consigues!",
            "¡Se ha liberado! Prueba otra vez."
    };

    private void huir() {
        TranslateTransition t = new TranslateTransition(Duration.millis(450), sprite);
        t.setByX(500);
        FadeTransition f = new FadeTransition(Duration.millis(450), sprite);
        f.setToValue(0);
        ParallelTransition pt = new ParallelTransition(t, f);
        pt.setOnFinished(e -> Dialogo.info(pantalla, "¡" + salvaje.getNombre() + " ha huido!",
                "Otra vez será. Sigue explorando para encontrar más Pokémon.", MenuExplorador::mostrar));
        pt.play();
    }

    private void capturado(Node bola) {
        Musica.reproducirUnaVez("/aud/PokemonCaptura.wav");
        mensaje.setText("¡Ya está! ¡" + salvaje.getNombre() + " atrapado!");
        ImageView destello = UI.vistaImagen("/img/PokemonCaptura.gif", 1080, 650);
        destello.setPreserveRatio(false);
        destello.setOpacity(0);
        destello.setMouseTransparent(true);
        pantalla.poner(destello, 0, 0);
        FadeTransition f = new FadeTransition(Duration.millis(300), destello);
        f.setToValue(0.85);
        f.setAutoReverse(true);
        f.setCycleCount(2);
        f.setOnFinished(e -> {
            pantalla.getLienzo().getChildren().remove(destello);
            pedirMote();
        });
        f.play();
    }

    private void pedirMote() {
        Dialogo.pedirTexto(pantalla, "¡Has capturado a " + salvaje.getNombre() + "!",
                "¿Quieres ponerle un mote? (Déjalo vacío para usar su nombre)", "", 15,
                "GUARDAR", "SIN MOTE",
                mote -> guardar(mote), () -> guardar(""));
    }

    private void guardar(String mote) {
        salvaje.setMote(mote);
        int equipo = PokemonCRUD.guardarCapturado(salvaje);
        String destino = switch (equipo) {
            case 1 -> "tu equipo titular";
            case 2 -> "el equipo 2";
            case 3 -> "la caja";
            default -> null;
        };
        if (destino == null) {
            Dialogo.info(pantalla, "No queda sitio", "Tus equipos y tu caja están llenos. Libera algún Pokémon.",
                    MenuExplorador::mostrar);
            return;
        }
        Dialogo.dosOpciones(pantalla, "¡" + salvaje.getNombreMostrado() + " se une a tu aventura!",
                "Lo encontrarás en " + destino + ". Puedes organizarlo en Perfil > Equipos.",
                "VOLVER AL MENÚ", Menu::mostrar, "SEGUIR EXPLORANDO", MenuExplorador::mostrar);
    }
}
