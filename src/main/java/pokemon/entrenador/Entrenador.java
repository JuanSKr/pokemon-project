package pokemon.entrenador;

import pokemon.pokemon.Pokemon;

import java.util.LinkedList;

/**
 * Datos del entrenador que ha iniciado sesión. Solo hay un entrenador a la vez,
 * por eso todo es estático. Se rellena en PokemonCRUD.cargarEntrenador().
 */
public class Entrenador {

    /** Máximo de Pokémon por equipo. */
    public static final int TAMANO_EQUIPO = 6;
    /** Máximo de Pokémon en la caja. */
    public static final int TAMANO_CAJA = 30;
    /** Avatar que se usa si el entrenador todavía no ha elegido uno. */
    public static final String FOTO_DEFECTO = "/img/entrenador2.gif";

    private static int id;
    private static String nombre = "";
    private static int dinero;
    private static String foto;

    public static LinkedList<Pokemon> equipo1 = new LinkedList<>();
    public static LinkedList<Pokemon> equipo2 = new LinkedList<>();
    public static LinkedList<Pokemon> caja = new LinkedList<>();

    private Entrenador() {
    }

    /** Carga los datos básicos del entrenador que ha iniciado sesión. */
    public static void iniciarSesion(int id, String nombre, int dinero, String foto) {
        Entrenador.id = id;
        Entrenador.nombre = nombre;
        Entrenador.dinero = dinero;
        Entrenador.foto = foto;
        equipo1.clear();
        equipo2.clear();
        caja.clear();
    }

    public static int getId() {
        return id;
    }

    public static String getNombre() {
        return nombre;
    }

    public static int getDinero() {
        return dinero;
    }

    public static void setDinero(int dinero) {
        Entrenador.dinero = Math.max(0, dinero);
    }

    /** Foto del avatar. Nunca devuelve null: si no tiene, devuelve la de por defecto. */
    public static String getFoto() {
        return (foto == null || foto.isBlank()) ? FOTO_DEFECTO : foto;
    }

    public static void setFoto(String foto) {
        Entrenador.foto = foto;
    }

    public static String fotoDefault() {
        return FOTO_DEFECTO;
    }
}
