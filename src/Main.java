import controllo.AzioneCambiaDifficolta;
import controllo.AzioneCambiaMenu;
import utility.Utility;

import javax.swing.*;
import java.awt.*;

public class Main {
    private JFrame frame = new JFrame();
    private JPanel pannelloPrincipale = new JPanel();
    private JPanel pannelloIndietro = new JPanel();

    private void inizializzaFrame() {
        frame.setSize(500,500);
        frame.setTitle("CAMPO MINATO");
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(pannelloPrincipale, BorderLayout.CENTER);
        mostraMenuIniziale();
        frame.setVisible(true);
    }

    private void mostraMenuIniziale() {
        JComboBox<String> comboDifficolta = new JComboBox();
        comboDifficolta.addItem("FACILE");
        comboDifficolta.addItem("MEDIO");
        comboDifficolta.addItem("DIFFICILE");
        pannelloPrincipale.setLayout(new GridBagLayout());
        JButton btnGioca = new JButton("GIOCA");
        btnGioca.setPreferredSize(new Dimension(200, 50));
        btnGioca.addActionListener(new AzioneCambiaMenu(pannelloPrincipale, comboDifficolta ));
        pannelloPrincipale.add(btnGioca);
        pannelloPrincipale.revalidate();
        pannelloPrincipale.repaint();
    }

    private void inizializzaPannelloPrincipale() {
        Utility.creaCampo(9,pannelloPrincipale);
    }

    private void lanciaGioco() {
        /*inizializzaPannelloPrincipale();*/
        JButton pulsanteIndietro = new JButton("Indietro");
        pulsanteIndietro.addActionListener(new AzioneCambiaDifficolta(pannelloPrincipale));
        pannelloIndietro.add(pulsanteIndietro, BorderLayout.CENTER);
        inizializzaFrame();
    }

    public static void main(String[] args) {
            Main main = new Main();
            main.lanciaGioco();
    }
}

