package juego_pokemon.pokemon;

import java.util.HashMap;
import java.util.Map;

//CLASE ENTRENADOR
class Entrenador {
 private int dinero;
 private Map<Integer, Objeto> mochila;

 // CONSTRUCTOR
 public Entrenador(int dinero) {
     this.dinero = dinero;
     this.mochila = new HashMap<>();
 }

 // MÉTODO PARA COMPRAR UN OBJETO
 public void comprarObjeto(Objeto objeto) {
     if (dinero >= objeto.getPrecio()) {
         dinero -= objeto.getPrecio();
         mochila.put(objeto.getId(), objeto);
         System.out.println("¡Objeto  " +objeto.getNombre() +" comprado y añadido a la mochila!");
     } else {
         System.out.println("No tienes suficiente dinero para comprar este objeto.");
     }
 }

 // MÉTODO PARA OBTENER EL DINERO DEL ENTRENADOR
 public int getDinero() {
     return dinero;
 }

 // MÉTODO PARA OBTENER LA MOCHILA DEL ENTRENADOR
 public Map<Integer, Objeto> getMochila() {
     return mochila;
 }
}