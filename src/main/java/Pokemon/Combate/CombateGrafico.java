package Pokemon.Combate;

import Pokemon.Combate.Movimientos.Ataque;
import Pokemon.Combate.Movimientos.Movimiento;
import Pokemon.Database.PokemonCRUD;
import Pokemon.Entrenador.Entrenador;
import Pokemon.Menus.Menu;
import Pokemon.Pokemon.Pokemon;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.stage.Stage;

import java.util.LinkedList;
import java.util.Objects;

public class CombateGrafico extends Application {


    private Stage primaryStage;
    private Scene previousScene;
    private Scene currentScene;
    protected static int opcion;
    protected static ProgressBar vidaRival = new ProgressBar();
    protected static ProgressBar vidaJugador = new ProgressBar();
    protected static int contador = 1;
    protected static int contadorEntrenador = 0;

    // Declaramos los botones que se van a utilizar en la clase

    static Button descansarButton = new Button();
    static Button atacarButton = new Button();
    static Button mochilaButton = new Button();
    static Button rendirseButton = new Button();

    static Button movimiento1Button = new Button();
    static Button movimiento2Button = new Button();
    static Button movimiento3Button = new Button();
    static Button movimiento4Button = new Button();

    // Declaramos el ancho y el laro de la ventana como constante.

    private static final int SCENE_WIDTH = 1080;
    private static final int SCENE_HEIGHT = 650;

    // Declaramos tanto el pokemon del rival como el del entrenador.

    static Pokemon pRival = PokemonCRUD.generarPokemon();

    static Pokemon pEntrenador = Combate.elegirPokemon(Selector.getOpcionSeleccionada());

    // Declaramos los objetos Label para los movimientos.

    static Label movimiento1Txt = new Label();
    static Label movimiento2Txt = new Label();
    static Label movimiento3Txt = new Label();
    static Label movimiento4Txt = new Label();

    static Label combateTxt = new Label();

    public CombateGrafico(Stage primaryStage, Scene currentScene) {
        this.primaryStage = primaryStage;
        this.currentScene = currentScene;
    }

