import java.awt.event.*;

public class WorldClickListener extends MouseAdapter {
    private World world;
    private int currentElementIndex;
    public WorldClickListener(World world) {
        this.world = world;
        currentElementIndex = 0;
    }
    
    public void mouseClicked(MouseEvent e) {
        world.addElement(e.getX(),e.getY(),world.getMenu().getItem(currentElementIndex).getPlaceElement());
    }
    
    public void changeCurrentElement(int indexOfElement)
    {
        currentElementIndex = indexOfElement;
    }
}
