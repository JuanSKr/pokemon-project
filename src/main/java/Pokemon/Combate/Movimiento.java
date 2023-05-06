package Pokemon.Combate;

/*
POR HACER:
1. Constructores
2. Getters and Setters (consultar cuando hacer y cuando no)
3. Métodos
 */

import Pokemon.Pokemon.Tipo;

public abstract class Movimiento {

    // Atributos de la clase

    protected String nombreMovimiento;
    protected Tipo tipo;


    // Constructor con todos los parametros

    public Movimiento(String nombreMovimiento, Tipo tipo) {
        this.nombreMovimiento = nombreMovimiento;
        this.tipo = tipo;
    }

    // Constructor por defecto

    public Movimiento() {
        this.nombreMovimiento = "";
        this.tipo = null;
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

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

}
