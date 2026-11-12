package pokemon.combate;

import pokemon.database.PokemonCRUD;
import pokemon.entrenador.Entrenador;
import pokemon.menus.Menu;
import pokemon.pokemon.Pokemon;
import pokemon.ui.App;
import pokemon.ui.Comunes;
import pokemon.ui.Musica;
import pokemon.ui.Pantalla;
import pokemon.ui.UI;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

import java.util.ArrayList;
import java.util.List;

/** Antes del combate: elegir qué Pokémon del equipo titular sale primero. */
public class Selector {

    public static void mostrar() {
        new Selector().abrir();
    }

    private void abrir() {
        Musica.reproducir("/aud/Menu.wav");
        PokemonCRUD.recargarEquipos();
        List<Pokemon> equipo = new ArrayList<>(Entrenador.equipo1);

        Pantalla pantalla = new Pantalla().fondoVideo("/vid/seleccion.mp4").estilo("Selector.css");
        StackPane root = pantalla.getContenido();

        ImageView selectorView = new ImageView(UI.imagen("/img/selector.png"));
        selectorView.setFitWidth(720);
        selectorView.setFitHeight(350);
        selectorView.setSmooth(true);
        selectorView.setId("selectorTxt");
        root.getChildren().add(selectorView);

        for (int i = 0; i < 6; i++) {
            Button opcion = new Button();
            opcion.setId("opcion" + (i + 1));
            root.getChildren().add(opcion);
            if (i < equipo.size()) {
                Pokemon p = equipo.get(i);
                final int indice = i;
                opcion.setOnAction(e -> CombateGrafico.mostrar(equipo, indice));
                UI.tooltip(opcion, "Empezar con " + p.getNombreMostrado());

                ImageView sprite = UI.vistaImagen(p.getFoto(), 100, 70);
                sprite.setId("pokemon" + (i + 1));
                sprite.setMouseTransparent(true);
                root.getChildren().add(sprite);

                Label nombre = UI.etiqueta(p.getNombreMostrado() + "\nNv. " + p.getNivel(), "nombre-selector");
                nombre.setId("nombre" + (i + 1));
                nombre.setMouseTransparent(true);
                root.getChildren().add(nombre);
            } else {
                opcion.setDisable(true);
                opcion.getStyleClass().add("vacia");
            }
        }

        Label ayuda = UI.etiqueta(equipo.size() < 6
                ? "Tu equipo titular tiene " + equipo.size() + " Pokémon. El rival sacará los mismos."
                : "Elige qué Pokémon sale primero.", "texto-claro");
        ayuda.setStyle("-fx-font-size: 17px;");
        ayuda.setTranslateY(255);
        root.getChildren().add(ayuda);

        pantalla.poner(Comunes.botonAtras(Menu::mostrar), 18, 16);
        pantalla.poner(Comunes.botonSilencio(), 998, 16);

        App.mostrar(pantalla);
    }
}
