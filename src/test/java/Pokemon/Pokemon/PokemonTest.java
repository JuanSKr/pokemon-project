package Pokemon.Pokemon;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import Pokemon.Combate.Movimientos.Ataque;
import Pokemon.Combate.Movimientos.Movimiento;

class PokemonTest {

	@Test
	void testAddMovimiento() {
		// CREAR UN POKEMON DE PRUEBA
		Pokemon pokemon = new Pokemon();
		Ataque ataque = new Ataque("Test", 50, Tipo.ACERO, "ATAQUE", 0);
		// AGREGAR EL MOVIMIENTO AL POKEMON
		pokemon.addMovimiento(pokemon);
		// VERIFICAR QUE EL MOVIMIENTO SE HAYA AGREGADO CORRECTAMENTE
		Assertions.assertEquals(ataque, pokemon.getMovimiento1());
	}

	@Test
	void testSubirNivel() {
		// CREAR UN POKEMON DE PRUEBA
		Pokemon pokemon = new Pokemon();
		pokemon.setNivel(5);
		pokemon.setAtaque(50);
		// SUBIR EL NIVEL DEL POKEMON
		pokemon.getNivel();

		// VERIFICAR QUE EL NIVEL SE HAYA INCREMENTADO Y EL ATAQUE HAYA AUMENTADO
		Assertions.assertEquals(6, pokemon.getNivel());
		Assertions.assertEquals(55, pokemon.getAtaque());
	}

	@Test
	void testMostrarPokemon() {
		// CREAR UN POKEMON DE PRUEBA
		Pokemon pokemon = new Pokemon();
		pokemon.setNombre("Pikachu");
		pokemon.setTipo1(Tipo.ELECTRICO);
		pokemon.setAtaque(50);
		// OBTENER LA REPRESENTACIÓN EN CADENA DEL POKEMON
		pokemon = Pokemon.mostrarPokemon(1,1);
		// VERIFICAR QUE LA REPRESENTACIÓN EN CADENA SEA LA ESPERADA
		String esperado = "Nombre: Pikachu\nTipo: Electric\nAtaque: 50";
		Assertions.assertEquals(esperado, pokemon);
	}

}
