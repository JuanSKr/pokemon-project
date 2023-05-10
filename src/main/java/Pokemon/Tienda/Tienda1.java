package Pokemon.Tienda;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Tienda1 extends Application {
    private Tienda tienda;
    private Label lblTitulo, lblOpcion, lblInventario, lblMochila, lblDinero;
    private TextField txtOpcion, txtDinero;
    private TextArea txtInventario, txtMochila;
    private Button btnAceptar, btnSalir;


    public Tienda1() {
        tienda = new Tienda();
    }
    @Override
    public void start(Stage p) {
        // Crear elementos de la interfaz de usuario
        lblTitulo = new Label("Tienda");
        lblOpcion = new Label("Elige una opción:");
        lblInventario = new Label("Inventario de la tienda:");
        lblMochila = new Label("Mochila del entrenador:");
        lblDinero = new Label("Dinero del entrenador:");
        txtOpcion = new TextField();
        txtInventario = new TextArea();
        txtMochila = new TextArea();
        txtDinero = new TextField();
        btnAceptar = new Button("Aceptar");
        btnSalir = new Button("Salir");

        // Establecer propiedades y estilos para los elementos de la interfaz de usuario
        lblTitulo.setStyle("-fx-font-size:24pt;-fx-font-weight:bold;");
        lblInventario.setStyle("-fx-font-size:16pt;");
        lblMochila.setStyle("-fx-font-size:16pt;");
        lblDinero.setStyle("-fx-font-size:16pt;");
        txtOpcion.setPrefWidth(50);
        txtInventario.setEditable(false);
        txtInventario.setPrefRowCount(10);
        txtMochila.setEditable(false);
        txtMochila.setPrefRowCount(10);
        txtDinero.setEditable(false);
        txtDinero.setPrefWidth(100);

        // Agregar funcionalidad para el botón Salir
        btnSalir.setOnAction(event -> p.close());

        // Crear contenedores para organizar los elementos de la interfaz de usuario
        VBox v = new VBox();
        v.setAlignment(Pos.CENTER);
        v.setSpacing(10);
        v.getChildren().addAll(lblOpcion, txtOpcion, btnAceptar, btnSalir);
	HBox h = new HBox();
        h.setAlignment(Pos.CENTER);
        h.setSpacing(10);
        h.getChildren().addAll(lblInventario, txtInventario, lblMochila, txtMochila, lblDinero, txtDinero);

        BorderPane bp = new BorderPane();
        bp.setTop(lblTitulo);
        bp.setCenter(v);
        bp.setBottom(h);
        BorderPane.setMargin(lblTitulo, new Insets(10, 0, 10, 0));
        BorderPane.setMargin(v, new Insets(10));
        BorderPane.setMargin(h, new Insets(10));

        Scene s = new Scene(bp, 1080, 650);
        p.setScene(s);
        p.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static void abrirTienda() {
    }
}