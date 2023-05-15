package Pokemon.Tienda;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import Pokemon.Entrenador.Entrenador;
import Pokemon.Tienda.Objeto;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Tooltip;
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
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class MenuTienda extends Application {
	private static final double WINDOW_WIDTH = 1080;
	private static final double WINDOW_HEIGHT = 650;
	private Scene previousScene;
	private Stage primaryStage;
	static Map<Integer, Objeto> inventario;

	public MenuTienda(Stage primaryStage, Scene previousScene) {
		this.primaryStage = primaryStage;
		this.previousScene = previousScene;
	}

	@Override
	public void start(Stage primaryStage) {
		// -------------------------MULTIMEDIA-----------------------------------------------
		Image backgroundImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/pokedex.png")));
		ImageView backgroundImageView = new ImageView(backgroundImage);
		backgroundImageView.setFitWidth(1080);
		backgroundImageView.setFitHeight(650);
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
			audioMediaPlayer.stop();
		});
		// AGREGAR BOTÓN PARA SILENCIAR O REANUDAR EL SONIDO
		Button muteButton = new Button(" *MUTE* ");
		muteButton.setOnAction(e -> {
			if (audioMediaPlayer.isMute()) {
				audioMediaPlayer.setMute(false);
			} else {
				audioMediaPlayer.setMute(true);
			}
		});

//---------------------INVENTARIO--------------------------------------------------------------------------------------------------
		// CREAR CONTENEDOR PARA EL INVENTARIO
		this.inventario = new HashMap<>();
		inventario.put(1, new Objeto(1, "Pesa", 50,
				"Aumenta el ataque y la defensa un 20%, pero disminuye la velocidad un 20%."));
		inventario.put(2, new Objeto(2, "Pluma", 60,
				"Aumenta la velocidad un 30%, pero disminuye la defensa y la defensa especial en un 20%."));
		inventario.put(3, new Objeto(3, "Chaleco", 70,
				"Aumenta la defensa y la defensa especial un 20%, pero disminuye la velocidad y el ataque un 15%."));
		inventario.put(4,
				new Objeto(4, "Bastón", 80, "Aumenta la estamina un 20%, pero disminuye en un 15% la velocidad."));
		inventario.put(5, new Objeto(5, "Pilas", 90,
				"Aumenta la recuperación de estamina en un 50%, pero disminuye la defensa especial un 30%."));

		VBox inventarioVBox = new VBox();
		inventarioVBox.setCursor(Cursor.CLOSED_HAND);// CAMBIA EL CURSOR
		inventarioVBox.setPrefWidth(200);
		inventarioVBox.setPrefHeight(200);
		inventarioVBox.setAlignment(Pos.CENTER);
		Label inventarioLabel = new Label("LA TIENDECIKA");
		inventarioLabel.setFont(Font.font("Arial", FontWeight.BOLD, 30)); // CAMBIA LA FUENTE Y EL TAMAÑO
		inventarioLabel.setAlignment(Pos.CENTER); // CENTRA EL LABEL EN EL VBOX
		ListView<Objeto> inventarioListView = new ListView<>();
		// AÑADIR AQUI CONFIGURAION DE TAMAÑO
		this.inventario.forEach((k, v) -> inventarioListView.getItems().add(v));
		inventarioListView.setCellFactory(lv -> new ListCell<Objeto>() {
			@Override
			public void updateItem(Objeto item, boolean empty) {
				super.updateItem(item, empty);
				if (empty) {
					setText(null);
					setTooltip(null);
				} else {
					setText(item.getNombre());
					Tooltip tooltip = new Tooltip();
					tooltip.setText("ID: " + item.getId() + "\n" + "Nombre: " + item.getNombre() + "\n"
							+ "Características: " + item.getDescripcion());
					setTooltip(tooltip);
					// Habilitar el arrastre
					setOnDragDetected(event -> {
						Dragboard db = startDragAndDrop(TransferMode.MOVE);
						ClipboardContent content = new ClipboardContent();
						content.putString(item.getNombre());
						db.setContent(content);
						event.consume();
					});

					// ELIMINAR EL OBJETO DEL INVENTARIO DESPUÉS DE SOLTARLO
					/*
					 * setOnDragDone(event -> { if (event.getTransferMode() == TransferMode.MOVE) {
					 * inventarioListView.getItems().remove(item); } event.consume(); });
					 */
				}
			}
		});
		inventarioVBox.getChildren().addAll(inventarioLabel, inventarioListView);

//----------------------------------------MOCHILA----------------------------------------------------------------------------
		// CREAR CONTENEDOR PARA LA CESTA
		VBox mochila = new VBox();
		mochila.setCursor(Cursor.CLOSED_HAND);// CAMBIA EL CURSOR
		mochila.setPrefWidth(200);
		mochila.setPrefHeight(200);
		mochila.setAlignment(Pos.CENTER);
		Label mochilaLabel = new Label("Mochila");
		mochilaLabel.setFont(Font.font("Arial", FontWeight.BOLD, 30)); // CAMBIA LA FUENTE Y EL TAMAÑO
		mochilaLabel.setAlignment(Pos.CENTER); // CENTRA EL LABEL EN EL VBOX
		ListView<Objeto> mochilaListView = new ListView<>();
		// AÑADIR AQUI CONFIGURAION DE TAMAÑO
		mochila.getChildren().addAll(mochilaLabel, mochilaListView);

