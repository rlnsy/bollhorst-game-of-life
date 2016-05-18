import javax.swing.*;
import java.awt.event.*;
//NOT WORKING
public class InventoryButton extends JButton implements ActionListener {
    private int elementID;
    private World world;
    public InventoryButton(int elementID, World world) {
        elementID = elementID;
        world = world;
    }
    
    public void actionPerformed(ActionEvent e) {
        world.setMouseElement(elementID);
    }
}
