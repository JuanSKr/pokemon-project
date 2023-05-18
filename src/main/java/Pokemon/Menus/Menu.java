package Pokemon.Menus;


import Pokemon.Database.PokemonCRUD;
import Pokemon.Tienda.MenuTienda;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Menu extends Application {

    private static MediaView videoView = new MediaView();


    @Override
    public void start(Stage primaryStage) throws IOException {

        Image barra = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/barra.png")));
        ImageView barraView = new ImageView(barra);
        barraView.setId("barra");

        // Creamos el reproductor para el vídeo
        Media videoBackground = new Media(getClass().getResource("/vid/menu.mp4").toExternalForm());
        MediaPlayer reproductor = new MediaPlayer(videoBackground);
        videoView.setMediaPlayer(reproductor);

        // Ajustar el tamaño del video al tamaño de la ventana
        videoView.setPreserveRatio(false);
        videoView.setFitWidth(1080);
        videoView.setFitHeight(650);

        reproductor.setCycleCount(MediaPlayer.INDEFINITE);
        reproductor.play();

        Image logo = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/logo.png")));
        ImageView logoView = new ImageView(logo);
        logoView.setFitWidth(540);
        logoView.setFitHeight(230);
        logoView.setId("logo");

        // CREAMOS UN REPRODUCTOR DE MEDIOS PARA REPRODUCIR EL AUDIO
        Media audioMedia = new Media(getClass().getResource("/aud/Menu.wav").toExternalForm());
        MediaPlayer audioMediaPlayer = new MediaPlayer(audioMedia);
        audioMediaPlayer.setAutoPlay(true);
        audioMediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);

        // AQUI SE AÑADEN LOS BOTONES DEL MENU
        // El setId le asigna su ID dentro del css.
        Button combateButton = new Button();
        combateButton.setId("combateButton");
        Button capturarButton = new Button();
        capturarButton.setId("capturarButton");
        Button pokedexButton = new Button();
        pokedexButton.setId("pokedexButton");
        Button entrenadorButton = new Button();
        entrenadorButton.setId("entrenadorButton");
        Button tiendaButton = new Button();
        tiendaButton.setId("tiendaButton");
        // AGREGAR BOTÓN PARA SILENCIAR O REANUDAR EL SONIDO
        Button muteButton = new Button();
        muteButton.setId("muteButton");
        muteButton.setOnAction(e -> {
            if (audioMediaPlayer.isMute()) {
                audioMediaPlayer.setMute(false);
            } else {
                audioMediaPlayer.setMute(true);
            }
        });

        // -----------------------------------------------------------------------------------------------------------------

        combateButton.setOnAction(e -> {
            // AQUÍ PUEDES LLAMAR A LA CLASE COMBATE
            Scene currentScene = primaryStage.getScene();
            MenuCombate menuCombate = new MenuCombate(primaryStage, currentScene);
            Scene menuCombateScene = menuCombate.getScene();
            primaryStage.setScene(menuCombateScene);

        });
        capturarButton.setOnAction(e -> {
            // CÓDIGO PARA EJECUTAR CUANDO SE HACE CLIC EN EL BOTÓN
            // POR EJEMPLO, PARA MOSTRAR LA ESCENA MENUENTRENADOR:
            MenuExplorador menuExplorador = new MenuExplorador(primaryStage, primaryStage.getScene());
            menuExplorador.start(primaryStage);
            audioMediaPlayer.stop();
        });



        pokedexButton.setOnAction(e -> {
            // AQUÍ PUEDES LLAMAR A LA CLASE POKEDEX
            System.out.println(PokemonCRUD.generarPokemon());
            MenuPokedex menuPokedex = new MenuPokedex(primaryStage, primaryStage.getScene());
            menuPokedex.start(primaryStage);
            audioMediaPlayer.stop();
        });
        entrenadorButton.setOnAction(e -> {
            // DETENEMOS LA REPRODUCCIÓN DEL AUDIO
            MenuEntrenador menuEntrenador = new MenuEntrenador(primaryStage, primaryStage.getScene());
            menuEntrenador.start(primaryStage);
            audioMediaPlayer.stop();
        });
        tiendaButton.setOnAction(e -> {
            // DETENEMOS LA REPRODUCCIÓN DEL AUDIO
            MenuTienda menuTienda = new MenuTienda(primaryStage, primaryStage.getScene());
            menuTienda.start(primaryStage);
            audioMediaPlayer.stop();

            //Muestrar por Consola
            //Tienda.abrirTienda();
        });


        // CREAMOS UN VBOX
        StackPane root = new StackPane();
        root.getChildren().addAll(videoView, barraView,combateButton, capturarButton, entrenadorButton, pokedexButton, tiendaButton, muteButton, logoView);

        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(20));


        Scene scene = new Scene(root, 1080, 650);
        scene.getStylesheets().add("Menu.css");

        primaryStage.setTitle("Pokémon: Menu");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        abrirMenu();
    }

    public static void abrirMenu() {
        launch();
    }
}
