package modello;

public class SharedCampoMinato {
    public static SharedCampoMinato sharedCampo = new SharedCampoMinato();
    private CampoMinato campoMinato;

    private SharedCampoMinato() {}

    public CampoMinato getCampoMinato() {
        return campoMinato;
    }

    public void setCampoMinato(CampoMinato campoMinato) {
        this.campoMinato = campoMinato;
    }
}
