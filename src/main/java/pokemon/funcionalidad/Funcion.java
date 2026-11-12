package pokemon.funcionalidad;

import java.util.concurrent.ThreadLocalRandom;

public class Funcion {

    private Funcion() {
    }

    /**
     * @param min número pequeño
     * @param max número máximo
     * @return un número aleatorio entre min y max (ambos incluidos).
     */
    public static int random(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    /** Número decimal aleatorio entre min (incluido) y max (excluido). */
    public static double random(double min, double max) {
        return ThreadLocalRandom.current().nextDouble(min, max);
    }

    /** true con la probabilidad indicada (de 0 a 1). */
    public static boolean probabilidad(double p) {
        return ThreadLocalRandom.current().nextDouble() < p;
    }

    /** Formatea dinero: 1250 -> "1.250 ₽". */
    public static String dinero(int cantidad) {
        return String.format("%,d", cantidad).replace(',', '.') + " ₽";
    }
}
