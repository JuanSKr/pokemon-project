/*
POR HACER:
1. Constructores
2. Getters and Setters (consultar cuando hacer y cuando no)
3. Métodos
 */

package juego_pokemon.pokemon;

import java.util.Scanner;

public class Ataque extends Movimiento {

    protected int potencia;
    protected Tipo tipoMovimiento;


    static Ataque lanzallamas = new Ataque("Lanzallamas", 80, Tipo.FUEGO);
    static Ataque cascada = new Ataque("Cascada", 50, Tipo.AGUA);
    static Ataque huesopalo = new Ataque("Hueso palo", 30, Tipo.TIERRA);


    // Constructor con todos los parámetros

    public Ataque(String nombreMovimiento, int potencia, Tipo tipoMovimiento) {
        super(nombreMovimiento);
        this.nombreMovimiento = nombreMovimiento;
        this.potencia = potencia;
        this.tipoMovimiento = tipoMovimiento;
    }

    // Construtor por defecto

    public Ataque() {
        this.nombreMovimiento = "";
        this.potencia = 0;
        this.tipoMovimiento = null;
    }

    // Constructor copia

    public Ataque(Ataque a) {
        this.nombreMovimiento = a.nombreMovimiento;
        this.potencia = a.potencia;
        this.tipoMovimiento = a.tipoMovimiento;
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

    public Tipo getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(Tipo tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }


    public static void main(String[] args) {

        System.out.println("Lanzallamas: " + lanzallamas.getTipoMovimiento());
        System.out.println("Cascada: " + cascada.getTipoMovimiento());
        System.out.println("Hueso palo: " + huesopalo.getTipoMovimiento());


    }


}
