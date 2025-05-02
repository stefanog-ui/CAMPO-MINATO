package modello;

import java.awt.*;

public class FieldsColors {
    public static Color lightGreen = new Color(101, 209, 101);
    public static Color darkerGreen = new Color(25, 140, 25);
    public static Color lightBrown = new Color(255, 180, 180, 223);
    public static Color darkerBrown = new Color(255, 180, 180, 138);

    public static Color getColoreNumero (int numeroMine) {
        return switch (numeroMine) {
            case 1 -> Color.BLUE;
            case 2 -> Color.GREEN;
            case 3 -> Color.RED;
            case 4 -> Color.MAGENTA;
            case 5 -> Color.YELLOW;
            default -> Color.BLACK;
        };
    }


}
