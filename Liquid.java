import java.util.ArrayList;

public abstract class Liquid extends WorldElement
{
    private WorldElement base;
    public boolean supported;
    
    public Liquid(boolean isMovable) {
        super(isMovable);
    }
    
    public void behave() {
        gravitate();
        if(base != null && !(base instanceof Liquid)) {
            if(getX() > base.getX() && canMoveRight())
                setLocation(getX()+1,getY());
            else if(canMoveLeft())
                setLocation(getX()-1,getY());
        }
    }
    
    public void gravitate() {
        boolean canFall = true;
        ArrayList<WorldElement> neighbours = getTouching();
        int neighbourIndex = 0;
        while(neighbourIndex < neighbours.size()) {
            WorldElement neighbour = neighbours.get(neighbourIndex);
            if(isSupportedBy(neighbour)) {
                canFall = false;
                neighbourIndex = neighbours.size();
                base = neighbour;
            }
            else
                neighbourIndex++;
        }
        if(canFall)
            moveDown(2);
    }
    
}
