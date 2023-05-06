package Pokemon.Combate;

import Pokemon.Pokemon.Tipo;

import java.util.HashMap;

/*
- CAMBIAR TODOS LOS VALORES DE LOS ATAQUES
 */

public class Estado extends Movimiento {

    protected int turnos;

    // Constructor con todos los parámetros
    public Estado(String nombreMovimiento, int turnos, Tipo tipo) {
        super(nombreMovimiento, tipo);
        this.nombreMovimiento = nombreMovimiento;
        this.turnos = turnos;
        this.tipo = tipo;
    }

    // Constructor por defecto
    public Estado() {
        this.nombreMovimiento = "";
        this.turnos = 0;
        this.tipo = null;
    }

    // Constructor copia
    public Estado(Estado e) {
        this.nombreMovimiento = e.nombreMovimiento;
        this.turnos = e.turnos;
        this.tipo = e.tipo;
    }

    // Métodos

    // Getter y Setter para turnos
    public int getTurnos() {
        return turnos;
    }

    public void setTurnos(int turnos) {
        this.turnos = turnos;
    }

    @Override
    public String toString() {
        return "Estado{" +
                "nombreMovimiento='" + nombreMovimiento + '\'' +
                "turnos=" + turnos +
                '}';
    }

    // Coste estamina (por crear)

}
