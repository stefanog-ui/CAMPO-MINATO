package modello;

public class Cella {
    private int id;
    private int numeroMine;
    private boolean hasMina;

    public Cella(int id, int numeroMine, boolean hasMina) {
        this.id = id;
        this.numeroMine = numeroMine;
        this.hasMina = hasMina;
    }

    public Cella(int id) {
        this.id = id;
        numeroMine = 0;
        hasMina = false;
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
}
