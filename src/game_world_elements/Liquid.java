package src.game_world_elements;

public abstract class Liquid extends PhysicsElement
{
    private WorldElement base;
    public boolean supported;
    
    public Liquid(boolean isMovable) {
        super(isMovable);
    }
    
    public void behave() {
        base = gravitate();
        //if(base != null && !(base instanceof Liquid)) {
        if(base != null) {
            if(getX() > base.getX() && canMoveRight())
                setLocation(getX()+1,getY());
            else if(canMoveLeft())
                setLocation(getX()-1,getY());
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
