package modello;

import utility.Utility;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class Icons {
    public static Icons sharedIcons = new Icons();
    public ImageIcon iconaBomba;

    private Icons() {
        ImageIcon bombIcon = new ImageIcon(Objects.requireNonNull(Utility.class.getResource("/resources/bomb.png")));
        Image scaledImg = bombIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        iconaBomba = new ImageIcon(scaledImg);
    }

}
