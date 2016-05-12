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
    private int xPos, yPos;
    private int width, height;
    
    public WorldElement(String imagePath)
    {
       this.xPos = 0;
       this.yPos = 0;
    
       try {
           sprite = ImageIO.read(new File(imagePath));
       } catch (IOException e) {
           throw new RuntimeException(e);
       }    
       
       width = sprite.getWidth(null);
       height = sprite.getHeight(null);
    }

    public Rectangle getHitBox() {
        return new Rectangle(xPos-width/2,yPos-height/2,width,height);
    }
    
    public void draw(Graphics g) {
        g.drawImage(sprite, xPos-width/2, yPos-height/2,world);
    }
    
    public void setWorld(World world) { this.world = world; }
    
    public void setLocation(int xValue, int yValue) { 
        xPos = xValue;
        yPos = yValue;
    }
    
    public boolean isTouching(WorldElement e) {
        if(!equals(e)&&(getHitBox().intersects(e.getHitBox())))
            return true;
        return false;
    }
    
    public abstract void update();
   
    public void moveDown(int value) {
        yPos+= value;
    }
    
    public void moveUp(int value) {
        yPos-= value;
    }
   
    public World getWorld() { return world; }
}
