package pokemon.tienda;

/**
 * Un objeto de la tienda / mochila. El catálogo vive en la tabla `articulo` de la base de datos.
 */
public class Objeto {

    /** Para qué sirve el objeto. */
    public enum Categoria {
        /** Sirve para capturar. valor = multiplicador de captura. */
        BALL,
        /** Cura vida. valor = porcentaje de la vida máxima. */
        CURACION,
        /** Recupera estamina. valor = porcentaje de la estamina máxima. */
        ESTAMINA,
        /** Revive a un Pokémon debilitado. valor = porcentaje de vida con el que vuelve. */
        REVIVIR
    }

    private final int id;
    private final String nombre;
    private final int precio;
    private final String descripcion;
    private final Categoria categoria;
    private final double valor;

    public Objeto(int id, String nombre, int precio, String descripcion, Categoria categoria, double valor) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.valor = valor;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPrecio() {
        return precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public double getValor() {
        return valor;
    }

    /** true si se puede usar durante un combate. */
    public boolean usableEnCombate() {
        return categoria != Categoria.BALL;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Objeto otro && otro.id == id;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }

    @Override
    public String toString() {
        return nombre;
    }
}
