package Pokemon.Combate;

import Pokemon.Combate.Movimientos.Ataque;
import Pokemon.Combate.Movimientos.Estado;
import Pokemon.Combate.Movimientos.Mejora;
import Pokemon.Combate.Movimientos.Movimiento;
import Pokemon.Database.PokemonCRUD;
import Pokemon.Funcionalidad.Funcion;
import Pokemon.Pokemon.Pokemon;

import java.util.Scanner;


public class Combate {

    static Scanner sc = new Scanner(System.in);

    static int pokemonElegido;
    static int contadorRival = 0;

    public static Pokemon elegirPokemon() {
        //El sout del equipo del Rival está aquí dentro, es equipoRival().

        System.out.println("¿Con qué Pokemon quieres empezar?");
        Pokemon pokemon = Pokemon.mostrarPokemon();
        System.out.println("----------------------------------");
        System.out.println("¡Has seleccionado a " + pokemon.getNombre() + "!");
        return pokemon;

    }

    /**
     * Son las acciones del entrenador (sin terminar)
     * @param pokemonEntrenador
     * @param pokemonRival
     */

    public static void accionEntrenador(Pokemon pokemonEntrenador, Pokemon pokemonRival) {

        Movimiento movimiento;

        pokemonEntrenador = PokemonCRUD.getPokemon(pokemonElegido);

        System.out.println("----------------------------------");
        System.out.println("      ¿Que quieres hacer?");
        System.out.println("----------------------------------");
        System.out.println("1. Atacar");
        System.out.println("2. Cambiar Pokemon");
        System.out.println("3. Mochila");
        System.out.println("4. Rendirse");
        System.out.println("5. Descansar");
        System.out.println("----------------------------------");
        System.out.print("Elige una opción: ");
        int opcion = sc.nextInt();

        switch (opcion) {

            case 1:
                Movimiento movimiento1 = pokemonEntrenador.getMovimiento1();
                Movimiento movimiento2 = pokemonEntrenador.getMovimiento2();
                Movimiento movimiento3 = pokemonEntrenador.getMovimiento3();
                Movimiento movimiento4 = pokemonEntrenador.getMovimiento4();
                System.out.println("----------------------------------");
                System.out.println("     Movimientos de " + pokemonEntrenador.getNombre());
                System.out.println("----------------------------------");
                System.out.println("1. " + movimiento1.getNombreMovimiento());
                if (movimiento2 != null) {
                    System.out.println("2. " + movimiento2.getNombreMovimiento());
                }
                if (movimiento3 != null) {
                    System.out.println("2. " + movimiento3.getNombreMovimiento());
                }
                if (movimiento4 != null) {
                    System.out.println("4. " + movimiento4.getNombreMovimiento());
                }
                System.out.println("----------------------------------");
                System.out.print("Elige una opción: ");
                int opcionMovimientos = sc.nextInt();

                switch (opcionMovimientos) {

                    case 1:
                        System.out.println(pokemonEntrenador.getNombre() + " ha utilizado " + movimiento1.getNombreMovimiento());
                        break;

                    case 2:
                        System.out.println(pokemonEntrenador.getNombre() + " ha utilizado " + movimiento2.getNombreMovimiento());
                        break;

                    case 3:
                        System.out.println(pokemonEntrenador.getNombre() + " ha utilizado " + movimiento3.getNombreMovimiento());
                        break;

                    case 4:
                        System.out.println(pokemonEntrenador.getNombre() + " ha utilizado " + movimiento4.getNombreMovimiento());
                        break;

                }

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

    /**
     * Son las acciones del rival (sin terminar)
     * @param rival
     * @param pokemonEntrenador
     * @param pokemonRival
     */

    public static void accionRival(Rival rival, Pokemon pokemonEntrenador, Pokemon pokemonRival) {
        int random = Funcion.random(1, 4);
        setMovimiento(pokemonRival);

        if (random == 1) {
            Ataque ataque = (Ataque) pokemonRival.getMovimiento1();
            System.out.println(pokemonRival.getNombre() + " enemigo ha utilizado " + ataque.getNombreMovimiento());

        } else if (random == 2) {
            Ataque ataque = (Ataque) pokemonRival.getMovimiento2();
            System.out.println(pokemonRival.getNombre() + " enemigo ha utilizado " + ataque.getNombreMovimiento());
        } else if (random == 3) {
            Estado estado = (Estado) pokemonRival.getMovimiento3();
            System.out.println(pokemonRival.getNombre() + " enemigo ha utilizado " + estado.getNombreMovimiento());
        } else {
            Mejora mejora = (Mejora) pokemonRival.getMovimiento4();
            System.out.println(pokemonRival.getNombre() + " enemigo ha utilizado " + mejora.getNombreMovimiento());
        }

    }

    /**
     * Hacer un setMovimiento para el Pokemon Rival.
     * @param pokemon
     */

    public static void setMovimiento(Pokemon pokemon) {

        Ataque ataque1 = PokemonCRUD.generarAtaque();
        Ataque ataque2 = PokemonCRUD.generarAtaque();
        Estado estado = PokemonCRUD.generarEstado();
        Mejora mejora = PokemonCRUD.generarMejora();

        pokemon.setMovimiento1(ataque1);
        pokemon.setMovimiento2(ataque2);
        pokemon.setMovimiento3(estado);
        pokemon.setMovimiento4(mejora);

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

    /**
     * Selecciona un rival aleatorio al que enfrentarte y saca un pokemon random.
     * También llama al método elegirPokemon para que el entrenador elija su Pokemon.
     * Además, guarda la ID en una variable estática y después es utilazada en el método accionEntrenador().
     * @param rival
     * @param pokemonRival
     * @param pokemonEntrenador
     */

    public static void iniciarCombate(Rival rival, Pokemon pokemonRival, Pokemon pokemonEntrenador) {

        System.out.println("----------------------------------");
        System.out.println("            COMBATE");
        System.out.println("----------------------------------");
        System.out.println("Vas a enfrentarte a " + rival.getNombre());
        System.out.println("----------------------------------");
        pokemonEntrenador = elegirPokemon();
        pokemonElegido = pokemonEntrenador.getId();
        double multiplicador = TablaTipos.tablaTipos.obtenerMultiplicador(pokemonEntrenador.getTipo1(), pokemonRival.getTipo1());
        System.out.println(rival.getNombre() + " ha elegido a " + pokemonRival.getNombre() + ".");
        System.out.println("Tu " + pokemonEntrenador.getNombre() + " " + TablaTipos.efectividadPokemon(multiplicador) + " contra el " + pokemonRival.getNombre() + " enemigo.");
        estadisticas(pokemonEntrenador, pokemonRival);


    }

    public static void rivalDebilitado(Rival rival, Pokemon pokemonRival) {

        if(contadorRival <= 6) {
            if (pokemonRival.getVitalidad() == 0) {
                System.out.println(pokemonRival.getNombre() + " se ha debilitado.");
                pokemonRival = Rival.pokemonRival();
                System.out.println(rival.getNombre() + " utilizará a " + pokemonRival.getNombre());
            }
        } else {
            System.out.println("Has ganado");
        }

    }


    public static void main(String[] args) {

        Rival rival = Rival.generarRival();
        Pokemon pokemonRival = Rival.pokemonRival();
        Pokemon pokemonEntrenador = new Pokemon();

        iniciarCombate(rival, pokemonRival, pokemonEntrenador);
        accionEntrenador(pokemonEntrenador, pokemonRival);
        accionRival(rival, pokemonEntrenador, pokemonRival);

        System.out.println("*******************************");

        accionEntrenador(pokemonEntrenador, pokemonRival);

        pokemonRival.setVitalidad(0);

        accionRival(rival, pokemonEntrenador, pokemonRival);

    }




}
