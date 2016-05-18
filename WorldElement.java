import javax.swing.*;
import java.awt.*;
import java.io.*;
import javax.imageio.ImageIO;
import java.util.ArrayList;

public abstract class WorldElement extends JComponent {

    private World world;
    
    private Image sprite;
    private int xPos, yPos;
    private int width, height;
    private boolean isVisible;
    private String imagePath;
    private boolean held;
    private boolean isMovable;
    
    public WorldElement(String imagePath, boolean isMovable)
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
       held = false;
       isMovable = isMovable;
       isVisible = true;
    }
    
    public Rectangle getHitBox() {
        return new Rectangle(xPos-width/2,yPos-height/2,width,height);
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
        if(!held) {
            boolean canFall = true;
            ArrayList<WorldElement> neighbours = getTouching();
            int neighbourIndex = 0;
            while(neighbourIndex < neighbours.size()) {
                WorldElement neighbour = neighbours.get(neighbourIndex);
                if(isSupportedBy(neighbour)) {
                    canFall = false;
                    neighbourIndex = neighbours.size();
                }
                else
                    neighbourIndex++;
            }
            if(canFall)
                moveDown(2);
        }
    }
    
    public void randomMove() {
        int randomNum = (int) Math.random();
    }
    
    public boolean isSupportedBy(WorldElement other) {
        if(other instanceof Island)
            return true;
        else {
            ArrayList<WorldElement> touchingOther = other.getTouching();
            touchingOther.remove(touchingOther.indexOf(this));
            if(other.getY() > getY()) {
                for(WorldElement e : touchingOther) {
                    if(other.isSupportedBy(e))
                        return true;
                }
            }
        }     
        return false;
    }
    
    public ArrayList<WorldElement> getTouching() {
        ArrayList<WorldElement> touching = new ArrayList<WorldElement>();
        for(WorldElement e : world.getElements()) {
            if(isTouching(e))
                touching.add(e);
        }
        return touching;
    }
    
    public boolean inBounds() {
        boolean inBoundsX = xPos + width/2 < world.getWidth() && xPos - width/2 > 0;
        boolean inBoundsY = yPos + height/2 < world.getHeight() && yPos - height/2 > 0;
        return inBoundsX && inBoundsY;
    }
    
    public String getImagePath(){ return imagePath; }
    public int getX() { return xPos; }
    public int getY() { return yPos; }
    public int getWidth() { return width; }
    public int getHeight() { return height; }
    
    public abstract Image getThumbnail();
    
    public void hold() { held = true; }
    public void release() { held = false; }
    
    public Image getSprite() { return sprite; }
    public boolean isMovable() { return isMovable; }
}
