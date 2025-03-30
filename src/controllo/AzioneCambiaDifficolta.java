package controllo;

import utility.Utility;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AzioneCambiaDifficolta implements ActionListener {
    private JPanel pannelloPrincipale;

    public AzioneCambiaDifficolta(JPanel pannelloPrincipale) {
        this.pannelloPrincipale = pannelloPrincipale;
    }

    private void cambiaLayoutPannello(int dimensione) {
        Utility.creaCampo(dimensione,pannelloPrincipale);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JComboBox<String> comboBox = (JComboBox<String>) e.getSource();
        String selected = (String) comboBox.getSelectedItem();
        Utility.cambiaDifficolta(selected,pannelloPrincipale);
    }
}
