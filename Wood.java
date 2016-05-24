import java.util.ArrayList;
public class Wood extends PhysicsElement

{
    public Wood(){
        super(true);
    }
    public void behave(){
        ArrayList<PhysicsElement> woodNeighbours = new ArrayList<PhysicsElement>();
        for(WorldElement e : getWorld().getElements()) {
            if(e instanceof Wood)
                woodNeighbours.add((PhysicsElement)(e));
        }
        for(int i = 0; i<woodNeighbours.size(); i++)
        {
            PhysicsElement e = woodNeighbours.get(i);
            // && (this.getY() != e.getY() && this.getX() != e.getY())
            if((woodNeighbours.size() > 1))
            {
                this.setLocation(e.getX(), (e.getY()+this.getHeight()/2));
            }
        }
            /*if (isUnder(Wood))
            {
                wood.yPos = other.getY();
            }*/
        }
    }
