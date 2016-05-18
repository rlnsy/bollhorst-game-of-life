import javax.swing.*;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.event.*;
import java.io.*;
import java.awt.event.*;
import java.awt.*;
import java.util.ArrayList;
 

public class Inventory extends JPanel {
    private Image background;
    private boolean isVisible;
    private World world;
    private ArrayList<JButton> buttons;
    
    public Inventory(World world)
    {
        try {
           background = ImageIO.read(new File("images/Toolbar.png"));
       } catch (IOException e) {
           throw new RuntimeException(e);
       }
       buttons = new ArrayList<JButton>();
       for(int i = 0; i < world.getMenu().getNumItems(); i++)
       {
           JButton newButton = new JButton();
           Image icon = world.getMenu().getDefaultSprite(i);
           newButton.setIcon(new ImageIcon(icon));
           newButton.setVisible(false);
           buttons.add(newButton);
           world.add(newButton);
       }
       isVisible = false;
       world = world;
    }
  
    public void draw(Graphics g) {
        if(isVisible)
           g.drawImage(background,0,0,world);
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
