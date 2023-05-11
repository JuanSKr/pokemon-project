package Pokemon.Menus;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
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
		Image backgroundImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/pokedex.png")));
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
		stackPane.setAlignment(Pos.TOP_LEFT);

		// AGREGAR BOTÓN PARA REGRESAR AL MENÚ ANTERIOR
		Button backButton = new Button(" <-- ");
		backButton.setOnAction(e -> {
			primaryStage.setScene(previousScene);
			audioMediaPlayer.play();
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
//-----------------------------------------------------------------------------------------------------------------------
		// Crear contenedor para equipo1
		VBox equipo1Container = new VBox();
		equipo1Container.setCursor(Cursor.CLOSED_HAND);// CAMBIA EL CURSOR
		equipo1Container.setAlignment(Pos.CENTER);

		Label equipo1Label = new Label("Equipo1");
		equipo1Label.setFont(new Font("Arial", 15));

		ListView<String> equipo1ListView = new ListView<>();
		// Añadir aqui configuraion de tamaño
		equipo1ListView.getItems().addAll("Elemento 1", "Elemento 2", "Elemento 3");

		/*
		 * Button equipo1ToEquipo2Button = new Button("Mover Equipo1 a Equipo2");
		 * 
		 * Button equipo1ToCajaButton = new Button("Mover Equipo1 a Caja");
		 */

		equipo1Container.getChildren().addAll(equipo1Label, equipo1ListView);

		// Crear contenedor para equipo2
		VBox equipo2Container = new VBox();
		equipo2Container.setCursor(Cursor.CLOSED_HAND);// CAMBIA EL CURSOR
		equipo2Container.setAlignment(Pos.CENTER);

		Label equipo2Label = new Label("Equipo2");
		equipo2Label.setFont(new Font("Arial", 15));

		ListView<String> equipo2ListView = new ListView<>();
		equipo2ListView.getItems().addAll("Elemento 10", "Elemento 12", "Elemento 13");

		/*
		 * Button equipo2ToEquipo1Button = new Button("Mover Equipo2 a Equipo1");
		 * 
		 * Button equipo2ToCajaButton = new Button("Mover Equipo2 a Caja");
		 */

		equipo2Container.getChildren().addAll(equipo2Label, equipo2ListView);

		// Crear contenedor para caja
		VBox cajaContainer = new VBox();
		cajaContainer.setCursor(Cursor.CLOSED_HAND);// CAMBIA EL CURSOR
		cajaContainer.setAlignment(Pos.CENTER);

		Label cajaLabel = new Label("Caja");
		cajaLabel.setFont(new Font("Arial", 15));

		ListView<String> cajaListView = new ListView<>();
		cajaListView.getItems().addAll("Elemento 21", "Elemento 22", "Elemento 23");

		/*
		 * Button cajaToEquipo1Button = new Button("Mover Caja a Equipo1");
		 * 
		 * Button cajaToEquipo2Button = new Button("Mover Caja a Equipo2");
		 */

		cajaContainer.getChildren().addAll(cajaLabel, cajaListView);

		
//------------------------------CODIGO PARA ARRASTRAR Y SOLTAR ----------------------------------------------------------------------
		// PERMITIR QUE LOS ELEMENTOS DE EQUIPO2LISTVIEW SEAN ARRASTRADOS
		equipo1ListView.setOnDragDetected(event -> {
			String selectedItem = equipo1ListView.getSelectionModel().getSelectedItem();
			if (selectedItem != null) {
				Dragboard dragboard = equipo1ListView.startDragAndDrop(TransferMode.MOVE);
				ClipboardContent content = new ClipboardContent();
				content.putString(selectedItem);
				dragboard.setContent(content);
			}
			event.consume();
		});
		// PERMITIR QUE LOS ELEMENTOS DE EQUIPO2LISTVIEW SEAN ARRASTRADOS
		equipo2ListView.setOnDragDetected(event -> {
			String selectedItem = equipo2ListView.getSelectionModel().getSelectedItem();
			if (selectedItem != null) {
				Dragboard dragboard = equipo2ListView.startDragAndDrop(TransferMode.MOVE);
				ClipboardContent content = new ClipboardContent();
				content.putString(selectedItem);
				dragboard.setContent(content);
			}
			event.consume();
		});
		// PERMITIR QUE LOS ELEMENTOS DE CAJALISTVIEW SEAN ARRASTRADOS
		cajaListView.setOnDragDetected(event -> {
			String selectedItem = cajaListView.getSelectionModel().getSelectedItem();
			if (selectedItem != null) {
				Dragboard dragboard = cajaListView.startDragAndDrop(TransferMode.MOVE);
				ClipboardContent content = new ClipboardContent();
				content.putString(selectedItem);
				dragboard.setContent(content);
			}
			event.consume();
		});
		// PERMITIR QUE LOS ELEMENTOS SEAN SOLTADOS EN EQUIPO1LISTVIEW
		equipo1ListView.setOnDragOver(event -> {
			if (event.getGestureSource() != equipo1ListView && event.getDragboard().hasString()) {
				event.acceptTransferModes(TransferMode.MOVE);
			}
			event.consume();
		});

		equipo1ListView.setOnDragDropped(event -> {
		    Dragboard dragboard = event.getDragboard();
		    boolean success = false;
		    if (dragboard.hasString() && equipo1ListView.getItems().size() < 6) {
		        String draggedItem = dragboard.getString();
		        ((ListView<String>) event.getGestureSource()).getItems().remove(draggedItem); // Eliminar del contenedor de origen
		        equipo1ListView.getItems().add(draggedItem);
		        success = true;
		    } else if (dragboard.hasString()) {
		        Alert alert = new Alert(AlertType.INFORMATION);
		        alert.setTitle("Información");
		        alert.setHeaderText(null);
		        alert.setContentText("El contenedor Equipo1 ha alcanzado su capacidad máxima de 6 elementos.");
		        alert.showAndWait();
		    }
		    event.setDropCompleted(success);
		    event.consume();
		});

		// PERMITIR QUE LOS ELEMENTOS SEAN SOLTADOS EN EQUIPO2LISTVIEW
		equipo2ListView.setOnDragOver(event -> {
			if (event.getGestureSource() != equipo2ListView && event.getDragboard().hasString()) {
				event.acceptTransferModes(TransferMode.MOVE);
			}
			event.consume();
		});

		equipo2ListView.setOnDragDropped(event -> {
		    Dragboard dragboard = event.getDragboard();
		    boolean success = false;
		    if (dragboard.hasString() && equipo2ListView.getItems().size() < 6) {
		        String draggedItem = dragboard.getString();
		        ((ListView<String>) event.getGestureSource()).getItems().remove(draggedItem); // Eliminar del contenedor de origen
		        equipo2ListView.getItems().add(draggedItem);
		        success = true;
		    } else if (dragboard.hasString()) {
		        Alert alert = new Alert(AlertType.INFORMATION);
		        alert.setTitle("Información");
		        alert.setHeaderText(null);
		        alert.setContentText("El contenedor Equipo2 ha alcanzado su capacidad máxima de 6 elementos.");
		        alert.showAndWait();
		    }
		    event.setDropCompleted(success);
		    event.consume();
		});
		// PERMITIR QUE LOS ELEMENTOS SEAN SOLTADOS EN CAJALISTVIEW
		cajaListView.setOnDragOver(event -> {
			if (event.getGestureSource() != cajaListView && event.getDragboard().hasString()) {
				event.acceptTransferModes(TransferMode.MOVE);
			}
			event.consume();
		});

		cajaListView.setOnDragDropped(event -> {
			Dragboard dragboard = event.getDragboard();
			boolean success = false;
			if (dragboard.hasString() && cajaListView.getItems().size() < 6) {
				String draggedItem = dragboard.getString();
				equipo2ListView.getItems().remove(draggedItem);
				cajaListView.getItems().add(draggedItem);
				success = true;
			} else if (dragboard.hasString()) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Información");
				alert.setHeaderText(null);
				alert.setContentText("El contenedor Caja ha alcanzado su capacidad máxima de 6 elementos.");
				alert.showAndWait();
			}
			event.setDropCompleted(success);
			event.consume();
		});

//-----------------------------------------------------------------------------------------------------------------------------------
		VBox containers = new VBox();
		containers.setAlignment(Pos.CENTER_LEFT);
		containers.getChildren().addAll(equipo1Container, equipo2Container, cajaContainer);

		stackPane.getChildren().add(containers);

		StackPane.setAlignment(muteButton, Pos.TOP_RIGHT);
		stackPane.getChildren().addAll(muteButton);// Añadir backgroundImageView para mostrar la imagen
		stackPane.getChildren().add(backButton);

		Scene scene = new Scene(stackPane, WINDOW_WIDTH, WINDOW_HEIGHT);
		// equipo2Container.getStylesheets().add("Entrenador.css"); Cambia el estilo de
		// equipo2Container

		primaryStage.setScene(scene);
		primaryStage.show();
	}
	public static void main(String[] args) {
		launch(args);
	}
}