    public Scene getScene() {

        Rival rival = Rival.generarRival();

        restaurarBotones();

        Image backgroundImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/fondocombate.jpg")));
        ImageView backgroundImageView = new ImageView(backgroundImage);
        backgroundImageView.setFitWidth(1080);
        backgroundImageView.setFitHeight(650);

        movimiento1Button.setMouseTransparent(true);
        movimiento1Button.setId("movimiento1");
        movimiento2Button.setMouseTransparent(true);
        movimiento2Button.setId("movimiento2");
        movimiento3Button.setMouseTransparent(true);
        movimiento3Button.setId("movimiento3");
        movimiento4Button.setMouseTransparent(true);
        movimiento4Button.setId("movimiento4");

        // CREAR UN CONTENEDOR PRINCIPAL
        VBox root = new VBox();
        root.setSpacing(30);
        root.setPadding(new Insets(10));
        root.setAlignment(Pos.TOP_LEFT);
        root.getChildren().add(backgroundImageView);


        // CREAR UN CONTENEDOR PARA LA PARTE INFERIOR
        Pane topContainer = new Pane(); // Cambiado de HBox a Pane
        topContainer.setPrefWidth(SCENE_WIDTH);
        topContainer.setPrefHeight(200);
        topContainer.setLayoutY(SCENE_HEIGHT - 200);
        root.getChildren().add(topContainer);

        // Imagen para las stats del Rival
        Image statsRival = new Image(getClass().getResourceAsStream("/img/statsrival.png"));
        ImageView statsRivalView = new ImageView(statsRival);
        statsRivalView.setFitWidth(250);
        statsRivalView.setFitHeight(100);
        statsRivalView.setLayoutX(828);
        statsRivalView.setLayoutY(7);
        topContainer.getChildren().add(statsRivalView);

        // CREAR UNA IMAGEN PARA EL POKÉMON RIVAL
        String foto = pRival.getFoto();
        System.out.println(foto); //TEST
        Image pRivalImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream(foto)));
        ImageView rivalView = new ImageView(pRivalImage);
        rivalView.setFitWidth(170);
        rivalView.setFitHeight(110);
        rivalView.setLayoutX(800);
        rivalView.setLayoutY(170);
        topContainer.getChildren().add(rivalView);

        // CREAR UNA BARRA DE VIDA PARA EL POKÉMON RIVAL
        double vidaR = pRival.getVitalidad();
        vidaRival.setProgress(vidaR / 100);
        vidaRival.setPrefWidth(150);
        VBox.setMargin(vidaRival, new Insets(0, 0, 0, 50));
        vidaRival.setLayoutX(892);
        vidaRival.setLayoutY(68);
        topContainer.getChildren().add(vidaRival);
        vidaRival.setStyle("-fx-background-color: lightgray; -fx-accent: #993399;");

        // CREAR UN CONTENEDOR PARA LA PARTE INFERIOR
        Pane bottomContainer = new Pane(); // Cambiado de HBox a Pane
        bottomContainer.setPrefWidth(SCENE_WIDTH);
        bottomContainer.setPrefHeight(200);
        bottomContainer.setLayoutY(SCENE_HEIGHT - 200);
        root.getChildren().add(bottomContainer);

        // CREAR UNA IMAGEN PARA EL POKÉMON DEL JUGADOR
        String rutaEntrenador = pEntrenador.getFotoEspalda();
        System.out.println(rutaEntrenador);
        Image pJugadorImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream(rutaEntrenador)));
        ImageView jugadorView = new ImageView(pJugadorImage);
        jugadorView.setFitWidth(190);
        jugadorView.setFitHeight(130);
        jugadorView.setLayoutX(100);
        jugadorView.setLayoutY(18);
        bottomContainer.getChildren().add(jugadorView);

        VBox movimientoButtonsContainer = new VBox();
        movimientoButtonsContainer.getChildren().addAll(movimiento1Button, movimiento2Button, movimiento3Button, movimiento4Button);
        movimientoButtonsContainer.setAlignment(Pos.BASELINE_RIGHT);
        movimientoButtonsContainer.setSpacing(10);
        bottomContainer.getChildren().add(movimientoButtonsContainer);

        // Imagen para las stats del entrenador del Pokemon
        Image statsEntrenador = new Image(getClass().getResourceAsStream("/img/stats.png"));
        ImageView statsEntrenadorView = new ImageView(statsEntrenador);
        statsEntrenadorView.setFitWidth(250);
        statsEntrenadorView.setFitHeight(100);
        statsEntrenadorView.setLayoutX(-22);
        statsEntrenadorView.setLayoutY(-100);
        bottomContainer.getChildren().add(statsEntrenadorView);

        // CREAR UNA BARRA DE VIDA PARA EL POKÉMON DEL JUGADOR
        double vidaJ = pRival.getVitalidad();
        vidaJugador.setProgress(vidaJ / 100);
        vidaJugador.setPrefWidth(150);
        VBox.setMargin(vidaJugador, new Insets(0, 0, 0, 50)); // Margen izquierdo de 20px
        vidaJugador.setLayoutX(10);
        vidaJugador.setLayoutY(-35);
        bottomContainer.getChildren().add(vidaJugador);
        vidaJugador.setStyle("-fx-background-color: lightgray; -fx-accent: #ff6600;");

        // Nombre pokemon entrenador

        String nombreEntrenador = pEntrenador.getNombre();
        Label entrenadorTxt = new Label(nombreEntrenador);
        entrenadorTxt.setId("entrenadorLabel");
        GridPane.setConstraints(entrenadorTxt, 0, 0);

        // Mostrar estamina del Pokemon del entrenador

        String estaminaE = "EST: " + pEntrenador.getEstamina();
        Label estaminaEntrenador = new Label(estaminaE);
        estaminaEntrenador.setId("estaminaEntrenador");
        GridPane.setConstraints(estaminaEntrenador, 0, 0);

        // Nombre pokemon rival

        String nombre = pRival.getNombre();
        Label rivalTxt = new Label(nombre);
        rivalTxt.setId("rivalLabel");
        GridPane.setConstraints(rivalTxt, 0, 0);

        // Mostrar estamina del Pokemon del rival

        String estaminaR = "EST: " + pRival.getEstamina();
        Label estaminaRival = new Label(estaminaR);
        estaminaRival.setId("estaminaRival");
        GridPane.setConstraints(estaminaRival, 0, 0);

        Alert inicio = new Alert(Alert.AlertType.INFORMATION);
        inicio.setTitle("¡Empieza el combate!");
        inicio.setHeaderText("Vas a luchar contra el " + rival.getNombre());
        inicio.setContentText("¡El " + rival.getNombre() + " empezará con " + pRival.getNombre() + "!");
        inicio.showAndWait();

        // Combate rival

        int primerAtaque = Combate.calcularVelocidad(pEntrenador, pRival);

        if (primerAtaque == 0) {
            System.out.println("Empiezas");
        } else {
            ataqueRival(vidaJugador, estaminaRival);
        }

        // CREAR BOTONES PARA LOS MOVIMIENTOS DEL POKÉMON

        atacarButton.setId("atacar");
        atacarButton.setOnAction(event -> {
            // Oculta el botón "atacar"
            atacarButton.setVisible(false);
            descansarButton.setVisible(false);
            mochilaButton.setVisible(false);
            rendirseButton.setVisible(false);

            // Muestra los botones de movimiento
            botonesMovimiento();

            obtenerMovimientos(pEntrenador);
        });


        // Movimiento 1
        movimiento1Button.setOnAction(event -> {

            if (pEntrenador.getVitalidad() > 0) {
                if (pRival.getVitalidad() > 0) {
                    ataqueRival(vidaJugador, estaminaRival);
                    Combate.ejecMovimiento1(pEntrenador, pRival);
                    actualizarEstamina(pEntrenador, estaminaEntrenador);
                    modificarBarra(vidaRival, pRival);
                    restaurarBotones();

                } else {
                    System.out.println(pRival.getNombre() + " se ha debilitado.");
                    pRival = PokemonCRUD.generarPokemon();
                    actualizarRival(pRival, vidaRival, estaminaRival, rivalView, rivalTxt, topContainer);

                }

            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle(pEntrenador.getNombre() + " se ha debilitado.");
                alert.setHeaderText(null);
                alert.setContentText(pEntrenador.getNombre() + " se ha debilitado. Cámbialo para seguir luchando.");
                contadorEntrenador++;
                alert.showAndWait();
                restaurarBotones();
                if(contadorEntrenador == 6) {
                    Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("¡Has perdido!");
                    alert.setHeaderText("Vaya... Has perdido el combate.");
                    alert.setContentText("Has perdido: " + Combate.dineroPerdido() + " PokeDollars.");
                    PokemonCRUD.dineroPerdido(Combate.dineroPerdido());
                    backMenu();
                    alert.showAndWait();
                }
            }

        });

        // Movimiento 2
        movimiento2Button.setOnAction(event -> {

            if (pEntrenador.getVitalidad() > 0) {
                if (pRival.getVitalidad() > 0) {
                    ataqueRival(vidaJugador, estaminaRival);
                    Combate.ejecMovimiento2(pEntrenador, pRival);
                    actualizarEstamina(pEntrenador, estaminaEntrenador);
                    modificarBarra(vidaRival, pRival);
                    restaurarBotones();

                } else {

                    System.out.println(pRival.getNombre() + " se ha debilitado.");
                    restaurarBotones();
                    pRival = PokemonCRUD.generarPokemon();
                    actualizarRival(pRival, vidaRival, estaminaRival, rivalView, rivalTxt, topContainer);
                }

            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle(pEntrenador.getNombre() + " se ha debilitado.");
                alert.setHeaderText(null);
                alert.setContentText(pEntrenador.getNombre() + " se ha debilitado. Cámbialo para seguir luchando.");
                contadorEntrenador++;
                alert.showAndWait();
                restaurarBotones();
                if(contadorEntrenador == 6) {
                    Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("¡Has perdido!");
                    alert.setHeaderText("Vaya... Has perdido el combate.");
                    alert.setContentText("Has perdido: " + Combate.dineroPerdido() + " PokeDollars.");
                    PokemonCRUD.dineroPerdido(Combate.dineroPerdido());
                    backMenu();
                    alert.showAndWait();
                }
            }
        });

        // Movimiento 3
        movimiento3Button.setOnAction(event -> {

            if (pEntrenador.getVitalidad() > 0) {
                if (pRival.getVitalidad() > 0) {
                    ataqueRival(vidaJugador, estaminaRival);
                    Combate.ejecMovimiento3(pEntrenador, pRival);
                    actualizarEstamina(pEntrenador, estaminaEntrenador);
                    modificarBarra(vidaRival, pRival);
                    restaurarBotones();

                } else {
                    System.out.println(pRival.getNombre() + " se ha debilitado.");
                }

            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle(pEntrenador.getNombre() + " se ha debilitado.");
                alert.setHeaderText(null);
                alert.setContentText(pEntrenador.getNombre() + " se ha debilitado. Cámbialo para seguir luchando.");
                contadorEntrenador++;
                alert.showAndWait();
                restaurarBotones();
                if(contadorEntrenador == 6) {
                    Alert alert3 = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("¡Has perdido!");
                    alert.setHeaderText("Vaya... Has perdido el combate.");
                    alert.setContentText("Has perdido: " + Combate.dineroPerdido() + " PokeDollars.");
                    PokemonCRUD.dineroPerdido(Combate.dineroPerdido());
                    backMenu();
                    alert.showAndWait();
                }
            }
        });

        // Movimiento 4
        movimiento4Button.setOnAction(event -> {

            if (pEntrenador.getVitalidad() > 0) {
                if (pRival.getVitalidad() > 0) {
                    ataqueRival(vidaJugador, estaminaRival);
                    Combate.ejecMovimiento4(pEntrenador, pRival);
                    actualizarEstamina(pEntrenador, estaminaEntrenador);
                    modificarBarra(vidaRival, pRival);
                    restaurarBotones();

                } else {
                    System.out.println(pRival.getNombre() + " se ha debilitado.");
                }

            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle(pEntrenador.getNombre() + " se ha debilitado.");
                alert.setHeaderText(null);
                alert.setContentText(pEntrenador.getNombre() + " se ha debilitado. Cámbialo para seguir luchando.");
                contadorEntrenador++;
                alert.showAndWait();
                restaurarBotones();
                if(contadorEntrenador == 6) {
                    Alert alert4 = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("¡Has perdido!");
                    alert.setHeaderText("Vaya... Has perdido el combate.");
                    alert.setContentText("Has perdido: " + Combate.dineroPerdido() + " PokeDollars.");
                    PokemonCRUD.dineroPerdido(Combate.dineroPerdido());
                    backMenu();
                    alert.showAndWait();
                }
            }

        });

        descansarButton.setId("descansar");
        descansarButton.setOnAction(event -> {
            if (pEntrenador.getVitalidad() > 0) {
                if (pRival.getVitalidad() > 0) {
                    double nuevaEstamina = Combate.descansar(pEntrenador);
                    pEntrenador.setEstamina(nuevaEstamina);
                    actualizarEstamina(pEntrenador, estaminaEntrenador);
                    ataqueRival(vidaJugador, estaminaRival);
                } else {
                    System.out.println(pRival.getNombre() + "se ha debilitado.");
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle(pEntrenador.getNombre() + " se ha debilitado.");
                alert.setHeaderText(null);
                alert.setContentText(pEntrenador.getNombre() + " se ha debilitado. Cámbialo para seguir luchando.");
                contadorEntrenador++;
                alert.showAndWait();
                if(contadorEntrenador == 6) {
                    Alert alert5 = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("¡Has perdido!");
                    alert.setHeaderText("Vaya... Has perdido el combate.");
                    alert.setContentText("Has perdido: " + Combate.dineroPerdido() + " PokeDollars.");
                    PokemonCRUD.dineroPerdido(Combate.dineroPerdido());
                    backMenu();
                    alert.showAndWait();
                }
            }
        });


        mochilaButton.setId("mochila");
        mochilaButton.setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("No disponible");
            alert.setHeaderText(null);
            alert.setContentText("Mochila no está disponible.");
            alert.showAndWait();
        });

        rendirseButton.setId("rendirse");
        rendirseButton.setOnAction(event -> {
            backMenu();
            abandonarCombate();
        });

        LinkedList<Pokemon> listaPokemon = new LinkedList<>();
        PokemonCRUD.getEquipo1(listaPokemon, PokemonCRUD.idEntrenador());
        ComboBox<Pokemon> equipo = new ComboBox<>();
        AnchorPane equipoContainer = new AnchorPane();
        equipoContainer.getChildren().add(equipo);
        equipo.setLayoutX(0);
        equipo.setLayoutY(0);

        for (Pokemon pokemon : listaPokemon) {
            equipo.getItems().add(pokemon);
        }

        equipo.setValue(pEntrenador);

        equipo.setOnAction(event -> {
            Pokemon pokemonElegido = equipo.getValue();
            if (pokemonElegido != null) {
                if (pokemonElegido.getNombre().equals(pEntrenador.getNombre())) {
                    System.out.println("¡Ese Pokémon ya está en combate!");
                } else {
                    System.out.println("Has seleccionado: " + pokemonElegido.getNombre());
                    restaurarBotones();
                    pokemonElegido = PokemonCRUD.getPokemon(pokemonElegido.getId());
                    obtenerMovimientos(pokemonElegido);
                    pEntrenador = pokemonElegido;
                    actualizarPokemon(pEntrenador, vidaJugador, estaminaEntrenador, jugadorView, entrenadorTxt, bottomContainer);
                    ataqueRival(vidaJugador, estaminaRival);
                }
            }
        });

        VBox movesContainer = new VBox();
        movesContainer.getChildren().addAll(atacarButton, descansarButton, mochilaButton, rendirseButton);
        movesContainer.setAlignment(Pos.BASELINE_RIGHT);
        movesContainer.setSpacing(10);
        bottomContainer.getChildren().add(movesContainer);
        // CREAR UN CAJÓN RECTANGULAR PARA MOSTRAR TEXTO
        // Creamos el rectángulo de fondo blanco opaco
        Rectangle rectangle = new Rectangle(750, 200);
        rectangle.setFill(Color.WHITE);
        rectangle.setOpacity(0.8);
        rectangle.setStroke(Color.GRAY);
        rectangle.setStrokeWidth(2);
        rectangle.setStrokeType(StrokeType.INSIDE);
        rectangle.setLayoutX(9.5);
        rectangle.setLayoutY(543.5);


        StackPane stackPane = new StackPane();
        Pane rectPane = new Pane(rectangle);
        stackPane.getChildren().addAll(backgroundImageView, rectPane, root, rivalTxt, entrenadorTxt, estaminaEntrenador, estaminaRival, movimiento1Txt, movimiento2Txt, movimiento3Txt, movimiento4Txt, equipoContainer);
        root.setAlignment(Pos.CENTER);
        Scene scene = new Scene(stackPane, SCENE_WIDTH, SCENE_HEIGHT);
        scene.getStylesheets().add("Combate.css");

        primaryStage.setScene(scene);
        primaryStage.show();
        return scene;
    }

    /**
     * Obtener los 4 movimientos del Pokemon del entrenador.
     *
     * @param pokemonEntrenador
     */

    public static void obtenerMovimientos(Pokemon pokemonEntrenador) {

        String nombre;
        Movimiento movimiento1 = pokemonEntrenador.getMovimiento1();
        Movimiento movimiento2 = pokemonEntrenador.getMovimiento2();
        Movimiento movimiento3 = pokemonEntrenador.getMovimiento3();
        Movimiento movimiento4 = pokemonEntrenador.getMovimiento4();

        if (movimiento1 != null) {
            nombre = movimiento1.getNombreMovimiento().toUpperCase();
            movimiento1Txt.setText(nombre);  // Establecer el texto del movimiento2Txt
            movimiento1Txt.setId("movimiento1Label");
            GridPane.setConstraints(movimiento1Txt, 0, 0);

        } else { //El movimiento1 nunca debería estar vació, pero se pone un else por si acaso
            movimiento1Txt.setId("movimiento1Vacio");
            GridPane.setConstraints(movimiento1Txt, 0, 0);
            movimiento1Txt.setText("SLOT VACIO");  // Establecer el texto del movimiento2Txt
        }

        if (movimiento2 != null) {
            nombre = movimiento2.getNombreMovimiento().toUpperCase();
            movimiento2Txt.setText(nombre);
            movimiento2Txt.setId("movimiento2Label");
            GridPane.setConstraints(movimiento2Txt, 0, 0);
        } else {
            movimiento2Txt.setId("movimiento2Vacio");
            GridPane.setConstraints(movimiento2Txt, 0, 0);
            movimiento2Txt.setText("SLOT VACIO");
        }
        if (movimiento3 != null) {
            nombre = movimiento3.getNombreMovimiento().toUpperCase();
            movimiento3Txt.setText(nombre);
            movimiento3Txt.setId("movimiento3Label");
            GridPane.setConstraints(movimiento3Txt, 0, 0);
        } else {
            movimiento3Txt.setId("movimiento3Vacio");
            GridPane.setConstraints(movimiento3Txt, 0, 0);
            movimiento3Txt.setText("SLOT VACIO");
        }
        if (movimiento4 != null) {
            nombre = movimiento4.getNombreMovimiento().toUpperCase();
            movimiento4Txt.setText(nombre);
            movimiento4Txt.setId("movimiento4Label");
            GridPane.setConstraints(movimiento4Txt, 0, 0);
        } else {
            movimiento4Txt.setId("movimiento4Vacio");
            GridPane.setConstraints(movimiento4Txt, 0, 0);
            movimiento4Txt.setText("SLOT VACIO");
        }

    }

    public void botonesMovimiento() {
        movimiento1Button.setVisible(true);
        movimiento2Button.setVisible(true);
        movimiento3Button.setVisible(true);
        movimiento4Button.setVisible(true);
        movimiento1Txt.setVisible(true);
        movimiento2Txt.setVisible(true);
        movimiento3Txt.setVisible(true);
        movimiento4Txt.setVisible(true);
        movimiento1Button.setMouseTransparent(false);
        movimiento2Button.setMouseTransparent(false);
        movimiento3Button.setMouseTransparent(false);
        movimiento4Button.setMouseTransparent(false);
        movimiento1Txt.setMouseTransparent(true);
        movimiento2Txt.setMouseTransparent(true);
        movimiento3Txt.setMouseTransparent(true);
        movimiento4Txt.setMouseTransparent(true);
    }

    public void restaurarBotones() {
        // Restaura la visibilidad de los botones originales
        atacarButton.setVisible(true);
        descansarButton.setVisible(true);
        mochilaButton.setVisible(true);
        rendirseButton.setVisible(true);

        // Oculta los botones de movimiento
        movimiento1Button.setVisible(false);
        movimiento2Button.setVisible(false);
        movimiento3Button.setVisible(false);
        movimiento4Button.setVisible(false);
        movimiento1Txt.setVisible(false);
        movimiento2Txt.setVisible(false);
        movimiento3Txt.setVisible(false);
        movimiento4Txt.setVisible(false);

    }

    /**
     * Este método modifica la barra de vida el Pokemon que se le pase por parámetro.
     *
     * @param barra
     * @param pokemon
     */
    public static void modificarBarra(ProgressBar barra, Pokemon pokemon) {

        barra.setProgress(1);

        barra.setProgress(pokemon.getVitalidad() / 100);

        if (pokemon.getVitalidad() <= 60) {
            barra.setStyle("-fx-background-color: lightgray; -fx-accent: #FFC300;");
            if (pokemon.getVitalidad() <= 40) {
                barra.setStyle("-fx-background-color: lightgray; -fx-accent: #FFB600;");
                if (pokemon.getVitalidad() <= 27) {
                    barra.setStyle("-fx-background-color: lightgray; -fx-accent: #BA0B0B;");
                    if (pokemon.getVitalidad() <= 0) {
                        barra.setProgress(0);
                        barra.setStyle("-fx-background-color: lightgray; -fx-accent: #FFFFFF;");

                    }

                }

            }

        }

    }

    /**
     * Este método actualiza la estamina del Pokemon que se le pase por parámetro, en el label que se le pase por parámetro
     *
     * @param pokemon
     * @param estaminaLabel
     */

    public static void actualizarEstamina(Pokemon pokemon, Label estaminaLabel) {

        double nuevaEstamina = pokemon.getEstamina();

        String estaminaE = "EST: " + nuevaEstamina;
        estaminaLabel.setText(estaminaE);
    }

    public static int getOpcion() {
        return opcion;
    }

    public static void ataqueRival(ProgressBar vidaJugador, Label estaminaRival) {

        Combate.accionRival(pRival, pEntrenador, estaminaRival);
        modificarBarra(vidaJugador, pEntrenador);
        actualizarEstamina(pRival, estaminaRival);
        System.out.println("Estamina rival: " + pRival.getEstamina());

    }


    public static void actualizarRival(Pokemon pokemon, ProgressBar viejaBarra, Label estamina, ImageView imagenPokemon, Label nombre, Pane contenedor) {

        if (contador < 6) {

            contador++;
            ProgressBar nuevaBarra = new ProgressBar();
            nuevaBarra.setProgress(pokemon.getVitalidad() / 100);
            nuevaBarra.setPrefWidth(150);
            VBox.setMargin(nuevaBarra, new Insets(0, 0, 0, 50));
            nuevaBarra.setLayoutX(892);
            nuevaBarra.setLayoutY(68);
            nuevaBarra.setStyle("-fx-background-color: lightgray; -fx-accent: #993399;");

            contenedor.getChildren().remove(viejaBarra);
            contenedor.getChildren().add(nuevaBarra);

            actualizarEstamina(pokemon, estamina);
            Image nuevaImagen = new Image(Objects.requireNonNull(CombateGrafico.class.getResourceAsStream(pokemon.getFoto())));
            imagenPokemon.setImage(nuevaImagen);
            nombre.setText(pokemon.getNombre());

            vidaRival = nuevaBarra;
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("¡Has ganado!");
            alert.setHeaderText("¡Felicidades! Has ganado el combate.");
            alert.setContentText("Has ganado: " + Combate.dineroGanado() + " PokeDollars.");
            PokemonCRUD.dineroGanado(Combate.dineroGanado());
            backMenu();
            alert.showAndWait();
        }
    }

    /**
     * @param pokemon
     * @param viejaBarra
     * @param estamina
     * @param imagenPokemon
     * @param nombre
     * @param contenedor
     */

    public static void actualizarPokemon(Pokemon pokemon, ProgressBar viejaBarra, Label estamina, ImageView imagenPokemon, Label nombre, Pane contenedor) {

        if (contador < 6) {

            contador++;
            ProgressBar nuevaBarra = new ProgressBar();
            nuevaBarra.setProgress(pokemon.getVitalidad() / 100);
            nuevaBarra.setPrefWidth(150);
            nuevaBarra.setStyle("-fx-background-color: lightgray; -fx-accent: #ff6600;");

            Pane barraPane = new Pane(nuevaBarra);
            barraPane.setPrefSize(150, 20);  // ajustar a tu necesidad
            VBox.setMargin(barraPane, new Insets(0, 0, 0, 50));
            nuevaBarra.setLayoutX(10);
            nuevaBarra.setLayoutY(-35);

            contenedor.getChildren().remove(viejaBarra);
            contenedor.getChildren().add(barraPane);


            actualizarEstamina(pokemon, estamina);
            Image nuevaImagen = new Image(Objects.requireNonNull(CombateGrafico.class.getResourceAsStream(pokemon.getFotoEspalda())));
            imagenPokemon.setImage(nuevaImagen);
            nombre.setText(pokemon.getNombre());

            vidaJugador = nuevaBarra;
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("¡Has ganado!");
            alert.setHeaderText("¡Felicidades! Has ganado el combate.");
            alert.setContentText("Has ganado: " + Combate.dineroGanado() + " PokeDollars.");
            PokemonCRUD.dineroGanado(Combate.dineroGanado());
            backMenu();
            alert.showAndWait();
        }
    }

    public static void backMenu() {
        Stage stage = (Stage) rendirseButton.getScene().getWindow();

        Menu menu = new Menu();
        try {
            menu.start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void abandonarCombate() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Has abandonado el combate.");
        alert.setHeaderText(null);
        alert.setContentText("Te has rendido y has perdido " + Combate.dineroPerdido() + " PokeDollars.");
        PokemonCRUD.dineroPerdido(Combate.dineroPerdido());
        alert.showAndWait();
    }


    public void start(Stage primaryStage) {
    }
}


