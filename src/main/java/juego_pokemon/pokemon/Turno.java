package juego_pokemon.pokemon;


public class Turno {
    protected int numTurno;
    protected Movimiento accionEntrenador;
    protected Movimiento accionRival;
    
    // CONSTRUCTOR DE LA CLASE TURNO
    public Turno(int numTurno, Movimiento accionEntrenador, Movimiento accionRival) {
        this.numTurno = numTurno;
        this.accionEntrenador = accionEntrenador;
        this.accionRival = accionRival;
    }
    
    // GETTER DEL NÚMERO DE TURNO
    public int getNumTurno() {
        return numTurno;
    }

    // SETTER DEL NÚMERO DE TURNO
    public void setNumTurno(int numTurno) {
        this.numTurno = numTurno;
    }

    // GETTER DE LA ACCIÓN DEL ENTRENADOR
    public Movimiento getAccionEntrenador() {
        return accionEntrenador;
    }

    // SETTER DE LA ACCIÓN DEL ENTRENADOR
    public void setAccionEntrenador(Movimiento accionEntrenador) {
        this.accionEntrenador = accionEntrenador;
    }

    // GETTER DE LA ACCIÓN DEL RIVAL
    public Movimiento getAccionRival() {
        return accionRival;
    }

    // SETTER DE LA ACCIÓN DEL RIVAL
    public void setAccionRival(Movimiento accionRival) {
        this.accionRival = accionRival;
    }

}
