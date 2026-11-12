package pokemon.ui;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.net.URL;

/**
 * Música de fondo y efectos de sonido. Solo suena una música a la vez: al cambiar de pantalla
 * se cambia de canción sin que se solapen, y el botón de silencio afecta a todo el juego.
 */
public final class Musica {

    private static MediaPlayer actual;
    private static String rutaActual;
    private static final BooleanProperty silencio = new SimpleBooleanProperty(false);
    private static final double VOLUMEN = 0.55;

    static {
        silencio.addListener((obs, antes, ahora) -> {
            if (actual != null) actual.setMute(ahora);
        });
    }

    private Musica() {
    }

    /** Pone una música en bucle. Si ya está sonando esa misma, no la reinicia. */
    public static void reproducir(String ruta) {
        if (ruta.equals(rutaActual) && actual != null) return;
        parar();
        URL url = Musica.class.getResource(ruta);
        if (url == null) {
            System.err.println("Música no encontrada: " + ruta);
            return;
        }
        try {
            actual = new MediaPlayer(new Media(url.toExternalForm()));
            actual.setCycleCount(MediaPlayer.INDEFINITE);
            actual.setVolume(VOLUMEN);
            actual.setMute(silencio.get());
            actual.play();
            rutaActual = ruta;
        } catch (RuntimeException e) {
            System.err.println("No se pudo reproducir " + ruta + ": " + e.getMessage());
            actual = null;
            rutaActual = null;
        }
    }

    /** Reproduce una vez un efecto de sonido (no corta la música de fondo). */
    public static void efecto(String ruta) {
        if (silencio.get()) return;
        URL url = Musica.class.getResource(ruta);
        if (url == null) return;
        try {
            AudioClip clip = new AudioClip(url.toExternalForm());
            clip.setVolume(0.8);
            clip.play();
        } catch (RuntimeException e) {
            System.err.println("No se pudo reproducir el efecto " + ruta + ": " + e.getMessage());
        }
    }

    /** Pone una música una sola vez (sin bucle), por ejemplo la fanfarria de captura. */
    public static void reproducirUnaVez(String ruta) {
        reproducir(ruta);
        if (actual != null) actual.setCycleCount(1);
        rutaActual = null; // para que la siguiente llamada a reproducir() cambie de canción
    }

    public static void parar() {
        if (actual != null) {
            actual.stop();
            actual.dispose();
            actual = null;
        }
        rutaActual = null;
    }

    public static void alternarSilencio() {
        silencio.set(!silencio.get());
    }

    public static boolean estaSilenciado() {
        return silencio.get();
    }

    public static BooleanProperty silencioProperty() {
        return silencio;
    }
}
