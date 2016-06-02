package src;

import java.awt.event.*;
import java.awt.Point;
import src.game_world_elements.WorldElement;
import src.game_world_elements.PhysicsElement;

public class WorldClickListener extends MouseAdapter implements MouseMotionListener {
    private World world;
    private int mouseElementID;
    private WorldElement pickedUp; //element held by cursor
    private boolean movingElement;
    
    /*
     * pre: none
     * post: constructs a new WorldClickListener linked to world and with a default 
     * place element
     */
    public WorldClickListener(World world) {
        this.world = world;
        mouseElementID = 1;
        movingElement = false;
    }
    
    /*
     * pre: none
     * post: sets the current mouseElementID to the given value
     */
    public void setMouseElement(int elementID) {
        mouseElementID = elementID;
    }
    
    /*
     * pre: popup menu is not open
     * post: picks ip an element if clicked, if none is clicked, attempts to add
     * a new instance of the current place element at the location of the click
     */
    public void mouseClicked(MouseEvent e) {
        if(movingElement) {
            movingElement = false;
            pickedUp.release();
            pickedUp = null;
        }
        else {
            for(WorldElement elem : world.getElements()) {
                if(elem.isMovable()) {
                    if(elem.getHitBox().contains(new Point(e.getX(),e.getY()))) {
                        pickedUp = elem.hold();
                        movingElement = true;
                    }
                }
            }
            if(!movingElement) {
                Point location = new Point(e.getX(),e.getY());
                world.addElement(location,world.getMenu().getElement(mouseElementID));
            }
        }
    }
    
    /*
     * pre: none
     * post: adds new elements to the locations covered by the mouse's movement
     */
    public void mouseDragged(MouseEvent e) {
       Point location = new Point(e.getX(),e.getY());
       world.addElement(location,world.getMenu().getElement(mouseElementID));
    }
    
    /*
     * pre: the mouse remains within the world panel
     * the picked up element has its location updated to stay with the mouse
     */
    public void mouseMoved(MouseEvent e) {
        if(movingElement && pickedUp != null) {
            pickedUp.setLocation(new Point(e.getX(),e.getY()));
        }
    }
}
