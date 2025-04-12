package controllo;

import modello.Cella;
import modello.Icons;
import modello.SharedCampoMinato;
import utility.Utility;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AzioneClickCella implements ActionListener {
    private int r;
    private int c;

    public AzioneClickCella(int r, int c) {
        this.r = r;
        this.c = c;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        JPanel pannelloPrincipale = (JPanel) button.getParent();
        Cella[][] matriceCelle = SharedCampoMinato.sharedCampo.getCampoMinato().getCampo();

        Cella clickedCell = matriceCelle[r][c];

        if (clickedCell.isScoperta()) return;

        if (clickedCell.isHasMina()) {
            button.setIcon(Icons.sharedIcons.iconaBomba);
            Utility.revealAllBombs(pannelloPrincipale);
        } else {
            revealEmptyArea(r, c, matriceCelle, pannelloPrincipale);
        }

        if (!clickedCell.isHasMina()) {
            if (Utility.checkWinCondition(matriceCelle)) {
                showWinDialog(pannelloPrincipale);
            }
        }


        delayPopupGameOver(clickedCell.isHasMina(), pannelloPrincipale);
    }

    private void revealEmptyArea(int r, int c, Cella[][] matriceCelle, JPanel pannelloPrincipale) {
        int dimensione = matriceCelle.length;

        if (r < 0 || r >= dimensione || c < 0 || c >= dimensione) return;

        Cella cella = matriceCelle[r][c];
        if (cella.isScoperta()) return;

        cella.setScoperta(true);
        JButton button = (JButton) pannelloPrincipale.getComponent(r * dimensione + c);
        button.setEnabled(false);

        if (cella.isHasMina()) return;

        int numeroMine = cella.getNumeroMine();
        if (numeroMine > 0) {
            button.setText(String.valueOf(numeroMine));
            return;
        } else {
            button.setText("");
            // Continue revealing adjacent cells
            for (int dr = -1; dr <= 1; dr++) {
                for (int dc = -1; dc <= 1; dc++) {
                    if (dr != 0 || dc != 0) {
                        revealEmptyArea(r + dr, c + dc, matriceCelle, pannelloPrincipale);
                    }
                }
            }
        }
    }

    private void showWinDialog(JPanel pannelloPrincipale) {
        SwingUtilities.invokeLater(() -> {
            int result = JOptionPane.showConfirmDialog(null, "🎉 Hai vinto! Vuoi giocare ancora?", "Hai vinto!", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {
                Utility.cambiaDifficolta("FACILE", pannelloPrincipale); // Or track selected difficulty
            }
        });
    }




    void delayPopupGameOver(boolean hasMine, JPanel pannelloPrincipale) {
        if (!hasMine) return;

        new Thread(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            SwingUtilities.invokeLater(() -> {
                int result = JOptionPane.showConfirmDialog(null, "💥 BOOM! Hai cliccato su una mina. Vuoi riprovare?", "Game Over", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    Utility.cambiaDifficolta("FACILE", pannelloPrincipale);
                }
            });
        }).start();
    }


}