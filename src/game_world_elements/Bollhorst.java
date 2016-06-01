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
    
    private int velocityLeft = 0;
    private int velocityRight = 0;
    private int velocityUp = 0;
    private int behaveCount = 0;
    
    public Bollhorst() {
        super(true);
        speechBox = ImageReader.readImage(ImageReader.getEffectLocation() + "bollhorst_speech.png");
    }
    
    public void behave() {
        gravitate();
        moveRight();
        if(velocityRight > 0 && behaveCount%4 == 0)
            velocityRight--;
        moveLeft();
        if(velocityLeft > 0 && behaveCount%4 == 0)
            velocityLeft--;
        jump();
        if(velocityUp > 0 && behaveCount%2 == 0)
            velocityUp--;
        behaveCount++;
    }
    
    public void draw(Graphics g) {
        super.draw(g);
        if(targetDetected())
            drawMessage(g);
    }
    
    public void drawMessage(Graphics g) {
        g.drawImage(speechBox,getX()-getWidth()/2-speechBox.getWidth(null),getY() - getHeight()/2,getWorld());
    }
    
    public Rectangle getDetectionBox () {
        return new Rectangle(getX()-getWidth()/2-DETECT_RADIUS,getY()-getHeight()/2,(2*DETECT_RADIUS)+getWidth(),getHeight());
    }
    
    public boolean targetDetected() {
        for(WorldElement e : getWorld().getElements()) {
            if(getDetectionBox().contains(e.getLocation()) && (e instanceof Villager || (e instanceof Bollhorst && !e.equals(this))))
                return true;    
        }
        return false;
    }
    
    public void touchedElement(WorldElement other) {}
    
    public void moveRight() {
        setLocation(getX()+velocityRight,getY());
    }
    
    public void addRightVelocity(){
        velocityRight += 2;
    }
    
    public void moveLeft() {
        setLocation(getX()-velocityLeft,getY());
    }
    
    public void addLeftVelocity(){
        velocityLeft += 2;
    }
    
    public void jump() {
        setLocation(getX(), getY()-velocityUp);
    }
    
    public void addUpVelocity(){
        velocityUp += 10;
    }
}