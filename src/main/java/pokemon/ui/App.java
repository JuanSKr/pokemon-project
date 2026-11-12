package pokemon.ui;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;

/**
 * La ventana del juego. Hay una sola ventana y una sola escena: al navegar solo se cambia
 * la pantalla que se muestra, así se mantiene el tamaño y la pantalla completa.
 * <p>
 * F11 (o Alt+Enter) activa y desactiva la pantalla completa.
 */
public final class App {

    private static Stage stage;
    private static Scene scene;
    private static Pantalla actual;

    private App() {
    }

    public static void iniciar(Stage ventana) {
        stage = ventana;
        stage.setTitle("Pokémon - Proyecto");
        Image icono = UI.imagen("/img/Gif/pikachu.gif");
        if (icono.getWidth() > 1) stage.getIcons().add(icono);
        stage.setMinWidth(640);
        stage.setMinHeight(420);
        stage.setFullScreenExitHint("Pulsa F11 para salir de la pantalla completa");
        stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);

        scene = new Scene(new Pantalla(), Pantalla.ANCHO, Pantalla.ALTO);
        scene.setFill(javafx.scene.paint.Color.web("#10131c"));
        scene.addEventFilter(javafx.scene.input.KeyEvent.KEY_PRESSED, e -> {
            if (e.getCode() == KeyCode.F11 || (e.getCode() == KeyCode.ENTER && e.isAltDown())) {
                stage.setFullScreen(!stage.isFullScreen());
                e.consume();
            }
        });
        stage.setScene(scene);

        Thread.setDefaultUncaughtExceptionHandler((hilo, error) -> {
            error.printStackTrace();
            Platform.runLater(() -> mostrarError(error));
        });
    }

    /** Muestra una pantalla nueva y libera la anterior (vídeos, animaciones...). */
    public static void mostrar(Pantalla pantalla) {
        Pantalla anterior = actual;
        actual = pantalla;
        scene.setRoot(pantalla);
        if (anterior != null) anterior.liberar();
        if (!stage.isShowing()) stage.show();
        pantalla.requestFocus();
    }

    public static Pantalla actual() {
        return actual;
    }

    public static Stage getStage() {
        return stage;
    }

    private static void mostrarError(Throwable error) {
        Throwable raiz = error;
        while (raiz.getCause() != null && !(raiz instanceof pokemon.database.PokemonCRUD.ErrorBaseDatos)) {
            raiz = raiz.getCause();
        }
        String texto = raiz instanceof pokemon.database.PokemonCRUD.ErrorBaseDatos
                ? raiz.getMessage()
                : "Ha ocurrido un error inesperado: " + raiz;
        if (actual != null) {
            Dialogo.info(actual, "¡Vaya! Algo ha fallado", texto);
        }
    }
}
