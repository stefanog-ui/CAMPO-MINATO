package vista;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class BackgroundPanel extends JPanel {
    private Image backgroundImage;

    public BackgroundPanel(String imagePath) {
        try {
            backgroundImage = new ImageIcon(Objects.requireNonNull(getClass().getResource(imagePath))).getImage();
        } catch (Exception e) {
            System.err.println("Error loading background image: " + e.getMessage());
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            // Draw the image to fill the panel
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}

