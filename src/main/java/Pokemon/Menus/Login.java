package Pokemon.Menus;

import Pokemon.Database.PokemonCRUD;
import Pokemon.Entrenador.Entrenador;
import Pokemon.Database.MySQL;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.Objects;

public class Login extends Application {

    private TextField nombreUsuario;
    private PasswordField contrasena;
    private Label mensaje;
    Image imageBackground = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/Login.gif")));

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Registro de Usuario");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        BackgroundSize backgroundSize = new BackgroundSize(1080, 650, false, false, false, false);
        BackgroundImage backgroundImage = new BackgroundImage(imageBackground, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        grid.setBackground(new Background(backgroundImage));
        grid.setVgap(8);
        grid.setHgap(10);

        Image logo = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/logo.png")));
        ImageView logoView = new ImageView(logo);
        logoView.setFitWidth(900);
        logoView.setFitHeight(350);
        logoView.setId("logo");
        grid.getChildren().add(logoView);


        Label nombreUsuarioTxt = new Label("Nombre de usuario:");
        nombreUsuarioTxt.setId("nombreUsuarioTxt");
        GridPane.setConstraints(nombreUsuarioTxt, 0, 0);

        nombreUsuario = new TextField();
        nombreUsuario.setId("labelNombreUsuario");
        GridPane.setConstraints(nombreUsuario, 1, 0);

        Label contrasenaTxt = new Label("Contraseña:");
        contrasenaTxt.setId("contrasenaTxt");
        GridPane.setConstraints(contrasenaTxt, 0, 1);

        contrasena = new PasswordField();
        contrasena.setId("labelContrasena");
        GridPane.setConstraints(contrasena, 1, 1);

        Button iniciarSesion = new Button();
        iniciarSesion.setId("iniciarSesion");
        iniciarSesion.setOnAction(e -> iniciarSesion());
        GridPane.setConstraints(iniciarSesion, 1, 2);


        Button crearCuenta = new Button();
        crearCuenta.setId("crearCuenta");
        crearCuenta.setOnAction(e -> crearCuenta());
        GridPane.setConstraints(crearCuenta, 1, 3);

        mensaje = new Label();
        mensaje.setId("error");
        GridPane.setConstraints(mensaje, 1, 3);

        grid.getChildren().addAll(nombreUsuarioTxt, nombreUsuario, contrasenaTxt, contrasena,
                iniciarSesion, crearCuenta, mensaje);

        Scene scene = new Scene(grid, 1080, 650);
        scene.getStylesheets().add("Login.css");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    private void iniciarSesion() {
        String nombre = nombreUsuario.getText();
        String password = contrasena.getText();

        PokemonCRUD.login(nombre, password);

        if (PokemonCRUD.login) {
            Menu menu = new Menu();
            try {
                PokemonCRUD.cargarEntrenador(nombre);
                menu.start(new Stage());
                ((Stage) nombreUsuario.getScene().getWindow()).close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            mensaje.setText("Usuario o contraseña incorrectos.");
        }
    }

    private void crearCuenta() {
        String nombre = nombreUsuario.getText();
        String password = contrasena.getText();

        if (nombre.isEmpty() || password.isEmpty()) {
            mensaje.setText("Debes rellenar todos los campos.");
        } else {
            PokemonCRUD.register(nombre, password);
            mensaje.setText("Te has registrado correctamente.");
        }

    }

    public static void iniciarLogin() {
        launch();
    }

}
