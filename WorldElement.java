/**
 * Created by rowli on 5/7/2016.
 */
import javax.swing.*;
import java.awt.*;
import java.io.*;
import javax.imageio.ImageIO;

public abstract class WorldElement extends JComponent {

    private World world;
    
    private Image sprite;
    private int xPos;
    private int yPos;
    private int height;
    private int width;
    
    public WorldElement(int xPos, int yPos, String imagePath)
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
       
       // world added by setWorld in World.addElement 
    }

    public void draw(Graphics g) {
        g.drawImage(sprite, xPos - width/2, yPos - height/2,world);
    }
    
    public void setWorld(World world) { this.world = world; }
    
    public void setLocation(int xValue, int yValue) { 
        xPos = xValue;
        yPos = yValue;
    }
    
    public void setHeight(int height) { this.height = height; }
    
    public void setWidth(int width) { this.width = width; }
    
    public abstract void doSomething();
    
    public void moveDown() {
        yPos++;
    }
}
