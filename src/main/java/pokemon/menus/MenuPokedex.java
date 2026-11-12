package pokemon.menus;

import pokemon.database.PokemonCRUD;
import pokemon.pokemon.Pokemon;
import pokemon.ui.App;
import pokemon.ui.Barra;
import pokemon.ui.Comunes;
import pokemon.ui.Musica;
import pokemon.ui.Pantalla;
import pokemon.ui.UI;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.util.List;

/** Pokédex: consulta de los datos de cualquier Pokémon. */
public class MenuPokedex {

    private final Pantalla pantalla = new Pantalla();
    private final ImageView sprite = new ImageView();
    private final Label numero = UI.etiqueta("", "pokedex-numero");
    private final Label nombre = UI.etiqueta("", "pokedex-nombre");
    private final HBox tipos = new HBox(6);
    private final GridPane stats = new GridPane();
    private final Label mensaje = UI.etiqueta("", "pokedex-mensaje");
    private final FlowPane resultados = new FlowPane(6, 6);
    private final TextField buscador = new TextField();
    private int total;
    private int actual = 1;

    private static final String[] NOMBRES_STATS = {"Vida", "Ataque", "Defensa", "At. Esp.", "Def. Esp.", "Velocidad", "Estamina"};
    private final Barra[] barras = new Barra[NOMBRES_STATS.length];
    private final Label[] valores = new Label[NOMBRES_STATS.length];

    public static void mostrar() {
        new MenuPokedex().abrir();
    }

    private void abrir() {
        Musica.reproducir("/aud/MenuPokedex.wav");
        pantalla.fondoDesenfocado("/img/pokedex.png").estilo("Pokedex.css");
        total = PokemonCRUD.totalPokedex();

        // Imagen del dispositivo, alineada con el diseño de 1080x650.
        ImageView dispositivo = UI.vistaImagen("/img/pokedex.png", Pantalla.ANCHO, Pantalla.ALTO);
        dispositivo.setPreserveRatio(false);
        dispositivo.setSmooth(true);
        pantalla.poner(dispositivo, 0, 0);

        // Pantalla blanca de la izquierda: el sprite
        sprite.setFitWidth(230);
        sprite.setFitHeight(200);
        sprite.setPreserveRatio(true);
        VBox pantallaBlanca = new VBox(sprite);
        pantallaBlanca.setAlignment(Pos.CENTER);
        pantallaBlanca.setPrefSize(300, 244);
        pantalla.poner(pantallaBlanca, 190, 166);
        pantalla.poner(numero, 200, 172);

        // Botones verde/amarillo del dispositivo = anterior / siguiente
        Button anterior = UI.botonClaro("◀");
        anterior.getStyleClass().add("pequeno");
        anterior.setOnAction(e -> ver(actual <= 1 ? total : actual - 1));
        UI.tooltip(anterior, "Anterior (flecha izquierda)");
        Button siguiente = UI.botonClaro("▶");
        siguiente.getStyleClass().add("pequeno");
        siguiente.setOnAction(e -> ver(actual >= total ? 1 : actual + 1));
        UI.tooltip(siguiente, "Siguiente (flecha derecha)");
        HBox flechas = new HBox(10, anterior, siguiente);
        pantalla.poner(flechas, 236, 438);

        // Buscador
        buscador.getStyleClass().add("campo-juego");
        buscador.setPromptText("Buscar nombre o número");
        buscador.setPrefWidth(270);
        buscador.setOnAction(e -> buscar());
        Button lupa = UI.boton("BUSCAR");
        lupa.getStyleClass().add("pequeno");
        lupa.setOnAction(e -> buscar());
        VBox busqueda = new VBox(8, buscador, lupa);
        pantalla.poner(busqueda, 175, 492);

        // Panel derecho: datos
        tipos.setAlignment(Pos.CENTER_LEFT);
        stats.setHgap(10);
        stats.setVgap(9);
        for (int i = 0; i < NOMBRES_STATS.length; i++) {
            Label n = UI.etiqueta(NOMBRES_STATS[i], "pokedex-stat");
            barras[i] = new Barra(Barra.Modo.FIJO, 190, 14, i == 0 ? "#7CF07C" : i == NOMBRES_STATS.length - 1 ? "#5DADFF" : "#FFCB05");
            valores[i] = UI.etiqueta("", "pokedex-valor");
            stats.addRow(i, n, barras[i], valores[i]);
        }
        resultados.setPrefWrapLength(340);
        VBox datos = new VBox(12, nombre, tipos, stats, mensaje, resultados);
        datos.setPrefWidth(350);
        pantalla.poner(datos, 566, 120);

        pantalla.poner(Comunes.botonAtras(Menu::mostrar), 18, 16);
        pantalla.poner(Comunes.botonSilencio(), 998, 16);

        pantalla.setOnKeyPressed(e -> {
            if (buscador.isFocused()) return;
            if (e.getCode() == KeyCode.LEFT) anterior.fire();
            else if (e.getCode() == KeyCode.RIGHT) siguiente.fire();
        });

        ver(1);
        App.mostrar(pantalla);
    }

    private void buscar() {
        String texto = buscador.getText().trim();
        resultados.getChildren().clear();
        if (texto.isEmpty()) return;
        List<Pokemon> encontrados = PokemonCRUD.buscarPokedex(texto);
        if (encontrados.isEmpty()) {
            mensaje.setText("No hay ningún Pokémon que se llame \"" + texto + "\".");
            return;
        }
        mostrarPokemon(encontrados.get(0));
        if (encontrados.size() > 1) {
            mensaje.setText("También coinciden:");
            for (Pokemon p : encontrados.subList(1, Math.min(encontrados.size(), 9))) {
                Button b = UI.botonClaro(p.getNombre());
                b.getStyleClass().add("pequeno");
                b.setOnAction(e -> mostrarPokemon(p));
                resultados.getChildren().add(b);
            }
        }
        buscador.selectAll();
    }

    private void ver(int id) {
        Pokemon p = PokemonCRUD.getPokedex(id);
        if (p != null) {
            resultados.getChildren().clear();
            mostrarPokemon(p);
        }
    }

    private void mostrarPokemon(Pokemon p) {
        actual = p.getIdPokedex();
        mensaje.setText("");
        sprite.setImage(UI.imagen(p.getFoto()));
        numero.setText(String.format("Nº %03d", p.getIdPokedex()));
        nombre.setText(p.getNombre());
        tipos.getChildren().setAll(UI.chipsTipos(p.getTipo1(), p.getTipo2()).getChildren());
        double[] v = {p.getVitalidad(), p.getAtaque(), p.getDefensa(), p.getAtaqueEspecial(), p.getDefensaEspecial(),
                p.getVelocidad(), p.getEstamina()};
        double[] max = {150, 200, 200, 200, 200, 200, 320};
        for (int i = 0; i < v.length; i++) {
            barras[i].animarA(v[i] / max[i], Duration.millis(350));
            valores[i].setText(String.valueOf((int) v[i]));
        }
    }
}
