package juego_pokemon.pokemon;

/*
IMPORTANTE:

    CAMBIAR TODOS LAS ESTADISTICAS DE LOS POKEMONS, ATAQUES, ETC...
    - Los tipos están bien
    - Está "ordenado" por evoluciones, no están todas.
 */

public class Pokedex {


	static Pokemon Pikachu = new Pokemon("Pikachu", null, 5, Tipo.ELECTRICO, null, 'H', 100, 50, 50, 65, 60, 150, 30,
			null, 10, null, Ataque.lanzallamas.getNombreMovimiento(), Estado.rayoconfuso.getNombreMovimiento(),
			Ataque.cascada.getNombreMovimiento(), null);

	static Pokemon Raichu = new Pokemon("Raichu", null, 5, Tipo.ELECTRICO, null, 'H', 100, 50, 50, 65, 60, 150, 30,
			null, 10, null, Estado.ataquearena.getNombreMovimiento(), Estado.rayoconfuso.getNombreMovimiento(),
			Ataque.cascada.getNombreMovimiento(), null);

	static Pokemon Bulbasaur = new Pokemon("Bulbasaur", null, 5, Tipo.PLANTA, null, 'H', 100, 30, 80, 20, 80, 100, 15,
			null, 35, null, Estado.fortaleza.getNombreMovimiento(), Estado.anulacion.getNombreMovimiento(),
			Ataque.cascada.getNombreMovimiento(), null);

	static Pokemon Squirtle = new Pokemon("Squirtle", null, 5, Tipo.AGUA, null, 'H', 100, 50, 50, 65, 60, 150, 30, null,
			10, null, Estado.ataquearena.getNombreMovimiento(), Estado.rayoconfuso.getNombreMovimiento(),
			Ataque.cascada.getNombreMovimiento(), null);

	static Pokemon Wartortle = new Pokemon("Wartortle", null, 5, Tipo.AGUA, null, 'H', 100, 50, 50, 65, 60, 150, 30,
			null, 10, null, Estado.ataquearena.getNombreMovimiento(), Estado.rayoconfuso.getNombreMovimiento(),
			Ataque.cascada.getNombreMovimiento(), null);

	static Pokemon Blastoise = new Pokemon("Blastoise", null, 5, Tipo.AGUA, null, 'H', 100, 50, 50, 65, 60, 150, 30,
			null, 10, null, Estado.ataquearena.getNombreMovimiento(), Estado.rayoconfuso.getNombreMovimiento(),
			Ataque.cascada.getNombreMovimiento(), null);

	static Pokemon Caterpie = new Pokemon("Caterpie", null, 5, Tipo.BICHO, null, 'H', 100, 50, 50, 65, 60, 150, 30,
			null, 10, null, Estado.ataquearena.getNombreMovimiento(), Estado.rayoconfuso.getNombreMovimiento(),
			Ataque.cascada.getNombreMovimiento(), null);

	static Pokemon Metapod = new Pokemon("Metapod", null, 5, Tipo.BICHO, null, 'H', 100, 50, 50, 65, 60, 150, 30, null,
			10, null, Estado.ataquearena.getNombreMovimiento(), Estado.rayoconfuso.getNombreMovimiento(),
			Ataque.cascada.getNombreMovimiento(), null);

	static Pokemon Butterfree = new Pokemon("Butterfree", null, 5, Tipo.BICHO, Tipo.VOLADOR, 'H', 100, 50, 50, 65, 60,
			150, 30, null, 10, null, Estado.ataquearena.getNombreMovimiento(), Estado.rayoconfuso.getNombreMovimiento(),
			Ataque.cascada.getNombreMovimiento(), null);

	static Pokemon Weedle = new Pokemon("Weedle", null, 5, Tipo.BICHO, Tipo.VENENO, 'H', 100, 50, 50, 65, 60, 150, 30,
			null, 10, null, Estado.ataquearena.getNombreMovimiento(), Estado.rayoconfuso.getNombreMovimiento(),
			Ataque.cascada.getNombreMovimiento(), null);

