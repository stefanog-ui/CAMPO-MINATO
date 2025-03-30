package controllo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AzioneCambiaMenu implements ActionListener {
    private JPanel pannelloPrincipale;
    private JComboBox comboDifficolta;

    public AzioneCambiaMenu(JPanel pannelloPrincipale, JComboBox comboDifficolta) {
        this.pannelloPrincipale = pannelloPrincipale;
        this.comboDifficolta = comboDifficolta;
    }

    private void cambiaMenu() {
        pannelloPrincipale.removeAll();
        pannelloPrincipale.setLayout(new BoxLayout(pannelloPrincipale, BoxLayout.Y_AXIS));
        JButton btnDifficolta = new JButton("LIVELLO DI DIFFICOLTÀ");
        JButton btnHelp = new JButton("HELP");

        Dimension dimensionePulsanti = new Dimension(200, 200);

        btnDifficolta.setMaximumSize(dimensionePulsanti);
        btnHelp.setMaximumSize(dimensionePulsanti);

        btnDifficolta.addActionListener(new MostraComboDifficolta(pannelloPrincipale, comboDifficolta));
        btnHelp.addActionListener(new AzioneHelp(pannelloPrincipale));

        pannelloPrincipale.add(btnDifficolta);
        pannelloPrincipale.add(btnHelp);

        btnDifficolta.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnHelp.setAlignmentX(Component.CENTER_ALIGNMENT);

        pannelloPrincipale.add(Box.createVerticalGlue());
        pannelloPrincipale.add(btnDifficolta);
        pannelloPrincipale.add(Box.createVerticalStrut(20));
        pannelloPrincipale.add(btnHelp);
        pannelloPrincipale.add(Box.createVerticalGlue());

        pannelloPrincipale.revalidate();
        pannelloPrincipale.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        cambiaMenu();
    }
}













