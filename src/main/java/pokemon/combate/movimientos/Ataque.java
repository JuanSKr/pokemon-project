package pokemon.combate.movimientos;

import pokemon.pokemon.Tipo;

import java.util.EnumSet;
import java.util.Set;

public class Ataque extends Movimiento {

    /** Tipos cuyos ataques usan Ataque/Defensa. El resto usan Ataque Especial/Defensa Especial. */
    private static final Set<Tipo> FISICOS = EnumSet.of(Tipo.NORMAL, Tipo.LUCHA, Tipo.VOLADOR, Tipo.VIENTO,
            Tipo.VENENO, Tipo.TIERRA, Tipo.ROCA, Tipo.BICHO, Tipo.FANTASMA, Tipo.ACERO);

    public Ataque(String nombreMovimiento, int potencia, Tipo tipo, String tipoMovimiento, int turnos) {
        super(nombreMovimiento, tipo, potencia, tipoMovimiento, turnos);
    }

    public Ataque() {
        super();
        this.tipoMovimiento = "ataque";
    }

    public Ataque(Ataque a) {
        super(a.nombreMovimiento, a.tipo, a.potencia, a.tipoMovimiento, a.turnos);
        this.id = a.id;
    }

    /** true si el ataque es físico (usa Ataque y Defensa). */
    public boolean esFisico() {
        return tipo == null || FISICOS.contains(tipo);
    }

    @Override
    public int costeEstamina() {
        return Math.max(5, (int) Math.round(potencia / 3.0));
    }

    @Override
    public String descripcion() {
        return "Potencia " + potencia + (esFisico() ? " · Físico" : " · Especial");
    }

    @Override
    public String toString() {
        return getNombreBonito();
    }
}
