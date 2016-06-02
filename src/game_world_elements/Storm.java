package src.game_world_elements;

import java.awt.*;
import res.AudioPlayer;

public class Storm extends NonPhysicsElement {
    
    /*
     * pre: none
     * post: constructs a new Storm
     */
    public Storm() {
        super();
    }
    
    /*
     * pre: none
     * post: moves Storm to the right and produces Waters at random spots
     * aong the bottom
     */
    public void behave() {
        setLocation(new Point(getX()+1,getY()));
        int dropSpot = (int)(Math.random()*getWidth()) + getX() - (getWidth()/2);
        getWorld().addSecondaryElement(new Point(dropSpot,getY()+(getHeight()/2)),new Water());
    }
    
    /*
     * pre: storm audio file present in resource directory
     * post: plays storm sound
     */
    @Override
    public void playSound() {
        AudioPlayer.playClip("storm.wav");
    }
    
    public void touchedElement(WorldElement other) {}
}
