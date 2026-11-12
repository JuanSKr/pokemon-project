package pokemon.menus;

import pokemon.database.PokemonCRUD;
import pokemon.entrenador.Entrenador;
import pokemon.funcionalidad.Funcion;
import pokemon.pokemon.Pokemon;
import pokemon.ui.App;
import pokemon.ui.Comunes;
import pokemon.ui.Musica;
import pokemon.ui.Pantalla;
import pokemon.ui.UI;
import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

/**
 * Mapa para explorar: el entrenador se mueve con las flechas (o WASD) y, al acercarse a un
 * Pokémon salvaje, empieza un encuentro para intentar capturarlo.
 */
public class MenuExplorador {

    private static final double VELOCIDAD = 240;      // píxeles por segundo
    private static final int MAX_SALVAJES = 3;
    private static final double DISTANCIA_ENCUENTRO = 52;
    private static final double MIN_X = 30, MAX_X = Pantalla.ANCHO - 30, MIN_Y = 100, MAX_Y = Pantalla.ALTO - 24;

    private final Pantalla pantalla = new Pantalla();
    private final Pane mundo = new Pane();
    private final Set<KeyCode> teclas = EnumSet.noneOf(KeyCode.class);
    private final List<Salvaje> salvajes = new ArrayList<>();
    private Group entrenador;
    private ImageView spriteEntrenador;
    private double x = Pantalla.ANCHO / 2, y = Pantalla.ALTO / 2 + 40;
    private double proximaAparicion;
    private double tiempo;
    private boolean enEncuentro;
    private AnimationTimer bucle;

    /** Un Pokémon salvaje paseando por el mapa. */
    private static class Salvaje {
        Pokemon pokemon;
        Group nodo;
        ImageView sprite;
        double x, y, vx, vy, cambio, fase;
    }

    public static void mostrar() {
        new MenuExplorador().abrir();
    }

    private void abrir() {
        Musica.reproducir("/aud/Entrenador.wav");
        pantalla.fondoImagen("/img/Prueba1.jpeg");

        mundo.setPrefSize(Pantalla.ANCHO, Pantalla.ALTO);
        mundo.setMouseTransparent(true);
        pantalla.poner(mundo, 0, 0);

        // Entrenador
        spriteEntrenador = UI.vistaImagen(Entrenador.getFoto(), 64, 64);
        spriteEntrenador.setLayoutX(-32);
        spriteEntrenador.setLayoutY(-60);
        entrenador = new Group(sombra(22), spriteEntrenador);
        mundo.getChildren().add(entrenador);

        // Barra superior
        Button atras = Comunes.botonAtras(Menu::mostrar);
        atras.setFocusTraversable(false);
        pantalla.poner(atras, 18, 14);
        Button silencio = Comunes.botonSilencio();
        silencio.setFocusTraversable(false);
        pantalla.poner(silencio, 998, 14);

        int balls = PokemonCRUD.getMochila().totalBalls();
        Label textoBalls = UI.etiqueta("x " + balls, "texto-claro", "negrita");
        textoBalls.setStyle("-fx-font-size: 20px;");
        Label ayuda = UI.etiqueta("Muévete con las flechas o WASD y acércate a un Pokémon", "texto-claro");
        HBox barra = new HBox(12, UI.iconoBall(1, 26), textoBalls, separador(), ayuda);
        barra.setAlignment(Pos.CENTER);
        barra.getStyleClass().add("panel-oscuro");
        barra.setPrefWidth(640);
        pantalla.poner(barra, 220, 22);
        if (balls == 0) {
            ayuda.setText("¡No tienes Poké Balls! Cómpralas en la Tienda.");
            ayuda.setStyle("-fx-text-fill: #FFCB05;");
        }

        // Teclado (filtro: así funciona aunque el foco esté en un botón)
        pantalla.addEventFilter(KeyEvent.KEY_PRESSED, e -> {
            if (esTeclaMovimiento(e.getCode())) {
                teclas.add(e.getCode());
                e.consume();
            }
        });
        pantalla.addEventFilter(KeyEvent.KEY_RELEASED, e -> {
            teclas.remove(e.getCode());
        });

        for (int i = 0; i < MAX_SALVAJES; i++) aparecer();

        bucle = new AnimationTimer() {
            private long anterior = -1;

            @Override
            public void handle(long ahora) {
                if (anterior < 0) {
                    anterior = ahora;
                    return;
                }
                double dt = Math.min(0.05, (ahora - anterior) / 1e9);
                anterior = ahora;
                actualizar(dt);
            }
        };
        bucle.start();
        pantalla.setAlSalir(bucle::stop);

        actualizarEntrenador();
        App.mostrar(pantalla);
    }

    private static Region separador() {
        Region r = new Region();
        r.setPrefSize(2, 24);
        r.setStyle("-fx-background-color: rgba(255,255,255,0.35);");
        return r;
    }

    private static boolean esTeclaMovimiento(KeyCode c) {
        return switch (c) {
            case UP, DOWN, LEFT, RIGHT, W, A, S, D -> true;
            default -> false;
        };
    }

    private static Ellipse sombra(double radio) {
        Ellipse e = new Ellipse(0, 0, radio, radio * 0.38);
        e.setFill(Color.rgb(0, 0, 0, 0.3));
        return e;
    }

    // =====================================================================================
    // Bucle del juego
    // =====================================================================================

