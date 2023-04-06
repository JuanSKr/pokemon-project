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
	protected int precio;

	// CONSTRUCTOR POR DEFECTO
	public Objeto() {
		this.idObjeto = 0;
		this.nombre = "";
		this.ataque = 0;
		this.defensa = 0;
		this.ataqueEspecial = 0;
		this.defensaEspecial = 0;
		this.vitalidad = 0;
		this.precio = 0;
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
		this.precio = 10; // PRECIO ESTABLECIDO AL OBJETO
	}

	// HASHMAP CON LOS OBJETOS DISPONIBLES EN LA TIENDA
	public static final HashMap<String, Objeto> objetosDisponibles = new HashMap<String, Objeto>() {
		{
			put("pesa", new Objeto(1, "pesa", 1.2, 1.2, 1.0, 1.0, 1.0, 10));
			put("pluma", new Objeto(2, "pluma", 1.0, 0.8, 1.0, 0.8, 1.3, 10));
			put("chaleco", new Objeto(3, "chaleco", 0.85, 1.2, 0.85, 1.2, 1.0, 10));
			put("bastón", new Objeto(4, "bastón", 1.0, 1.0, 1.0, 1.0, 1.2, 10));
			put("pilas", new Objeto(5, "pilas", 1.0, 1.0, 0.7, 0.7, 1.0, 10));
		}
	};

	public int getIdObjeto() {
		// TODO Auto-generated method stub
		return idObjeto;
	}

	@Override
	public String toString() {
		return "ID: " + idObjeto + ", Nombre: " + nombre + ", Precio: " + precio;
	}
	

}
