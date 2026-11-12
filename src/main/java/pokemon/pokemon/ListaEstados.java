package pokemon.pokemon;

/**
 * Estados alterados que puede sufrir un Pokémon durante un combate.
 * Los efectos de cada turno se aplican en {@link pokemon.combate.Combate}.
 */
public enum ListaEstados {

    CONFUSO("Confuso", "CNF", "#B05CC8", "Puede herirse a sí mismo en lugar de actuar."),
    MALDITO("Maldito", "MLD", "#4B3B6B", "Pierde vida al final de cada turno."),
    ENAMORADO("Enamorado", "ENA", "#E85C9C", "A veces no ataca por estar enamorado."),
    DRENADO("Drenado", "DRN", "#5C9C3C", "El rival le roba vida cada turno."),
    ENVENENADO("Envenenado", "ENV", "#9C3CB0", "Pierde vida al final de cada turno.");

    private final String nombre;
    private final String abreviatura;
    private final String color;
    private final String descripcion;

    ListaEstados(String nombre, String abreviatura, String color, String descripcion) {
        this.nombre = nombre;
        this.abreviatura = abreviatura;
        this.color = color;
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public String getAbreviatura() {
        return abreviatura;
    }

    public String getColor() {
        return color;
    }

    public String getDescripcion() {
        return descripcion;
    }

    /** Convierte el texto de la base de datos en un estado, o null si no se reconoce. */
    public static ListaEstados desdeTexto(String texto) {
        if (texto == null) return null;
        try {
            return ListaEstados.valueOf(texto.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
