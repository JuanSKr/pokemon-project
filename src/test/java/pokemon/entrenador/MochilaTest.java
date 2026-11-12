package pokemon.entrenador;

import org.junit.jupiter.api.Test;
import pokemon.tienda.Objeto;

import static org.junit.jupiter.api.Assertions.*;

class MochilaTest {

    private static final Objeto POKE_BALL = new Objeto(1, "Poké Ball", 200, "", Objeto.Categoria.BALL, 1.0);
    private static final Objeto SUPER_BALL = new Objeto(2, "Super Ball", 600, "", Objeto.Categoria.BALL, 1.5);
    private static final Objeto POCION = new Objeto(4, "Poción", 300, "", Objeto.Categoria.CURACION, 40);

    @Test
    void cuentaLasBalls() {
        Mochila m = new Mochila();
        m.poner(POKE_BALL, 5);
        m.poner(SUPER_BALL, 2);
        m.poner(POCION, 3);
        assertEquals(7, m.totalBalls());
        assertEquals(2, m.deCategoria(Objeto.Categoria.BALL).size());
        assertEquals(3, m.cantidad(POCION));
    }

    @Test
    void ponerCeroLoQuita() {
        Mochila m = new Mochila();
        m.poner(POCION, 1);
        m.poner(POCION, 0);
        assertTrue(m.estaVacia());
        assertEquals(0, m.cantidad(POCION));
    }

    @Test
    void lasBallsNoSeUsanEnCombate() {
        assertFalse(POKE_BALL.usableEnCombate());
        assertTrue(POCION.usableEnCombate());
    }
}
