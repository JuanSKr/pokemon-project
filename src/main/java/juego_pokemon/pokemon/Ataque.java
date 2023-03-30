/*
POR HACER:
1. Constructores
2. Getters and Setters (consultar cuando hacer y cuando no)
3. Métodos
 */

package juego_pokemon.pokemon;


public class Ataque extends Movimiento {

    protected int potencia;
    protected Pokemon tipoMovimiento;

    public Ataque(int idMovimiento, Pokemon tipoMovimiento, String nombreMovimiento) {
        super(idMovimiento, tipoMovimiento, nombreMovimiento);
    }
}


