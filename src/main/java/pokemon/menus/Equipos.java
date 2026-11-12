package pokemon.menus;

import pokemon.combate.movimientos.Movimiento;
import pokemon.database.PokemonCRUD;
import pokemon.entrenador.Entrenador;
import pokemon.pokemon.Pokemon;
import pokemon.ui.App;
import pokemon.ui.Barra;
import pokemon.ui.Comunes;
import pokemon.ui.Dialogo;
import pokemon.ui.Musica;
import pokemon.ui.Pantalla;
import pokemon.ui.UI;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.List;

/**
 * Gestión de los Pokémon del entrenador: equipo titular (el que combate), equipo 2 y caja.
 * Se pueden arrastrar entre zonas, ver su ficha, cambiarles el mote o liberarlos.
 */
public class Equipos {

    private static final double ANCHO_TARJETA = 98, ALTO_TARJETA = 100;
    private static final String[] NOMBRES_ZONA = {"", "Equipo titular", "Equipo 2", "Caja"};

    private final Pantalla pantalla = new Pantalla();
    private final HBox titular = new HBox(8);
    private final HBox equipo2 = new HBox(8);
    private final TilePane caja = new TilePane(8, 8);
    private final Label tituloTitular = UI.etiqueta("", "titulo-zona");
    private final Label tituloEquipo2 = UI.etiqueta("", "titulo-zona");
    private final Label tituloCaja = UI.etiqueta("", "titulo-zona");
    private final VBox ficha = new VBox(7);
    private Pokemon seleccionado;

    public static void mostrar() {
        new Equipos().abrir();
    }

    private void abrir() {
        Musica.reproducir("/aud/MenuEntrenador.wav");
        pantalla.fondoVideo("/vid/entrenador.mp4").estilo("Equipos.css");
        javafx.scene.layout.Region oscuro = new javafx.scene.layout.Region();
        oscuro.setStyle("-fx-background-color: rgba(10, 20, 45, 0.45);");
        pantalla.capaFondo(oscuro);

        Text titulo = UI.titulo("EQUIPOS", 58);
        pantalla.poner(titulo, 100, 12);
        Label ayuda = UI.etiqueta("Arrastra tus Pokémon para moverlos. Solo el equipo titular combate.", "texto-claro");
        pantalla.poner(ayuda, 420, 40);

        caja.setPrefColumns(6);
        caja.setPrefTileWidth(ANCHO_TARJETA);
        caja.setPrefTileHeight(ALTO_TARJETA);
        caja.setPadding(new Insets(2));
        ScrollPane scrollCaja = new ScrollPane(caja);
        scrollCaja.setFitToWidth(true);
        scrollCaja.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollCaja.setPrefHeight(176);

        VBox izquierda = new VBox(6,
                tituloTitular, zona(titular, 1),
                tituloEquipo2, zona(equipo2, 2),
                tituloCaja, zona(scrollCaja, 3));
        izquierda.getStyleClass().add("panel");
        izquierda.setPrefSize(694, 548);
        pantalla.poner(izquierda, 20, 88);

        ficha.getStyleClass().add("panel");
        ficha.setPrefSize(342, 548);
        ficha.setAlignment(Pos.TOP_LEFT);
        pantalla.poner(ficha, 720, 88);

        pantalla.poner(Comunes.botonAtras(MenuEntrenador::mostrar), 18, 14);
        pantalla.poner(Comunes.botonSilencio(), 998, 14);

        refrescar();
        App.mostrar(pantalla);
    }

    /** Envuelve una zona para que acepte Pokémon arrastrados. */
    private Node zona(Node contenido, int numero) {
        VBox z = new VBox(contenido);
        z.getStyleClass().add("zona");
        z.setPadding(new Insets(6));
        z.setOnDragOver(e -> {
            if (e.getDragboard().hasString()) e.acceptTransferModes(TransferMode.MOVE);
            e.consume();
        });
        z.setOnDragEntered(e -> {
            if (e.getDragboard().hasString()) z.getStyleClass().add("destino");
        });
        z.setOnDragExited(e -> z.getStyleClass().remove("destino"));
        z.setOnDragDropped(e -> {
            Dragboard db = e.getDragboard();
            boolean hecho = false;
            if (db.hasString()) {
                hecho = mover(Integer.parseInt(db.getString()), numero);
            }
            e.setDropCompleted(hecho);
            e.consume();
        });
        return z;
    }

    // =====================================================================================
    // Contenido
    // =====================================================================================

