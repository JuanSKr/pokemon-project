//package Pokemon.Tienda;
//
//import Pokemon.Entrenador.Entrenador;
//
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Scanner;
//
////CLASE TIENDA
//public class Tienda {
//	private static Map<Integer, Objeto> inventario;
//
//	// CONSTRUCTOR
//
//	// MÉTODO PARA MOSTRAR EL INVENTARIO DE LA TIENDA
//	public static void mostrarInventario() {
//		System.out.println("Inventario de la tienda:");
//		for (Objeto objeto : inventario.values()) {
//			System.out.println(objeto.toString());
//		}
//	}
//
//	// MÉTODO PARA OBTENER UN OBJETO DEL INVENTARIO DE LA TIENDA
//	public Objeto obtenerObjeto(int id) {
//		return inventario.get(id);
//	}
//
//	public static void abrirTienda() {
//		// CREAMOS UN ENTRENADOR CON 500 MONEDAS
//		Entrenador entrenador = new Entrenador();
//		// Creamos una tienda
//		Tienda tienda = new Tienda();
//		// CREAMOS UN OBJETO SCANNER PARA LEER LA ENTRADA DEL USUARIO
//		Scanner scanner = new Scanner(System.in);
//		// MOSTRAMOS EL MENÚ DE OPCIONES
//		int opcion;
//		do {
//			System.out.println("Menu:");
//			System.out.println("1. Mostrar inventario de la tienda");
//			System.out.println("2. Mostrar mochila del entrenador");
//			System.out.println("3. Mostrar dinero del entrenador");
//			System.out.println("4. Salir");
//			System.out.print("Elige una opcion: ");
//			opcion = scanner.nextInt();
//
//			// REALIZAMOS UNA ACCIÓN SEGÚN LA OPCIÓN ELEGIDA
//			switch (opcion) {
//			case 1:
//				// MOSTRAMOS EL INVENTARIO DE LA TIENDA
//				tienda.mostrarInventario();
//
//				// PREGUNTAMOS AL USUARIO QUÉ OBJETO QUIERE COMPRAR
//				System.out.print("Elige el ID del objeto que quieres comprar: ");
//				int idObjeto = scanner.nextInt();
//				Objeto objetoComprado = tienda.obtenerObjeto(idObjeto);
//
//				// COMPRAMOS EL OBJETO SI EXISTE EN LA TIENDA
//				if (objetoComprado != null) {
//					entrenador.comprarObjeto(objetoComprado);
//				} else {
//					System.out.println("No existe ningun objeto con ese ID en la tienda.");
//				}
//				break;
//			case 2:
//				// MOSTRAMOS LA MOCHILA DEL ENTRENADOR
//				System.out.println("Mochila del entrenador:");
//				for (Objeto objeto : entrenador.getMochila().values()) {
//					System.out.println(objeto.toString());
//				}
//
//				// MOSTRAMOS EL CONTADOR DE OBJETOS
//				System.out.println("Contador de objetos:");
//				for (Map.Entry<Objeto, Integer> entry : entrenador.getContador().entrySet()) {
//					Objeto objeto = entry.getKey();
//					int cantidad = entry.getValue();
//					System.out.println(objeto.getNombre() + ": " + cantidad);
//				}
//				break;
//			case 3:
//				// MOSTRAMOS EL DINERO DEL ENTRENADOR
//				System.out.println("Dinero del entrenador: " + entrenador.getNombre()  + " monedas " + entrenador.getDinero() );
//				break;
//			case 4:
//				// SALIMOS DEL PROGRAMA
//				System.out.println("¡Hasta luego!");
//				break;
//			default:
//				System.out.println("Opcion no valida.");
//			}
//		} while (opcion != 4);
//	}
//
//	public Map<Integer, Objeto> getInventario() {
//	    return this.inventario;
//	}
//}