package Pokemon.Combate;

import Pokemon.Combate.Movimientos.Ataque;
import Pokemon.Combate.Movimientos.Estado;
import Pokemon.Combate.Movimientos.Mejora;
import Pokemon.Combate.Movimientos.Movimiento;
import Pokemon.Database.PokemonCRUD;
import Pokemon.Entrenador.Entrenador;
import Pokemon.Funcionalidad.Funcion;
import Pokemon.Pokemon.ListaEstados;
import Pokemon.Pokemon.Pokemon;

import java.util.Scanner;


public class Combate {

    static Scanner sc = new Scanner(System.in);

    static int pokemonElegido;
    static int contadorRival = 0;
    static int contadorEntrenador = 0;

    public static Pokemon elegirPokemon() {

        Entrenador.setId(6);
        //El sout del equipo del Rival está aquí dentro, es equipoRival().

        System.out.println("¿Con qué Pokemon quieres empezar?");
        Pokemon pokemon = Pokemon.mostrarPokemon(Entrenador.getId());
        System.out.println("----------------------------------");
        System.out.println("¡Has seleccionado a " + pokemon.getNombre() + "!");
        return pokemon;

    }

    /**
     * Son las acciones del entrenador (sin terminar)
     *
     * @param pokemonEntrenador
     * @param pokemonRival
     */

