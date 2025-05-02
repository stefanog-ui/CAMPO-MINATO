package vista;

import modello.Icons;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.*;

public class FinePartitaPanel extends JPanel {
    private JLabel tempoLabel;
    private JLabel bestTempoLabel;
    private JButton riprovaButton;

    private int bestTempo = -1;
    private static final String BEST_FILE = "miglior_tempo.dat";

    public enum StatoPartita {
        VITTORIA,
        SCONFITTA,
        SCONFITTA_DOPO_VITTORIA
    }

    public FinePartitaPanel(StatoPartita stato, int tempoAttuale, ActionListener riprovaAzione) {
        setLayout(new BorderLayout());
        setOpaque(false);

        caricaTempoMigliore();

        JPanel tempoPanel = new JPanel(new GridLayout(1, 2));
        tempoPanel.setOpaque(false);

        tempoLabel = new JLabel("", SwingConstants.CENTER);
        tempoLabel.setVerticalTextPosition(SwingConstants.BOTTOM);
        tempoLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        tempoLabel.setIcon(Icons.orologio);

        bestTempoLabel = new JLabel("", SwingConstants.CENTER);
        bestTempoLabel.setVerticalTextPosition(SwingConstants.BOTTOM);
        bestTempoLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        bestTempoLabel.setIcon(Icons.coppa);

        tempoPanel.add(tempoLabel);
        tempoPanel.add(bestTempoLabel);
        add(tempoPanel, BorderLayout.CENTER);

        // Bottone riprova
        riprovaButton = new JButton(" Riprova", Icons.riprova);
        riprovaButton.setFont(new Font("Arial", Font.BOLD, 16));
        riprovaButton.setBackground(new Color(60, 179, 113));
        riprovaButton.setForeground(Color.WHITE);
        riprovaButton.addActionListener(riprovaAzione);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.add(riprovaButton);

        add(buttonPanel, BorderLayout.SOUTH);

        aggiorna(stato, tempoAttuale);

        buttonPanel.setVisible(true);

    }

    public void aggiorna(StatoPartita stato, int tempoAttuale) {
        switch (stato) {
            case VITTORIA:
                tempoLabel.setText(String.format("%03d", tempoAttuale));
                if (bestTempo == -1 || tempoAttuale < bestTempo) {
                    bestTempo = tempoAttuale;
                    salvaTempoMigliore();
                }
                bestTempoLabel.setText(String.format("%03d", bestTempo));
                break;
            case SCONFITTA:
                tempoLabel.setText("---");
                bestTempoLabel.setText("---");
                break;
            case SCONFITTA_DOPO_VITTORIA:
                tempoLabel.setText("---");
                bestTempoLabel.setText(String.format("%03d", bestTempo));
                break;
        }
    }

    private void salvaTempoMigliore() {
        try (FileWriter fw = new FileWriter(BEST_FILE)) {
            fw.write(String.valueOf(bestTempo));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void caricaTempoMigliore() {
        File file = new File(BEST_FILE);
        if (file.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                bestTempo = Integer.parseInt(br.readLine());
            } catch (IOException | NumberFormatException e) {
                bestTempo = -1;
            }
        }
    }

    public void resetTempoMigliore() {
        bestTempo = -1;
        File f = new File(BEST_FILE);
        if (f.exists()) f.delete();
    }
}

