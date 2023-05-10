package Pokemon.Menus;

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
import javafx.stage.Stage;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Objects;

import Pokemon.Database.PokemonCRUD;

public class MenuPokedex1 extends Application {
	private static final double WINDOW_WIDTH = 1080;
	private static final double WINDOW_HEIGHT = 650;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		Image backgroundImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/pokedex.jpg")));
		ImageView backgroundImageView = new ImageView(backgroundImage);
		backgroundImageView.setFitWidth(1080);
		backgroundImageView.setFitHeight(650);
		// Crear el campo de texto y el botón de búsqueda
		TextField searchField = new TextField();
		Button searchButton = new Button("Buscar");

		// Crear los controles para mostrar la información del Pokémon
		Label nameLabel = new Label();
		ImageView imageView = new ImageView();
		Label vitalityLabel = new Label();
		Label speedLabel = new Label();
		Label staminaLabel = new Label();
		Label attackLabel = new Label();
		Label defenseLabel = new Label();
		Label specialAttackLabel = new Label();
		Label specialDefenseLabel = new Label();
		Label noEncontradoLabel = new Label();

		// AGREGAR UN CONTROLADOR DE EVENTOS AL CAMPO DE TEXTO PARA REALIZAR LA BÚSQUEDA
		// CUANDO SE PRESIONE LA TECLA ENTER
		searchField.setOnKeyPressed(event -> {
			if (event.getCode() == KeyCode.ENTER) {
				searchButton.fire();
			}
		});

		// AGREGAR UN CONTROLADOR DE EVENTOS AL BOTÓN DE BÚSQUEDA
		searchButton.setOnAction(event -> {
			// OBTENER EL TEXTO INGRESADO POR EL USUARIO EN EL CAMPO DE TEXTO
			String searchText = searchField.getText();

			// RESTABLECER EL CAMPO DE TEXTO A VACÍO
			searchField.clear();

			try {
				// ESTABLECER CONEXIÓN A LA BASE DE DATOS
				Connection db = DriverManager.getConnection("jdbc:mysql://localhost:3306/pokemon", "root", "");

				// Crear un objeto Statement para ejecutar consultas SQL
				Statement stmt = db.createStatement();

				// EJECUTAR UNA CONSULTA SQL PARA BUSCAR EL POKÉMON EN LA BASE DE DATOS
				ResultSet rs = stmt.executeQuery("SELECT * FROM pokedex WHERE nombre = '" + searchText + "'");

				// VERIFICAR SI SE ENCONTRÓ EL POKÉMON EN LA BASE DE DATOS
				if (rs.next()) {
					// OBTENER LA INFORMACIÓN DEL POKÉMON Y ACTUALIZAR LOS CONTROLES CON ELLA
					String nombre = rs.getString("nombre");
					nameLabel.setText("Nombre: " + nombre);

					String ruta = rs.getString("foto");
					Image imageArchivo = new Image(Objects.requireNonNull(getClass().getResourceAsStream(ruta)));
					imageView.setImage(imageArchivo);

					// CAMBIAR LOS INT POR DOUBLE CUANDO SE ACTUALIZA LA BASE DE DATOS
					int vitalidad = rs.getInt("vitalidad");
					vitalityLabel.setText("Vitalidad: " + vitalidad);

					int velocidad = rs.getInt("velocidad");
					speedLabel.setText("Velocidad: " + velocidad);

					int estamina = rs.getInt("estamina");
					staminaLabel.setText("Estamina: " + estamina);

					int ataque = rs.getInt("ataque");
					attackLabel.setText("Ataque: " + ataque);

					int defensa = rs.getInt("defensa");
					defenseLabel.setText("Defensa: " + defensa);

					int ataqueEspecial = rs.getInt("ataque_especial");
					specialAttackLabel.setText("Ataque Especial: " + ataqueEspecial);

					int defensaEspecial = rs.getInt("defensa_especial");
					specialDefenseLabel.setText("Defensa Especial: " + defensaEspecial);
				} else { // falta poner la condicion para que si no aparece el nombre salte el mensaje
					noEncontradoLabel.setText("No se encontró el Pokémon en la base de datos");
					System.out.println("No se encontró el Pokémon en la base de datos.");
				}

				// Cerrar la conexión a la base de datos
				db.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		});

		// Crear el layout y agregar los controles a él
		HBox searchBox = new HBox(10);
		searchBox.getChildren().addAll(searchField, searchButton);

		VBox infoBox = new VBox(10);
		infoBox.getChildren().addAll(nameLabel, imageView, vitalityLabel, speedLabel, staminaLabel, attackLabel,
				defenseLabel, specialAttackLabel, specialDefenseLabel, noEncontradoLabel);

		VBox root = new VBox(10);
		root.getChildren().addAll(searchBox, infoBox);

		StackPane stackPane = new StackPane();
		stackPane.getChildren().addAll(backgroundImageView, root);
		root.setAlignment(Pos.CENTER);
		Scene scene = new Scene(stackPane, WINDOW_WIDTH, WINDOW_HEIGHT);


		// Mostrar la ventana
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
