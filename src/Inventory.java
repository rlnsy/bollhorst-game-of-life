package src;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import res.ImageReader;

public class Inventory extends JPanel {
    private final Image background;
    private boolean isVisible;
    private World world;
    private ArrayList<InventoryButton> buttons;
    
    public Inventory(World world ) {
       world = world;
       background = ImageReader.readImage(ImageReader.getBackgroundLocation() + "toolbar.png");
       setPreferredSize(new Dimension(background.getWidth(null),background.getHeight(null)));
       buttons = new ArrayList<InventoryButton>();
       buildButtons(world);
       isVisible = false;
    }
    
    public void buildButtons(World world) {
       this.removeAll();
       for(int i = 0; i < world.getMenu().getNumItems(); i++)
       {
           InventoryButton newButton = new InventoryButton(i,world);
           Image icon = world.getMenu().getElementThumbnail(i);
           newButton.setIcon(new ImageIcon(icon));
           newButton.setBackground(new Color(123,214,248));
           newButton.setPreferredSize(new Dimension(40,40));
           newButton.setVisible(false);
           buttons.add(newButton);
           world.add(newButton);
       }
    }
  
    public void draw(Graphics g) {
        if(isVisible)
           g.drawImage(background,0,0,world);
    }
    
    public void changeVisibility() {
        if(isVisible) { 
          isVisible = false;
          for(JButton b : buttons)
            b.setVisible(false);
        }
        else {
          isVisible = true;
          for(JButton b : buttons)
            b.setVisible(true);
        }
   }
}
