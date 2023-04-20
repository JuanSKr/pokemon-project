package Pokemon.Tienda;


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
	public class MenuTienda extends Application {

	    private static final int SCENE_WIDTH = 1080;
	    private static final int SCENE_HEIGHT = 650;
	    private static final int PADDING_SIZE = 10;  
	    private static final int GAP_SIZE = 8;

	    private Stage primaryStage;
	    private Scene previousScene;
	    Image image = new Image(getClass().getResourceAsStream("/img/Prueba.jpeg"));
	    

	    public MenuTienda(Stage primaryStage, Scene previousScene) {
	        this.primaryStage = primaryStage;
	        this.previousScene = previousScene;
	    }

	    @Override
	    public void start(Stage primaryStage) {
	        // Este método ya no es necesario
	    }

	    public Scene getScene() {
	        GridPane gridPane = new GridPane();
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
	}