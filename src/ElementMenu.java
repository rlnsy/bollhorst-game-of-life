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
    
    /*
     * pre: none
     * post: constructs a new ElementMenu
     */
    public ElementMenu() {
        unlockedElements = new ArrayList<String>();
    }
    
    /*
     * pre: elementID is a valid ID correspoding to an element in the menu
     * post: returns a new Instsnce of the clas corresponding to elementID
     */
    public WorldElement getElement(int elementID) {
        WorldElement newElement = new Dorito();
        try {
            newElement = (WorldElement) (Class.forName(ELEMENT_PACKAGE + "." + elementList[elementID]).newInstance());
            //create a new instance of the element type from the string name
        }
        catch(ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            System.out.println("Could not read class from menu");
        }
        return newElement;
    }
    
    /*
     * pre: elementID is a valid ID correspoding to an element in the menu
     * post: returns the thumbnail in the resource directory used for the element
     * corresponding to elementID
     */
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
    
    /*
     * pre: none
     * post: returns true if the class type of element is present in the list of unlocked types
     */
    public boolean hasUnlocked(WorldElement element) {
        if(unlockedElements.indexOf(element.getClass().getSimpleName()) != -1)
            return true;
        else
            return false;
    }
    
    /*
     * pre: adds the element type name to the list of unlocked types
     */
    public void unlock(String elementName) {
        unlockedElements.add(elementName);
    }
    
    /*
     * pre: none
     * post: adds all element types ever defined in the menu to the list of unlocked types
     */
    public void unlockAll() {
        for(String type : elementList)
            unlock(type);
    }
    
    /*
     * pre none
     * post: returns the number of defined element types
     */
    public int getNumItems() { return elementList.length; }
    
    /*
     * pre: elementID is a valid ID correspoding to an element in the menu
     * post: returns a new Pickeritem for use in the popup menu
     */
    public ElementPickerItem getPickerItem(int elementID) {
        WorldElement element = getElement(elementID);
        String menuLabel = elementList[elementID];
        return new ElementPickerItem(menuLabel,elementID,element);
    }
}
