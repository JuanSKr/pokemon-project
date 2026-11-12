package pokemon.combate;

import pokemon.combate.Combate.Efecto;
import pokemon.combate.Combate.Foto;
import pokemon.combate.Combate.Paso;
import pokemon.combate.movimientos.Movimiento;
import pokemon.database.PokemonCRUD;
import pokemon.entrenador.Entrenador;
import pokemon.entrenador.Mochila;
import pokemon.funcionalidad.Funcion;
import pokemon.menus.Menu;
import pokemon.pokemon.Pokemon;
import pokemon.tienda.Objeto;
import pokemon.ui.App;
import pokemon.ui.Barra;
import pokemon.ui.Comunes;
import pokemon.ui.Dialogo;
import pokemon.ui.Musica;
import pokemon.ui.Pantalla;
import pokemon.ui.UI;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.ParallelTransition;
import javafx.animation.PauseTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.Glow;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.util.Duration;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.Map;

/** Pantalla del combate contra un entrenador rival. */
public class CombateGrafico {

    private static final Duration ANIM_BARRA = Duration.millis(450);

    private final Pantalla pantalla = new Pantalla();
    private final Combate combate;

    private final ImageView spriteJugador = new ImageView();
    private final ImageView spriteRival = new ImageView();
    private final PanelInfo infoJugador = new PanelInfo(true);
    private final PanelInfo infoRival = new PanelInfo(false);
    private final Label texto = new Label();
    private final Label indicador = new Label("▼");
    private final Label turno = UI.etiqueta("", "turno-combate");
    private final StackPane zonaAcciones = new StackPane();

    private final Deque<Paso> cola = new ArrayDeque<>();
    private Runnable alTerminarCola;
    private Timeline escritura;
    private PauseTransition espera;
    private boolean escribiendo;

    /** Abre el combate con el equipo titular, empezando por el Pokémon de la posición inicial. */
    public static void mostrar(List<Pokemon> equipo, int inicial) {
        int nivelMedio = (int) Math.round(equipo.stream().mapToInt(Pokemon::getNivel).average().orElse(1));
        Rival rival = Rival.generarRival(equipo.size(), nivelMedio);
        new CombateGrafico(new Combate(equipo, inicial, rival)).abrir();
    }

    private CombateGrafico(Combate combate) {
        this.combate = combate;
    }

    // =====================================================================================
    // Construcción de la pantalla
    // =====================================================================================

