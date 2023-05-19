/*
POR HACER:
1. Constructores
2. Getters and Setters (consultar cuando hacer y cuando no)
3. Métodos
 */

package Pokemon.Combate.Movimientos;

import Pokemon.Pokemon.Pokemon;
import Pokemon.Pokemon.Tipo;

/*
 - CAMBIAR TODOS LOS VALORES DE LOS ATAQUES
 */

public class Ataque extends Movimiento {

    // Constructor con todos los parámetros

    public Ataque(String nombreMovimiento, int potencia, int nivelAprendizaje, Tipo tipo, String tipoMovimiento, int turnos) {
        super(nombreMovimiento, tipo, potencia, tipoMovimiento, turnos);
        this.nombreMovimiento = nombreMovimiento;
        this.potencia = potencia;
        this.tipo = tipo;
        this.tipoMovimiento = tipoMovimiento;
    }

    // Construtor por defecto

    public Ataque() {
        this.nombreMovimiento = "";
        this.potencia = 0;
        this.tipo = null;
        this.tipoMovimiento = "";

    }

    // Constructor copia

    public Ataque(Ataque a) {
        this.nombreMovimiento = a.nombreMovimiento;
        this.potencia = a.potencia;
        this.tipo = a.tipo;
    }

    // Metodos

    /**
     * Se calcula el coste de estamina del ataque mediante su fórmula
     * @param pokemon
     * @param ataque
     * @return
     */

    public static double costeEstamina(Pokemon pokemon, Ataque ataque) {

       double coste = (ataque.getPotencia() / 2);

       return (pokemon.getEstamina() - coste);

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
