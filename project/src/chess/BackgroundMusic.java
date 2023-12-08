package chess;

import javax.sound.sampled.*;
import java.io.IOException;
import java.io.InputStream;

public class BackgroundMusic {
    private Clip clip;

    public BackgroundMusic(String musicFilePath) {
        try {
            // Load the audio files using getResourceAsStream()
            InputStream audioFile1 = BackgroundMusic.class.getResourceAsStream("/chess/BackgroundMusic.wav");
            InputStream audioFile2 = BackgroundMusic.class.getResourceAsStream("/chess/CelebrationMusic.wav");

            if (audioFile1 != null && audioFile2 != null) {
                // Use audioFile1 or audioFile2 as needed for different functionalities
                // For instance, you might use audioFile1 to initialize clip here
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(audioFile1);
                clip = AudioSystem.getClip();
                clip.open(audioInputStream);
            } else {
                System.out.println("File not found: " + musicFilePath);
            }
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
