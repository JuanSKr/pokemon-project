package Pokemon.Capturar;

import Pokemon.Database.PokemonCRUD;
import Pokemon.Entrenador.Entrenador;
import Pokemon.Pokemon.Pokemon;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.LinkedList;
import java.util.Objects;

public class MenuEntrenador extends Application {
    private static final double WINDOW_WIDTH = 1080;
    private static final double WINDOW_HEIGHT = 650;
    private Scene previousScene;
    private Stage primaryStage;
    private LinkedList<Pokemon> equipo1 = new LinkedList<>();
    private LinkedList<Pokemon> equipo2 = new LinkedList<>();
    private LinkedList<Pokemon> caja = new LinkedList<>();

    public MenuEntrenador(Stage primaryStage, Scene previousScene) {
        this.primaryStage = primaryStage;
        this.previousScene = previousScene;
    }

    @Override
    public void start(Stage primaryStage) {

        PokemonCRUD.getEquipo1(equipo1, PokemonCRUD.idEntrenador());
        PokemonCRUD.getEquipo2(equipo2, PokemonCRUD.idEntrenador());
        PokemonCRUD.getCaja(caja, PokemonCRUD.idEntrenador());

        Image backgroundImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/pokedex.png")));
        ImageView backgroundImageView = new ImageView(backgroundImage);
        backgroundImageView.setFitWidth(1080);
        backgroundImageView.setFitHeight(650);
        // -------------------------MULTIMEDIA-----------------------------------------------
        // CREAMOS UN REPRODUCTOR DE MEDIOS PARA REPRODUCIR EL AUDIO
        Media audioMedia = new Media(getClass().getResource("/aud/MenuEntrenador.wav").toExternalForm());
        MediaPlayer audioMediaPlayer = new MediaPlayer(audioMedia);
        audioMediaPlayer.setAutoPlay(true);
        audioMediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);

        StackPane stackPane = new StackPane();// BOTONES MUTE Y BACK
        stackPane.setAlignment(Pos.TOP_LEFT);

        // AGREGAR BOTÓN PARA REGRESAR AL MENÚ ANTERIOR
        Button backButton = new Button(" <-- ");
        backButton.setOnAction(e -> {
            primaryStage.setScene(previousScene);
            audioMediaPlayer.stop();
        });
        // AGREGAR BOTÓN PARA SILENCIAR O REANUDAR EL SONIDO
        Button muteButton = new Button(" -ZzZ- ");
        muteButton.setOnAction(e -> {
            if (audioMediaPlayer.isMute()) {
                audioMediaPlayer.setMute(false);
            } else {
                audioMediaPlayer.setMute(true);
            }
        });
//-----------------------------------------------------------------------------------------------------------------------
        // Crear contenedor para equipo1
        VBox equipo1Container = new VBox();
        equipo1Container.setCursor(Cursor.CLOSED_HAND);// CAMBIA EL CURSOR
        equipo1Container.setAlignment(Pos.CENTER);

        Label equipo1Label = new Label("Equipo1");
        equipo1Label.setFont(new Font("Arial", 15));

        ListView<String> equipo1ListView = new ListView<>();
        // Añadir aqui configuraion de tamaño
        PokemonCRUD.getEquipo1(Entrenador.equipo1, PokemonCRUD.idEntrenador());
        obtenerEquipo1(equipo1, equipo1ListView);

        equipo1Container.getChildren().addAll(equipo1Label, equipo1ListView);

        // Crear contenedor para equipo2
        VBox equipo2Container = new VBox();
        equipo2Container.setCursor(Cursor.CLOSED_HAND);// CAMBIA EL CURSOR
        equipo2Container.setAlignment(Pos.CENTER);

        Label equipo2Label = new Label("Equipo2");
        equipo2Label.setFont(new Font("Arial", 15));

        ListView<String> equipo2ListView = new ListView<>();
        obtenerEquipo2(equipo2, equipo2ListView);

        equipo2Container.getChildren().addAll(equipo2Label, equipo2ListView);

