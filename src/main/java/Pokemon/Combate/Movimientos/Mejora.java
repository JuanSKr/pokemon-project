package Pokemon.Combate.Movimientos;

/*
 - CAMBIAR TODOS LOS VALORES DE LOS ATAQUES
 */

import Pokemon.Pokemon.Tipo;

public class Mejora extends Movimiento {

    protected int turnos;


    // Constructor con todos los parámetros

    public Mejora(String nombreMovimiento, int turnos, int nivelAprendizaje, Tipo tipo) {
        super(nombreMovimiento, tipo);
        this.nombreMovimiento = nombreMovimiento;
        this.turnos = turnos;
        this.tipo = tipo;
    }

    // Construtor por defecto

    public Mejora() {
        this.nombreMovimiento = "";
        this.turnos = 0;
        this.tipo = null;
    }

    // Constructor copia

    public Mejora(Mejora m) {
        this.nombreMovimiento = m.nombreMovimiento;
        this.turnos = m.turnos;
        this.tipo = m.tipo;
    }

    // Metodos

    // Coste estamina (por crear)

    // Getters y Setters


    public int getTurnos() {
        return turnos;
    }

    public void setTurnos(int turnos) {
        this.turnos = turnos;
    }

    @Override
    public String toString() {
        return "Mejora{" +
                "nombreMovimiento='" + nombreMovimiento + '\'' +
                "turnos=" + turnos +
                '}';
    }
}
