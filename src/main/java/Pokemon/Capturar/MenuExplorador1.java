package Pokemon.Capturar;

import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.geometry.Bounds;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MenuExplorador1 extends Application {

    private List<String> caja = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) {
        Pane root = new Pane();
        Scene scene = new Scene(root, 600, 600);

        // Entrenador
        Circle entrenador = new Circle(50);
        entrenador.setFill(Color.BLUE);
        entrenador.setTranslateX(300);
        entrenador.setTranslateY(300);

        // Pokémon
        Circle pokemon = new Circle(25);
        pokemon.setFill(Color.RED);
        pokemon.setTranslateX(100);
        pokemon.setTranslateY(100);

        // Movimiento del entrenador con las flechas del teclado
        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.UP) {
                entrenador.setTranslateY(entrenador.getTranslateY() - 10);
            } else if (event.getCode() == KeyCode.DOWN) {
                entrenador.setTranslateY(entrenador.getTranslateY() + 10);
            } else if (event.getCode() == KeyCode.LEFT) {
                entrenador.setTranslateX(entrenador.getTranslateX() - 10);
            } else if (event.getCode() == KeyCode.RIGHT) {
                entrenador.setTranslateX(entrenador.getTranslateX() + 10);
            }

            // Captura del Pokémon
            Bounds boundsEntrenador = entrenador.localToScene(entrenador.getBoundsInLocal());
            Bounds boundsPokemon = pokemon.localToScene(pokemon.getBoundsInLocal());
            if (boundsEntrenador.intersects(boundsPokemon)) {
                Random rand = new Random();
                int probabilidad = rand.nextInt(100) + 1;
                if (probabilidad <= 30) {
                    caja.add("Pikachu");
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Capturado");
                    alert.setHeaderText(null);
                    alert.setContentText("¡Has capturado a Pikachu!");
                    alert.showAndWait();

                    // Nuevo Pokémon en posición aleatoria
                    int x = rand.nextInt((int) root.getWidth());
                    int y = rand.nextInt((int) root.getHeight());
                    pokemon.setTranslateX(x);
                    pokemon.setTranslateY(y);
                } else {
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Escapado");
                    alert.setHeaderText(null);
                    alert.setContentText("Se te escapó el Pokémon.");
                    alert.showAndWait();
                }
            }
        });

        // Movimiento automático del Pokémon
        TranslateTransition transition = new TranslateTransition(Duration.seconds(2), pokemon);
        transition.setToX(500);
        transition.setToY(500);
        transition.setAutoReverse(true);
        transition.setCycleCount(TranslateTransition.INDEFINITE);
        transition.play();

        root.getChildren().addAll(entrenador, pokemon);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}