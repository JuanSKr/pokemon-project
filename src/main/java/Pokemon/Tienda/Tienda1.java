package Pokemon.Tienda;

import Pokemon.Entrenador.Entrenador;
import Pokemon.Menus.Tienda;
import Pokemon.Pokemon.Objeto;
import java.util.HashMap;
import java.util.Map;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

// CLASE TIENDA
public abstract class Tienda1 extends Application {
	private static Map<Integer, Objeto> inventario;
	
	
	@Override
	public void start(Stage primaryStage) {
	    // CREAMOS UN ENTRENADOR CON 500 MONEDAS
	    Entrenador entrenador = new Entrenador();
	    // Creamos una tienda
	    Tienda tienda = new Tienda();

	    // CREAR GRIDPANE
	    GridPane grid = new GridPane();
	    grid.setAlignment(Pos.TOP_CENTER);
	    grid.setHgap(10);
	    grid.setVgap(10);
	    grid.setPadding(new Insets(25, 25, 25, 25));

	    // CREAR TEXTAREA PARA SALIDA DE LA TIENDA
	    TextArea output = new TextArea();
	    output.setEditable(false);
	    output.setWrapText(true);
	    output.setFont(Font.font("Arial", 14));
	    output.setPrefSize(500, 300);
	    grid.add(output, 0, 0, 2, 1);

	    // CREAR BOTONES
	    Button btnMostrarInventario = new Button("Mostrar inventario");
	    Button btnMostrarMochila = new Button("Mostrar mochila del entrenador");
	    Button btnMostrarDinero = new Button("Mostrar dinero del entrenador");
	    Button btnSalir = new Button("Salir");
	    // CREAR TEXTAREA PARA MOSTRAR RESULTADOS
	    TextArea txtResultado = new TextArea();
	    txtResultado.setEditable(false); // Evitar que el usuario edite el texto

	    // CREAR GRIDPANE PARA ORGANIZAR LOS ELEMENTOS
	    GridPane grid1 = new GridPane();
	    grid1.setPadding(new Insets(10, 10, 10, 10));
	    grid1.setVgap(8);
	    grid1.setHgap(10);

	    // AGREGAR ELEMENTOS AL GRIDPANE
	    GridPane.setConstraints(btnMostrarInventario, 0, 0);
	    GridPane.setConstraints(btnMostrarMochila, 1, 0);
	    GridPane.setConstraints(btnMostrarDinero, 2, 0);
	    GridPane.setConstraints(btnSalir, 3, 0);
	    GridPane.setConstraints(txtResultado, 0, 1, 4, 1);
	    grid1.getChildren().addAll(btnMostrarInventario, btnMostrarMochila, btnMostrarDinero, btnSalir, txtResultado);
	    
	    // AGREGAR ELEMENTOS AL GRIDPANE PRINCIPAL
	    grid.add(grid1, 0, 1);
    
	}
    // CONSTRUCTOR
    public Tienda1() {
        this.inventario = new HashMap<>();
        inventario.put(1, new Objeto(1, "Pesa", 50,
                "Aumenta el ataque y la defensa un 20%, pero disminuye la velocidad un 20%."));
        inventario.put(2, new Objeto(2, "Pluma", 60,
                "Aumenta la velocidad un 30%, pero disminuye la defensa y la defensa especial en un 20%."));
        inventario.put(3, new Objeto(3, "Chaleco", 70,
                "Aumenta la defensa y la defensa especial un 20%, pero disminuye la velocidad y el ataque un 15%."));
        inventario.put(4,
                new Objeto(4, "Bastón", 80, "Aumenta la estamina un 20%, pero disminuye en un 15% la velocidad."));
        inventario.put(5, new Objeto(5, "Pilas", 90,
                "Aumenta la recuperación de estamina en un 50%, pero disminuye la defensa especial un 30%."));
    }
 
    // MÉTODO PARA MOSTRAR EL INVENTARIO DE LA TIENDA
    public static void mostrarInventario(TextArea output) {
        output.clear();
        output.appendText("Inventario de la tienda:\n");
        for (Objeto objeto : inventario.values()) {
            output.appendText(objeto.toString() + "\n");
        }
    }

