package Pokemon.Menus;


import Pokemon.Pokemon.Tipo;
import javafx.application.Application;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.*;

import Pokemon.Pokemon.Pokemon;

public class MenuPokedex extends Application {

    private static final double WINDOW_WIDTH = 1080;
    private static final double WINDOW_HEIGHT = 650;

    private ObservableList<Pokemon> data = FXCollections.observableArrayList();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            // Establecer conexión a la base de datos

            // Establecer conexión a la base de datos
            Connection db = DriverManager.getConnection("jdbc:mysql://localhost:3306/pokemon", "root", "");

            // Crear un objeto Statement para ejecutar consultas SQL
            Statement stmt = db.createStatement();

            // Ejecutar una consulta SQL para obtener todos los registros de la tabla pokedex
            ResultSet rs = stmt.executeQuery("SELECT * FROM pokedex");

            // Iterar a través de los resultados de la consulta y agregarlos a la lista de datos
            while (rs.next()) {
                Pokemon pokemon = new Pokemon();
//                pokemon.setFoto(new Image(rs.getBinaryStream("foto")));
                pokemon.setNombre(rs.getString("nombre"));
                pokemon.setVitalidad(rs.getInt("vitalidad"));
                pokemon.setVelocidad(rs.getInt("velocidad"));
                pokemon.setEstamina(rs.getInt("estamina"));
                pokemon.setAtaque(rs.getInt("ataque"));
                pokemon.setDefensa(rs.getInt("defensa"));
                pokemon.setAtaqueEspecial(rs.getInt("ataque_especial"));
                pokemon.setDefensaEspecial(rs.getInt("defensa_especial"));
                pokemon.setTipo1(Tipo.valueOf(rs.getString("tipo1").toUpperCase()));
                String tipo2 = rs.getString("tipo2");
                if (tipo2 != null) {
                    pokemon.setTipo2(Tipo.valueOf(tipo2.toUpperCase()));
                }
                data.add(pokemon);
            }

// Crear una tabla con las columnas correspondientes
            TableView<Pokemon> tabla = new TableView<>();
            tabla.setPrefWidth(1080);
            tabla.setPrefHeight(650);
            TableColumn<Pokemon, String> columnaNombre = new TableColumn<>("Nombre");
            columnaNombre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
            TableColumn<Pokemon, Number> columnaVitalidad = new TableColumn<>("Vitalidad");
            columnaVitalidad.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getVitalidad()));
            TableColumn<Pokemon, Number> columnaVelocidad = new TableColumn<>("Velocidad");
            columnaVelocidad.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getVelocidad()));
            TableColumn<Pokemon, Number> columnaEstamina = new TableColumn<>("Estamina");
            columnaEstamina.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getEstamina()));
            TableColumn<Pokemon, Number> columnaAtaque = new TableColumn<>("Ataque");
            columnaAtaque.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getAtaque()));
            TableColumn<Pokemon, Number> columnaDefensa = new TableColumn<>("Defensa");
            columnaDefensa.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getDefensa()));
            TableColumn<Pokemon, Number> columnaAtqEspecial = new TableColumn<>("Ataque Especial");
            columnaAtqEspecial.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getAtaqueEspecial()));
            TableColumn<Pokemon, Number> columnaDefEspecial = new TableColumn<>("Defensa Especial");
            columnaDefEspecial.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getDefensaEspecial()));
            TableColumn<Pokemon, Tipo> columnaTipo = new TableColumn<>("Tipo");
            columnaTipo.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getTipo1()));
            TableColumn<Pokemon, Tipo> columnaTipo2 = new TableColumn<>("Tipo 2");
            columnaTipo2.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getTipo2()));

// Agregar las columnas a la tabla
            tabla.getColumns().addAll(columnaNombre, columnaVitalidad, columnaVelocidad, columnaEstamina, columnaAtaque, columnaDefensa, columnaAtqEspecial, columnaDefEspecial, columnaTipo, columnaTipo2);

// Agregar los datos a la tabla
            tabla.setItems(data);

// Crear el layout y agregar la tabla a él
            VBox root = new VBox();
            root.getChildren().add(tabla);

// Crear la escena y agregar el layout a ella
            Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);

// Mostrar la ventana
            primaryStage.setScene(scene);
            primaryStage.show();

            // Cerrar la conexión a la base de datos
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
