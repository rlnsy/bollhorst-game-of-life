import javax.swing.*;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.event.*;
import java.io.*;
import java.awt.event.*;
import java.awt.*;
import java.util.ArrayList;
 

public class Inventory extends JPanel {
    private final Image background;
    private boolean isVisible;
    private final World WORLD;
    private ArrayList<InventoryButton> buttons;
    
    public Inventory(World world )
    {
       WORLD = world;
        try {
           background = ImageIO.read(new File("images/Toolbar.png"));
       } catch (IOException e) {
           throw new RuntimeException(e);
       }
       buttons = new ArrayList<InventoryButton>();
       for(int i = 0; i < WORLD.getMenu().getNumItems(); i++)
       {
           InventoryButton newButton = new InventoryButton(i,WORLD);
           Image icon = WORLD.getMenu().getElementThumbnail(i);
           newButton.setIcon(new ImageIcon(icon));
           newButton.setVisible(false);
           buttons.add(newButton);
           WORLD.add(newButton);
       }
       isVisible = false;
    }
  
    public void draw(Graphics g) {
        if(isVisible)
           g.drawImage(background,0,0,WORLD);
    }
    
    public void changeVisibility() {
        if(isVisible)
        { 
          isVisible = false;
          for(JButton b : buttons)
            b.setVisible(false);
        }
        else
        {
          isVisible = true;
          for(JButton b : buttons)
            b.setVisible(true);
        }
   }
}
