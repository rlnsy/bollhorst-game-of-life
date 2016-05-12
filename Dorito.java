public class Dorito extends WorldElement {
    public Dorito() {
        super("images/dorito.png");
    }
    
    public void update() {
        
        boolean canMove = true;
        for(WorldElement e : getWorld().getElements()) {
            if(isTouching(e))
                canMove = false;
        }
        if(canMove)
            moveDown(1);
        
    }
 }
