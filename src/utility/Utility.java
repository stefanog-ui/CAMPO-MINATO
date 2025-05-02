package utility;

import controllo.AzioneMouseClickCella;
import modello.*;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Utility {

    public static void creaCampo(int dimensione, JPanel pannelloPrincipale) {
        Cella[][] matriceCelle = new Cella[dimensione][dimensione];
        CampoMinato campoM = new CampoMinato(matriceCelle);
        SharedCampoMinato.sharedCampo.setCampoMinato(campoM);
        SharedCampoMinato.sharedCampo.setPrimoTocco(true);

        pannelloPrincipale.removeAll();
        pannelloPrincipale.setLayout(new GridLayout(dimensione, dimensione));

        Random random = new Random();
        int celleTotali = dimensione * dimensione;
        int numMine = (int) (celleTotali * 0.15);

        for (int i = 0; i < numMine; i++) {
            int indiceRandom;
            do {
                indiceRandom = random.nextInt(celleTotali);
            } while (matriceCelle[indiceRandom / dimensione][indiceRandom % dimensione] != null);

            int righe = indiceRandom / dimensione;
            int colonne = indiceRandom % dimensione;
            matriceCelle[righe][colonne] = new Cella(indiceRandom, 0, true);
        }

        for (int i = 0; i < celleTotali; i++) {
            int righe = i / dimensione;
            int colonne = i % dimensione;

            if (matriceCelle[righe][colonne] == null) {
                int mineVicino = 0;
                for (int dr = -1; dr <= 1; dr++) {
                    for (int dc = -1; dc <= 1; dc++) {
                        int nuoveRighe = righe + dr;
                        int nuoveColonne = colonne + dc;
                        if (nuoveRighe >= 0 && nuoveRighe < dimensione && nuoveColonne >= 0 && nuoveColonne < dimensione) {
                            if (matriceCelle[nuoveRighe][nuoveColonne] != null && matriceCelle[nuoveRighe][nuoveColonne].isHasMina()) {
                                mineVicino++;
                            }
                        }
                    }
                }
                matriceCelle[righe][colonne] = new Cella(i, mineVicino, false);
            }

            JButton button = new JButton();
            Color bgColor = ((righe + colonne) % 2 == 0 ) ? ColoreCampo.lightGreen : ColoreCampo.darkerGreen;

            button.setOpaque(true);
            button.setContentAreaFilled(true);
            button.setBorderPainted(false);
            button.setBackground(bgColor);

            button.addMouseListener(new AzioneMouseClickCella(righe, colonne));
            pannelloPrincipale.add(button);
        }

        pannelloPrincipale.revalidate();
        pannelloPrincipale.repaint();
        System.out.println("Campo creato: " + dimensione + "x" + dimensione);
    }

    public static void rivelaBombe(JPanel pannelloPrincipale) {
        Cella[][] matriceCelle = SharedCampoMinato.sharedCampo.getCampoMinato().getCampo();
        for (int r = 0; r < matriceCelle.length; r++) {
            for (int c = 0; c < matriceCelle[r].length; c++) {
                Cella cella = matriceCelle[r][c];
                JButton button = (JButton) pannelloPrincipale.getComponent(r * matriceCelle.length + c);

                if (cella.isHasMina()) {
                    button.setIcon(Icons.sharedIcons.iconaBomba);
                    button.setEnabled(true);
                    button.removeMouseListener(new AzioneMouseClickCella(r, c));
                }
                else {
                    if (cella.getNumeroMine() > 0) {
                        button.setText(String.valueOf(cella.getNumeroMine()));
                    } else {
                        button.setText("");
                    }
                }
            }
        }
    }

    public static boolean checkCondizioniVittoria(Cella[][] matriceCelle) {
        for (int r = 0; r < matriceCelle.length; r++) {
            for (int c = 0; c < matriceCelle[r].length; c++) {
                Cella cella = matriceCelle[r][c];
                if (!cella.isHasMina() && !cella.isScoperta()) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void cambiaDifficolta(String difficolta, JPanel pannelloPrincipale) {
        SharedCampoMinato.sharedCampo.setDifficolta(difficolta);
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