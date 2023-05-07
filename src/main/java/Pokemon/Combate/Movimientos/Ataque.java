/*
POR HACER:
1. Constructores
2. Getters and Setters (consultar cuando hacer y cuando no)
3. Métodos
 */

package Pokemon.Combate.Movimientos;

import Pokemon.Pokemon.Tipo;

/*
 - CAMBIAR TODOS LOS VALORES DE LOS ATAQUES
 */

public class Ataque extends Movimiento {

    protected int potencia;



    // Constructor con todos los parámetros

    public Ataque(String nombreMovimiento, int potencia, int nivelAprendizaje, Tipo tipo) {
        super(nombreMovimiento, tipo);
        this.nombreMovimiento = nombreMovimiento;
        this.potencia = potencia;
        this.tipo = tipo;
    }

    // Construtor por defecto

    public Ataque() {
        this.nombreMovimiento = "";
        this.potencia = 0;
        this.tipo = null;

    }

    // Constructor copia

    public Ataque(Ataque a) {
        this.nombreMovimiento = a.nombreMovimiento;
        this.potencia = a.potencia;
        this.tipo = a.tipo;
    }

    // Metodos

    // Coste estamina (por crear)

    public static int costeEstamina(int potencia, int estamina) {

        int costeEstamina = (potencia / 2);

        return estamina - costeEstamina;
    }

    public static int calcularAtaque(int vidaPokemon, Ataque ataque) {

        int vidaFinal = vidaPokemon - ataque.getPotencia();

        return vidaFinal;

    }

    // Getters y Setters

    public int getPotencia() {
        return potencia;
    }

    public void setPotencia(int potencia) {
        this.potencia = potencia;
    }

    @Override
    public String toString() {
        return "Ataque{" +
                "nombreMovimiento='" + nombreMovimiento + '\'' +
                "potencia=" + potencia +
                "tipo=" + tipo +
                '}';
    }
}
