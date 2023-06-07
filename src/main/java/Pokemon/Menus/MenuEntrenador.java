package Pokemon.Menus;


import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Objects;

public class MenuEntrenador extends Application {
    private static final double WINDOW_WIDTH = 1080;
    private static final double WINDOW_HEIGHT = 650;
    private Scene previousScene;
    private Stage primaryStage;

    private static MediaView videoView = new MediaView();

    Media videoBackground = new Media(getClass().getResource("/vid/entrenador.mp4").toExternalForm());
    MediaPlayer reproductor = new MediaPlayer(videoBackground);


    public MenuEntrenador(Stage primaryStage, Scene previousScene) {
        this.primaryStage = primaryStage;
        this.previousScene = previousScene;
    }

    @Override
    public void start(Stage primaryStage) {

        videoView.setMediaPlayer(reproductor);
        videoView.setPreserveRatio(false);
        videoView.setFitWidth(1080);
        videoView.setFitHeight(650);

        reproductor.setCycleCount(MediaPlayer.INDEFINITE);
        reproductor.play();


        // -------------------------MULTIMEDIA-----------------------------------------------
        // CREAMOS UN REPRODUCTOR DE MEDIOS PARA REPRODUCIR EL AUDIO
        Media audioMedia = new Media(getClass().getResource("/aud/MenuEntrenador.wav").toExternalForm());
        MediaPlayer audioMediaPlayer = new MediaPlayer(audioMedia);
        audioMediaPlayer.setAutoPlay(true);
        audioMediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);

        Image barra = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/entrenador-barra.png")));
        ImageView barraView = new ImageView(barra);
        barraView.setId("barra");

        StackPane stackPane = new StackPane();// BOTONES MUTE Y BACK
     

        // AGREGAR BOTÓN PARA REGRESAR AL MENÚ ANTERIOR
        Button backButton = new Button();
        backButton.setId("back");
        backButton.setOnAction(e -> {
            primaryStage.setScene(previousScene);
            audioMediaPlayer.stop();
        });
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
//----------------------------BOTONES-------------------------------------------------------------------------------------------
        Button equiposButton = new Button();
        equiposButton.setId("equiposButton");
        equiposButton.setOnAction(e -> {
            // LLAMADA A LA ESCENA EQUIPOS
            Equipos equipos = new Equipos(primaryStage, primaryStage.getScene());
            equipos.start(primaryStage);
        });
        Button avatarButton = new Button();
        avatarButton.setId("avatarButton");
        avatarButton.setOnAction(e -> {
            // LLAMADA A LA ESCENA AVATAR
            Avatar avatar = new Avatar(primaryStage, primaryStage.getScene());
            avatar.start(primaryStage);
        });



        stackPane.getChildren().add(videoView);
        stackPane.getChildren().add(barraView);
        StackPane.setAlignment(muteButton, Pos.TOP_RIGHT);
        stackPane.getChildren().addAll(muteButton);
        StackPane.setAlignment(backButton, Pos.TOP_LEFT);
        stackPane.getChildren().add(backButton);
        StackPane.setAlignment(equiposButton, Pos.CENTER);
        stackPane.getChildren().add(equiposButton);
        StackPane.setAlignment(avatarButton, Pos.CENTER_LEFT);
        stackPane.getChildren().add(avatarButton);
        
        Scene scene = new Scene(stackPane, WINDOW_WIDTH, WINDOW_HEIGHT);
        scene.getStylesheets().add("Perfil.css");
       

        primaryStage.setScene(scene);
        primaryStage.show();
    }  
}

