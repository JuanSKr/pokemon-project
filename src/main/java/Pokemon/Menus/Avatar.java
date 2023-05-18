package Pokemon.Menus;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Clase AvatarSelector que permite al usuario seleccionar una imagen de avatar de entre 6 imágenes .gif.
 */
public class Avatar extends Application {

    // TAMAÑO DE LA VENTANA
    private static final int WINDOW_WIDTH = 1080;
    private static final int WINDOW_HEIGHT = 650;
    // IMAGEN SELECCIONADA POR EL USUARIO
    private Image selectedImage; 
    private Stage primaryStage;
    private Scene previousScene;

    public Avatar(Stage primaryStage, Scene previousScene) {
        this.primaryStage = primaryStage;
        this.previousScene = previousScene;
    }

    @Override
    public void start(Stage primaryStage) {
    	
        // Contenedor principal
        VBox root = new VBox(10);
        root.setPadding(new Insets(10));
        root.setAlignment(Pos.CENTER);

        // CONTENEDOR PARA LAS IMÁGENES DE AVATAR
        HBox avatarContainer = new HBox(10);
        avatarContainer.setAlignment(Pos.CENTER);

        // AÑADIR LAS IMÁGENES DE AVATAR AL CONTENEDOR
        // CARGAR LA IMAGEN DESDE EL ARCHIVO
        Image image1 = new Image(getClass().getResourceAsStream("/img/entrenador1.gif"));
        Image image2 = new Image(getClass().getResourceAsStream("/img/entrenador2.gif"));
        Image image3 = new Image(getClass().getResourceAsStream("/img/entrenador1.gif"));
        Image image4 = new Image(getClass().getResourceAsStream("/img/entrenador2.gif"));
        Image image5 = new Image(getClass().getResourceAsStream("/img/entrenador1.gif"));
        Image image6 = new Image(getClass().getResourceAsStream("/img/entrenador2.gif"));

        // CREAR UN IMAGEVIEW PARA MOSTRAR LA IMAGEN
        ImageView imageView1 = new ImageView(image1);
        imageView1.setFitWidth(100);
        imageView1.setPreserveRatio(true);
        ImageView imageView2 = new ImageView(image2);
        imageView2.setFitWidth(100);
        imageView2.setPreserveRatio(true);
        ImageView imageView3 = new ImageView(image3);
        imageView3.setFitWidth(100);
        imageView3.setPreserveRatio(true);
        ImageView imageView4 = new ImageView(image4);
        imageView4.setFitWidth(100);
        imageView4.setPreserveRatio(true);
        ImageView imageView5 = new ImageView(image5);
        imageView5.setFitWidth(100);
        imageView5.setPreserveRatio(true);
        ImageView imageView6 = new ImageView(image6);
        imageView6.setFitWidth(100);
        imageView6.setPreserveRatio(true);

        // AÑADIR UN EVENTO DE CLIC AL IMAGEVIEW PARA SELECCIONAR LA IMAGEN
        imageView1.setOnMouseClicked(event -> {
            selectedImage = image1;
        });
        imageView2.setOnMouseClicked(event -> {
            selectedImage = image2;
        });
        imageView3.setOnMouseClicked(event -> {
            selectedImage = image3;
        });
        imageView4.setOnMouseClicked(event -> {
            selectedImage = image4;
        });
        imageView5.setOnMouseClicked(event -> {
            selectedImage = image5;
        });
        imageView6.setOnMouseClicked(event -> {
            selectedImage = image6;
        });

       // AÑADIR EL IMAGEVIEW AL CONTENEDOR
       avatarContainer.getChildren().addAll(imageView1, imageView2, imageView3, imageView4, imageView5, imageView6);

       // BOTÓN PARA ACEPTAR LA SELECCIÓN
       Button acceptButton = new Button("Aceptar");
       acceptButton.setOnAction(event -> {
           if (selectedImage != null) {
               // Aquí puedes añadir el código para usar la imagen seleccionada como avatar en tu programa
               System.out.println("Imagen seleccionada: " + selectedImage.getUrl());
           }
       });
    // AGREGAR BOTÓN PARA REGRESAR AL MENÚ ANTERIOR
       Button backButton = new Button(" <-- ");
       backButton.setOnAction(e -> {
    	   StackPane.setAlignment(backButton, Pos.TOP_LEFT);
           primaryStage.setScene(previousScene);
       });
       
       
       
       // AÑADIR LOS ELEMENTOS AL CONTENEDOR PRINCIPAL
       root.getChildren().addAll(avatarContainer, acceptButton, backButton);
   
    

       // CREAR Y MOSTRAR LA ESCENA
       Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
       primaryStage.setScene(scene);
       primaryStage.show();
   }

   public static void main(String[] args) {
     launch(args);
   }
}
