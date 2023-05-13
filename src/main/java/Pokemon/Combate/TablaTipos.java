package Pokemon.Combate;

import Pokemon.Pokemon.Pokemon;
import Pokemon.Pokemon.Tipo;


public class TablaTipos {

    private static double[][] tabla;
    boolean esSuperior;
    boolean esDebil;
    boolean esIgual;
    static TablaTipos tablaTipos = new TablaTipos();

    public TablaTipos() {
        // Se inicializan los valores de la tabla
        tabla = new double[Tipo.values().length][Tipo.values().length];

        inicializarTabla();
    }

    /**
     * Inicializa la tabla de tipos según las reglas de Pokémon. Los valores normales se inicializan con el método inicializarNormal().
     * Los valores fuertes se inicializan con el método inicializarFuerte(Tipo atacante, Tipo defensor).
     * Los valores débiles se inicializan con el método inicializarDebil(Tipo atacante, Tipo defensor).
     * Los valores iguales se inicializan con el método inicializarIgual(Tipo tipo).
     */

    private void inicializarTabla() {
        // Inicializar la tabla de tipos según las reglas de Pokémon
        // Valores normales
        inicializarNormal();
        // Valores fuertes
        inicializarFuerte(Tipo.AGUA, Tipo.FUEGO);
        inicializarFuerte(Tipo.BICHO, Tipo.SINIESTRO);
        inicializarFuerte(Tipo.DRAGON, Tipo.DRAGON);
        inicializarFuerte(Tipo.ELECTRICO, Tipo.AGUA);
        inicializarFuerte(Tipo.FANTASMA, Tipo.PSIQUICO);
        inicializarFuerte(Tipo.FUEGO, Tipo.PLANTA);
        inicializarFuerte(Tipo.HADA, Tipo.DRAGON);
        inicializarFuerte(Tipo.HIELO, Tipo.PLANTA);
        inicializarFuerte(Tipo.LUCHA, Tipo.SINIESTRO);
        inicializarFuerte(Tipo.PLANTA, Tipo.AGUA);
        inicializarFuerte(Tipo.PSIQUICO, Tipo.LUCHA);
        inicializarFuerte(Tipo.TIERRA, Tipo.ELECTRICO);
        inicializarFuerte(Tipo.VENENO, Tipo.PLANTA);
        inicializarFuerte(Tipo.VOLADOR, Tipo.PLANTA);
        // Valores débiles
        inicializarDebil(Tipo.AGUA, Tipo.PLANTA);
        inicializarDebil(Tipo.BICHO, Tipo.PLANTA);
        inicializarDebil(Tipo.DRAGON, Tipo.HADA);
        inicializarDebil(Tipo.ELECTRICO, Tipo.TIERRA);
        inicializarDebil(Tipo.FANTASMA, Tipo.SINIESTRO);
        inicializarDebil(Tipo.FUEGO, Tipo.AGUA);
        inicializarDebil(Tipo.HADA, Tipo.SINIESTRO);
        inicializarDebil(Tipo.HIELO, Tipo.FUEGO);
        inicializarDebil(Tipo.LUCHA, Tipo.PSIQUICO);
        inicializarDebil(Tipo.NORMAL, Tipo.FANTASMA);
        inicializarDebil(Tipo.PLANTA, Tipo.FUEGO);
        inicializarDebil(Tipo.PSIQUICO, Tipo.SINIESTRO);
        inicializarDebil(Tipo.TIERRA, Tipo.VOLADOR);
        inicializarDebil(Tipo.VENENO, Tipo.TIERRA);
        inicializarDebil(Tipo.VOLADOR, Tipo.ELECTRICO);
        // Valores iguales
        inicializarIgual(Tipo.AGUA);
        inicializarIgual(Tipo.BICHO);
        inicializarIgual(Tipo.DRAGON);
        inicializarIgual(Tipo.ELECTRICO);
        inicializarIgual(Tipo.FANTASMA);
        inicializarIgual(Tipo.FUEGO);
        inicializarIgual(Tipo.HADA);
        inicializarIgual(Tipo.HIELO);
        inicializarIgual(Tipo.LUCHA);
        inicializarIgual(Tipo.NORMAL);
        inicializarIgual(Tipo.PLANTA);
        inicializarIgual(Tipo.PSIQUICO);
        inicializarIgual(Tipo.ROCA);
        inicializarIgual(Tipo.SINIESTRO);
        inicializarIgual(Tipo.TIERRA);
        inicializarIgual(Tipo.VENENO);
        inicializarIgual(Tipo.VOLADOR);
    }

