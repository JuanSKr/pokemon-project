package juego_pokemon.pokemon;

/*	UN TIPO SERÁ UN ENUMERADO CON TODAS LAS POSIBLES OPCIONES DE TIPO DE POKÉMON Y DE
	ATAQUE, EN ESTE CASO, LAS OPCIONES SERÁN:
*/

public enum tipoPokemon {
	AGUA("Agua"), BICHO("Bicho"), DRAGON("Dragón"), ELECTRICO("Eléctrico"), FANTASMA("Fantasma"), FUEGO("Fuego"),
	HADA("Hada"), HIELO("Hielo"), LUCHA("Lucha"), NORMAL("Normal"), PLANTA("Planta"), PSIQUICO("Psíquico"),
	ROCA("Roca"), SINIESTRO("Siniestro"), TIERRA("Tierra"), VENENO("Veneno"), VOLADOR("Volador");

	private String nombreTipo;

	// CONSTRUCTOR DE LA CLASE ENUM TIPOPOKEMON
	private tipoPokemon(String nombreTipo) {
		this.nombreTipo = nombreTipo;
	}

	// GETTER DEL NOMBRE DEL TIPO DE POKEMON
	public String getNombreTipo() {
		return nombreTipo;
	}
}
