package pokemon.tienda;

import pokemon.database.PokemonCRUD;
import pokemon.entrenador.Entrenador;
import pokemon.entrenador.Mochila;
import pokemon.funcionalidad.Funcion;
import pokemon.menus.Menu;
import pokemon.ui.App;
import pokemon.ui.Comunes;
import pokemon.ui.Dialogo;
import pokemon.ui.Musica;
import pokemon.ui.Pantalla;
import pokemon.ui.UI;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.Map;

/** Tienda: comprar Poké Balls y objetos para el combate. */
public class MenuTienda {

    private final Pantalla pantalla = new Pantalla();
    private final Comunes.Dinero dinero = new Comunes.Dinero();
    private final VBox listaMochila = new VBox(8);

    public static void mostrar() {
        new MenuTienda().abrir();
    }

    private void abrir() {
        Musica.reproducir("/aud/Tienda.wav");
        pantalla.fondoVideo("/vid/avatar.mp4").estilo("Tienda.css");
        javafx.scene.layout.Region oscuro = new javafx.scene.layout.Region();
        oscuro.setStyle("-fx-background-color: linear-gradient(to bottom, rgba(10,20,45,0.55), rgba(10,20,45,0.2));");
        pantalla.capaFondo(oscuro);

        Text titulo = UI.titulo("TIENDA", 58);
        pantalla.poner(titulo, 100, 12);
        pantalla.poner(dinero, 800, 30);

        // Catálogo
        TilePane catalogo = new TilePane(12, 12);
        catalogo.setPrefColumns(2);
        catalogo.setPrefTileWidth(292);
        catalogo.setPadding(new Insets(4));
        for (Objeto o : PokemonCRUD.getArticulos()) {
            catalogo.getChildren().add(tarjeta(o));
        }
        ScrollPane scroll = new ScrollPane(catalogo);
        scroll.setFitToWidth(true);
        scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scroll.setPrefSize(644, 470);
        Label tituloCatalogo = UI.etiqueta("Artículos", "titulo-panel");
        VBox panelCatalogo = new VBox(10, tituloCatalogo, scroll);
        panelCatalogo.getStyleClass().add("panel");
        panelCatalogo.setPrefSize(680, 540);
        pantalla.poner(panelCatalogo, 24, 92);

        // Mochila
        Label tituloMochila = UI.etiqueta("Tu mochila", "titulo-panel");
        ScrollPane scrollMochila = new ScrollPane(listaMochila);
        scrollMochila.setFitToWidth(true);
        scrollMochila.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollMochila.setPrefHeight(470);
        VBox panelMochila = new VBox(10, tituloMochila, scrollMochila);
        panelMochila.getStyleClass().add("panel");
        panelMochila.setPrefSize(344, 540);
        pantalla.poner(panelMochila, 716, 92);
        refrescarMochila();

        pantalla.poner(Comunes.botonAtras(Menu::mostrar), 18, 14);
        pantalla.poner(Comunes.botonSilencio(), 998, 14);

        App.mostrar(pantalla);
    }

    private VBox tarjeta(Objeto o) {
        Label nombre = UI.etiqueta(o.getNombre(), "nombre-articulo");
        Label precio = UI.etiqueta(Funcion.dinero(o.getPrecio()), "precio-articulo");
        VBox cabecera = new VBox(2, nombre, precio);
        HBox arriba = new HBox(12, UI.iconoObjeto(o, 52), cabecera);
        arriba.setAlignment(Pos.CENTER_LEFT);

        Label descripcion = UI.etiqueta(o.getDescripcion(), "texto");
        descripcion.setWrapText(true);
        descripcion.setPrefHeight(42);
        descripcion.setStyle("-fx-font-size: 13px;");

        int[] cantidad = {1};
        Label numero = UI.etiqueta("1", "cantidad-articulo");
        Button menos = UI.botonClaro("−");
        Button mas = UI.botonClaro("+");
        menos.getStyleClass().add("pequeno");
        mas.getStyleClass().add("pequeno");
        Button comprar = UI.boton("COMPRAR");
        comprar.getStyleClass().add("pequeno");

        Runnable refrescar = () -> {
            numero.setText(String.valueOf(cantidad[0]));
            menos.setDisable(cantidad[0] <= 1);
            mas.setDisable(cantidad[0] >= 99);
            UI.tooltip(comprar, "Total: " + Funcion.dinero(o.getPrecio() * cantidad[0]));
        };
        menos.setOnAction(e -> {
            cantidad[0]--;
            refrescar.run();
        });
        mas.setOnAction(e -> {
            cantidad[0]++;
            refrescar.run();
        });
        comprar.setOnAction(e -> comprar(o, cantidad[0], () -> {
            cantidad[0] = 1;
            refrescar.run();
        }));
        refrescar.run();

        HBox controles = new HBox(6, menos, numero, mas, UI.espacio(), comprar);
        controles.setAlignment(Pos.CENTER_LEFT);

        VBox t = new VBox(8, arriba, descripcion, controles);
        t.getStyleClass().addAll("tarjeta", "tarjeta-articulo");
        t.setPrefWidth(292);
        t.setMaxWidth(292);
        t.setPadding(new Insets(12));
        return t;
    }

    private void comprar(Objeto o, int cantidad, Runnable despues) {
        int total = o.getPrecio() * cantidad;
        if (total > Entrenador.getDinero()) {
            Dialogo.info(pantalla, "No te llega el dinero",
                    "Cuesta " + Funcion.dinero(total) + " y tienes " + Funcion.dinero(Entrenador.getDinero())
                            + ".\n¡Gana combates para conseguir más!");
            return;
        }
        String unidades = cantidad == 1 ? "1 " + o.getNombre() : cantidad + " × " + o.getNombre();
        Dialogo.confirmar(pantalla, "¿Comprar " + unidades + "?", "Total: " + Funcion.dinero(total),
                "COMPRAR", "CANCELAR", false, () -> {
                    if (PokemonCRUD.comprar(o, cantidad)) {
                        dinero.refrescar();
                        refrescarMochila();
                        despues.run();
                        Dialogo.aviso(pantalla, "¡Has comprado " + unidades + "!");
                    } else {
                        Dialogo.info(pantalla, "No se pudo comprar", "No tienes dinero suficiente.");
                    }
                });
    }

    private void refrescarMochila() {
        listaMochila.getChildren().clear();
        Mochila mochila = PokemonCRUD.getMochila();
        if (mochila.estaVacia()) {
            Label vacia = UI.etiqueta("Tu mochila está vacía.\nCompra Poké Balls para poder capturar Pokémon.", "texto-suave");
            vacia.setWrapText(true);
            listaMochila.getChildren().add(vacia);
            return;
        }
        for (Map.Entry<Objeto, Integer> e : mochila.getObjetos().entrySet()) {
            Label nombre = UI.etiqueta(e.getKey().getNombre(), "nombre-articulo");
            nombre.setStyle("-fx-font-size: 16px;");
            Label cantidad = UI.etiqueta("x" + e.getValue(), "cantidad-mochila");
            HBox fila = new HBox(10, UI.iconoObjeto(e.getKey(), 32), nombre, UI.espacio(), cantidad);
            fila.setAlignment(Pos.CENTER_LEFT);
            fila.getStyleClass().add("fila-mochila");
            UI.tooltip(fila, e.getKey().getDescripcion());
            listaMochila.getChildren().add(fila);
        }
    }
}
