package juego_pokemon.pokemon;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



//CLASE TIENDA
class Tienda {
    private Map<Integer, Objeto> inventario;
   
    
    public Tienda() {
        inventario = new HashMap<Integer, Objeto>();
        inventario.put(1, new Objeto(1, "Pesa", 50, "Aumenta el ataque y la defensa un 20%, pero disminuye la velocidad un 20%."));
        inventario.put(2, new Objeto(2, "Pluma", 60, "Aumenta la velocidad un 30%, pero disminuye la defensa y la defensa especial en un 20%."));
        inventario.put(3, new Objeto(3, "Chaleco", 70, "Aumenta la defensa y la defensa especial un 20%, pero disminuye la velocidad y el ataque un 15%."));
        inventario.put(4, new Objeto(4, "Bastón", 80, "Aumenta la estamina un 20%, pero disminuye en un 15% la velocidad."));
        inventario.put(5, new Objeto(5, "Pilas", 90, "Aumenta la recuperación de estamina en un 50%, pero disminuye la defensa especial un 30%."));
        
    }

    // MÉTODO PARA MOSTRAR EL INVENTARIO DE LA TIENDA
    public void mostrarInventario() {
        System.out.println("Inventario de la tienda:");
        for (Objeto objeto : inventario.values()) {
            System.out.println(objeto.toString());
        }
    }

    // MÉTODO PARA OBTENER UN OBJETO DEL INVENTARIO DE LA TIENDA Y ALMACENARLO EN LA MOCHILA
    public Objeto obtenerObjeto(int id) {
        Objeto objeto = inventario.get(id);
        inventario.remove(id);
        return objeto;
    }

    public List<Objeto> getObjetos() {
        return new ArrayList<Objeto>(inventario.values());
    }

    public boolean comprar(Objeto objeto) {
        if (Entrenador.dinero < objeto.getPrecio()) {
            return false;
        }
        Entrenador.dinero -= objeto.getPrecio();
        return true;
    }

    public static int getDinero() {
        return Entrenador.getDinero() - Objeto.precio;
    }

    public Map<Integer, Objeto> getInventario() {
        return inventario;
    }

	public void quitarObjeto(int id) {
		// TODO Auto-generated method stub
		
	}

	// MÉTODO PARA OBTENER UN OBJETO DEL INVENTARIO DE LA TIENDA Y ALMACENARLO EN LA MOCHILA
	public boolean obtenerObjeto(int id, Entrenador entrenador) {
	    Objeto objeto = inventario.get(id);
	    if (objeto != null) {
	        inventario.remove(id);
	        entrenador.agregarObjetoMochila(objeto);
	        return true;
	    } else {
	        return false;
	    }
	}
}
