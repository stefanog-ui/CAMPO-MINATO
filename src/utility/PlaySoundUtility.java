package utility;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

public class PlaySoundUtility {

    public static void suonoBomba() {
        try {
            URL suonoURL = Utility.class.getResource("/risorse/bomba.wav");
            if (suonoURL == null) {
                System.err.println("Il suono della bomba non è stato trovato");
                return;
            }

            AudioInputStream audio = AudioSystem.getAudioInputStream(suonoURL);
            Clip clip = AudioSystem.getClip();
            clip.open(audio);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

}