package Pokemon.Combate;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
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


        // CREAR UN MENÚ DESPLEGABLE PARA EL EQUIPO
        MenuButton teamMenu = new MenuButton("EQUIPO");
        teamMenu.getItems().addAll(new MenuItem("Pikachu"), new MenuItem("Charmander"), new MenuItem("Squirtle"));
        root.getChildren().add(teamMenu);


        // CREAR UN CONTENEDOR PARA LA PARTE SUPERIOR
        HBox topContainer = new HBox();
        topContainer.setSpacing(10);
        topContainer.setAlignment(Pos.CENTER);
        root.getChildren().add(topContainer);

        // CREAR UNA IMAGEN PARA EL POKÉMON RIVAL
        Image pokemonRival = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/Gif/onix.gif")));
        ImageView rivalView = new ImageView(pokemonRival);
        rivalView.setFitWidth(200);
        rivalView.setPreserveRatio(true);
        HBox.setMargin(rivalView, new Insets(0, 0, 0, 50));
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
        HBox bottomContainer = new HBox();
        bottomContainer.setSpacing(10);
        bottomContainer.setAlignment(Pos.CENTER);
        root.getChildren().add(bottomContainer);

        // CREAR UNA IMAGEN PARA EL POKÉMON DEL JUGADOR
        Image pokemonJugador = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/Gif/gyaradosespalda.gif")));
        ImageView jugadorView = new ImageView(pokemonJugador);
        jugadorView.setFitWidth(150);
        jugadorView.setPreserveRatio(true);
        HBox.setMargin(jugadorView, new Insets(0, 20, 0, 0));
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
        Button atacarButton = new Button("Atacar");
        atacarButton.setOnAction(event -> {
            System.out.println("Atacar");
        });
        Button descansarButton = new Button("Descansar");
        descansarButton.setOnAction(event -> {
            System.out.println("Descansar");
        });
        Button mochilaButton = new Button("Mochila");
        mochilaButton.setOnAction(event -> {
            System.out.println("Mochila");
        });
        Button rendirseButton = new Button("Rendirse");
        rendirseButton.setOnAction(event -> {
            System.out.println("Rendirse");
        });
        VBox movesContainer = new VBox();
        movesContainer.getChildren().addAll(atacarButton, descansarButton, mochilaButton, rendirseButton);
        movesContainer.setAlignment(Pos.BASELINE_RIGHT);
        movesContainer.setSpacing(10);
        bottomContainer.getChildren().add(movesContainer);
        // CREAR UN CAJÓN RECTANGULAR PARA MOSTRAR TEXTO
        Label textLabel = new Label("HOOOOOHOHOOHOOHOH");
        Rectangle textBackground = new Rectangle(SCENE_WIDTH - 20, 40);
        textBackground.setOpacity(0.3);
        StackPane textContainer = new StackPane(textBackground, textLabel);
        root.getChildren().add(textContainer);

        // MOSTRAR LA ESCENA
        Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}