package pokemon.menus;

import pokemon.database.PokemonCRUD;
import pokemon.entrenador.Entrenador;
import pokemon.ui.App;
import pokemon.ui.Comunes;
import pokemon.ui.Dialogo;
import pokemon.ui.Pantalla;
import pokemon.ui.UI;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

import java.util.ArrayList;
import java.util.List;

/** Elección del avatar del entrenador. */
public class Avatar {

    private static final String[] FOTOS = {
            "/img/gato.gif", "/img/entrenador2.gif", "/img/entrenador3.png",
            "/img/entrenador4.gif", "/img/entrenador5.gif", "/img/entrenador6.png"
    };
    private static final String[] NOMBRES = {"Gato", "Rojo", "Rubí", "Aura", "Bruno", "El Nano"};

    private final List<StackPane> opciones = new ArrayList<>();
    private String elegida = Entrenador.getFoto();

    public static void mostrar() {
        new Avatar().abrir();
    }

    private void abrir() {
        Pantalla pantalla = new Pantalla().fondoVideo("/vid/avatar.mp4").estilo("Avatar.css");

        ImageView titulo = UI.vistaImagen("/img/avatar.png", 440, 315);
        titulo.setSmooth(true);
        titulo.setId("txtView");
        pantalla.poner(titulo, 320, 0);

        double ancho = 140, sep = 26;
        double x0 = (Pantalla.ANCHO - (ancho * 6 + sep * 5)) / 2;
        for (int i = 0; i < FOTOS.length; i++) {
            String foto = FOTOS[i];
            ImageView fondo = UI.vistaImagen("/img/botones/casilla.png", ancho, ancho);
            fondo.setSmooth(true);
            ImageView imagen = UI.vistaImagen(foto, 96, 96);
            StackPane.setAlignment(imagen, javafx.geometry.Pos.TOP_CENTER);
            StackPane.setMargin(imagen, new javafx.geometry.Insets(10, 0, 0, 0));
            Label nombre = UI.etiqueta(NOMBRES[i], "nombre-avatar");
            StackPane.setAlignment(nombre, javafx.geometry.Pos.BOTTOM_CENTER);
            StackPane opcion = new StackPane(fondo, imagen, nombre);
            opcion.getStyleClass().add("opcion-avatar");
            opcion.setPrefSize(ancho, ancho);
            opcion.setOnMouseClicked(e -> elegir(foto));
            UI.tooltip(opcion, NOMBRES[i]);
            opcion.setUserData(foto);
            opciones.add(opcion);
            pantalla.poner(opcion, x0 + i * (ancho + sep), 326);
        }
        refrescar();

        Button aceptar = new Button();
        aceptar.setId("aceptar");
        aceptar.setOnAction(e -> {
            PokemonCRUD.updateFoto(elegida);
            Dialogo.info(pantalla, "¡Avatar actualizado!", "Así te verán en el mapa y en el menú.", MenuEntrenador::mostrar);
        });
        UI.tooltip(aceptar, "Guardar el avatar elegido");
        pantalla.poner(aceptar, 460, 504);

        pantalla.poner(Comunes.botonAtras(MenuEntrenador::mostrar), 18, 16);

        App.mostrar(pantalla);
    }

    private void elegir(String foto) {
        elegida = foto;
        refrescar();
    }

    private void refrescar() {
        for (StackPane o : opciones) {
            o.getStyleClass().remove("elegida");
            if (o.getUserData().equals(elegida)) o.getStyleClass().add("elegida");
        }
    }
}
