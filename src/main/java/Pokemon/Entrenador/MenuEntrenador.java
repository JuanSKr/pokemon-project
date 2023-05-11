package Pokemon.Entrenador;

import Pokemon.Database.MySQL;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import Pokemon.Pokemon.Pokemon;

import java.util.Objects;

public class MenuEntrenador extends Application {
    private static final int SCENE_WIDTH = 1080;
    private static final int SCENE_HEIGHT = 650;
    private static final int PADDING_SIZE = 10;
    private static final int GAP_SIZE = 8;
    private Stage primaryStage;
    private Scene previousScene;
    Image image = new Image(getClass().getResourceAsStream("/img/Prueba1.gif"));

    
       

    @Override
    public void start(Stage primaryStage) {
       
        GridPane gridPane = new GridPane();
        gridPane.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
        gridPane.setPadding(new Insets(PADDING_SIZE));
        BackgroundImage backgroundImage = new BackgroundImage(image,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT
        );
        gridPane.setBackground(new Background(backgroundImage));
        gridPane.setVgap(GAP_SIZE);
        gridPane.setHgap(GAP_SIZE);
        ImageView imagenLogo = new ImageView(image);

        // CREAMOS UN REPRODUCTOR DE MEDIOS PARA REPRODUCIR EL AUDIO
        Media audioMedia = new Media(getClass().getResource("/aud/Prueba.wav").toExternalForm());
        MediaPlayer audioMediaPlayer = new MediaPlayer(audioMedia);
        audioMediaPlayer.setAutoPlay(true);
        audioMediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);

        // AGREGAR BOTÓN PARA REGRESAR AL MENÚ ANTERIOR
        Button backButton = new Button(" <-- ");
        backButton.setOnAction(e -> {
            primaryStage.setScene(previousScene);
            audioMediaPlayer.stop();
        });
        gridPane.add(backButton, 0, 0);
        GridPane.setHalignment(backButton, HPos.LEFT);

        // AGREGAR BOTÓN PARA SILENCIAR O REANUDAR EL SONIDO
        Button muteButton = new Button(" -ZzZ- ");
        muteButton.setOnAction(e -> {
            if (audioMediaPlayer.isMute()) {
                audioMediaPlayer.setMute(false);
            } else {
                audioMediaPlayer.setMute(true);
            }
        });
        //BOTON DE MUTE
        gridPane.add(muteButton, 1, 0);
        GridPane.setHalignment(muteButton, HPos.RIGHT);
        // MÉTODO DE INICIO DE LA APLICACIÓN
        Scene scene = new Scene(gridPane, SCENE_WIDTH, SCENE_HEIGHT);
       
    }
}
