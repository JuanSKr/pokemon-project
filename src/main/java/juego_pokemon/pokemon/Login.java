package juego_pokemon.pokemon;

import java.util.Scanner;

public class Login {

    static Scanner sc = new Scanner(System.in);

    public static void login() {

        System.out.println("Escribe tu nombre: ");
        Entrenador.setNombre(sc.nextLine());

        System.out.println("Nombre entrenador: " + Entrenador.getNombre());


    }
}
