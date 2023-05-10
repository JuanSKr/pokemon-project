package Pokemon.Entrenador;

import Pokemon.Database.PokemonCRUD;
import Pokemon.Tienda.Objeto;
import Pokemon.Pokemon.Pokemon;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;

//CLASE ENTRENADOR
public class Entrenador {

    static Scanner sc = new Scanner(System.in);


    protected static String nombre;
    private static int dinero;
    public static LinkedList<Objeto> mochila = new LinkedList<>();
    private static Map<Objeto, Integer> contador; // nuevo HashMap
    public static LinkedList<Pokemon> equipo1 = new LinkedList<>();
    public static LinkedList<Pokemon> equipo2 = new LinkedList<>();
    public static LinkedList<Pokemon> caja = new LinkedList<>();
    private String pass;

//


    // Constructor con todos los parametros

    public Entrenador(String nombre, int dinero, LinkedList<Objeto> mochila, Map<Objeto, Integer> contador, String contrasena, LinkedList<Pokemon> equipo1, LinkedList<Pokemon> equipo2, LinkedList<Pokemon> caja) {
        this.nombre = nombre;
        this.dinero = dinero;
        this.contador = new HashMap<>(); //Ver si se quita o se deja.
        this.pass = contrasena;

    }

    // Constructor por defecto:

    public Entrenador() {
        this.nombre = "";
        this.dinero = 500;
        this.mochila = null;
        this.contador = null;
    }


    // MÉTODO PARA COMPRAR UN OBJETO
    public static void comprarObjeto(Objeto objeto) {
        if (dinero >= objeto.getPrecio()) {
            dinero -= objeto.getPrecio();
            mochila.add(objeto.getId(), objeto);
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


    public static void setContador(Map<Objeto, Integer> contador) {
        Entrenador.contador = contador;
    }


    public static void setContrasena(String contrasena) {



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
     * @return Se le pasan dos parametros al método, el Pokemon que se quiere añadir, y el equipo al que se quiere añadir.
     * Si el equipo contiene < 6 integrantes se añade, si no, prueba en el equipo2, y si tampoco cabe lo mete en la caja.
     */

    public static int addPokemon(Pokemon pokemon) {
        int equipo;

        PokemonCRUD.getEquipo1(Entrenador.equipo1);
        PokemonCRUD.getEquipo2(Entrenador.equipo2);
        PokemonCRUD.getCaja(Entrenador.caja);

        if (equipo1.size() < 6) {
            equipo1.add(pokemon);
            equipo = 1;
            System.out.println(equipo1);
        } else if (equipo2.size() < 6) {
            equipo2.add(pokemon);
            equipo = 2;
            System.out.println(equipo2);
        } else {
            caja.add(pokemon);
            equipo = 3;
        }

        return equipo;
    }



    /**
     * @return Metodo para darle al usuario la opción de elegir su Pokémon inicial entre 3.
     */

//    public static void primerPokemon() {
//
//        System.out.println("-----------------------");
//        System.out.println("   PRIMER POKEMON");
//        System.out.println("-----------------------");
//        System.out.println("1.Pikachu\n2.Charmander\n3.Bulbasaur");
//        System.out.print("Elige tu primer pokemon: ");
//        int opcion = sc.nextInt();
//
//        switch (opcion) {
//
//            case 1:
//
//                equipo1.add(Pokedex.Pikachu);
//
//                System.out.println(Pokedex.Pikachu.getNombre() + " se ha añadido a equipo1.");
//                break;
//
//            case 2:
//
//                equipo1.add(Pokedex.Raichu);
//                System.out.println(Pokedex.Raichu.getNombre() + " se ha añadido a equipo1.");
//
//                break;
//
//            case 3:
//
//                equipo1.add(Pokedex.Bulbasaur);
//                System.out.println(Pokedex.Bulbasaur.getNombre() + " se ha añadido a equipo1.");
//
//                break;
//        }
//
//    }

    public static void verEquipos() {

        PokemonCRUD.getEquipo1(equipo1);
        PokemonCRUD.getEquipo2(equipo2);
        PokemonCRUD.getCaja(caja);

        System.out.println("Equipo 1:");
        for (Pokemon mostrarEquipo : equipo1) {
            System.out.println(mostrarEquipo.toString());
        }

        System.out.println("Equipo 2:");
        for (Pokemon mostrarEquipo : equipo2) {
            System.out.println(mostrarEquipo.toString());
        }

        System.out.println("Caja:");
        for (Pokemon mostrarEquipo : caja) {
            System.out.println(mostrarEquipo.toString());
        }
    }



    public static void main(String[] args) {

        PokemonCRUD.mostrarPokemon();


    }

}
