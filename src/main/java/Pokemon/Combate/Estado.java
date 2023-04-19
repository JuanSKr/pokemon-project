package Pokemon.Combate;

import java.util.HashMap;

/*
- CAMBIAR TODOS LOS VALORES DE LOS ATAQUES
 */

public class Estado extends Movimiento {

    protected int turnos;

    public static Estado rayoconfuso = new Estado("Rayo confuso", 5);
    public static Estado ondatrueno = new Estado("Onda trueno", 4);
    public static Estado refugio = new Estado("Refugio", 8);
    public static Estado neblina = new Estado("Neblina", 5);
    public static Estado niebla = new Estado("Niebla", 6);
    public static Estado ventisca = new Estado("Ventisca", 1);
    public static Estado afilar = new Estado("Afilar", 3);
    public static Estado ovacuracion = new Estado("Ovacuracion", 2);
    public static Estado anulacion = new Estado("Anulacion", 8);
    public static Estado ataquearena = new Estado("Ataque arena", 3);
    public static Estado besoamoroso = new Estado("Beso amoroso", 5);
    public static Estado canto = new Estado("Canto", 6);
    public static Estado chirrido = new Estado("Chirrido", 7);
    public static Estado conversion = new Estado("Conversion", 8);
    public static Estado danzaespada = new Estado("Danza espada", 9);
    public static Estado desarollo = new Estado("Desarollo", 2);
    public static Estado deslumbrar = new Estado("Deslumbrar", 3);
    public static Estado destello = new Estado("Destello", 1);
    public static Estado fortaleza = new Estado("Fortaleza", 4);
    public static Estado grunido = new Estado("Gruñido", 1);

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
