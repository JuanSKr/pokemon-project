/*
POR HACER:
1. Constructores
2. Getters and Setters (consultar cuando hacer y cuando no)
3. Métodos
 */

package juego_pokemon.pokemon;


import java.util.HashMap;
import java.util.Scanner;

public class Ataque extends Movimiento {

    protected int potencia;
    protected Tipo tipoMovimiento;


    static HashMap<String, Ataque> movimientosAtaque = new HashMap<>();

    static Ataque lanzallamas = new Ataque(1, "Lanzallamas", 80, Tipo.FUEGO);
    static Ataque cascada = new Ataque(2, "Cascada", 50, Tipo.AGUA);
    static Ataque huesopalo = new Ataque(3, "Hueso palo", 30, Tipo.TIERRA);


    // Constructor con todos los parámetros

    public Ataque(int idMovimiento, String nombreMovimiento, int potencia, Tipo tipoMovimiento) {
        super(idMovimiento, nombreMovimiento);
        this.idMovimiento = idMovimiento;
        this.nombreMovimiento = nombreMovimiento;
        this.potencia = potencia;
        this.tipoMovimiento = tipoMovimiento;
    }

    // Construtor por defecto

    public Ataque() {
        this.idMovimiento = 0;
        this.nombreMovimiento = "";
        this.potencia = 0;
        this.tipoMovimiento = null;
    }

    // Constructor copia

    public Ataque(Ataque a) {
        this.idMovimiento = a.idMovimiento;
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

    public static HashMap<String, Ataque> getMovimientosAtaque() {
        return movimientosAtaque;
    }

    public static void setMovimientosAtaque(HashMap<String, Ataque> movimientosAtaque) {
        Ataque.movimientosAtaque = movimientosAtaque;
    }

    public static Ataque getLanzallamas() {
        return lanzallamas;
    }

    public static void setLanzallamas(Ataque lanzallamas) {
        Ataque.lanzallamas = lanzallamas;
    }


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int vidaPokemon = 15;
        int estamina = 100;


        System.out.println("------------------");
        System.out.println("   Movimientos");
        System.out.println("------------------");
        System.out.println("1. Lanzallamas");
        System.out.println("2. Movimiento 2");
        System.out.println("3. Movimiento 3");
        System.out.println("4. Movimiento 4");
        System.out.println("------------------");
        System.out.print("Elige un movimiento: ");
        int opcion = sc.nextInt();

        switch (opcion) {

            case 1:
                System.out.println("Estamina: "+estamina);
                System.out.println("----------------");
                System.out.println("Vida Pikachu enemigo: " + vidaPokemon);
                System.out.println("¡Pepe ha utilizado " + lanzallamas.getNombreMovimiento() + "!");
                System.out.println("El Pikachu enemigo ha recibido: " + lanzallamas.getPotencia() + " puntos de daño");
                System.out.println("Ahora el Pikachu enemigo tiene: " + calcularAtaque(vidaPokemon, lanzallamas) + " puntos de vida");
                System.out.println("Ahora tu estamina es de: "+costeEstamina(lanzallamas.getPotencia(), estamina));



        }

    }


}


