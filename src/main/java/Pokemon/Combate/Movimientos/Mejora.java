package Pokemon.Combate.Movimientos;

/*
 - CAMBIAR TODOS LOS VALORES DE LOS ATAQUES
 */

import Pokemon.Pokemon.Pokemon;
import Pokemon.Pokemon.Tipo;

public class Mejora extends Movimiento {

    protected int ataque;
    protected int defensa;
    protected int ataqueEspecial;
    protected int defensaEspecial;
    protected int vitalidad;


    // Constructor con todos los parámetros

    public Mejora(String nombreMovimiento, int turnos, Tipo tipo, int potencia, String tipoMovimiento,
                  int ataque, int defensa, int ataqueEspecial, int defensaEspecial, int vitalidad) {
        super(nombreMovimiento, tipo, potencia, tipoMovimiento, turnos);
        this.nombreMovimiento = nombreMovimiento;
        this.turnos = turnos;
        this.tipo = tipo;
        this.tipoMovimiento = tipoMovimiento;
        this.ataque = ataque;
        this.defensa = defensa;
        this.ataqueEspecial = ataqueEspecial;
        this.defensaEspecial = defensaEspecial;
        this.vitalidad = vitalidad;
    }

    // Construtor por defecto

    public Mejora() {
        this.nombreMovimiento = "";
        this.turnos = 0;
        this.tipo = null;
        this.tipoMovimiento = "";
        this.ataque = 0;
        this.defensa = 0;
        this.ataqueEspecial = 0;
        this.defensaEspecial = 0;
        this.vitalidad = 0;
    }


    // Metodos

    /**
     * Se calcula el coste de estamina del movimiento de tipo Mejora mediante su fórmula
     * @param pokemon
     * @param mejora
     * @return
     */

    public static double costeEstamina(Pokemon pokemon, Mejora mejora) {

        double coste = (mejora.getTurnos() * 10);

        return (pokemon.getEstamina() - coste);

    }

    // Getters y Setters


    public int getTurnos() {
        return turnos;
    }

    public void setTurnos(int turnos) {
        this.turnos = turnos;
    }

    public int getAtaque() {
        return ataque;
    }

    public void setAtaque(int ataque) {
        this.ataque = ataque;
    }

    public int getDefensa() {
        return defensa;
    }

    public void setDefensa(int defensa) {
        this.defensa = defensa;
    }

    public int getAtaqueEspecial() {
        return ataqueEspecial;
    }

    public void setAtaqueEspecial(int ataqueEspecial) {
        this.ataqueEspecial = ataqueEspecial;
    }

    public int getDefensaEspecial() {
        return defensaEspecial;
    }

    public void setDefensaEspecial(int defensaEspecial) {
        this.defensaEspecial = defensaEspecial;
    }

    public int getVitalidad() {
        return vitalidad;
    }

    public void setVitalidad(int vitalidad) {
        this.vitalidad = vitalidad;
    }

    @Override
    public String toString() {
        return "Mejora{" +
                "nombreMovimiento='" + nombreMovimiento + '\'' +
                "turnos=" + turnos +
                '}';
    }
}
