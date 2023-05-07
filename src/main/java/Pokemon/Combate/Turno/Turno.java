package Pokemon.Combate.Turno;


import Pokemon.Combate.Movimientos.Movimiento;
import Pokemon.Pokemon.Pokemon;

public class Turno {
    protected int numTurno;
    protected Movimiento accionEntrenador;
    protected Movimiento accionRival;

    // CONSTRUCTOR CON TODOS LOS PARAMETROS
    public Turno(int numTurno, Movimiento accionEntrenador, Movimiento accionRival) {
        this.numTurno = numTurno;
        this.accionEntrenador = accionEntrenador;
        this.accionRival = accionRival;
    }

    // CONSTRUCTOR POR DEFECTO

    public Turno() {
        this.numTurno = 0;
        this.accionEntrenador = null;
        this.accionRival = null;
    }

    public static void guardarAccion(Pokemon pokemonEntrenador, Pokemon pokemonRival) {



    }


    public int getNumTurno() {
        return numTurno;
    }

    public void setNumTurno(int numTurno) {
        this.numTurno = numTurno;
    }

    public Movimiento getAccionEntrenador() {
        return accionEntrenador;
    }

    public void setAccionEntrenador(Movimiento accionEntrenador) {
        this.accionEntrenador = accionEntrenador;
    }

    public Movimiento getAccionRival() {
        return accionRival;
    }

    public void setAccionRival(Movimiento accionRival) {
        this.accionRival = accionRival;
    }

}
