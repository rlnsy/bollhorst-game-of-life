package src.game_world_elements;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import src.*;
import res.ImageReader;
import res.AudioPlayer;

public abstract class WorldElement extends JComponent {
    
    private World world;
    private Image sprite;
    private int xPos, yPos,width, height;
    private int burnedFor,maxBurnTime, age;
    private boolean isVisible,isMovable,isBurning,isHeld;
    private Image effect; //an additional image overlayed on top of element
    
    /*
     * pre: none
     * post: constructs a new WorldElement with default values
     */
    public WorldElement()
    {
       this.xPos = 0;
       this.yPos = 0;
       sprite = getDefaultSprite();
       width = sprite.getWidth(null);
       height = sprite.getHeight(null);
       this.isMovable = false;
       isVisible = true;
       isHeld = false;
       maxBurnTime = 250;
       age = 0;
    }
    
    /*
     * pre: a valid sprite image of correct name exists in resource directory
     * post: returns the default element sprite for this element based on its class
     */
    public Image getDefaultSprite() {
       String classType = getClass().getSimpleName().toLowerCase();
       String imagePath = ImageReader.getDefaultSpriteLocation() + classType + ".png";
       return ImageReader.readImage(imagePath);
    }
    
    /*
     * pre: image has been initialized
     * post: draws the element's image and effect(if applicable) at its location
     */
    public void draw(Graphics g) {
        if(isVisible) {
            g.drawImage(sprite, xPos-width/2, yPos-height/2,world);
            if(effect != null)
                g.drawImage(effect, xPos-width/2, yPos-height/2,world);
        }
    }
    
    /*
     * pre: none
     * post: makes required changes to this element 
     * and calls the touchedElement method with every touching element
     */
    public void update() {
        age++;
        if(!inBounds())
            isVisible  = false;
        if(isBurning)
            burnedFor++;
        else
            effect = null;
        if(burnedFor == maxBurnTime)
            burn();
        behave();
        for(WorldElement other : getTouching()) {
            touchedElement(other);
        }
    }
    
    public void playSound() {}
    
    /*
     * pre: this and other are in the same world
     * post: returns a +# if other is to the right, -# if to the left
     */
    public int getDirectionOf(WorldElement other) {
        return -1 * (getX() - other.getX());
    }
    
    /*
     * pre: none
     * post: returns true if this is directly below other
     */
    public boolean isUnder(WorldElement other) {
        return ((getY() - getHeight()/2) <= (other.getY() + other.getHeight()/2)) &&
                ((getX()- getWidth()/2) < (other.getX() + other.getWidth()/2)) && ((getX() + getWidth()/2) > (other.getX() - other.getWidth()));
    }
    
    /*
     * pre: none
     * post: returns true if and only if this element's hitbox intersects with that of e
     */
    public boolean isTouching(WorldElement e) {
        if(!equals(e)&&(getHitBox().intersects(e.getHitBox())))
            return true;
        return false;
    }
    
    /*
     * pre: none
     * post: returns a rectangle the width and height of the element placed at its top-left corner
     */
    public Rectangle getHitBox() {
        return new Rectangle(xPos-width/2,yPos-height/2,width,height);
    }
    
    /*
     * pre: none
     * post: returns a point at the center of the element
     */
    public Point getLocation() {
        return new Point(getX(),getY());
    }
    
    /*
     * pre: none
     * post: returns all the elements in world.getElements that are touching this
     */
    public ArrayList<WorldElement> getTouching() {
        ArrayList<WorldElement> touching = new ArrayList<WorldElement>();
        for(WorldElement e : world.getElements()) {
            if(isTouching(e))
                touching.add(e);
        }
        return touching;
    }
    
    /*
     * pre: none
     * post: returns true if and only if this object's parameters are within those of the world panel
     */
    public boolean inBounds() {
        boolean inBoundsX = xPos + width/2 < world.getWidth() && xPos - width/2 > 0;
        boolean inBoundsY = yPos + height/2 < world.getHeight() && yPos - height/2 > 0;
        return inBoundsX && inBoundsY;
    }
    
    /*
     * pre: none
     * post: sets this element's isHeld attribute to true and returns a
     * pointer to this element for other purposes
     */
    public WorldElement hold() { 
        isHeld = true; 
        return this;
    }
    
    /*
     * pre: none
     * post: sets this element's isHeld attribute to false
     */
    public void release() { isHeld = false; }
    
    /*
     * pre: world has been initialized
     * post: removes this element and leaves an Ash in its place
     */
    public void burn() {
        removeFromWorld();
        world.addSecondaryElement(new Point(getX(),getY()),new Ash());
    }
    
    /*
     * pre: none
     * post: effectively removes this element by making it invisible
     * (to be removed by a call to checkForDeadElements)
     */
    public void removeFromWorld() {
        isVisible = false;
    }
    
    /*
     * pre: none
     * post: sets this object's world attribute the given World
     */
    public void setWorld(World world) { 
        this.world = world; 
    }
    
    /*
     * pre: none
     * post: sets the element's x and positions to the corresponding given values,
     * or sets them to zero if given a negative value
     */
    public void setLocation(Point location) { 
        int xValue = (int)location.getX();
        int yValue = (int)location.getY();
        if(xValue < 0)
            xValue = 0;
        if(yValue < 0)
            yValue = 0;
        xPos = xValue;
        yPos = yValue;
    }
    
    /*
     * pre: this element has an existing effect image of the same size in the resource directory
     * post: plays the fire sound, sets this objects isBurning attribute to true,
     * and sets its effect to the fire effect
     */
    public void setBurning(boolean value) {
        if(value)
            AudioPlayer.playClip("fire.wav");
        this.isBurning = value;
        this.effect = ImageReader.getEffectImage("fire",width,height);
    }
    
    public void setSprite(Image newSprite) {sprite = newSprite; }
    
    public void setMovable(boolean movable) { isMovable = movable; }
    
    public void changeVisibility(boolean isVisible) { 
        this.isVisible = isVisible; 
    }
    
    public int getX() { return xPos; }
    
    public int getY() { return yPos; }
    
    public World getWorld() { return world; }
    
    @Override
    public int getWidth() { return width; }
    
    @Override
    public int getHeight() { return height; }
    
    public Image getSprite() { return sprite; }
    
    public int getAge() { return age; }
    
    public boolean isMovable() { return isMovable; }
    
    public boolean isVisible() { return isVisible; }
    
    public boolean isHeld() { return isHeld; }
    
    public boolean isBurning() { return isBurning; }
    
    public abstract void behave();
    
    public abstract boolean canMoveRight();
    
    public abstract boolean canMoveLeft();
    
    public abstract void touchedElement(WorldElement other);
}
