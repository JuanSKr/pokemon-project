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
            pokemonEntrenador.setDefensaEspecial(pokemonEntrenador.getDefensaEspecial() + mejora.getDefensaEspecial());
            System.out.println("Ahora la stat de defensa esp es: " + pokemonEntrenador.getDefensaEspecial());
        } else if (mejora.getVitalidad() != 0) {
            pokemonEntrenador.setVitalidad(pokemonEntrenador.getVitalidad() + mejora.getVitalidad());
            System.out.println("Ahora la stat de vitalidad es: " + pokemonEntrenador.getVitalidad());
        } else {
            System.out.println("Error.");
        }
    }


    public static void ejecMovimiento1(Pokemon pokemonEntrenador, Pokemon pokemonAfectado) {

        Movimiento movimiento = pokemonEntrenador.getMovimiento1();
        double estaminaAtaque = (movimiento.getPotencia() / 2);
        double estMejoraEstado = (movimiento.getTurnos() * 10);

        try {

            if (movimiento.getTipoMovimiento().equals("ataque")) {
                if (pokemonEntrenador.getEstamina() < estaminaAtaque) {
                    System.out.println("No estamina");
                } else {
                    System.out.println(pokemonEntrenador.getNombre() + " ha utilizado " + movimiento.getNombreMovimiento());
                    double nuevaVida = pokemonAfectado.getVitalidad() - calcularAtaque(pokemonEntrenador, pokemonAfectado, movimiento);
                    double nuevaEstamina = Ataque.costeEstamina(pokemonEntrenador, (Ataque) movimiento);
                    pokemonAfectado.setVitalidad(nuevaVida);
                    pokemonEntrenador.setEstamina(nuevaEstamina);
                }

            } else if (movimiento.getTipoMovimiento().equals("estado")) {
                if (pokemonEntrenador.getEstamina() < estMejoraEstado) {
                    System.out.println("No estamina");
                } else {
                    ListaEstados.setEstado(pokemonAfectado, pokemonEntrenador, (Estado) movimiento);
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
                System.out.println("¡" + pokemonEntrenador + " ha fallado!");
            }

        } catch (NullPointerException e) {
            e.printStackTrace();
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
                    System.out.println(pokemonEntrenador.getNombre() + " ha utilizado " + movimiento.getNombreMovimiento());
                    double nuevaVida = pokemonRival.getVitalidad() - calcularAtaque(pokemonEntrenador, pokemonRival, movimiento);
                    double nuevaEstamina = Ataque.costeEstamina(pokemonEntrenador, (Ataque) movimiento);
                    pokemonRival.setVitalidad(nuevaVida);
                    pokemonEntrenador.setEstamina(nuevaEstamina);
                }

            } else if (movimiento.getTipoMovimiento().equals("estado")) {
                if (pokemonEntrenador.getEstamina() < estMejoraEstado) {
                    System.out.println("No estamina");
                } else {
                    ListaEstados.setEstado(pokemonEntrenador, pokemonRival, (Estado) movimiento);
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
                System.out.println("¡" + pokemonEntrenador + " ha fallado!");
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
                    System.out.println(pokemonEntrenador.getNombre() + " ha utilizado " + movimiento.getNombreMovimiento());
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
                System.out.println("¡" + pokemonEntrenador + " ha fallado!");
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
                    System.out.println(pokemonEntrenador.getNombre() + " ha utilizado " + movimiento.getNombreMovimiento());
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
                System.out.println("¡" + pokemonEntrenador + " ha fallado!");
            }

        } catch (NullPointerException e) {
            System.out.println("Error: Slot de movimiento vacío.");
        }
    }

    public static void movimientoRival(Pokemon pokemonAtacante, Pokemon pokemonAfectado, Movimiento movimiento, Label estaminaLabel) {
        double estaminaAtaque = (movimiento.getPotencia() / 2);
        double estMejoraEstado = (movimiento.getTurnos() * 10);

        try {
            if (movimiento.getTipoMovimiento().equals("ataque")) {
                if (pokemonAtacante.getEstamina() < estaminaAtaque) {
                    System.out.println(pokemonAtacante.getNombre() + " no tiene suficiente estamina. Ha decidido descansar.");
                    pokemonAtacante.setEstamina(descansar(pokemonAtacante));
                    CombateGrafico.actualizarEstamina(pokemonAtacante, estaminaLabel);
                } else {
                    System.out.println(pokemonAtacante.getNombre() + " enemigo ha utilizado " + movimiento.getNombreMovimiento());
                    double nuevaVida = pokemonAfectado.getVitalidad() - calcularAtaque(pokemonAtacante, pokemonAfectado, movimiento);
                    double nuevaEstamina = Ataque.costeEstamina(pokemonAtacante, (Ataque) movimiento);
                    pokemonAfectado.setVitalidad(nuevaVida);
                    pokemonAtacante.setEstamina(nuevaEstamina);
                }
            } else if (movimiento.getTipoMovimiento().equals("estado")) {
                if (pokemonAtacante.getEstamina() < estMejoraEstado) {
                    System.out.println(pokemonAtacante.getNombre() + " no tiene suficiente estamina. Ha decidido descansar.");
                    pokemonAtacante.setEstamina(descansar(pokemonAtacante));
                    CombateGrafico.actualizarEstamina(pokemonAtacante, estaminaLabel);
                } else {
                    System.out.println(pokemonAtacante.getNombre() + " enemigo ha utilizado " + movimiento.getNombreMovimiento());
                    ListaEstados.setEstado(pokemonAfectado, pokemonAtacante, (Estado) movimiento);
                    double nuevaEstamina = Estado.costeEstamina(pokemonAtacante, (Estado) movimiento);
                    pokemonAtacante.setEstamina(nuevaEstamina);
                }
            } else if (movimiento.getTipoMovimiento().equals("mejora")) {
                if (pokemonAtacante.getEstamina() < estMejoraEstado) {
                    System.out.println(pokemonAtacante.getNombre() + " no tiene suficiente estamina. Ha decidido descansar.");
                    pokemonAtacante.setEstamina(descansar(pokemonAtacante));
                    CombateGrafico.actualizarEstamina(pokemonAtacante, estaminaLabel);
                } else {
                    System.out.println(pokemonAtacante.getNombre() + " enemigo ha utilizado " + movimiento.getNombreMovimiento());
                    setMejora(pokemonAtacante, (Mejora) movimiento);
                    double nuevaEstamina = Mejora.costeEstamina(pokemonAtacante, (Mejora) movimiento);
                    pokemonAtacante.setEstamina(nuevaEstamina);
                }
            } else {
                System.out.println("¡" + pokemonAtacante.getNombre() + " ha fallado!");
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }


    /**
     * Son las acciones del rival (sin terminar)
     *
     * @param pokemonEntrenador
     * @param pokemonRival
     */

    public static void accionRival(Pokemon pokemonRival, Pokemon pokemonEntrenador, Label estaminaRival) {
        int random = Funcion.random(1, 4);
        Ataque ataque1 = PokemonCRUD.generarAtaque();
        Ataque ataque2 = PokemonCRUD.generarAtaque();
        Estado estado = PokemonCRUD.generarEstado();
        Mejora mejora = PokemonCRUD.generarMejora();

        pokemonRival.setMovimiento1(ataque1);
        pokemonRival.setMovimiento2(ataque2);
        pokemonRival.setMovimiento3(estado);
        pokemonRival.setMovimiento4(mejora);

        if (random == 1) {
            ataque1 = (Ataque) pokemonRival.getMovimiento1();
            movimientoRival(pokemonRival, pokemonEntrenador, pokemonRival.getMovimiento1(), estaminaRival);

        } else if (random == 2) {
            ataque2 = (Ataque) pokemonRival.getMovimiento2();
            movimientoRival(pokemonRival, pokemonEntrenador, pokemonRival.getMovimiento2(), estaminaRival);
        } else if (random == 3) {
            estado = (Estado) pokemonRival.getMovimiento3();
            movimientoRival(pokemonRival, pokemonEntrenador, pokemonRival.getMovimiento3(), estaminaRival);
        } else {
            mejora = (Mejora) pokemonRival.getMovimiento4();
            movimientoRival(pokemonRival, pokemonEntrenador, pokemonRival.getMovimiento4(), estaminaRival);
        }
    }


    /**
     * Este método suma una cantidad random de estamina (entre 10 y 35) al Pokemon
     * que se le pase por parámetro.
     *
     * @param pokemon
     */

    public static double descansar(Pokemon pokemon) {

        int addEstamina = Funcion.random(80, 120);

        double idEstamina = (pokemon.getEstamina() + addEstamina);

        return idEstamina;

    }

    public static int dineroGanado() {

        return Funcion.random(300, 500);

    }

    public static int dineroPerdido() {
        double perdido = Entrenador.getDinero();

        return (int) perdido / 3;

    }


}
