package Pokemon.Combate;

import Pokemon.Database.PokemonCRUD;
import Pokemon.Entrenador.Entrenador;
import Pokemon.Pokemon.Pokemon;
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

public class Selector extends Application {

    private Scene previousScene;
    private Stage primaryStage;

    private static MediaView videoView = new MediaView();
    static int opcionSeleccionada;

    public Selector(Stage primaryStage, Scene previousScene) {
        this.primaryStage = primaryStage;
        this.previousScene = previousScene;
    }

    @Override
    public void start(Stage primaryStage) {

        Image pokemon1 = setImagen(0);
        ImageView pokemon1View = new ImageView(pokemon1);
        pokemon1View.setFitWidth(100);
        pokemon1View.setFitHeight(70);
        pokemon1View.setId("pokemon1");
        pokemon1View.setMouseTransparent(true);

        Image pokemon2 = setImagen(1);
        ImageView pokemon2View = new ImageView(pokemon2);
        pokemon2View.setFitWidth(100);
        pokemon2View.setFitHeight(70);
        pokemon2View.setId("pokemon2");
        pokemon2View.setMouseTransparent(true);

        Image pokemon3 = setImagen(2);
        ImageView pokemon3View = new ImageView(pokemon3);
        pokemon3View.setFitWidth(100);
        pokemon3View.setFitHeight(70);
        pokemon3View.setId("pokemon3");
        pokemon3View.setMouseTransparent(true);

        Image pokemon4 = setImagen(3);
        ImageView pokemon4View = new ImageView(pokemon4);
        pokemon4View.setFitWidth(100);
        pokemon4View.setFitHeight(70);
        pokemon4View.setId("pokemon4");
        pokemon4View.setMouseTransparent(true);

        Image pokemon5 = setImagen(4);
        ImageView pokemon5View = new ImageView(pokemon5);
        pokemon5View.setFitWidth(100);
        pokemon5View.setFitHeight(70);
        pokemon5View.setId("pokemon5");
        pokemon5View.setMouseTransparent(true);

        Image pokemon6 = setImagen(5);
        ImageView pokemon6View = new ImageView(pokemon6);
        pokemon6View.setFitWidth(100);
        pokemon6View.setFitHeight(70);
        pokemon6View.setId("pokemon6");
        pokemon6View.setMouseTransparent(true);

        // Creamos el reproductor para el vídeo
        Media videoBackground = new Media(getClass().getResource("/vid/seleccion.mp4").toExternalForm());
        MediaPlayer reproductor = new MediaPlayer(videoBackground);
        videoView.setMediaPlayer(reproductor);

        // Ajustar el tamaño del video al tamaño de la ventana
        videoView.setPreserveRatio(false);
        videoView.setFitWidth(1080);
        videoView.setFitHeight(650);

        reproductor.setCycleCount(MediaPlayer.INDEFINITE);
        reproductor.play();


        // CREAMOS UN REPRODUCTOR DE MEDIOS PARA REPRODUCIR EL AUDIO
        Media audioMedia = new Media(getClass().getResource("/aud/Menu.wav").toExternalForm());
        MediaPlayer audioMediaPlayer = new MediaPlayer(audioMedia);
        audioMediaPlayer.setAutoPlay(true);
        audioMediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);

        Image selectorTxt = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/selector.png")));
        ImageView selectorView = new ImageView(selectorTxt);
        selectorView.setFitWidth(720);
        selectorView.setFitHeight(350);
        selectorView.setId("selectorTxt");