    private void refrescar() {
        PokemonCRUD.recargarEquipos();
        llenar(titular, Entrenador.equipo1, Entrenador.TAMANO_EQUIPO);
        llenar(equipo2, Entrenador.equipo2, Entrenador.TAMANO_EQUIPO);
        caja.getChildren().clear();
        for (Pokemon p : Entrenador.caja) caja.getChildren().add(tarjeta(p));
        if (Entrenador.caja.isEmpty()) {
            Label vacia = UI.etiqueta("La caja está vacía.", "texto-suave");
            vacia.setPadding(new Insets(10));
            caja.getChildren().add(vacia);
        }
        tituloTitular.setText("EQUIPO TITULAR  " + Entrenador.equipo1.size() + "/" + Entrenador.TAMANO_EQUIPO);
        tituloEquipo2.setText("EQUIPO 2  " + Entrenador.equipo2.size() + "/" + Entrenador.TAMANO_EQUIPO);
        tituloCaja.setText("CAJA  " + Entrenador.caja.size() + "/" + Entrenador.TAMANO_CAJA);

        // Mantener la selección si sigue existiendo
        Pokemon nuevo = null;
        if (seleccionado != null) {
            for (List<Pokemon> l : List.of(Entrenador.equipo1, Entrenador.equipo2, Entrenador.caja)) {
                for (Pokemon p : l) if (p.getId() == seleccionado.getId()) nuevo = p;
            }
        }
        if (nuevo == null && !Entrenador.equipo1.isEmpty()) nuevo = Entrenador.equipo1.get(0);
        if (nuevo == null && !Entrenador.equipo2.isEmpty()) nuevo = Entrenador.equipo2.get(0);
        if (nuevo == null && !Entrenador.caja.isEmpty()) nuevo = Entrenador.caja.get(0);
        seleccionar(nuevo);
    }

    private void llenar(HBox fila, List<Pokemon> lista, int huecos) {
        fila.getChildren().clear();
        for (int i = 0; i < huecos; i++) {
            fila.getChildren().add(i < lista.size() ? tarjeta(lista.get(i)) : Comunes.tarjetaPokemon(null, ANCHO_TARJETA, ALTO_TARJETA));
        }
    }

    private VBox tarjeta(Pokemon p) {
        VBox t = Comunes.tarjetaPokemon(p, ANCHO_TARJETA, ALTO_TARJETA);
        t.setUserData(p.getId());
        if (seleccionado != null && seleccionado.getId() == p.getId()) t.getStyleClass().add("seleccionada");
        t.setOnMouseClicked(e -> seleccionar(p));
        t.setOnDragDetected(e -> {
            Dragboard db = t.startDragAndDrop(TransferMode.MOVE);
            db.setDragView(t.snapshot(null, null));
            ClipboardContent c = new ClipboardContent();
            c.putString(String.valueOf(p.getId()));
            db.setContent(c);
            e.consume();
        });
        return t;
    }

    private void seleccionar(Pokemon p) {
        seleccionado = p;
        for (Pane zona : List.of(titular, equipo2, caja)) {
            for (Node n : zona.getChildren()) {
                n.getStyleClass().remove("seleccionada");
                if (p != null && Integer.valueOf(p.getId()).equals(n.getUserData())) n.getStyleClass().add("seleccionada");
            }
        }
        mostrarFicha(p);
    }

