package Pokemon.Menus;

import Pokemon.Combate.CombateGrafico;
import Pokemon.Database.PokemonCRUD;
import Pokemon.Entrenador.Entrenador;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

import java.util.Objects;

public class Avatar extends Application {

    private static final int WINDOW_WIDTH = 1080;
    private static final int WINDOW_HEIGHT = 650;
    private Image selectedImage;
    private Stage primaryStage;
    private Scene previousScene;

    public Avatar(Stage primaryStage, Scene previousScene) {
        this.primaryStage = primaryStage;
        this.previousScene = previousScene;
    }

    @Override
    public void start(Stage primaryStage) {
        StackPane root = new StackPane();
        root.setStyle("-fx-background-color: black");

        Media videoBackground = new Media(getClass().getResource("/vid/avatar.mp4").toExternalForm());
        MediaPlayer mediaPlayer = new MediaPlayer(videoBackground);
        MediaView videoView = new MediaView(mediaPlayer);
        videoView.fitWidthProperty().bind(primaryStage.widthProperty());
        videoView.fitHeightProperty().bind(primaryStage.heightProperty());
        videoView.setPreserveRatio(false);
        videoView.setMediaPlayer(mediaPlayer);

        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();

        VBox container = new VBox(10);
        container.setPadding(new Insets(10));
        container.setAlignment(Pos.CENTER);

        Image avatarTxt = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/avatar.png")));
        ImageView avatarView = new ImageView(avatarTxt);
        avatarView.setFitWidth(600);
        avatarView.setFitHeight(350);
        avatarView.setId("txtView");

        Pane avatarContainer = new Pane();
        Image image1 = new Image(getClass().getResourceAsStream("/img/gato.gif"));
        Image image2 = new Image(getClass().getResourceAsStream("/img/entrenador2.gif"));
        Image image3 = new Image(getClass().getResourceAsStream("/img/entrenador3.png"));
        Image image4 = new Image(getClass().getResourceAsStream("/img/entrenador4.gif"));
        Image image5 = new Image(getClass().getResourceAsStream("/img/entrenador5.gif"));
        Image image6 = new Image(getClass().getResourceAsStream("/img/entrenador6.png"));

        ImageView imageView1 = new ImageView(image1);
        imageView1.setFitWidth(120);
        imageView1.setFitHeight(120);
        imageView1.setId("foto1");
        imageView1.setMouseTransparent(true);

        ImageView imageView2 = new ImageView(image2);
        imageView2.setFitWidth(120);
        imageView2.setFitHeight(120);
        imageView2.setId("foto2");
        imageView2.setMouseTransparent(true);

        ImageView imageView3 = new ImageView(image3);
        imageView3.setFitWidth(100);
        imageView3.setFitHeight(100);
        imageView3.setId("foto3");
        imageView3.setMouseTransparent(true);

        ImageView imageView4 = new ImageView(image4);
        imageView4.setFitWidth(100);
        imageView4.setFitHeight(100);
        imageView4.setId("foto4");
        imageView4.setMouseTransparent(true);

        ImageView imageView5 = new ImageView(image5);
        imageView5.setFitWidth(100);
        imageView5.setFitHeight(100);
        imageView5.setId("foto5");
        imageView5.setMouseTransparent(true);

        ImageView imageView6 = new ImageView(image6);
        imageView6.setFitWidth(100);
        imageView6.setFitHeight(100);
        imageView6.setId("foto6");
        imageView6.setMouseTransparent(true);

        Button opcion1 = new Button();
        opcion1.setId("opcion1");
        Button opcion2 = new Button();
        opcion2.setId("opcion2");
        Button opcion3 = new Button();
        opcion3.setId("opcion3");
        Button opcion4 = new Button();
        opcion4.setId("opcion4");
        Button opcion5 = new Button();
        opcion5.setId("opcion5");
        Button opcion6 = new Button();
        opcion6.setId("opcion6");

        opcion1.setOnAction(e -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Entrenador seleccionado");
            alert.setHeaderText("Has seleccionado al entrenador número 1.");
            alert.setContentText("Recuerda confirmar los cambios antes de salir.");
            alert.showAndWait();

            String foto = "/img/gato.gif";
            Entrenador.setFoto(foto);
            PokemonCRUD.updateFoto(foto);
        });

        opcion2.setOnAction(e -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Entrenador seleccionado");
            alert.setHeaderText("Has seleccionado al entrenador número 2.");
            alert.setContentText("Recuerda confirmar los cambios antes de salir.");
            alert.showAndWait();

            String foto = "/img/entrenador2.gif";
            Entrenador.setFoto(foto);
            PokemonCRUD.updateFoto(foto);

        });

        opcion3.setOnAction(e -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Entrenador seleccionado");
            alert.setHeaderText("Has seleccionado al entrenador número 3.");
            alert.setContentText("Recuerda confirmar los cambios antes de salir.");
            alert.showAndWait();

            String foto = "/img/entrenador3.gif";
            Entrenador.setFoto(foto);
            PokemonCRUD.updateFoto(foto);
        });

        opcion4.setOnAction(e -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Entrenador seleccionado");
            alert.setHeaderText("Has seleccionado al entrenador número 4.");
            alert.setContentText("Recuerda confirmar los cambios antes de salir.");
            alert.showAndWait();

            String foto = "/img/entrenador4.gif";
            Entrenador.setFoto(foto);
            PokemonCRUD.updateFoto(foto);
        });

        opcion5.setOnAction(e -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Entrenador seleccionado");
            alert.setHeaderText("Has seleccionado al entrenador número 5.");
            alert.setContentText("Recuerda confirmar los cambios antes de salir.");
            alert.showAndWait();

            String foto = "/img/entrenador5.gif";
            Entrenador.setFoto(foto);
            PokemonCRUD.updateFoto(foto);
        });

        opcion6.setOnAction(e -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("¿Cómo 33?");
            alert.setHeaderText("Has seleccionado a El Nano.");
            alert.setContentText("Vaya padreada.");
            alert.showAndWait();

            String foto = "/img/entrenador6.png";
            Entrenador.setFoto(foto);
            PokemonCRUD.updateFoto(foto);
        });

        avatarContainer.getChildren().addAll(avatarView, opcion1, opcion2, opcion3, opcion4, opcion5, opcion6,
                imageView1, imageView2, imageView3, imageView4, imageView5, imageView6);

        Button aceptar = new Button();
        aceptar.setId("aceptar");
        aceptar.setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Cambios aplicados");
            alert.setHeaderText(null);
            alert.setContentText("¡El avatar se ha actualizado correctamente!");
            alert.showAndWait();

        });

        Button backButton = new Button();
        backButton.setId("back");
        backButton.setOnAction(e -> {
            StackPane.setAlignment(backButton, Pos.TOP_LEFT);
            primaryStage.setScene(previousScene);
        });

        container.getChildren().addAll(avatarContainer, aceptar, backButton);
        root.getChildren().addAll(videoView, container);

        Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
        scene.getStylesheets().add("Avatar.css");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
