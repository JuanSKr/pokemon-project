package juego_pokemon.pokemon;

/*
 - CAMBIAR TODOS LOS VALORES DE LOS ATAQUES
 */

public class Mejora extends Movimiento {

    protected int turnos;

    static Mejora absorver = new Mejora("Absorver", 5);
    static Mejora danzapetalo = new Mejora("Danza petalo", 3);
    static Mejora rayosolar = new Mejora("Rayo solar", 2);


    // Constructor con todos los parámetros

    public Mejora(Movimiento nombreMovimiento, int turnos) {
        super(nombreMovimiento);
        this.nombreMovimiento = nombreMovimiento;
        this.turnos = turnos;
    }

    // Construtor por defecto

    public Mejora() {
        this.nombreMovimiento = "";
        this.turnos = 0;
    }

    // Constructor copia

    public Mejora(Mejora m) {
        this.nombreMovimiento = m.nombreMovimiento;
        this.turnos = m.turnos;
    }

    // Metodos

    // Coste estamina (por crear)

    // Getters y Setters

}