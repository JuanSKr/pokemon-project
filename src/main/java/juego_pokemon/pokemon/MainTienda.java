package juego_pokemon.pokemon;

import java.util.Scanner;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

//CLASE MAIN
public class MainTienda extends Application {
	
    private Entrenador entrenador;
    private Tienda tienda;
    private Scanner scanner;

    @Override
    public void start(Stage primaryStage) {
        // CREAMOS EL ENTRENADOR Y LA TIENDA
        entrenador = new Entrenador(500);
        tienda = new Tienda();

        // CREAMOS UN SCANNER PARA LEER LA ENTRADA DEL USUARIO
        scanner = new Scanner(System.in);

        // CREAMOS LOS ELEMENTOS GRÁFICOS NECESARIOS PARA MOSTRAR EL MENÚ
        Label titulo = new Label("Menu:");
        Button btnMostrarInventario = new Button("Mostrar inventario de la tienda");
        Button btnMostrarMochila = new Button("Mostrar mochila del entrenador");
        Button btnMostrarDinero = new Button("Mostrar dinero del entrenador");
        Button btnSalir = new Button("Salir");

        // ASIGNAMOS LOS CONTROLADORES DE EVENTOS PARA CADA BOTÓN
        btnMostrarInventario.setOnAction(e -> {
			try {
				mostrarInventario();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
        btnMostrarMochila.setOnAction(e -> mostrarMochila());
        btnMostrarDinero.setOnAction(e -> mostrarDinero());
        btnSalir.setOnAction(e -> primaryStage.close());

        // CREAMOS EL CONTENEDOR PRINCIPAL Y AGREGAMOS LOS ELEMENTOS GRÁFICOS
        VBox root = new VBox(20, titulo, btnMostrarInventario, btnMostrarMochila, btnMostrarDinero, btnSalir);
        root.setAlignment(Pos.CENTER);

        // CREAMOS LA ESCENA Y LA MOSTRAMOS EN LA VENTANA
        Scene scene = new Scene(root, 1080, 650);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

	private Object mostrarDinero() {
		  
		return null;
	}

	private Object mostrarMochila() {
		// TODO Auto-generated method stub
		return null;
	}

	private void mostrarInventario() throws Exception {
	    // MOSTRAMOS EL INVENTARIO DE LA TIENDA EN UNA NUEVA VENTANA
	    TiendaGUI tiendaGUI = new TiendaGUI(tienda);
	    tiendaGUI.start(new Stage());
	}

	 public static void main(String[] args) {
	        launch();
	    }
	
}
