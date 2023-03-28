import java.util.LinkedList;

//Hay que implementar la funciones a las clases 
//moverACaja/moverAEquipo/entrenar/capturar/combatir/criar


public class Entrenador {
	// Atributos privados de la clase Entrenador
	private String nombre;
	private int dinero;
	private LinkedList<Pokemon>equipo1;
	private LinkedList<Pokemon>equipo2;
	private LinkedList<Pokemon>caja;
	private LinkedList<Pokemon>mochila;
	
	
	// Constructor de la clase Entrenador
	public Entrenador(String nombre, int dinero) {
		super();
		this.nombre = nombre;
		this.dinero = dinero;
		this.equipo1 = new LinkedList<>();
        this.equipo2 = new LinkedList<>();
        this.caja = new LinkedList<>();
        this.mochila = new LinkedList<>();
	}
	// Método para mover un objeto de la mochila a la caja
	 public void moverACaja() {}
	 
	// Método para mover un Pokemon del equipo1 o equipo2 a la mochila
	 public void moverAEquipo() {}
	 
	 // Método para entrenar a un Pokemon de la mochila
	 public void entrenar() {}
	 
	 // Método para capturar un nuevo Pokemon y añadirlo a la caja
	 public void capturar() {}
	 
	 // Método para combatir con un Pokemon del equipo1 o equipo2
	 public void combatir() {}
	 
	// Método para criar un huevo Pokemon
	 public void criar() {}
	
}
	
	
	
	
	 
	

	

