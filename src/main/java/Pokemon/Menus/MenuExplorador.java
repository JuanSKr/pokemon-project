package Pokemon.Menus;

import Pokemon.Menus.Menu;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.IOException;

import Pokemon.Pokemon.Pokemon;

import static Pokemon.Entrenador.Entrenador.*;
import static Pokemon.Capturar.Exploracion.*;

public class MenuExplorador extends Application {
    private static final double WINDOW_WIDTH = 1080;
    private static final double WINDOW_HEIGHT = 650;
    private Circle trainerCircle;
    private Circle pokemonCircle;
    private double pokemonSpeedX = 1;
    private double pokemonSpeedY = 1;
    static Pokemon pokemon;

    @Override
    public void start(Stage primaryStage) {
        // Generar Pokemon Random
//        pokemon = pokeRandom();

        // Crear la circunferencia entrenador
        trainerCircle = new Circle(300, 200, 20, Color.BLUE);

        // Crear la circunferencia Pokémon
        pokemonCircle = new Circle(100, 100, 10, Color.RED);

        // Creamos el panel especial y se agregan las circunferencias.
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
                } else {
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
                // ERROR: ERROR: ERROR: ERROR: ERROR: ERROR: ERROR: ERROR: ERROR: ERROR:
                pokemonCircle.setCenterX(pokemonCircle.getCenterX() + pokemonSpeedX);
                pokemonCircle.setCenterY(pokemonCircle.getCenterY() + pokemonSpeedY);
                // Hacer que el Pokemon rebote en las paredes
                if (pokemonCircle.getCenterX() < 0 || pokemonCircle.getCenterX() > WINDOW_WIDTH) {
                    pokemonSpeedX = -pokemonSpeedX;
                }
                if (pokemonCircle.getCenterY() < 0 || pokemonCircle.getCenterY() > WINDOW_HEIGHT) {
                    pokemonSpeedY = -pokemonSpeedY;
                }
                // Verificar si las circunferencias están en contacto
                if (trainerCircle.getBoundsInParent().intersects(pokemonCircle.getBoundsInParent())) {

                    Pokemon copia = new Pokemon(pokemon);

                    // Crear el Label y los botones
                    Label label = new Label("¿Quieres darle un mote a tu " + copia.toString() + " ?");
                    TextField textField = new TextField();
                    Button ponerMoteButton = new Button("Aplicar mote");
                    Button noMoteButton = new Button("No dar mote");

                    // Agregar los nodos al diálogo personalizado
                    VBox vbox = new VBox();
                    vbox.getChildren().addAll(label, textField, ponerMoteButton, noMoteButton);
                    Dialog<String> dialog = new Dialog<>();
                    dialog.setTitle("Capturado!");
                    dialog.setHeaderText("¡Felicidades! Has capturado a " + copia.toString());
                    dialog.getDialogPane().setContent(vbox);

                    // Mostrar el diálogo
                    dialog.show();


                    // Le pone un mote al pokemon, lo añade al equipo disponible y cierra la ventana.
                    ponerMoteButton.setOnAction(event -> {
                        String apodo = textField.getText();
                        copia.setMote(apodo); // Asignar el mote al Pokemon
                        addPokemon(copia, equipo1, equipo2, caja);
                        verEquipos();
                        dialog.close();
                        dialog.setResult("cerrar");
                    });

                    //Cierra la ventana de diálogo y añade al Pokemon al equipo.
                    noMoteButton.setOnAction(event -> {
                        addPokemon(copia, equipo1, equipo2, caja);
                        verEquipos();
                        dialog.close();
                        dialog.setResult("cerrar");
                    });

                    //Volver al menu despúes de la captura.
                    Menu menu = new Menu();

                    try {
                        menu.start(primaryStage);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }

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