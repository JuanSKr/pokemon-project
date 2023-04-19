/*
POR HACER:
1. Constructores
2. Getters and Setters (consultar cuando hacer y cuando no)
3. Métodos
 */

package Pokemon.Combate;

import Pokemon.Pokemon.Tipo;

/*
 - CAMBIAR TODOS LOS VALORES DE LOS ATAQUES
 */

public class Ataque extends Movimiento {

    protected int potencia;
    protected Tipo tipoMovimiento;


    public static Ataque lanzallamas = new Ataque("Lanzallamas", 80, Tipo.FUEGO);
    public static Ataque cascada = new Ataque("Cascada", 50, Tipo.AGUA);
    public static Ataque huesopalo = new Ataque("Hueso palo", 30, Tipo.TIERRA);

    public static Ataque ataqueaereo = new Ataque("Ataque aéreo", 80, Tipo.VOLADOR);
    public static Ataque picotaladro = new Ataque("Pico taladro", 20, Tipo.VOLADOR);
    public static Ataque picotazo = new Ataque("Picotazo", 65, Tipo.VOLADOR);
    public static Ataque excavar = new Ataque("Excavar", 30, Tipo.TIERRA);
    public static Ataque fisura = new Ataque("Fisura", 30, Tipo.TIERRA);
    public static Ataque hojafilada = new Ataque("Hoja afilada", 30, Tipo.PLANTA);
    public static Ataque latigocepa = new Ataque("Latigo cepa", 30, Tipo.PLANTA);
    public static Ataque agarre = new Ataque("Agarre", 30, Tipo.NORMAL);
    public static Ataque aranazo = new Ataque("Arañazo", 30, Tipo.NORMAL);
    public static Ataque ataquefuria = new Ataque("Ataque furia", 30, Tipo.NORMAL);
    public static Ataque ataquerapido = new Ataque("Ataque rapido", 30, Tipo.NORMAL);
    public static Ataque cabezazo = new Ataque("Cabezazo", 30, Tipo.NORMAL);
    public static Ataque cuchillada = new Ataque("Cuchillada", 30, Tipo.NORMAL);
    public static Ataque derribo = new Ataque("Derribo", 30, Tipo.NORMAL);
    public static Ataque golpekarate = new Ataque("Golpe kárate", 30, Tipo.NORMAL);
    public static Ataque doblepatada = new Ataque("Hueso palo", 30, Tipo.LUCHA);
    public static Ataque punotrueno = new Ataque("Hueso palo", 30, Tipo.ELECTRICO);


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
