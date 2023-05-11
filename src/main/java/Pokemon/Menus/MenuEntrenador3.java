package Pokemon.Menus;

import Pokemon.Database.MySQL;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import Pokemon.Pokemon.Pokemon;

import java.util.Objects;

public class MenuEntrenador3 extends Application {
	private static final int SCENE_WIDTH = 1080;
	private static final int SCENE_HEIGHT = 650;
	private static final int PADDING_SIZE = 10;
	private static final int GAP_SIZE = 8;
	private Stage primaryStage;
	private Scene previousScene;
	Image image = new Image(getClass().getResourceAsStream("/img/Prueba1.gif"));

	

	@Override
	public void start(Stage primaryStage) {
		// Este método ya no es necesario
		GridPane gridPane = new GridPane();
		gridPane.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
		gridPane.setPadding(new Insets(PADDING_SIZE));
		BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
		gridPane.setBackground(new Background(backgroundImage));
		gridPane.setVgap(GAP_SIZE);
		gridPane.setHgap(GAP_SIZE);
		ImageView imagenLogo = new ImageView(image);
		//-------------------------MULTIMEDIA-----------------------------------------------
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
		// BOTON DE MUTE
		gridPane.add(muteButton, 1, 0);
		GridPane.setHalignment(muteButton, HPos.RIGHT);
		//-----------------------------------------------------------------------------------------------------------------
		// AGREGAR CONTENEDORES PARA EQUIPO1, EQUIPO2 Y CAJA
		VBox equipo1Container = new VBox();
		equipo1Container.setAlignment(Pos.CENTER);

		Label equipo1Label = new Label("Equipo1");

		Button equipo1ToEquipo2Button = new Button("Mover Equipo1 a Equipo2");

		equipo1ToEquipo2Button.setOnAction(e -> {
			// LÓGICA PARA MOVER ELEMENTO DE EQUIPO1 A EQUIPO2
		});

		Button equipo1ToCajaButton = new Button("Mover Equipo1 a Caja");

		equipo1ToCajaButton.setOnAction(e -> {
			// LÓGICA PARA MOVER ELEMENTO DE EQUIPO1 A CAJA
		});

		equipo1Container.getChildren().addAll(equipo1Label, equipo1ToEquipo2Button, equipo1ToCajaButton);

		VBox equipo2Container = new VBox();

		equipo2Container.setAlignment(Pos.CENTER);

		Label equipo2Label = new Label("Equipo2");

		Button equipo2ToEquipo1Button = new Button("Mover Equipo2 a Equipo1");

		equipo2ToEquipo1Button.setOnAction(e -> {
			// LÓGICA PARA MOVER ELEMENTO DE EQUIPO2 A EQUIPO1
		});

		Button equipo2ToCajaButton = new Button("Mover Equipo2 a Caja");

		equipo2ToCajaButton.setOnAction(e -> {
			// LÓGICA PARA MOVER ELEMENTO DE EQUIPO2 A CAJA
		});
		equipo2Container.getChildren().addAll(equipo2Label, equipo2ToEquipo1Button, equipo2ToCajaButton);

		VBox cajaContainer = new VBox();
		cajaContainer.setAlignment(Pos.CENTER);

		Label cajaLabel = new Label("Caja");

		Button cajaToEquipo1Button = new Button("Mover Caja a Equipo1");

		cajaToEquipo1Button.setOnAction(e -> {
			// LÓGICA PARA MOVER ELEMENTO DE CAJA A EQUIPO1
		});

		Button cajaToEquipo2Button = new Button("Mover Caja a Equipo2");

		cajaToEquipo2Button.setOnAction(e -> {
			// LÓGICA PARA MOVER ELEMENTO DE CAJA A EQUIPO2
		});

		cajaContainer.getChildren().addAll(cajaLabel, cajaToEquipo1Button, cajaToEquipo2Button);

		gridPane.add(equipo1Container, 0, 1);
		gridPane.add(equipo2Container, 1, 1);
		gridPane.add(cajaContainer, 2, 1);

		// MÉTODO DE INICIO DE LA APLICACIÓN
		Scene scene = new Scene(gridPane, SCENE_WIDTH, SCENE_HEIGHT);
	
	}
}