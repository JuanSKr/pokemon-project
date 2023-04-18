package juego_pokemon.pokemon;


import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

//CLASE TIENDA
class Tienda {
 private Map<Integer, Objeto> inventario;

 // CONSTRUCTOR
 public Tienda() {
     this.inventario = new HashMap<>();
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

 // MÉTODO PARA OBTENER UN OBJETO DEL INVENTARIO DE LA TIENDA
 public Objeto obtenerObjeto(int id) {
     return inventario.get(id);
 }

 public static void abrirTienda() {
	   // CREAMOS UN ENTRENADOR CON 500 MONEDAS
	   Entrenador entrenador = new Entrenador();

	   // Creamos una tienda
	   Tienda tienda = new Tienda();
	           // CREAMOS UN OBJETO SCANNER PARA LEER LA ENTRADA DEL USUARIO
	           Scanner scanner = new Scanner(System.in);

	           // MOSTRAMOS EL MENÚ DE OPCIONES
	           int opcion;
	           do {
	               System.out.println("Menu:");
	               System.out.println("1. Mostrar inventario de la tienda");
	               System.out.println("2. Mostrar mochila del entrenador");
	               System.out.println("3. Mostrar dinero del entrenador");
	               System.out.println("4. Salir");
	               System.out.print("Elige una opcion: ");
	               opcion = scanner.nextInt();

	               // REALIZAMOS UNA ACCIÓN SEGÚN LA OPCIÓN ELEGIDA
	               switch (opcion) {
	                   case 1:
	                       // MOSTRAMOS EL INVENTARIO DE LA TIENDA
	                       tienda.mostrarInventario();

	                       // PREGUNTAMOS AL USUARIO QUÉ OBJETO QUIERE COMPRAR
	                       System.out.print("Elige el ID del objeto que quieres comprar: ");
	                       int idObjeto = scanner.nextInt();
	                       Objeto objetoComprado = tienda.obtenerObjeto(idObjeto);

	                       // COMPRAMOS EL OBJETO SI EXISTE EN LA TIENDA
	                       if (objetoComprado != null) {
	                           entrenador.comprarObjeto(objetoComprado);
	                       } else {
	                           System.out.println("No existe ningun objeto con ese ID en la tienda.");
	                       }
	                       break;

	                   case 2:
	                	    // MOSTRAMOS LA MOCHILA DEL ENTRENADOR
	                	    System.out.println("Mochila del entrenador:");
	                	    for (Objeto objeto : entrenador.getMochila().values()) {
	                	        System.out.println(objeto.toString());
	                	    }

	                	    // MOSTRAMOS EL CONTADOR DE OBJETOS
	                	    System.out.println("Contador de objetos:");
	                	    for (Map.Entry<Objeto, Integer> entry : entrenador.getContador().entrySet()) {
	                	        Objeto objeto = entry.getKey();
	                	        int cantidad = entry.getValue();
	                	        System.out.println(objeto.getNombre() + ": " + cantidad);
	                	    }
	                	    break;


	                   case 3:
	                       // MOSTRAMOS EL DINERO DEL ENTRENADOR
	                       System.out.println("Dinero del entrenador: " + entrenador.getDinero() + " monedas");
	                       break;

	                   case 4:
	                       // SALIMOS DEL PROGRAMA
	                       System.out.println("¡Hasta luego!");
	                       break;

	                   default:
	                       System.out.println("Opcion no valida.");
	               }
	           } while (opcion != 4);
	       }

		public static void tiendaButton() {


		}
}