	static Pokemon Kakuna = new Pokemon("Kakuna", null, 5, Tipo.BICHO, Tipo.VENENO, 'H', 100, 50, 50, 65, 60, 150, 30,
			null, 10, null, Estado.ataquearena.getNombreMovimiento(), Estado.rayoconfuso.getNombreMovimiento(),
			Ataque.cascada.getNombreMovimiento(), null);

	static Pokemon Pidgey = new Pokemon("Pidgey", null, 5, Tipo.NORMAL, Tipo.VOLADOR, 'H', 100, 50, 50, 65, 60, 150, 30,
			null, 10, null, Estado.ataquearena.getNombreMovimiento(), Estado.rayoconfuso.getNombreMovimiento(),
			Ataque.cascada.getNombreMovimiento(), null);

	static Pokemon Pidgeotto = new Pokemon("Pidgeotto", null, 5, Tipo.NORMAL, Tipo.VOLADOR, 'H', 100, 50, 50, 65, 60,
			150, 30, null, 10, null, Estado.ataquearena.getNombreMovimiento(), Estado.rayoconfuso.getNombreMovimiento(),
			Ataque.cascada.getNombreMovimiento(), null);

	static Pokemon Pidgeot = new Pokemon("Pidgeot", null, 5, Tipo.NORMAL, Tipo.VOLADOR, 'H', 100, 50, 50, 65, 60, 150,
			30, null, 10, null, Estado.ataquearena.getNombreMovimiento(), Estado.rayoconfuso.getNombreMovimiento(),
			Ataque.cascada.getNombreMovimiento(), null);

	static Pokemon Rattata = new Pokemon("Rattata", null, 5, Tipo.NORMAL, null, 'H', 100, 50, 50, 65, 60, 150, 30, null,
			10, null, Estado.ataquearena.getNombreMovimiento(), Estado.rayoconfuso.getNombreMovimiento(),
			Ataque.cascada.getNombreMovimiento(), null);

	static Pokemon Raticate = new Pokemon("Raticate", null, 5, Tipo.NORMAL, null, 'H', 100, 50, 50, 65, 60, 150, 30,
			null, 10, null, Estado.ataquearena.getNombreMovimiento(), Estado.rayoconfuso.getNombreMovimiento(),
			Ataque.cascada.getNombreMovimiento(), null);

	static Pokemon Spearow = new Pokemon("Spearow", null, 5, Tipo.NORMAL, Tipo.VOLADOR, 'H', 100, 50, 50, 65, 60, 150,
			30, null, 10, null, Estado.ataquearena.getNombreMovimiento(), Estado.rayoconfuso.getNombreMovimiento(),
			Ataque.cascada.getNombreMovimiento(), null);

	static Pokemon Fearow = new Pokemon("Fearow", null, 5, Tipo.NORMAL, Tipo.VOLADOR, 'H', 100, 50, 50, 65, 60, 150, 30,
			null, 10, null, Estado.ataquearena.getNombreMovimiento(), Estado.rayoconfuso.getNombreMovimiento(),
			Ataque.cascada.getNombreMovimiento(), null);

	static Pokemon Vulpix = new Pokemon("Vulpix", null, 5, Tipo.FUEGO, null, 'H', 100, 50, 50, 65, 60, 150, 30, null,
			10, null, Estado.ataquearena.getNombreMovimiento(), Estado.rayoconfuso.getNombreMovimiento(),
			Ataque.cascada.getNombreMovimiento(), null);

	static Pokemon Ninetales = new Pokemon("Ninetales", null, 5, Tipo.FUEGO, null, 'H', 100, 50, 50, 65, 60, 150, 30,
			null, 10, null, Estado.ataquearena.getNombreMovimiento(), Estado.rayoconfuso.getNombreMovimiento(),
			Ataque.cascada.getNombreMovimiento(), null);

    static Pokemon Pikachu = new Pokemon(
            "Pikachu",
            null,
            5,
            Tipo.ELECTRICO,
            null,
            'H',
            100,
            50,
            50,
            65,
            60,
            150,
            30,
            null,
            10,
            null,
            Ataque.lanzallamas.getNombreMovimiento(),
            Estado.rayoconfuso.getNombreMovimiento(),
            Ataque.cascada.getNombreMovimiento(),
            null);

