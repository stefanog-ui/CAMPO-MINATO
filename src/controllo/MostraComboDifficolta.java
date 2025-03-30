package controllo;

import utility.Utility;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MostraComboDifficolta implements ActionListener {
    private JPanel pannelloPrincipale;
    private JPanel pannelloIndietro;
    private JComboBox<String> comboDifficolta;

    public MostraComboDifficolta(JPanel pannelloPrincipale, JPanel pannelloIndietro, JComboBox<String> comboDifficolta) {
        this.pannelloPrincipale = pannelloPrincipale;
        this.pannelloIndietro = pannelloIndietro;
        this.comboDifficolta = comboDifficolta;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int result = JOptionPane.showConfirmDialog(
                pannelloPrincipale,
                comboDifficolta,
                "Scegli Difficoltà",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );

        if (result == JOptionPane.OK_OPTION) {
            String selectedDifficulty = (String) comboDifficolta.getSelectedItem();
            Utility.cambiaDifficolta(selectedDifficulty,pannelloPrincipale);
            pannelloIndietro.setVisible(true);
        }
    }
}