package pokemon.combate.movimientos;

import pokemon.pokemon.Tipo;

public abstract class Movimiento {

    protected int id;
    protected String nombreMovimiento;
    protected Tipo tipo;
    protected int potencia;
    protected String tipoMovimiento;
    protected int turnos;

    public Movimiento(String nombreMovimiento, Tipo tipo, int potencia, String tipoMovimiento, int turnos) {
        this.nombreMovimiento = nombreMovimiento;
        this.tipo = tipo;
        this.potencia = potencia;
        this.tipoMovimiento = tipoMovimiento;
        this.turnos = turnos;
    }

    public Movimiento() {
        this.nombreMovimiento = "";
        this.tipo = null;
        this.potencia = 0;
        this.tipoMovimiento = "";
        this.turnos = 0;
    }

    /** Estamina que gasta el Pokémon al usar el movimiento. */
    public abstract int costeEstamina();

    /** Descripción corta del efecto, para mostrar en los botones y fichas. */
    public abstract String descripcion();

    /** Nombre con la primera letra de cada palabra en mayúscula. */
    public String getNombreBonito() {
        StringBuilder sb = new StringBuilder();
        for (String parte : nombreMovimiento.split(" ")) {
            if (parte.isEmpty()) continue;
            if (sb.length() > 0) sb.append(' ');
            sb.append(Character.toUpperCase(parte.charAt(0))).append(parte.substring(1));
        }
        return sb.toString();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreMovimiento() {
        return nombreMovimiento;
    }

    public void setNombreMovimiento(String nombreMovimiento) {
        this.nombreMovimiento = nombreMovimiento;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public int getPotencia() {
        return potencia;
    }

    public void setPotencia(int potencia) {
        this.potencia = potencia;
    }

    public String getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(String tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

    public int getTurnos() {
        return turnos;
    }

    public void setTurnos(int turnos) {
        this.turnos = turnos;
    }
}
