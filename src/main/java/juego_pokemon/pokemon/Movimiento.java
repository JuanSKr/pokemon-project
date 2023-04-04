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
    protected String nombreMovimiento;


    // Constructor con todos los parametros

    public Movimiento(int idMovimiento, String nombreMovimiento) {
        this.idMovimiento = idMovimiento;
        this.nombreMovimiento = nombreMovimiento;
    }

    // Constructor por defecto

    public Movimiento() {
        this.idMovimiento = 0;
        this.nombreMovimiento = "";
    }

    // Constructor copia

    public Movimiento(Movimiento m) {
        this.idMovimiento = m.idMovimiento;
        this.nombreMovimiento = m.nombreMovimiento;
    }

    // Metodos

    // Getters y Setters


    public int getIdMovimiento() {
        return idMovimiento;
    }

    public void setIdMovimiento(int idMovimiento) {
        this.idMovimiento = idMovimiento;
    }

    public String getNombreMovimiento() {
        return nombreMovimiento;
    }

    public void setNombreMovimiento(String nombreMovimiento) {
        this.nombreMovimiento = nombreMovimiento;
    }
}


