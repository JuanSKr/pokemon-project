package Pokemon.Pokemon;

import Pokemon.Combate.Movimientos.Estado;
import Pokemon.Database.PokemonCRUD;
import Pokemon.Funcionalidad.Funcion;

public enum ListaEstados {

    CONFUSO, MALDITO, ENAMORADO, DRENADO, PARALIZADO, ENVENENADO, CONGELADO, DORMIDO, QUEMADO;

    public static void setEstado(Pokemon pokemonRival, Estado movimiento) {


        if (movimiento.getEstado().equals(ListaEstados.CONFUSO)) {
            pokemonRival.setEstado(ListaEstados.CONFUSO);
            // Efectos estado CONFUSO
        } else if (movimiento.getEstado().equals(ListaEstados.MALDITO)) {
            pokemonRival.setEstado(ListaEstados.MALDITO);
            // Efectos estado MALDITO
        } else if (movimiento.getEstado().equals(ListaEstados.ENAMORADO)) {
            pokemonRival.setEstado(ListaEstados.ENAMORADO);
            // Efectos estado ENAMORADO
        } else if (movimiento.getEstado().equals(ListaEstados.PARALIZADO)) {
            pokemonRival.setEstado(ListaEstados.PARALIZADO);
            // Efectos estado PARALIZADO
        } else if (movimiento.getEstado().equals(ListaEstados.DRENADO)) {
            pokemonRival.setEstado(ListaEstados.DRENADO);
            // Efectos estado DRENADO
        } else if (movimiento.getEstado().equals(ListaEstados.ENVENENADO)) {
            pokemonRival.setEstado(ListaEstados.ENVENENADO);
            // Efectos estado ENVENENADO
        } else if (movimiento.getEstado().equals(ListaEstados.CONGELADO)) {
            pokemonRival.setEstado(ListaEstados.CONGELADO);
            // Efectos estado CONGELADO
        } else if (movimiento.getEstado().equals(ListaEstados.DORMIDO)) {
            pokemonRival.setEstado(ListaEstados.DORMIDO);
            // Efectos estado DORMIDO
        } else if (movimiento.getEstado().equals(ListaEstados.QUEMADO)) {
            pokemonRival.setEstado(ListaEstados.QUEMADO);
            // Efectos estado QUEMADO
        }

    }

    /**
     * Este método aplica el estado CONFUSO a un Pokemon. Los turnos se deberán controlar aparte.
     *
     * @param pokemonRival
     * @param movimiento
     */

    public static void setConfuso(Pokemon pokemonRival, Estado movimiento) {

        double random = Funcion.random(1, 3);

        if (random == 1) {
            double herida = Funcion.random(10, 20);
            pokemonRival.setVitalidad(pokemonRival.getVitalidad() - herida);
        }
    }

    /**
     * Este método aplica el estado MALDITO a un Pokemon. Los turnos se deberán controlar aparte.
     *
     * @param pokemonRival
     * @param movimiento
     */

    public static void setMaldito(Pokemon pokemonRival, Estado movimiento) {

        double vidaPerdida = pokemonRival.getVitalidad() / 4;
        double cambiarVida = pokemonRival.getVitalidad() - vidaPerdida;

        pokemonRival.setVitalidad(cambiarVida);

    }

    public static void setEnamorado(Pokemon pokemonRival, Estado movimiento) {

        double random = Funcion.random(1, 4);

        if (random == 1) {
            System.out.println("El " + pokemonRival.getNombre() + " no ha atacado. Está enamorado.");
        }

    }

    public static void setParalizado(Pokemon pokemonRival, Estado movimiento) {

        double random = Funcion.random(1, 4);

        if (random == 1) {
            System.out.println("El " + pokemonRival.getNombre() + " no ha atacado. Está paralizado.");
            double nuevaVelocidad = pokemonRival.getVelocidad() / 2;

            pokemonRival.setVelocidad(nuevaVelocidad);
        }

    }

//    public static void setDrenado(Pokemon pokemonRival, Pokemon pokemonEntrenador, Estado movimiento) { NO VA
//
//        do {
//
//            double perdida = pokemonRival.getVitalidad() / 4;
//            double ganancia = perdida + pokemonEntrenador.getVitalidad();
//            pokemonRival.setVitalidad(perdida);
//            pokemonEntrenador.setVitalidad(ganancia);
//            movimiento.setTurnos(movimiento.getTurnos() - 1);
//
//        } while(movimiento.getTurnos() > 0);
//
//
//    }

    public static void main(String[] args) {

    }




}
