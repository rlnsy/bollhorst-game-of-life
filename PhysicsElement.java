import java.util.ArrayList;
public class PhysicsElement extends WorldElement
{
    private Physics physics;
    
    public PhysicsElement(boolean isMovable) {
        super(isMovable);
        physics = new Physics(0);
    }
    
    public void behave() {}
    
    public void update() {
        super.update();
        physics.update();
    }
   
    // returns the element that blocked the fall, or null
    // Physics version
    public PhysicsElement gravitate() {
        for(int i = 0; i < physics.getYVel(); i++) {
            if(!isHeld()) {
                boolean canFall = true;
                ArrayList<PhysicsElement> physicalNeighbours = new ArrayList<PhysicsElement>();
                for(WorldElement e : getTouching()) {
                    if(e instanceof PhysicsElement)
                        physicalNeighbours.add((PhysicsElement)(e));
                }
                int neighbourIndex = 0;
                while(neighbourIndex < physicalNeighbours.size()) {
                    PhysicsElement neighbour = physicalNeighbours.get(neighbourIndex);
                    if(isSupportedBy(neighbour)) {
                        canFall = false;
                        return neighbour;
                    }
                    else
                        neighbourIndex++;
                }
                setLocation(getX(),getY() + 1);
            }
        }
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
        if(other instanceof Island)
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
}
