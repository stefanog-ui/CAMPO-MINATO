package modello;

public class Cella {
    private int id;
    private int numeroMine;
    private boolean hasMina;
    private boolean isScoperta;
    private boolean hasBandiera = false;

    public Cella(int id, int numeroMine, boolean hasMina) {
        this.id = id;
        this.numeroMine = numeroMine;
        this.hasMina = hasMina;
        this.isScoperta = false;
    }

    public Cella(int id) {
        this.id = id;
        numeroMine = 0;
        hasMina = false;
        isScoperta = false;
    }

    public int getId() {
        return id;
    }

    public int getNumeroMine() {
        return numeroMine;
    }

    public boolean isHasMina() {
        return hasMina;
    }

    public void setHasMina(boolean hasMina) {
        this.hasMina = hasMina;
    }

    public boolean isScoperta() {
        return isScoperta;
    }

    public void setScoperta(boolean scoperta) {
        isScoperta = scoperta;
    }

    public boolean isHasBandiera() {
        return hasBandiera;
    }

    public void setHasBandiera(boolean hasBandiera) {
        this.hasBandiera = hasBandiera;
    }
}
