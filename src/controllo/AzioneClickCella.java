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
        Cella[][] matriceCelle = SharedCampoMinato.sharedCampo.getCampoMinato().getCampo();
        Cella clickedCell = matriceCelle[r][c];
        clickedCell.setScoperta(true);

        if (clickedCell.isHasMina()) {
            button.setIcon(Icons.sharedIcons.iconaBomba);
        } else if (clickedCell.getNumeroMine() > 0) {
            button.setText(String.valueOf(clickedCell.getNumeroMine()));
        } else {
            button.setText("");
        }

        button.setEnabled(false);
        delayPopupGameOver(clickedCell.isHasMina(), (JPanel) button.getParent());
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