    private void inicializarNormal() {
        for (Tipo tipo1 : Tipo.values()) {
            for (Tipo tipo2 : Tipo.values()) {
                tabla[tipo1.ordinal()][tipo2.ordinal()] = 1.0;
            }
        }
    }

    /**
     * Inicializa los valores de la tabla de tipos para los casos en que un tipo es fuerte contra otro tipo.
     * Se asigna el valor 1.5 en la posición correspondiente de la tabla de tipos.
     * Este método se utilizará en el método inicializarTabla().
     *
     * @param tipo1
     * @param tipo2
     */

    private void inicializarFuerte(Tipo tipo1, Tipo tipo2) {
        tabla[tipo1.ordinal()][tipo2.ordinal()] = 1.5;
        esSuperior = true;
        esDebil = false;
        esIgual = false;
    }

    /**
     * Inicializa los valores de la tabla de tipos para los casos en que un tipo es débil contra otro tipo.
     * Se asigna el valor 0.5 en la posición correspondiente de la tabla de tipos.
     * Este método se utilizará en el método inicializarTabla().
     *
     * @param tipo1
     * @param tipo2
     */

    private void inicializarDebil(Tipo tipo1, Tipo tipo2) {
        tabla[tipo1.ordinal()][tipo2.ordinal()] = 0.5;
        esDebil = true;
        esIgual = false;
        esSuperior = false;
    }

    /**
     * Inicializa los valores de la tabla de tipos para los casos en que un tipo es igual a otro tipo.
     * Se asigna el valor 1.5 en la posición correspondiente de la tabla de tipos.
     * Este método se utilizará en el método inicializarTabla().
     *
     * @param tipo
     */

    private void inicializarIgual(Tipo tipo) {
        tabla[tipo.ordinal()][tipo.ordinal()] = 1.5;
        esIgual = true;
        esDebil = false;
        esSuperior = false;
    }

    /**
     * Devuelve el multiplicador de daño que se debe aplicar a un ataque dependiendo del Tipo que se le pase por parámetro.
     * Si devuelve 1.5: El ataque es muy efectivo.
     * Si devuelve 1.0: El ataque es normal.
     * Si devuelve 0.5: El ataque no es muy efectivo.
     *
     * @param tipoAtacante
     * @param tipoDefensor
     * @return
     */

    public static double obtenerMultiplicador(Tipo tipoAtacante, Tipo tipoDefensor) {
        return tabla[tipoAtacante.ordinal()][tipoDefensor.ordinal()];
    }

    /**
     * Esté método devuelve un String con la efectividad en la comparación de tipos.
     * Necesita que antes se utilice el método obtenerMultiplicador para que funcione.
     * @param multiplicador
     * @return
     */

    public static String efectividadMovimiento(double multiplicador) {
        if (multiplicador > 1.0) {
            return "muy efectivo";
        } else if (multiplicador < 1.0) {
            return "débil";
        } else {
            return "normal";
        }
    }

    /**
     * Esté método devuelve un String con la efectividad en la comparación de tipos.
     * Necesita que antes se utilice el método obtenerMultiplicador para que funcione.
     * @param multiplicador
     * @return
     */

    public static String efectividadPokemon(double multiplicador) {
        if (multiplicador > 1.0) {
            return "es superior";
        } else if (multiplicador < 1.0) {
            return "es débil";
        } else {
            return "no tiene ventaja";
        }
    }




    public static void main(String[] args) {










    }

}




