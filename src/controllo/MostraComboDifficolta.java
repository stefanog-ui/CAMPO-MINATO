package controllo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MostraComboDifficolta implements ActionListener {
    private JPanel pannelloPrincipale;
    private JComboBox<String> comboDifficolta;

    public MostraComboDifficolta(JPanel pannelloPrincipale, JComboBox<String> comboDifficolta) {
        this.pannelloPrincipale = pannelloPrincipale;
        this.comboDifficolta = comboDifficolta;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(pannelloPrincipale, comboDifficolta, "Scegli Difficoltà", JOptionPane.QUESTION_MESSAGE);
    }
}
