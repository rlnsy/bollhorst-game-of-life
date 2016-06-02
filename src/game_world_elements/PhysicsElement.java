package src.game_world_elements;

import java.util.ArrayList;
import res.AudioPlayer;
import java.awt.*;

public abstract class PhysicsElement extends WorldElement
{
    private final int DEFAULT_GRAVITY_ACCELERATION = -1;
    private boolean isStationary;
    
    private int xVelocity = 0;
    private int yVelocity = 0;
    private WorldElement support;
    
    /*
     * pre: none
     * post: constructs a new PhysicsElement
     */
    public PhysicsElement() {
        super();
        isStationary = false;
    }
    
    /*
     * pre: none
     * post: calls methods to update the element's velocity and location
     */
    public void behave() {
        accelerate();
        applyPhysics();
    }
    
    /*
     * pre: none
     * post: applies velocities to move element, taking collisions into account
     */
    public void applyPhysics() {
        int newX = getX();
        int newY = getY();
        
        if(getYVelocity() < 0) {
            for(int i = 0; i < (-1*getYVelocity()); i++) {
                if(canMoveDown())    
                    newY += 1;
                setLocation(new Point(newX,newY));
            }
        }
        else if(getYVelocity() > 0) {
            for(int i = 0; i < getYVelocity(); i++) {
                newY -= 1;
            }
        }
        
        if(getXVelocity() < 0) {
            for(int i = 0; i < -1*getXVelocity(); i++) {
                if(canMoveLeft())    
                    newX -= 1;
                setLocation(new Point(newX,newY));
            }
        }
        else if(getXVelocity() > 0) {
            for(int i = 0; i < getXVelocity(); i++) {
                if(canMoveRight())    
                    newX += 1;
                setLocation(new Point(newX,newY));
            }
        }
        setLocation(new Point(newX,newY));
    }
    
    /*
     * pre: none
     * post: applies acceleration due to gravity
     */
    public void accelerate() {
        if(!isHeld())
            changeYVelocity(DEFAULT_GRAVITY_ACCELERATION);
    }
   
    /* pre: none
     * post: returns true if and only if no supported element is beneath
     * this one, and in that case sets the support attribute to the element
     * that blocked the potential movement
     */
    public boolean canMoveDown() {
        if(!isHeld()) {
            ArrayList<PhysicsElement> physicalNeighbours = new ArrayList<PhysicsElement>();
            for(WorldElement e : getTouching()) {
                if(e instanceof PhysicsElement)
                    physicalNeighbours.add((PhysicsElement)(e));
            }
            int neighbourIndex = 0;
            while(neighbourIndex < physicalNeighbours.size()) {
                PhysicsElement neighbour = physicalNeighbours.get(neighbourIndex);
                if(isSupportedBy(neighbour) || neighbour instanceof BuildBlock) {
                    makeStationary();
                    support = neighbour;
                    return false;
                }
                else {
                    neighbourIndex++;
                }
            }
        }
        return true;
    }
    
    /*
     * pre: none
     * post: returns true if and only if no element is touching from the right
     */
    public boolean canMoveRight() {
        for(WorldElement e : getTouching()) {
            if(e.isTouching(this) && this.getDirectionOf(e) > 0)
                return false;
        }
        return true;
    }
    
    /*
     * pre: none
     * post: returns true if and only if no element is touching from the left
     */
    public boolean canMoveLeft() {
        for(WorldElement e : getTouching()) {
            if(e.isTouching(this) && this.getDirectionOf(e) < 0)
                return false;
        }
        return true;
    }
    
    /*
     * pre: none
     * post: returns all elements that are touching and are under this element
     */
    public ArrayList<PhysicsElement> getPossibleSupports() {
        ArrayList<PhysicsElement> possible = new ArrayList<PhysicsElement>();
        for(WorldElement e : getTouching()) {
            if(e.isUnder(this) && e instanceof PhysicsElement)
                possible.add((PhysicsElement)(e));
        }
        return possible;
    }
    
    /*
     * pre: none
     * post: returns true if other is in a sequence of touching elements
     * that at some point touches the island or a building block
     */
    public boolean isSupportedBy(PhysicsElement other) {
        if(other instanceof Island || other instanceof BuildBlock)
            return true;
        else {
            ArrayList<PhysicsElement> potentialSupports = other.getPossibleSupports();
            if(other.getY() > getY()) {
                for(PhysicsElement e : potentialSupports) {
                    if(other.isSupportedBy(e))
                        return true;
                }
            }
        }     
        return false;
    }
    
    /*
     * pre: "thud" audio file is present in resource directory
     * post: sets y velocity to zero and plays a thud noise
     */
    public void makeStationary() {
        setYVelocity(0);
        if(!isStationary() && !(this instanceof Liquid)) {
            AudioPlayer.playClip("thud.wav");
        }
        isStationary = true;
    }
    
    //increases X velocity by value
    public void changeXVelocity(int value){
        xVelocity += value;
    }
    
    //increases Y velocity by value
    public void changeYVelocity(int value){
        yVelocity += value;
    }
    
    public void setXVelocity(int value) {
        this.xVelocity = value;
    }
    
    public void setYVelocity(int value) {
        this.yVelocity = value;
    }
    
    public boolean isStationary() { 
        return isStationary; 
    }
    
    public int getXVelocity() { return xVelocity; }
    
    public int getYVelocity() { return yVelocity; }
    
    public WorldElement getSupport() {
        if(support != null)
            return support;
        else
            return null;
    }
}
