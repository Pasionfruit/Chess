package chess;

import javax.sound.sampled.*;
import java.io.IOException;

public class BackgroundMusic {
    private Clip clip;

    public BackgroundMusic(String musicFilePath) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(
                    BackgroundMusic.class.getResourceAsStream(musicFilePath));
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void play() {
        if (clip != null) {
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }

    public void stop() {
        if (clip != null) {
            clip.stop();
        }
    }
}
