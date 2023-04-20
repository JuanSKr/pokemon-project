package Pokemon.Menus;

import Pokemon.Entrenador.Entrenador;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Login extends Application {

    private TextField nombreUsuario;
    private PasswordField contrasena;
    private Label mensaje;
    private Entrenador entrenador;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Registro de Usuario");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        BackgroundImage backgroundImage = new BackgroundImage(new Image("https://i.imgur.com/ehLRxNl.png"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        grid.setBackground(new Background(backgroundImage));
        grid.setVgap(8);
        grid.setHgap(10);

        Image logo = new Image(getClass().getResourceAsStream("/img/logo.png"));
        ImageView imgView = new ImageView(logo);
        imgView.setFitWidth(900);
        imgView.setFitHeight(350);
        imgView.setId("logo");
        grid.getChildren().add(imgView);


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
        GridPane.setConstraints(mensaje, 1, 4);

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

        if (nombre.equals("user") && password.equals("1234")) {
            mensaje.setText("Inicio de sesión exitoso.");
            Entrenador.setNombre(nombre);
            Menu menu = new Menu();
            try {
                menu.start(new Stage());
                ((Stage) nombreUsuario.getScene().getWindow()).close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            mensaje.setText("Nombre de usuario o contraseña incorrectos.");
        }
    }

    private void crearCuenta() {
        entrenador = null;
        mensaje.setText("Test");
    }

    public static void main(String[] args) {
        launch(args);
    }

}
