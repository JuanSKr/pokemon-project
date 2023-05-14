package Pokemon.Pokemon;

import Pokemon.Combate.Movimientos.Estado;
import Pokemon.Database.PokemonCRUD;
import Pokemon.Funcionalidad.Funcion;

public enum ListaEstados {

    CONFUSO, MALDITO, ENAMORADO, DRENADO, PARALIZADO, ENVENENADO, CONGELADO, DORMIDO, QUEMADO;

    public static void setEstado(Pokemon pokemonAfectado, Pokemon pokemonAtacante, Estado movimiento) {


        if (movimiento.getEstado().equals(ListaEstados.CONFUSO)) {
            pokemonAfectado.setEstado(ListaEstados.CONFUSO);
            setConfuso(pokemonAfectado, movimiento);

        } else if (movimiento.getEstado().equals(ListaEstados.MALDITO)) {
            pokemonAfectado.setEstado(ListaEstados.MALDITO);
            setMaldito(pokemonAfectado, movimiento);

        } else if (movimiento.getEstado().equals(ListaEstados.ENAMORADO)) {
            pokemonAfectado.setEstado(ListaEstados.ENAMORADO);
            setEnamorado(pokemonAfectado, movimiento);

        } else if (movimiento.getEstado().equals(ListaEstados.DRENADO)) {
            pokemonAfectado.setEstado(ListaEstados.DRENADO);
            setDrenaje(pokemonAfectado, pokemonAtacante, movimiento);

        } else if (movimiento.getEstado().equals(ListaEstados.ENVENENADO)) {
            pokemonAfectado.setEstado(ListaEstados.ENVENENADO);
            setEnvenenado(pokemonAfectado, movimiento);
        }

    }

    /**
     * Este método aplica el estado CONFUSO a un Pokemon. Los turnos se deberán controlar aparte.
     *
     * @param pokemonAfectado
     * @param movimiento
     */

    public static void setConfuso(Pokemon pokemonAfectado, Estado movimiento) {

        double random = Funcion.random(1, 3);

        if (random == 1) {
            double herida = Funcion.random(10, 20);
            pokemonAfectado.setVitalidad(pokemonAfectado.getVitalidad() - herida);
        }
    }

    /**
     * Este método aplica el estado MALDITO a un Pokemon. Los turnos se deberán controlar aparte.
     *
     * @param pokemonAfectado
     * @param movimiento
     */

    public static void setMaldito(Pokemon pokemonAfectado, Estado movimiento) {

        double vidaPerdida = pokemonAfectado.getVitalidad() / 4;
        double cambiarVida = pokemonAfectado.getVitalidad() - vidaPerdida;

        pokemonAfectado.setVitalidad(cambiarVida);

    }

    /**
     * Este método aplica el estado ENAMORADO a un Pokemon. Los turnos se deberán controlar aparte.
     *
     * @param pokemonAfectado
     * @param movimiento
     */

    public static void setEnamorado(Pokemon pokemonAfectado, Estado movimiento) {

        double random = Funcion.random(1, 4);

        if (random == 1) {
            System.out.println("El " + pokemonAfectado.getNombre() + " no ha atacado. Está enamorado.");
        }

    }

    /**
     * Este método aplica el estado DRENAJE a un Pokemon. Los turnos se deberán controlar aparte.
     *
     * @param pokemonAfectado
     * @param pokemonAtacante
     * @param movimiento
     */


    public static void setDrenaje(Pokemon pokemonAfectado, Pokemon pokemonAtacante, Estado movimiento) {

        double drenaje = (pokemonAfectado.getVitalidad() * 0.15);

        pokemonAfectado.setVitalidad(pokemonAfectado.getVitalidad() - drenaje);
        pokemonAtacante.setVitalidad(pokemonAtacante.getVitalidad() + drenaje);

        System.out.println(pokemonAtacante.getNombre() + " drenó " + drenaje + " puntos de vida de " + pokemonAfectado.getNombre());
        System.out.println(pokemonAtacante.getNombre() + " ahora tiene " + pokemonAtacante.getVitalidad() + " puntos de vida");
        System.out.println(pokemonAfectado.getNombre() + " ahora tiene " + pokemonAfectado.getVitalidad() + " puntos de vida");

    }

    /**
     * Este método aplica el estado ENVENENADO a un Pokemon. Los turnos se deberán controlar aparte.
     *
     * @param pokemonAfectado
     * @param movimiento
     */

    public static void setEnvenenado(Pokemon pokemonAfectado, Estado movimiento) {

        double veneno = pokemonAfectado.getVitalidad() * 0.125;

        pokemonAfectado.setVitalidad(pokemonAfectado.getVitalidad() - veneno);
        System.out.println("Ahora "+pokemonAfectado.getNombre() + "está envenenado.");

    }

}
