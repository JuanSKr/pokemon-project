package juego_pokemon.pokemon;

import java.util.Map;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

//Clase MochilaGUI
public class MochilaGUI extends Stage {

    private Map<Integer, Objeto> mochila;

    public MochilaGUI(ObservableMap<Integer, Objeto> mochila) {
    	//INICIALIZAR EL MAPA
    	this.mochila = FXCollections.observableHashMap();
        // CONFIGURAMOS LA VENTANA
        setTitle("Mochila del entrenador");
        setWidth(600);
        setHeight(400);

        this.mochila = mochila; // GUARDAMOS UNA REFERENCIA A LA MOCHILA DEL ENTRENADOR

        // CREAMOS LA TABLA
        TableView<Objeto> tabla = new TableView<>();
        tabla.setEditable(false);

        // CREAMOS LAS COLUMNAS
        TableColumn<Objeto, String> columnaNombre = new TableColumn<>("Nombre");
        columnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));

        TableColumn<Objeto, Integer> columnaPrecio = new TableColumn<>("Precio");
        columnaPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));

        // AGREGAMOS LAS COLUMNAS A LA TABLA
        tabla.getColumns().addAll(columnaNombre, columnaPrecio);

        // AGREGAMOS LOS DATOS A LA TABLA
        ObservableList<Objeto> datos = FXCollections.observableArrayList(mochila.values());
        tabla.setItems(datos);

        // AGREGAMOS LA TABLA A UN LAYOUT
        VBox layout = new VBox();
        layout.getChildren().addAll(tabla);

        // AGREGAMOS EL LAYOUT A LA ESCENA Y CONFIGURAMOS LA VENTANA
        Scene scene = new Scene(layout);
        setScene(scene);
    }

    public void agregarObjeto(Objeto objeto) {
        // AGREGAMOS EL OBJETO A LA MOCHILA DEL ENTRENADOR
        mochila.put(objeto.getId(), objeto);

        // ACTUALIZAMOS LOS DATOS DE LA TABLA
        TableView<Objeto> tabla = (TableView<Objeto>) getScene().getRoot().getChildrenUnmodifiable().get(0);
        ObservableList<Objeto> datos = FXCollections.observableArrayList(mochila.values());
        tabla.setItems(datos);
    }
}