    // MÉTODO PARA OBTENER UN OBJETO DEL INVENTARIO DE LA TIENDA
    public Objeto obtenerObjeto(int id) {
        return inventario.get(id);
    }
   
    public static void abrirTienda(Stage stage) {
        // CREAMOS UN ENTRENADOR CON 500 MONEDAS
        Entrenador entrenador = new Entrenador();
        // Creamos una tienda
        Tienda tienda = new Tienda();

        // CREAR GRIDPANE
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.TOP_CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        // CREAR TEXTAREA PARA SALIDA DE LA TIENDA
        TextArea output = new TextArea();
        output.setEditable(false);
        output.setWrapText(true);
        output.setFont(Font.font("Arial", 14));
        output.setPrefSize(500, 300);
        grid.add(output, 0, 0, 2, 1);

        // CREAR BOTONES
        Button btnMostrarInventario = new Button("Mostrar inventario");
        Button btnMostrarMochila = new Button("Mostrar mochila del entrenador");
        Button btnMostrarDinero = new Button("Mostrar dinero del entrenador");
        Button btnSalir = new Button("Salir");
        // CREAR TEXTAREA PARA MOSTRAR RESULTADOS
        TextArea txtResultado = new TextArea();
        txtResultado.setEditable(false); // Evitar que el usuario edite el texto
        
        // CREAR GRIDPANE PARA ORGANIZAR LOS ELEMENTOS
        GridPane grid1 = new GridPane();
        grid1.setPadding(new Insets(10, 10, 10, 10));
        grid1.setVgap(8);
        grid1.setHgap(10);
        
        // AGREGAR ELEMENTOS AL GRIDPANE
        GridPane.setConstraints(btnMostrarInventario, 0, 0);
        GridPane.setConstraints(btnMostrarMochila, 1, 0);
        GridPane.setConstraints(btnMostrarDinero, 2, 0);
        GridPane.setConstraints(btnSalir, 3, 0);
        GridPane.setConstraints(txtResultado, 0, 1, 4, 1);
        grid1.getChildren().addAll(btnMostrarInventario, btnMostrarMochila, btnMostrarDinero, btnSalir, txtResultado);
        
        // AGREGAR ACCIÓN AL BOTÓN DE MOSTRAR INVENTARIO
        btnMostrarInventario.setOnAction(e -> {
            txtResultado.setText("");
            txtResultado.appendText("Inventario de la tienda:\n");
            for (Objeto objeto : inventario.values()) {
                txtResultado.appendText(objeto.toString() + "\n");
            }
            txtResultado.appendText("Elige el ID del objeto que quieres comprar: ");
        });
        
        // AGREGAR ACCIÓN AL BOTÓN DE MOSTRAR MOCHILA
        btnMostrarMochila.setOnAction(e -> {
            txtResultado.setText("");
            txtResultado.appendText("Mochila del entrenador:\n");
            for (Objeto objeto : entrenador.getMochila().values()) {
                txtResultado.appendText(objeto.toString() + "\n");
            }
            txtResultado.appendText("Contador de objetos:\n");
            for (Map.Entry<Objeto, Integer> entry : entrenador.getContador().entrySet()) {
                Objeto objeto = entry.getKey();
                int cantidad = entry.getValue();
                txtResultado.appendText(objeto.getNombre() + ": " + cantidad + "\n");
            }
        });
        
        // AGREGAR ACCIÓN AL BOTÓN DE MOSTRAR DINERO
        btnMostrarDinero.setOnAction(e -> {
            txtResultado.setText("");
            txtResultado.appendText("Dinero del entrenador: " + entrenador.getNombre() + " monedas " + entrenador.getDinero() + "\n");
        });
        
        // AGREGAR ACCIÓN AL BOTÓN DE SALIR
        btnSalir.setOnAction(e -> {
            txtResultado.setText("");
            txtResultado.appendText("¡Hasta luego!");
            System.exit(0);
        });
 
	 }
    public Map<Integer, Objeto> getInventario() {
        return this.inventario;
    }
    public static void main(String[] args) {
        launch(args);
    }
}
