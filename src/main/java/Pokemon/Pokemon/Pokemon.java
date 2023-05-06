package Pokemon.Pokemon;

import Pokemon.Combate.Movimiento;
import Pokemon.Database.PokemonCRUD;
import Pokemon.Funcionalidad.Funcion;

public class Pokemon {

    protected int id;
    protected String nombre;
    protected String mote;
    protected int nivel;
    protected int xp;
    protected Tipo tipo1;
    protected Tipo tipo2;
    protected char sexo;
    protected int vitalidad;
    protected int ataque;
    protected int defensa;
    protected int ataqueEspecial;
    protected int defensaEspecial;
    protected int estamina;
    protected int velocidad;
    protected ListaEstados estado;
    protected int fertilidad;
    protected Objeto obj;
    protected Movimiento movimiento1;
    protected Movimiento movimiento2;
    protected Movimiento movimiento3;
    protected Movimiento movimiento4;
    protected String foto;

    // Constructor con todos los parámetros

    public Pokemon(int id, String nombre, String mote, int nivel, int xp, Tipo tipo1, Tipo tipo2, char sexo, int vitalidad,
                   int ataque, int defensa, int ataqueEspecial, int defensaEspecial, int estamina, int velocidad,
                   ListaEstados estado, int fertilidad, Objeto obj, Movimiento movimiento1, Movimiento movimiento2, Movimiento movimiento3, Movimiento movimiento4, String foto) {
        this.id = id;
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
        this.foto = foto;
    }

    // Constructor por defecto

    public Pokemon() {
        this.id = 0;
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
        this.movimiento1 = null;
        this.movimiento2 = null;
        this.movimiento3 = null;
        this.movimiento4 = null;
        this.foto = "";
    }

    // Constructor copia

    public Pokemon(Pokemon capturado) {
        this.id = capturado.id;
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
        this.foto = capturado.foto;
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

    public int getVitalidad() {
        return vitalidad;
    }

    public void setVitalidad(int vitalidad) {
        this.vitalidad = vitalidad;
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

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public int getDefensaEspecial() {
        return defensaEspecial;
    }

    public void setDefensaEspecial(int defensaEspecial) {
        this.defensaEspecial = defensaEspecial;
    }

    public int getEstamina() {
        return estamina;
    }

    public void setEstamina(int estamina) {
        this.estamina = estamina;
    }

    public int getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(int velocidad) {
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

    public Movimiento getMovimiento1() {
        return movimiento1;
    }

    public void setMovimiento1(Movimiento movimiento1) {
        this.movimiento1 = movimiento1;
    }

    public Movimiento getMovimiento2() {
        return movimiento2;
    }

    public void setMovimiento2(Movimiento movimiento2) {
        this.movimiento2 = movimiento2;
    }

    public Movimiento getMovimiento3() {
        return movimiento3;
    }

    public void setMovimiento3(Movimiento movimiento3) {
        this.movimiento3 = movimiento3;
    }

    public Movimiento getMovimiento4() {
        return movimiento4;
    }

    public void setMovimiento4(Movimiento movimiento4) {
        this.movimiento4 = movimiento4;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /**
     * Si el pokemon tiene mote se mostrará el mote en lugar del nombre, si no tiene mote se mostrará el nombre.
     * @return toString con condicional
     */
    @Override
    public String toString() {
        return "Pokemon{" +
                "nombre='" + nombre + '\'' +
                ", mote='" + mote + '\'' +
                ", nivel=" + nivel +
                ", xp=" + xp +
                ", tipo1=" + tipo1 +
                ", tipo2=" + tipo2 +
                ", sexo=" + sexo +
                ", vitalidad=" + vitalidad +
                ", ataque=" + ataque +
                ", defensa=" + defensa +
                ", ataqueEspecial=" + ataqueEspecial +
                ", defensaEspecial=" + defensaEspecial +
                ", estamina=" + estamina +
                ", velocidad=" + velocidad +
                ", estado=" + estado +
                ", fertilidad=" + fertilidad +
                ", obj=" + obj +
                ", movimiento1=" + movimiento1 +
                ", movimiento2=" + movimiento2 +
                ", movimiento3=" + movimiento3 +
                ", movimiento4=" + movimiento4 +
                ", foto='" + foto + '\'' +
                '}';
    }

// Metodos propios

    /**
     * Un Pokemon aprende un nuevo movimiento cada 3 niveles.
     * Si el nivel del Pokemon es múltiplo de 3, llama al método addMovimiento().
     * @param pokemon
     */

    public static void aprenderMovimiento(Pokemon pokemon) {

        if((pokemon.getNivel() % 3) == 0) {
            addMovimiento(pokemon);
        }

    }

    /**
     * Este método, dependiendo del valor que se le pase a opción asigna el movimiento.
     * Es un método para complementar el método aprenderMovimiento().
     * @param pokemon
     */

    public static void addMovimiento(Pokemon pokemon) {

        Movimiento movimiento = PokemonCRUD.generarMovimiento();

        int opcion = 1; //Cambiar -> Hay que hacer esto en botones

        switch (opcion) {

            case 1:
                pokemon.setMovimiento1(movimiento);
                break;

            case 2:
                pokemon.setMovimiento2(movimiento);
                break;

            case 3:
                pokemon.setMovimiento3(movimiento);
                break;

            case 4:
                pokemon.setMovimiento4(movimiento);
                break;

        }

    }

    public static void subirNivel(Pokemon pokemon) {

        int xpNecesario = (pokemon.getNivel() * 10);
        int nuevoNivel = (pokemon.getNivel() + 1);

        if(pokemon.getXp() == xpNecesario) {
            pokemon.setNivel(nuevoNivel);
            subirStats(pokemon);
        }
    }

    public static void subirStats(Pokemon pokemon) {

        pokemon.setVitalidad(pokemon.getVitalidad() + Funcion.random(1, 5));
        pokemon.setVelocidad(pokemon.getVelocidad() + Funcion.random(1, 5));
        pokemon.setEstamina(pokemon.getEstamina() + Funcion.random(1, 5));
        pokemon.setAtaque(pokemon.getAtaque() + Funcion.random(1, 5));
        pokemon.setDefensa(pokemon.getDefensa() + Funcion.random(1, 5));
        pokemon.setAtaqueEspecial(pokemon.getAtaqueEspecial() + Funcion.random(1, 5));
        pokemon.setDefensaEspecial(pokemon.getDefensaEspecial() + Funcion.random(1, 5));

    }

    public static void main(String[] args) {


    }

}

