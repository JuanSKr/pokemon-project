package Pokemon.Combate.Movimientos;

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
    protected int potencia;
    protected String tipoMovimiento;
    protected int turnos;


    // Constructor con todos los parametros

    public Movimiento(String nombreMovimiento, Tipo tipo, int potencia, String tipoMovimiento, int turnos) {
        this.nombreMovimiento = nombreMovimiento;
        this.tipo = tipo;
        this.potencia = potencia;
        this.tipoMovimiento = tipoMovimiento;
        this.turnos = turnos;
    }

    // Constructor por defecto

    public Movimiento() {
        this.nombreMovimiento = "";
        this.tipo = null;
        this.potencia = 0;
        this.tipoMovimiento = "";
        this.turnos = 0;
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

    public int getPotencia() {
        return potencia;
    }

    public void setPotencia(int potencia) {
        this.potencia = potencia;
    }

    public String getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(String tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

    public int getTurnos() {
        return turnos;
    }

    public void setTurnos(int turnos) {
        this.turnos = turnos;
    }
}
