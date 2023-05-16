package Pokemon.Menus;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

//AQUÍ SE ESTÁ CREANDO LA CLASE MENUENTRENADOR
public class MenuCombate extends Application {

    private static final int SCENE_WIDTH = 1080;
    private static final int SCENE_HEIGHT = 650;
    private static final int PADDING_SIZE = 10;
    private static final int GAP_SIZE = 8;

    Image imageBackground = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/Login.gif")));
    Image imageBackground2 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/Prueba1.gif")));
    Image imageBackground3 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/Prueba.gif")));

    private Stage primaryStage;
    private Scene previousScene;
    Image image = new Image(getClass().getResourceAsStream("/img/Prueba.gif"));

    public MenuCombate(Stage primaryStage, Scene previousScene) {
        this.primaryStage = primaryStage;
        this.previousScene = previousScene;
    }

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
            // Asignar un nuevo Background a la previousScene
            VBox root = (VBox) previousScene.getRoot();
            BackgroundImage imageMenu = Menu.backgroundRandom(imageBackground, imageBackground2, imageBackground3);
            Background background = new Background(backgroundImage);
            root.setBackground(background);

            primaryStage.setScene(previousScene);
        });
        gridPane.add(backButton, 0, 0);
        GridPane.setHalignment(backButton, HPos.RIGHT);

        // Método de inicio de la aplicación
        Scene scene = new Scene(gridPane, SCENE_WIDTH, SCENE_HEIGHT);
        return scene;
    }
}