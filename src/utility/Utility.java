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

        for (int i=0; i<dimensione; i++) {
            for (int j=0; j<dimensione; j++) {

                if (! matriceCelle[i][j].isHasMina()) {

                    int cont = 0;

                    for (int k=-1; k<2; k++) { // riga sopra, riga centrale, riga sotto
                        for (int w=-1; w<2; w++) { // colonna sinistra, colonna centrale, colonna destra

                            if (i+k >= 0 && i+k < dimensione) {  // se c'è una riga sopra o una riga sotto
                                if (j+w >= 0 && j+w < dimensione) {  // se c'è la colonna a sinistra o a destra
                                    //if (k != 0 || w != 0) {  // escludere la cella centrale

                                        if (matriceCelle[i + k][j + w].isHasMina()) {  // se c'è la mina
                                            cont++; // aumento il contatore
                                        }

                                    }
                            }

                        }
                    }

                    matriceCelle[i][j].getNumeroMine();  // setto il contato delle mine vicine alla cella corrente

                }


            }
        }

        for (int i=0; i<dimensione; i++) {
            for (int j=0; j<dimensione; j++) {
                if (matriceCelle[i][j].isHasMina()) {
                    System.out.print(" X |");
                }
                else {
                    System.out.print(" ");
                    System.out.print(matriceCelle[i][j].getNumeroMine());
                    System.out.print(" |");
                }
            }
            System.out.println("");
        }
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
