package juego_pokemon.pokemon;

import java.util.HashMap;

//FALTAN POR AÑADIR MÉTODOS GETTERS Y SETTERS


public class Objeto {

    protected int idObjeto;
    protected String nombre;
    protected double ataque;
    protected double defensa;
    protected double ataqueEspecial;
    protected double defensaEspecial;
    protected double vitalidad;
    protected double precio;

    // CONSTRUCTOR POR DEFECTO
    public Objeto() {
        this.idObjeto = 0;
        this.nombre = "";
        this.ataque = 0;
        this.defensa = 0;
        this.ataqueEspecial = 0;
        this.defensaEspecial = 0;
        this.vitalidad = 0;
        this.precio = 5;//PRECIO ESTABLECIDO AL OBJETO
    }

    // CONSTRUCTOR CON PARÁMETROS
    public Objeto(int idObjeto, String nombre, double ataque, double defensa, double ataqueEspecial,
                  double defensaEspecial, double vitalidad, double precio) {
        this.idObjeto = idObjeto;
        this.nombre = nombre;
        this.ataque = ataque;
        this.defensa = defensa;
        this.ataqueEspecial = ataqueEspecial;
        this.defensaEspecial = defensaEspecial;
        this.vitalidad = vitalidad;
        this.precio = precio;
    }

    // HASHMAP CON LOS OBJETOS DISPONIBLES EN LA TIENDA
    public static final HashMap<String, Objeto> objetosDisponibles = new HashMap<String, Objeto>() {{
        put("pesa", new Objeto(1, "pesa", 1.2, 1.2, 1.0, 1.0, 1.0, 10.0));
        put("pluma", new Objeto(2, "pluma", 1.0, 0.8, 1.0, 0.8, 1.3, 10.0));
        put("chaleco", new Objeto(3, "chaleco", 0.85, 1.2, 0.85, 1.2, 1.0, 10.0));
        put("bastón", new Objeto(4, "bastón", 1.0, 1.0, 1.0, 1.0, 1.2, 10.0));
        put("pilas", new Objeto(5, "pilas", 1.0, 1.0, 0.7, 0.7, 1.0, 10.0));
    }};

    public int getIdObjeto() {
        // TODO Auto-generated method stub
        return 0;
    }

}
