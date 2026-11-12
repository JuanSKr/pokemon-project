package pokemon.ui;

import pokemon.entrenador.Entrenador;
import pokemon.funcionalidad.Funcion;
import pokemon.pokemon.Pokemon;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 * Piezas de interfaz que se repiten en varias pantallas: botón de volver, botón de silencio,
 * contador de dinero y tarjeta de Pokémon.
 */
public final class Comunes {

    private Comunes() {
    }

    /** Botón amarillo con la flecha de volver (el de Perfil / Pokédex). */
    public static Button botonAtras(Runnable accion) {
        Button b = UI.botonImagen("/img/botones/entrenador-atras.png", 64, 64);
        b.setOnAction(e -> accion.run());
        UI.tooltip(b, "Volver");
        return b;
    }

    /** Botón de silencio. Se ilumina cuando el sonido está quitado. */
    public static Button botonSilencio() {
        Button b = UI.botonImagen("/img/botones/pokedex-mute.png", 64, 64);
        Runnable refrescar = () -> {
            b.setOpacity(Musica.estaSilenciado() ? 1.0 : 0.75);
            b.setStyle(Musica.estaSilenciado()
                    ? "-fx-effect: dropshadow(gaussian, #FFCB05, 14, 0.6, 0, 0);" : "");
        };
        b.setOnAction(e -> Musica.alternarSilencio());
        // Escucha débil: así el botón se puede liberar cuando se cambia de pantalla.
        javafx.beans.value.ChangeListener<Boolean> escucha = (o, a, n) -> refrescar.run();
        b.getProperties().put("escuchaSilencio", escucha);
        Musica.silencioProperty().addListener(new javafx.beans.value.WeakChangeListener<>(escucha));
        refrescar.run();
        UI.tooltip(b, "Quitar / poner el sonido");
        return b;
    }

    /** Contador de dinero del entrenador. Llama a refrescar() cuando cambie el dinero. */
    public static class Dinero extends HBox {
        private final Label texto = new Label();

        public Dinero() {
            getStyleClass().add("dinero");
            getChildren().addAll(UI.iconoMoneda(26), texto);
            setMaxSize(USE_PREF_SIZE, USE_PREF_SIZE);
            refrescar();
        }

        public void refrescar() {
            texto.setText(Funcion.dinero(Entrenador.getDinero()));
        }
    }

    /** Tarjeta pequeña con el sprite, nombre y nivel de un Pokémon. */
    public static VBox tarjetaPokemon(Pokemon p, double ancho, double alto) {
        VBox v = new VBox(2);
        v.getStyleClass().add("tarjeta");
        v.setAlignment(Pos.CENTER);
        v.setPrefSize(ancho, alto);
        v.setMinSize(ancho, alto);
        v.setMaxSize(ancho, alto);
        if (p == null) {
            v.getStyleClass().add("vacia");
            return v;
        }
        double tamSprite = Math.min(ancho - 16, alto - 38);
        ImageView sprite = UI.vistaImagen(p.getFoto(), tamSprite, tamSprite);
        StackPane caja = new StackPane(sprite);
        caja.setPrefSize(tamSprite, tamSprite);
        caja.setMinSize(tamSprite, tamSprite);
        Label nombre = UI.etiqueta(p.getNombreMostrado(), "nombre-tarjeta");
        nombre.setMaxWidth(ancho - 8);
        Label nivel = UI.etiqueta("Nv. " + p.getNivel(), "nivel-tarjeta");
        v.getChildren().addAll(caja, nombre, nivel);
        UI.tooltip(v, p.getNombreMostrado() + (p.getMote() != null && !p.getMote().isBlank() ? " (" + p.getNombre() + ")" : "")
                + " · Nv. " + p.getNivel());
        return v;
    }
}
