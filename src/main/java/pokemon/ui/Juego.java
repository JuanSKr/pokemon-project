package pokemon.ui;

import pokemon.database.BaseDatos;
import pokemon.menus.Login;
import javafx.application.Application;
import javafx.stage.Stage;

/** Punto de entrada de JavaFX: prepara la ventana, la base de datos y abre el login. */
public class Juego extends Application {

    @Override
    public void start(Stage stage) {
        App.iniciar(stage);
        Login.mostrar();
        try {
            BaseDatos.inicializar();
        } catch (Exception e) {
            e.printStackTrace();
            Dialogo.info(App.actual(), "No hay conexión con la base de datos",
                    "Enciende MySQL en XAMPP y comprueba que existe la base de datos \"pokemon\" "
                            + "(importa bd/pokemon.sql). Después vuelve a abrir el juego.");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
