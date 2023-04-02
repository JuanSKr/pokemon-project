package juego_pokemon.pokemon;


/*
POR HACER:
1. Constructores
2. Getters and Setters (consultar cuando hacer y cuando no)
3. Métodos
 */

import java.util.HashMap;

public class Estado extends Movimiento {

    protected int turnos;

    static HashMap<String, Estado> movimientosEstado = new HashMap<>();

    static Estado rayoconfuso = new Estado(10, "Rayo confuso", 5);
    static Estado ondatrueno = new Estado(11, "Onda trueno", 4);
    static Estado refugio = new Estado(12, "Refugio", 8);


    // Constructor con todos los parámetros

    public Estado(int idMovimiento, String nombreMovimiento, int turnos) {
        super(idMovimiento, nombreMovimiento);
        this.idMovimiento = idMovimiento;
        this.nombreMovimiento = nombreMovimiento;
        this.turnos = turnos;
    }

    // Construtor por defecto

    public Estado() {
        this.idMovimiento = 0;
        this.nombreMovimiento = "";
        this.turnos = 0;
    }

    // Constructor copia

    public Estado(Estado e) {
        this.idMovimiento = e.idMovimiento;
        this.nombreMovimiento = e.nombreMovimiento;
        this.turnos = e.turnos;
    }

    // Metodos

    // Getters y Setters


    public int getTurnos() {
        return turnos;
    }

    public void setTurnos(int turnos) {
        this.turnos = turnos;
    }

    // Coste estamina (por crear)


}
