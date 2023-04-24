package Pokemon.Entrenador;

import Pokemon.Pokemon.Objeto;
import Pokemon.Pokemon.Pokemon;
import Pokemon.Menus.Pokedex;

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
    private static LinkedList<Pokemon> equipo1;
    private static LinkedList<Pokemon> equipo2;
    private static LinkedList<Pokemon> caja;
    private String contrasena;

//


    // Constructor con todos los parametros

    public Entrenador() {
        this.nombre = nombre;
        this.dinero = dinero;
        this.mochila = mochila;
        this.contador = contador;
        this.equipo1 = equipo1;
        this.equipo2 = equipo2;
        this.caja = caja;
    }


    public Entrenador(String nombre, int dinero, Map<Integer, Objeto> mochila, Map<Objeto, Integer> contador, String contrasena, LinkedList<Pokemon> equipo1, LinkedList<Pokemon> equipo2, LinkedList<Pokemon> caja) {
        this.nombre = "";
        this.dinero = 500;
        this.mochila = new HashMap<>();
        this.contador = new HashMap<>();
        this.contrasena = contrasena;
        this.equipo1 = equipo1;
        this.equipo2 = equipo2;
        this.caja = caja;
    }


    // MÉTODO PARA COMPRAR UN OBJETO
    public static void comprarObjeto(Objeto objeto) {
        if (dinero >= objeto.getPrecio()) {
            dinero -= objeto.getPrecio();
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

    public static void setDinero(int dinero) {
        Entrenador.dinero = dinero;
    }

    public static void setMochila(Map<Integer, Objeto> mochila) {
        Entrenador.mochila = mochila;
    }

    public static void setContador(Map<Objeto, Integer> contador) {
        Entrenador.contador = contador;
    }

    public static void setContrasena(String contrasena) {
        // TODO Auto-generated method stub

    }

    public static String getContrasena() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * @param pokemon
     * @param equipoInicial
     * @param equipoFinal
     * @return Se le pasan 3 parametros al método, el Pokemon que se quiere mover, el equipo donde está y donde debe acabar
     */

    public static void moverPokemon(Pokemon pokemon, LinkedList equipoInicial, LinkedList equipoFinal) {

        equipoInicial.remove(pokemon);
        equipoFinal.add(pokemon);

    }


    /**
     * @param pokemon
     * @param equipo1
     * @return Se le pasan dos parametros al método, el Pokemon que se quiere añadir, y el equipo al que se quiere añadir. Si el equipo contiene < 6 integrantes se añade, si no, no.
     */

    public static void addPokemon(Pokemon pokemon, LinkedList<Pokemon> equipo1, LinkedList<Pokemon> equipo2, LinkedList<Pokemon> caja) {
        try {
            if (equipo1.size() < 1) {
                equipo1.add(pokemon);
            } else if (equipo2 != null && equipo2.size() < 1) {
                equipo2.add(pokemon);
                System.out.println("El " + pokemon.getNombre() + " no cabe en el equipo titular. Ha sido agregado al equipo suplente.");
            } else {
                caja.add(pokemon);
                System.out.println("El " + pokemon.getNombre() + " no cabe en ningún equipo. Ha sido agregado a la caja.");
            }
        } catch (NullPointerException e) {
            System.out.println("Error: No tienes capacidad para almacenar a " + pokemon.getNombre());
        }
    }

    /**
     * @return Metodo para darle al usuario la opción de elegir su Pokémon inicial entre 3.
     */

    public static void primerPokemon() {
        LinkedList<Pokemon> equipo1 = new LinkedList<Pokemon>();
        LinkedList<Pokemon> equipo2 = new LinkedList<Pokemon>();
        LinkedList<Pokemon> caja = new LinkedList<Pokemon>();

        System.out.print("Elige que pokemon añadir a equipo1: ");
        System.out.println("\n1.Pikachu\n2.Charmander\n3.Bulbasaur");
        int opcion = sc.nextInt();

        switch (opcion) {

            case 1:

                equipo1.add(Pokedex.Pikachu);

                System.out.println(Pokedex.Pikachu.getNombre() + " se ha añadido a equipo1.");
                break;

            case 2:

                equipo1.add(Pokedex.Raichu);
                System.out.println(Pokedex.Raichu.getNombre() + " se ha añadido a equipo1.");

                break;

            case 3:

                equipo1.add(Pokedex.Bulbasaur);
                System.out.println(Pokedex.Bulbasaur.getNombre() + " se ha añadido a equipo1.");

                break;
        }

    }


    public static void main(String[] args) {

        LinkedList<Pokemon> equipo1 = new LinkedList<Pokemon>();
        LinkedList<Pokemon> equipo2 = new LinkedList<Pokemon>();
        LinkedList<Pokemon> caja = new LinkedList<Pokemon>();

        addPokemon(Pokedex.Raichu, equipo1, equipo2, caja);
        addPokemon(Pokedex.Butterfree, equipo1, equipo2, caja);

        System.out.println("Equipo 1:");
        for (Pokemon mostrarEquipo : equipo1) {
            System.out.println(mostrarEquipo.getNombre());
        }

        System.out.println("---------------[TEST]------------------");
        System.out.println("Añadimos Raticate a equipo2 (no cabe)");
        System.out.println("---------------[TEST]------------------");

        addPokemon(Pokedex.Bulbasaur, equipo1, equipo2, caja);

        System.out.println("Caja:");
        for (Pokemon mostrarEquipo : caja) {
            System.out.println(mostrarEquipo.getNombre());
        }

    }

}
