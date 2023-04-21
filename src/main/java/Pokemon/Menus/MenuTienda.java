package Pokemon.Menus;


import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class MenuTienda extends Application {

    private static final int SCENE_WIDTH = 1080;
    private static final int SCENE_HEIGHT = 650;
    private static final int PADDING_SIZE = 10;  
    private static final int GAP_SIZE = 8;

    private Stage primaryStage;
    private Scene previousScene;

    public MenuTienda(Stage primaryStage, Scene previousScene) {
        this.primaryStage = primaryStage;
        this.previousScene = previousScene;
    }

    @Override
    public void start(Stage primaryStage) {
        // Este método ya no es necesario
    }

    public Scene getScene() {
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        BackgroundSize backgroundSize = new BackgroundSize(1280, 720, false, false, false, false);
        BackgroundImage backgroundImage = new BackgroundImage(new Image("https://i.imgur.com/GGNNkkF.jpg"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        gridPane.setBackground(new Background(backgroundImage));
        gridPane.setVgap(8);
        gridPane.setHgap(10);

        // CREAMOS UN REPRODUCTOR DE MEDIOS PARA REPRODUCIR EL VÍDEO
        Media media = new Media(getClass().getResource("/vid/VideoTienda.mp4").toExternalForm());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);

        // CREAMOS UNA VISTA DE MEDIOS PARA MOSTRAR EL VÍDEO
        MediaView mediaView = new MediaView(mediaPlayer);
        mediaView.setFitWidth(SCENE_WIDTH);
        mediaView.setFitHeight(SCENE_HEIGHT);

        // AGREGAMOS LA VISTA DE MEDIOS AL CONTENEDOR
//        StackPane stackPane = new StackPane();
//        stackPane.getChildren().add(mediaView);
//        stackPane.setAlignment(Pos.CENTER);
//        gridPane.add(stackPane, 0, 0);
        
        
		/* CAJON
		 * BorderPane borderPane = new BorderPane(); TextArea textArea = new TextArea();
		 * borderPane.setRight(textArea);
		 */

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
        gridPane.add(muteButton, 1, 0);
        GridPane.setHalignment(muteButton, HPos.RIGHT);
        

        // MÉTODO DE INICIO DE LA APLICACIÓN
        Scene scene = new Scene(gridPane, SCENE_WIDTH, SCENE_HEIGHT);
        return scene;
    }
}