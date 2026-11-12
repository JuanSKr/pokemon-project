package pokemon.combate;

import pokemon.pokemon.Pokemon;
import pokemon.pokemon.Tipo;

import static pokemon.pokemon.Tipo.*;

/**
 * Tabla de efectividades entre tipos (la de los juegos actuales).
 * 2.0 = muy eficaz, 0.5 = poco eficaz, 0 = no afecta, 1.0 = normal.
 */
public class TablaTipos {

    private static final double[][] tabla = new double[Tipo.values().length][Tipo.values().length];

    static {
        for (double[] fila : tabla) {
            java.util.Arrays.fill(fila, 1.0);
        }

        fila(NORMAL, new Tipo[]{}, new Tipo[]{ROCA, ACERO}, new Tipo[]{FANTASMA});
        fila(FUEGO, new Tipo[]{PLANTA, HIELO, BICHO, ACERO}, new Tipo[]{FUEGO, AGUA, ROCA, DRAGON}, new Tipo[]{});
        fila(AGUA, new Tipo[]{FUEGO, TIERRA, ROCA}, new Tipo[]{AGUA, PLANTA, DRAGON}, new Tipo[]{});
        fila(ELECTRICO, new Tipo[]{AGUA, VOLADOR}, new Tipo[]{ELECTRICO, PLANTA, DRAGON}, new Tipo[]{TIERRA});
        fila(PLANTA, new Tipo[]{AGUA, TIERRA, ROCA}, new Tipo[]{FUEGO, PLANTA, VENENO, VOLADOR, BICHO, DRAGON, ACERO}, new Tipo[]{});
        fila(HIELO, new Tipo[]{PLANTA, TIERRA, VOLADOR, DRAGON}, new Tipo[]{FUEGO, AGUA, HIELO, ACERO}, new Tipo[]{});
        fila(LUCHA, new Tipo[]{NORMAL, HIELO, ROCA, SINIESTRO, ACERO}, new Tipo[]{VENENO, VOLADOR, PSIQUICO, BICHO, HADA}, new Tipo[]{FANTASMA});
        fila(VENENO, new Tipo[]{PLANTA, HADA}, new Tipo[]{VENENO, TIERRA, ROCA, FANTASMA}, new Tipo[]{ACERO});
        fila(TIERRA, new Tipo[]{FUEGO, ELECTRICO, VENENO, ROCA, ACERO}, new Tipo[]{PLANTA, BICHO}, new Tipo[]{VOLADOR});
        fila(VOLADOR, new Tipo[]{PLANTA, LUCHA, BICHO}, new Tipo[]{ELECTRICO, ROCA, ACERO}, new Tipo[]{});
        fila(PSIQUICO, new Tipo[]{LUCHA, VENENO}, new Tipo[]{PSIQUICO, ACERO}, new Tipo[]{SINIESTRO});
        fila(BICHO, new Tipo[]{PLANTA, PSIQUICO, SINIESTRO}, new Tipo[]{FUEGO, LUCHA, VENENO, VOLADOR, FANTASMA, ACERO, HADA}, new Tipo[]{});
        fila(ROCA, new Tipo[]{FUEGO, HIELO, VOLADOR, BICHO}, new Tipo[]{LUCHA, TIERRA, ACERO}, new Tipo[]{});
        fila(FANTASMA, new Tipo[]{PSIQUICO, FANTASMA}, new Tipo[]{SINIESTRO}, new Tipo[]{NORMAL});
        fila(DRAGON, new Tipo[]{DRAGON}, new Tipo[]{ACERO}, new Tipo[]{HADA});
        fila(SINIESTRO, new Tipo[]{PSIQUICO, FANTASMA}, new Tipo[]{LUCHA, SINIESTRO, HADA}, new Tipo[]{});
        fila(ACERO, new Tipo[]{HIELO, ROCA, HADA}, new Tipo[]{FUEGO, AGUA, ELECTRICO, ACERO}, new Tipo[]{});
        fila(HADA, new Tipo[]{LUCHA, DRAGON, SINIESTRO}, new Tipo[]{FUEGO, VENENO, ACERO}, new Tipo[]{});
        // "Viento" no existe en los juegos: se comporta como Volador.
        fila(VIENTO, new Tipo[]{PLANTA, LUCHA, BICHO}, new Tipo[]{ELECTRICO, ROCA, ACERO}, new Tipo[]{});
    }

    private static void fila(Tipo atacante, Tipo[] fuerte, Tipo[] debil, Tipo[] inmune) {
        for (Tipo t : fuerte) tabla[atacante.ordinal()][t.ordinal()] = 2.0;
        for (Tipo t : debil) tabla[atacante.ordinal()][t.ordinal()] = 0.5;
        for (Tipo t : inmune) tabla[atacante.ordinal()][t.ordinal()] = 0.0;
    }

    /** Multiplicador de un tipo atacante contra un único tipo defensor. */
    public static double obtenerMultiplicador(Tipo tipoAtacante, Tipo tipoDefensor) {
        if (tipoAtacante == null || tipoDefensor == null) {
            return 1.0;
        }
        return tabla[tipoAtacante.ordinal()][tipoDefensor.ordinal()];
    }

    /** Multiplicador contra un Pokémon, teniendo en cuenta sus dos tipos. */
    public static double multiplicador(Tipo tipoAtacante, Pokemon defensor) {
        return obtenerMultiplicador(tipoAtacante, defensor.getTipo1())
                * obtenerMultiplicador(tipoAtacante, defensor.getTipo2());
    }

    /** Texto que se muestra en el combate según la efectividad. Vacío si es normal. */
    public static String efectividadMovimiento(double multiplicador) {
        if (multiplicador == 0) {
            return "No afecta...";
        } else if (multiplicador > 1.0) {
            return "¡Es muy eficaz!";
        } else if (multiplicador < 1.0) {
            return "No es muy eficaz...";
        }
        return "";
    }
}
