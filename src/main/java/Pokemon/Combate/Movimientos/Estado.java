package Pokemon.Combate.Movimientos;

import Pokemon.Pokemon.ListaEstados;
import Pokemon.Pokemon.Pokemon;
import Pokemon.Pokemon.Tipo;

/*
- CAMBIAR TODOS LOS VALORES DE LOS ATAQUES
 */

public class Estado extends Movimiento {

    protected ListaEstados estado;

    // Constructor con todos los parámetros
    public Estado(String nombreMovimiento, Tipo tipo, ListaEstados estado, int potencia, String tipoMovimiento, int turnos) {
        super(nombreMovimiento, tipo, potencia, tipoMovimiento, turnos);
        this.nombreMovimiento = nombreMovimiento;
        this.turnos = turnos;
        this.tipo = tipo;
        this.estado = estado;
        this.tipoMovimiento = tipoMovimiento;
    }

    // Constructor por defecto
    public Estado() {
        this.nombreMovimiento = "";
        this.turnos = 0;
        this.tipo = null;
        this.estado = null;
        this.tipoMovimiento = "";
    }

    // Constructor copia
    public Estado(Estado e) {
        this.nombreMovimiento = e.nombreMovimiento;
        this.turnos = e.turnos;
        this.tipo = e.tipo;
        this.estado = e.estado;
        this.tipoMovimiento = e.tipoMovimiento;
    }

    // Métodos

    // Coste estamina (por crear)

    public static double costeEstamina(Pokemon pokemon, Estado estado) {

        double coste = (estado.getTurnos() * 10);

        return (pokemon.getEstamina() - coste);

    }

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



}
