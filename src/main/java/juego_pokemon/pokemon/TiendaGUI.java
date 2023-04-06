package juego_pokemon.pokemon;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class TiendaGUI extends Application {

    private Tienda tienda;

    public TiendaGUI(Tienda tienda) {
        this.tienda = tienda;
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
            gridPane.add(label, 0, row++);
        }

        // CREAMOS UNA NUEVA ESCENA CON EL GRIDPANE Y LA MOSTRAMOS EN LA VENTANA
        Scene scene = new Scene(gridPane, 1080, 650);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
