package src.game_world_elements;

import res.ImageReader;

public class Fire extends NonPhysicsElement
{
    public static final String[] nonFlammables = {"Liquid","Island","Fire","Storm","Ash","Dorito","FireBall","Bollhorst"};
    
    private int age;
    private final int MAX_AGE = 10;
    
    public Fire() {
        super(); 
        age = 0;
        randomizeAppearence();
    }
    
    public void randomizeAppearence() {
        int fireVar = (int)(Math.random()*3) + 1;
        setSprite(ImageReader.readImage(ImageReader.getDefaultSpriteLocation() + "fire" + fireVar + ".png"));
    }
    
    public void behave() {
        age++;
        if(age == MAX_AGE)
            removeFromWorld();
    }
    
    public static boolean checkIfFlammable(WorldElement element) {
        String elementName = element.getClass().getSimpleName();
        for(int i = 0; i < nonFlammables.length; i++) {
            if(nonFlammables[i].equals(elementName))
                return false;
        }
        return true;
    }
    
    public void touchedElement(WorldElement other) {
        if(checkIfFlammable(other))
                if(!other.isBurning())
                    other.setBurning(true);
    }
}