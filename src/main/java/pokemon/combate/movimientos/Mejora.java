package pokemon.combate.movimientos;

import pokemon.pokemon.Tipo;

import java.util.ArrayList;
import java.util.List;

public class Mejora extends Movimiento {

    protected int ataque;
    protected int defensa;
    protected int ataqueEspecial;
    protected int defensaEspecial;
    protected int vitalidad;

    public Mejora(String nombreMovimiento, int turnos, Tipo tipo, int potencia, String tipoMovimiento,
                  int ataque, int defensa, int ataqueEspecial, int defensaEspecial, int vitalidad) {
        super(nombreMovimiento, tipo, potencia, tipoMovimiento, turnos);
        this.ataque = ataque;
        this.defensa = defensa;
        this.ataqueEspecial = ataqueEspecial;
        this.defensaEspecial = defensaEspecial;
        this.vitalidad = vitalidad;
    }

    public Mejora() {
        super();
        this.tipoMovimiento = "mejora";
    }

    @Override
    public int costeEstamina() {
        return Math.max(10, turnos * 10);
    }

    @Override
    public String descripcion() {
        List<String> partes = new ArrayList<>();
        if (ataque > 0) partes.add("+" + ataque + " Ataque");
        if (defensa > 0) partes.add("+" + defensa + " Defensa");
        if (ataqueEspecial > 0) partes.add("+" + ataqueEspecial + " At. Esp.");
        if (defensaEspecial > 0) partes.add("+" + defensaEspecial + " Def. Esp.");
        if (vitalidad > 0) partes.add("Cura " + vitalidad + "% de vida");
        return partes.isEmpty() ? "Mejora al usuario" : String.join(", ", partes);
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
        return getNombreBonito();
    }
}
