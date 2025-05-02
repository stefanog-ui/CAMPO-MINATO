import controllo.AzioneCambiaMenu;
import utility.Utility;
import vista.Sfondo;

import javax.swing.*;
import java.awt.*;

public class Main {
    private final JFrame frame = new JFrame();
    private final JPanel pannelloPrincipale = new Sfondo("/risorse/menu-bg.png");
    private final JPanel pannelloIndietro = new JPanel();
    private final JComboBox<String> comboDifficolta = new JComboBox<>();

    private void inizializzaFrame() {
        JButton pulsanteIndietro = new JButton("Indietro");
        pulsanteIndietro.addActionListener(new AzioneCambiaMenu(pannelloPrincipale,pannelloIndietro,comboDifficolta));
        pannelloIndietro.add(pulsanteIndietro, BorderLayout.CENTER);
        frame.setSize(500,500);
        frame.setTitle("CAMPO MINATO");
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(pannelloPrincipale, BorderLayout.CENTER);
        pannelloIndietro.add(pulsanteIndietro, BorderLayout.CENTER);
        frame.add(pannelloIndietro, BorderLayout.NORTH);
        pannelloIndietro.setVisible(false);
        mostraMenuIniziale();
        frame.setVisible(true);
    }

    private void mostraMenuIniziale() {
        comboDifficolta.addItem("FACILE");
        comboDifficolta.addItem("MEDIO");
        comboDifficolta.addItem("DIFFICILE");
        pannelloPrincipale.setLayout(new GridBagLayout());
        JButton btnGioca = new JButton("GIOCA");
        btnGioca.setPreferredSize(new Dimension(200, 50));
        btnGioca.addActionListener(new AzioneCambiaMenu(pannelloPrincipale, pannelloIndietro, comboDifficolta ));
        pannelloPrincipale.add(btnGioca);
        pannelloPrincipale.revalidate();
        pannelloPrincipale.repaint();
    }

    private void inizializzaPannelloPrincipale() {
        Utility.creaCampo(9, pannelloPrincipale);
    }

    private void lanciaGioco() {
        inizializzaFrame();
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.lanciaGioco();
    }
}