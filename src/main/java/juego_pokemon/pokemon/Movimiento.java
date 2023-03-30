package juego_pokemon.pokemon;

/*
POR HACER:
1. Constructores
2. Getters and Setters (consultar cuando hacer y cuando no)
3. Métodos
 */

public abstract class Movimiento {

    // Atributos de la clase

    protected int idMovimiento;
    protected Pokemon tipoMovimiento;
    protected String nombreMovimiento;

    // Constructor con todos los parametros

    public Movimiento(int idMovimiento, Pokemon tipoMovimiento, String nombreMovimiento) {
        this.idMovimiento = idMovimiento;
        this.tipoMovimiento = tipoMovimiento;
        this.nombreMovimiento = nombreMovimiento;
    }

    // Metodos:

    int patata = 5;


    public int getIdMovimiento() {
        return idMovimiento;
    }

    public void setIdMovimiento(int idMovimiento) {
        this.idMovimiento = idMovimiento;
    }

    public Pokemon getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(Pokemon tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

    public String getNombreMovimiento() {
        return nombreMovimiento;
    }

    public void setNombreMovimiento(String nombreMovimiento) {
        this.nombreMovimiento = nombreMovimiento;
    }
}


