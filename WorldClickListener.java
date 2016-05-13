import java.awt.event.*;

public class WorldClickListener extends MouseAdapter implements MouseMotionListener {
    private World world;
    private int currentElementIndex;
    
    public WorldClickListener(World world) {
        this.world = world;
        currentElementIndex = 0;
    }
    
    public void changeCurrentElement(int elementIndex) {
        currentElementIndex = elementIndex;
    }
    
    public void mouseClicked(MouseEvent e) {
        world.addElement(e.getX(),e.getY(),world.getMenu().getElement(currentElementIndex));
    }
    
    public void mouseDragged(MouseEvent e) {
       world.addElement(e.getX(),e.getY(),world.getMenu().getElement(currentElementIndex));
    }
    
    public void mouseMoved(MouseEvent e) {}
}