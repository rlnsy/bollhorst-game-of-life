package src.popmenu;

import java.awt.event.*;
import src.World;

public class PopUpListener extends MouseAdapter {
    
    private World world;
    
    public PopUpListener(World world) {
        super();
        this.world = world;
    }
    
    public void mousePressed(MouseEvent e){
        if (e.isPopupTrigger())
            displayMenu(e);
    }

    public void mouseReleased(MouseEvent e){
        if (e.isPopupTrigger())
            displayMenu(e);
    }

    private void displayMenu(MouseEvent e){
        ElementPicker menu = new ElementPicker(e.getX(), e.getY(),world);
        menu.show(e.getComponent(), e.getX(), e.getY());
    }
}
