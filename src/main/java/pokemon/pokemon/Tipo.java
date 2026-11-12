package pokemon.pokemon;

public enum Tipo {
    ACERO("Acero", "#B8B8D0"),
    AGUA("Agua", "#6890F0"),
    BICHO("Bicho", "#A8B820"),
    DRAGON("Dragón", "#7038F8"),
    ELECTRICO("Eléctrico", "#F8C030"),
    FANTASMA("Fantasma", "#705898"),
    FUEGO("Fuego", "#F08030"),
    HADA("Hada", "#EE99AC"),
    HIELO("Hielo", "#98D8D8"),
    LUCHA("Lucha", "#C03028"),
    NORMAL("Normal", "#A8A878"),
    PLANTA("Planta", "#78C850"),
    PSIQUICO("Psíquico", "#F85888"),
    ROCA("Roca", "#B8A038"),
    SINIESTRO("Siniestro", "#705848"),
    TIERRA("Tierra", "#E0C068"),
    VENENO("Veneno", "#A040A0"),
    VIENTO("Viento", "#A890F0"),
    VOLADOR("Volador", "#A890F0"),
    ATRAPADO("Atrapado", "#68A090");

    private final String nombre;
    private final String color;

    Tipo(String nombre, String color) {
        this.nombre = nombre;
        this.color = color;
    }

    /** Nombre para mostrar en pantalla (con tildes). */
    public String getNombre() {
        return nombre;
    }

    /** Color asociado al tipo, en formato #RRGGBB. */
    public String getColor() {
        return color;
    }

    /**
     * Convierte el texto guardado en la base de datos en un Tipo.
     * Devuelve null si el texto está vacío, es "null" o no corresponde a ningún tipo.
     */
    public static Tipo desdeTexto(String texto) {
        if (texto == null) {
            return null;
        }
        String limpio = texto.trim().toUpperCase()
                .replace("Á", "A").replace("É", "E").replace("Í", "I").replace("Ó", "O").replace("Ú", "U");
        if (limpio.isEmpty() || limpio.equals("NULL")) {
            return null;
        }
        try {
            return Tipo.valueOf(limpio);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
