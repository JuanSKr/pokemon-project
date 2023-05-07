package Pokemon.Combate;

import Pokemon.Database.MySQL;
import Pokemon.Database.PokemonCRUD;
import Pokemon.Entrenador.Entrenador;
import Pokemon.Pokemon.Pokemon;

import java.util.Scanner;


public class Combate {

    static Scanner sc = new Scanner(System.in);

    public static Pokemon elegirPokemon() {
        //El sout del equipo del Rival está aquí dentro, es equipoRival().

        System.out.println("¿Con qué Pokemon quieres empezar?");
        Pokemon pokemon = Pokemon.mostrarPokemon();
        System.out.println("----------------------------------");
        System.out.println("¡Has seleccionado a " + pokemon.getNombre() + "!");
        return pokemon;

    }

    public static void accionEntrenador(Pokemon pokemonEntrenador, Pokemon pokemonRival) {
        System.out.println("----------------------------------");
        System.out.println("      ¿Que quieres hacer?");
        System.out.println("----------------------------------");
        System.out.println("1. Atacar");
        System.out.println("2. Cambiar Pokemon");
        System.out.println("3. Mochila");
        System.out.println("4. Rendirse");
        System.out.println("5. Descansar");
        System.out.println("----------------------------------");
        int opcion = sc.nextInt();

        switch (opcion) {

            case 1:
                System.out.println("ataque");
                break;

            case 2:
                System.out.println("cambiar");
                break;

            case 3:
                System.out.println("mochila");
                break;

            case 4:
                System.out.println("rendirse");
                break;

            case 5:
                System.out.println("descansar");
                break;
        }

    }


    public static void accionRival(Rival rival, Pokemon pokemonEntrenador, Pokemon pokemonRival) {

        System.out.println(pokemonRival.getNombre() + "ha utilizado " + Rival.movimientoRandom(pokemonRival));

    }

    /**
     * @param pokemonEntrenador Pokemon del entrenador
     * @param pokemonRival      Pokemon del rival (bot)
     */

    public static void estadisticas(Pokemon pokemonEntrenador, Pokemon pokemonRival) {

        System.out.println("------------------------");
        System.out.println("Stats de " + pokemonEntrenador.getNombre() + " (Tú)");
        System.out.println("------------------------");
        System.out.println("Vida: " + pokemonEntrenador.getVitalidad());
        System.out.println("Estamina: " + pokemonEntrenador.getEstamina());
        System.out.println("------------------------");
        System.out.println("Stats de " + pokemonRival.getNombre() + " (Rival)");
        System.out.println("------------------------");
        System.out.println("Vida: " + pokemonRival.getVitalidad());
        System.out.println("Estamina: " + pokemonRival.getEstamina());

    }


    public static void main(String[] args) {

        Rival rival = Rival.generarRival();
        Pokemon pokemonRival = Rival.pokemonRival();
        Pokemon pokemon = new Pokemon();
        System.out.println("----------------------------------");
        System.out.println("            COMBATE");
        System.out.println("----------------------------------");
        System.out.println("Vas a enfrentarte a " + rival.getNombre());
        System.out.println("----------------------------------");
        pokemon = elegirPokemon();
        double multiplicador = TablaTipos.tablaTipos.obtenerMultiplicador(pokemon.getTipo1(), pokemonRival.getTipo1());
        System.out.println(rival.getNombre() + " ha elegido a " + pokemonRival.getNombre() + ".");
        System.out.println("Tu " + pokemon.getNombre() + TablaTipos.efectividadPokemon(multiplicador) + " contra el " + pokemonRival.getNombre() + " enemigo.");

        estadisticas(pokemon, pokemonRival);


    }


}
