package Pokemon.Capturar;


import Pokemon.Entrenador.Entrenador;
import Pokemon.Menus.Pokedex;
import Pokemon.Pokemon.Pokemon;

import java.util.LinkedList;
import java.util.Scanner;

import static Pokemon.Entrenador.Entrenador.*;

import Pokemon.Menus.Pokedex;

public class Exploracion {

    static Scanner sc = new Scanner(System.in);

    public static void capturarPokemon() {

        Pokemon pokemon = pokeRandom();

        System.out.println("Ha aparecido un " + pokemon + " salvaje.");
        System.out.println("-------------------------");
        System.out.print("Pulsa 1 para capturarlo: ");
        int captura = sc.nextInt();

        if (captura == 1) {
            addPokemon(pokemon, equipo1, equipo2, caja);
        }


    }

    public static Pokemon pokeRandom() {

        Pokemon[] pokemons = {
                Pokedex.Pikachu,
                Pokedex.Raichu,
                Pokedex.Bulbasaur,
                Pokedex.Ivysaur,
                Pokedex.Venusaur,
                Pokedex.Charmander,
                Pokedex.Charmeleon,
                Pokedex.Charizard,
                Pokedex.Squirtle,
                Pokedex.Wartortle,
                Pokedex.Butterfree,
                Pokedex.Weedle,
                Pokedex.Kakuna,
                Pokedex.Beedrill,
                Pokedex.Pidgey,
                Pokedex.Pidgeotto,
                Pokedex.Pidgeot,
                Pokedex.Rattata,
                Pokedex.Raticate,
                Pokedex.Spearow};

        int random = (int) (Math.random() * 10 + 1);

        Pokemon pokemonRandom = pokemons[random];

        return pokemonRandom;
    }

    public static void main(String[] args) {

        capturarPokemon();
        verEquipos();
    }


}