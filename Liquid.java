public abstract class Liquid extends WorldElement
{
    private WorldElement base;
    
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
            if(isTouching(e)) {
                canMove = false;
                base = e;
            }
        }
        if(canMove)
            moveDown(2);
    }
}
