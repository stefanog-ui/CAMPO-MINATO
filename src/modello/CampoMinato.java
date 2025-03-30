package modello;

public class CampoMinato {
    private Cella campo[][];

    public CampoMinato(Cella[][] campo) {
        this.campo = campo;
    }

    public Cella[][] getCampo() {
        return campo;
    }


}