//---------------------DINERO---------------------------------------------------------------------------------------------------		

		VBox dineroContainer = new VBox();
		dineroContainer.setAlignment(Pos.CENTER);
		Label dineroLabel = new Label("Dinero: ");
		dineroLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		Label dineroValue = new Label(Integer.toString(Entrenador.getDinero()));
		dineroValue.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		Button actualizarDineroBtn = new Button("Actualizar"); // AGREGA UN BOTÓN PARA ACTUALIZAR EL DINERO
		actualizarDineroBtn.setOnAction(event -> {
			dineroValue.setText(Integer.toString(Entrenador.getDinero())); // ACTUALIZA EL VALOR DEL LABEL
		});
		VBox dineroHbox = new VBox(dineroLabel, dineroValue, actualizarDineroBtn); // CREA UNA HBOX PARA ACOMODAR LOS
																					// ELEMENTOS HORIZONTALMENTE
		dineroHbox.setAlignment(Pos.CENTER);
		dineroHbox.setSpacing(10);
		dineroContainer.getChildren().add(dineroHbox); // AGREGA LA HBOX AL CONTENEDOR PRINCIPAL

//----------------------CESTA-----------------------------------------------------------------------------------
		// CREAR CONTENEDOR PARA LA CESTA
		VBox cesta = new VBox();
		cesta.setCursor(Cursor.CLOSED_HAND);// CAMBIA EL CURSOR
		cesta.setPrefWidth(200);
		cesta.setPrefHeight(200);
		cesta.setAlignment(Pos.CENTER);
		Label cestaLabel = new Label("Cesta");
		cestaLabel.setFont(Font.font("Arial", FontWeight.BOLD, 30)); // CAMBIA LA FUENTE Y EL TAMAÑO
		cestaLabel.setAlignment(Pos.CENTER); // CENTRA EL LABEL EN EL VBOX
		ListView<Objeto> cestaListView = new ListView<>();
		// AÑADIR AQUI CONFIGURAION DE TAMAÑO
		cesta.getChildren().addAll(cestaLabel, cestaListView);
		// HABILITAR EL SOLTAR EN EL CONTENEDOR CESTA
		cesta.setOnDragOver(event -> {
			if (event.getGestureSource() != cesta && event.getDragboard().hasString()) {
				event.acceptTransferModes(TransferMode.MOVE);
			}
			event.consume();
		});
		cesta.setOnDragDropped(event -> {
			Dragboard db = event.getDragboard();
			boolean success = false;
			if (db.hasString()) {
				// OBTENER EL OBJETO DIRECTAMENTE DE LA LISTA DE OBJETOS DEL INVENTARIO
				Optional<Objeto> objetoComprado = inventarioListView.getItems().stream()
						.filter(objeto -> objeto.getNombre().equals(db.getString())).findFirst();
				if (objetoComprado.isPresent()) {
					Alert alert = new Alert(AlertType.CONFIRMATION);
					alert.setTitle("Confirmar compra");
					alert.setHeaderText("Precio: " + objetoComprado.get().getPrecio());
					alert.setContentText("¿Quieres comprar este objeto?");

					Optional<ButtonType> result = alert.showAndWait();
					if (result.isPresent() && result.get() == ButtonType.OK) {
		                // VERIFICAR SI EL ENTRENADOR TIENE SUFICIENTE DINERO
		                if (Entrenador.getDinero() >= objetoComprado.get().getPrecio()) {
		                    // COMPRAR EL OBJETO Y AGREGARLO A LA CESTA Y A LA MOCHILA DEL ENTRENADOR
		                    Entrenador.comprarObjeto(objetoComprado.get());
		                    mochilaListView.getItems().add(objetoComprado.get());
		                    dineroValue.setText(Integer.toString(Entrenador.getDinero()));
		                    success = true;
					}
					}
				} else {
					System.out.println("No existe ningun objeto con ese nombre en la tienda.");
				}
			}
			event.setDropCompleted(success);
			event.consume();
		});

//--------------------------------MUESTRA LOS ELEMENTOS POR VENTANA---------------------------------------------------------------------------------------		
		VBox containers = new VBox();
		containers.setAlignment(Pos.TOP_CENTER);
		containers.getChildren().addAll(inventarioVBox, cesta, mochila, dineroLabel, dineroValue);
		stackPane.getChildren().add(containers);

		StackPane.setAlignment(muteButton, Pos.TOP_RIGHT);
		stackPane.getChildren().addAll(muteButton);// Añadir backgroundImageView para mostrar la imagen
		stackPane.getChildren().add(backButton);

		Scene scene = new Scene(stackPane, WINDOW_WIDTH, WINDOW_HEIGHT);
		// equipo2Container.getStylesheets().add(" .css"); Cambia el estilo de
		// equipo2Container

		primaryStage.setScene(scene);
		primaryStage.show();
	}
}