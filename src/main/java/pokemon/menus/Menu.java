package pokemon.menus;

import pokemon.combate.Selector;
import pokemon.database.PokemonCRUD;
import pokemon.entrenador.Entrenador;
import pokemon.tienda.MenuTienda;
import pokemon.ui.App;
import pokemon.ui.Comunes;
import pokemon.ui.Dialogo;
import pokemon.ui.Musica;
import pokemon.ui.Pantalla;
import pokemon.ui.UI;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/** Menú principal (el de la barra amarilla). */
public class Menu {

    public static void mostrar() {
        new Menu().abrir();
    }

    private void abrir() {
        Musica.reproducir("/aud/Menu.wav");
        Pantalla pantalla = new Pantalla().fondoVideo("/vid/menu.mp4").estilo("Menu.css");

        ImageView barraView = UI.vistaImagen("/img/barra.png", 1080, 650);
        barraView.setPreserveRatio(false);
        barraView.setSmooth(true);
        barraView.setId("barra");

        ImageView logoView = UI.vistaImagen("/img/logo.png", 540, 230);
        logoView.setPreserveRatio(false);
        logoView.setSmooth(true);
        logoView.setId("logo");

        Button combateButton = new Button();
        combateButton.setId("combateButton");
        Button capturarButton = new Button();
        capturarButton.setId("capturarButton");
        Button pokedexButton = new Button();
        pokedexButton.setId("pokedexButton");
        Button entrenadorButton = new Button();
        entrenadorButton.setId("entrenadorButton");
        Button tiendaButton = new Button();
        tiendaButton.setId("tiendaButton");
        Button muteButton = new Button();
        muteButton.setId("muteButton");

        UI.tooltip(combateButton, "Lucha contra un entrenador rival");
        UI.tooltip(capturarButton, "Explora y captura Pokémon salvajes");
        UI.tooltip(pokedexButton, "Consulta los datos de cualquier Pokémon");
        UI.tooltip(tiendaButton, "Compra Poké Balls y objetos");
        UI.tooltip(entrenadorButton, "Tu avatar y tus equipos");
        UI.tooltip(muteButton, "Quitar / poner el sonido");

        muteButton.setOnAction(e -> Musica.alternarSilencio());

        combateButton.setOnAction(e -> {
            PokemonCRUD.recargarEquipos();
            if (Entrenador.equipo1.isEmpty()) {
                Dialogo.confirmar(pantalla, "Tu equipo está vacío",
                        "Necesitas al menos un Pokémon en tu equipo titular para combatir. "
                                + "Captura alguno o pásalo desde tu caja en Perfil > Equipos.",
                        "IR A CAPTURAR", "CERRAR", false, MenuExplorador::mostrar);
            } else {
                Selector.mostrar();
            }
        });
        capturarButton.setOnAction(e -> MenuExplorador.mostrar());
        pokedexButton.setOnAction(e -> MenuPokedex.mostrar());
        entrenadorButton.setOnAction(e -> MenuEntrenador.mostrar());
        tiendaButton.setOnAction(e -> MenuTienda.mostrar());

        pantalla.getContenido().getChildren().addAll(barraView, combateButton, capturarButton, entrenadorButton,
                pokedexButton, tiendaButton, muteButton, logoView);

        // Tarjeta del entrenador (arriba a la izquierda)
        ImageView avatar = UI.vistaImagen(Entrenador.getFoto(), 58, 58);
        Label nombre = UI.etiqueta(Entrenador.getNombre(), "texto-claro", "negrita");
        nombre.setStyle("-fx-font-size: 20px;");
        Comunes.Dinero dinero = new Comunes.Dinero();
        VBox datos = new VBox(4, nombre, dinero);
        HBox tarjeta = new HBox(12, avatar, datos);
        tarjeta.setAlignment(Pos.CENTER_LEFT);
        tarjeta.getStyleClass().add("panel-oscuro");
        pantalla.poner(tarjeta, 18, 16);

        Button salir = UI.botonImagen("/img/botones/menu-salir.png", 130, 73);
        UI.tooltip(salir, "Cerrar sesión");
        salir.setOnAction(e -> Dialogo.confirmar(pantalla, "¿Cerrar sesión?",
                "Volverás a la pantalla de inicio.", "CERRAR SESIÓN", "CANCELAR", false, Login::mostrar));
        pantalla.poner(salir, 16, 566);

        App.mostrar(pantalla);
    }
}