        // Crear contenedor para caja
        VBox cajaContainer = new VBox();
        cajaContainer.setCursor(Cursor.CLOSED_HAND);// CAMBIA EL CURSOR
        cajaContainer.setAlignment(Pos.CENTER);

        Label cajaLabel = new Label("Caja");
        cajaLabel.setFont(new Font("Arial", 15));

        ListView<String> cajaListView = new ListView<>();
        obtenerCaja(caja, cajaListView);


        cajaContainer.getChildren().addAll(cajaLabel, cajaListView);


//------------------------------CODIGO PARA ARRASTRAR Y SOLTAR ----------------------------------------------------------------------
        // PERMITIR QUE LOS ELEMENTOS DE EQUIPO1LISTVIEW SEAN ARRASTRADOS
        equipo1ListView.setOnDragDetected(event -> {
            String pokemonSeleccionado = equipo1ListView.getSelectionModel().getSelectedItem();
            if (pokemonSeleccionado != null) {
                Dragboard arrastrado = equipo1ListView.startDragAndDrop(TransferMode.MOVE);
                ClipboardContent content = new ClipboardContent();
                content.putString(pokemonSeleccionado);
                arrastrado.setContent(content);
            }
            event.consume();
        });
        // PERMITIR QUE LOS ELEMENTOS DE EQUIPO2LISTVIEW SEAN ARRASTRADOS
        equipo2ListView.setOnDragDetected(event -> {
            String pokemonSeleccionado = equipo2ListView.getSelectionModel().getSelectedItem();
            if (pokemonSeleccionado != null) {
                Dragboard arrastrado = equipo2ListView.startDragAndDrop(TransferMode.MOVE);
                ClipboardContent content = new ClipboardContent();
                content.putString(pokemonSeleccionado);
                arrastrado.setContent(content);
            }
            event.consume();
        });
        // PERMITIR QUE LOS ELEMENTOS DE CAJALISTVIEW SEAN ARRASTRADOS
        cajaListView.setOnDragDetected(event -> {
            String pokemonSeleccionado = cajaListView.getSelectionModel().getSelectedItem();
            if (pokemonSeleccionado != null) {
                Dragboard arrastrado = cajaListView.startDragAndDrop(TransferMode.MOVE);
                ClipboardContent content = new ClipboardContent();
                content.putString(pokemonSeleccionado);
                arrastrado.setContent(content);
            }
            event.consume();
        });
        // PERMITIR QUE LOS ELEMENTOS SEAN SOLTADOS EN EQUIPO1LISTVIEW
        equipo1ListView.setOnDragOver(event -> {
            if (event.getGestureSource() != equipo1ListView && event.getDragboard().hasString()) {
                event.acceptTransferModes(TransferMode.MOVE);
            }
            event.consume();
        });

        // Mover elementos

