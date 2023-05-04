package Pokemon.Combate;

import Pokemon.Database.MySQL;
import Pokemon.Pokemon.Pokemon;

import java.util.LinkedList;

public class Rival {

    protected String nombre;
    protected String foto;
    protected LinkedList<Pokemon> equipo;


    /**
     *
     * @return Este método genera un rival aleatorio y lo devuelve.
     */

    public static Rival generarRival() { //Añadir fotos

        Rival rival = new Rival();

        String[] rivales = {
                "Entrenador Iniesta",
                "Entrenador Guti",
                "Entrenador Hamilton",
                "Montañero Marín",
                "Entrenadora Paquita"
        };

        int random = (int) (Math.random() * 4 + 0);

        String nombreRival = rivales[random];

        rival.setNombre(nombreRival);

        return rival;

    }


    public Rival(String nombre, String foto, LinkedList<Pokemon> equipo) {
        this.nombre = nombre;
        this.foto = foto;
        this.equipo = equipo;
    }

    public Rival() {
        this.nombre = "";
        this.foto = "";
        this.equipo = null;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public LinkedList<Pokemon> getEquipo() {
        return equipo;
    }

    public void setEquipo(LinkedList<Pokemon> equipo) {
        this.equipo = equipo;
    }

    @Override
    public String toString() {
        return nombre;
    }

    public static void main(String[] args) {
//        System.out.println(generarRival());

        Pokemon pokemon = MySQL.generarPokemon();

        System.out.println(pokemon);
    }
}


