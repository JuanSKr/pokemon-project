package juego_pokemon.pokemon;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class PokemonApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(PokemonApplication.class.getResource("pokemon-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1080, 850);

        // crear botones personalizados para minimizar y cerrar la ventana
        Button minimizeButton = new Button("-");
        minimizeButton.setOnAction(event -> stage.setIconified(true));

        Button closeButton = new Button("x");
        closeButton.setOnAction(event -> stage.close());

     // crear un contenedor horizontal para los botones
        HBox titleBar = new HBox(minimizeButton, closeButton);

        // agregar el contenedor a la barra de título de la ventana
        Node root = scene.getRoot();
        root.setOnMousePressed(event -> {
            event.consume();
            root.startFullDrag();
        });
        root.setOnMouseDragged(event -> {
            event.consume();
            stage.setX(event.getScreenX());
            stage.setY(event.getScreenY());
        });
        
        scene.setFill(null);
        scene.getStylesheets().add("style.css");
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
   }

    public static void main(String[] args) {
        launch();
    }
}