    private void abrir() {
        Musica.reproducir("/aud/TemaInicioPokemonVersion2.wav");
        pantalla.fondoImagen("/img/fondocombate.jpg").estilo("Combate.css");

        // Plataformas bajo los Pokémon
        pantalla.poner(plataforma(160, 36), 640, 266);
        pantalla.poner(plataforma(200, 42), 70, 410);

        spriteRival.setFitWidth(210);
        spriteRival.setFitHeight(175);
        spriteRival.setPreserveRatio(true);
        StackPane cajaRival = cajaSprite(spriteRival, 230, 190);
        pantalla.poner(cajaRival, 685, 104);

        spriteJugador.setFitWidth(290);
        spriteJugador.setFitHeight(235);
        spriteJugador.setPreserveRatio(true);
        StackPane cajaJugador = cajaSprite(spriteJugador, 300, 240);
        pantalla.poner(cajaJugador, 120, 208);

        // Al empezar se ve a los entrenadores; luego salen los Pokémon.
        spriteRival.setImage(UI.imagen(combate.getRival().getFoto()));
        spriteJugador.setImage(UI.imagen(Entrenador.getFoto()));

        infoRival.setVisible(false);
        infoJugador.setVisible(false);
        pantalla.poner(infoRival, 36, 34);
        pantalla.poner(infoJugador, 668, 312);

        turno.setVisible(false);
        pantalla.poner(turno, 470, 16);

        // Caja de texto
        texto.getStyleClass().add("texto-combate");
        texto.setWrapText(true);
        texto.setAlignment(Pos.TOP_LEFT);
        texto.setMaxSize(600, 140);
        indicador.getStyleClass().add("indicador");
        indicador.setVisible(false);
        StackPane.setAlignment(texto, Pos.TOP_LEFT);
        StackPane.setAlignment(indicador, Pos.BOTTOM_RIGHT);
        StackPane cajaTexto = new StackPane(texto, indicador);
        cajaTexto.getStyleClass().add("caja-texto");
        cajaTexto.setPrefSize(640, 168);
        cajaTexto.setOnMouseClicked(e -> avanzar());
        pantalla.poner(cajaTexto, 12, 470);

        zonaAcciones.getStyleClass().add("zona-acciones");
        zonaAcciones.setPrefSize(404, 168);
        pantalla.poner(zonaAcciones, 664, 470);

        pantalla.poner(Comunes.botonSilencio(), 1008, 16).setScaleX(0.8);

        pantalla.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER || e.getCode() == KeyCode.SPACE) {
                if (!cola.isEmpty() || escribiendo || espera != null) avanzar();
            }
        });
        pantalla.setAlSalir(() -> {
            if (escritura != null) escritura.stop();
            if (espera != null) espera.stop();
        });

        App.mostrar(pantalla);
        reproducir(combate.inicio(), this::menuPrincipal);
    }

    private static Node plataforma(double rx, double ry) {
        Ellipse e = new Ellipse(rx, ry, rx, ry);
        e.setFill(Color.rgb(20, 60, 20, 0.35));
        e.setStroke(Color.rgb(255, 255, 255, 0.45));
        e.setStrokeWidth(3);
        return e;
    }

    private static StackPane cajaSprite(ImageView iv, double w, double h) {
        StackPane sp = new StackPane(iv);
        sp.setPrefSize(w, h);
        sp.setAlignment(Pos.BOTTOM_CENTER);
        sp.setMouseTransparent(true);
        return sp;
    }

    // =====================================================================================
    // Menús de acciones
    // =====================================================================================

    private void menuPrincipal() {
        mostrarTexto("¿Qué debería hacer " + combate.jugador().getNombreMostrado() + "?");
        indicador.setVisible(false);
        turno.setText("TURNO " + combate.getTurno());
        turno.setVisible(true);

        Button atacar = UI.botonImagen("/img/botones/atacar.png", 186, 58);
        atacar.setOnAction(e -> menuMovimientos());
        UI.tooltip(atacar, "Elegir un movimiento");
        Button equipo = UI.botonImagen("/img/botones/equipo.png", 186, 42);
        equipo.setOnAction(e -> abrirEquipo(false));
        UI.tooltip(equipo, "Cambiar de Pokémon (gasta el turno)");
        Button descansar = UI.botonImagen("/img/botones/descansar.png", 186, 48);
        descansar.setOnAction(e -> ejecutar(combate.descansar()));
        UI.tooltip(descansar, "Recuperar estamina (el rival ataca)");
        Button mochila = UI.botonImagen("/img/botones/mochila.png", 186, 48);
        mochila.setOnAction(e -> abrirMochila());
        UI.tooltip(mochila, "Usar un objeto");
        Button rendirse = UI.botonImagen("/img/botones/rendirse.png", 186, 42);
        rendirse.setOnAction(e -> Dialogo.confirmar(pantalla, "¿Rendirte?",
                "Perderás " + Funcion.dinero(Entrenador.getDinero() / 3) + " y volverás al menú.",
                "RENDIRSE", "SEGUIR LUCHANDO", true, () -> ejecutar(combate.rendirse())));
        UI.tooltip(rendirse, "Abandonar el combate");

        GridPane g = new GridPane();
        g.setHgap(8);
        g.setVgap(6);
        g.setAlignment(Pos.CENTER);
        g.add(atacar, 0, 0);
        g.add(equipo, 1, 0);
        g.add(descansar, 0, 1);
        g.add(mochila, 1, 1);
        g.add(rendirse, 0, 2, 2, 1);
        GridPane.setHalignment(rendirse, HPos.CENTER);
        for (Node n : g.getChildren()) GridPane.setHalignment(n, HPos.CENTER);
        zonaAcciones.getChildren().setAll(g);
    }

    private void menuMovimientos() {
        Pokemon p = combate.jugador();
        GridPane g = new GridPane();
        g.setHgap(8);
        g.setVgap(8);
        g.setAlignment(Pos.CENTER);
        List<Movimiento> movimientos = p.getMovimientos();
        for (int i = 0; i < 4; i++) {
            Node celda;
            if (i < movimientos.size()) {
                Movimiento m = movimientos.get(i);
                celda = botonMovimiento(p, m, i);
            } else {
                Label vacio = UI.etiqueta("—", "movimiento-vacio");
                vacio.setPrefSize(190, 56);
                vacio.setAlignment(Pos.CENTER);
                celda = vacio;
            }
            g.add(celda, i % 2, i / 2);
        }
        Button volver = UI.botonClaro("◀ VOLVER");
        volver.getStyleClass().add("pequeno");
        volver.setCancelButton(true);
        volver.setOnAction(e -> menuPrincipal());
        g.add(volver, 0, 2, 2, 1);
        GridPane.setHalignment(volver, HPos.CENTER);
        zonaAcciones.getChildren().setAll(g);
        mostrarTexto("Estamina: " + (int) p.getEstaminaActual() + " / " + (int) p.getEstamina()
                + ". Si no te llega para un movimiento, descansa.");
    }

    private Node botonMovimiento(Pokemon p, Movimiento m, int indice) {
        ImageView fondo = new ImageView(UI.imagen("/img/botones/movimiento.png"));
        fondo.setFitWidth(190);
        fondo.setFitHeight(56);
        Label nombre = UI.etiqueta(m.getNombreBonito(), "nombre-movimiento");
        Label tipo = UI.chipTipo(m.getTipo());
        tipo.setStyle(tipo.getStyle() + "-fx-font-size: 10px; -fx-padding: 0 6 0 6;");
        Label coste = UI.etiqueta(m.costeEstamina() + " EST", "datos-movimiento");
        HBox datos = new HBox(6, tipo, coste);
        datos.setAlignment(Pos.CENTER);
        VBox textos = new VBox(1, nombre, datos);
        textos.setAlignment(Pos.CENTER);
        StackPane contenido = new StackPane(fondo, textos);
        Button b = new Button();
        b.getStyleClass().add("boton-imagen");
        b.setGraphic(contenido);
        boolean usable = p.puedeUsar(m);
        b.setDisable(!usable);
        b.setOnAction(e -> ejecutar(combate.usarMovimiento(indice)));
        String tip = m.getNombreBonito() + " (" + (m.getTipo() == null ? "?" : m.getTipo().getNombre()) + ")\n"
                + m.descripcion() + "\nCoste: " + m.costeEstamina() + " de estamina";
        if (!usable) tip += "\n¡No tienes estamina suficiente!";
        // Los botones desactivados no muestran tooltip: se pone en el contenedor.
        StackPane envoltorio = new StackPane(b);
        UI.tooltip(envoltorio, tip);
        return envoltorio;
    }

    /** Elegir un Pokémon del equipo. Si es obligado (porque el activo se ha debilitado) no se puede cancelar. */
    private void abrirEquipo(boolean obligado) {
        TilePane tarjetas = new TilePane(10, 10);
        tarjetas.setPrefColumns(3);
        tarjetas.setAlignment(Pos.CENTER);
        Dialogo[] dialogo = new Dialogo[1];
        List<Pokemon> equipo = combate.getEquipoJugador();
        for (int i = 0; i < equipo.size(); i++) {
            Pokemon p = equipo.get(i);
            VBox tarjeta = tarjetaCombate(p, i == combate.getActivoJugador());
            boolean elegible = !p.estaDebilitado() && i != combate.getActivoJugador();
            if (elegible) {
                final int indice = i;
                tarjeta.setOnMouseClicked(e -> {
                    dialogo[0].cerrar();
                    ejecutar(combate.cambiar(indice));
                });
            } else {
                tarjeta.setDisable(true);
            }
            tarjetas.getChildren().add(tarjeta);
        }
        Label titulo = UI.etiqueta(obligado ? "¿Quién sale ahora?" : "Cambiar de Pokémon", "dialogo-titulo");
        VBox contenido = new VBox(14, titulo, tarjetas);
        contenido.setAlignment(Pos.CENTER);
        if (!obligado) {
            Button cancelar = UI.botonClaro("CANCELAR");
            cancelar.setCancelButton(true);
            cancelar.setOnAction(e -> dialogo[0].cerrar());
            contenido.getChildren().add(cancelar);
        }
        dialogo[0] = Dialogo.personalizado(pantalla, contenido, 620);
    }

    private VBox tarjetaCombate(Pokemon p, boolean enCombate) {
        // Se crea con 108 de alto para que el sprite se calcule pequeño y quepan la barra y el texto.
        VBox t = Comunes.tarjetaPokemon(p, 170, 108);
        Barra vida = new Barra(Barra.Modo.VIDA, 140, 10);
        vida.setValor(p.getVida() / p.getVitalidad());
        Label estado = UI.etiqueta(p.estaDebilitado() ? "DEBILITADO" : enCombate ? "EN COMBATE"
                : (int) Math.ceil(p.getVida()) + " / " + (int) p.getVitalidad() + " PS", "nivel-tarjeta");
        t.getChildren().addAll(vida, estado);
        t.setPrefHeight(150);
        t.setMinHeight(150);
        t.setMaxHeight(150);
        if (p.estaDebilitado()) t.getStyleClass().add("debilitado");
        if (enCombate) t.getStyleClass().add("seleccionada");
        return t;
    }

    private void abrirMochila() {
        Mochila mochila = PokemonCRUD.getMochila();
        VBox lista = new VBox(8);
        lista.setAlignment(Pos.CENTER);
        Dialogo[] dialogo = new Dialogo[1];
        boolean hayAlgo = false;
        for (Map.Entry<Objeto, Integer> e : mochila.getObjetos().entrySet()) {
            Objeto o = e.getKey();
            if (!o.usableEnCombate()) continue;
            hayAlgo = true;
            Label nombre = UI.etiqueta(o.getNombre(), "titulo-panel");
            nombre.setStyle("-fx-font-size: 18px;");
            Label desc = UI.etiqueta(o.getDescripcion(), "texto-suave");
            desc.setWrapText(true);
            VBox textos = new VBox(1, nombre, desc);
            textos.setPrefWidth(330);
            Label cantidad = UI.etiqueta("x" + e.getValue(), "titulo-panel");
            HBox fila = new HBox(12, UI.iconoObjeto(o, 40), textos, UI.espacio(), cantidad);
            fila.setAlignment(Pos.CENTER_LEFT);
            fila.getStyleClass().add("tarjeta");
            fila.setPadding(new Insets(8, 14, 8, 10));
            fila.setOnMouseClicked(ev -> {
                dialogo[0].cerrar();
                usarObjeto(o);
            });
            lista.getChildren().add(fila);
        }
        Label titulo = UI.etiqueta("Mochila", "dialogo-titulo");
        VBox contenido = new VBox(14, titulo);
        contenido.setAlignment(Pos.CENTER);
        if (hayAlgo) {
            contenido.getChildren().add(lista);
        } else {
            Label vacia = UI.etiqueta("No llevas objetos que se puedan usar en combate.\nCompra Pociones, Éteres o Revivir en la Tienda.",
                    "dialogo-texto");
            vacia.setWrapText(true);
            contenido.getChildren().add(vacia);
        }
        if (mochila.totalBalls() > 0) {
            Label nota = UI.etiqueta("Las Poké Balls no sirven contra los Pokémon de otro entrenador.", "texto-suave");
            contenido.getChildren().add(nota);
        }
        Button cerrar = UI.botonClaro("CERRAR");
        cerrar.setCancelButton(true);
        cerrar.setOnAction(e -> dialogo[0].cerrar());
        contenido.getChildren().add(cerrar);
        dialogo[0] = Dialogo.personalizado(pantalla, contenido, 560);
    }

    private void usarObjeto(Objeto o) {
        Pokemon activo = combate.jugador();
        switch (o.getCategoria()) {
            case CURACION -> {
                if (activo.getVida() >= activo.getVitalidad()) {
                    Dialogo.aviso(pantalla, activo.getNombreMostrado() + " ya tiene la vida al máximo.");
                } else {
                    ejecutar(combate.usarObjeto(o, activo));
                }
            }
            case ESTAMINA -> {
                if (activo.getEstaminaActual() >= activo.getEstamina()) {
                    Dialogo.aviso(pantalla, activo.getNombreMostrado() + " ya tiene la estamina al máximo.");
                } else {
                    ejecutar(combate.usarObjeto(o, activo));
                }
            }
            case REVIVIR -> elegirParaRevivir(o);
            default -> {
            }
        }
    }

    private void elegirParaRevivir(Objeto o) {
        List<Pokemon> debilitados = combate.getEquipoJugador().stream().filter(Pokemon::estaDebilitado).toList();
        if (debilitados.isEmpty()) {
            Dialogo.aviso(pantalla, "Ninguno de tus Pokémon está debilitado.");
            return;
        }
        TilePane tarjetas = new TilePane(10, 10);
        tarjetas.setPrefColumns(3);
        tarjetas.setAlignment(Pos.CENTER);
        Dialogo[] dialogo = new Dialogo[1];
        for (Pokemon p : debilitados) {
            VBox t = Comunes.tarjetaPokemon(p, 150, 120);
            t.setOnMouseClicked(e -> {
                dialogo[0].cerrar();
                ejecutar(combate.usarObjeto(o, p));
            });
            tarjetas.getChildren().add(t);
        }
        Button cancelar = UI.botonClaro("CANCELAR");
        cancelar.setCancelButton(true);
        cancelar.setOnAction(e -> dialogo[0].cerrar());
        VBox contenido = new VBox(14, UI.etiqueta("¿A quién quieres revivir?", "dialogo-titulo"), tarjetas, cancelar);
        contenido.setAlignment(Pos.CENTER);
        dialogo[0] = Dialogo.personalizado(pantalla, contenido, 560);
    }

    // =====================================================================================
    // Ejecución de turnos
    // =====================================================================================

    private void ejecutar(List<Paso> pasos) {
        zonaAcciones.getChildren().clear();
        reproducir(pasos, this::despuesDelTurno);
    }

    private void despuesDelTurno() {
        switch (combate.getResultado()) {
            case VICTORIA -> {
                Musica.reproducirUnaVez("/aud/PokemonCaptura.wav");
                Dialogo.info(pantalla, "¡Has ganado el combate!",
                        "Has derrotado a " + combate.getRival().getNombre() + " y has ganado "
                                + Funcion.dinero(combate.getDineroGanado()) + ".\nTu equipo ha ganado experiencia.",
                        Menu::mostrar);
            }
            case DERROTA -> {
                Musica.reproducirUnaVez("/aud/PokemonNoCapturado.wav");
                // Sin ternarios dentro de la llamada: el compilador de VS Code (ECJ) los traduce mal aquí.
                String texto = "Todos tus Pokémon se han debilitado.";
                if (combate.getDineroPerdido() > 0) {
                    texto += "\nHas perdido " + Funcion.dinero(combate.getDineroPerdido()) + ".";
                }
                texto += "\n¡Entrena a tu equipo y vuelve a intentarlo!";
                Dialogo.info(pantalla, "Has perdido el combate...", texto, Menu::mostrar);
            }
            case RENDICION -> {
                String texto = null;
                if (combate.getDineroPerdido() > 0) {
                    texto = "Has perdido " + Funcion.dinero(combate.getDineroPerdido()) + ".";
                }
                Dialogo.info(pantalla, "Te has rendido", texto, Menu::mostrar);
            }
            default -> {
                if (combate.necesitaCambio()) {
                    mostrarTexto("¿Qué Pokémon vas a sacar?");
                    abrirEquipo(true);
                } else {
                    menuPrincipal();
                }
            }
        }
    }

    /** Muestra una lista de pasos uno detrás de otro y al final ejecuta alTerminar. */
    private void reproducir(List<Paso> pasos, Runnable alTerminar) {
        cola.clear();
        cola.addAll(pasos);
        alTerminarCola = alTerminar;
        siguientePaso();
    }

    private void siguientePaso() {
        if (espera != null) {
            espera.stop();
            espera = null;
        }
        Paso paso = cola.poll();
        if (paso == null) {
            indicador.setVisible(false);
            Runnable r = alTerminarCola;
            alTerminarCola = null;
            if (r != null) r.run();
            return;
        }
        animar(paso.efecto(), paso);
        infoJugador.actualizar(paso.jugador());
        infoRival.actualizar(paso.rival());
        if (paso.texto() == null) {
            esperar(Duration.millis(550));
        } else {
            escribir(paso.texto());
        }
    }

    /** Avanza: si el texto se está escribiendo lo completa; si ya está, pasa al siguiente mensaje. */
    private void avanzar() {
        if (escribiendo) {
            escritura.stop();
            escribiendo = false;
            texto.setText((String) texto.getUserData());
            esperar(Duration.millis(1100));
        } else if (espera != null) {
            siguientePaso();
        }
    }

    private void escribir(String mensaje) {
        if (escritura != null) escritura.stop();
        texto.setUserData(mensaje);
        texto.setText("");
        indicador.setVisible(false);
        escribiendo = true;
        int[] i = {0};
        escritura = new Timeline(new KeyFrame(Duration.millis(16), e -> {
            i[0] = Math.min(mensaje.length(), i[0] + 1);
            texto.setText(mensaje.substring(0, i[0]));
        }));
        escritura.setCycleCount(mensaje.length());
        escritura.setOnFinished(e -> {
            escribiendo = false;
            esperar(Duration.millis(1100));
        });
        escritura.play();
    }

    private void esperar(Duration d) {
        indicador.setVisible(!cola.isEmpty() || alTerminarCola != null);
        espera = new PauseTransition(d);
        espera.setOnFinished(e -> siguientePaso());
        espera.play();
    }

    /** Texto fijo (sin escribir letra a letra), para los menús. */
    private void mostrarTexto(String mensaje) {
        if (escritura != null) escritura.stop();
        escribiendo = false;
        texto.setText(mensaje);
    }

    // =====================================================================================
    // Animaciones
    // =====================================================================================

    private void animar(Efecto efecto, Paso paso) {
        switch (efecto) {
            case ATACA_JUGADOR -> embestir(spriteJugador, 45, -25);
            case ATACA_RIVAL -> embestir(spriteRival, -45, 25);
            case GOLPE_JUGADOR -> golpear(spriteJugador);
            case GOLPE_RIVAL -> golpear(spriteRival);
            case ENTRA_JUGADOR -> entrar(spriteJugador, UI.spriteEspalda(paso.jugador().spriteEspalda(), paso.jugador().sprite()), infoJugador);
            case ENTRA_RIVAL -> entrar(spriteRival, UI.imagen(paso.rival().sprite()), infoRival);
            case SALE_JUGADOR -> salir(spriteJugador);
            case SALE_RIVAL -> salir(spriteRival);
            case DEBILITA_JUGADOR -> caer(spriteJugador);
            case DEBILITA_RIVAL -> caer(spriteRival);
            case MEJORA_JUGADOR -> brillar(spriteJugador);
            case MEJORA_RIVAL -> brillar(spriteRival);
            case SUBE_NIVEL -> brillar(infoJugador);
            default -> {
            }
        }
    }

    private void embestir(Node n, double dx, double dy) {
        TranslateTransition t = new TranslateTransition(Duration.millis(110), n);
        t.setByX(dx);
        t.setByY(dy);
        t.setAutoReverse(true);
        t.setCycleCount(2);
        t.play();
    }

    private void golpear(Node n) {
        Timeline parpadeo = new Timeline(
                new KeyFrame(Duration.ZERO, e -> n.setOpacity(0.15)),
                new KeyFrame(Duration.millis(80), e -> n.setOpacity(1)),
                new KeyFrame(Duration.millis(160), e -> n.setOpacity(0.15)),
                new KeyFrame(Duration.millis(240), e -> n.setOpacity(1)));
        TranslateTransition temblor = new TranslateTransition(Duration.millis(50), n);
        temblor.setByX(8);
        temblor.setAutoReverse(true);
        temblor.setCycleCount(6);
        parpadeo.play();
        temblor.play();
    }

    private void entrar(ImageView iv, javafx.scene.image.Image imagen, PanelInfo info) {
        iv.setImage(imagen);
        iv.setTranslateX(0);
        iv.setTranslateY(0);
        iv.setOpacity(1);
        iv.setScaleX(0.1);
        iv.setScaleY(0.1);
        ScaleTransition s = new ScaleTransition(Duration.millis(320), iv);
        s.setToX(1);
        s.setToY(1);
        s.play();
        if (!info.isVisible()) {
            info.setVisible(true);
            FadeTransition f = new FadeTransition(Duration.millis(300), info);
            f.setFromValue(0);
            f.setToValue(1);
            f.play();
        }
    }

    private void salir(ImageView iv) {
        ScaleTransition s = new ScaleTransition(Duration.millis(260), iv);
        s.setToX(0.05);
        s.setToY(0.05);
        s.play();
    }

    private void caer(ImageView iv) {
        TranslateTransition t = new TranslateTransition(Duration.millis(420), iv);
        t.setByY(70);
        FadeTransition f = new FadeTransition(Duration.millis(420), iv);
        f.setToValue(0);
        new ParallelTransition(t, f).play();
    }

    private void brillar(Node n) {
        Glow glow = new Glow(0);
        n.setEffect(glow);
        Timeline t = new Timeline(
                new KeyFrame(Duration.ZERO, new javafx.animation.KeyValue(glow.levelProperty(), 0)),
                new KeyFrame(Duration.millis(220), new javafx.animation.KeyValue(glow.levelProperty(), 0.9)),
                new KeyFrame(Duration.millis(440), new javafx.animation.KeyValue(glow.levelProperty(), 0)));
        t.setCycleCount(2);
        t.setOnFinished(e -> n.setEffect(null));
        new SequentialTransition(t).play();
    }

    // =====================================================================================
    // Panel con nombre, nivel, vida y estamina
    // =====================================================================================

    private static class PanelInfo extends StackPane {
        private final boolean jugador;
        private final Label nombre = UI.etiqueta("", "info-nombre");
        private final Label nivel = UI.etiqueta("", "info-nivel");
        private final Label estado = UI.chipEstado(null);
        private final Barra vida;
        private final Barra estamina;
        private final Label numerosVida = UI.etiqueta("", "info-numeros");
        private final Label numerosEstamina = UI.etiqueta("", "info-numeros-est");
        private final HBox equipo = new HBox(4);
        private String ultimoNombre;

        PanelInfo(boolean jugador) {
            this.jugador = jugador;
            double ancho = jugador ? 390 : 370;
            double alto = jugador ? 132 : 118;
            ImageView fondo = new ImageView(UI.imagen(jugador ? "/img/stats.png" : "/img/statsrival.png"));
            fondo.setFitWidth(ancho);
            fondo.setFitHeight(alto);

            vida = new Barra(Barra.Modo.VIDA, jugador ? 186 : 210, 15);
            estamina = new Barra(Barra.Modo.ESTAMINA, 150, 9);

            HBox fila1 = new HBox(8, nombre, estado, UI.espacio(), nivel);
            fila1.setAlignment(Pos.CENTER_LEFT);
            Label ps = UI.etiqueta("PS", "info-etiqueta");
            HBox fila2 = new HBox(8, ps, vida, numerosVida);
            fila2.setAlignment(Pos.CENTER_LEFT);
            Label est = UI.etiqueta("EST", "info-etiqueta");
            HBox fila3 = new HBox(8, est, estamina, numerosEstamina);
            fila3.setAlignment(Pos.CENTER_LEFT);
            equipo.setAlignment(Pos.CENTER_LEFT);
            VBox filas = new VBox(5, fila1, fila2, fila3, equipo);
            filas.setPadding(jugador ? new Insets(12, 52, 10, 22) : new Insets(10, 70, 8, 20));
            numerosVida.setVisible(jugador);
            numerosVida.setManaged(jugador);

            getChildren().addAll(fondo, filas);
            setPrefSize(ancho, alto);
            setMaxSize(ancho, alto);
        }

        void actualizar(Foto f) {
            boolean cambio = !f.nombre().equals(ultimoNombre);
            ultimoNombre = f.nombre();
            nombre.setText(f.nombre());
            nivel.setText("Nv. " + f.nivel());
            estado.setText(f.estado() == null ? "" : f.estado().getAbreviatura());
            estado.setVisible(f.estado() != null);
            if (f.estado() != null) estado.setStyle("-fx-background-color: " + f.estado().getColor() + ";");
            double v = f.vida() / f.vidaMax();
            double e = f.estamina() / f.estaminaMax();
            if (cambio) {
                vida.setValor(v);
                estamina.setValor(e);
            } else {
                vida.animarA(v, ANIM_BARRA);
                estamina.animarA(e, ANIM_BARRA);
            }
            numerosVida.setText((int) Math.ceil(f.vida()) + " / " + (int) f.vidaMax());
            numerosEstamina.setText(jugador ? (int) f.estamina() + " / " + (int) f.estaminaMax() : "");
            equipo.getChildren().clear();
            for (boolean vivo : f.vivos()) {
                Circle c = new Circle(6);
                c.setFill(vivo ? Color.web("#E3350D") : Color.web("#777"));
                c.setStroke(Color.web("#222"));
                c.setStrokeWidth(1.5);
                equipo.getChildren().add(c);
            }
        }
    }
}
