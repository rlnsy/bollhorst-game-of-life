public class Water extends WorldElement {
    
    private WorldElement platform;
    
    public Water () {
        super("images/water.png");
    }
    
    public void behave() {
        gravitate();
        if(platform != null) {
            if(getX() > platform.getX())
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
                platform = e;
            }
        }
        if(canMove)
            moveDown(2);
    }
}