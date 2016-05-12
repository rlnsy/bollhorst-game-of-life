public class Water extends WorldElement {
    
    private WorldElement base;
    
    public Water () {
        super("images/water.png");
    }
    
    public void behave() {
        gravitate();
        if(base != null) {
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