/**
 * Created by rowli on 5/7/2016.
 */
import javax.swing.*;
import java.awt.*;
import java.io.*;
import javax.imageio.ImageIO;

public class WorldElement extends JComponent {

    private JPanel scene;
    
    private Image sprite;
    private int xPos;
    private int yPos;
    private int height;
    private int width;
    
    public WorldElement(Scene scene, int xPos, int yPos, String imagePath)
    {
        this.xPos = xPos;
        this.yPos = yPos;
        
        height = 0;
        width = 0;

        try {
            sprite = ImageIO.read(new File(imagePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        this.scene = scene;
        
        /*
        Thread elementThread = new Thread(new Runnable() {
            public void run() {
                while(true) {
                    repaint();
                }
            }
        });

        elementThread.start();
        */
    }

    public void draw(Graphics g)
    {
        g.drawImage(sprite, xPos - width/2, yPos - height/2,scene);
    }
    
    public void setHeight(int height)
    {
        this.height = height;
    }
    
    public void setWidth(int width)
    {
        this.width = width;
    }
}
