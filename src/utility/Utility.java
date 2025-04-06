package utility;

import controllo.AzioneClickCella;
import modello.CampoMinato;
import modello.Cella;
import modello.Icons;
import modello.SharedCampoMinato;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;
import java.util.Random;

public class Utility {

    public static void creaCampo(int dimensione, JPanel pannelloPrincipale) {
        Cella[][] matriceCelle = new Cella[dimensione][dimensione];
        CampoMinato campoM = new CampoMinato(matriceCelle);
        SharedCampoMinato.sharedCampo.setCampoMinato(campoM);

        pannelloPrincipale.removeAll();
        pannelloPrincipale.setLayout(new GridLayout(dimensione, dimensione));

        Random random = new Random();
        int totalCells = dimensione * dimensione;
        int mineCount = (int) (totalCells * 0.15);

        for (int i = 0; i < mineCount; i++) {
            int randomIndex;
            do {
                randomIndex = random.nextInt(totalCells);
            } while (matriceCelle[randomIndex / dimensione][randomIndex % dimensione] != null);

            int row = randomIndex / dimensione;
            int col = randomIndex % dimensione;
            matriceCelle[row][col] = new Cella(randomIndex, 0, true);
        }

        for (int i = 0; i < totalCells; i++) {
            int row = i / dimensione;
            int col = i % dimensione;

            if (matriceCelle[row][col] == null) {
                int mineVicino = 0;
                for (int dr = -1; dr <= 1; dr++) {
                    for (int dc = -1; dc <= 1; dc++) {
                        int newRow = row + dr;
                        int newCol = col + dc;
                        if (newRow >= 0 && newRow < dimensione && newCol >= 0 && newCol < dimensione) {
                            if (matriceCelle[newRow][newCol] != null && matriceCelle[newRow][newCol].isHasMina()) {
                                mineVicino++;
                            }
                        }
                    }
                }
                matriceCelle[row][col] = new Cella(i, mineVicino, false);
            }

            JButton button = new JButton();
            button.setOpaque(true);

            button.addActionListener(new AzioneClickCella(row, col));
            pannelloPrincipale.add(button);
        }

        pannelloPrincipale.revalidate();
        pannelloPrincipale.repaint();
        System.out.println("Campo creato: " + dimensione + "x" + dimensione);
    }

    public static void revealAllBombs(JPanel pannelloPrincipale) {
        Cella[][] matriceCelle = SharedCampoMinato.sharedCampo.getCampoMinato().getCampo();
        for (int r = 0; r < matriceCelle.length; r++) {
            for (int c = 0; c < matriceCelle[r].length; c++) {
                Cella cella = matriceCelle[r][c];
                JButton button = (JButton) pannelloPrincipale.getComponent(r * matriceCelle.length + c);

                if (cella.isHasMina()) {
                    button.setIcon(Icons.sharedIcons.iconaBomba);
                }
                else {
                    if (cella.getNumeroMine() > 0) {
                        button.setText(String.valueOf(cella.getNumeroMine()));
                    } else {
                        button.setText("");
                    }
                }
                button.setEnabled(false);
            }
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
