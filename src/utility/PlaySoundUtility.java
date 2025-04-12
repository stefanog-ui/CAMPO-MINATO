package utility;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

public class PlaySoundUtility {

    public static void playBombSound() {
        try {
            URL soundURL = Utility.class.getResource("/resources/bomba.wav");
            if (soundURL == null) {
                System.err.println("Bomb sound file not found!");
                return;
            }

            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundURL);
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }


}