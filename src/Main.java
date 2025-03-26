import javax.swing.*;
import java.awt.*;

public class Main {
    private JFrame frame = new JFrame();
    private JPanel pannelloPrincipale = new JPanel();

    private void inizializzaFrame() {
        frame.setSize(1000,1000);
        frame.setTitle("CAMPO MINATO");
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(pannelloPrincipale);
        frame.setVisible(true);
    }

    private void inizializzaPannelloPrincipale() {
        pannelloPrincipale.setLayout(new GridLayout(21, 20));
    }


    private void lanciaGioco() {
        inizializzaPannelloPrincipale();
        JComboBox comboBoxDifficolta = new JComboBox();
        comboBoxDifficolta.addItem("FACILE");
        comboBoxDifficolta.addItem("MEDIO");
        comboBoxDifficolta.addItem("DIFFICILE");
        pannelloPrincipale.add(comboBoxDifficolta);
        inizializzaFrame();
    }


    public static void main(String[] args) {
            Main main = new Main();
            main.lanciaGioco();
    }
}