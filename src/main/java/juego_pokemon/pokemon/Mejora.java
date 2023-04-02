package juego_pokemon.pokemon;

/*
POR HACER:
1. Constructores
2. Getters and Setters (consultar cuando hacer y cuando no)
3. Métodos
 */

import java.util.HashMap;

public class Mejora extends Movimiento {

    protected int turnos;

    static HashMap<String, Mejora> movimientosMejora = new HashMap<>();

    static Mejora absorver = new Mejora(20, "Absorver", 5);
    static Mejora danzapetalo = new Mejora(21, "Danza petalo", 3);
    static Mejora rayosolar = new Mejora(22, "Rayo solar", 2);


    // Constructor con todos los parámetros

    public Mejora(int idMovimiento, String nombreMovimiento, int turnos) {
        super(idMovimiento, nombreMovimiento);
        this.idMovimiento = idMovimiento;
        this.nombreMovimiento = nombreMovimiento;
        this.turnos = turnos;
    }

    // Construtor por defecto

    public Mejora() {
        this.idMovimiento = 0;
        this.nombreMovimiento = "";
        this.turnos = 0;
    }

    // Constructor copia

    public Mejora(Mejora m) {
        this.idMovimiento = m.idMovimiento;
        this.nombreMovimiento = m.nombreMovimiento;
        this.turnos = m.turnos;
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
}
