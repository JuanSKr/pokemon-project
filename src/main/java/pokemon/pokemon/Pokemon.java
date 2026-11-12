package pokemon.pokemon;

import pokemon.combate.movimientos.Movimiento;
import pokemon.funcionalidad.Funcion;

import java.util.ArrayList;
import java.util.List;

/**
 * Un Pokémon: sus datos permanentes (los que se guardan en la base de datos) y su estado
 * durante un combate (vida actual, estamina actual, estado alterado y mejoras).
 */
public class Pokemon {

    public static final int NIVEL_MAXIMO = 100;

    // ---- Datos permanentes ----
    protected int id;            // id_capturado (o id_pokedex si es salvaje / del rival)
    protected int idPokedex;
    protected String nombre;
    protected String mote;
    protected int nivel = 1;
    protected int xp;
    protected int equipo;        // 1 = titular, 2 = equipo 2, 3 = caja
    protected Tipo tipo1;
    protected Tipo tipo2;
    protected double vitalidad;  // vida máxima
    protected double ataque;
    protected double defensa;
    protected double ataqueEspecial;
    protected double defensaEspecial;
    protected double estamina;   // estamina máxima
    protected double velocidad;
    protected int fertilidad;
    protected Movimiento movimiento1;
    protected Movimiento movimiento2;
    protected Movimiento movimiento3;
    protected Movimiento movimiento4;
    protected String foto;
    protected String fotoEspalda;

    // ---- Estado durante el combate ----
    private double vida;
    private double estaminaActual;
    private double bonusAtaque, bonusDefensa, bonusAtaqueEspecial, bonusDefensaEspecial;
    private ListaEstados estado;
    private int turnosEstado;

    public Pokemon() {
        this.nombre = "";
        this.mote = "";
        this.foto = "";
        this.fotoEspalda = "";
    }

    /** Constructor copia. */
    public Pokemon(Pokemon o) {
        this.id = o.id;
        this.idPokedex = o.idPokedex;
        this.nombre = o.nombre;
        this.mote = o.mote;
        this.nivel = o.nivel;
        this.xp = o.xp;
        this.equipo = o.equipo;
        this.tipo1 = o.tipo1;
        this.tipo2 = o.tipo2;
        this.vitalidad = o.vitalidad;
        this.ataque = o.ataque;
        this.defensa = o.defensa;
        this.ataqueEspecial = o.ataqueEspecial;
        this.defensaEspecial = o.defensaEspecial;
        this.estamina = o.estamina;
        this.velocidad = o.velocidad;
        this.fertilidad = o.fertilidad;
        this.movimiento1 = o.movimiento1;
        this.movimiento2 = o.movimiento2;
        this.movimiento3 = o.movimiento3;
        this.movimiento4 = o.movimiento4;
        this.foto = o.foto;
        this.fotoEspalda = o.fotoEspalda;
    }

    // =====================================================================================
    // Combate
    // =====================================================================================

    /** Deja al Pokémon listo para empezar un combate: vida y estamina al máximo, sin estados. */
    public void prepararCombate() {
        vida = vitalidad;
        estaminaActual = estamina;
        bonusAtaque = bonusDefensa = bonusAtaqueEspecial = bonusDefensaEspecial = 0;
        estado = null;
        turnosEstado = 0;
    }

    public boolean estaDebilitado() {
        return vida <= 0;
    }

    public double getVida() {
        return vida;
    }

    public void setVida(double vida) {
        this.vida = Math.max(0, Math.min(vitalidad, vida));
    }

    /** Resta vida y devuelve la cantidad realmente perdida. */
    public double recibirDanio(double danio) {
        double antes = vida;
        setVida(vida - danio);
        return antes - vida;
    }

    /** Suma vida y devuelve la cantidad realmente curada. */
    public double curar(double cantidad) {
        double antes = vida;
        setVida(vida + cantidad);
        return vida - antes;
    }

    public double getEstaminaActual() {
        return estaminaActual;
    }

