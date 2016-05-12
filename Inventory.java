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
       isVisible = true;
       world = world;
    }
  
    public void draw(Graphics g) {
        if(isVisible)
            g.drawImage(background,0,0,world);
    }
}
