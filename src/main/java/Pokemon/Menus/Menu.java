package Pokemon.Menus;

import Pokemon.Capturar.MenuExplorador;
import Pokemon.Combate.MenuCombate;
import Pokemon.Entrenador.Entrenador;
import Pokemon.Entrenador.MenuEntrenador;
import Pokemon.Tienda.MenuTienda;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.IOException;

public class Menu extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {

    	// CREAMOS UN REPRODUCTOR DE MEDIOS PARA REPRODUCIR EL AUDIO
//        Media audioMedia = new Media(getClass().getResource("/aud/Prueba.wav").toExternalForm());
//        MediaPlayer audioMediaPlayer = new MediaPlayer(audioMedia);
//        audioMediaPlayer.setAutoPlay(true);
//        audioMediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
    	
        //AQUI SE AÑADEN LOS BOTONES DEL MENU
            // El setId le asigna su ID dentro del css.
        Button combateButton = new Button("Combate");
        combateButton.setId("combateButton");
        Button exploracionButton = new Button("Explorar");
        exploracionButton.setId("explorarButton");
        Button pokedexButton = new Button("Pokédex");
        pokedexButton.setId("pokedexButton");
        Button entrenadorButton = new Button(Entrenador.getNombre());
        entrenadorButton.setId("entrenadorButton");
        // AÑADIR MOCHILA DENTRO DE ENTRENADOR
//        Button mochilaButton = new Button("Mochila");
//        mochilaButton.setId("mochilaButton");
        Button tiendaButton = new Button("Tienda");
        tiendaButton.setId("tiendaButton");
        Button salirButton = new Button("Salir");
        salirButton.setId("salirButton");


        //-----------------------------------------------------------------------------------------------------------------

        combateButton.setOnAction(e -> {
            // AQUÍ PUEDES LLAMAR A LA CLASE COMBATE
        	Scene currentScene = primaryStage.getScene();
            MenuCombate menuCombate = new MenuCombate(primaryStage, currentScene);
            Scene menuCombateScene = menuCombate.getScene();
            primaryStage.setScene(menuCombateScene);

        });
        exploracionButton.setOnAction(e -> {
        	 // CÓDIGO PARA EJECUTAR CUANDO SE HACE CLIC EN EL BOTÓN
            // POR EJEMPLO, PARA MOSTRAR LA ESCENA MENUENTRENADOR:
            Scene currentScene = primaryStage.getScene();
            MenuExplorador menuExploracion = new MenuExplorador(primaryStage, currentScene);
            Scene menuExploradorScene = menuExploracion.getScene();
            primaryStage.setScene(menuExploradorScene);

        });
        pokedexButton.setOnAction(e -> {
            // AQUÍ PUEDES LLAMAR A LA CLASE POKEDEX
            System.out.println("Pokedex");

        });
        entrenadorButton.setOnAction(e -> {
            // CÓDIGO PARA EJECUTAR CUANDO SE HACE CLIC EN EL BOTÓN
            // POR EJEMPLO, PARA MOSTRAR LA ESCENA MENUENTRENADOR:
            Scene currentScene = primaryStage.getScene();
            MenuEntrenador menuEntrenador = new MenuEntrenador(primaryStage, currentScene);
            Scene menuEntrenadorScene = menuEntrenador.getScene();
            primaryStage.setScene(menuEntrenadorScene);
        });

//        mochilaButton.setOnAction(e -> {
//            // AQUÍ PUEDES LLAMAR A LA CLASE MOCHILA
//            System.out.println("Mocihla");
//
//        });

        tiendaButton.setOnAction(e -> {
        	  Scene currentScene = primaryStage.getScene();
              MenuTienda menuTienda = new MenuTienda(primaryStage, currentScene);
              Scene menuTiendaScene = menuTienda.getScene();
              primaryStage.setScene(menuTiendaScene);
              //Muestrar por Consola
            //Tienda.abrirTienda();
        });

        //FUNCION PARA SALIR DEL PROGRAMA
        salirButton.setOnAction(e -> primaryStage.close());


        // CREAMOS UN VBOX
        VBox root = new VBox(10, combateButton, exploracionButton, pokedexButton, entrenadorButton, tiendaButton, salirButton);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(20));
        BackgroundImage backgroundImage = new BackgroundImage(new Image("https://i.imgur.com/cDi4PvX.jpg"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        root.setBackground(new Background(backgroundImage));


        //TAMAÑO VENTANA

        Scene scene = new Scene(root, 1080, 650);
        scene.getStylesheets().add("Menu.css");

        primaryStage.setTitle("Menú de Inicio");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        abrirMenu();
    }

    public static void abrirMenu() {
        launch();
    }
}