    public void setEstaminaActual(double estaminaActual) {
        this.estaminaActual = Math.max(0, Math.min(estamina, estaminaActual));
    }

    public boolean puedeUsar(Movimiento m) {
        return m != null && estaminaActual >= m.costeEstamina();
    }

    public double getAtaqueCombate() {
        return ataque + bonusAtaque;
    }

    public double getDefensaCombate() {
        return defensa + bonusDefensa;
    }

    public double getAtaqueEspecialCombate() {
        return ataqueEspecial + bonusAtaqueEspecial;
    }

    public double getDefensaEspecialCombate() {
        return defensaEspecial + bonusDefensaEspecial;
    }

    /** Aplica una mejora (con tope: como mucho se duplica cada estadística). Devuelve true si cambió algo. */
    public boolean mejorar(int atq, int def, int atqEsp, int defEsp) {
        double a = bonusAtaque, d = bonusDefensa, ae = bonusAtaqueEspecial, de = bonusDefensaEspecial;
        bonusAtaque = Math.min(ataque, bonusAtaque + atq);
        bonusDefensa = Math.min(defensa, bonusDefensa + def);
        bonusAtaqueEspecial = Math.min(ataqueEspecial, bonusAtaqueEspecial + atqEsp);
        bonusDefensaEspecial = Math.min(defensaEspecial, bonusDefensaEspecial + defEsp);
        return a != bonusAtaque || d != bonusDefensa || ae != bonusAtaqueEspecial || de != bonusDefensaEspecial;
    }

    public ListaEstados getEstado() {
        return estado;
    }

    public int getTurnosEstado() {
        return turnosEstado;
    }

    public void setEstado(ListaEstados estado) {
        setEstado(estado, estado == null ? 0 : 3);
    }

    public void setEstado(ListaEstados estado, int turnos) {
        this.estado = estado;
        this.turnosEstado = estado == null ? 0 : turnos;
    }

    /** Resta un turno al estado alterado. Devuelve true si el estado se acaba de terminar. */
    public boolean pasarTurnoEstado() {
        if (estado == null) return false;
        turnosEstado--;
        if (turnosEstado <= 0) {
            estado = null;
            turnosEstado = 0;
            return true;
        }
        return false;
    }

    // =====================================================================================
    // Niveles
    // =====================================================================================

    /** Experiencia necesaria para pasar del nivel actual al siguiente. */
    public int xpParaSubir() {
        return 40 + nivel * 30;
    }

    /**
     * Suma experiencia y sube de nivel tantas veces como haga falta.
     * @return número de niveles subidos
     */
    public int ganarExperiencia(int cantidad) {
        if (nivel >= NIVEL_MAXIMO) return 0;
        xp += cantidad;
        int subidos = 0;
        while (nivel < NIVEL_MAXIMO && xp >= xpParaSubir()) {
            xp -= xpParaSubir();
            nivel++;
            subidos++;
            subirStats();
        }
        if (nivel >= NIVEL_MAXIMO) xp = 0;
        return subidos;
    }

    /** Sube las estadísticas un poco al subir de nivel. */
    public void subirStats() {
        vitalidad += Funcion.random(2, 4);
        ataque += Funcion.random(1, 3);
        defensa += Funcion.random(1, 3);
        ataqueEspecial += Funcion.random(1, 3);
        defensaEspecial += Funcion.random(1, 3);
        velocidad += Funcion.random(1, 3);
        estamina += Funcion.random(2, 5);
    }

    /** Suma de las estadísticas base: sirve para saber lo "fuerte" que es (y lo difícil de capturar). */
    public double sumaStats() {
        return ataque + defensa + ataqueEspecial + defensaEspecial + velocidad;
    }

    // =====================================================================================
    // Utilidades
    // =====================================================================================

    /** El mote si lo tiene; si no, el nombre. */
    public String getNombreMostrado() {
        return (mote != null && !mote.isBlank()) ? mote.trim() : nombre;
    }

