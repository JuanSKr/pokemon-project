package Pokemon.Capturar;


import javafx.application.Application;
import Pokemon.Pokemon.Pokedex;
import Pokemon.Pokemon.Pokemon;

import java.util.Scanner;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/*
POR HACER:
    - Que la bola a capturar aparezca en un sitio random y no siempre en el mismo sitio.
    - Que no solo gire en X e Y, que también gire en diagonal.
 */

//AQUÍ SE ESTÁ CREANDO LA CLASE MENUENTRENADOR
public class Exploracion extends Application {

    static Scanner sc = new Scanner(System.in);

    private static final int SCENE_WIDTH = 1080;
    private static final int SCENE_HEIGHT = 650;
    private static final int PADDING_SIZE = 10;
    private static final int GAP_SIZE = 8;
    private Stage primaryStage;
    private Scene previousScene;
    Image image = new Image(getClass().getResourceAsStream("/img/Prueba.gif"));


    @Override
    public void start(Stage primaryStage) {
        // Este método ya no es necesario
    }

    public Scene getScene() {
        GridPane gridPane = new GridPane();
        gridPane.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
        gridPane.setPadding(new Insets(PADDING_SIZE));
        BackgroundImage backgroundImage = new BackgroundImage(image,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT
        );
        gridPane.setBackground(new Background(backgroundImage));
        gridPane.setVgap(GAP_SIZE);
        gridPane.setHgap(GAP_SIZE);
        ImageView imagenLogo = new ImageView(image);

        // Agregar botón para regresar al menú anterior
        Button backButton = new Button(" <-- ");
        backButton.setOnAction(e -> {
            primaryStage.setScene(previousScene);
        });
        gridPane.add(backButton, 0, 0);
        GridPane.setHalignment(backButton, HPos.RIGHT);

        // Método de inicio de la aplicación
        Scene scene = new Scene(gridPane, SCENE_WIDTH, SCENE_HEIGHT);
        return scene;
    }


    public static Pokemon pokeRandom() {

        Pokemon[] pokemons = {
                Pokedex.Pikachu,
                Pokedex.Raichu,
                Pokedex.Bulbasaur,
                Pokedex.Ivysaur,
                Pokedex.Venusaur,
                Pokedex.Charmander,
                Pokedex.Charmeleon,
                Pokedex.Charizard,
                Pokedex.Squirtle,
                Pokedex.Wartortle,
                Pokedex.Butterfree,
                Pokedex.Weedle,
                Pokedex.Kakuna,
                Pokedex.Beedrill,
                Pokedex.Pidgey,
                Pokedex.Pidgeotto,
                Pokedex.Pidgeot,
                Pokedex.Rattata,
                Pokedex.Raticate,
                Pokedex.Spearow
        };

        int random = (int) (Math.random() * 19 + 0);

        Pokemon pokemonRandom = pokemons[random];

        return pokemonRandom;
    }


    public Exploracion(Stage primaryStage, Scene previousScene) {
        this.primaryStage = primaryStage;
        this.previousScene = previousScene;
    }
}