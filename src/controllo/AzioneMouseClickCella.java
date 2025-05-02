package controllo;

import modello.Cella;
import modello.ColoreCampo;
import modello.Icons;
import modello.SharedCampoMinato;
import utility.PlaySoundUtility;
import utility.Utility;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AzioneMouseClickCella extends MouseAdapter {
    private int r;
    private int c;

    public AzioneMouseClickCella(int r, int c) {
        this.r = r;
        this.c = c;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        JButton button = (JButton) e.getSource();
        JPanel pannelloPrincipale = (JPanel) button.getParent();
        Cella[][] matriceCelle = SharedCampoMinato.sharedCampo.getCampoMinato().getCampo();

        Color controlloColore = (r + c) % 2 == 0 ? ColoreCampo.lightBrown : ColoreCampo.darkerBrown;
        Cella cellaCliccata = matriceCelle[r][c];

        if (cellaCliccata.isScoperta()) return;

        if (SwingUtilities.isRightMouseButton(e)) {
            if (!cellaCliccata.isScoperta()) {
                Color coloreSfondo = ((r + c) % 2 == 0 ) ? ColoreCampo.lightGreen : ColoreCampo.darkerGreen;

                button.setOpaque(true);
                button.setContentAreaFilled(true);
                button.setBorderPainted(false);
                button.setBackground(coloreSfondo);

                Icon iconaCorrente = button.getIcon();
                if (iconaCorrente == null) {
                    cellaCliccata.setHasBandiera(true);
                    button.setIcon(Icons.sharedIcons.iconaBandiera);
                } else {
                    cellaCliccata.setHasBandiera(false);
                    button.setIcon(null);
                }
            }
            return;
        }

        if (cellaCliccata.isHasBandiera()) return;

        button.setIcon(null);

        if (cellaCliccata.isHasMina() ) {
            if (SharedCampoMinato.sharedCampo.isPrimoTocco()) {
                cellaCliccata.setHasMina(false);
                scopriCella(r, c, matriceCelle, pannelloPrincipale);
                SharedCampoMinato.sharedCampo.setPrimoTocco(false);
                effettoCellaCliccata(button, controlloColore);
                return;
            }
            PlaySoundUtility.suonoBomba();
            button.setIcon(Icons.sharedIcons.iconaBomba);
            button.setEnabled(true);
            Utility.rivelaBombe(pannelloPrincipale);
        } else {
            rivelaArea(r, c, matriceCelle, pannelloPrincipale);
        }

        SharedCampoMinato.sharedCampo.setPrimoTocco(false);

        if (!cellaCliccata.isHasMina()) {
            if (Utility.checkCondizioniVittoria(matriceCelle)) {
                messaggioVittoria(pannelloPrincipale);
            }
        }

        effettoCellaCliccata(button, controlloColore);

        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(pannelloPrincipale);
        messaggioSconfitta(cellaCliccata.isHasMina(), pannelloPrincipale, frame);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        super.mouseEntered(e);
        JButton button = (JButton) e.getSource();
        if (button.isEnabled()) {
            button.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        super.mouseExited(e);
        JButton button = (JButton) e.getSource();
        button.setBorder(BorderFactory.createEmptyBorder());
    }

    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
        JButton button = (JButton) e.getSource();
        if (button.isEnabled() && button.getText().isBlank()) {
            button.setBackground(Color.LIGHT_GRAY);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        super.mouseReleased(e);
    }

    private void effettoCellaCliccata(JButton button, Color coloreCella) {
        button.setEnabled(!button.getText().isBlank());
        button.setBackground(coloreCella);
        button.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
    }

    private void rivelaArea(int r, int c, Cella[][] matriceCelle, JPanel pannelloPrincipale) {
        if (notValid(r, c, matriceCelle)) return;

        Cella cella = matriceCelle[r][c];
        if (cella.isScoperta()) return;

        scopriCella(r, c, matriceCelle, pannelloPrincipale);

        if (cella.isHasMina()) return;

        int numeroMine = cella.getNumeroMine();
        JButton button = getButton(r, c, pannelloPrincipale, matriceCelle.length);

        if (numeroMine > 0) {
            System.out.println("numeroMine " + numeroMine);
            Color coloreNumero = ColoreCampo.getColoreNumero(numeroMine);
            button.setForeground(coloreNumero);

            Font fontCorrente = button.getFont();
            Font nuovoFont = fontCorrente.deriveFont(Font.BOLD, fontCorrente.getSize() + 2f);
            button.setFont(nuovoFont);

            button.setText(String.valueOf(numeroMine));
            button.setEnabled(true);
        } else {
            button.setText("");
            for (int dr = -1; dr <= 1; dr++) {
                for (int dc = -1; dc <= 1; dc++) {
                    if (dr != 0 || dc != 0) {
                        rivelaArea(r + dr, c + dc, matriceCelle, pannelloPrincipale);
                    }
                }
            }
        }
    }

    private void scopriCella(int r, int c, Cella[][] matriceCelle, JPanel pannelloPrincipale) {
        if (notValid(r, c, matriceCelle)) return;

        Cella cella = matriceCelle[r][c];
        if (cella.isScoperta()) return;

        cella.setScoperta(true);
        JButton button = getButton(r, c, pannelloPrincipale, matriceCelle.length);
        button.setEnabled(false);
        button.setText("");
        Color controlloColore = (r + c) % 2 == 0 ? ColoreCampo.lightBrown : ColoreCampo.darkerBrown;// Default empty text
        effettoCellaCliccata(button, controlloColore);
    }

    private boolean notValid(int r, int c, Cella[][] matriceCelle) {
        int dimensione = matriceCelle.length;
        return r < 0 || r >= dimensione || c < 0 || c >= dimensione;
    }

    private JButton getButton(int r, int c, JPanel pannello, int dim) {
        return (JButton) pannello.getComponent(r * dim + c);
    }


    private void messaggioVittoria(JPanel pannelloPrincipale) {
        SwingUtilities.invokeLater(() -> {
            int risultato = JOptionPane.showConfirmDialog(null, "🎉 Hai vinto! Vuoi giocare ancora?", "Hai vinto!", JOptionPane.YES_NO_OPTION);
            if (risultato == JOptionPane.YES_OPTION) {
                Utility.cambiaDifficolta(SharedCampoMinato.sharedCampo.getDifficolta(), pannelloPrincipale);
            }
        });
    }

    public void messaggioSconfitta(boolean hasMine, JPanel pannelloPrincipale, JFrame frame) {
        if (!hasMine) return;

        new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            SwingUtilities.invokeLater(() -> {
                int result = JOptionPane.showConfirmDialog(null, "💥 BOOM! Hai cliccato su una mina. Vuoi riprovare?", "Game Over", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    Utility.cambiaDifficolta(SharedCampoMinato.sharedCampo.getDifficolta(), pannelloPrincipale);
                } else {
                    frame.dispose();
                }
            });
        }).start();
    }
}