        Button opcion1 = new Button();
        opcion1.setId("opcion1");
        Button opcion2 = new Button();
        opcion2.setId("opcion2");
        Button opcion3 = new Button();
        opcion3.setId("opcion3");
        Button opcion4 = new Button();
        opcion4.setId("opcion4");
        Button opcion5 = new Button();
        opcion5.setId("opcion5");
        Button opcion6 = new Button();
        opcion6.setId("opcion6");
//        muteButton.setOnAction(e -> {
//            if (audioMediaPlayer.isMute()) {
//                audioMediaPlayer.setMute(false);
//            } else {
//                audioMediaPlayer.setMute(true);
//            }
//        });
//
//        // -----------------------------------------------------------------------------------------------------------------
//
        opcion1.setOnAction(e ->

        {
            opcionSeleccionada = 1;
            Scene currentScene = primaryStage.getScene();
            CombateGrafico combate = new CombateGrafico(primaryStage, currentScene);
            Scene menuCombateScene = combate.getScene();
            primaryStage.setScene(menuCombateScene);
        });

        opcion2.setOnAction(e ->

        {
            opcionSeleccionada = 2;
            Scene currentScene = primaryStage.getScene();
            CombateGrafico combate = new CombateGrafico(primaryStage, currentScene);
            Scene menuCombateScene = combate.getScene();
            primaryStage.setScene(menuCombateScene);
        });

        opcion3.setOnAction(e ->

        {
            opcionSeleccionada = 3;
            Scene currentScene = primaryStage.getScene();
            CombateGrafico combate = new CombateGrafico(primaryStage, currentScene);
            Scene menuCombateScene = combate.getScene();
            primaryStage.setScene(menuCombateScene);
        });

        opcion4.setOnAction(e ->

        {
            opcionSeleccionada = 4;
            Scene currentScene = primaryStage.getScene();
            CombateGrafico combate = new CombateGrafico(primaryStage, currentScene);
            Scene menuCombateScene = combate.getScene();
            primaryStage.setScene(menuCombateScene);
        });

        opcion5.setOnAction(e ->

        {
            opcionSeleccionada = 5;
            Scene currentScene = primaryStage.getScene();
            CombateGrafico combate = new CombateGrafico(primaryStage, currentScene);
            Scene menuCombateScene = combate.getScene();
            primaryStage.setScene(menuCombateScene);
        });

        opcion6.setOnAction(e ->

        {
            opcionSeleccionada = 6;
            Scene currentScene = primaryStage.getScene();
            CombateGrafico combate = new CombateGrafico(primaryStage, currentScene);
            Scene menuCombateScene = combate.getScene();
            primaryStage.setScene(menuCombateScene);
        });


        // CREAMOS UN VBOX
        StackPane root = new StackPane();
//        root.getChildren().addAll(videoView, barraView,combateButton, capturarButton, entrenadorButton, pokedexButton, tiendaButton, muteButton);
        root.getChildren().

                addAll(videoView, selectorView, opcion1, opcion2, opcion3, opcion4, opcion5, opcion6, pokemon1View,
                        pokemon2View, pokemon3View, pokemon4View, pokemon5View, pokemon6View);

        root.setAlignment(Pos.CENTER);
        root.setPadding(new

                Insets(20));


        Scene scene = new Scene(root, 1080, 650);
        scene.getStylesheets().add("Selector.css");

        primaryStage.setTitle("Pokémon: Selecciona tu Pokémon");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public Image setImagen(int posicion) {
        Entrenador.equipo1.clear();
        PokemonCRUD.getEquipo1(Entrenador.equipo1, PokemonCRUD.idEntrenador());

        Image imagen = null;

        if (Entrenador.equipo1.isEmpty()) {
            opcionSeleccionada = 0; // Reiniciar desde la posición 0
            // Aquí puedes realizar otras acciones, como mostrar un mensaje de que no hay Pokémon disponibles.
        } else {
            if (Entrenador.equipo1.get(posicion) != null) {
                Pokemon pokemon = Entrenador.equipo1.get(posicion);
                imagen = new Image(Objects.requireNonNull(getClass().getResourceAsStream(pokemon.getFoto())));
            }
        }
        return imagen;
    }


    public static int getOpcionSeleccionada() {
        return opcionSeleccionada;
    }


    public static void main(String[] args) {
        abrirMenu();
    }

    public static void abrirMenu() {
        launch();
    }


}



