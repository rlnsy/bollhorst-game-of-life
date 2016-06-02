package src.game_world_elements;

import java.awt.*;

public abstract class Liquid extends PhysicsElement
{
    private WorldElement base;
    public boolean supported;
    
    public Liquid() {
        super();
    }
    
    public void behave() {
        super.behave();
        base = getSupport();
        //if(base != null && !(base instanceof Liquid)) {
        if(base != null) {
            if(getX() > base.getX() && canMoveRight())
                setLocation(new Point(getX()+1,getY()));
            else if(canMoveLeft())
                setLocation(new Point(getX()-1,getY()));
        }
    }
    
    public boolean isTouchingLiquid() {
        for(WorldElement e : getTouching()) {
            if(e instanceof Liquid)
                return true;
        }
        return false;
    }
}
