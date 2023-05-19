package Pokemon.Tienda;

import Pokemon.Entrenador.Entrenador;
import Pokemon.Tienda.Objeto;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

//CLASE TIENDA
public class Tienda {
	protected static Map<Integer, Objeto> inventario;

	// CONSTRUCTOR
	public  Tienda() {
		this.inventario = new HashMap<>();
		inventario.put(1, new Objeto(1, "Pesa", 50,
				"Aumenta el ataque y la defensa un 20%, pero disminuye la velocidad un 20%."));
		inventario.put(2, new Objeto(2, "Pluma", 60,
				"Aumenta la velocidad un 30%, pero disminuye la defensa y la defensa especial en un 20%."));
		inventario.put(3, new Objeto(3, "Chaleco", 70,
				"Aumenta la defensa y la defensa especial un 20%, pero disminuye la velocidad y el ataque un 15%."));
		inventario.put(4,
				new Objeto(4, "Bastón", 80, "Aumenta la estamina un 20%, pero disminuye en un 15% la velocidad."));
		inventario.put(5, new Objeto(5, "Pilas", 90,
				"Aumenta la recuperación de estamina en un 50%, pero disminuye la defensa especial un 30%."));
	}

	// MÉTODO PARA MOSTRAR EL INVENTARIO DE LA TIENDA
	public static void mostrarInventario() {
		System.out.println("Inventario de la tienda:");
		for (Objeto objeto : inventario.values()) {
			System.out.println(objeto.toString());
		}
	}

	// MÉTODO PARA OBTENER UN OBJETO DEL INVENTARIO DE LA TIENDA
	public Objeto obtenerObjeto(int id) {
		return inventario.get(id);
	}

	public Map<Integer, Objeto> getInventario() {
	    return this.inventario;
	}
}