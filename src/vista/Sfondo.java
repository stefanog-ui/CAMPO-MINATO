package vista;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class Sfondo extends JPanel {
    private Image sfondo;

    public Sfondo(String imagePath) {
        try {
            sfondo = new ImageIcon(Objects.requireNonNull(getClass().getResource(imagePath))).getImage();
        } catch (Exception e) {
            System.err.println("Errore durante il caricamento dell'immagine di sfondo: " + e.getMessage());
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (sfondo != null) {
            g.drawImage(sfondo, 0, 0, getWidth(), getHeight(), this);
        }
    }
}

