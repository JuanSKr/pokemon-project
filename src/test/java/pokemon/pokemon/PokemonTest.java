package pokemon.pokemon;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PokemonTest {

    private static Pokemon nuevo() {
        Pokemon p = new Pokemon();
        p.setNombre("Pikachu");
        p.setVitalidad(100);
        p.setEstamina(120);
        p.setAtaque(50);
        p.setDefensa(50);
        p.prepararCombate();
        return p;
    }

    @Test
    void laVidaNoBajaDeCeroNiSuperaElMaximo() {
        Pokemon p = nuevo();
        assertEquals(100, p.recibirDanio(250));
        assertEquals(0, p.getVida());
        assertTrue(p.estaDebilitado());
        p.curar(500);
        assertEquals(100, p.getVida());
    }

    @Test
    void elMoteSeMuestraSiExiste() {
        Pokemon p = nuevo();
        assertEquals("Pikachu", p.getNombreMostrado());
        p.setMote("  Chispas ");
        assertEquals("Chispas", p.getNombreMostrado());
        p.setMote("");
        assertEquals("Pikachu", p.getNombreMostrado());
    }

    @Test
    void subeDeNivelConExperiencia() {
        Pokemon p = nuevo();
        int necesaria = p.xpParaSubir();
        assertEquals(0, p.ganarExperiencia(necesaria - 1));
        assertEquals(1, p.getNivel());
        assertEquals(1, p.ganarExperiencia(1));
        assertEquals(2, p.getNivel());
        assertEquals(0, p.getXp());
        assertTrue(p.getVitalidad() > 100, "al subir de nivel sube la vida máxima");
    }

    @Test
    void lasMejorasTienenTope() {
        Pokemon p = nuevo();
        assertTrue(p.mejorar(30, 0, 0, 0));
        p.mejorar(1000, 0, 0, 0);
        assertEquals(100, p.getAtaqueCombate(), "como mucho se duplica");
        p.prepararCombate();
        assertEquals(50, p.getAtaqueCombate(), "al empezar otro combate se pierden las mejoras");
    }

    @Test
    void losEstadosSeAcaban() {
        Pokemon p = nuevo();
        p.setEstado(ListaEstados.ENVENENADO, 2);
        assertFalse(p.pasarTurnoEstado());
        assertTrue(p.pasarTurnoEstado());
        assertNull(p.getEstado());
    }

    @Test
    void tiposDesdeLaBaseDeDatos() {
        assertEquals(Tipo.ELECTRICO, Tipo.desdeTexto("Electrico"));
        assertEquals(Tipo.PSIQUICO, Tipo.desdeTexto("psíquico"));
        assertNull(Tipo.desdeTexto("null"));
        assertNull(Tipo.desdeTexto(null));
        assertNull(Tipo.desdeTexto("inventado"));
    }
}
