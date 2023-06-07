package Pokemon.Menus;

import Pokemon.Database.MySQL;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Objects;

import Pokemon.Database.PokemonCRUD;

public class MenuPokedex extends Application {
	private static final double WINDOW_WIDTH = 1080;
	private static final double WINDOW_HEIGHT = 650;
	private Scene previousScene;
	private Stage primaryStage;

	public MenuPokedex(Stage primaryStage, Scene previousScene) {
		this.primaryStage = primaryStage;
		this.previousScene = previousScene;
	}

	@Override
	public void start(Stage primaryStage) {
		Image backgroundImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/pokedex.png")));
		ImageView backgroundImageView = new ImageView(backgroundImage);
		backgroundImageView.setFitWidth(1080);
		backgroundImageView.setFitHeight(650);
		// CREAMOS UN REPRODUCTOR DE MEDIOS PARA REPRODUCIR EL AUDIO
		Media audioMedia = new Media(getClass().getResource("/aud/MenuPokedex.wav").toExternalForm());
		MediaPlayer audioMediaPlayer = new MediaPlayer(audioMedia);
		audioMediaPlayer.setAutoPlay(true);
		audioMediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);

		// CREAR EL CAMPO DE TEXTO Y EL BOTÓN DE BÚSQUEDA
		TextField searchField = new TextField();
		searchField.setId("buscarBarra");

		Button buscar = new Button();
		buscar.setId("buscarButton");

		// CREAR LOS CONTROLES PARA MOSTRAR LA INFORMACIÓN DEL POKÉMON
		Label nombreLabel = new Label();
		nombreLabel.setId("nombre");

		ImageView imageView = new ImageView();
		imageView.setId("imagen");
		imageView.setFitWidth(110);
		imageView.setFitHeight(70);

		Label vitalidadLabel = new Label();
		vitalidadLabel.setId("vitalidad");
		Label velocidadLabel = new Label();
		velocidadLabel.setId("velocidad");
		Label estaminaLabel = new Label();
		estaminaLabel.setId("estamina");
		Label ataqueLabel = new Label();
		ataqueLabel.setId("ataque");
		Label defensaLabel = new Label();
		defensaLabel.setId("defensa");
		Label ataqueEspecialLabel = new Label();
		ataqueEspecialLabel.setId("ataqueEspecial");
		Label defensaEspecialLabel = new Label();
		defensaEspecialLabel.setId("defensaEspecial");
		Label noEncontradoLabel = new Label();
		noEncontradoLabel.setId("noEncontrado");

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
		// AGREGAR UN CONTROLADOR DE EVENTOS AL CAMPO DE TEXTO PARA REALIZAR LA BÚSQUEDA
		// CUANDO SE PRESIONE LA TECLA ENTER
		searchField.setOnKeyPressed(event -> {
			if (event.getCode() == KeyCode.ENTER) {
				buscar.fire();
			}
		});

		buscar.setOnAction(event -> {
			String nombrePokemon = searchField.getText();

			searchField.clear();

			/**
			 * PokemonCRUD: READ (2)
			 */

			try {

				Connection db = MySQL.getConexion();

				Statement stmt = db.createStatement();


				ResultSet rs = stmt.executeQuery("SELECT * FROM pokedex WHERE nombre = '" + nombrePokemon + "'");

				if (rs.next()) {

					String nombre = rs.getString("nombre");
					nombreLabel.setText(nombre);

					String ruta = rs.getString("foto");
					Image imageArchivo = new Image(Objects.requireNonNull(getClass().getResourceAsStream(ruta)));
					imageView.setImage(imageArchivo);

					double vitalidad = rs.getDouble("vitalidad");
					vitalidadLabel.setText("Vitalidad: " + vitalidad);

					double velocidad = rs.getDouble("velocidad");
					velocidadLabel.setText("Velocidad: " + velocidad);

					double estamina = rs.getDouble("estamina");
					estaminaLabel.setText("Estamina: " + estamina);

					double ataque = rs.getDouble("ataque");
					ataqueLabel.setText("Ataque: " + ataque);

					double defensa = rs.getDouble("defensa");
					defensaLabel.setText("Defensa: " + defensa);

					double ataqueEspecial = rs.getDouble("ataque_especial");
					ataqueEspecialLabel.setText("Atq Especial: " + ataqueEspecial);

					double defensaEspecial = rs.getDouble("defensa_especial");
					defensaEspecialLabel.setText("Def Especial: " + defensaEspecial);
				} else { // FALTA PONER LA CONDICION PARA QUE SI NO APARECE EL NOMBRE SALTE EL MENSAJE
					noEncontradoLabel.setText("No se encontró el Pokémon en la base de datos");
					System.out.println("No se encontró el Pokémon en la base de datos.");
				}

				// CERRAR LA CONEXIÓN A LA BASE DE DATOS
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		});

		// CREAR EL LAYOUT Y AGREGAR LOS CONTROLES A ÉL
		VBox searchBox = new VBox(13);
		buscar.setPrefWidth(30);
		searchBox.getChildren().addAll(searchField);

		VBox multimediaBox = new VBox(10);
		searchBox.getChildren().addAll(backButton, muteButton);

		VBox infoBox = new VBox(10);
		infoBox.getChildren().addAll(nombreLabel, imageView, vitalidadLabel, velocidadLabel, estaminaLabel, ataqueLabel,
				defensaLabel, ataqueEspecialLabel, defensaEspecialLabel, noEncontradoLabel);

		VBox root = new VBox(10);
		root.getChildren().addAll(searchBox, infoBox);

		StackPane stackPane = new StackPane();
		stackPane.getChildren().addAll(backgroundImageView, root);
		root.setAlignment(Pos.CENTER);
		Scene scene = new Scene(stackPane, WINDOW_WIDTH, WINDOW_HEIGHT);
		scene.getStylesheets().add("Pokedex.css");

		// MOSTRAR LA VENTANA
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
