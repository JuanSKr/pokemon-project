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
import javafx.scene.control.Label;

import java.util.Scanner;

import static Pokemon.Database.PokemonCRUD.generarAtaque;
import static Pokemon.Database.PokemonCRUD.generarPokemon;


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

    public static double calcularAtaque(Pokemon pokemonAtacante, Pokemon pokemonDefensor, Movimiento ataque) {

        double ataqueStat = pokemonAtacante.getAtaque();
        double defensaStat = pokemonDefensor.getDefensa();
        double potencia = ataque.getPotencia();

        double multiplicador = TablaTipos.obtenerMultiplicador(pokemonAtacante.getTipo1(), pokemonDefensor.getTipo1());


        double calculado1 = (potencia * ataqueStat / defensaStat) / 2;

        double calculoFinal = (calculado1 * multiplicador);

        return calculoFinal;

    }

    /**
     * Este método comprueba que el valor del movimiento no sea 0. Si es 0 no entra, si es distinto de 0 si.
     * Se hace así porque en la tabla Movimiento el valor en lugar de nulo es 0.
     *
     * @param pokemonEntrenador
     * @param mejora
     */

    public static void setMejora(Pokemon pokemonEntrenador, Mejora mejora) {

        if (mejora.getAtaque() != 0) {
            pokemonEntrenador.setAtaque(pokemonEntrenador.getAtaque() + mejora.getAtaque());
            System.out.println("Ahora la stat de ataque es: " + pokemonEntrenador.getAtaque());
        } else if (mejora.getDefensa() != 0) {
            pokemonEntrenador.setDefensa(pokemonEntrenador.getDefensa() + mejora.getDefensa());
            System.out.println("Ahora la stat de defensa es: " + pokemonEntrenador.getDefensa());
        } else if (mejora.getAtaqueEspecial() != 0) {
            pokemonEntrenador.setAtaqueEspecial(pokemonEntrenador.getAtaqueEspecial() + mejora.getAtaqueEspecial());
            System.out.println("Ahora la stat de ataque esp es: " + pokemonEntrenador.getAtaqueEspecial());
        } else if (mejora.getDefensaEspecial() != 0) {
            pokemonEntrenador.setAtaque(pokemonEntrenador.getDefensaEspecial() + mejora.getDefensaEspecial());
            System.out.println("Ahora la stat de defensa esp es: " + pokemonEntrenador.getDefensaEspecial());
        } else if (mejora.getVitalidad() != 0) {
            pokemonEntrenador.setAtaque(pokemonEntrenador.getVitalidad() + mejora.getVitalidad());
            System.out.println("Ahora la stat de vitalidad es: " + pokemonEntrenador.getVitalidad());

        } else {
            System.out.println("Error.");
        }

    }

    public static void ejecMovimiento1(Pokemon pokemonEntrenador, Pokemon pokemonRival, Label estaminaEntrenador) {

        Movimiento movimiento = pokemonEntrenador.getMovimiento1();
        double costeEstamnia = (movimiento.getPotencia() / 2);

        try {

            if (movimiento.getTipoMovimiento().equals("ataque")) {
                if(pokemonEntrenador.getEstamina() < costeEstamnia) {
                    System.out.println("No estamina");
                } else {
                    String mensaje = pokemonEntrenador.getNombre() + " ha utilizado " + movimiento.getNombreMovimiento();
                    double nuevaVida = pokemonRival.getVitalidad() - calcularAtaque(pokemonEntrenador, pokemonRival, movimiento);
                    double nuevaEstamina = Ataque.costeEstamina(pokemonEntrenador, (Ataque) movimiento);
                    pokemonRival.setVitalidad(nuevaVida);
                    pokemonEntrenador.setEstamina(nuevaEstamina);
                }

            } else if (movimiento.getTipoMovimiento().equals("estado")) {

                ListaEstados.setEstado(pokemonEntrenador, pokemonRival, (Estado) movimiento);
                //Coste estamina

            } else if (movimiento.getTipoMovimiento().equals("mejora")) {

                setMejora(pokemonEntrenador, (Mejora) movimiento);
                //Coste estamina

            } else {
                System.out.println("Error.");
            }

        } catch (NullPointerException e) {
            System.out.println("Error: Slot de movimiento vacío.");
        }

    }

    public static void ejecMovimiento2(Pokemon pokemonEntrenador, Pokemon pokemonRival) {

        Movimiento movimiento = pokemonEntrenador.getMovimiento2();

        try {

            if (movimiento.getTipoMovimiento().equals("ataque")) {

                System.out.println(pokemonEntrenador.getNombre() + " ha utilizado " + movimiento.getNombreMovimiento());
                double nuevaVida = pokemonRival.getVitalidad() - calcularAtaque(pokemonEntrenador, pokemonRival, movimiento);

                pokemonRival.setVitalidad(nuevaVida);

            } else if (movimiento.getTipoMovimiento().equals("estado")) {

                ListaEstados.setEstado(pokemonEntrenador, pokemonRival, (Estado) movimiento);

            } else if (movimiento.getTipoMovimiento().equals("mejora")) {

                setMejora(pokemonEntrenador, (Mejora) movimiento);

            } else {
                System.out.println("Error.");
            }

        } catch (NullPointerException e) {
            System.out.println("Error: Slot de movimiento vacío.");
        }
    }

    public static void ejecMovimiento3(Pokemon pokemonEntrenador, Pokemon pokemonRival) {

        Movimiento movimiento = pokemonEntrenador.getMovimiento3();

        try {

            if (movimiento.getTipoMovimiento().equals("ataque")) {

                System.out.println(pokemonEntrenador.getNombre() + " ha utilizado " + movimiento.getNombreMovimiento());
                double nuevaVida = pokemonRival.getVitalidad() - calcularAtaque(pokemonEntrenador, pokemonRival, movimiento);

                pokemonRival.setVitalidad(nuevaVida);

            } else if (movimiento.getTipoMovimiento().equals("estado")) {

                ListaEstados.setEstado(pokemonEntrenador, pokemonRival, (Estado) movimiento);

            } else if (movimiento.getTipoMovimiento().equals("mejora")) {

                setMejora(pokemonEntrenador, (Mejora) movimiento);

            } else {
                System.out.println("Error.");
            }

        } catch (NullPointerException e) {
            System.out.println("Error: Slot de movimiento vacío.");
        }
    }

    public static void ejecMovimiento4(Pokemon pokemonEntrenador, Pokemon pokemonRival) {

        Movimiento movimiento = pokemonEntrenador.getMovimiento4();

        try {

            if (movimiento.getTipoMovimiento().equals("ataque")) {

                System.out.println(pokemonEntrenador.getNombre() + " ha utilizado " + movimiento.getNombreMovimiento());
                double nuevaVida = pokemonRival.getVitalidad() - calcularAtaque(pokemonEntrenador, pokemonRival, movimiento);

                pokemonRival.setVitalidad(nuevaVida);

            } else if (movimiento.getTipoMovimiento().equals("estado")) {

                ListaEstados.setEstado(pokemonEntrenador, pokemonRival, (Estado) movimiento);

            } else if (movimiento.getTipoMovimiento().equals("mejora")) {

                setMejora(pokemonEntrenador, (Mejora) movimiento);

            } else {
                System.out.println("Error.");
            }

        } catch (NullPointerException e) {
            System.out.println("Error: Slot de movimiento vacío.");
        }
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

    public static double descansar(Pokemon pokemon) {

        int addEstamina = Funcion.random(60, 100);

        double idEstamina = (pokemon.getEstamina() + addEstamina);

        return idEstamina;

    }

    public static void main(String[] args) {

        Pokemon atacante = generarPokemon();

        Pokemon defensor = generarPokemon();

        Ataque ataque = generarAtaque();

        System.out.println(calcularAtaque(atacante, defensor, ataque));

    }


}
