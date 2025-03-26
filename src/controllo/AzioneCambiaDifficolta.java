package controllo;

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
        pannelloPrincipale.removeAll();
        pannelloPrincipale.setLayout(new GridLayout(dimensione, dimensione));
        for (int i = 0; i < dimensione * dimensione; i++) {
            pannelloPrincipale.add(new JButton("Pulsante " + (i + 1)));
        }
        pannelloPrincipale.revalidate();
        pannelloPrincipale.repaint();
        System.out.println("Layout changed to: " + dimensione + "x" + dimensione);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JComboBox<String> comboBox = (JComboBox<String>) e.getSource();
        String selected = (String) comboBox.getSelectedItem();
        switch (selected) {
            case "FACILE": cambiaLayoutPannello(4);
                            break;
            case "MEDIO": cambiaLayoutPannello(6);
                            break;
            case "DIFFICILE": cambiaLayoutPannello(8);
                                break;
        }

    }
}
