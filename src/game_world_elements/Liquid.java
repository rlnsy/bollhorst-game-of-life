package src.game_world_elements;

import java.awt.*;

public abstract class Liquid extends PhysicsElement
{
    private WorldElement base;
    //Element on which the liquid particle slides
    public boolean supported;
    
    public Liquid() {
        super();
    }
    
    /*
     * pre: none
     * post: element behaves as a physical element and slides on its base of support
     */
    @Override
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
    
    /*
     * pre: none
     * post: returns true if in contact with some instance of Liquid
     */
    public boolean isTouchingLiquid() {
        for(WorldElement e : getTouching()) {
            if(e instanceof Liquid)
                return true;
        }
        return false;
    }
}