    /** Movimientos que tiene aprendidos (sin huecos vacíos). */
    public List<Movimiento> getMovimientos() {
        List<Movimiento> lista = new ArrayList<>();
        if (movimiento1 != null) lista.add(movimiento1);
        if (movimiento2 != null) lista.add(movimiento2);
        if (movimiento3 != null) lista.add(movimiento3);
        if (movimiento4 != null) lista.add(movimiento4);
        return lista;
    }

    public void setMovimientos(List<Movimiento> movimientos) {
        // Sin operador ternario a propósito: el compilador de VS Code (ECJ) lo traduce mal en este proyecto
        // porque el paquete "Pokemon" y la clase "Pokemon" se llaman igual.
        movimiento1 = null;
        movimiento2 = null;
        movimiento3 = null;
        movimiento4 = null;
        if (movimientos.size() > 0) movimiento1 = movimientos.get(0);
        if (movimientos.size() > 1) movimiento2 = movimientos.get(1);
        if (movimientos.size() > 2) movimiento3 = movimientos.get(2);
        if (movimientos.size() > 3) movimiento4 = movimientos.get(3);
    }

    public boolean tieneTipo(Tipo tipo) {
        return tipo != null && (tipo == tipo1 || tipo == tipo2);
    }

    @Override
    public String toString() {
        return getNombreMostrado();
    }

    // =====================================================================================
    // Getters y setters
    // =====================================================================================

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getIdPokedex() { return idPokedex; }
    public void setIdPokedex(int idPokedex) { this.idPokedex = idPokedex; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getMote() { return mote; }
    public void setMote(String mote) { this.mote = mote; }
    public int getNivel() { return nivel; }
    public void setNivel(int nivel) { this.nivel = Math.max(1, nivel); }
    public int getXp() { return xp; }
    public void setXp(int xp) { this.xp = xp; }
    public int getEquipo() { return equipo; }
    public void setEquipo(int equipo) { this.equipo = equipo; }
    public Tipo getTipo1() { return tipo1; }
    public void setTipo1(Tipo tipo1) { this.tipo1 = tipo1; }
    public Tipo getTipo2() { return tipo2; }
    public void setTipo2(Tipo tipo2) { this.tipo2 = tipo2; }
    public double getVitalidad() { return vitalidad; }
    public void setVitalidad(double vitalidad) { this.vitalidad = vitalidad; }
    public double getAtaque() { return ataque; }
    public void setAtaque(double ataque) { this.ataque = ataque; }
    public double getDefensa() { return defensa; }
    public void setDefensa(double defensa) { this.defensa = defensa; }
    public double getAtaqueEspecial() { return ataqueEspecial; }
    public void setAtaqueEspecial(double ataqueEspecial) { this.ataqueEspecial = ataqueEspecial; }
    public double getDefensaEspecial() { return defensaEspecial; }
    public void setDefensaEspecial(double defensaEspecial) { this.defensaEspecial = defensaEspecial; }
    public double getEstamina() { return estamina; }
    public void setEstamina(double estamina) { this.estamina = estamina; }
    public double getVelocidad() { return velocidad; }
    public void setVelocidad(double velocidad) { this.velocidad = velocidad; }
    public int getFertilidad() { return fertilidad; }
    public void setFertilidad(int fertilidad) { this.fertilidad = fertilidad; }
    public Movimiento getMovimiento1() { return movimiento1; }
    public void setMovimiento1(Movimiento m) { this.movimiento1 = m; }
    public Movimiento getMovimiento2() { return movimiento2; }
    public void setMovimiento2(Movimiento m) { this.movimiento2 = m; }
    public Movimiento getMovimiento3() { return movimiento3; }
    public void setMovimiento3(Movimiento m) { this.movimiento3 = m; }
    public Movimiento getMovimiento4() { return movimiento4; }
    public void setMovimiento4(Movimiento m) { this.movimiento4 = m; }
    public String getFoto() { return foto; }
    public void setFoto(String foto) { this.foto = foto; }
    public String getFotoEspalda() { return fotoEspalda; }
    public void setFotoEspalda(String fotoEspalda) { this.fotoEspalda = fotoEspalda; }
}
