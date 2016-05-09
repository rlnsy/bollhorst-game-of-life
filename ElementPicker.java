import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

class ElementPicker extends JPopupMenu implements ActionListener {
    
    private World world;
    private int x;
    private int y;
    
    private ArrayList<JMenuItem> items;
    
    public ElementPicker(int x, int y, World world){
        this.world = world;
        this.x = x;
        this.y = y;
        
        items = new ArrayList<JMenuItem>();
        items.add(new JMenuItem("Add Dorito"));
        items.add(new JMenuItem("Add other"));
        
        for(JMenuItem i : items) {
            i.addActionListener(this);
            add(i);
        }
    }
    
    public void actionPerformed(ActionEvent e) {
        world.addElement(new Dorito(x,y));
    }
}