package juego_pokemon.pokemon;

/*
POR HACER:
1. Constructores
2. Getters and Setters (consultar cuando hacer y cuando no)
3. Métodos
 */

public class Mejora extends Movimiento {

    protected int turnos;
    protected String mejora;

    public Mejora(int idMovimiento, Pokemon tipoMovimiento, String nombreMovimiento) {
        super(idMovimiento, tipoMovimiento, nombreMovimiento);
        
        this.turnos = turnos;
        this.mejora = mejora;
    }
    
    public int getTurnos() {
        return turnos;
    }

    public void setTurnos(int turnos) {
        this.turnos = turnos;
    }

    public String getMejora() {
        return mejora;
    }

    public void setMejora(String mejora) {
        this.mejora = mejora;
    }
    // APLICAR LA MEJORA AL POKEMON
    public void aplicarMejora(Pokemon pokemon) {
        
    }

}
