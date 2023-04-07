package juego_pokemon.pokemon;

//FALTA POR AÑADIR LA MEJORA DEL POKEMON UNA VEZ USA EL OBJETO

//CLASE OBJETO
class Objeto {
	private int id;
	private String nombre;
	static int precio;
	private String efecto;
	public String idObjeto;

	// Constructor
	public Objeto(int id, String nombre, int precio, String efecto) {
		this.id = id;
		this.nombre = nombre;
		Objeto.precio = precio;
		this.efecto = efecto;
	}

	// MÉTODO PARA OBTENER EL ID DEL OBJETO
	public int getId() {
		return id;
	}

	// MÉTODO PARA OBTENER EL NOMBRE DEL OBJETO
	public String getNombre() {
		return nombre;
	}

	// MÉTODO PARA OBTENER EL PRECIO DEL OBJETO
	public int getPrecio() {
		return precio;
	}

	// MÉTODO PARA OBTENER LA DESCRIPCIÓN DEL OBJETO
	public String getDescripcion() {
		return efecto;
	}

	// MÉTODO PARA MOSTRAR LA INFORMACIÓN DEL OBJETO
	@Override
	public String toString() {
		return "*OBJETO--> " + id + " NOMBRE--> " + nombre + "  PRECIO--> " + precio + "  EFECTO-->" + efecto;
	}

	public boolean comprobarCompra(Entrenador entrenador) {
	    return entrenador.getDinero() >= precio;
	}


}