        // Mover a equipo 1
        equipo1ListView.setOnDragDropped(event -> {
            Dragboard arrastrar = event.getDragboard();
            boolean completado = false;
            if (arrastrar.hasString() && equipo1ListView.getItems().size() < 6) {
                String pokemon = arrastrar.getString();
                String nombrePokemon = pokemon;
                int idPokemon = PokemonCRUD.idPokemon(nombrePokemon);
                ((ListView<String>) event.getGestureSource()).getItems().remove(pokemon); // Eliminar del contenedor de origen
                equipo1ListView.getItems().add(pokemon);
                completado = true;
                PokemonCRUD.actualizarPokemon(1, idPokemon);
            } else if (arrastrar.hasString()) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Información");
                alert.setHeaderText(null);
                alert.setContentText("El contenedor Equipo1 ha alcanzado su capacidad máxima de 6 elementos.");
                alert.showAndWait();
            }
            event.setDropCompleted(completado);
            event.consume();
        });

        // PERMITIR QUE LOS ELEMENTOS SEAN SOLTADOS EN EQUIPO2LISTVIEW
        equipo2ListView.setOnDragOver(event -> {
            if (event.getGestureSource() != equipo2ListView && event.getDragboard().hasString()) {
                event.acceptTransferModes(TransferMode.MOVE);
            }
            event.consume();
        });


        // Mover a equipo 2
        equipo2ListView.setOnDragDropped(event -> {
            Dragboard arrastrar = event.getDragboard();
            boolean completado = false;
            if (arrastrar.hasString() && equipo2ListView.getItems().size() < 6) {
                String pokemon = arrastrar.getString();
                String nombrePokemon = pokemon;
                int idPokemon = PokemonCRUD.idPokemon(nombrePokemon);
                ((ListView<String>) event.getGestureSource()).getItems().remove(pokemon); // Eliminar del contenedor de origen
                equipo2ListView.getItems().add(pokemon);
                completado = true;
                PokemonCRUD.actualizarPokemon(2, idPokemon);
            } else if (arrastrar.hasString()) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Información");
                alert.setHeaderText(null);
                alert.setContentText("El contenedor Equipo2 ha alcanzado su capacidad máxima de 6 elementos.");
                alert.showAndWait();
            }
            event.setDropCompleted(completado);
            event.consume();
        });
        // PERMITIR QUE LOS ELEMENTOS SEAN SOLTADOS EN CAJALISTVIEW
        cajaListView.setOnDragOver(event -> {
            if (event.getGestureSource() != cajaListView && event.getDragboard().hasString()) {
                event.acceptTransferModes(TransferMode.MOVE);
            }
            event.consume();
        });

        // Mover a caja
        cajaListView.setOnDragDropped(event -> {
            Dragboard arrastrar = event.getDragboard();
            boolean completado = false;
            if (arrastrar.hasString() && cajaListView.getItems().size() < 6) { //Cambiar a 15
                String pokemon = arrastrar.getString();
                String nombrePokemon = pokemon;
                int idPokemon = PokemonCRUD.idPokemon(nombrePokemon);
                ((ListView<String>) event.getGestureSource()).getItems().remove(pokemon); // Eliminar del contenedor de origen
                cajaListView.getItems().add(pokemon);
                completado = true;
                PokemonCRUD.actualizarPokemon(3, idPokemon);
            } else if (arrastrar.hasString()) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Información");
                alert.setHeaderText(null);
                alert.setContentText("El contenedor Caja ha alcanzado su capacidad máxima de 6 elementos.");
                alert.showAndWait();
            }
            event.setDropCompleted(completado);
            event.consume();
        });

