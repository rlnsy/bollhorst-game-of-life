package src.game_world_elements;

import java.awt.event.*;
import java.awt.*;
import javax.swing.Action;
import javax.swing.AbstractAction;
import res.ImageReader;
import res.AudioPlayer;


public class Bollhorst extends PhysicsElement {
    private Image speechBox;
    private final int DETECT_RADIUS = 100;
    private final int SPEEDY_SPEED = 3;
    private int physicsCount = 0;
    
    /*
     * pre: none
     * post: constructs a new Bollhorst
     */
    public Bollhorst() {
        super();
        setMovable(true);
        speechBox = ImageReader.readImage(ImageReader.getEffectLocation() + "bollhorst_speech.png");
    }
    
    /*
     * pre: none
     * post: draws the bollhorst and his message (if near a Villager)
     */
    public void draw(Graphics g) {
        super.draw(g);
        if(targetDetected())
            drawMessage(g);
    }
    
    /*
     * pre: none
     * post: behaves physically
     */
    public void behave() {
        super.behave();
    }
    
    /*
     * pre: message image is correctly read
     * post: draws the message image at the corner of the bollhorst
     */
    private void drawMessage(Graphics g) {
        g.drawImage(speechBox,getX()-getWidth()/2-speechBox.getWidth(null),getY() - getHeight()/2,getWorld());
    }
    
    /*
     * pre: none
     * post: returns a large rectangle surrounding the bollhorst
     */
    private Rectangle getDetectionBox () {
        return new Rectangle(getX()-getWidth()/2-DETECT_RADIUS,getY()-getHeight()/2,(2*DETECT_RADIUS)+getWidth(),getHeight());
    }
    
    /*
     * pre: none
     * post: returns true if a Villager is present in the detection box
     */
    public boolean targetDetected() {
        for(WorldElement e : getWorld().getElements()) {
            if(getDetectionBox().contains(e.getLocation()) && (e instanceof Villager))
                return true;    
        }
        return false;
    }
    
    @Override
    public void touchedElement(WorldElement other) {
        if(other instanceof Dorito)
            eat(other);
    }
    
    /*
     * pre: other is in world
     * post: removes other from the world
     */
    public void eat(WorldElement other) {
        other.removeFromWorld();
    }
    
    /*
     * pre: none
     * post: decreases velocities incrementally
     */
    @Override
    public void accelerate() {
        if(getXVelocity() > 0 && physicsCount%4 == 0)
            changeXVelocity(-1);
        else if(getXVelocity() < 0 && physicsCount%4 == 0)
            changeXVelocity(1);

        if(getYVelocity() > 0 && physicsCount%2 == 0)
            changeYVelocity(-1);
        else if(getYVelocity() < 0 && physicsCount%2 == 0)
            changeYVelocity(1);
    }
    
    /*
     * pre: none
     * post: applies physics and modifies physics count for acceleration purposes
     */
    public void applyPhysics() {
        super.applyPhysics();
        physicsCount++;
    }
    
    /*
     * pre: bollhorst in not surrounded by elements
     * post: adds fireballs with initial velocities at various points around bollhorst
     */
    public void spitFire() {
        AudioPlayer.playClip("fire.wav");
        Point[] spots = new Point[6];
        int padding = new FireBall().getWidth();
        FireBall[] fires = new FireBall[6];
        for(int i = 0; i < fires.length; i++)
            fires[i] = new FireBall();
        
        spots[0] = new Point(getX() + getWidth()/2 + padding,getY() - getHeight()/2 - padding);
        spots[1] = new Point(getX() + getWidth()/2 + padding, getY());
        spots[2] = new Point(getX() + getWidth()/2 + padding, getY() + getHeight()/2 + padding);
        spots[3] = new Point(getX() - getWidth()/2 - padding,getY() - getHeight()/2 - padding);
        spots[4] = new Point(getX() - getWidth()/2 - padding, getY());
        spots[5] = new Point(getX() - getWidth()/2 - padding, getY() + getHeight()/2 + padding);
        
        fires[0].setXVelocity(3); fires[0].setYVelocity(3);
        fires[1].setXVelocity(3);
        fires[2].setXVelocity(3); fires[2].setYVelocity(-3);
        fires[3].setXVelocity(-3); fires[3].setYVelocity(3);
        fires[4].setXVelocity(-3); 
        fires[5].setXVelocity(-3); fires[5].setYVelocity(-3);
        
        for(int i = 0; i < spots.length; i++)
            getWorld().addSecondaryElement(spots[i],fires[i]);
    }
}