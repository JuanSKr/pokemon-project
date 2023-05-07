package Pokemon.Capturar;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import Pokemon.Pokemon.Pokemon;
import static Pokemon.Entrenador.Entrenador.*;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;


public class MenuExplorador extends Application {
	private static final double WINDOW_WIDTH = 1080;
	private static final double WINDOW_HEIGHT = 650;
	private Circle trainerCircle;
	private Circle pokemonCircle;
	private double pokemonSpeedX = 0;
	private double pokemonSpeedY = 0;
	static Pokemon pokemon;
	private Scene previousScene;

	@Override
	public void start(Stage primaryStage) {
		// GENERAR POKEMON RANDOM
//		pokemon = pokeRandom();
		// CARGAR LA IMAGEN DE FONDO
		Image backgroundImage = new Image(
				"C:\\Users\\mamea\\Documents\\GitHub\\Pokemon\\Pokemon\\src\\main\\resources\\img\\Prueba1.jpeg");
		ImageView backgroundImageView = new ImageView(backgroundImage);
		backgroundImageView.setFitWidth(1080);
		backgroundImageView.setFitHeight(650);
		// CREAR LA CIRCUNFERENCIA ENTRENADOR
		trainerCircle = new Circle(150, 100, 36);
		// CREAR LA CIRCUNFERENCIA POKÉMON
		pokemonCircle = new Circle(350, 300, 40);
		// CARGAR LA IMAGEN POKEMON
		Image image = new Image(
				"C:\\Users\\mamea\\Documents\\GitHub\\Pokemon\\Pokemon\\src\\main\\resources\\img\\Pokemon2.gif");
		// CREAR EL IMAGEVIEW Y AJUSTAR SU TAMAÑO Y POSICIÓN
		ImageView imageView = new ImageView(image);
		imageView.setFitWidth(pokemonCircle.getRadius() * 2);
		imageView.setFitHeight(pokemonCircle.getRadius() * 2);
		imageView.setX(pokemonCircle.getCenterX() - pokemonCircle.getRadius());
		imageView.setY(pokemonCircle.getCenterY() - pokemonCircle.getRadius());
		// AGREGAR UN CHANGE LISTENER A LAS PROPIEDADES centerX Y centerY DE LA
		// CIRCUNFERENCIA
		pokemonCircle.centerXProperty().addListener((observable, oldValue, newValue) -> {
			imageView.setX(newValue.doubleValue() - pokemonCircle.getRadius());
		});
		pokemonCircle.centerYProperty().addListener((observable, oldValue, newValue) -> {
			imageView.setY(newValue.doubleValue() - pokemonCircle.getRadius());
		});
		// CARGAR LA IMAGEN ENTRENADOR
		Image image1 = new Image(
				"C:\\Users\\mamea\\Documents\\GitHub\\Pokemon\\Pokemon\\src\\main\\resources\\img\\Entrenador1.gif");
		// CREAR EL IMAGEVIEW Y AJUSTAR SU TAMAÑO Y POSICIÓN
		ImageView imageView1 = new ImageView(image1);
		imageView1.setFitWidth(trainerCircle.getRadius() * 2);
		imageView1.setFitHeight(trainerCircle.getRadius() * 2);
		imageView1.setX(trainerCircle.getCenterX() - trainerCircle.getRadius());
		imageView1.setY(trainerCircle.getCenterY() - trainerCircle.getRadius());
		// AGREGAR UN CHANGE LISTENER A LAS PROPIEDADES centerX Y centerY DE LA
		// CIRCUNFERENCIA
		trainerCircle.centerXProperty().addListener((observable, oldValue, newValue) -> {
			imageView1.setX(newValue.doubleValue() - trainerCircle.getRadius());
		});
		trainerCircle.centerYProperty().addListener((observable, oldValue, newValue) -> {
			imageView1.setY(newValue.doubleValue() - trainerCircle.getRadius());
		});

		// CREAMOS EL PANEL ESPECIAL Y SE AGREGAN LAS CIRCUNFERENCIAS.
		Pane root = new Pane();
		root.getChildren().addAll(backgroundImageView, imageView, imageView1);

		// AÑADIR AQUI BOTÓN PARA REGRESAR AL MENÚ ANTERIOR
		// ------------------------------------------------------------------------------------------------------------------
		// CREAR LA ESCENA PRINCIPAL Y AGREGAR EL PANEL
		Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
		// AGREGAR UN EVENTHANDLER PARA EL EVENTO KEY_PRESSED EN LA ESCENA
		Set<KeyCode> pressedKeys = new HashSet<>();
		scene.setOnKeyPressed(event -> {
			pressedKeys.add(event.getCode());
			handleEvent(event);
			KeyCode code = event.getCode();
			double moveDistance = 10;
			// MOVIMIENTO EN DIAGONAL
			moveCircleDiagonally(code, trainerCircle, moveDistance, pressedKeys);
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
			// VERIFICAR SI EL POKEMON ESTÁ FUERA DE LA PANTALLA
			double radius1 = pokemonCircle.getRadius();
			double maxX1 = WINDOW_WIDTH + radius1;
			double maxY1 = WINDOW_HEIGHT + radius1;
			double minX1 = 0 - radius1;
			double minY1 = 0 - radius1;

			if (pokemonCircle.getCenterX() < minX1 || pokemonCircle.getCenterX() > maxX1
					|| pokemonCircle.getCenterY() < minY1 || pokemonCircle.getCenterY() > maxY1) {
				cambiarPokemon();
			}
		});

		scene.setOnKeyReleased(event -> {
			pressedKeys.remove(event.getCode());
		});
//---------------------------------------------------------------------------------------------------------------

		// CREAR UN ANIMATIONTIMER PARA ACTUALIZAR EL JUEGO EN CADA FRAME
		AnimationTimer timer = new AnimationTimer() {
			@Override
			public void handle(long now) {
				// GENERAR NÚMEROS ALEATORIOS ENTRE -1 Y 1
				double randomX = Math.random() * 2 - 1;
				double randomY = Math.random() * 2 - 1;
				// CAMBIAR LA VELOCIDAD EN X E Y DEL POKÉMON
				pokemonSpeedX += randomX * 0.10;
				pokemonSpeedY += randomY * 0.5;
				// MOVER EL POKÉMON EN LA DIRECCIÓN ACTUAL
				pokemonCircle.setCenterX(pokemonCircle.getCenterX() + pokemonSpeedX);
				pokemonCircle.setCenterY(pokemonCircle.getCenterY() + pokemonSpeedY);

//------------------------------------------------------------------------------------------------------------			
				// SI CONSIGUE CAPTURA EL POKEMON NUESTRA LA SIGUIENTE ESCENA
				if (trainerCircle.getBoundsInParent().intersects(pokemonCircle.getBoundsInParent())) {
					Random rand = new Random();
					if (rand.nextDouble() <= 0.500) {// PORCENTAJE DE POSIBILIDADES DE CONSEGUIR EL POKEMON
						
						Pokemon copia = new Pokemon(pokemon);
						// CREAR EL CONTENIDO DE LA NUEVA ESCENA
						// CREAMOS UN REPRODUCTOR DE MEDIOS PARA REPRODUCIR EL AUDIO
				        Media audioMedia = new Media(getClass().getResource("/aud/Prueba.wav").toExternalForm());
				        MediaPlayer audioMediaPlayer = new MediaPlayer(audioMedia);
				        audioMediaPlayer.setAutoPlay(true);
						Label mensajeLabel = new Label("¡Felicidades! Has capturado a " + copia.toString());
						mensajeLabel.setStyle("-fx-font-size: 18px; -fx-text-fill: #333333;");
						Label preguntaLabel = new Label("¿Quieres darle un mote a tu " + copia.toString() + " ?");
						preguntaLabel.setStyle("-fx-font-size: 18px; -fx-text-fill: #333333;");
						TextField textField = new TextField();
						textField.setStyle("-fx-padding: 10px;");
						Button ponerMoteButton = new Button("Aplicar mote");
						ponerMoteButton.setStyle(
								"-fx-background-color: #333333; -fx-text-fill: white; -fx-font-size: 16px; -fx-padding: 10px 20px; -fx-border-radius: 5px; -fx-background-radius: 5px;");
						ponerMoteButton.setOnMouseEntered(e -> ponerMoteButton.setStyle(
								"-fx-background-color: #555555; -fx-text-fill: white; -fx-font-size: 16px; -fx-padding: 10px 20px; -fx-border-radius: 5px; -fx-background-radius: 5px;"));
						ponerMoteButton.setOnMouseExited(e -> ponerMoteButton.setStyle(
								"-fx-background-color: #333333; -fx-text-fill: white; -fx-font-size: 16px; -fx-padding: 10px 20px; -fx-border-radius: 5px; -fx-background-radius: 5px;"));
						Button noMoteButton = new Button("No dar mote");
						noMoteButton.setStyle(
								"-fx-background-color: #333333; -fx-text-fill: white; -fx-font-size: 16px; -fx-padding: 10px 20px; -fx-border-radius: 5px; -fx-background-radius: 5px;");
						noMoteButton.setOnMouseEntered(e -> noMoteButton.setStyle(
								"-fx-background-color: #555555; -fx-text-fill:white;-fx-font-size:16px;-fx-padding:10px 20px;-fx-border-radius:5px;-fx-background-radius:5px;"));
						noMoteButton.setOnMouseExited(e -> noMoteButton.setStyle(
								"-fx-background-color: #333333; -fx-text-fill:white;-fx-font-size:16px;-fx-padding:10px 20px;-fx-border-radius:5px;-fx-background-radius:5px;"));
						Button salirButton = new Button("Salir");
						salirButton.setStyle(
								"-fx-background-color: #333333; -fx-text-fill:white;-fx-font-size:16px;-fx-padding:10px 20px;-fx-border-radius:5px;-fx-background-radius:5px;");
						salirButton.setOnMouseEntered(e -> salirButton.setStyle(
								"-fx-background-color:#555555;-fx-text-fill:white;-fx-font-size:16px;-fx-padding:10px 20px;-fx-border-radius:5px;-fx-background-radius:5px;"));
						salirButton.setOnMouseExited(e -> salirButton.setStyle(
								"-fx-background-color:#333333;-fx-text-fill:white;-fx-font-size:16px;-fx-padding:10px 20px;-fx-border-radius:5px;-fx-background-radius:5px;"));
						VBox vbox = new VBox();
						vbox.setAlignment(Pos.CENTER); // ALINEAR LOS ELEMENTOS EN EL CENTRO
						vbox.getChildren().addAll(mensajeLabel, preguntaLabel, textField, ponerMoteButton, noMoteButton,
								salirButton);
						vbox.setSpacing(10);
						vbox.setPadding(new Insets(20));
						// CREAR LA NUEVA ESCENA
						Scene nuevaEscena = new Scene(vbox, 1080, 650);
						// AÑADIR LA NUEVA ESCENA A LA VENTANA PRINCIPAL
						primaryStage.setScene(nuevaEscena);
						// LE PONE UN MOTE AL POKEMON, LO AÑADE AL EQUIPO DISPONIBLE Y CIERRA LA
						// VENTANA.
						ponerMoteButton.setOnAction(event -> {
							String apodo = textField.getText();
							copia.setMote(apodo); // ASIGNAR EL MOTE AL POKEMON
//							addPokemon(copia, equipo1, equipo2, caja);
							verEquipos();
							primaryStage.setScene(scene); // VOLVER A LA ESCENA ANTERIOR
						});
						// CIERRA LA VENTANA DE DIÁLOGO Y AÑADE AL POKEMON AL EQUIPO.
						noMoteButton.setOnAction(event -> {
//							addPokemon(copia, equipo1, equipo2, caja);
							verEquipos();
							primaryStage.setScene(scene); // VOLVER A LA ESCENA ANTERIOR
						});
						// CIERRA LA VENTANA Y AÑADE AL POKEMON AL EQUIPO.
						salirButton.setOnAction(event -> {
							primaryStage.setScene(scene); // VOLVER A LA ESCENA ANTERIOR
						});
						// MOSTRAR LA NUEVA ESCENA
						primaryStage.show();

						// COLOCAR AL POKÉMON EN UNA POSICIÓN ALEATORIA DE LA VENTANA
						pokemonCircle.setCenterX(Math.random() * (WINDOW_WIDTH - pokemonCircle.getRadius() * 2)
								+ pokemonCircle.getRadius());
						pokemonCircle.setCenterY(Math.random() * (WINDOW_HEIGHT - pokemonCircle.getRadius() * 2)
								+ pokemonCircle.getRadius());

					} else {
						// El usuario no pudo capturar el Pokémon
						 // CREAMOS UN REPRODUCTOR DE MEDIOS PARA REPRODUCIR EL AUDIO
				        Media audioMedia = new Media(getClass().getResource("/aud/PuñetaMod.wav").toExternalForm());
				        MediaPlayer audioMediaPlayer = new MediaPlayer(audioMedia);
				        audioMediaPlayer.setAutoPlay(true);
						Label mensajeLabel = new Label("Lo siento, no pudiste capturar a " + pokemon.toString());
						Button salirButton = new Button("Salir");
						salirButton.setStyle(
								"-fx-background-color: #333333; -fx-text-fill:white;-fx-font-size:16px;-fx-padding:10px 20px;-fx-border-radius:5px;-fx-background-radius:5px;");
						salirButton.setOnMouseEntered(e -> salirButton.setStyle(
								"-fx-background-color:#555555;-fx-text-fill:white;-fx-font-size:16px;-fx-padding:10px 20px;-fx-border-radius:5px;-fx-background-radius:5px;"));
						salirButton.setOnMouseExited(e -> salirButton.setStyle(
								"-fx-background-color:#333333;-fx-text-fill:white;-fx-font-size:16px;-fx-padding:10px 20px;-fx-border-radius:5px;-fx-background-radius:5px;"));
						VBox vbox = new VBox();
						vbox.getChildren().addAll(mensajeLabel, salirButton);
						vbox.setAlignment(Pos.CENTER); // ALINEAR LOS ELEMENTOS EN EL CENTRO
						vbox.setSpacing(10);
						vbox.setPadding(new Insets(20));
						// CREAR LA NUEVA ESCENA
						Scene nuevaEscena = new Scene(vbox, 1080, 650);
						// CIERRA LA VENTANA Y AÑADE AL POKEMON AL EQUIPO.
						salirButton.setOnAction(event -> {
							
							primaryStage.setScene(scene); // VOLVER A LA ESCENA ANTERIOR
						});
						// AÑADIR LA NUEVA ESCENA A LA VENTANA PRINCIPAL
						primaryStage.setScene(nuevaEscena);
						// MOSTRAR LA NUEVA ESCENA
						primaryStage.show();
						// COLOCAR AL POKÉMON EN UNA POSICIÓN ALEATORIA DE LA VENTANA
						pokemonCircle.setCenterX(Math.random() * (WINDOW_WIDTH - pokemonCircle.getRadius() * 2)
								+ pokemonCircle.getRadius());
						pokemonCircle.setCenterY(Math.random() * (WINDOW_HEIGHT - pokemonCircle.getRadius() * 2)
								+ pokemonCircle.getRadius());
					}
				}
//----------------------------------------------------------------------------------------------------------------------------
				// VERIFICAR SI LA CIRCUNFERENCIA ENTRENADOR ESTÁ FUERA DE LOS LÍMITES DE
				// LA/VENTANA
				// Y ACTUALIZAR SU POSICIÓN EN CONSECUENCIA
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

//----------------------------------------------------------------------------------------------------------------------------
	// CAMBIAR EL POKÉMON ACTUAL POR UNO NUEVO
	private void cambiarPokemon() {
		// GENERAR NÚMEROS ALEATORIOS PARA LA POSICIÓN EN X E Y DEL NUEVO POKÉMON
		double randomX = Math.random() * (WINDOW_WIDTH - 20) + 10;
		double randomY = Math.random() * (WINDOW_HEIGHT - 20) + 10;
		// ESTABLECER LA NUEVA POSICIÓN Y VELOCIDAD DEL POKÉMON
		pokemonCircle.setCenterX(randomX);
		pokemonCircle.setCenterY(randomY);
		pokemonSpeedX = 0.5;
		pokemonSpeedY = 0.10;
		// GENERAR UN NUEVO POKÉMON ALEATORIO

	}

//----------------------------------------------------------------------------------------------------------------------------
	// MOVIMIENTO EN DIAGONAL PRESIONANDO 2 TECLAS
	private void moveCircleDiagonally(KeyCode code, Circle circle, double moveDistance, Set<KeyCode> pressedKeys) {
		if (pressedKeys.contains(KeyCode.UP) && pressedKeys.contains(KeyCode.LEFT)) {
			circle.setCenterY(circle.getCenterY() - moveDistance);
			circle.setCenterX(circle.getCenterX() - moveDistance);
		} else if (pressedKeys.contains(KeyCode.UP) && pressedKeys.contains(KeyCode.RIGHT)) {
			circle.setCenterY(circle.getCenterY() - moveDistance);
			circle.setCenterX(circle.getCenterX() + moveDistance);
		} else if (pressedKeys.contains(KeyCode.DOWN) && pressedKeys.contains(KeyCode.LEFT)) {
			circle.setCenterY(circle.getCenterY() + moveDistance);
			circle.setCenterX(circle.getCenterX() - moveDistance);
		} else if (pressedKeys.contains(KeyCode.DOWN) && pressedKeys.contains(KeyCode.RIGHT)) {
			circle.setCenterY(circle.getCenterY() + moveDistance);
			circle.setCenterX(circle.getCenterX() + moveDistance);
		}
	}

//---------------------------------------------------------------------------------------------------------------------------	
	// MOVIMIENTO DIRECCION
	private void handleEvent(KeyEvent event) {
		KeyCode code = event.getCode();
		if (code == KeyCode.UP || code == KeyCode.DOWN || code == KeyCode.LEFT || code == KeyCode.RIGHT) {
			// MOVER LA CIRCUNFERENCIA ENTRENADOR
			double moveDistance = 10;
			if (code == KeyCode.UP) {
				trainerCircle.setCenterY(trainerCircle.getCenterY() - moveDistance);
			} else if (code == KeyCode.DOWN) {
				trainerCircle.setCenterY(trainerCircle.getCenterY() + moveDistance);
			} else if (code == KeyCode.LEFT) {
				trainerCircle.setCenterX(trainerCircle.getCenterX() - moveDistance);
			} else if (code == KeyCode.RIGHT) {
				trainerCircle.setCenterX(trainerCircle.getCenterX() + moveDistance);
			}
		}
	}

//--------------------------------------------------------------------------------------------------------------------------
	private void loadImages() {
		// INTRODICIR AQUI EL BLOQUE DE DE IMAGENES DE CIRCUNFERENCIA
	}

//--------------------------------------------------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------------------------------------
	public static void main(String[] args) {
		launch(args);
	}
}