//-----------------------------------------------------------------------------------------------------------------------------------
        VBox containers = new VBox();
        containers.setAlignment(Pos.CENTER_LEFT);
        containers.getChildren().addAll(equipo1Container, equipo2Container, cajaContainer);

        stackPane.getChildren().add(containers);

        StackPane.setAlignment(muteButton, Pos.TOP_RIGHT);
        stackPane.getChildren().addAll(muteButton);// Añadir backgroundImageView para mostrar la imagen
        stackPane.getChildren().add(backButton);

        Scene scene = new Scene(stackPane, WINDOW_WIDTH, WINDOW_HEIGHT);
        // equipo2Container.getStylesheets().add("Entrenador.css"); Cambia el estilo de
        // equipo2Container

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void obtenerEquipo1(LinkedList<Pokemon> equipo1, ListView<String> equipo1ListView) {

        if (equipo1.size() == 0) {
            System.out.println("Vacio");
        } else if (equipo1.size() == 1) {
            equipo1ListView.getItems().addAll(String.valueOf(equipo1.get(0)));
        } else if (equipo1.size() == 2) {
            equipo1ListView.getItems().addAll(String.valueOf(equipo1.get(0)),
                    String.valueOf(equipo1.get(1)));
        } else if (equipo1.size() == 3) {
            equipo1ListView.getItems().addAll(String.valueOf(equipo1.get(0)),
                    String.valueOf(equipo1.get(1)), String.valueOf(equipo1.get(2)));
        } else if (equipo1.size() == 4) {
            equipo1ListView.getItems().addAll(String.valueOf(equipo1.get(0)),
                    String.valueOf(equipo1.get(1)), String.valueOf(equipo1.get(2)),
                    String.valueOf(equipo1.get(3)));
        } else if (equipo1.size() == 5) {
            equipo1ListView.getItems().addAll(String.valueOf(equipo1.get(0)),
                    String.valueOf(equipo1.get(1)), String.valueOf(equipo1.get(2)),
                    String.valueOf(equipo1.get(3)), String.valueOf(equipo1.get(4)));
        } else if (equipo1.size() == 6) {
            equipo1ListView.getItems().addAll(String.valueOf(equipo1.get(0)),
                    String.valueOf(equipo1.get(1)), String.valueOf(equipo1.get(2)),
                    String.valueOf(equipo1.get(3)), String.valueOf(equipo1.get(4)),
                    String.valueOf(equipo1.get(5)));
        }

    }

    public static void obtenerEquipo2(LinkedList<Pokemon> equipo2, ListView<String> equipo2ListView) {

        if (equipo2.size() == 0) {
            System.out.println("Vacio");
        } else if (equipo2.size() == 1) {
            equipo2ListView.getItems().addAll(String.valueOf(equipo2.get(0)));
        } else if (equipo2.size() == 2) {
            equipo2ListView.getItems().addAll(String.valueOf(equipo2.get(0)),
                    String.valueOf(equipo2.get(1)));
        } else if (equipo2.size() == 3) {
            equipo2ListView.getItems().addAll(String.valueOf(equipo2.get(0)),
                    String.valueOf(equipo2.get(1)), String.valueOf(equipo2.get(2)));
        } else if (equipo2.size() == 4) {
            equipo2ListView.getItems().addAll(String.valueOf(equipo2.get(0)),
                    String.valueOf(equipo2.get(1)), String.valueOf(equipo2.get(2)),
                    String.valueOf(equipo2.get(3)));
        } else if (equipo2.size() == 5) {
            equipo2ListView.getItems().addAll(String.valueOf(equipo2.get(0)),
                    String.valueOf(equipo2.get(1)), String.valueOf(equipo2.get(2)),
                    String.valueOf(equipo2.get(3)), String.valueOf(equipo2.get(4)));
        } else if (equipo2.size() == 6) {
            equipo2ListView.getItems().addAll(String.valueOf(equipo2.get(0)),
                    String.valueOf(equipo2.get(1)), String.valueOf(equipo2.get(2)),
                    String.valueOf(equipo2.get(3)), String.valueOf(equipo2.get(4)),
                    String.valueOf(equipo2.get(5)));
        } else {
            equipo2ListView.getItems().add("No hay ningún Pokemon.");
        }

    }

    public static void obtenerCaja(LinkedList<Pokemon> caja, ListView<String> equipo1ListView) {

        if (caja.size() == 0) {
            System.out.println("Vacio");
        } else if (caja.size() == 1) {
            equipo1ListView.getItems().addAll(String.valueOf(caja.get(0)));
        } else if (caja.size() == 2) {
            equipo1ListView.getItems().addAll(String.valueOf(caja.get(0)),
                    String.valueOf(caja.get(1)));
        } else if (caja.size() == 3) {
            equipo1ListView.getItems().addAll(String.valueOf(caja.get(0)),
                    String.valueOf(caja.get(1)), String.valueOf(caja.get(2)));
        } else if (caja.size() == 4) {
            equipo1ListView.getItems().addAll(String.valueOf(caja.get(0)),
                    String.valueOf(caja.get(1)), String.valueOf(caja.get(2)),
                    String.valueOf(caja.get(3)));
        } else if (caja.size() == 5) {
            equipo1ListView.getItems().addAll(String.valueOf(caja.get(0)),
                    String.valueOf(caja.get(1)), String.valueOf(caja.get(2)),
                    String.valueOf(caja.get(3)), String.valueOf(caja.get(4)));
        } else if (caja.size() == 6) {
            equipo1ListView.getItems().addAll(String.valueOf(caja.get(0)),
                    String.valueOf(caja.get(1)), String.valueOf(caja.get(2)),
                    String.valueOf(caja.get(3)), String.valueOf(caja.get(4)),
                    String.valueOf(caja.get(5)));
        }

    }


}
