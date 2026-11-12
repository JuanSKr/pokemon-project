package pokemon.ui;

import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.util.function.Consumer;

/**
 * Diálogos dentro del juego (en lugar de ventanas emergentes del sistema).
 * Se dibujan encima de la pantalla actual, con el mismo estilo que el resto del juego,
 * y funcionan bien en pantalla completa.
 */
public final class Dialogo {

    private final Pantalla pantalla;
    private final StackPane capa;

    private Dialogo(Pantalla pantalla, Node contenido, double ancho) {
        this.pantalla = pantalla;
        VBox panel = new VBox(16, contenido);
        panel.getStyleClass().addAll("panel", "dialogo");
        panel.setAlignment(Pos.CENTER);
        panel.setMaxWidth(ancho);
        panel.setMaxHeight(Region.USE_PREF_SIZE);

        capa = new StackPane(panel);
        capa.getStyleClass().add("velo");
        capa.setAlignment(Pos.CENTER);
        capa.setOnMouseClicked(e -> e.consume());

        pantalla.getCapaDialogos().getChildren().add(capa);
        FadeTransition f = new FadeTransition(Duration.millis(140), capa);
        f.setFromValue(0);
        f.setToValue(1);
        ScaleTransition s = new ScaleTransition(Duration.millis(160), panel);
        s.setFromX(0.85);
        s.setFromY(0.85);
        s.setToX(1);
        s.setToY(1);
        f.play();
        s.play();
    }

    /** Cierra el diálogo. */
    public void cerrar() {
        pantalla.getCapaDialogos().getChildren().remove(capa);
    }

    /** Abre un diálogo con un contenido cualquiera. */
    public static Dialogo personalizado(Pantalla pantalla, Node contenido, double ancho) {
        return new Dialogo(pantalla, contenido, ancho);
    }

    private static VBox cuerpo(String titulo, String texto) {
        Label t = new Label(titulo);
        t.getStyleClass().add("dialogo-titulo");
        t.setWrapText(true);
        VBox v = new VBox(10, t);
        v.setAlignment(Pos.CENTER);
        if (texto != null && !texto.isBlank()) {
            Label c = new Label(texto);
            c.getStyleClass().add("dialogo-texto");
            c.setWrapText(true);
            v.getChildren().add(c);
        }
        return v;
    }

    private static HBox botones(Button... botones) {
        HBox h = new HBox(14, botones);
        h.setAlignment(Pos.CENTER);
        return h;
    }

    /** Mensaje con un botón "Aceptar". */
    public static Dialogo info(Pantalla pantalla, String titulo, String texto, Runnable alCerrar) {
        VBox v = cuerpo(titulo, texto);
        Button ok = UI.boton("ACEPTAR");
        ok.setDefaultButton(true);
        ok.setCancelButton(true);
        v.getChildren().add(botones(ok));
        Dialogo d = new Dialogo(pantalla, v, 520);
        ok.setOnAction(e -> {
            d.cerrar();
            if (alCerrar != null) alCerrar.run();
        });
        Platform.runLater(ok::requestFocus);
        return d;
    }

    public static Dialogo info(Pantalla pantalla, String titulo, String texto) {
        return info(pantalla, titulo, texto, null);
    }

    /** Pregunta Sí / No. Si peligroso es true, el botón de confirmar sale en rojo. */
    public static Dialogo confirmar(Pantalla pantalla, String titulo, String texto, String textoSi, String textoNo,
                                    boolean peligroso, Runnable siConfirma) {
        VBox v = cuerpo(titulo, texto);
        Button si = peligroso ? UI.botonRojo(textoSi) : UI.boton(textoSi);
        Button no = UI.botonClaro(textoNo);
        no.setCancelButton(true);
        v.getChildren().add(botones(no, si));
        Dialogo d = new Dialogo(pantalla, v, 540);
        si.setOnAction(e -> {
            d.cerrar();
            siConfirma.run();
        });
        no.setOnAction(e -> d.cerrar());
        Platform.runLater(no::requestFocus);
        return d;
    }

    /** Dos opciones, cada una con su acción (las dos cierran el diálogo). */
    public static Dialogo dosOpciones(Pantalla pantalla, String titulo, String texto,
                                      String textoA, Runnable accionA, String textoB, Runnable accionB) {
        VBox v = cuerpo(titulo, texto);
        Button a = UI.botonClaro(textoA);
        Button b = UI.boton(textoB);
        b.setDefaultButton(true);
        v.getChildren().add(botones(a, b));
        Dialogo d = new Dialogo(pantalla, v, 560);
        a.setOnAction(e -> {
            d.cerrar();
            accionA.run();
        });
        b.setOnAction(e -> {
            d.cerrar();
            accionB.run();
        });
        Platform.runLater(b::requestFocus);
        return d;
    }

    /** Pide un texto (por ejemplo un mote). */
    public static Dialogo pedirTexto(Pantalla pantalla, String titulo, String texto, String inicial, int maximo,
                                     String textoAceptar, String textoCancelar,
                                     Consumer<String> alAceptar, Runnable alCancelar) {
        VBox v = cuerpo(titulo, texto);
        TextField campo = new TextField(inicial == null ? "" : inicial);
        campo.getStyleClass().add("campo-juego");
        campo.setMaxWidth(340);
        campo.setTextFormatter(new javafx.scene.control.TextFormatter<String>(c ->
                c.getControlNewText().length() <= maximo ? c : null));
        Button ok = UI.boton(textoAceptar);
        ok.setDefaultButton(true);
        Button cancelar = UI.botonClaro(textoCancelar);
        cancelar.setCancelButton(true);
        v.getChildren().addAll(campo, botones(cancelar, ok));
        Dialogo d = new Dialogo(pantalla, v, 540);
        ok.setOnAction(e -> {
            d.cerrar();
            alAceptar.accept(campo.getText().trim());
        });
        cancelar.setOnAction(e -> {
            d.cerrar();
            if (alCancelar != null) alCancelar.run();
        });
        Platform.runLater(() -> {
            campo.requestFocus();
            campo.selectAll();
        });
        return d;
    }

    /** Aviso corto que aparece abajo y desaparece solo. */
    public static void aviso(Pantalla pantalla, String texto) {
        Label l = new Label(texto);
        l.getStyleClass().add("aviso");
        l.setMouseTransparent(true);
        StackPane.setAlignment(l, Pos.BOTTOM_CENTER);
        l.setTranslateY(-36);
        pantalla.getCapaDialogos().getChildren().add(l);
        FadeTransition entrar = new FadeTransition(Duration.millis(180), l);
        entrar.setFromValue(0);
        entrar.setToValue(1);
        FadeTransition salir = new FadeTransition(Duration.millis(400), l);
        salir.setDelay(Duration.millis(1900));
        salir.setFromValue(1);
        salir.setToValue(0);
        salir.setOnFinished(e -> pantalla.getCapaDialogos().getChildren().remove(l));
        entrar.setOnFinished(e -> salir.play());
        entrar.play();
    }
}
