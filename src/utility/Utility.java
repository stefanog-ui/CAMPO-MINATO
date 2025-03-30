package utility;

import javax.swing.*;
import java.awt.*;

public class Utility {

    public static void creaCampo(int dimensione,JPanel pannelloPrincipale) {
        pannelloPrincipale.removeAll();
        pannelloPrincipale.setLayout(new GridLayout(dimensione, dimensione));
        for (int i = 0; i < dimensione * dimensione; i++) {
            pannelloPrincipale.add(new JButton("Pulsante " + (i + 1)));
        }
        pannelloPrincipale.revalidate();
        pannelloPrincipale.repaint();
        System.out.println("Layout changed to: " + dimensione + "x" + dimensione);
    }

    public static void cambiaDifficolta(String difficolta, JPanel pannelloPrincipale) {
        switch (difficolta) {
            case "FACILE": creaCampo(9, pannelloPrincipale);
                            break;
            case "MEDIO": creaCampo(14, pannelloPrincipale);
                            break;
            case "DIFFICILE": creaCampo(19, pannelloPrincipale);
                                break;
        }
    }
}
