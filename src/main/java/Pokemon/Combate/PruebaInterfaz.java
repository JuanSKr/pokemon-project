package Pokemon.Combate;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.stage.Stage;

import java.util.Objects;

import static Pokemon.Entrenador.Entrenador.addPokemon;
import static Pokemon.Entrenador.Entrenador.verEquipos;

//AQUÍ SE ESTÁ CREANDO LA CLASE MENUENTRENADOR
public class PruebaInterfaz extends Application {

    private static final int SCENE_WIDTH = 1080;
    private static final int SCENE_HEIGHT = 650;

    @Override
    public void start(Stage primaryStage) throws Exception {

        Image backgroundImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/fondocombate.jpg")));
        ImageView backgroundImageView = new ImageView(backgroundImage);
        backgroundImageView.setFitWidth(1080);
        backgroundImageView.setFitHeight(650);


        // CREAR UN CONTENEDOR PRINCIPAL
        VBox root = new VBox();
        root.setSpacing(30);
        root.setPadding(new Insets(10));
        root.setAlignment(Pos.TOP_LEFT);
        root.getChildren().add(backgroundImageView);


// CREAR UN CONTENEDOR PARA LA PARTE INFERIOR
        Pane topContainer = new Pane(); // Cambiado de HBox a Pane
        topContainer.setPrefWidth(SCENE_WIDTH);
        topContainer.setPrefHeight(200);
        topContainer.setLayoutY(SCENE_HEIGHT - 200);
        root.getChildren().add(topContainer);

        // CREAR UNA IMAGEN PARA EL POKÉMON RIVAL
        Image pokemonRival = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/Gif/gyarados.gif")));
        ImageView rivalView = new ImageView(pokemonRival);
        rivalView.setFitWidth(170);
        rivalView.setFitHeight(110);
        rivalView.setLayoutX(800);
        rivalView.setLayoutY(170);
        topContainer.getChildren().add(rivalView);

        // CREAR UNA BARRA DE VIDA PARA EL POKÉMON RIVAL
        ProgressBar vidaRival = new ProgressBar();
        double test = 70;
        vidaRival.setProgress(test / 100);
        vidaRival.setPrefWidth(150);
        HBox.setMargin(vidaRival, new Insets(0, 0, 0, 50));
        topContainer.getChildren().add(vidaRival);
        vidaRival.setStyle("-fx-background-color: lightgray; -fx-accent: #62EA14;");

// CREAR UN CONTENEDOR PARA LA PARTE INFERIOR
        Pane bottomContainer = new Pane(); // Cambiado de HBox a Pane
        bottomContainer.setPrefWidth(SCENE_WIDTH);
        bottomContainer.setPrefHeight(200);
        bottomContainer.setLayoutY(SCENE_HEIGHT - 200);
        root.getChildren().add(bottomContainer);

// CREAR UNA IMAGEN PARA EL POKÉMON DEL JUGADOR
        Image pokemonJugador = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/Gif/geodudeespalda.gif")));
        ImageView jugadorView = new ImageView(pokemonJugador);
        jugadorView.setFitWidth(190);
        jugadorView.setFitHeight(130);
        jugadorView.setLayoutX(100);
        jugadorView.setLayoutY(18);
        bottomContainer.getChildren().add(jugadorView);

        // CREAR UNA BARRA DE VIDA PARA EL POKÉMON DEL JUGADOR
        ProgressBar vidaJugador = new ProgressBar();
        double test2 = 70;
        vidaJugador.setProgress(test2 / 100);
        vidaJugador.setPrefWidth(150);
        HBox.setMargin(vidaJugador, new Insets(0, 0, 0, 50)); // Margen izquierdo de 20px
        bottomContainer.getChildren().add(vidaJugador);
        vidaJugador.setStyle("-fx-background-color: lightgray; -fx-accent: #62EA14;");


        // CREAR BOTONES PARA LOS MOVIMIENTOS DEL POKÉMON
        Button atacarButton = new Button("Luchar");
        atacarButton.setId("atacar");
        atacarButton.setOnAction(event -> {
            System.out.println("Atacar");
        });

        Button descansarButton = new Button("Descansar");
        descansarButton.setId("descansar");
        descansarButton.setOnAction(event -> {
            System.out.println("Descansar");
        });
        Button mochilaButton = new Button("Pokémon");
        mochilaButton.setId("mochila");
        mochilaButton.setOnAction(event -> {
            System.out.println("Mochila");
        });
        Button rendirseButton = new Button("Rendirse");
        rendirseButton.setId("rendirse");
        rendirseButton.setOnAction(event -> {
            System.out.println("Rendirse");
        });
        VBox movesContainer = new VBox();
        movesContainer.getChildren().addAll(atacarButton, descansarButton, mochilaButton, rendirseButton);
        movesContainer.setAlignment(Pos.BASELINE_RIGHT);
        movesContainer.setSpacing(10);
        bottomContainer.getChildren().add(movesContainer);
        // CREAR UN CAJÓN RECTANGULAR PARA MOSTRAR TEXTO
        // Creamos el rectángulo de fondo blanco opaco
        Rectangle rectangle = new Rectangle(750, 200);
        rectangle.setFill(Color.WHITE);
        rectangle.setOpacity(0.8);
        rectangle.setStroke(Color.GRAY);
        rectangle.setStrokeWidth(2);
        rectangle.setStrokeType(StrokeType.INSIDE);
        rectangle.setLayoutX(9.5);
        rectangle.setLayoutY(543.5);

        StackPane stackPane = new StackPane();
        Pane rectPane = new Pane(rectangle);
        stackPane.getChildren().addAll(backgroundImageView, rectPane, root); // El orden se modificó aquí
        root.setAlignment(Pos.CENTER);
        Scene scene = new Scene(stackPane, SCENE_WIDTH, SCENE_HEIGHT);
        scene.getStylesheets().add("Combate.css");

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}