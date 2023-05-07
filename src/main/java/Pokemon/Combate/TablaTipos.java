package Pokemon.Combate;

import Pokemon.Pokemon.Pokedex;
import Pokemon.Pokemon.Pokemon;
import Pokemon.Pokemon.Tipo;

public enum TablaTipos {

    NEUTRO,
    VENTAJA,
    DOBLE_VENTAJA,
    DESVENTAJA;

    /**
     *
     * @param pokemon1
     * @param pokemon2
     * @return Este método te pide dos Pokemons por parámetro y compara los tipos, devuelve un ENUM.
     */

    public static TablaTipos tablaTipos(Pokemon pokemon1, Pokemon pokemon2) {

        // Tipo ACERO

        // Efectivo contra:
        if (pokemon1.getTipo1() == Tipo.ACERO && pokemon2.getTipo1() == Tipo.HADA) {
            return VENTAJA;
        } else if (pokemon1.getTipo1() == Tipo.ACERO && pokemon2.getTipo1() == Tipo.HIELO) {
            return DOBLE_VENTAJA;
        } else if (pokemon1.getTipo1() == Tipo.ACERO && pokemon2.getTipo1() == Tipo.ROCA) {
            return DOBLE_VENTAJA;

            // Débil contra:
        } else if (pokemon1.getTipo1() == Tipo.ACERO && pokemon2.getTipo1() == Tipo.LUCHA) {
            return DESVENTAJA;
        } else if (pokemon1.getTipo1() == Tipo.ACERO && pokemon2.getTipo1() == Tipo.FUEGO) {
            return DESVENTAJA;
        } else if (pokemon1.getTipo1() == Tipo.ACERO && pokemon2.getTipo1() == Tipo.TIERRA) {
            return DESVENTAJA;

            // Tipo AGUA

            // Efectivo contra:
        } else if (pokemon1.getTipo1() == Tipo.AGUA && pokemon2.getTipo1() == Tipo.FUEGO) {
            return DOBLE_VENTAJA;

        } else if (pokemon1.getTipo1() == Tipo.AGUA && pokemon2.getTipo1() == Tipo.TIERRA) {
            return VENTAJA;

        } else if (pokemon1.getTipo1() == Tipo.AGUA && pokemon2.getTipo1() == Tipo.ROCA) {
            return VENTAJA;

            // Débil contra:
        } else if (pokemon1.getTipo1() == Tipo.AGUA && pokemon2.getTipo1() == Tipo.ELECTRICO) {
            return DESVENTAJA;

        } else if (pokemon1.getTipo1() == Tipo.AGUA && pokemon2.getTipo1() == Tipo.PLANTA) {
            return DESVENTAJA;

            // Tipo BICHO

            // Efectivo contra:
        } else if (pokemon1.getTipo1() == Tipo.BICHO && pokemon2.getTipo1() == Tipo.PLANTA) {
            return DOBLE_VENTAJA;

        } else if (pokemon1.getTipo1() == Tipo.BICHO && pokemon2.getTipo1() == Tipo.SINIESTRO) {
            return VENTAJA;

        } else if (pokemon1.getTipo1() == Tipo.BICHO && pokemon2.getTipo1() == Tipo.PSIQUICO) {
            return VENTAJA;

            // Débil contra:
        } else if (pokemon1.getTipo1() == Tipo.BICHO && pokemon2.getTipo1() == Tipo.FUEGO) {
            return DESVENTAJA;

        } else if (pokemon1.getTipo1() == Tipo.BICHO && pokemon2.getTipo1() == Tipo.VOLADOR) {
            return DESVENTAJA;

        } else if (pokemon1.getTipo1() == Tipo.BICHO && pokemon2.getTipo1() == Tipo.ROCA) {
            return DESVENTAJA;

            // Tipo DRAGON

            // Efectivo contra:
        } else if (pokemon1.getTipo1() == Tipo.DRAGON && pokemon2.getTipo1() == Tipo.DRAGON) {
            return VENTAJA;

            // Débil contra:
        } else if (pokemon1.getTipo1() == Tipo.DRAGON && pokemon2.getTipo1() == Tipo.HADA) {
            return DESVENTAJA;

        } else if (pokemon1.getTipo1() == Tipo.DRAGON && pokemon2.getTipo1() == Tipo.HIELO) {
            return DESVENTAJA;

            // Tipo ELÉCTRICO

            // Efectivo contra:
        } else if (pokemon1.getTipo1() == Tipo.ELECTRICO && pokemon2.getTipo1() == Tipo.VOLADOR) {
            return VENTAJA;
        } else if (pokemon1.getTipo1() == Tipo.ELECTRICO && pokemon2.getTipo1() == Tipo.AGUA) {
            return VENTAJA;

            // Débil contra:
        } else if (pokemon1.getTipo1() == Tipo.ELECTRICO && pokemon2.getTipo1() == Tipo.TIERRA) {
            return DESVENTAJA;

            // Tipo FANTASMA

            // Efectivo contra:
        } else if (pokemon1.getTipo1() == Tipo.FANTASMA && pokemon2.getTipo1() == Tipo.FANTASMA) {
            return VENTAJA;
        } else if (pokemon1.getTipo1() == Tipo.FANTASMA && pokemon2.getTipo1() == Tipo.PSIQUICO) {
            return VENTAJA;

            // Débil contra:
        } else if (pokemon1.getTipo1() == Tipo.FANTASMA && pokemon2.getTipo1() == Tipo.FANTASMA) {
            return DESVENTAJA;
        } else if (pokemon1.getTipo1() == Tipo.FANTASMA && pokemon2.getTipo1() == Tipo.SINIESTRO) {
            return DESVENTAJA;

            // Tipo FUEGO

            // Efectivo contra:
        } else if (pokemon1.getTipo1() == Tipo.FUEGO && pokemon2.getTipo1() == Tipo.BICHO) {
            return VENTAJA;
        } else if (pokemon1.getTipo1() == Tipo.FUEGO && pokemon2.getTipo1() == Tipo.PLANTA) {
            return DOBLE_VENTAJA;
        } else if (pokemon1.getTipo1() == Tipo.FUEGO && pokemon2.getTipo1() == Tipo.ACERO) {
            return VENTAJA;
        } else if (pokemon1.getTipo1() == Tipo.FUEGO && pokemon2.getTipo1() == Tipo.HIELO) {
            return DOBLE_VENTAJA;

            // Débil contra:
        } else if (pokemon1.getTipo1() == Tipo.FUEGO && pokemon2.getTipo1() == Tipo.TIERRA) {
            return DESVENTAJA;
        } else if (pokemon1.getTipo1() == Tipo.FUEGO && pokemon2.getTipo1() == Tipo.ROCA) {
            return DESVENTAJA;
        } else if (pokemon1.getTipo1() == Tipo.FUEGO && pokemon2.getTipo1() == Tipo.AGUA) {
            return DESVENTAJA;

            // Tipo HADA

            // Efectivo contra:
        } else if (pokemon1.getTipo1() == Tipo.HADA && pokemon2.getTipo1() == Tipo.LUCHA) {
            return VENTAJA;
        } else if (pokemon1.getTipo1() == Tipo.HADA && pokemon2.getTipo1() == Tipo.SINIESTRO) {
            return VENTAJA;
        } else if (pokemon1.getTipo1() == Tipo.HADA && pokemon2.getTipo1() == Tipo.DRAGON) {
            return DOBLE_VENTAJA;

            // Débil contra:
        } else if (pokemon1.getTipo1() == Tipo.HADA && pokemon2.getTipo1() == Tipo.VENENO) {
            return DESVENTAJA;
        } else if (pokemon1.getTipo1() == Tipo.HADA && pokemon2.getTipo1() == Tipo.ACERO) {
            return DESVENTAJA;

            // Tipo HIELO

            // Efectivo contra:
        } else if (pokemon1.getTipo1() == Tipo.HIELO && pokemon2.getTipo1() == Tipo.DRAGON) {
            return VENTAJA;
        } else if (pokemon1.getTipo1() == Tipo.HIELO && pokemon2.getTipo1() == Tipo.VOLADOR) {
            return VENTAJA;
        } else if (pokemon1.getTipo1() == Tipo.HIELO && pokemon2.getTipo1() == Tipo.PLANTA) {
            return DOBLE_VENTAJA;
        } else if (pokemon1.getTipo1() == Tipo.HIELO && pokemon2.getTipo1() == Tipo.TIERRA) {
            return DOBLE_VENTAJA;

            // Débil contra:
        } else if (pokemon1.getTipo1() == Tipo.HIELO && pokemon2.getTipo1() == Tipo.LUCHA) {
            return DESVENTAJA;
        } else if (pokemon1.getTipo1() == Tipo.HIELO && pokemon2.getTipo1() == Tipo.FUEGO) {
            return DESVENTAJA;
        } else if (pokemon1.getTipo1() == Tipo.HIELO && pokemon2.getTipo1() == Tipo.ROCA) {
            return DESVENTAJA;
        } else if (pokemon1.getTipo1() == Tipo.HIELO && pokemon2.getTipo1() == Tipo.ACERO) {
            return DESVENTAJA;

            // Tipo LUCHA

            // Efectivo contra:
        } else if (pokemon1.getTipo1() == Tipo.LUCHA && pokemon2.getTipo1() == Tipo.SINIESTRO) {
            return VENTAJA;
        } else if (pokemon1.getTipo1() == Tipo.LUCHA && pokemon2.getTipo1() == Tipo.HIELO) {
            return DOBLE_VENTAJA;
        } else if (pokemon1.getTipo1() == Tipo.LUCHA && pokemon2.getTipo1() == Tipo.NORMAL) {
            return VENTAJA;
        } else if (pokemon1.getTipo1() == Tipo.LUCHA && pokemon2.getTipo1() == Tipo.ROCA) {
            return VENTAJA;
        } else if (pokemon1.getTipo1() == Tipo.LUCHA && pokemon2.getTipo1() == Tipo.AGUA) {
            return DOBLE_VENTAJA;

            // Débil contra:
        } else if (pokemon1.getTipo1() == Tipo.LUCHA && pokemon2.getTipo1() == Tipo.HADA) {
            return DESVENTAJA;
        } else if (pokemon1.getTipo1() == Tipo.LUCHA && pokemon2.getTipo1() == Tipo.VOLADOR) {
            return DESVENTAJA;
        } else if (pokemon1.getTipo1() == Tipo.LUCHA && pokemon2.getTipo1() == Tipo.PSIQUICO) {
            return DESVENTAJA;

            // Tipo NORMAL

            // Efectivo contra:
            // Nadie

            // Débil contra:
        } else if (pokemon1.getTipo1() == Tipo.NORMAL && pokemon2.getTipo1() == Tipo.LUCHA) {
            return DESVENTAJA;

            // Tipo PLANTA

            // Efectivo contra:
        } else if (pokemon1.getTipo1() == Tipo.PLANTA && pokemon2.getTipo1() == Tipo.TIERRA) {
            return VENTAJA;
        } else if (pokemon1.getTipo1() == Tipo.PLANTA && pokemon2.getTipo1() == Tipo.ROCA) {
            return VENTAJA;
        } else if (pokemon1.getTipo1() == Tipo.PLANTA && pokemon2.getTipo1() == Tipo.AGUA) {
            return DOBLE_VENTAJA;

            // Débil contra:
        } else if (pokemon1.getTipo1() == Tipo.PLANTA && pokemon2.getTipo1() == Tipo.BICHO) {
            return DESVENTAJA;
        } else if (pokemon1.getTipo1() == Tipo.PLANTA && pokemon2.getTipo1() == Tipo.FUEGO) {
            return DESVENTAJA;
        } else if (pokemon1.getTipo1() == Tipo.PLANTA && pokemon2.getTipo1() == Tipo.VOLADOR) {
            return DESVENTAJA;
        } else if (pokemon1.getTipo1() == Tipo.PLANTA && pokemon2.getTipo1() == Tipo.HIELO) {
            return DESVENTAJA;
        } else if (pokemon1.getTipo1() == Tipo.PLANTA && pokemon2.getTipo1() == Tipo.VENENO) {
            return DESVENTAJA;

            // Tipo PSÍQUICO

            // Efectivo contra:
        } else if (pokemon1.getTipo1() == Tipo.PSIQUICO && pokemon2.getTipo1() == Tipo.LUCHA) {
            return VENTAJA;
        } else if (pokemon1.getTipo1() == Tipo.PSIQUICO && pokemon2.getTipo1() == Tipo.VENENO) {
            return VENTAJA;

            // Débil contra:
        } else if (pokemon1.getTipo1() == Tipo.PSIQUICO && pokemon2.getTipo1() == Tipo.BICHO) {
            return DESVENTAJA;
        } else if (pokemon1.getTipo1() == Tipo.PSIQUICO && pokemon2.getTipo1() == Tipo.SINIESTRO) {
            return DESVENTAJA;
        } else if (pokemon1.getTipo1() == Tipo.PSIQUICO && pokemon2.getTipo1() == Tipo.FANTASMA) {
            return DESVENTAJA;

            // Tipo ROCA

            // Efectivo contra:
        } else if (pokemon1.getTipo1() == Tipo.ROCA && pokemon2.getTipo1() == Tipo.BICHO) {
            return DOBLE_VENTAJA;
        } else if (pokemon1.getTipo1() == Tipo.ROCA && pokemon2.getTipo1() == Tipo.FUEGO) {
            return VENTAJA;
        } else if (pokemon1.getTipo1() == Tipo.ROCA && pokemon2.getTipo1() == Tipo.VOLADOR) {
            return VENTAJA;
        } else if (pokemon1.getTipo1() == Tipo.ROCA && pokemon2.getTipo1() == Tipo.HIELO) {
            return VENTAJA;

            // Débil contra:
        } else if (pokemon1.getTipo1() == Tipo.ROCA && pokemon2.getTipo1() == Tipo.LUCHA) {
            return DESVENTAJA;
        } else if (pokemon1.getTipo1() == Tipo.ROCA && pokemon2.getTipo1() == Tipo.PLANTA) {
            return DESVENTAJA;
        } else if (pokemon1.getTipo1() == Tipo.ROCA && pokemon2.getTipo1() == Tipo.TIERRA) {
            return DESVENTAJA;
        } else if (pokemon1.getTipo1() == Tipo.ROCA && pokemon2.getTipo1() == Tipo.ACERO) {
            return DESVENTAJA;
        } else if (pokemon1.getTipo1() == Tipo.ROCA && pokemon2.getTipo1() == Tipo.AGUA) {
            return DESVENTAJA;

            // Tipo SINIESTRO

            // Efectivo contra:
        } else if (pokemon1.getTipo1() == Tipo.SINIESTRO && pokemon2.getTipo1() == Tipo.FANTASMA) {
            return DOBLE_VENTAJA;
        } else if (pokemon1.getTipo1() == Tipo.SINIESTRO && pokemon2.getTipo1() == Tipo.PSIQUICO) {
            return VENTAJA;

            // Débil contra:
        } else if (pokemon1.getTipo1() == Tipo.SINIESTRO && pokemon2.getTipo1() == Tipo.BICHO) {
            return DESVENTAJA;
        } else if (pokemon1.getTipo1() == Tipo.SINIESTRO && pokemon2.getTipo1() == Tipo.HADA) {
            return DESVENTAJA;
        } else if (pokemon1.getTipo1() == Tipo.SINIESTRO && pokemon2.getTipo1() == Tipo.LUCHA) {
            return DESVENTAJA;

            // Tipo TIERRA

            // Efectivo contra:
        } else if (pokemon1.getTipo1() == Tipo.TIERRA && pokemon2.getTipo1() == Tipo.ELECTRICO) {
            return DOBLE_VENTAJA;
        } else if (pokemon1.getTipo1() == Tipo.TIERRA && pokemon2.getTipo1() == Tipo.FUEGO) {
            return VENTAJA;
        } else if (pokemon1.getTipo1() == Tipo.TIERRA && pokemon2.getTipo1() == Tipo.VENENO) {
            return VENTAJA;
        } else if (pokemon1.getTipo1() == Tipo.TIERRA && pokemon2.getTipo1() == Tipo.ROCA) {
            return VENTAJA;
        } else if (pokemon1.getTipo1() == Tipo.TIERRA && pokemon2.getTipo1() == Tipo.ACERO) {
            return VENTAJA;

            // Débil contra:
        } else if (pokemon1.getTipo1() == Tipo.TIERRA && pokemon2.getTipo1() == Tipo.PLANTA) {
            return DESVENTAJA;
        } else if (pokemon1.getTipo1() == Tipo.TIERRA && pokemon2.getTipo1() == Tipo.HIELO) {
            return DESVENTAJA;
        } else if (pokemon1.getTipo1() == Tipo.TIERRA && pokemon2.getTipo1() == Tipo.AGUA) {
            return DESVENTAJA;

            // Tipo VENENO

            // Efectivo contra:
        } else if (pokemon1.getTipo1() == Tipo.VENENO && pokemon2.getTipo1() == Tipo.PLANTA) {
            return DOBLE_VENTAJA;
        } else if (pokemon1.getTipo1() == Tipo.VENENO && pokemon2.getTipo1() == Tipo.HADA) {
            return VENTAJA;

            // Débil contra:
        } else if (pokemon1.getTipo1() == Tipo.VENENO && pokemon2.getTipo1() == Tipo.TIERRA) {
            return DESVENTAJA;
        } else if (pokemon1.getTipo1() == Tipo.VENENO && pokemon2.getTipo1() == Tipo.PSIQUICO) {
            return DESVENTAJA;

            // Tipo VOLADOR

            // Efectivo contra:
        } else if (pokemon1.getTipo1() == Tipo.VOLADOR && pokemon2.getTipo1() == Tipo.BICHO) {
            return DOBLE_VENTAJA;
        } else if (pokemon1.getTipo1() == Tipo.VOLADOR && pokemon2.getTipo1() == Tipo.LUCHA) {
            return VENTAJA;
        } else if (pokemon1.getTipo1() == Tipo.VOLADOR && pokemon2.getTipo1() == Tipo.PLANTA) {
            return VENTAJA;

            // Débil contra:
        } else if (pokemon1.getTipo1() == Tipo.VOLADOR && pokemon2.getTipo1() == Tipo.ELECTRICO) {
            return DESVENTAJA;
        } else if (pokemon1.getTipo1() == Tipo.VOLADOR && pokemon2.getTipo1() == Tipo.HIELO) {
            return DESVENTAJA;
        } else if (pokemon1.getTipo1() == Tipo.VOLADOR && pokemon2.getTipo1() == Tipo.ROCA) {
            return DESVENTAJA;
        } else {
            return NEUTRO;

        }
    }


    public static void main(String[] args) {
        System.out.println(tablaTipos(Pokedex.Charizard, Pokedex.Lapras));
    }

}


