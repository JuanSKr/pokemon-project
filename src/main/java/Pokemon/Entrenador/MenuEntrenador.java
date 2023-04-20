package Pokemon.Entrenador;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class MenuEntrenador extends Application {

    private static final int SCENE_WIDTH = 1080;
    private static final int SCENE_HEIGHT = 650;
    private static final int PADDING_SIZE = 10;  
    private static final int GAP_SIZE = 8;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Menu de Entrenador");

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(PADDING_SIZE));
        BackgroundImage backgroundImage = new BackgroundImage(
            new Image("https://i.imgur.com/ehLRxNl.png"),
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.CENTER,
            BackgroundSize.DEFAULT
        );
        gridPane.setBackground(new Background(backgroundImage));
        gridPane.setVgap(GAP_SIZE);
        gridPane.setHgap(GAP_SIZE);
        ImageView imagenLogo = new ImageView(new Image("https://i.imgur.com/35umlbf.png"));

        // Método de inicio de la aplicación
        Scene scene = new Scene(gridPane, SCENE_WIDTH, SCENE_HEIGHT);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

