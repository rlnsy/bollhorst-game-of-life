package src.game_world_elements;

import java.awt.*;
import res.AudioPlayer;

public class Storm extends NonPhysicsElement {
    public Storm() {
        super();
    }
    public void behave() {
        setLocation(new Point(getX()+1,getY()));
        int dropSpot = (int)(Math.random()*getWidth()) + getX() - (getWidth()/2);
        getWorld().addSecondaryElement(new Point(dropSpot,getY()+(getHeight()/2)),new Water());
    }
    
    @Override
    public void playSound() {
        AudioPlayer.playClip("storm.wav");
    }
    
    public void touchedElement(WorldElement other) {}
}
