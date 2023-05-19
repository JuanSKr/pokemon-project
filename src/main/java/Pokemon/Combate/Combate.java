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

import static Pokemon.Database.PokemonCRUD.*;


public class Combate {

    static Scanner sc = new Scanner(System.in);

    protected static int pokemonElegido;
    protected static int contadorRival = 0;
    protected static int contadorEntrenador = 0;

    public static Pokemon elegirPokemon(int opcionPokemon) {

        Pokemon pokemon = Pokemon.mostrarPokemon(PokemonCRUD.idEntrenador(), opcionPokemon);
        System.out.println("¡Has seleccionado a " + pokemon.getNombre() + "!");
        return pokemon;

    }

    /**
     * Si el Pokemon del entrenador es más rápido devuelve un 1, si no, devuelve un 2.
     *
     * @param pokemonEntrenador
     * @param pokemonRival
     * @return 1 o 2
     */

    public static int calcularVelocidad(Pokemon pokemonEntrenador, Pokemon pokemonRival) {

        if (pokemonEntrenador.getVelocidad() > pokemonRival.getVelocidad()) {
            return 0;
        } else {
            return 1;
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

    public static void ejecMovimiento1(Pokemon pokemonAtacante, Pokemon pokemonAfectado) {

        Movimiento movimiento = pokemonAtacante.getMovimiento1();
        double estaminaAtaque = (movimiento.getPotencia() / 2);
        double estMejoraEstado = (movimiento.getTurnos() * 10);

        try {

            if (movimiento.getTipoMovimiento().equals("ataque")) {
                if (pokemonAtacante.getEstamina() < estaminaAtaque) {
                    System.out.println("No estamina");
                } else {
                    String mensaje = pokemonAtacante.getNombre() + " ha utilizado " + movimiento.getNombreMovimiento();
                    double nuevaVida = pokemonAfectado.getVitalidad() - calcularAtaque(pokemonAtacante, pokemonAfectado, movimiento);
                    double nuevaEstamina = Ataque.costeEstamina(pokemonAtacante, (Ataque) movimiento);
                    pokemonAfectado.setVitalidad(nuevaVida);
                    pokemonAtacante.setEstamina(nuevaEstamina);
                }

            } else if (movimiento.getTipoMovimiento().equals("estado")) {
                if (pokemonAtacante.getEstamina() < estMejoraEstado) {
                    System.out.println("No estamina");
                } else {
                    ListaEstados.setEstado(pokemonAfectado, pokemonAtacante, (Estado) movimiento);
                    double nuevaEstamina = Estado.costeEstamina(pokemonAtacante, (Estado) movimiento);
                    pokemonAtacante.setEstamina(nuevaEstamina);
                }

            } else if (movimiento.getTipoMovimiento().equals("mejora")) {
                if (pokemonAtacante.getEstamina() < estMejoraEstado) {
                    System.out.println("No estamina");
                } else {
                    setMejora(pokemonAtacante, (Mejora) movimiento);
                    double nuevaEstamina = Mejora.costeEstamina(pokemonAtacante, (Mejora) movimiento);
                    pokemonAtacante.setEstamina(nuevaEstamina);
                }

            } else {
                System.out.println("Error.");
            }

        } catch (NullPointerException e) {
            System.out.println("Error: Slot de movimiento vacío.");
        }

    }

    public static void ejecMovimiento2(Pokemon pokemonEntrenador, Pokemon pokemonRival) {

        Movimiento movimiento = pokemonEntrenador.getMovimiento2();
        double estaminaAtaque = (movimiento.getPotencia() / 2);
        double estMejoraEstado = (movimiento.getTurnos() * 10);

        try {

            if (movimiento.getTipoMovimiento().equals("ataque")) {
                if (pokemonEntrenador.getEstamina() < estaminaAtaque) {
                    System.out.println("No estamina");
                } else {
                    String mensaje = pokemonEntrenador.getNombre() + " ha utilizado " + movimiento.getNombreMovimiento();
                    double nuevaVida = pokemonRival.getVitalidad() - calcularAtaque(pokemonEntrenador, pokemonRival, movimiento);
                    double nuevaEstamina = Ataque.costeEstamina(pokemonEntrenador, (Ataque) movimiento);
                    pokemonRival.setVitalidad(nuevaVida);
                    pokemonEntrenador.setEstamina(nuevaEstamina);
                }

            } else if (movimiento.getTipoMovimiento().equals("estado")) {
                if (pokemonEntrenador.getEstamina() < estMejoraEstado) {
                    System.out.println("No estamina");
                } else {
                    ListaEstados.setEstado(pokemonRival, pokemonEntrenador, (Estado) movimiento);
                    double nuevaEstamina = Estado.costeEstamina(pokemonEntrenador, (Estado) movimiento);
                    pokemonEntrenador.setEstamina(nuevaEstamina);
                }

            } else if (movimiento.getTipoMovimiento().equals("mejora")) {
                if (pokemonEntrenador.getEstamina() < estMejoraEstado) {
                    System.out.println("No estamina");
                } else {
                    setMejora(pokemonEntrenador, (Mejora) movimiento);
                    double nuevaEstamina = Mejora.costeEstamina(pokemonEntrenador, (Mejora) movimiento);
                    pokemonEntrenador.setEstamina(nuevaEstamina);
                }

            } else {
                System.out.println("Error.");
            }

        } catch (NullPointerException e) {
            System.out.println("Error: Slot de movimiento vacío.");
        }
    }

    public static void ejecMovimiento3(Pokemon pokemonEntrenador, Pokemon pokemonRival) {

        Movimiento movimiento = pokemonEntrenador.getMovimiento3();
        double estaminaAtaque = (movimiento.getPotencia() / 2);
        double estMejoraEstado = (movimiento.getTurnos() * 10);

        try {

            if (movimiento.getTipoMovimiento().equals("ataque")) {
                if (pokemonEntrenador.getEstamina() < estaminaAtaque) {
                    System.out.println("No estamina");
                } else {
                    String mensaje = pokemonEntrenador.getNombre() + " ha utilizado " + movimiento.getNombreMovimiento();
                    double nuevaVida = pokemonRival.getVitalidad() - calcularAtaque(pokemonEntrenador, pokemonRival, movimiento);
                    double nuevaEstamina = Ataque.costeEstamina(pokemonEntrenador, (Ataque) movimiento);
                    pokemonRival.setVitalidad(nuevaVida);
                    pokemonEntrenador.setEstamina(nuevaEstamina);
                }

            } else if (movimiento.getTipoMovimiento().equals("estado")) {
                if (pokemonEntrenador.getEstamina() < estMejoraEstado) {
                    System.out.println("No estamina");
                } else {
                    ListaEstados.setEstado(pokemonRival, pokemonEntrenador, (Estado) movimiento);
                    double nuevaEstamina = Estado.costeEstamina(pokemonEntrenador, (Estado) movimiento);
                    pokemonEntrenador.setEstamina(nuevaEstamina);
                }

            } else if (movimiento.getTipoMovimiento().equals("mejora")) {
                if (pokemonEntrenador.getEstamina() < estMejoraEstado) {
                    System.out.println("No estamina");
                } else {
                    setMejora(pokemonEntrenador, (Mejora) movimiento);
                    double nuevaEstamina = Mejora.costeEstamina(pokemonEntrenador, (Mejora) movimiento);
                    pokemonEntrenador.setEstamina(nuevaEstamina);
                }

            } else {
                System.out.println("Error.");
            }

        } catch (NullPointerException e) {
            System.out.println("Error: Slot de movimiento vacío.");
        }
    }

    public static void ejecMovimiento4(Pokemon pokemonEntrenador, Pokemon pokemonRival) {

        Movimiento movimiento = pokemonEntrenador.getMovimiento4();
        double estaminaAtaque = (movimiento.getPotencia() / 2);
        double estMejoraEstado = (movimiento.getTurnos() * 10);
        int contador = 0;
        try {

            if (movimiento.getTipoMovimiento().equals("ataque")) {
                if (pokemonEntrenador.getEstamina() < estaminaAtaque) {
                    System.out.println("No estamina");
                } else {
                    String mensaje = pokemonEntrenador.getNombre() + " ha utilizado " + movimiento.getNombreMovimiento();
                    double nuevaVida = pokemonRival.getVitalidad() - calcularAtaque(pokemonEntrenador, pokemonRival, movimiento);
                    double nuevaEstamina = Ataque.costeEstamina(pokemonEntrenador, (Ataque) movimiento);
                    pokemonRival.setVitalidad(nuevaVida);
                    pokemonEntrenador.setEstamina(nuevaEstamina);
                }

            } else if (movimiento.getTipoMovimiento().equals("estado")) {
                if (pokemonEntrenador.getEstamina() < estMejoraEstado) {
                    System.out.println("No estamina");
                } else {
                    ListaEstados.setEstado(pokemonRival, pokemonEntrenador, (Estado) movimiento);
                    double nuevaEstamina = Estado.costeEstamina(pokemonEntrenador, (Estado) movimiento);
                    pokemonEntrenador.setEstamina(nuevaEstamina);
                }

            } else if (movimiento.getTipoMovimiento().equals("mejora")) {
                if (pokemonEntrenador.getEstamina() < estMejoraEstado) {
                    System.out.println("No estamina");
                } else {
                    setMejora(pokemonEntrenador, (Mejora) movimiento);
                    double nuevaEstamina = Mejora.costeEstamina(pokemonEntrenador, (Mejora) movimiento);
                    pokemonEntrenador.setEstamina(nuevaEstamina);
                }

            } else {
                System.out.println("Error.");
            }

        } catch (NullPointerException e) {
            System.out.println("Error: Slot de movimiento vacío.");
        }
    }

    public static void movimientoRival1(Pokemon pokemonAtacante, Pokemon pokemonAfectado, Movimiento movimiento) {

        double estaminaAtaque = (movimiento.getPotencia() / 2);
        double estMejoraEstado = (movimiento.getTurnos() * 10);

        try {

            if (movimiento.getTipoMovimiento().equals("ataque")) {
                if (pokemonAtacante.getEstamina() < estaminaAtaque) {
                    System.out.println("No estamina");
                } else {
                    String mensaje = pokemonAtacante.getNombre() + " ha utilizado " + movimiento.getNombreMovimiento();
                    double nuevaVida = pokemonAfectado.getVitalidad() - calcularAtaque(pokemonAtacante, pokemonAfectado, movimiento);
                    double nuevaEstamina = Ataque.costeEstamina(pokemonAtacante, (Ataque) movimiento);
                    pokemonAfectado.setVitalidad(nuevaVida);
                    pokemonAtacante.setEstamina(nuevaEstamina);
                }

            } else if (movimiento.getTipoMovimiento().equals("estado")) {
                if (pokemonAtacante.getEstamina() < estMejoraEstado) {
                    System.out.println("No estamina");
                } else {
                    ListaEstados.setEstado(pokemonAfectado, pokemonAtacante, (Estado) movimiento);
                    double nuevaEstamina = Estado.costeEstamina(pokemonAtacante, (Estado) movimiento);
                    pokemonAtacante.setEstamina(nuevaEstamina);
                }

            } else if (movimiento.getTipoMovimiento().equals("mejora")) {
                if (pokemonAtacante.getEstamina() < estMejoraEstado) {
                    System.out.println("No estamina");
                } else {
                    setMejora(pokemonAtacante, (Mejora) movimiento);
                    double nuevaEstamina = Mejora.costeEstamina(pokemonAtacante, (Mejora) movimiento);
                    pokemonAtacante.setEstamina(nuevaEstamina);
                }

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
     * @param pokemonEntrenador
     * @param pokemonRival
     */

    public static void accionRival(Pokemon pokemonRival, Pokemon pokemonEntrenador) {
        int random = Funcion.random(1, 4);
        setMovimiento(pokemonRival);

        if (random == 1) {
//                Ataque ataque = (Ataque) pokemonRival.getMovimiento1();
//                System.out.println(pokemonRival.getNombre() + " enemigo ha utilizado " + ataque.getNombreMovimiento());
            movimientoRival1(pokemonRival, pokemonEntrenador, pokemonRival.getMovimiento1());

        } else if (random == 2) {
//                Ataque ataque = (Ataque) pokemonRival.getMovimiento2();
//                System.out.println(pokemonRival.getNombre() + " enemigo ha utilizado " + ataque.getNombreMovimiento());
            movimientoRival1(pokemonRival, pokemonEntrenador, pokemonRival.getMovimiento2());
        } else if (random == 3) {
//                Estado estado = (Estado) pokemonRival.getMovimiento3();
//                System.out.println(pokemonRival.getNombre() + " enemigo ha utilizado " + estado.getNombreMovimiento());
            movimientoRival1(pokemonRival, pokemonEntrenador, pokemonRival.getMovimiento3());
        } else {
//                Mejora mejora = (Mejora) pokemonRival.getMovimiento4();
//                System.out.println(pokemonRival.getNombre() + " enemigo ha utilizado " + mejora.getNombreMovimiento());
            movimientoRival1(pokemonRival, pokemonEntrenador, pokemonRival.getMovimiento4());
        }
    }


    /**
     * Hacer un setMovimiento para el Pokemon Rival.
     *
     * @param pokemon
     */

    public static void setMovimiento(Pokemon pokemon) { //ERROR

        Ataque ataque1 = PokemonCRUD.generarAtaque();
        Ataque ataque2 = PokemonCRUD.generarAtaque();
        Estado estado = PokemonCRUD.generarEstado();
        Mejora mejora = PokemonCRUD.generarMejora();

        pokemon.setMovimiento1(ataque1);
        pokemon.setMovimiento2(ataque2);
        pokemon.setMovimiento3(estado);
        pokemon.setMovimiento4(mejora);

        System.out.println("Mov1: " + ataque1.getTipoMovimiento());
        System.out.println("Mov2: " + ataque2.getTipoMovimiento());
        System.out.println("Mov3: " + estado.getTipoMovimiento());
        System.out.println("Mov4: " + mejora.getTipoMovimiento());

    }

    public static boolean rivalDebilitado(Rival rival, Pokemon pokemonRival) {
        if (contadorRival < 6) {
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
                pokemonEntrenador = elegirPokemon(1);
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

        int addEstamina = Funcion.random(60, 90);

        double idEstamina = (pokemon.getEstamina() + addEstamina);

        return idEstamina;

    }


}
