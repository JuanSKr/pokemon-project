package pokemon.menus;

import pokemon.ui.App;
import pokemon.ui.Musica;
import pokemon.ui.Pantalla;
import pokemon.ui.UI;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

/** Perfil del entrenador: acceso a Avatar y a Equipos. */
public class MenuEntrenador {

    public static void mostrar() {
        new MenuEntrenador().abrir();
    }

    private void abrir() {
        Musica.reproducir("/aud/MenuEntrenador.wav");
        Pantalla pantalla = new Pantalla().fondoVideo("/vid/entrenador.mp4").estilo("Perfil.css");
        StackPane stackPane = pantalla.getContenido();

        ImageView barraView = new ImageView(UI.imagen("/img/entrenador-barra.png"));
        barraView.setId("barra");

        Button backButton = new Button();
        backButton.setId("back");
        backButton.setOnAction(e -> Menu.mostrar());
        UI.tooltip(backButton, "Volver al menú");

        Button muteButton = new Button();
        muteButton.setId("muteButton");
        muteButton.setOnAction(e -> Musica.alternarSilencio());
        UI.tooltip(muteButton, "Quitar / poner el sonido");

        Button equiposButton = new Button();
        equiposButton.setId("equiposButton");
        equiposButton.setOnAction(e -> Equipos.mostrar());
        UI.tooltip(equiposButton, "Organiza tus equipos y tu caja");

        Button avatarButton = new Button();
        avatarButton.setId("avatarButton");
        avatarButton.setOnAction(e -> Avatar.mostrar());
        UI.tooltip(avatarButton, "Elige tu aspecto de entrenador");

        stackPane.getChildren().add(barraView);
        StackPane.setAlignment(muteButton, Pos.TOP_RIGHT);
        stackPane.getChildren().add(muteButton);
        StackPane.setAlignment(backButton, Pos.TOP_LEFT);
        stackPane.getChildren().add(backButton);
        StackPane.setAlignment(equiposButton, Pos.CENTER);
        stackPane.getChildren().add(equiposButton);
        StackPane.setAlignment(avatarButton, Pos.CENTER_LEFT);
        stackPane.getChildren().add(avatarButton);

        App.mostrar(pantalla);
    }
}
