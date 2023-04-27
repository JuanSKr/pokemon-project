package Pokemon.Capturar;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MenuExplorador extends Application {
	private static final double WINDOW_WIDTH = 1080;
	private static final double WINDOW_HEIGHT = 650;
	private Circle trainerCircle;
	private Circle pokemonCircle;
	private List<String> trainerBox = new ArrayList<>();
	private double pokemonSpeedX = 1;
	private double pokemonSpeedY = 1;

	
	@Override
	public void start(Stage primaryStage) {
		// Crear una lista de Pokémon disponibles
		List<String> pokemonList = List.of("Pikachu", "Charmander", "Squirtle", "Bulbasaur");

		// Generar un número aleatorio para seleccionar un Pokémon
		int index = new Random().nextInt(pokemonList.size());
		String selectedPokemon = pokemonList.get(index);

		// Crear la circunferencia entrenador
		trainerCircle = new Circle(300, 200, 20, Color.BLUE);

		// Crear la circunferencia Pokémon
		pokemonCircle = new Circle(100, 100, 10, Color.RED);

		// CREAR EL PANEL PRINCIPAL Y AGREGAR LAS CIRCUNFERENCIAS
		Pane root = new Pane();
		root.getChildren().addAll(trainerCircle, pokemonCircle);

		// CREAR LA ESCENA PRINCIPAL Y AGREGAR EL PANEL
		Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);

		// AGREGAR UN EVENTHANDLER PARA EL EVENTO KEY_PRESSED EN LA ESCENA
		scene.setOnKeyPressed(event -> {
			KeyCode code = event.getCode();
			if (code == KeyCode.UP || code == KeyCode.DOWN || code == KeyCode.LEFT || code == KeyCode.RIGHT) {
				// MOVER LA CIRCUNFERENCIA ENTRENADOR
				double moveDistance = 5;
				if (code == KeyCode.UP) {
					trainerCircle.setCenterY(trainerCircle.getCenterY() - moveDistance);
				} else if (code == KeyCode.DOWN) {
					trainerCircle.setCenterY(trainerCircle.getCenterY() + moveDistance);
				} else if (code == KeyCode.LEFT) {
					trainerCircle.setCenterX(trainerCircle.getCenterX() - moveDistance);
				} else if (code == KeyCode.RIGHT) {
					trainerCircle.setCenterX(trainerCircle.getCenterX() + moveDistance);
				}
				// VERIFICAR SI LA CIRCUNFERENCIA ENTRENADOR ESTÁ FUERA DE LOS LÍMITES DE LA
				// VENTANA Y ACTUALIZAR SU POSICIÓN EN CONSECUENCIA
				double radius = trainerCircle.getRadius();
				double maxX = WINDOW_WIDTH - radius;
				double maxY = WINDOW_HEIGHT - radius;
				double minX = radius;
				double minY = radius;
				if (trainerCircle.getCenterX() < minX) {
					trainerCircle.setCenterX(maxX);
				} else if (trainerCircle.getCenterX() > maxX) {
					trainerCircle.setCenterX(minX);
				}
				if (trainerCircle.getCenterY() < minY) {
					trainerCircle.setCenterY(maxY);
				} else if (trainerCircle.getCenterY() > maxY) {
					trainerCircle.setCenterY(minY);
				}
			}
		});
		// Crear un AnimationTimer para actualizar el juego en cada frame
		AnimationTimer timer = new AnimationTimer() {
			@Override
			public void handle(long now) {
				// Mover el Pokémon en la dirección actual
				pokemonCircle.setCenterX(pokemonCircle.getCenterX() + pokemonSpeedX);
				pokemonCircle.setCenterY(pokemonCircle.getCenterY() + pokemonSpeedY);
				// Verificar si el Pokémon está cerca de los límites de la ventana y cambiar su
				// dirección si es necesario
				if (pokemonCircle.getCenterX() < 0 || pokemonCircle.getCenterX() > WINDOW_WIDTH) {
					pokemonSpeedX = -pokemonSpeedX;
				}
				if (pokemonCircle.getCenterY() < 0 || pokemonCircle.getCenterY() > WINDOW_HEIGHT) {
					pokemonSpeedY = -pokemonSpeedY;
				}
				// Verificar si las circunferencias están en contacto
				if (trainerCircle.getBoundsInParent().intersects(pokemonCircle.getBoundsInParent())) {
					// Generar un número aleatorio para determinar si el Pokémon fue capturado
					if (Math.random() < 0.10) {
						// El Pokémon fue capturado
						// Agregar el Pokémon a la caja del entrenador
						trainerBox.add(selectedPokemon);
						Alert alert = new Alert(Alert.AlertType.INFORMATION);
						alert.setTitle("Pokemon capturado");
						alert.setHeaderText(null);
						alert.setContentText("¡Felicidades! Has capturado a " + selectedPokemon + ".");
						alert.show();
					} else {
						// El Pokémon se escapó
						// Mostrar un mensaje al usuario
						Alert alert = new Alert(Alert.AlertType.INFORMATION);
						alert.setTitle("Pokemon escapó");
						alert.setHeaderText(null);
						alert.setContentText("¡" + selectedPokemon + " ha escapado!");
						alert.show();
					}
					// Seleccionar un nuevo Pokémon al azar
					// Generar un número aleatorio para seleccionar un Pokémon
					int index = (int) (Math.random() * pokemonList.size());
					final var selectedPokemon = pokemonList.get(index);
					// Colocar al Pokémon en una posición aleatoria de la ventana
					pokemonCircle.setCenterX(
							Math.random() * (WINDOW_WIDTH - pokemonCircle.getRadius() * 2) + pokemonCircle.getRadius());
					pokemonCircle.setCenterY(Math.random() * (WINDOW_HEIGHT - pokemonCircle.getRadius() * 2)
							+ pokemonCircle.getRadius());
				}

				// Verificar si la circunferencia entrenador está fuera de los límites de la
				// ventana y actualizar su posición en consecuencia
				if (trainerCircle.getCenterX() < 0) {
					trainerCircle.setCenterX(WINDOW_WIDTH);
				} else if (trainerCircle.getCenterX() > WINDOW_WIDTH) {
					trainerCircle.setCenterX(0);
				}
				if (trainerCircle.getCenterY() < 0) {
					trainerCircle.setCenterY(WINDOW_HEIGHT);
				} else if (trainerCircle.getCenterY() > WINDOW_HEIGHT) {
					trainerCircle.setCenterY(0);
				}
			}
		};
		timer.start();

		primaryStage.setScene(scene);
		primaryStage.show();
	}
	public static void main(String[] args) {
		launch(args);
	}
}