import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

class ElementPicker extends JPopupMenu implements ActionListener {
    
    private World world;
    private int xPos;
    private int yPos;
    
    private ArrayList<ElementMenuItem> items;
    
    public ElementPicker(int xPos, int yPos, World world){
        this.world = world;
        this.xPos = xPos;
        this.yPos = yPos;
        add(new JLabel("Place new object..."));
        items = new ArrayList<ElementMenuItem>();

        for(int i = 0; i < world.getMenu().getNumItems(); i++)
            items.add(world.getMenu().getMenuItem(i));
       
        for(ElementMenuItem i : items) {
            i.addActionListener(this);
            add(i);
        }
    }
    
    public void actionPerformed(ActionEvent e) {
        JMenuItem item = (JMenuItem)e.getSource();
        int index = items.indexOf(item);
        for(ElementMenuItem i : items) {
            if(i.getPosition() == index)
            {
                WorldElement placeElement = i.getPlaceElement();
                world.addElement(xPos,yPos, placeElement);
            }
        }
    }
}