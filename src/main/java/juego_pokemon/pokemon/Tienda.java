package juego_pokemon.pokemon;

//FALTA POR AÑADIR EL METODO DE REALIZAR LA TRANSACCIÓN DE COMPRA EN (comprarObjeto)

import java.util.LinkedList;

public class Tienda {

	protected LinkedList<Objeto> listaObjetos;

	// CONSTRUCTOR DE LA CLASE TIENDA
	public Tienda() {
		this.listaObjetos = new LinkedList<Objeto>();
	}

	// GETTER DE LA LISTA DE OBJETOS
	public LinkedList<Objeto> getListaObjetos() {
		return this.listaObjetos;
	}

	// SETTER DE LA LISTA DE OBJETOS
	public void setListaObjetos(LinkedList<Objeto> listaObjetos) {
		this.listaObjetos = listaObjetos;
	}

	// MÉTODO PARA AGREGAR UN OBJETO A LA LISTA DE OBJETOS
	public void agregarObjeto(Objeto objeto) {
		this.listaObjetos.add(objeto);
	}

	// MÉTODO PARA COMPRAR UN OBJETO DE LA TIENDA
	public void comprarObjeto(int idObjeto) {
		for (Objeto objeto : this.listaObjetos) {
			if (objeto.getIdObjeto() == idObjeto) {

				// REALIZAR LA TRANSACCIÓN DE COMPRA

				break;
			}
		}

	}

}
