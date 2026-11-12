package pokemon.combate;

import pokemon.database.PokemonCRUD;
import pokemon.funcionalidad.Funcion;
import pokemon.pokemon.Pokemon;

import java.util.ArrayList;
import java.util.List;

/** Entrenador rival generado al azar para un combate. */
public class Rival {

    private static final String[] NOMBRES = {
            "Entrenador Iniesta", "Entrenador Guti", "Entrenador Hamilton", "Montañero Marín", "Campista Puja",
            "Dominguera Lola", "Karateka Nacho", "Científica Vera", "Pescador Ramón", "Medium Ágata"
    };
    private static final String[] FOTOS = {
            "/img/Entrenador1.gif", "/img/entrenador4.gif", "/img/entrenador5.gif", "/img/entrenador3.png"
    };

    private final String nombre;
    private final String foto;
    private final List<Pokemon> equipo;

    public Rival(String nombre, String foto, List<Pokemon> equipo) {
        this.nombre = nombre;
        this.foto = foto;
        this.equipo = equipo;
    }

    /**
     * Genera un rival con tantos Pokémon como tiene el jugador y de un nivel parecido.
     * @param tamanoEquipo número de Pokémon del rival
     * @param nivelMedio   nivel medio del equipo del jugador
     */
    public static Rival generarRival(int tamanoEquipo, int nivelMedio) {
        String nombre = NOMBRES[Funcion.random(0, NOMBRES.length - 1)];
        String foto = FOTOS[Funcion.random(0, FOTOS.length - 1)];
        List<Pokemon> equipo = new ArrayList<>();
        for (int i = 0; i < tamanoEquipo; i++) {
            Pokemon p = PokemonCRUD.generarPokemon();
            int nivel = Math.max(1, nivelMedio + Funcion.random(-1, 1));
            p.setNivel(nivel);
            for (int n = 1; n < nivel; n++) p.subirStats();
            p.setMovimientos(PokemonCRUD.generarMovimientos(p));
            p.prepararCombate();
            equipo.add(p);
        }
        return new Rival(nombre, foto, equipo);
    }

    public String getNombre() {
        return nombre;
    }

    public String getFoto() {
        return foto;
    }

    public List<Pokemon> getEquipo() {
        return equipo;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
