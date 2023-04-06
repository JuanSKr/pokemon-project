package juego_pokemon.pokemon;


import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import java.util.HashMap;

public class Tienda extends Application {

	private HashMap<Integer, Objeto> objetosDisponibles; // MAPA DE OBJETOS DISPONIBLES EN LA TIENDA

	public Tienda() {
		// INICIALIZAR MAPA DE OBJETOS EN VENTA
		objetosDisponibles = new HashMap<>();
		for (Objeto objeto : Objeto.objetosDisponibles.values()) {
			objetosDisponibles.put(objeto.idObjeto, objeto);
		}
	}

	// MÉTODO QUE MUESTRA EL CATÁLOGO DE OBJETOS DISPONIBLES EN LA TIENDA
	public void mostrarCatalogo() {
		System.out.println("Catálogo de objetos:");
		for (Objeto objeto : objetosDisponibles.values()) {
			System.out.println("ID: " + objeto.idObjeto + ", Nombre: " + objeto.nombre + ", Precio: " + objeto.precio);
		}
	}

	// MÉTODO QUE PERMITE A UN ENTRENADOR COMPRAR UN OBJETO DE LA TIENDA
	public void comprarObjeto(Entrenador entrenador, int idObjeto)
			throws ObjetoNoEncontradoException, DineroInsuficienteException, ObjetoYaEnMochilaException {
		Objeto objetoComprado = objetosDisponibles.get(idObjeto);
		if (objetoComprado == null) {
			throw new ObjetoNoEncontradoException();
		}

		if (entrenador.getDinero() < objetoComprado.precio) {
			throw new DineroInsuficienteException();
		}

		if (entrenador.getMochila().contains(objetoComprado)) {
			throw new ObjetoYaEnMochilaException();
		}

		entrenador.setDinero(objetoComprado.precio);
		entrenador.getMochila().add(objetoComprado);
		System.out.println("Has comprado el objeto " + objetoComprado.nombre + ".");
	}

	class ObjetoNoEncontradoException extends Exception {
// EXCEPCIÓN LANZADA CUANDO NO SE ENCUENTRA EL OBJETO CON EL ID INDICADO EN LA TIENDA
	}

	class DineroInsuficienteException extends Exception {
// EXCEPCIÓN LANZADA CUANDO EL ENTRENADOR NO TIENE SUFICIENTE DINERO PARA COMPRAR EL OBJETO
	}

	class ObjetoYaEnMochilaException extends Exception {
// EXCEPCIÓN LANZADA CUANDO EL OBJETO QUE SE INTENTA COMPRAR YA ESTÁ EN LA MOCHILA DEL ENTRENADOR

	}

//AQUI SE CREA LA CLASE PARA PROBAR LA TIENDA
	@Override
	public void start(Stage primaryStage) {
	    GridPane root = new GridPane();
	    root.setAlignment(Pos.CENTER);
	    root.setHgap(10);
	    root.setVgap(10);

	    Label catalogoLabel = new Label("Catálogo de objetos:");
	    root.add(catalogoLabel, 0, 0);

	    int row = 1;
	    for (Objeto objeto : objetosDisponibles.values()) {
	        Label objetoLabel = new Label("ID: " + objeto.idObjeto + ", Nombre: " + objeto.nombre + ", Precio: " + objeto.precio);
	        root.add(objetoLabel, 0, row);

	        Button comprarButton = new Button("Comprar");
	        comprarButton.setOnAction(event -> {
	        	try {
	                comprarObjeto(new Entrenador(), objeto.idObjeto);
	                objetoLabel.setText(objetoLabel.getText() + " - Comprado");
	                comprarButton.setDisable(true);
	            } catch (ObjetoNoEncontradoException | DineroInsuficienteException | ObjetoYaEnMochilaException e) {
	                //EL MENSAJE QUE SE MOSTRARA CUANDO SE PRESIONES LE BOTON DE COMPRA
	            	Alert alert = new Alert(Alert.AlertType.INFORMATION, e.getMessage(), ButtonType.OK);
	                alert.showAndWait();
	            }
	        });
	        root.add(comprarButton, 1, row);

	        row++;
	    }
	    //BOTONES DE CIERRE Y MINIMIZAR
	    HBox buttonsBox = new HBox();
	    buttonsBox.setAlignment(Pos.TOP_RIGHT);
	    buttonsBox.setPadding(new Insets(10));
	    buttonsBox.setSpacing(10);

	    Button closeButton = new Button("X");
	    closeButton.setOnAction(event -> primaryStage.close());
	    buttonsBox.getChildren().add(closeButton);

	    Button minimizeButton = new Button("_");
	    minimizeButton.setOnAction(event -> primaryStage.setIconified(true));
	    buttonsBox.getChildren().add(minimizeButton);

	    root.add(buttonsBox, 0, 0, 2, 1);

	    Scene scene = new Scene(root, 1080, 850);
	    primaryStage.setScene(scene);
	    primaryStage.show();
	}

	public static void main(String[] args) {
	    launch(args);
	}
}
