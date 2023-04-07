package juego_pokemon.pokemon;

import java.io.IOException;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

//CLASE MAIN
public class MainTienda extends Application {
	
    private Entrenador entrenador;
    private Tienda tienda;
    

    @Override
    public void start(Stage primaryStage) {
        // CREAMOS EL ENTRENADOR Y LA TIENDA
        entrenador = new Entrenador();
        tienda = new Tienda();

       

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
             } catch (IOException ex) {
                 ex.printStackTrace();
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
        Scene scene = new Scene(root, 600, 650);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

	private Object mostrarDinero() {
		System.out.println("Dinero del entrenador: " + Tienda.getDinero());
		return Tienda.getDinero();
	}

	private void mostrarMochila() {
	    Stage ventanaMochila = new Stage();
	    ventanaMochila.setTitle("Mochila del entrenador");

	    // CREAMOS UN LISTVIEW PARA MOSTRAR LOS OBJETOS DE LA MOCHILA
	    ListView<Objeto> listView = new ListView<>();
	    listView.getItems().addAll(entrenador.getMochila().values());

	    // CREAMOS UN BOTÓN PARA CERRAR LA VENTANA
	    Button botonCerrar = new Button("Cerrar");
	    botonCerrar.setOnAction(e -> ventanaMochila.close());

	    // CREAMOS UN VBOX PARA AÑADIR LOS COMPONENTES
	    VBox vbox = new VBox(listView, botonCerrar);
	    vbox.setAlignment(Pos.CENTER);
	    vbox.setSpacing(10);

	    // CREAMOS UNA NUEVA ESCENA CON EL VBOX Y LA MOSTRAMOS EN LA VENTANA
	    Scene scene = new Scene(vbox, 400, 400);
	    ventanaMochila.setScene(scene);
	    ventanaMochila.show();
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
