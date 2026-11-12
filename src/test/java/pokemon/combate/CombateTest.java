package pokemon.combate;

import org.junit.jupiter.api.Test;
import pokemon.combate.movimientos.Ataque;
import pokemon.pokemon.Pokemon;
import pokemon.pokemon.Tipo;

import static org.junit.jupiter.api.Assertions.*;

class CombateTest {

    private static Pokemon pokemon(Tipo t1, Tipo t2, double ataque, double defensa) {
        Pokemon p = new Pokemon();
        p.setNombre("Prueba");
        p.setTipo1(t1);
        p.setTipo2(t2);
        p.setVitalidad(100);
        p.setAtaque(ataque);
        p.setDefensa(defensa);
        p.setAtaqueEspecial(ataque);
        p.setDefensaEspecial(defensa);
        p.setEstamina(150);
        p.setVelocidad(80);
        p.prepararCombate();
        return p;
    }

    private static Ataque ataque(Tipo tipo, int potencia) {
        Ataque a = new Ataque();
        a.setNombreMovimiento("golpe");
        a.setTipo(tipo);
        a.setPotencia(potencia);
        return a;
    }

    @Test
    void tablaDeTipos() {
        assertEquals(2.0, TablaTipos.obtenerMultiplicador(Tipo.AGUA, Tipo.FUEGO));
        assertEquals(0.5, TablaTipos.obtenerMultiplicador(Tipo.FUEGO, Tipo.AGUA));
        assertEquals(0.0, TablaTipos.obtenerMultiplicador(Tipo.NORMAL, Tipo.FANTASMA));
        assertEquals(1.0, TablaTipos.obtenerMultiplicador(Tipo.NORMAL, Tipo.AGUA));
        // Doble tipo: Eléctrico contra Agua/Volador (Gyarados) es x4
        assertEquals(4.0, TablaTipos.multiplicador(Tipo.ELECTRICO, pokemon(Tipo.AGUA, Tipo.VOLADOR, 50, 50)));
        // Tierra no afecta a voladores aunque el otro tipo sea débil
        assertEquals(0.0, TablaTipos.multiplicador(Tipo.TIERRA, pokemon(Tipo.ELECTRICO, Tipo.VOLADOR, 50, 50)));
    }

    @Test
    void elDanioEsRazonable() {
        Pokemon a = pokemon(Tipo.NORMAL, null, 80, 80);
        Pokemon d = pokemon(Tipo.NORMAL, null, 80, 80);
        double danio = Combate.calcularDanio(a, d, ataque(Tipo.LUCHA, 80), 1.0, false);
        // Con estadísticas iguales y sin bonificaciones: entre 3 y 5 golpes para debilitar a 100 PS
        assertTrue(danio >= 18 && danio <= 30, "daño " + danio);
    }

    @Test
    void elTipoPropioYLaEficaciaSuben() {
        Pokemon a = pokemon(Tipo.FUEGO, null, 80, 80);
        Pokemon d = pokemon(Tipo.PLANTA, null, 80, 80);
        double normal = Combate.calcularDanio(a, d, ataque(Tipo.NORMAL, 80), 1.0, false);
        double eficaz = Combate.calcularDanio(a, d, ataque(Tipo.FUEGO, 80), 2.0, false);
        assertTrue(eficaz > normal * 2.2, normal + " vs " + eficaz);
    }

    @Test
    void elDanioNuncaEsNegativoNiCero() {
        Pokemon a = pokemon(Tipo.NORMAL, null, 5, 5);
        Pokemon d = pokemon(Tipo.ROCA, null, 250, 250);
        assertTrue(Combate.calcularDanio(a, d, ataque(Tipo.NORMAL, 20), 0.5, false) >= 1);
    }
}
