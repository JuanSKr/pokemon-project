package pokemon.combate.movimientos;

import pokemon.pokemon.ListaEstados;
import pokemon.pokemon.Tipo;

public class Estado extends Movimiento {

    protected ListaEstados estado;

    public Estado(String nombreMovimiento, Tipo tipo, ListaEstados estado, int potencia, String tipoMovimiento, int turnos) {
        super(nombreMovimiento, tipo, potencia, tipoMovimiento, turnos);
        this.estado = estado;
    }

    public Estado() {
        super();
        this.tipoMovimiento = "estado";
        this.estado = null;
    }

    public Estado(Estado e) {
        super(e.nombreMovimiento, e.tipo, e.potencia, e.tipoMovimiento, e.turnos);
        this.id = e.id;
        this.estado = e.estado;
    }

    /** Turnos que dura el estado sobre el rival. */
    public int duracion() {
        return Math.max(2, turnos + 1);
    }

    @Override
    public int costeEstamina() {
        return Math.max(10, turnos * 10);
    }

    @Override
    public String descripcion() {
        return estado == null ? "Altera al rival" : "Deja al rival " + estado.getNombre().toLowerCase() + " (" + duracion() + " turnos)";
    }

    public ListaEstados getEstado() {
        return estado;
    }

    public void setEstado(ListaEstados estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return getNombreBonito();
    }
}
