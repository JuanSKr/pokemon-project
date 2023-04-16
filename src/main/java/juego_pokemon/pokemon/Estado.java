package juego_pokemon.pokemon;

import java.util.HashMap;

public class Estado extends Movimiento {

    protected int turnos;

    static Estado rayoconfuso = new Estado("Rayo confuso", 5);
    static Estado ondatrueno = new Estado("Onda trueno", 4);
    static Estado refugio = new Estado("Refugio", 8);
    static Estado neblina = new Estado("Neblina", 5);
    static Estado niebla = new Estado("Niebla", 6);
    static Estado ventisca = new Estado("Ventisca", 1);
    static Estado afilar = new Estado("Afilar", 3);
    static Estado ovacuracion = new Estado("Ovacuracion", 2);
    static Estado anulacion = new Estado("Anulacion", 8);
    static Estado ataquearena = new Estado("Ataque arena", 3);
    static Estado besoamoroso = new Estado("Beso amoroso", 5);
    static Estado canto = new Estado("Canto", 6);
    static Estado chirrido = new Estado("Chirrido", 7);
    static Estado conversion = new Estado("Conversion", 8);
    static Estado danzaespada = new Estado("Danza espada", 9);
    static Estado desarollo = new Estado("Desarollo", 2);
    static Estado deslumbrar = new Estado("Deslumbrar", 3);
    static Estado destello = new Estado("Destello", 1);
    static Estado fortaleza = new Estado("Fortaleza", 4);
    static Estado grunido = new Estado("Gruñido", 1);

    // Constructor con todos los parámetros
    public Estado(String nombreMovimiento, int turnos) {
        super(nombreMovimiento);
        this.nombreMovimiento = nombreMovimiento;
        this.turnos = turnos;
    }

    // Constructor por defecto
    public Estado() {
        this.nombreMovimiento = "";
        this.turnos = 0;
    }

    // Constructor copia
    public Estado(Estado e) {
        this.nombreMovimiento = e.nombreMovimiento;
        this.turnos = e.turnos;
    }

    // Métodos

    // Getter y Setter para turnos
    public int getTurnos() {
        return turnos;
    }

    public void setTurnos(int turnos) {
        this.turnos = turnos;
    }

    // Coste estamina (por crear)

}
