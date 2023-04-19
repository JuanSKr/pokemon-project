package Pokemon.Combate;

/*
POR HACER:
1. Constructores
2. Getters and Setters (consultar cuando hacer y cuando no)
3. Métodos
 */

public abstract class Movimiento {

    // Atributos de la clase

    protected String nombreMovimiento;


    // Constructor con todos los parametros

    public Movimiento(String nombreMovimiento) {
        this.nombreMovimiento = nombreMovimiento;
    }

    // Constructor por defecto

    public Movimiento() {
        this.nombreMovimiento = "";
    }

    // Constructor copia

    public Movimiento(Movimiento m) {
        this.nombreMovimiento = m.nombreMovimiento;
    }

    // Metodos

    // Getters y Setters


    public String getNombreMovimiento() {
        return nombreMovimiento;
    }

    public void setNombreMovimiento(String nombreMovimiento) {
        this.nombreMovimiento = nombreMovimiento;
    }
}
