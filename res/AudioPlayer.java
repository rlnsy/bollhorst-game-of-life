package res;

import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;
import javax.swing.*;

public class AudioPlayer {
    private final String SOUND_DIR = "sounds/";
    
    public static void playClip(String name) {
        new AudioPlayer().play(name);
    }
    
    private void play(String fileName) {
        try {
            URL path = getClass().getResource(SOUND_DIR + fileName);
            AudioInputStream stream = AudioSystem.getAudioInputStream(path);
            
            Clip clip = AudioSystem.getClip();
            
            clip.open(stream);
            clip.start();
        }
        catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {}
    }
}
