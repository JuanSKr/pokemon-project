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

		// AGREGAR UN CONTROLADOR DE EVENTOS AL BOTÓN DE BÚSQUEDA
		buscar.setOnAction(event -> {
			// OBTENER EL TEXTO INGRESADO POR EL USUARIO EN EL CAMPO DE TEXTO
			String nombrePokemon = searchField.getText();

			// RESTABLECER EL CAMPO DE TEXTO A VACÍO
			searchField.clear();

			try {
				// ESTABLECER CONEXIÓN A LA BASE DE DATOS
				Connection db = MySQL.getConexion();
				// Crear un objeto Statement para ejecutar consultas SQL
				Statement stmt = db.createStatement();

				// EJECUTAR UNA CONSULTA SQL PARA BUSCAR EL POKÉMON EN LA BASE DE DATOS
				ResultSet rs = stmt.executeQuery("SELECT * FROM pokedex WHERE nombre = '" + nombrePokemon + "'");

				// VERIFICAR SI SE ENCONTRÓ EL POKÉMON EN LA BASE DE DATOS
				if (rs.next()) {
					// OBTENER LA INFORMACIÓN DEL POKÉMON Y ACTUALIZAR LOS CONTROLES CON ELLA
					String nombre = rs.getString("nombre");
					nombreLabel.setText(nombre);

					String ruta = rs.getString("foto");
					Image imageArchivo = new Image(Objects.requireNonNull(getClass().getResourceAsStream(ruta)));
					imageView.setImage(imageArchivo);

					// CAMBIAR LOS INT POR DOUBLE CUANDO SE ACTUALIZA LA BASE DE DATOS
					int vitalidad = rs.getInt("vitalidad");
					vitalidadLabel.setText("Vitalidad: " + vitalidad);

					int velocidad = rs.getInt("velocidad");
					velocidadLabel.setText("Velocidad: " + velocidad);

					int estamina = rs.getInt("estamina");
					estaminaLabel.setText("Estamina: " + estamina);

					int ataque = rs.getInt("ataque");
					ataqueLabel.setText("Ataque: " + ataque);

					int defensa = rs.getInt("defensa");
					defensaLabel.setText("Defensa: " + defensa);

					int ataqueEspecial = rs.getInt("ataque_especial");
					ataqueEspecialLabel.setText("Atq Especial: " + ataqueEspecial);

					int defensaEspecial = rs.getInt("defensa_especial");
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
