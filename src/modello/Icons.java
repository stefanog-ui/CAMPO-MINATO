package modello;

import utility.Utility;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class Icons {
    public static Icons sharedIcons = new Icons();
    public ImageIcon iconaBomba;
    public ImageIcon iconaBandiera;

    private Icons() {
        ImageIcon bombIcon = new ImageIcon(Objects.requireNonNull(Utility.class.getResource("/resources/bomb.png")));
        ImageIcon flagIcon = new ImageIcon(Objects.requireNonNull(Utility.class.getResource("/resources/flag.png")));
        Image scaledImg = bombIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        Image scaledImgFlag = flagIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        iconaBomba = new ImageIcon(scaledImg);
        iconaBandiera = new ImageIcon(scaledImgFlag);
    }

}