package Pokemon.Entrenador;

public class Mochila {
    private int idMochila;
    private int idEntrenador;
    private int idObjeto;
    private int idObjeto2;
    private int idObjeto3;
    private int idObjeto4;
    private int idObjeto5;
    private int idObjeto6;

    public Mochila(int idMochila, int idEntrenador, int idObjeto, int idObjeto2, int idObjeto3, int idObjeto4, int idObjeto5, int idObjeto6) {
        this.idMochila = idMochila;
        this.idEntrenador = idEntrenador;
        this.idObjeto = idObjeto;
        this.idObjeto2 = idObjeto2;
        this.idObjeto3 = idObjeto3;
        this.idObjeto4 = idObjeto4;
        this.idObjeto5 = idObjeto5;
        this.idObjeto6 = idObjeto6;
    }

    public Mochila() {
        this.idMochila = 0;
        this.idEntrenador = 0;
        this.idObjeto = 0;
        this.idObjeto2 = 0;
        this.idObjeto3 = 0;
        this.idObjeto4 = 0;
        this.idObjeto5 = 0;
        this.idObjeto6 = 0;
    }

    public int getIdMochila() {
        return idMochila;
    }

    public void setIdMochila(int idMochila) {
        this.idMochila = idMochila;
    }

    public int getIdEntrenador() {
        return idEntrenador;
    }

    public void setIdEntrenador(int idEntrenador) {
        this.idEntrenador = idEntrenador;
    }

    public int getIdObjeto() {
        return idObjeto;
    }

    public void setIdObjeto(int idObjeto) {
        this.idObjeto = idObjeto;
    }

    public int getIdObjeto2() {
        return idObjeto2;
    }

    public void setIdObjeto2(int idObjeto2) {
        this.idObjeto2 = idObjeto2;
    }

    public int getIdObjeto3() {
        return idObjeto3;
    }

    public void setIdObjeto3(int idObjeto3) {
        this.idObjeto3 = idObjeto3;
    }

    public int getIdObjeto4() {
        return idObjeto4;
    }

    public void setIdObjeto4(int idObjeto4) {
        this.idObjeto4 = idObjeto4;
    }

    public int getIdObjeto5() {
        return idObjeto5;
    }

    public void setIdObjeto5(int idObjeto5) {
        this.idObjeto5 = idObjeto5;
    }

    public int getIdObjeto6() {
        return idObjeto6;
    }

    public void setIdObjeto6(int idObjeto6) {
        this.idObjeto6 = idObjeto6;
    }

    @Override
    public String toString() {
        return "Mochila{" +
                "idMochila=" + idMochila +
                ", idEntrenador=" + idEntrenador +
                ", idObjeto=" + idObjeto +
                ", idObjeto2=" + idObjeto2 +
                ", idObjeto3=" + idObjeto3 +
                ", idObjeto4=" + idObjeto4 +
                ", idObjeto5=" + idObjeto5 +
                ", idObjeto6=" + idObjeto6 +
                '}';
    }
}
