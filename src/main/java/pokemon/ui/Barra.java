package pokemon.ui;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.layout.Region;
import javafx.util.Duration;

/**
 * Barra de progreso del juego (vida, estamina, estadísticas). El valor va de 0 a 1 y
 * se puede animar. En modo "vida" cambia de verde a amarillo y a rojo según baja.
 */
public class Barra extends Region {

    public enum Modo { VIDA, ESTAMINA, FIJO }

    private final Region relleno = new Region();
    private final DoubleProperty valor = new SimpleDoubleProperty(1);
    private final Modo modo;
    private final String colorFijo;
    private Timeline animacion;

    public Barra(Modo modo, double ancho, double alto) {
        this(modo, ancho, alto, "#2A75BB");
    }

    public Barra(Modo modo, double ancho, double alto, String colorFijo) {
        this.modo = modo;
        this.colorFijo = colorFijo;
        getStyleClass().add("barra");
        relleno.getStyleClass().add("barra-relleno");
        getChildren().add(relleno);
        setPrefSize(ancho, alto);
        setMinSize(ancho, alto);
        setMaxSize(ancho, alto);
        valor.addListener((o, a, b) -> {
            actualizarColor();
            requestLayout();
        });
        actualizarColor();
    }

    @Override
    protected void layoutChildren() {
        double p = 2;
        double w = Math.max(0, (getWidth() - p * 2) * Math.max(0, Math.min(1, valor.get())));
        relleno.resizeRelocate(p, p, w, getHeight() - p * 2);
    }

    private void actualizarColor() {
        String color;
        if (modo == Modo.VIDA) {
            double v = valor.get();
            color = v > 0.5 ? "linear-gradient(to bottom, #7CF07C, #2DB84B)"
                    : v > 0.2 ? "linear-gradient(to bottom, #FFE066, #F2B705)"
                    : "linear-gradient(to bottom, #FF7A6B, #D9301C)";
        } else if (modo == Modo.ESTAMINA) {
            color = "linear-gradient(to bottom, #8EC8FF, #2F7FE0)";
        } else {
            color = colorFijo;
        }
        relleno.setStyle("-fx-background-color: " + color + ";");
    }

    /** Cambia el valor (0..1) al instante. */
    public void setValor(double v) {
        if (animacion != null) animacion.stop();
        valor.set(limitar(v));
    }

    /** Cambia el valor (0..1) con una animación suave. */
    public void animarA(double v, Duration duracion) {
        if (animacion != null) animacion.stop();
        animacion = new Timeline(new KeyFrame(duracion, new KeyValue(valor, limitar(v))));
        animacion.play();
    }

    public double getValor() {
        return valor.get();
    }

    private static double limitar(double v) {
        return Double.isNaN(v) ? 0 : Math.max(0, Math.min(1, v));
    }
}
