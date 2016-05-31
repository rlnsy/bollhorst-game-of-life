package src.game_world_elements;

import res.ImageReader;

public class Fire extends NonPhysicsElement
{
    public static final String[] nonFlammables = {"Liquid","Island","Fire","Storm","Ash","Dorito"};
    
    private int age;
    private final int MAX_AGE = 10;
    public Fire() {
        super(false);
        int fireVar = (int)(Math.random()*3) + 1;
        setSprite(ImageReader.readImage(ImageReader.getDefaultSpriteLocation() + "fire" + fireVar + ".png"));
        age = 0;
    }
    
    public void behave() {
        age++;
        if(age == MAX_AGE)
            setLocation(10000,10000);
        for(WorldElement e : getTouching()) {
            if(checkIfFlammable(e))
                if(!e.isBurning())
                    e.setBurning(true);
        }
    }
    
    public static boolean checkIfFlammable(WorldElement element) {
        String elementName = element.getClass().getSimpleName();
        for(int i = 0; i < nonFlammables.length; i++) {
            if(nonFlammables[i].equals(elementName))
                return false;
        }
        return true;
    }
}