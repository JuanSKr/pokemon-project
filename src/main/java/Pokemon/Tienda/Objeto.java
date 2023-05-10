package Pokemon.Tienda;

// CLASE OBJETO
public class Objeto {
    private int id;
    private String nombre;
    private int precio;
    private String descripcion;
    private double ataque;
	private double defensa;
	private double defensaEspecial;
	private double velocidad;
	private double estamina;

    // Constructor
    public Objeto(int id, String nombre, int precio, double ataque, double defensa, double defensaEspecial, double velocidad, double estamina, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.ataque = ataque;
		this.defensa = defensa;
		this.defensaEspecial = defensaEspecial;
		this.velocidad = velocidad;
		this.estamina = estamina;
    }

    public Objeto() {
        this.id = 0;
        this.nombre = "";
        this.descripcion = "";
        this.precio = 0;
        this.ataque = 0;
        this.defensa = 0;
        this.defensaEspecial = 0;
        this.velocidad = 0;
        this.estamina = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public double getAtaque() {
        return ataque;
    }

    public void setAtaque(double ataque) {
        this.ataque = ataque;
    }

    public double getDefensa() {
        return defensa;
    }

    public void setDefensa(double defensa) {
        this.defensa = defensa;
    }

    public double getDefensaEspecial() {
        return defensaEspecial;
    }

    public void setDefensaEspecial(double defensaEspecial) {
        this.defensaEspecial = defensaEspecial;
    }

    public double getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(double velocidad) {
        this.velocidad = velocidad;
    }

    public double getEstamina() {
        return estamina;
    }

    public void setEstamina(double estamina) {
        this.estamina = estamina;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Objeto{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", precio=" + precio +
                ", descripcion='" + descripcion + '\'' +
                ", ataque=" + ataque +
                ", defensa=" + defensa +
                ", defensaEspecial=" + defensaEspecial +
                ", velocidad=" + velocidad +
                ", estamina=" + estamina +
                '}';
    }
}