    private void actualizar(double dt) {
        if (enEncuentro) return;
        tiempo += dt;

        // Movimiento del entrenador (normalizado para que en diagonal no vaya más rápido)
        double dx = 0, dy = 0;
        if (teclas.contains(KeyCode.LEFT) || teclas.contains(KeyCode.A)) dx -= 1;
        if (teclas.contains(KeyCode.RIGHT) || teclas.contains(KeyCode.D)) dx += 1;
        if (teclas.contains(KeyCode.UP) || teclas.contains(KeyCode.W)) dy -= 1;
        if (teclas.contains(KeyCode.DOWN) || teclas.contains(KeyCode.S)) dy += 1;
        if (dx != 0 || dy != 0) {
            double len = Math.hypot(dx, dy);
            x = limitar(x + dx / len * VELOCIDAD * dt, MIN_X, MAX_X);
            y = limitar(y + dy / len * VELOCIDAD * dt, MIN_Y, MAX_Y);
            if (dx != 0) spriteEntrenador.setScaleX(dx < 0 ? -1 : 1);
            // Pequeño saltito al andar
            spriteEntrenador.setTranslateY(-Math.abs(Math.sin(tiempo * 14)) * 4);
        } else {
            spriteEntrenador.setTranslateY(0);
        }
        actualizarEntrenador();

        // Pokémon salvajes
        for (Salvaje s : new ArrayList<>(salvajes)) {
            s.cambio -= dt;
            if (s.cambio <= 0) nuevaDireccion(s);
            s.x += s.vx * dt;
            s.y += s.vy * dt;
            if (s.x < MIN_X || s.x > MAX_X) {
                s.vx = -s.vx;
                s.x = limitar(s.x, MIN_X, MAX_X);
            }
            if (s.y < MIN_Y || s.y > MAX_Y) {
                s.vy = -s.vy;
                s.y = limitar(s.y, MIN_Y, MAX_Y);
            }
            if (s.vx != 0) s.sprite.setScaleX(s.vx > 0 ? -1 : 1);
            s.sprite.setTranslateY(-Math.abs(Math.sin(tiempo * 6 + s.fase)) * 5);
            s.nodo.setLayoutX(s.x);
            s.nodo.setLayoutY(s.y);

            if (Math.hypot(s.x - x, s.y - y) < DISTANCIA_ENCUENTRO) {
                encontrar(s);
                return;
            }
        }

        // Aparecen Pokémon nuevos poco a poco
        if (salvajes.size() < MAX_SALVAJES && tiempo >= proximaAparicion) {
            aparecer();
            proximaAparicion = tiempo + Funcion.random(1.5, 3.5);
        }

        // Profundidad: lo que está más abajo se dibuja delante (viewOrder menor = más delante)
        for (Salvaje s : salvajes) s.nodo.setViewOrder(-s.y);
    }

    private void actualizarEntrenador() {
        entrenador.setLayoutX(x);
        entrenador.setLayoutY(y);
        entrenador.setViewOrder(-y);
    }

    private void aparecer() {
        Salvaje s = new Salvaje();
        s.pokemon = PokemonCRUD.generarPokemon();
        // Lejos del entrenador para que no aparezca encima
        int intentos = 0;
        do {
            s.x = Funcion.random(MIN_X + 20, MAX_X - 20);
            s.y = Funcion.random(MIN_Y + 30, MAX_Y - 20);
        } while (Math.hypot(s.x - x, s.y - y) < 240 && ++intentos < 30);
        s.fase = Math.random() * Math.PI * 2;
        s.sprite = UI.vistaImagen(s.pokemon.getFoto(), 72, 72);
        s.sprite.setLayoutX(-36);
        s.sprite.setLayoutY(-66);
        s.nodo = new Group(sombra(24), s.sprite);
        s.nodo.setLayoutX(s.x);
        s.nodo.setLayoutY(s.y);
        nuevaDireccion(s);
        mundo.getChildren().add(s.nodo);
        salvajes.add(s);

        s.nodo.setScaleX(0);
        s.nodo.setScaleY(0);
        ScaleTransition st = new ScaleTransition(Duration.millis(350), s.nodo);
        st.setToX(1);
        st.setToY(1);
        st.play();
    }

    private static void nuevaDireccion(Salvaje s) {
        if (Funcion.probabilidad(0.25)) {
            s.vx = 0;
            s.vy = 0;
        } else {
            double angulo = Math.random() * Math.PI * 2;
            double v = Funcion.random(35.0, 75.0);
            s.vx = Math.cos(angulo) * v;
            s.vy = Math.sin(angulo) * v;
        }
        s.cambio = Funcion.random(1.2, 3.2);
    }

    private void encontrar(Salvaje s) {
        enEncuentro = true;
        teclas.clear();
        bucle.stop();
        // Destello blanco antes del encuentro
        Region destello = new Region();
        destello.setStyle("-fx-background-color: white;");
        destello.setPrefSize(Pantalla.ANCHO, Pantalla.ALTO);
        destello.setOpacity(0);
        pantalla.poner(destello, 0, 0);
        FadeTransition f = new FadeTransition(Duration.millis(140), destello);
        f.setFromValue(0);
        f.setToValue(1);
        f.setAutoReverse(true);
        f.setCycleCount(4);
        f.setOnFinished(e -> Encuentro.mostrar(s.pokemon));
        f.play();
    }

    private static double limitar(double v, double min, double max) {
        return Math.max(min, Math.min(max, v));
    }
}
