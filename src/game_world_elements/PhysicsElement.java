package src.game_world_elements;

import java.util.ArrayList;
import res.AudioPlayer;

public abstract class PhysicsElement extends WorldElement
{
    private final double DEFAULT_GRAVITY_ACCELERATION = 0.5;
    private boolean isStationary;
    
    private double yVel;
    
    public PhysicsElement(boolean isMovable) {
        super(isMovable);
        isStationary = false;
        yVel = 0;
    }
    
    public void behave() {}
    
    public void update() {
        super.update();
        if(!isStationary)
            yVel += DEFAULT_GRAVITY_ACCELERATION;
    }
   
    // returns the element that blocked the fall, or null
    // Physics version
    public PhysicsElement gravitate() {
        for(int i = 0; i < yVel; i++) {
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
                        return neighbour;
                    }
                    else {
                        neighbourIndex++;
                    }
                }
                setLocation(getX(),getY() + 1);
            }
        }
        isStationary = false;
        return null;
    }
    
    public boolean canMoveRight() {
        for(WorldElement e : getTouching()) {
            if(e.nextTo(this) && this.getDirectionOf(e) > 0)
                return false;
        }
        return true;
    }
    
    public boolean canMoveLeft() {
        for(WorldElement e : getTouching()) {
            if(e.nextTo(this) && this.getDirectionOf(e) < 0)
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
    
    public boolean isStationary() { return isStationary; }
    
    public void makeStationary() {
        yVel = 0;
        if(!isStationary() && !(this instanceof Liquid))
            AudioPlayer.playClip("thud.wav"); 
        isStationary = true;
    }
}
