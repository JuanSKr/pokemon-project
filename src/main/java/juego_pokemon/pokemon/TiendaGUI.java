package juego_pokemon.pokemon;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class TiendaGUI extends Application {

    private Tienda tienda;
    private Entrenador entrenador;

    public TiendaGUI(Tienda tienda) {
        this.tienda = tienda;
    }
    static void mostrarMensaje(String mensaje) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Mensaje");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Inventario de la tienda");

        // CREAMOS UN GRIDPANE PARA MOSTRAR LOS OBJETOS DE LA TIENDA
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        // MOSTRAMOS LOS OBJETOS DE LA TIENDA EN EL GRIDPANE
        int row = 0;
        for (Objeto objeto : tienda.getInventario().values()) {
            Label label = new Label(objeto.toString());
            
         // CREAMOS UN BOTÓN PARA COMPRAR CADA OBJETO
            Button button = new Button("Comprar");
            button.setOnAction(event -> {
                if (tienda.obtenerObjeto(objeto.getId()) != null) {
                    Entrenador entrenador = new Entrenador();
                    entrenador.comprarObjeto(objeto);
                    tienda.quitarObjeto(objeto.getId());
                } else {
                    System.out.println("No se pudo comprar el objeto.");
                }
            });
            HBox hbox = new HBox();
            hbox.getChildren().addAll(label, button);
            gridPane.add(hbox, 0, row++);
        }

        // CREAMOS UNA NUEVA ESCENA CON EL GRIDPANE Y LA MOSTRAMOS EN LA VENTANA
        Scene scene = new Scene(gridPane, 400, 650);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
	
}

