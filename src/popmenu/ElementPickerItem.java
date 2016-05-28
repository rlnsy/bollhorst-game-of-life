package src.popmenu;

import javax.swing.*;
import src.WorldElement;
public class ElementPickerItem extends JMenuItem
{
    private int menuPosition;
    private WorldElement element;
    
    public ElementPickerItem(String label, int positionInMenu, WorldElement element) {
        super(label);
        menuPosition = positionInMenu;
        this.element = element;
    }
    
    public int getPosition() { return menuPosition; };
    
    public WorldElement getPlaceElement() { return element; }
}
