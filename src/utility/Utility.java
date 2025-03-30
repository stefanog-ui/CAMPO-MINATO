package utility;

import modello.CampoMinato;
import modello.Cella;
import modello.SharedCampoMinato;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Utility {

    public static void creaCampo(int dimensione, JPanel pannelloPrincipale) {
        Cella[][] matriceCelle = new Cella[dimensione][dimensione];
        CampoMinato campoM = new CampoMinato(matriceCelle);
        SharedCampoMinato.sharedCampo.setCampoMinato(campoM);
        pannelloPrincipale.removeAll();
        pannelloPrincipale.setLayout(new GridLayout(dimensione, dimensione));

        Random random = new Random();
        int totCelle = dimensione * dimensione;
        int totMine = (int) (totCelle * 0.15);

        for (int i = 0; i < totMine; i++) {
            int indiceRandom;
            do {
                indiceRandom = random.nextInt(totCelle);
            } while (matriceCelle[indiceRandom / dimensione][indiceRandom % dimensione] != null);
            int riga = indiceRandom / dimensione;
            int colonna = indiceRandom % dimensione;
            matriceCelle[riga][colonna] = new Cella(indiceRandom, 0, true);
        }

        for (int i = 0; i < totCelle; i++) {
            int riga = i / dimensione;
            int colonna = i % dimensione;

            if (matriceCelle[riga][colonna] == null) {
                matriceCelle[riga][colonna] = new Cella(i, 0, false);
            }

            JButton button = new JButton("Pulsante " + (i + 1));
            pannelloPrincipale.add(button);
        }

        pannelloPrincipale.revalidate();
        pannelloPrincipale.repaint();
        System.out.println("Layout cambiato: " + dimensione + "x" + dimensione);
    }

    public static void cambiaDifficolta(String difficolta, JPanel pannelloPrincipale) {
        switch (difficolta) {
            case "FACILE": creaCampo(9, pannelloPrincipale);
                            break;
            case "MEDIO": creaCampo(14, pannelloPrincipale);
                            break;
            case "DIFFICILE": creaCampo(19, pannelloPrincipale);
                                break;
        }
    }
}
