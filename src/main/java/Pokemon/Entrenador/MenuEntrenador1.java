package Pokemon.Entrenador;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

	//AQUÍ SE ESTÁ CREANDO LA CLASE MENUENTRENADOR
public class MenuEntrenador1 extends Application {

	// SE DEFINEN LAS CONSTANTES PARA EL TAMAÑO DEL ESCENARIO, EL RELLENO Y EL ESPACIO ENTRE ELEMENTOS
    private static final int SCENE_WIDTH = 1080;
    private static final int SCENE_HEIGHT = 650;
    private static final int PADDING_SIZE = 10;  
    private static final int GAP_SIZE = 8;

    // SE DEFINEN LAS VARIABLES PARA EL ESCENARIO PRINCIPAL Y LA ESCENA ANTERIOR
    private Stage primaryStage;
    private Scene previousScene;

    // SE CREA EL CONSTRUCTOR DE LA CLASE MENUENTRENADOR
    public MenuEntrenador1(Stage primaryStage, Scene previousScene) {
        this.primaryStage = primaryStage;
        this.previousScene = previousScene;
    }

    @Override
    public void start(Stage primaryStage) {
        // Este método ya no es necesario
    }

    // SE DEFINE EL MÉTODO PARA OBTENER LA ESCENA
    public Scene getScene() {
    	// SE CREA UN GRIDPANE Y SE LE APLICA RELLENO, IMAGEN DE FONDO Y SEPARACIÓN ENTRE ELEMENTOS
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
     // SE CREA UN IMAGEVIEW CON LA IMAGEN DEL LOGO
        ImageView imagenLogo = new ImageView(new Image("https://i.imgur.com/35umlbf.png"));

     // SE CREA UN BOTÓN PARA REGRESAR AL MENÚ ANTERIOR Y SE LE ASIGNA UNA ACCIÓN
        Button backButton = new Button(" <--  ");
        backButton.setOnAction(e -> {
            primaryStage.setScene(previousScene);
        });
     // SE AÑADE EL BOTÓN AL GRIDPANE Y SE ALINEA A LA DERECHA
        gridPane.add(backButton, 0, 0);
        GridPane.setHalignment(backButton, HPos.RIGHT);

     // SE DEFINE LA ESCENA CON EL GRIDPANE COMO RAÍZ Y SE DEVUELVE
        Scene scene = new Scene(gridPane, SCENE_WIDTH, SCENE_HEIGHT);
        return scene;
    }
}