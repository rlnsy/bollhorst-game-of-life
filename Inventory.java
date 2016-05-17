import javax.swing.*;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.event.*;
import java.io.*;
import java.awt.event.*;
import java.awt.*;
 

public class Inventory extends JComponent {
    private Image background;
    private boolean isVisible;
    private World world;
    
    public Inventory(World world)
    {
        try {
           background = ImageIO.read(new File("images/Toolbar.png"));
       } catch (IOException e) {
           throw new RuntimeException(e);
       }
       
       JButton button = new JButton();
       try {
        Image img = ImageIO.read(new File("images/dirt.png"));
        button.setIcon(new ImageIcon(img));
      } catch (IOException ex) {
      }
       world.add(button);
       button.isVisible();
       isVisible = false;
       world = world;
    }
  
    public void draw(Graphics g) {
        if(isVisible)
           g.drawImage(background,0,0,world);
    }
    
    public void changeVisibility() {
        if(isVisible)
            isVisible = false;
        else
            isVisible = true;
    }
}
