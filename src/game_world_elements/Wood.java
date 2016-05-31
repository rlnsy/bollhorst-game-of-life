package src.game_world_elements;

import res.AudioPlayer;

public class Wood extends BuildBlock {
    public Wood() {
        super();
        AudioPlayer.playClip("wood.wav");
    }
}