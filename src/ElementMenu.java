package src;

import java.awt.*;
import src.popmenu.*;
import java.util.ArrayList;
import src.game_world_elements.WorldElement;
import src.game_world_elements.Dorito;
import res.ImageReader;

public class ElementMenu
{
    private final String ELEMENT_PACKAGE = "src.game_world_elements";
    private String[] elementList = {"Water","Dirt","Villager","Fire","Storm","Tree", "Wood", "Bollhorst","Dorito"};
    private ArrayList<String> unlockedElements;
    
    public ElementMenu() {
        unlockedElements = new ArrayList<String>();
    }
   
    public ElementPickerItem getPickerItem(int elementID) {
        WorldElement element = getElement(elementID);
        String menuLabel = elementList[elementID];
        return new ElementPickerItem(menuLabel,elementID,element);
    }
    
    public WorldElement getElement(int elementID) {
        WorldElement newElement = new Dorito();
        try {
            newElement = (WorldElement) (Class.forName(ELEMENT_PACKAGE + "." + elementList[elementID]).newInstance());
        }
        catch(ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            System.out.println("Could not read class from menu");
        }
        return newElement;
    }
    
    public Image getElementThumbnail(int elementID) {
        WorldElement element = getElement(elementID);
        if(hasUnlocked(element)) {
            String simpleClassName = elementList[elementID].toLowerCase();
            String imagePath = ImageReader.getThumbnailLocation() + simpleClassName + ".png";
            return ImageReader.readImage(imagePath);
        }
        else
            return ImageReader.readImage(ImageReader.getThumbnailLocation() + "locked.png");
    }
    
    public boolean hasUnlocked(WorldElement element) {
        if(unlockedElements.indexOf(element.getClass().getSimpleName()) != -1)
            return true;
        else
            return false;
    }
    
    public void unlock(String elementName) {
        unlockedElements.add(elementName);
    }
    
    public void unlockAll() {
        for(String type : elementList)
            unlock(type);
    }
    
    public int getNumItems() { return elementList.length; }
}
