import javax.swing.*;
import java.awt.*;
import java.io.*;
import javax.imageio.ImageIO;

public abstract class WorldElement extends JComponent {

    private World world;
    
    private Image sprite;
    private int xPos, yPos;
    private int width, height;
    private boolean isVisible;
    private boolean isCollisionElement;
    
    public WorldElement(String imagePath, boolean collidable)
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
       isCollisionElement = collidable;
       isVisible = true;
    }
    
    public Rectangle getHitBox() {
        return new Rectangle(xPos-width/2,yPos-height/2,width,height);
        // return a rectangle representing a 1 thick buffer around the image
    }
    
    public void draw(Graphics g) {
        if(isVisible)
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
    
    public void update() {
        if(!inBounds())
            isVisible  = false;
        behave();
    }
    
    public boolean isVisible() { return isVisible; }
    
    public abstract void behave();
   
    public void moveDown(int value) {
        yPos+= value;
    }
    
    public void moveUp(int value) {
        yPos-= value;
    }
   
    public World getWorld() { return world; }
    
    public void gravitate() {
        boolean canMove = true;
        for(WorldElement e : getWorld().getElements()) {
            if(isTouching(e))
                canMove = false;
        }
        if(canMove)
            moveDown(2);
    }
    
    public boolean inBounds() {
        boolean inBoundsX = xPos + width/2 < world.getWidth() && xPos - width/2 > 0;
        boolean inBoundsY = yPos + height/2 < world.getHeight() && yPos - height/2 > 0;
        return inBoundsX && inBoundsY;
    }
    
    public int getX() { return xPos; }
    public int getY() { return yPos; }
}
