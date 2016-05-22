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
    private boolean isBurning;
    private int burnedFor;
    private int maxBurnTime;
    private double playerMovement;
    private Image effect;
    private boolean isStationary;
    
    private int testSteps;
    
    public WorldElement(boolean isMovable)
    {
       this.xPos = 0;
       this.yPos = 0;
       String classType = getClass().getName().toLowerCase();
       String imagePath = Game.DEFAULT_SPRITE_LOCATION + classType + ".png";
       sprite = Game.readImage(imagePath);
       width = sprite.getWidth(null);
       height = sprite.getHeight(null);
       held = false;
       isMovable = isMovable;
       isVisible = true;
       isStationary = false;
       maxBurnTime = 50;
       
       testSteps = 0;
    }
    
    public Rectangle getHitBox() {
        return new Rectangle(xPos-width/2,yPos-height/2,width,height);
    }
    
    public void draw(Graphics g) {
        if(isVisible) {
            g.drawImage(sprite, xPos-width/2, yPos-height/2,world);
            if(effect != null)
                g.drawImage(effect, xPos-width/2, yPos-height/2,world);
        }
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
        int possibleSupports = 0;
        for(WorldElement e : getTouching()) {
            if(e.isUnder(this))
                possibleSupports++;
        }
        if(possibleSupports == 0)
            isStationary = false;
        if(!inBounds())
            isVisible  = false;
        if(isBurning)
            burnedFor++;
        if(burnedFor == maxBurnTime)
            isVisible = false;
        behave();
    }
    
    public boolean isVisible() { return isVisible; }
    
    public void setVisble(boolean isVisible) { 
        this.isVisible = isVisible; 
    }
    
    public abstract void behave();
   
    public void moveDown(int value) {
        yPos+= value;
    }
    
    public void moveUp(int value) {
        yPos-= value;
    }
   
    public World getWorld() { return world; }
    
    public void gravitate() {
        if(!held && !isStationary) {
            testSteps++;
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
            else
                isStationary = true;
        }
    }
    
    public void randomMove() {
        double randomNum = Math.random();
        int numMove = 0;
        if(randomNum < 0.990) {
            if(numMove%100 == 0)
               if((playerMovement == 1 && canMoveRight()) || (playerMovement == -1 && canMoveLeft()))
                    xPos += playerMovement;  
        }
        else if(randomNum <= 0.993 && randomNum >= 0.990) {
            playerMovement = 1;
            numMove++;
        }
        else if(randomNum > 0.993 && randomNum <= 0.997) {
            playerMovement = 0;
            numMove++;
        }
        else if(randomNum > 0.997 && canMoveLeft()) {
            playerMovement = -1;
            numMove++;
        }
    }
    
    // returns + if to right, - if to left
    public int getDirectionOf(WorldElement other) {
        return -1 * (getX() - other.getX());
    }
    
    public boolean canMoveRight() {
        for(WorldElement e : getTouching()) {
            if(e.nextTo(this) && this.getDirectionOf(e) > 0)
                return false;
        }
        return true;
    }
    
    public boolean canMoveLeft() {
        for(WorldElement e : getTouching()) {
            if(e.nextTo(this) && this.getDirectionOf(e) < 0)
                return false;
        }
        return true;
    }
    
    public boolean nextTo(WorldElement other) {
        boolean bool = (getY() < other.getY() + other.getHeight()/2) && (getY() > other.getY() - other.getHeight()/2);
        return bool; 
    }
    
    public boolean isUnder(WorldElement other) {
        return ((getY() - getHeight()/2) <= (other.getY() + other.getHeight()/2)) &&
                ((getX()- getWidth()/2) < (other.getX() + other.getWidth()/2)) && ((getX() + getWidth()/2) > (other.getX() - other.getWidth()));
    }
    
    public boolean isSupportedBy(WorldElement other) {
        if(other instanceof Island)
            return true;
        else {
            ArrayList<WorldElement> touchingOther = other.getTouching();
            int thisIndex = touchingOther.indexOf(this);
            if(thisIndex > 0)
                touchingOther.remove(thisIndex);
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

    public Image getThumbnail() {
        String imagePath = Game.MENU_THUMBNAIL_LOCATION;
        imagePath += getClass().getName().toLowerCase() + ".png";
        return Game.readImage(imagePath);
    }
    
    public void hold() { held = true; }
    public void release() { held = false; }
    
    public Image getSprite() { return sprite; }
    public boolean isMovable() { return isMovable; }
    public boolean isStationary() { return isStationary; }
    
    public void setSprite(Image newSprite) {sprite = newSprite; }
    
    public void setBurning(boolean isBurning) {
        this.isBurning = isBurning;
        this.effect = Game.getEffectImage("fire",width,height);
    }
}
