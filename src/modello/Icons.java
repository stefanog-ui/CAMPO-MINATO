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
        ImageIcon immagineBomba = new ImageIcon(Objects.requireNonNull(Utility.class.getResource("/risorse/bomba.png")));
        ImageIcon immagineBandiera = new ImageIcon(Objects.requireNonNull(Utility.class.getResource("/risorse/bandiera.png")));
        Image imgBomba = immagineBomba.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        Image imgBandiera = immagineBandiera.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        iconaBomba = new ImageIcon(imgBomba);
        iconaBandiera = new ImageIcon(imgBandiera);
    }

}