    static Pokemon Raichu = new Pokemon(
            "Raichu",
            null,
            5,
            Tipo.ELECTRICO,
            null,
            'H',
            100,
            50,
            50,
            65,
            60,
            150,
            30,
            null,
            10,
            null,
            Estado.ataquearena.getNombreMovimiento(),
            Estado.rayoconfuso.getNombreMovimiento(),
            Ataque.cascada.getNombreMovimiento(),
            null);

    static Pokemon Bulbasaur = new Pokemon(
            "Bulbasaur",
            null,
            5,
            Tipo.PLANTA,
            null,
            'H',
            100,
            30,
            80,
            20,
            80,
            100,
            15,
            null,
            35,
            null,
            Estado.fortaleza.getNombreMovimiento(),
            Estado.anulacion.getNombreMovimiento(),
            Ataque.cascada.getNombreMovimiento(),
            null);


    static Pokemon Squirtle = new Pokemon(
            "Squirtle",
            null,
            5,
            Tipo.AGUA,
            null,
            'H',
            100,
            50,
            50,
            65,
            60,
            150,
            30,
            null,
            10,
            null,
            Estado.ataquearena.getNombreMovimiento(),
            Estado.rayoconfuso.getNombreMovimiento(),
            Ataque.cascada.getNombreMovimiento(),
            null);

    static Pokemon Wartortle = new Pokemon(
            "Wartortle",
            null,
            5,
            Tipo.AGUA,
            null,
            'H',
            100,
            50,
            50,
            65,
            60,
            150,
            30,
            null,
            10,
            null,
            Estado.ataquearena.getNombreMovimiento(),
            Estado.rayoconfuso.getNombreMovimiento(),
            Ataque.cascada.getNombreMovimiento(),
            null);

    static Pokemon Blastoise = new Pokemon(
            "Blastoise",
            null,
            5,
            Tipo.AGUA,
            null,
            'H',
            100,
            50,
            50,
            65,
            60,
            150,
            30,
            null,
            10,
            null,
            Estado.ataquearena.getNombreMovimiento(),
            Estado.rayoconfuso.getNombreMovimiento(),
            Ataque.cascada.getNombreMovimiento(),
            null);

    static Pokemon Caterpie = new Pokemon(
            "Caterpie",
            null,
            5,
            Tipo.BICHO,
            null,
            'H',
            100,
            50,
            50,
            65,
            60,
            150,
            30,
            null,
            10,
            null,
            Estado.ataquearena.getNombreMovimiento(),
            Estado.rayoconfuso.getNombreMovimiento(),
            Ataque.cascada.getNombreMovimiento(),
            null);

    static Pokemon Metapod = new Pokemon(
            "Metapod",
            null,
            5,
            Tipo.BICHO,
            null,
            'H',
            100,
            50,
            50,
            65,
            60,
            150,
            30,
            null,
            10,
            null,
            Estado.ataquearena.getNombreMovimiento(),
            Estado.rayoconfuso.getNombreMovimiento(),
            Ataque.cascada.getNombreMovimiento(),
            null);

    static Pokemon Butterfree = new Pokemon(
            "Butterfree",
            null,
            5,
            Tipo.BICHO,
            Tipo.VOLADOR,
            'H',
            100,
            50,
            50,
            65,
            60,
            150,
            30,
            null,
            10,
            null,
            Estado.ataquearena.getNombreMovimiento(),
            Estado.rayoconfuso.getNombreMovimiento(),
            Ataque.cascada.getNombreMovimiento(),
            null);

    static Pokemon Weedle = new Pokemon(
            "Weedle",
            null,
            5,
            Tipo.BICHO,
            Tipo.VENENO,
            'H',
            100,
            50,
            50,
            65,
            60,
            150,
            30,
            null,
            10,
            null,
            Estado.ataquearena.getNombreMovimiento(),
            Estado.rayoconfuso.getNombreMovimiento(),
            Ataque.cascada.getNombreMovimiento(),
            null);

    static Pokemon Kakuna = new Pokemon(
            "Kakuna",
            null,
            5,
            Tipo.BICHO,
            Tipo.VENENO,
            'H',
            100,
            50,
            50,
            65,
            60,
            150,
            30,
            null,
            10,
            null,
            Estado.ataquearena.getNombreMovimiento(),
            Estado.rayoconfuso.getNombreMovimiento(),
            Ataque.cascada.getNombreMovimiento(),
            null);

