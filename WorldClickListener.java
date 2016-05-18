import java.awt.event.*;
import java.awt.Point;

public class WorldClickListener extends MouseAdapter implements MouseMotionListener {
    private World world;
    private int currentElementIndex;
    private WorldElement pickedUp;
    private boolean movingElement;
    
    public WorldClickListener(World world) {
        this.world = world;
        currentElementIndex = 0;
        movingElement = false;
    }
    
    public void changeCurrentElement(int elementIndex) {
        currentElementIndex = elementIndex;
    }
    
    public void mouseClicked(MouseEvent e) {
        if(movingElement) {
            movingElement = false;
            pickedUp.release();
            pickedUp = null;
        }
        else {
            for(WorldElement elem : world.getElements()) {
                if(elem.getHitBox().contains(new Point(e.getX(),e.getY()))) {
                    elem.hold();
                    pickedUp = elem;
                    movingElement = true;
                }
            }
            world.addElement(e.getX(),e.getY(),world.getMenu().getElement(currentElementIndex));
        }
    }
    
    public void mouseDragged(MouseEvent e) {
       world.addElement(e.getX(),e.getY(),world.getMenu().getElement(currentElementIndex));
    }
    
    public void mouseMoved(MouseEvent e) {
        if(movingElement && pickedUp != null) {
            pickedUp.setLocation(e.getX(),e.getY());
        }
    }
    
    public void setMoveElement(WorldElement e) {
        pickedUp = e;
    }
}
