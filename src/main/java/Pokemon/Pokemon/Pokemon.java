package Pokemon.Pokemon;

import Pokemon.Menus.Pokedex;

public class Pokemon {

    protected String nombre;
    protected String mote;
    protected int nivel;
    protected int xp;
    protected Tipo tipo1;
    protected Tipo tipo2;
    protected char sexo;
    protected double vitalidad;
    protected double ataque;
    protected double defensa;
    protected double ataqueEspecial;
    protected double defensaEspecial;
    protected double estamina;
    protected double velocidad;
    protected ListaEstados estado;
    protected int fertilidad;
    protected Objeto obj;
    protected String movimiento1;
    protected String movimiento2;
    protected String movimiento3;
    protected String movimiento4;

    // Constructor con todos los parámetros

    public Pokemon(String nombre, String mote, int nivel, int xp, Tipo tipo1, Tipo tipo2, char sexo, double vitalidad,
                   double ataque, double defensa, double ataqueEspecial, double defensaEspecial, double estamina, double velocidad,
                   ListaEstados estado, int fertilidad, Objeto obj, String movimiento1, String movimiento2, String movimiento3, String movimiento4) {
        this.nombre = nombre;
        this.mote = mote;
        this.nivel = nivel;
        this.xp = xp;
        this.tipo1 = tipo1;
        this.tipo2 = tipo2;
        this.sexo = sexo;
        this.vitalidad = vitalidad;
        this.ataque = ataque;
        this.defensa = defensa;
        this.ataqueEspecial = ataqueEspecial;
        this.defensaEspecial = defensaEspecial;
        this.estamina = estamina;
        this.velocidad = velocidad;
        this.estado = estado;
        this.fertilidad = fertilidad;
        this.obj = obj;
        this.movimiento1 = movimiento1;
        this.movimiento2 = movimiento2;
        this.movimiento3 = movimiento3;
        this.movimiento4 = movimiento4;
    }

    // Constructor por defecto

    public Pokemon() {
        this.nombre = "";
        this.mote = "";
        this.nivel = 0;
        this.xp = 0;
        this.tipo1 = null;
        this.tipo2 = null;
        this.sexo = ' ';
        this.vitalidad = 0;
        this.ataque = 0;
        this.defensa = 0;
        this.ataqueEspecial = 0;
        this.defensaEspecial = 0;
        this.estamina = 0;
        this.velocidad = 0;
        this.estado = null;
        this.fertilidad = 0;
        this.obj = null;
        this.movimiento1 = "";
        this.movimiento2 = "";
        this.movimiento3 = "";
        this.movimiento4 = "";
    }

    // Constructor copia

    public Pokemon(Pokemon capturado) {
        this.nombre = capturado.nombre;
        this.mote = capturado.mote;
        this.nivel = capturado.nivel;
        this.xp = capturado.xp;
        this.tipo1 = capturado.tipo1;
        this.tipo2 = capturado.tipo2;
        this.sexo = capturado.sexo;
        this.vitalidad = capturado.vitalidad;
        this.ataque = capturado.ataque;
        this.defensa = capturado.defensa;
        this.ataqueEspecial = capturado.ataqueEspecial;
        this.defensaEspecial = capturado.defensaEspecial;
        this.estamina = capturado.estamina;
        this.velocidad = capturado.velocidad;
        this.estado = capturado.estado;
        this.fertilidad = capturado.fertilidad;
        this.obj = capturado.obj;
        this.movimiento1 = capturado.movimiento1;
        this.movimiento2 = capturado.movimiento2;
        this.movimiento3 = capturado.movimiento3;
        this.movimiento4 = capturado.movimiento4;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMote() {
        return mote;
    }

    public void setMote(String mote) {
        this.mote = mote;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public Tipo getTipo1() {
        return tipo1;
    }

    public void setTipo1(Tipo tipo1) {
        this.tipo1 = tipo1;
    }

    public Tipo getTipo2() {
        return tipo2;
    }

    public void setTipo2(Tipo tipo2) {
        this.tipo2 = tipo2;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public double getVitalidad() {
        return vitalidad;
    }

    public void setVitalidad(double vitalidad) {
        this.vitalidad = vitalidad;
    }

    public double getAtaque() {
        return ataque;
    }

    public void setAtaque(double ataque) {
        this.ataque = ataque;
    }

    public double getDefensa() {
        return defensa;
    }

    public void setDefensa(double defensa) {
        this.defensa = defensa;
    }

    public double getAtaqueEspecial() {
        return ataqueEspecial;
    }

    public void setAtaqueEspecial(double ataqueEspecial) {
        this.ataqueEspecial = ataqueEspecial;
    }

    public double getDefensaEspecial() {
        return defensaEspecial;
    }

    public void setDefensaEspecial(double defensaEspecial) {
        this.defensaEspecial = defensaEspecial;
    }

    public double getEstamina() {
        return estamina;
    }

    public void setEstamina(double estamina) {
        this.estamina = estamina;
    }

    public double getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(double velocidad) {
        this.velocidad = velocidad;
    }

    public ListaEstados getEstado() {
        return estado;
    }

    public void setEstado(ListaEstados estado) {
        this.estado = estado;
    }

    public int getFertilidad() {
        return fertilidad;
    }

    public void setFertilidad(int fertilidad) {
        this.fertilidad = fertilidad;
    }

    public Objeto getObj() {
        return obj;
    }

    public void setObj(Objeto obj) {
        this.obj = obj;
    }

    public String getMovimiento1() {
        return movimiento1;
    }

    public void setMovimiento1(String movimiento1) {
        this.movimiento1 = movimiento1;
    }

    public String getMovimiento2() {
        return movimiento2;
    }

    public void setMovimiento2(String movimiento2) {
        this.movimiento2 = movimiento2;
    }

    public String getMovimiento3() {
        return movimiento3;
    }

    public void setMovimiento3(String movimiento3) {
        this.movimiento3 = movimiento3;
    }

    public String getMovimiento4() {
        return movimiento4;
    }

    public void setMovimiento4(String movimiento4) {
        this.movimiento4 = movimiento4;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    @Override
    public String toString() {
        return nombre;
    }

    public static Pokemon pokeRandom() {

        int random = (int)(Math.random() *10+1);

        return Pokedex.Pikachu;



    }
}

