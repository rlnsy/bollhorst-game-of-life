package src.game_world_elements;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import src.*;

public abstract class WorldElement extends JComponent {
    
    private World world;
    private Image sprite;
    private String imagePath;
    private int xPos, yPos,width, height;
    private boolean isVisible,isMovable,isBurning,held;
    private int burnedFor,maxBurnTime, age;
    private Image effect;
    
    public WorldElement(boolean isMovable)
    {
       this.xPos = 0;
       this.yPos = 0;
       String classType = getClass().getSimpleName().toLowerCase();
       String imagePath = ImageReader.DEFAULT_SPRITE_LOCATION + classType + ".png";
       sprite = ImageReader.readImage(imagePath);
       width = sprite.getWidth(null);
       height = sprite.getHeight(null);
       isMovable = isMovable;
       isVisible = true;
       held = false;
       maxBurnTime = 50;
       age = 0;
    }
    
    // **GRAPHICS**
    public void draw(Graphics g) {
        if(isVisible) {
            g.drawImage(sprite, xPos-width/2, yPos-height/2,world);
            if(effect != null)
                g.drawImage(effect, xPos-width/2, yPos-height/2,world);
        }
    }
    
    public void update() {
        age++;
        if(!inBounds())
            isVisible  = false;
        if(isBurning)
            burnedFor++;
        if(burnedFor == maxBurnTime)
            burn();
        behave();
    }
    
    // **OBJECTS/COLLISIONS**
    
    public abstract boolean canMoveRight();
    public abstract boolean canMoveLeft();
    
    // returns + if to right, - if to left
    public int getDirectionOf(WorldElement other) {
        return -1 * (getX() - other.getX());
    }
    
    public boolean nextTo(WorldElement other) {
        boolean bool = (getY() < other.getY() + other.getHeight()/2) && (getY() > other.getY() - other.getHeight()/2);
        return bool; 
    }
    
    public boolean isUnder(WorldElement other) {
        return ((getY() - getHeight()/2) <= (other.getY() + other.getHeight()/2)) &&
                ((getX()- getWidth()/2) < (other.getX() + other.getWidth()/2)) && ((getX() + getWidth()/2) > (other.getX() - other.getWidth()));
    }
    
    public boolean isTouching(WorldElement e) {
        if(!equals(e)&&(getHitBox().intersects(e.getHitBox())))
            return true;
        return false;
    }
    
    public Rectangle getHitBox() {
        return new Rectangle(xPos-width/2,yPos-height/2,width,height);
    }
    
    public Point getLocation() {
        return new Point(getX(),getY());
    }
    
    // TODO: make not slow
    
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
    
    // **SET**
    public void setSprite(Image newSprite) {sprite = newSprite; }
    
    public void changeVisibility(boolean isVisible) { 
        this.isVisible = isVisible; 
    }
    
    public void setWorld(World world) { 
        this.world = world; 
    }
    
    public void setLocation(int xValue, int yValue) { 
        xPos = xValue;
        yPos = yValue;
    }
    
    // **MODIFY**
    public void setBurning(boolean isBurning) {
        this.isBurning = isBurning;
        this.effect = Game.getEffectImage("fire",width,height);
    }
    
    public void hold() { held = true; }
    public void release() { held = false; }
    
    public void burn() {
        isVisible = false;
        world.addSecondaryElement(getX(),getY(),new Ash());
    }
    
    // **GET**
    public int getX() { return xPos; }
    public int getY() { return yPos; }
    public World getWorld() { return world; }
    public int getWidth() { return width; }
    public int getHeight() { return height; }
    public Image getSprite() { return sprite; }
    
    public int getAge() { return age; }
    public String getImagePath(){ return imagePath; }
    public boolean isMovable() { return isMovable; }
    public boolean isVisible() { return isVisible; }
    public boolean isHeld() { return held; }
    
    public boolean isTouchingIsland() {
        for(WorldElement e : getTouching()) {
            if(e instanceof Island)
                return true;
        }
        return false;
    }
    
    // **ABSTRACT**
    public abstract void behave();
}
