package pokemon.entrenador;

import pokemon.tienda.Objeto;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Objetos que lleva el entrenador y cuántos tiene de cada uno.
 * Se carga desde la tabla `inventario` (ver PokemonCRUD.getMochila).
 */
public class Mochila {

    private final Map<Objeto, Integer> objetos = new LinkedHashMap<>();

    public void poner(Objeto objeto, int cantidad) {
        if (cantidad <= 0) {
            objetos.remove(objeto);
        } else {
            objetos.put(objeto, cantidad);
        }
    }

    public int cantidad(Objeto objeto) {
        return objetos.getOrDefault(objeto, 0);
    }

    public Map<Objeto, Integer> getObjetos() {
        return objetos;
    }

    /** Objetos de una categoría con al menos una unidad. */
    public List<Objeto> deCategoria(Objeto.Categoria categoria) {
        List<Objeto> lista = new ArrayList<>();
        for (Map.Entry<Objeto, Integer> e : objetos.entrySet()) {
            if (e.getKey().getCategoria() == categoria && e.getValue() > 0) {
                lista.add(e.getKey());
            }
        }
        return lista;
    }

    /** Número total de Poké Balls de cualquier tipo. */
    public int totalBalls() {
        int total = 0;
        for (Objeto o : deCategoria(Objeto.Categoria.BALL)) {
            total += cantidad(o);
        }
        return total;
    }

    public boolean estaVacia() {
        return objetos.isEmpty();
    }
}
