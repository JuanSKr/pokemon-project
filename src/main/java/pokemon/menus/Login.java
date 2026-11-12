package pokemon.menus;

import pokemon.database.PokemonCRUD;
import pokemon.ui.App;
import pokemon.ui.Comunes;
import pokemon.ui.Musica;
import pokemon.ui.Pantalla;
import pokemon.ui.UI;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/** Pantalla de inicio de sesión y registro. */
public class Login {

    private final Pantalla pantalla = new Pantalla();
    private final TextField usuario = new TextField();
    private final PasswordField contrasena = new PasswordField();
    private final Label mensaje = new Label();

    public static void mostrar() {
        new Login().abrir();
    }

    /** Se mantiene por compatibilidad con el código antiguo: arranca el juego entero. */
    public static void iniciarLogin() {
        javafx.application.Application.launch(pokemon.ui.Juego.class);
    }

    private void abrir() {
        Musica.reproducir("/aud/TemaInicioPokemonVersion1.wav");
        pantalla.fondoImagen("/img/Login.gif").estilo("Login.css");

        ImageView logo = UI.vistaImagen("/img/logo.png", 520, 220);
        logo.setSmooth(true);
        logo.setId("logo");
        pantalla.poner(logo, 280, 18);

        Label usuarioTxt = UI.etiqueta("Nombre de usuario", "texto-login");
        usuario.getStyleClass().add("campo-juego");
        usuario.setPromptText("Tu nombre de entrenador");
        usuario.setPrefWidth(380);

        Label contrasenaTxt = UI.etiqueta("Contraseña", "texto-login");
        contrasena.getStyleClass().add("campo-juego");
        contrasena.setPromptText("••••••");
        contrasena.setPrefWidth(380);

        Button entrar = UI.botonImagen("/img/botones/login-iniciar.png", 180, 102);
        entrar.setId("iniciarSesion");
        entrar.setOnAction(e -> iniciarSesion());
        UI.tooltip(entrar, "Entrar con tu cuenta");
        Button registrarse = UI.botonImagen("/img/botones/login-crear.png", 180, 102);
        registrarse.setId("crearCuenta");
        registrarse.setOnAction(e -> crearCuenta());
        UI.tooltip(registrarse, "Crear una cuenta nueva");

        usuario.setOnAction(e -> contrasena.requestFocus());
        contrasena.setOnAction(e -> iniciarSesion());

        mensaje.setId("error");
        mensaje.setWrapText(true);
        mensaje.setMaxWidth(440);
        mensaje.setAlignment(Pos.CENTER);

        HBox botones = new HBox(20, entrar, registrarse);
        botones.setAlignment(Pos.CENTER);

        VBox formulario = new VBox(8, usuarioTxt, usuario, new Label(), contrasenaTxt, contrasena, botones, mensaje);
        ((Label) formulario.getChildren().get(2)).setMinHeight(2);
        formulario.setAlignment(Pos.CENTER_LEFT);
        formulario.setPrefWidth(440);
        formulario.getStyleClass().add("formulario-login");
        VBox.setMargin(botones, new javafx.geometry.Insets(12, 0, 0, 0));
        pantalla.poner(formulario, 320, 250);

        Button silencio = Comunes.botonSilencio();
        pantalla.poner(silencio, 1000, 14);

        Label ayuda = UI.etiqueta("F11: pantalla completa", "texto-claro");
        ayuda.setStyle("-fx-font-size: 13px;");
        pantalla.poner(ayuda, 18, 622);

        App.mostrar(pantalla);
        Platform.runLater(usuario::requestFocus);
    }

    private void iniciarSesion() {
        String nombre = usuario.getText().trim();
        String pass = contrasena.getText();
        if (nombre.isEmpty() || pass.isEmpty()) {
            error("Escribe tu usuario y tu contraseña.");
            return;
        }
        if (PokemonCRUD.login(nombre, pass)) {
            Menu.mostrar();
        } else {
            error("Usuario o contraseña incorrectos.");
            contrasena.clear();
            contrasena.requestFocus();
        }
    }

    private void crearCuenta() {
        String nombre = usuario.getText().trim();
        String pass = contrasena.getText();
        if (nombre.isEmpty() || pass.isEmpty()) {
            error("Rellena el usuario y la contraseña para crear tu cuenta.");
            return;
        }
        if (nombre.length() > 20 || pass.length() > 20) {
            error("El usuario y la contraseña pueden tener como mucho 20 caracteres.");
            return;
        }
        if (PokemonCRUD.registrar(nombre, pass) == PokemonCRUD.Registro.YA_EXISTE) {
            error("Ya existe un entrenador llamado \"" + nombre + "\".");
            return;
        }
        mensaje.getStyleClass().remove("mensaje-error");
        mensaje.setText("¡Cuenta creada! Te regalamos 5 Poké Balls. Pulsa Entrar para empezar.");
    }

    private void error(String texto) {
        if (!mensaje.getStyleClass().contains("mensaje-error")) mensaje.getStyleClass().add("mensaje-error");
        mensaje.setText(texto);
    }
}
