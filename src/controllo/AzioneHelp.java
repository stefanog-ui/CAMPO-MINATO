package controllo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AzioneHelp implements ActionListener {
    JPanel pannelloPrincipale;

    public AzioneHelp(JPanel pannelloPrincipale) {
        this.pannelloPrincipale = pannelloPrincipale;
    }

    private void menuHelp() {
        JOptionPane.showMessageDialog(
                pannelloPrincipale,
                "🔹 REGOLAMENTO DEL CAMPO MINATO 🔹\n\n" +
                        "📌 Obiettivo del gioco:\n" +
                        "- Scoprire tutte le caselle senza mine per vincere.\n" +
                        "- Selezionare una casella contenente una mina fa perdere la partita.\n\n" +
                        "📌 Come giocare:\n" +
                        "- Clicca su una casella per rivelarla.\n" +
                        "- Se la casella è vuota, verranno rivelate anche le caselle adiacenti.\n" +
                        "- Se la casella contiene un numero, indica quante mine sono vicine.\n" +
                        "- Usa la logica per dedurre dove si trovano le mine e segnale con un flag.\n\n" +
                        "📌 Suggerimenti:\n" +
                        "- Le caselle con il numero 1 sono adiacenti ad una sola mina.\n" +
                        "- Se una casella è circondata da numeri, analizza i loro valori per capire dove sono le mine.\n" +
                        "- Se sei incerto su una mossa, usa il tasto destro per mettere un segnalino e ragionarci sopra.\n\n" +
                        "🔸 Buona fortuna! Evita le mine e completa il campo! 🔸",
                "Guida al Gioco",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        menuHelp();
    }
}

