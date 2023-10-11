package Pokemon.Entrenador;

import Pokemon.Database.PokemonCRUD;
import Pokemon.Tienda.Objeto;
import Pokemon.Pokemon.Pokemon;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;

//CLASE ENTRENADOR
public class Entrenador {

    static Scanner sc = new Scanner(System.in);


    protected static String nombre;
    private static int dinero;
    private static Map<Integer, Objeto> mochila;
    private static Map<Objeto, Integer> contador; // nuevo HashMap
    public static LinkedList<Pokemon> equipo1 = new LinkedList<>();
    public static LinkedList<Pokemon> equipo2 = new LinkedList<>();
    public static LinkedList<Pokemon> caja = new LinkedList<>();
    public static String foto;
    private String pass;

//


    // Constructor con todos los parametros

    public Entrenador(String nombre, int dinero, Map<Integer, Objeto> mochila, Map<Objeto, Integer> contador, String contrasena,
                      LinkedList<Pokemon> equipo1, LinkedList<Pokemon> equipo2, LinkedList<Pokemon> caja, String foto) {
        this.nombre = nombre;
        this.dinero = dinero;
        this.mochila = new HashMap<>(); //Pasar a linkedList
        this.contador = new HashMap<>(); //Ver si se quita o se deja.
        this.pass = contrasena;
        this.foto = foto;

    }

    // Constructor por defecto:

    public Entrenador() {
        this.nombre = "";
        this.dinero = 800;
        this.mochila = null;
        this.contador = null;
        this.foto = null;
    }


    // MÉTODO PARA COMPRAR UN OBJETO
    public static void comprarObjeto(Objeto objeto) {
        if (dinero >= objeto.getPrecio()) {
            dinero -= objeto.getPrecio();
            PokemonCRUD.actualizarDinero();
            mochila.put(objeto.getId(), objeto);
            System.out.println("¡Objeto  " + objeto.getNombre() + " comprado y añadido a la mochila!");
            // Incrementar el contador del objeto comprado
            if (contador.containsKey(objeto)) {
                contador.put(objeto, contador.get(objeto) + 1);
            } else {
                contador.put(objeto, 1);
            }
        } else {
            System.out.println("No tienes suficiente dinero para comprar este objeto.");
        }
    }


    // MÉTODO PARA OBTENER EL DINERO DEL ENTRENADOR
    public static int getDinero() {
        return dinero;
    }

    public static Map<Integer, Objeto> getMochila() {
        return mochila;
    }

    public static Map<Objeto, Integer> getContador() {
        return contador;
    }

    public static String getNombre() {
        return nombre;
    }

    public static void setNombre(String nombre) {
        Entrenador.nombre = nombre;
    }

    public static void setDinero() {
        Entrenador.dinero = dinero;
    }

    public static void setMochila(Map<Integer, Objeto> mochila) {
        Entrenador.mochila = mochila;
    }

    public static void setContador(Map<Objeto, Integer> contador) {
        Entrenador.contador = contador;
    }

    public static String getFoto() {
        return foto;
    }

    public static void setFoto(String foto) {
        Entrenador.foto = foto;
    }

    public static LinkedList<Pokemon> getEquipo1() {
        return equipo1;
    }

    public static void setEquipo1(LinkedList<Pokemon> equipo1) {
        Entrenador.equipo1 = equipo1;
    }

    public static LinkedList<Pokemon> getEquipo2() {
        return equipo2;
    }

    public static void setEquipo2(LinkedList<Pokemon> equipo2) {
        Entrenador.equipo2 = equipo2;
    }

    public static LinkedList<Pokemon> getCaja() {
        return caja;
    }

    public static void setCaja(LinkedList<Pokemon> caja) {
        Entrenador.caja = caja;
    }

    public static void setContrasena(String contrasena) {

    }

    public static String getContrasena() {
        return null;
    }


    /**
     * @param pokemon
     * @return Se le pasan dos parametros al método, el Pokemon que se quiere añadir, y el equipo al que se quiere añadir.
     * Si el equipo contiene < 6 integrantes se añade, si no, prueba en el equipo2, y si tampoco cabe lo mete en la caja.
     */

    public static int addPokemon(Pokemon pokemon) {
        int equipo = 0;

        Entrenador.equipo1.clear();
        Entrenador.equipo2.clear();
        Entrenador.caja.clear();


        PokemonCRUD.getEquipo1(Entrenador.equipo1, PokemonCRUD.idEntrenador());
        PokemonCRUD.getEquipo2(Entrenador.equipo2, PokemonCRUD.idEntrenador());
        PokemonCRUD.getCaja(Entrenador.caja, PokemonCRUD.idEntrenador());

        if (equipo1.size() < 6) {
            equipo1.add(pokemon);
            equipo = 1;
            System.out.println(equipo1);
        } else if (equipo2.size() < 6) {
            equipo2.add(pokemon);
            equipo = 2;
            System.out.println(equipo2);
        } else if(caja.size() < 15) {
            caja.add(pokemon);
            equipo = 3;
        } else {
            return equipo;
        }

        return equipo;
    }

    public static String fotoDefault() { //Método añadido post-tests.
        return "img/entrenador2.gif";
    }


}
