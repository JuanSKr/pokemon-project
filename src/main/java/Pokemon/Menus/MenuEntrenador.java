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
import javafx.stage.Stage;
import java.util.Objects;

public class MenuEntrenador extends Application {
    private static final double WINDOW_WIDTH = 1080;
    private static final double WINDOW_HEIGHT = 650;
    private Scene previousScene;
    private Stage primaryStage;


    public MenuEntrenador(Stage primaryStage, Scene previousScene) {
        this.primaryStage = primaryStage;
        this.previousScene = previousScene;
    }

    @Override
    public void start(Stage primaryStage) {


        Image backgroundImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/Prueba.jpeg")));
        ImageView backgroundImageView = new ImageView(backgroundImage);
        backgroundImageView.setFitWidth(1080);
        backgroundImageView.setFitHeight(650);
        // -------------------------MULTIMEDIA-----------------------------------------------
        // CREAMOS UN REPRODUCTOR DE MEDIOS PARA REPRODUCIR EL AUDIO
        Media audioMedia = new Media(getClass().getResource("/aud/MenuEntrenador.wav").toExternalForm());
        MediaPlayer audioMediaPlayer = new MediaPlayer(audioMedia);
        audioMediaPlayer.setAutoPlay(true);
        audioMediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);

        StackPane stackPane = new StackPane();// BOTONES MUTE Y BACK
     

        // AGREGAR BOTÓN PARA REGRESAR AL MENÚ ANTERIOR
        Button backButton = new Button(" <-- ");
        backButton.setOnAction(e -> {
            primaryStage.setScene(previousScene);
            audioMediaPlayer.stop();
        });
        // AGREGAR BOTÓN PARA SILENCIAR O REANUDAR EL SONIDO
        Button muteButton = new Button(" -ZzZ- ");
        muteButton.setOnAction(e -> {
            if (audioMediaPlayer.isMute()) {
                audioMediaPlayer.setMute(false);
            } else {
                audioMediaPlayer.setMute(true);
            }
        });
//----------------------------BOTONES-------------------------------------------------------------------------------------------
        Button equiposButton = new Button("equiposButton");
        equiposButton.setOnAction(e -> {
            // LLAMADA A LA ESCENA EQUIPOS
            Equipos equipos = new Equipos(primaryStage, primaryStage.getScene());
            equipos.start(primaryStage);
        });
        Button avatarButton = new Button("Avatar");
        avatarButton.setOnAction(e -> {
            // LLAMADA A LA ESCENA AVATAR
            Avatar avatar = new Avatar(primaryStage, primaryStage.getScene());
            avatar.start(primaryStage);
        });

       

        
        //-------------------------------------------------------------------------------------------------------------------------------
        stackPane.getChildren().add(backgroundImageView);
        StackPane.setAlignment(muteButton, Pos.TOP_RIGHT);
        stackPane.getChildren().addAll(muteButton);// Añadir backgroundImageView para mostrar la imagen
        StackPane.setAlignment(backButton, Pos.TOP_LEFT);
        stackPane.getChildren().add(backButton);
        StackPane.setAlignment(equiposButton, Pos.CENTER);
        stackPane.getChildren().add(equiposButton);
        StackPane.setAlignment(avatarButton, Pos.CENTER_LEFT);
        stackPane.getChildren().add(avatarButton);
        
        
        Scene scene = new Scene(stackPane, WINDOW_WIDTH, WINDOW_HEIGHT);
       

        primaryStage.setScene(scene);
        primaryStage.show();
    }  
}

