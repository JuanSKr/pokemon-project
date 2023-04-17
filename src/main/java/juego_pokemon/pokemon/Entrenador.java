package juego_pokemon.pokemon;

import java.util.HashMap;
import java.util.Map;

//CLASE ENTRENADOR
class Entrenador {
 private static  int dinero;
 private Map<Integer, Objeto> mochila;
 private Map<Objeto, Integer> contador; // nuevo HashMap

 // CONSTRUCTOR
 public Entrenador(int dinero) {
     this.dinero = 200;
     this.mochila = new HashMap<>();
     this.contador = new HashMap<>(); // inicializar el nuevo HashMap
 }

 // MÉTODO PARA COMPRAR UN OBJETO
 public void comprarObjeto(Objeto objeto) {
     if (dinero >= objeto.getPrecio()) {
         dinero -= objeto.getPrecio();
         mochila.put(objeto.getId(), objeto);
         System.out.println("¡Objeto  " +objeto.getNombre() +" comprado y añadido a la mochila!");
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
}
