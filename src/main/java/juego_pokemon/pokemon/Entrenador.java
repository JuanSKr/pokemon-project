package juego_pokemon.pokemon;

import java.util.LinkedList;
import juego_pokemon.pokemon.Tienda.DineroInsuficienteException;
import juego_pokemon.pokemon.Tienda.ObjetoYaEnMochilaException;

public class Entrenador {
	// Atributos privados de la clase Entrenador
	protected String nombre;
	protected int dinero;
	protected LinkedList<Pokemon> equipo1;
	protected LinkedList<Pokemon> equipo2;
	protected LinkedList<Pokemon> caja;
	protected LinkedList<Objeto> mochila;

	// Constructor de la clase Entrenador
	public Entrenador() {
		super();
		this.nombre = nombre;
		this.dinero = dinero;
		this.equipo1 = new LinkedList<>();
		this.equipo2 = new LinkedList<>();
		this.caja = new LinkedList<>();
		this.mochila = new LinkedList<>();
	}

	// MÉTODO PARA MOVER UN OBJETO DE LA MOCHILA A LA CAJA
	public void moverACaja() {
	}

	// MÉTODO PARA MOVER UN POKEMON DEL EQUIPO1 O EQUIPO2 A LA MOCHILA
	public void moverAEquipo() {
	}

	// MÉTODO PARA ENTRENAR A UN POKEMON DE LA MOCHILA
	public void entrenar() {
	}

	// MÉTODO PARA CAPTURAR UN NUEVO POKEMON Y AÑADIRLO A LA CAJA
	public void capturar() {
	}

	// MÉTODO PARA COMBATIR CON UN POKEMON DEL EQUIPO1 O EQUIPO2
	public void combatir() {
	}

	// MÉTODO PARA CRIAR UN HUEVO POKEMON
	public void criar() {
	}

	public static void restarSaldo(int precio) {
		// TODO Auto-generated method stub

	}

	public static void setObjeto(Objeto objetoComprado) {
		// TODO Auto-generated method stub

	}

	public void setDinero() {
		// TODO Auto-generated method stub

	}

	public LinkedList<Objeto> getMochila() {

		return null;
	}

	public static double getDinero() {

		return 0;
	}

	public void setDinero(double precio) {

	}

	// MÉTODO PARA COMPRAR UN OBJETO DE LA TIENDA Y AÑADIRLO A LA MOCHILA
	public void comprarObjeto(Objeto objeto) throws DineroInsuficienteException, ObjetoYaEnMochilaException {
		if (dinero < objeto.precio) {
			throw new DineroInsuficienteException();
		} else if (mochila.contains(objeto)) {
			throw new ObjetoYaEnMochilaException();
		} else {
			dinero -= objeto.precio;
			mochila.add(objeto);
			System.out.println("Has comprado el objeto " + objeto.nombre + ".");
		}
	}

	// MÉTODO PARA VER EL CONTENIDO DE LA MOCHILA
	public void verMochila() {
		System.out.println("Contenido de la mochila:");
		for (Objeto objeto : mochila) {
			System.out.println(" - " + objeto.nombre);
		}
	}

	public int getIdObjeto() {
		return getIdObjeto();
	}

}
