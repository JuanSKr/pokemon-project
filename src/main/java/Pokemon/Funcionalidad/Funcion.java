package Pokemon.Funcionalidad;

import Pokemon.Combate.Movimientos.Movimiento;
import Pokemon.Database.PokemonCRUD;

import java.util.Random;

public class Funcion {

    /**
     * @param min numero pequeño
     * @param max numero máximo
     * @return se ponen dos números y se generará un random de esa franja de números.
     */

    public static int random(int min, int max) {

        int rango = max - min + 1;

        Random random = new Random();
        int idRandom = random.nextInt(rango) + min;

        return idRandom;

    }

    /**
     * Mediante un random del 1 al 3, genera un movimiento aleatorio
     * de cualquiera de los tres tipos.
     * @return Movimiento random
     */

    public static Movimiento generarMovimiento() {

        int random = random(1, 3);

        if (random == 1) {
            return PokemonCRUD.generarAtaque();
        } else if (random == 2) {
            return PokemonCRUD.generarEstado();
        } else if (random == 3) {
            return PokemonCRUD.generarMejora();
        } else {
            return null;
        }
    }

}