    static Pokemon Pidgey = new Pokemon(
            "Pidgey",
            null,
            5,
            Tipo.NORMAL,
            Tipo.VOLADOR,
            'H',
            100,
            50,
            50,
            65,
            60,
            150,
            30,
            null,
            10,
            null,
            Estado.ataquearena.getNombreMovimiento(),
            Estado.rayoconfuso.getNombreMovimiento(),
            Ataque.cascada.getNombreMovimiento(),
            null);

    static Pokemon Pidgeotto = new Pokemon(
            "Pidgeotto",
            null,
            5,
            Tipo.NORMAL,
            Tipo.VOLADOR,
            'H',
            100,
            50,
            50,
            65,
            60,
            150,
            30,
            null,
            10,
            null,
            Estado.ataquearena.getNombreMovimiento(),
            Estado.rayoconfuso.getNombreMovimiento(),
            Ataque.cascada.getNombreMovimiento(),
            null);

    static Pokemon Pidgeot = new Pokemon(
            "Pidgeot",
            null,
            5,
            Tipo.NORMAL,
            Tipo.VOLADOR,
            'H',
            100,
            50,
            50,
            65,
            60,
            150,
            30,
            null,
            10,
            null,
            Estado.ataquearena.getNombreMovimiento(),
            Estado.rayoconfuso.getNombreMovimiento(),
            Ataque.cascada.getNombreMovimiento(),
            null);

    static Pokemon Rattata = new Pokemon(
            "Rattata",
            null,
            5,
            Tipo.NORMAL,
            null,
            'H',
            100,
            50,
            50,
            65,
            60,
            150,
            30,
            null,
            10,
            null,
            Estado.ataquearena.getNombreMovimiento(),
            Estado.rayoconfuso.getNombreMovimiento(),
            Ataque.cascada.getNombreMovimiento(),
            null);

    static Pokemon Raticate = new Pokemon(
            "Raticate",
            null,
            5,
            Tipo.NORMAL,
            null,
            'H',
            100,
            50,
            50,
            65,
            60,
            150,
            30,
            null,
            10,
            null,
            Estado.ataquearena.getNombreMovimiento(),
            Estado.rayoconfuso.getNombreMovimiento(),
            Ataque.cascada.getNombreMovimiento(),
            null);

    static Pokemon Spearow = new Pokemon(
            "Spearow",
            null,
            5,
            Tipo.NORMAL,
            Tipo.VOLADOR,
            'H',
            100,
            50,
            50,
            65,
            60,
            150,
            30,
            null,
            10,
            null,
            Estado.ataquearena.getNombreMovimiento(),
            Estado.rayoconfuso.getNombreMovimiento(),
            Ataque.cascada.getNombreMovimiento(),
            null);

    static Pokemon Fearow = new Pokemon(
            "Fearow",
            null,
            5,
            Tipo.NORMAL,
            Tipo.VOLADOR,
            'H',
            100,
            50,
            50,
            65,
            60,
            150,
            30,
            null,
            10,
            null,
            Estado.ataquearena.getNombreMovimiento(),
            Estado.rayoconfuso.getNombreMovimiento(),
            Ataque.cascada.getNombreMovimiento(),
            null);


    static Pokemon Vulpix = new Pokemon(
            "Vulpix",
            null,
            5,
            Tipo.FUEGO,
            null,
            'H',
            100,
            50,
            50,
            65,
            60,
            150,
            30,
            null,
            10,
            null,
            Estado.ataquearena.getNombreMovimiento(),
            Estado.rayoconfuso.getNombreMovimiento(),
            Ataque.cascada.getNombreMovimiento(),
            null);

    static Pokemon Ninetales = new Pokemon(
            "Ninetales",
            null,
            5,
            Tipo.FUEGO,
            null,
            'H',
            100,
            50,
            50,
            65,
            60,
            150,
            30,
            null,
            10,
            null,
            Estado.ataquearena.getNombreMovimiento(),
            Estado.rayoconfuso.getNombreMovimiento(),
            Ataque.cascada.getNombreMovimiento(),
            null);


}

