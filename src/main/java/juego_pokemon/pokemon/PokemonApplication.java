package juego_pokemon.pokemon;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.io.IOException;

public class PokemonApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(PokemonApplication.class.getResource("pokemon-view.fxml"));

        	//AQUI SE AÑADEN LOS BOTONES DEL MENU
            Button combateButton = new Button("Combate");
            Button exploracionButton = new Button("Exploración");
            Button pokedexButton = new Button("Pokédex");
            Button entrenadorButton = new Button("Entrenador");
            Button mochilaButton = new Button("Mochila");
            Button tiendaButton = new Button("Tienda");
            Button salirButton = new Button("Salir");
            
            //ESTILO DE LOS BOTONES
          //CREAMOS UN EFECTO DE SOMBRA PARA LOS BOTONES
            DropShadow shadow = new DropShadow();

            //CONFIGURAMOS LA SOMBRA
            shadow.setColor(Color.BLACK);
            shadow.setOffsetX(2);
            shadow.setOffsetY(2);

            //AGREGAMOS EL EFECTO DE SOMBRA AL PASAR EL MOUSE POR ENCIMA DE CADA BOTÓN
            combateButton.setStyle("-fx-background-color: orange; -fx-text-fill: white; -fx-font-size: 18px;");
            combateButton.setOnMouseEntered(e -> combateButton.setEffect(shadow));
            combateButton.setOnMouseExited(e -> combateButton.setEffect(null));

            exploracionButton.setStyle("-fx-background-color: brown; -fx-text-fill: white; -fx-font-size: 18px;");
            exploracionButton.setOnMouseEntered(e -> exploracionButton.setEffect(shadow));
            exploracionButton.setOnMouseExited(e -> exploracionButton.setEffect(null));

            pokedexButton.setStyle("-fx-background-color: #6d6875; -fx-text-fill: white; -fx-font-size: 18px;");
            pokedexButton.setOnMouseEntered(e -> pokedexButton.setEffect(shadow));
            pokedexButton.setOnMouseExited(e -> pokedexButton.setEffect(null));

            entrenadorButton.setStyle("-fx-background-color: #282c34; -fx-text-fill: white; -fx-font-size: 18px;");
            entrenadorButton.setOnMouseEntered(e -> entrenadorButton.setEffect(shadow));
            entrenadorButton.setOnMouseExited(e -> entrenadorButton.setEffect(null));

            mochilaButton.setStyle("-fx-background-color: #20bf6b; -fx-text-fill: white; -fx-font-size: 18px;");
            mochilaButton.setOnMouseEntered(e -> mochilaButton.setEffect(shadow));
            mochilaButton.setOnMouseExited(e -> mochilaButton.setEffect(null));

            tiendaButton.setStyle("-fx-background-color: #3867d6; -fx-text-fill: white; -fx-font-size: 18px;");
            tiendaButton.setOnMouseEntered(e -> tiendaButton.setEffect(shadow));
            tiendaButton.setOnMouseExited(e -> tiendaButton.setEffect(null));

            salirButton.setStyle("-fx-background-color: #eb3b5a; -fx-text-fill: white; -fx-font-size: 18px;");
            salirButton.setOnMouseEntered(e -> salirButton.setEffect(shadow));
            salirButton.setOnMouseExited(e -> salirButton.setEffect(null));

 //-----------------------------------------------------------------------------------------------------------------
            
            combateButton.setOnAction(e -> {
                // AQUÍ PUEDES LLAMAR A LA CLASE COMBATE
            });
            exploracionButton.setOnAction(e -> {
                // AQUÍ PUEDES LLAMAR A LA CLASE EXPLORAR PARA CAPTURAR POKEMOS
            	
            });
            
            pokedexButton.setOnAction(e -> {
                // AQUÍ PUEDES LLAMAR A LA CLASE POKEDEX
            });
            entrenadorButton.setOnAction(e -> {
                // AQUÍ PUEDES LLAMAR A LA CLASE ENTRENADOR
            });
            
            mochilaButton.setOnAction(e -> {
                // AQUÍ PUEDES LLAMAR A LA CLASE MOCHILA
            });

            tiendaButton.setOnAction(e -> {
                // AQUÍ PUEDES LLAMAR A LA CLASE TIENDA
            	Tienda.abrirTienda();
            	
            });
            
            //FUNCION PARA SALIR DEL PROGRAMA
            salirButton.setOnAction(e -> primaryStage.close());
            
            
         // CREAMOS UN VBOX 
            VBox root = new VBox(10, combateButton, exploracionButton, pokedexButton, entrenadorButton, mochilaButton, tiendaButton, salirButton);
            root.setAlignment(Pos.CENTER);
            root.setPadding(new Insets(20));
            root.setBackground(new Background(new BackgroundFill(Color.web("green"), CornerRadii.EMPTY, Insets.EMPTY)));

            //TAMAÑO VENTANA
            Scene scene = new Scene(root, 1080, 650);
            primaryStage.setTitle("Menú de Inicio");
            primaryStage.setScene(scene);
            primaryStage.show();
        }

        public static void main(String[] args) {
            launch(args);
        }
    }

