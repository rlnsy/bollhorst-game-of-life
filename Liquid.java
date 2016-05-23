public abstract class Liquid extends PhysicsElement
{
    private WorldElement base;
    public boolean supported;
    
    public Liquid(boolean isMovable) {
        super(isMovable);
    }
    
    public void behave() {
        base = gravitate();
        if(base != null && !(base instanceof Liquid)) {
            if(getX() > base.getX() && canMoveRight())
                setLocation(getX()+1,getY());
            else if(canMoveLeft())
                setLocation(getX()-1,getY());
        }
    }
}
