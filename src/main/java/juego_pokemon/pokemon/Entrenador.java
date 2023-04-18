package juego_pokemon.pokemon;

import java.util.HashMap;
import java.util.Map;

//CLASE ENTRENADOR
class Entrenador {
    protected static String nombre;
    private static int dinero;
    private static Map<Integer, Objeto> mochila;
    private static Map<Objeto, Integer> contador; // nuevo HashMap
    private String nombreUsuario;
    private String contrasena;

    // Constructor con todos los parametros

    public Entrenador() {
        this.nombre = nombre;
        this.dinero = dinero;
        this.mochila = mochila;
        this.contador = contador;
    }

    public Entrenador(String usuario, String nombreUsuario) {
        this.nombre = "";
        this.dinero = 200;
        this.mochila = new HashMap<>();
        this.contador = new HashMap<>();
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
    }


    // MÉTODO PARA COMPRAR UN OBJETO
    public void comprarObjeto(Objeto objeto) {
        if (dinero >= objeto.getPrecio()) {
            dinero -= objeto.getPrecio();
            mochila.put(objeto.getId(), objeto);
            System.out.println("¡Objeto  " + objeto.getNombre() + " comprado y añadido a la mochila!");
            // Incrementar el contador del objeto comprado
            if (contador.containsKey(objeto)) {
                contador.put(objeto, contador.get(objeto) + 1);
            } else {
                contador.put(objeto, 1);
            }
        } else {
            System.out.println("No tienes suficiente dinero para comprar este objeto.");
        }
    }


    // MÉTODO PARA OBTENER EL DINERO DEL ENTRENADOR
    public static int getDinero() {
        return dinero;
    }

    public Map<Integer, Objeto> getMochila() {
        return mochila;
    }

    public Map<Objeto, Integer> getContador() {
        return contador;
    }

    public static String getNombre() {
        return nombre;
    }

    public static void setNombre(String nombre) {
        Entrenador.nombre = nombre;
    }

    public static void setDinero(int dinero) {
        Entrenador.dinero = dinero;
    }

    public static void setMochila(Map<Integer, Objeto> mochila) {
        Entrenador.mochila = mochila;
    }

    public static void setContador(Map<Objeto, Integer> contador) {
        Entrenador.contador = contador;
    }

	public static void setContrasena(String contrasena) {
		// TODO Auto-generated method stub
		
	}

	public static String getContrasena() {
		// TODO Auto-generated method stub
		return null;
	}
}
