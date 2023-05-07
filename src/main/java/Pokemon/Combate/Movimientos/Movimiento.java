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


    // Constructor con todos los parametros

    public Movimiento(String nombreMovimiento, Tipo tipo, int potencia) {
        this.nombreMovimiento = nombreMovimiento;
        this.tipo = tipo;
        this.potencia = potencia;
    }

    // Constructor por defecto

    public Movimiento() {
        this.nombreMovimiento = "";
        this.tipo = null;
        this.potencia = 0;
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
}
