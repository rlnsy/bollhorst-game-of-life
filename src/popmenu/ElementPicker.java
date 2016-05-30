package src.popmenu;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import src.World;
import src.game_world_elements.WorldElement;

class ElementPicker extends JPopupMenu implements ActionListener {
    
    private World world;
    private int xPos;
    private int yPos;
    
    private ArrayList<ElementPickerItem> items;
    
    public ElementPicker(int xPos, int yPos, World world){
        this.world = world;
        this.xPos = xPos;
        this.yPos = yPos;
        add(new JLabel("Place new object..."));
        items = new ArrayList<ElementPickerItem>();

        for(int i = 0; i < world.getMenu().getNumItems(); i++)
            items.add(world.getMenu().getPickerItem(i));
       
        for(ElementPickerItem i : items) {
            i.addActionListener(this);
            add(i);
        }
    }
    
    public void actionPerformed(ActionEvent e) {
        JMenuItem item = (JMenuItem)e.getSource();
        int index = items.indexOf(item);
        for(ElementPickerItem i : items) {
            if(i.getPosition() == index)
            {
                WorldElement placeElement = i.getPlaceElement();
                world.addElement(xPos,yPos, placeElement);
            }
        }
    }
}