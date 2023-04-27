package Pokemon.Tienda;

import Pokemon.Entrenador.Entrenador;
import Pokemon.Menus.Tienda;
import Pokemon.Pokemon.Objeto;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class MenuTienda1 extends Application {
    Tienda tienda = new Tienda();
    Entrenador entrenador = new Entrenador();
    private TextArea textArea = new TextArea();
    private TextField txtIdObjeto = new TextField();

    @Override
    public void start(Stage primaryStage) {
        // Creamos un GridPane para organizar los elementos
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10, 10, 10, 10));

        // Creamos los botones para las opciones del menú
        Button btnInventario = new Button("Mostrar inventario");
        Button btnMochila = new Button("Mostrar mochila");
        Button btnDinero = new Button("Mostrar dinero");
        Button btnComprar = new Button("Comprar objeto");
        Button btnSalir = new Button("Salir");

        // Agregamos los botones al GridPane
        grid.add(btnInventario, 0, 0);
        grid.add(btnMochila, 1, 0);
        grid.add(btnDinero, 2, 0);
        grid.add(btnComprar, 3, 0);
        grid.add(btnSalir, 4, 0);

        // Agregamos el TextField y el TextArea al GridPane
        grid.add(txtIdObjeto, 0, 1);
        grid.add(textArea, 0, 2, 5, 1);

        // Configuramos las acciones de los botones
        btnInventario.setOnAction(event -> mostrarInventario());
        btnMochila.setOnAction(event -> mostrarMochila());
        btnDinero.setOnAction(event -> mostrarDinero());
        btnComprar.setOnAction(event -> comprarObjeto());
        btnSalir.setOnAction(event -> salir());

        // Creamos la escena y la agregamos al Stage
        Scene scene = new Scene(grid);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    private void mostrarInventario() {
        // Mostramos el inventario en el TextArea
        textArea.clear();
        textArea.appendText("Inventario de la tienda:\n");
        for (Objeto objeto : tienda.getInventario().values()) {
            textArea.appendText(objeto.toString() + "\n");
        }
    }

    private void mostrarMochila() {
        // Mostramos la mochila del entrenador en el TextArea
        textArea.clear();
        textArea.appendText("Mochila del entrenador:\n");
        for (Objeto objeto : entrenador.getMochila().values()) {
            textArea.appendText(objeto.toString() + "\n");
        }
    }

    private void mostrarDinero() {
        // Mostramos el dinero del entrenador en el TextArea
        textArea.clear();
        textArea.appendText("Dinero del entrenador: " + Entrenador.getDinero() + " monedas\n");
    }

    private void comprarObjeto() {
        // Obtenemos el ID del objeto que el usuario quiere comprar
        int idObjeto = Integer.parseInt(txtIdObjeto.getText());

        // Comprobamos si el objeto existe en el inventario de la tienda
        Objeto objeto = tienda.obtenerObjeto(idObjeto);
        if (objeto != null) {
            // Si el objeto existe, lo compramos
            entrenador.comprarObjeto(objeto);
            textArea.appendText("Has comprado el objeto: " + objeto.getNombre() + "\n");
        } else {
            // Si el objeto no existe, mostramos un mensaje de error
            textArea.appendText("No existe ningun objeto con ese ID en la tienda.\n");
        }
    }

    private void salir() {
        // Salimos de la aplicación
        System.exit(0);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
