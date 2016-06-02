package src.game_world_elements;

import java.awt.event.*;
import java.awt.*;
import res.ImageReader;
import javax.swing.Action;
import javax.swing.AbstractAction;

public class Bollhorst extends PhysicsElement {
    private Image speechBox;
    private final int DETECT_RADIUS = 100;
    private final int SPEEDY_SPEED = 3;
    private int physicsCount = 0;
    
    public Bollhorst() {
        super();
        setMovable(true);
        speechBox = ImageReader.readImage(ImageReader.getEffectLocation() + "bollhorst_speech.png");
    }
    
    public void draw(Graphics g) {
        super.draw(g);
        if(targetDetected())
            drawMessage(g);
    }
    
    private void drawMessage(Graphics g) {
        g.drawImage(speechBox,getX()-getWidth()/2-speechBox.getWidth(null),getY() - getHeight()/2,getWorld());
    }
    
    private Rectangle getDetectionBox () {
        return new Rectangle(getX()-getWidth()/2-DETECT_RADIUS,getY()-getHeight()/2,(2*DETECT_RADIUS)+getWidth(),getHeight());
    }
    
    public boolean targetDetected() {
        for(WorldElement e : getWorld().getElements()) {
            if(getDetectionBox().contains(e.getLocation()) && (e instanceof Villager || (e instanceof Bollhorst && !e.equals(this))))
                return true;    
        }
        return false;
    }
    
    public void touchedElement(WorldElement other) {
        if(other instanceof Dorito)
            eat(other);
    }
    
    public void eat(WorldElement other) {
        other.removeFromWorld();
    }
    
    public void behave() {
        super.behave();
    }
    
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
    
    public void applyPhysics() {
        super.applyPhysics();
        physicsCount++;
    }
}