package Pokemon.Menus;


import Pokemon.Database.PokemonCRUD;
import Pokemon.Entrenador.Entrenador;
import Pokemon.Funcionalidad.Funcion;
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
import java.util.Objects;

public class Menu extends Application {


    Image imageBackground = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/Login.gif")));
    Image imageBackground2 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/Prueba1.gif")));
    Image imageBackground3 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/Prueba.gif")));


    public static BackgroundImage backgroundRandom(Image foto1, Image foto2, Image foto3) {

        int random = Funcion.random(1, 3);
        BackgroundImage backgroundImage;
        System.out.println("Metodo ejecutado");

        if (random == 1) {
            BackgroundSize backgroundSize = new BackgroundSize(1080, 650, false, false, false, false);
            backgroundImage = new BackgroundImage(foto1, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        } else if (random == 2) {
            BackgroundSize backgroundSize = new BackgroundSize(1080, 650, false, false, false, false);
            backgroundImage = new BackgroundImage(foto2, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        } else {
            BackgroundSize backgroundSize = new BackgroundSize(1080, 650, false, false, false, false);
            backgroundImage = new BackgroundImage(foto3, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        }

        return backgroundImage;
    }


    @Override
    public void start(Stage primaryStage) throws IOException {

        // CREAMOS UN REPRODUCTOR DE MEDIOS PARA REPRODUCIR EL AUDIO
        Media audioMedia = new Media(getClass().getResource("/aud/Menu.wav").toExternalForm());
        MediaPlayer audioMediaPlayer = new MediaPlayer(audioMedia);
        audioMediaPlayer.setAutoPlay(true);
        audioMediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);

        // AQUI SE AÑADEN LOS BOTONES DEL MENU
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
        // AGREGAR BOTÓN PARA SILENCIAR O REANUDAR EL SONIDO
        Button muteButton = new Button(" -ZzZ- ");
        muteButton.setOnAction(e -> {
            if (audioMediaPlayer.isMute()) {
                audioMediaPlayer.setMute(false);
            } else {
                audioMediaPlayer.setMute(true);
            }
        });

        // -----------------------------------------------------------------------------------------------------------------

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
            MenuExplorador menuExplorador = new MenuExplorador(primaryStage, primaryStage.getScene());
            menuExplorador.start(primaryStage);
            audioMediaPlayer.stop();
        });
        pokedexButton.setOnAction(e -> {
            // AQUÍ PUEDES LLAMAR A LA CLASE POKEDEX
            System.out.println(PokemonCRUD.generarPokemon());
            MenuPokedex menuPokedex = new MenuPokedex(primaryStage, primaryStage.getScene());
            menuPokedex.start(primaryStage);
            audioMediaPlayer.stop();
        });
        entrenadorButton.setOnAction(e -> {
            // DETENEMOS LA REPRODUCCIÓN DEL AUDIO
            MenuEntrenador menuEntrenador = new MenuEntrenador(primaryStage, primaryStage.getScene());
            menuEntrenador.start(primaryStage);
            audioMediaPlayer.stop();
        });
        tiendaButton.setOnAction(e -> {
            // DETENEMOS LA REPRODUCCIÓN DEL AUDIO
            MenuTienda menuTienda = new MenuTienda(primaryStage, primaryStage.getScene());
            menuTienda.start(primaryStage);
            audioMediaPlayer.stop();

            //Muestrar por Consola
            //Tienda.abrirTienda();
        });


        // FUNCION PARA SALIR DEL PROGRAMA
        salirButton.setOnAction(e -> primaryStage.close());

        // CREAMOS UN VBOX
        VBox root = new VBox(10, combateButton, exploracionButton, pokedexButton, entrenadorButton, tiendaButton,
                salirButton, muteButton);

        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(20));

        BackgroundImage backgroundImage = backgroundRandom(imageBackground, imageBackground2, imageBackground3);
        Background background = new Background(backgroundImage);
        root.setBackground(background);

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
