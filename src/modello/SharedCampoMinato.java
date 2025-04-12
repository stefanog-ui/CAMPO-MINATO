package modello;

public class SharedCampoMinato {
    public static SharedCampoMinato sharedCampo = new SharedCampoMinato();
    private CampoMinato campoMinato;
    private String difficolta;

    private SharedCampoMinato() {}

    public CampoMinato getCampoMinato() {
        return campoMinato;
    }

    public void setCampoMinato(CampoMinato campoMinato) {
        this.campoMinato = campoMinato;
    }

    public String getDifficolta() {
        return difficolta;
    }

    public void setDifficolta(String difficolta) {
        this.difficolta = difficolta;
    }
}
