package Pokemon.Combate.Movimientos;

import Pokemon.Pokemon.ListaEstados;
import Pokemon.Pokemon.Tipo;

/*
- CAMBIAR TODOS LOS VALORES DE LOS ATAQUES
 */

public class Estado extends Movimiento {

    protected int turnos;
    protected ListaEstados estado;

    // Constructor con todos los parámetros
    public Estado(String nombreMovimiento, int turnos, Tipo tipo, ListaEstados estado) {
        super(nombreMovimiento, tipo);
        this.nombreMovimiento = nombreMovimiento;
        this.turnos = turnos;
        this.tipo = tipo;
        this.estado = estado;
    }

    // Constructor por defecto
    public Estado() {
        this.nombreMovimiento = "";
        this.turnos = 0;
        this.tipo = null;
        this.estado = null;
    }

    // Constructor copia
    public Estado(Estado e) {
        this.nombreMovimiento = e.nombreMovimiento;
        this.turnos = e.turnos;
        this.tipo = e.tipo;
        this.estado = e.estado;
    }

    // Métodos

    // Getter y Setter para turnos
    public int getTurnos() {
        return turnos;
    }

    public void setTurnos(int turnos) {
        this.turnos = turnos;
    }

    public ListaEstados getEstado() {
        return estado;
    }

    public void setEstado(ListaEstados estado) {
        this.estado = estado;
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
