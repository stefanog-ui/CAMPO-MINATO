import controllo.AzioneCambiaDifficolta;

import javax.swing.*;
import java.awt.*;

public class Main {
    private JFrame frame = new JFrame();
    private JPanel pannelloPrincipale = new JPanel();
    private JPanel pannelloCombo = new JPanel();

    private void inizializzaFrame() {
        frame.setSize(500,500);
        frame.setTitle("CAMPO MINATO");
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(pannelloPrincipale, BorderLayout.CENTER);
        frame.add(pannelloCombo, BorderLayout.NORTH);
        frame.setVisible(true);
    }

    private void inizializzaPannelloPrincipale() {
        creaCampo(4);
    }

    private void lanciaGioco() {
        inizializzaPannelloPrincipale();
        JComboBox comboBoxDifficolta = new JComboBox();
        comboBoxDifficolta.addItem("FACILE");
        comboBoxDifficolta.addItem("MEDIO");
        comboBoxDifficolta.addItem("DIFFICILE");
        comboBoxDifficolta.addActionListener(new AzioneCambiaDifficolta(pannelloPrincipale));
        pannelloCombo.add(comboBoxDifficolta, BorderLayout.CENTER);
        inizializzaFrame();
    }

    private void creaCampo(int dimensione) {
        pannelloPrincipale.removeAll();
        pannelloPrincipale.setLayout(new GridLayout(dimensione, dimensione));
        for (int i = 0; i < dimensione * dimensione; i++) {
            pannelloPrincipale.add(new JButton("Pulsante " + (i + 1)));
        }
        pannelloPrincipale.revalidate();
        pannelloPrincipale.repaint();
        System.out.println("Layout changed to: " + dimensione + "x" + dimensione);
    }

    public static void main(String[] args) {
            Main main = new Main();
            main.lanciaGioco();
    }
}