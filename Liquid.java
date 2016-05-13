public abstract class Liquid extends WorldElement
{
    private WorldElement base;
    public boolean supported;
    
    public Liquid(String imagePath) {
        super(imagePath);
    }
    
    public void behave() {
        gravitate();
        if(base != null && !(base instanceof Liquid)) {
            if(getX() > base.getX())
                setLocation(getX()+1,getY());
            else
                setLocation(getX()-1,getY());
        }
    }
    
    public void gravitate() {
        boolean canMove = true;
        for(WorldElement e : getWorld().getElements()) {
            if(isTouching(e) && !(e instanceof Liquid)) {
                canMove = false;
                supported = true;
                base = e;
            }
            else if(e instanceof Liquid && isTouching(e) && ((Liquid)e).isSupported()) {
                canMove = false;
                base = e;
            }
        }
        if(canMove)
            moveDown(2);
    }
    
    public boolean isSupported() { return supported; }
}
