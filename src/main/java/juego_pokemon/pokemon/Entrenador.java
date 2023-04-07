package juego_pokemon.pokemon;

import java.util.HashMap;
import java.util.Map;

//CLASE ENTRENADOR
class Entrenador {
	static  int dinero;
	private static Map<Integer, Objeto> mochila;

	// CONSTRUCTOR
	public Entrenador() {
		Entrenador.dinero = 500;
		Entrenador.mochila = new HashMap<>();
	}

	// MÉTODO PARA COMPRAR UN OBJETO
	public boolean comprarObjeto(Objeto objeto) {
		if (dinero >= objeto.getPrecio()) {
			dinero -= objeto.getPrecio();
			mochila.put(objeto.getId(), objeto);

			TiendaGUI.mostrarMensaje("¡Objeto " + objeto.getNombre() + " comprado y añadido a la mochila!");
			return true;
		} else {
			TiendaGUI.mostrarMensaje("No tienes suficiente dinero para comprar este objeto.");
			return false;
		}
	}

	// MÉTODO PARA OBTENER EL DINERO DEL ENTRENADOR
	public static int getDinero() {
	    return dinero;
	}


	// MÉTODO PARA OBTENER LA MOCHILA DEL ENTRENADOR
	public static Map<Integer, Objeto> getMochila() {
		return mochila;
	}

//MÉTODO PARA AGREGAR UN OBJETO A LA MOCHILA DEL ENTRENADOR
	public void agregarObjetoMochila(Objeto objeto) {
		mochila.put(objeto.getId(), objeto);
	}

//MÉTODO PARA ESTABLECER EL DINERO DEL ENTRENADOR
	public void setDinero(int dinero) {
		Entrenador.dinero = dinero;
	}
	
	// MÉTODO PARA OBTENER EL NÚMERO DE OBJETOS EN LA MOCHILA DEL ENTRENADOR
	public int getNumeroObjetosMochila() {
	return mochila.size();
	}
	
	// MÉTODO PARA OBTENER UN OBJETO ESPECÍFICO DE LA MOCHILA DEL ENTRENADOR
	public Objeto obtenerObjetoMochila(int id) {
	return mochila.get(id);
	}

	// MÉTODO PARA ELIMINAR UN OBJETO DE LA MOCHILA DEL ENTRENADOR
	public void eliminarObjetoMochila(int id) {
	mochila.remove(id);
	}


	// MÉTODO PARA COMPRAR UN POKÉMON
	public boolean comprarPokemon(Pokemon pokemon) {
	if (dinero >= pokemon.getPrecio()) {
	dinero -= pokemon.getPrecio();
	TiendaGUI.mostrarMensaje("¡Pokémon " + pokemon.getNombre() + " comprado y añadido a la mochila!");
	return true;
	} else {
	TiendaGUI.mostrarMensaje("No tienes suficiente dinero para comprar este Pokémon.");
	return false;
	}
	}
}
