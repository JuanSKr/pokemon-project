package Pokemon.Entrenador;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import org.junit.jupiter.api.Test;

import Pokemon.Pokemon.Pokemon;
import Pokemon.Pokemon.Tipo;
import Pokemon.Tienda.Objeto;

class EntrenadorTest {

	@Test
		void testComprarObjeto_suficienteDinero() {
		// Configuración del escenario de prueba
        Objeto objeto = new Objeto(1, "Poción", 10, null);
        int dinero = 20;
        Map<Integer, Objeto> mochila = new HashMap<>();
        Map<Objeto, Integer> contador = new HashMap<>();
     // Ejecución del método a probar
        Entrenador.comprarObjeto(objeto);
     // Verificación de los resultados
        assertEquals(10, dinero);
        assertTrue(mochila.containsKey(objeto.getId()));
        assertEquals(1, (int) contador.get(objeto));
	}
	@Test
	void testComprarObjeto_insuficienteDinero() {
		// CONFIGURACIÓN DEL ESCENARIO DE PRUEBA
        Objeto objeto = new Objeto(1, "Poción", 10, null);
        int dinero = 5;
        Map<Integer, Objeto> mochila = new HashMap<>();
        Map<Objeto, Integer> contador = new HashMap<>();
     // EJECUCIÓN DEL MÉTODO A PROBAR
        Entrenador.comprarObjeto(objeto);
        // VERIFICACIÓN DE LOS RESULTADOS
        assertEquals(5, dinero);
        assertFalse(mochila.containsKey(objeto.getId()));
        assertNull(contador.get(objeto));
	}

	@Test
	void testAddPokemon_equipo1() {
		// CONFIGURACIÓN DEL ESCENARIO DE PRUEBA
        Pokemon pokemon = new Pokemon(1, "Pikachu", null, 5, 0, Tipo.ELECTRICO, null, 'H', 50, 50, 50, 50, 50, 100, 100, null, 10,
        		null,  null, null, null, null, null, null);
        LinkedList<Pokemon> equipo1 = new LinkedList<>();
        LinkedList<Pokemon> equipo2 = new LinkedList<>();
        LinkedList<Pokemon> caja = new LinkedList<>();
        for (int i = 1; i <= 6; i++) {
        	equipo2.add(pokemon);
            
        }

	}
	
	@Test
    void testAddPokemon_equipo2() {
        // CONFIGURACIÓN DEL ESCENARIO DE PRUEBA
        Pokemon pokemon = new Pokemon(1, "Pikachu", null, 5, 0, Tipo.ELECTRICO, null, 'H', 50, 50, 50, 50, 50, 100, 100, null, 10,
        		null,  null, null, null, null, null, null);
        LinkedList<Pokemon> equipo1 = new LinkedList<>();
        LinkedList<Pokemon> equipo2 = new LinkedList<>();
        LinkedList<Pokemon> caja = new LinkedList<>();
        for (int i = 1; i <= 6; i++) {
        	equipo2.add(pokemon);
            
        }
     // EJECUCIÓN DEL MÉTODO A PROBAR
        int resultado = Entrenador.addPokemon(pokemon);
    }
	 @Test
	    void testAddPokemon_caja() {
	        // CONFIGURACIÓN DEL ESCENARIO DE PRUEBA
	        Pokemon pokemon = new Pokemon(1, "Pikachu", null, 5, 0, Tipo.ELECTRICO, null, 'H', 50, 50, 50, 50, 50, 100, 100, null, 10,
	        		null,  null, null, null, null, null, null);
	        LinkedList<Pokemon> equipo1 = new LinkedList<>();
	        LinkedList<Pokemon> equipo2 = new LinkedList<>();
	        LinkedList<Pokemon> caja = new LinkedList<>();
	        for (int i = 1; i <= 6; i++) {
	        	caja.add(pokemon);
	            
	        }
	     // EJECUCIÓN DEL MÉTODO A PROBAR
	        int resultado = Entrenador.addPokemon(pokemon);
	    }

	@Test
	void testFotoDefault() {
		 // EJECUCIÓN DEL MÉTODO A PROBAR
        String resultado = Entrenador.fotoDefault();       
		// VERIFICACIÓN DE LOS RESULTADOS
        assertEquals("/img/alonso.png", resultado);
	}

}
