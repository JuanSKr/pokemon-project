package juego_pokemon.pokemon;

import java.util.HashMap;
import java.util.LinkedList;

public class Tienda {
	private HashMap<Integer, Objeto> objetosDisponibles; // MAPA DE OBJETOS DISPONIBLES EN LA TIENDA

	public Tienda() {
		// INICIALIZAR MAPA DE OBJETOS EN VENTA
		objetosDisponibles = new HashMap<>();
		for (Objeto objeto : Objeto.objetosDisponibles.values()) {
			objetosDisponibles.put(objeto.idObjeto, objeto);
		}
	}

	public void mostrarCatalogo() {
		System.out.println("Catálogo de objetos:");
		for (Objeto objeto : objetosDisponibles.values()) {
			System.out.println("ID: " + objeto.idObjeto + ", Nombre: " + objeto.nombre + ", Precio: " + objeto.precio);
		}
	}

	public void comprarObjeto(Entrenador entrenador, int idObjeto)
			throws ObjetoNoEncontradoException, DineroInsuficienteException, ObjetoYaEnMochilaException {
		Objeto objetoComprado = objetosDisponibles.get(idObjeto);
		if (objetoComprado == null) {
			throw new ObjetoNoEncontradoException();
		}

		if (entrenador.getDinero() < objetoComprado.precio) {
			throw new DineroInsuficienteException();
		}

		if (entrenador.getMochila().contains(objetoComprado)) {
			throw new ObjetoYaEnMochilaException();
		}

		entrenador.setDinero(objetoComprado.precio);
		entrenador.getMochila().add(objetoComprado);
		System.out.println("Has comprado el objeto " + objetoComprado.nombre + ".");

	}
}

class ObjetoNoEncontradoException extends Exception {
// EXCEPCIÓN LANZADA CUANDO NO SE ENCUENTRA EL OBJETO CON EL ID INDICADO EN LA TIENDA
}

class DineroInsuficienteException extends Exception {
// EXCEPCIÓN LANZADA CUANDO EL ENTRENADOR NO TIENE SUFICIENTE DINERO PARA COMPRAR EL OBJETO
}

class ObjetoYaEnMochilaException extends Exception {
// EXCEPCIÓN LANZADA CUANDO EL OBJETO QUE SE INTENTA COMPRAR YA ESTÁ EN LA MOCHILA DEL ENTRENADOR

}
