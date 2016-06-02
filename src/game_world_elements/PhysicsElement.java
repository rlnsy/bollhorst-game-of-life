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
    
    public PhysicsElement() {
        super();
        isStationary = false;
    }
    
    public void update() {
        super.update();
    }
    
    public void behave() {
        accelerate();
        applyPhysics();
    }
    
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
    
    public void accelerate() {
        changeYVelocity(DEFAULT_GRAVITY_ACCELERATION);
    }
   
    /* pre:
     * post: moves this element downwards until it collides with a supported
     * element, Returns the element that blocked fall if one did
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
    
    public boolean canMoveRight() {
        for(WorldElement e : getTouching()) {
            if(e.isTouching(this) && this.getDirectionOf(e) > 0)
                return false;
        }
        return true;
    }
    
    public boolean canMoveLeft() {
        for(WorldElement e : getTouching()) {
            if(e.isTouching(this) && this.getDirectionOf(e) < 0)
                return false;
        }
        return true;
    }
    
    public ArrayList<PhysicsElement> getPossibleSupports() {
        ArrayList<PhysicsElement> possible = new ArrayList<PhysicsElement>();
        for(WorldElement e : getTouching()) {
            if(e.isUnder(this) && e instanceof PhysicsElement)
                possible.add((PhysicsElement)(e));
        }
        return possible;
    }
    
    public boolean isSupportedBy(PhysicsElement other) {
        if(other instanceof Island || other instanceof BuildBlock)
            return true;
        else {
            ArrayList<PhysicsElement> potentialSupports = other.getPossibleSupports();
            int thisIndex = potentialSupports.indexOf(this);
            if(thisIndex > 0)
                potentialSupports.remove(thisIndex);
            if(other.getY() > getY()) {
                for(PhysicsElement e : potentialSupports) {
                    if(other.isSupportedBy(e))
                        return true;
                }
            }
        }     
        return false;
    }
    
    public void makeStationary() {
        //setXVelocity(0);
        setYVelocity(0);
        if(!isStationary() && !(this instanceof Liquid)) {
            AudioPlayer.playClip("thud.wav");
        }
        isStationary = true;
    }
    
    public void changeXVelocity(int value){
        xVelocity += value;
    }
    
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
