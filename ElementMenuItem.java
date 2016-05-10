import javax.swing.*;
public class ElementMenuItem extends JMenuItem
{
    private int menuPosition;
    private WorldElement element;
    
    public ElementMenuItem(String label, int positionInMenu, WorldElement element) {
        super(label);
        menuPosition = positionInMenu;
        this.element = element;
    }
    
    public int getPosition() { return menuPosition; };
    
    public WorldElement getPlaceElement() { return element; }
}
