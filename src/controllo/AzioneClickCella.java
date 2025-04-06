package controllo;

import modello.Cella;
import modello.Icons;
import modello.SharedCampoMinato;
import utility.Utility;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

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
    }
}