    private void mostrarFicha(Pokemon p) {
        ficha.getChildren().clear();
        if (p == null) {
            Label vacio = UI.etiqueta("Todavía no tienes Pokémon.\n¡Ve a Capturar para conseguir tu primero!", "texto");
            vacio.setWrapText(true);
            Button capturar = UI.boton("IR A CAPTURAR");
            capturar.setOnAction(e -> MenuExplorador.mostrar());
            ficha.getChildren().addAll(vacio, capturar);
            return;
        }

        ImageView sprite = UI.vistaImagen(p.getFoto(), 96, 90);
        Label nombre = UI.etiqueta(p.getNombreMostrado(), "nombre-ficha");
        nombre.setMaxWidth(196);
        Label especie = UI.etiqueta(p.getMote() != null && !p.getMote().isBlank() ? p.getNombre() : "Sin mote", "texto-suave");
        HBox tipos = UI.chipsTipos(p.getTipo1(), p.getTipo2());
        Label nivel = UI.etiqueta("Nv. " + p.getNivel(), "nivel-ficha");
        VBox cabecera = new VBox(3, nombre, especie, tipos, nivel);
        HBox arriba = new HBox(10, sprite, cabecera);
        arriba.setAlignment(Pos.CENTER_LEFT);

        Barra xp = new Barra(Barra.Modo.FIJO, 150, 10, "#5DADFF");
        xp.setValor((double) p.getXp() / p.xpParaSubir());
        Label xpTexto = UI.etiqueta(p.getXp() + "/" + p.xpParaSubir() + " XP", "texto-suave");
        HBox filaNivel = new HBox(8, UI.etiqueta("XP", "stat-ficha"), xp, xpTexto);
        filaNivel.setAlignment(Pos.CENTER_LEFT);

        GridPane stats = new GridPane();
        stats.setHgap(8);
        stats.setVgap(3);
        String[] nombres = {"Vida", "Ataque", "Defensa", "At. Esp.", "Def. Esp.", "Velocidad", "Estamina"};
        double[] valores = {p.getVitalidad(), p.getAtaque(), p.getDefensa(), p.getAtaqueEspecial(),
                p.getDefensaEspecial(), p.getVelocidad(), p.getEstamina()};
        double[] maximos = {200, 220, 220, 220, 220, 220, 360};
        for (int i = 0; i < nombres.length; i++) {
            Barra b = new Barra(Barra.Modo.FIJO, 150, 9, i == 0 ? "#2DB84B" : "#F2B705");
            b.setValor(valores[i] / maximos[i]);
            stats.addRow(i, UI.etiqueta(nombres[i], "stat-ficha"), b, UI.etiqueta(String.valueOf((int) valores[i]), "valor-ficha"));
        }

        VBox movimientos = new VBox(3);
        for (Movimiento m : p.getMovimientos()) {
            Label mn = UI.etiqueta(m.getNombreBonito(), "movimiento-ficha");
            HBox fila = new HBox(6, UI.chipTipo(m.getTipo()), mn);
            fila.setAlignment(Pos.CENTER_LEFT);
            UI.tooltip(fila, m.descripcion() + " · Coste: " + m.costeEstamina() + " de estamina");
            movimientos.getChildren().add(fila);
        }
        if (p.getMovimientos().size() < 4) {
            movimientos.getChildren().add(UI.etiqueta("Aprenderá más movimientos en su próximo combate.", "texto-suave"));
        }

        Button mote = UI.botonClaro("MOTE");
        mote.getStyleClass().add("pequeno");
        mote.setOnAction(e -> Dialogo.pedirTexto(pantalla, "Mote de " + p.getNombre(),
                "Escribe un mote (o déjalo vacío para quitarlo).", p.getMote(), 15, "GUARDAR", "CANCELAR",
                nuevo -> {
                    PokemonCRUD.actualizarMote(p.getId(), nuevo);
                    refrescar();
                }, null));
        Button liberar = UI.botonRojo("LIBERAR");
        liberar.getStyleClass().add("pequeno");
        liberar.setOnAction(e -> liberar(p));

        HBox mover = new HBox(6);
        mover.setAlignment(Pos.CENTER);
        for (int z = 1; z <= 3; z++) {
            if (z == p.getEquipo()) continue;
            final int destino = z;
            Button b = UI.boton(z == 3 ? "A LA CAJA" : z == 1 ? "A TITULAR" : "A EQUIPO 2");
            b.getStyleClass().add("pequeno");
            b.setOnAction(e -> mover(p.getId(), destino));
            mover.getChildren().add(b);
        }
        HBox otros = new HBox(8, mote, liberar);
        otros.setAlignment(Pos.CENTER);

        ficha.getChildren().addAll(arriba, filaNivel, stats, movimientos, UI.espacio(), mover, otros);
    }

    // =====================================================================================
    // Acciones
    // =====================================================================================

    private boolean mover(int id, int destino) {
        Pokemon p = PokemonCRUD.getPokemon(id);
        if (p == null || p.getEquipo() == destino) return false;
        int ocupados = switch (destino) {
            case 1 -> Entrenador.equipo1.size();
            case 2 -> Entrenador.equipo2.size();
            default -> Entrenador.caja.size();
        };
        int maximo = destino == 3 ? Entrenador.TAMANO_CAJA : Entrenador.TAMANO_EQUIPO;
        if (ocupados >= maximo) {
            Dialogo.aviso(pantalla, NOMBRES_ZONA[destino] + " está lleno. Saca a alguno primero.");
            return false;
        }
        PokemonCRUD.moverPokemon(id, destino);
        seleccionado = p;
        refrescar();
        Dialogo.aviso(pantalla, p.getNombreMostrado() + " → " + NOMBRES_ZONA[destino]);
        return true;
    }

    private void liberar(Pokemon p) {
        int total = Entrenador.equipo1.size() + Entrenador.equipo2.size() + Entrenador.caja.size();
        if (total <= 1) {
            Dialogo.info(pantalla, "No puedes liberarlo", p.getNombreMostrado() + " es tu único Pokémon.");
            return;
        }
        Dialogo.confirmar(pantalla, "¿Liberar a " + p.getNombreMostrado() + "?",
                "Volverá a la naturaleza y no podrás recuperarlo.", "LIBERAR", "CANCELAR", true, () -> {
                    PokemonCRUD.liberarPokemon(p.getId());
                    seleccionado = null;
                    refrescar();
                    Dialogo.aviso(pantalla, "¡Adiós, " + p.getNombreMostrado() + "!");
                });
    }
}
