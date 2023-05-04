package Pokemon.Combate;

import Pokemon.Database.MySQL;
import Pokemon.Pokemon.Pokemon;

import java.util.Scanner;


public class Combate {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Pokemon pokemon = MySQL.generarPokemon();

        System.out.println("El rival ha sacado un " + pokemon.getNombre());
        System.out.println("--------------------------------");
        System.out.print("¿Quieres atacarle? (1 = S/2 = N): ");
        int opcion = sc.nextInt();

        if(opcion == 1) {
            System.out.println("----------------------------");
            System.out.println("Vida del "+ pokemon.getNombre() +": "+pokemon.getVitalidad());
            System.out.println("----------------------------");
            System.out.println("Has atacado al " +pokemon.getNombre());
            System.out.println("Le has hecho 30 de daño");
            pokemon.setVitalidad(pokemon.getVitalidad() - 30);
            System.out.println("----------------------------");
            System.out.println("Vida del "+ pokemon.getNombre() +": "+pokemon.getVitalidad());
            System.out.println("----------------------------");
        }


    }

}