    public static void accionEntrenador(Pokemon pokemonEntrenador, Pokemon pokemonRival) {

        Movimiento movimiento;
        Double calculoAtaque;

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
                        calculoAtaque = pokemonRival.getVitalidad() - movimiento1.getPotencia();
                        pokemonRival.setVitalidad(calculoAtaque);
                        break;

                    case 2:
                        System.out.println(pokemonEntrenador.getNombre() + " ha utilizado " + movimiento2.getNombreMovimiento());
                        calculoAtaque = pokemonRival.getVitalidad() - movimiento1.getPotencia();
                        pokemonRival.setVitalidad(calculoAtaque);
                        break;

                    case 3:
                        System.out.println(pokemonEntrenador.getNombre() + " ha utilizado " + movimiento3.getNombreMovimiento());
                        calculoAtaque = pokemonRival.getVitalidad() - movimiento1.getPotencia();
                        pokemonRival.setVitalidad(calculoAtaque);
                        break;

                    case 4:
                        System.out.println(pokemonEntrenador.getNombre() + " ha utilizado " + movimiento4.getNombreMovimiento());
                        calculoAtaque = pokemonRival.getVitalidad() - movimiento1.getPotencia();
                        pokemonRival.setVitalidad(calculoAtaque);
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
                descansar(pokemonEntrenador);
                break;
        }

    }




    public static void ejecMovimiento1(Pokemon pokemonEntrenador, Pokemon pokemonRival) {

        Movimiento movimiento = pokemonEntrenador.getMovimiento1();

        if (movimiento.getTipoMovimiento().equals("ataque")) {

            double multiplicador = TablaTipos.obtenerMultiplicador(pokemonEntrenador.getTipo1(), pokemonRival.getTipo1());

            double potenciaMultiplicada = multiplicador * movimiento.getPotencia();

            System.out.println(pokemonEntrenador.getNombre() + " ha utilizado " + movimiento.getNombreMovimiento());
            double calculoAtaque = pokemonRival.getVitalidad() - potenciaMultiplicada;
            pokemonRival.setVitalidad(calculoAtaque);

        } else if (movimiento.getTipoMovimiento().equals("estado")) {


        } else if (movimiento.getTipoMovimiento().equals("mejora")) {

        } else {
            System.out.println("Error.");
        }

    }

    public static void ejecMovimiento2(Pokemon pokemonEntrenador, Pokemon pokemonRival) {

        Movimiento movimiento = pokemonEntrenador.getMovimiento2();

        double multiplicador = TablaTipos.obtenerMultiplicador(pokemonEntrenador.getTipo1(), pokemonRival.getTipo1());

        double potenciaMultiplicada = multiplicador * movimiento.getPotencia();

        System.out.println(pokemonEntrenador.getNombre() + " ha utilizado " + movimiento.getNombreMovimiento());
        double calculoAtaque = pokemonRival.getVitalidad() - potenciaMultiplicada;
        pokemonRival.setVitalidad(calculoAtaque);
    }

    public static void ejecMovimiento3(Pokemon pokemonEntrenador, Pokemon pokemonRival) {

        Movimiento movimiento = pokemonEntrenador.getMovimiento3();

        double multiplicador = TablaTipos.obtenerMultiplicador(pokemonEntrenador.getTipo1(), pokemonRival.getTipo1());

        double potenciaMultiplicada = multiplicador * movimiento.getPotencia();

        System.out.println(pokemonEntrenador.getNombre() + " ha utilizado " + movimiento.getNombreMovimiento());
        double calculoAtaque = pokemonRival.getVitalidad() - potenciaMultiplicada;
        pokemonRival.setVitalidad(calculoAtaque);
    }

    public static void ejecMovimiento4(Pokemon pokemonEntrenador, Pokemon pokemonRival) {

        Movimiento movimiento = pokemonEntrenador.getMovimiento4();


        double multiplicador = TablaTipos.obtenerMultiplicador(pokemonEntrenador.getTipo1(), pokemonRival.getTipo1());

        double potenciaMultiplicada = multiplicador * movimiento.getPotencia();

        System.out.println(pokemonEntrenador.getNombre() + " ha utilizado " + movimiento.getNombreMovimiento());
        double calculoAtaque = pokemonRival.getVitalidad() - potenciaMultiplicada;
        pokemonRival.setVitalidad(calculoAtaque);
    }

    /**
     * Son las acciones del rival (sin terminar)
     *
     * @param rival
     * @param pokemonEntrenador
     * @param pokemonRival
     */

    public static void accionRival(Rival rival, Pokemon pokemonEntrenador, Pokemon pokemonRival) {
        int random = Funcion.random(1, 4);
        setMovimiento(pokemonRival);

        if (rivalDebilitado(rival, pokemonRival)) {

            if (random == 1) {
                Ataque ataque = (Ataque) pokemonRival.getMovimiento1();
                System.out.println(pokemonRival.getNombre() + " enemigo ha utilizado " + ataque.getNombreMovimiento());
                pokemonEntrenador.setVitalidad(setAtaque(ataque, pokemonEntrenador));

            } else if (random == 2) {
                Ataque ataque = (Ataque) pokemonRival.getMovimiento2();
                System.out.println(pokemonRival.getNombre() + " enemigo ha utilizado " + ataque.getNombreMovimiento());
                pokemonEntrenador.setVitalidad(setAtaque(ataque, pokemonEntrenador));
            } else if (random == 3) {
                Estado estado = (Estado) pokemonRival.getMovimiento3();
                System.out.println(pokemonRival.getNombre() + " enemigo ha utilizado " + estado.getNombreMovimiento());
            } else {
                Mejora mejora = (Mejora) pokemonRival.getMovimiento4();
                System.out.println(pokemonRival.getNombre() + " enemigo ha utilizado " + mejora.getNombreMovimiento());
            }

        }

    }

    public static double setAtaque(Ataque ataque, Pokemon pokemonAfectado) {

        double pokemonVida = pokemonAfectado.getVitalidad();

        double potenciaAtaque = ataque.getPotencia();

        return pokemonVida - potenciaAtaque;


    }

    /**
     * Hacer un setMovimiento para el Pokemon Rival.
     *
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
     * Selecciona un rival aleatorio al que enfrentarte y saca un pokemon random.
     * También llama al método elegirPokemon para que el entrenador elija su Pokemon.
     * Además, guarda la ID en una variable estática y después es utilazada en el método accionEntrenador().
     *
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
        pokemonElegido = pokemonEntrenador.getId();
        double multiplicador = TablaTipos.obtenerMultiplicador(pokemonEntrenador.getTipo1(), pokemonRival.getTipo1());
        System.out.println(rival.getNombre() + " ha elegido a " + pokemonRival.getNombre() + ".");
        System.out.println("Tu " + pokemonEntrenador.getNombre() + " " + TablaTipos.efectividadPokemon(multiplicador) + " contra el " + pokemonRival.getNombre() + " enemigo.");


    }

    public static boolean rivalDebilitado(Rival rival, Pokemon pokemonRival) {

        if (contadorRival <= 6) {
            if (pokemonRival.getVitalidad() == 0) {
                System.out.println(pokemonRival.getNombre() + " se ha debilitado.");
                contadorRival++;
                pokemonRival = Rival.pokemonRival();
                System.out.println(rival.getNombre() + " utilizará a " + pokemonRival.getNombre());
                return false;
            }
        } else {
            System.out.println("Has ganado");
            return true;
        }
        return false;
    }

    public static boolean pokemonDebilitado(Pokemon pokemonEntrenador) {

        if (contadorEntrenador <= 6) {
            if (pokemonEntrenador.getVitalidad() == 0) {
                System.out.println(pokemonEntrenador.getNombre() + " se ha debilitado.");
                contadorEntrenador++;
                pokemonEntrenador = elegirPokemon();
                System.out.println(Entrenador.getNombre() + " utilizará a " + pokemonEntrenador.getNombre());
                return false;
            }
        } else {
            System.out.println("Has perdido");
            return true;
        }
        return false;

    }

    /**
     * Este método suma una cantidad random de estamina (entre 10 y 35) al Pokemon
     * que se le pase por parámetro.
     *
     * @param pokemon
     */

    public static void descansar(Pokemon pokemon) {

        int addEstamina = Funcion.random(10, 35);

        double idEstamina = pokemon.getEstamina();

        pokemon.setEstamina(idEstamina + addEstamina);

    }

    public static void main(String[] args) {

        Movimiento movimiento = PokemonCRUD.generarMovimiento();

        System.out.println(movimiento.getTipo());


    }


}
