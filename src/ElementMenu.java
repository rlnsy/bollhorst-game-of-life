package src;

import java.awt.*;
import src.popmenu.*;
import src.game_world_elements.WorldElement;
import src.game_world_elements.Dorito;
import res.ImageReader;

public class ElementMenu
{
    private final String ELEMENT_PACKAGE = "src.game_world_elements";
    private String[] elementTypeList = {"Water","Dirt","Villager","Fire","Storm","Tree", "Wood", "Bollhorst","Dorito"};
   
    public ElementPickerItem getPickerItem(int elementID) {
        WorldElement element = getElement(elementID);
        String menuLabel = elementTypeList[elementID];
        return new ElementPickerItem(menuLabel,elementID,element);
    }
    
    public WorldElement getElement(int elementID) {
        WorldElement newElement = new Dorito();
        try {
            newElement = (WorldElement) (Class.forName(ELEMENT_PACKAGE + "." + elementTypeList[elementID]).newInstance());
        }
        catch(ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            System.out.println("Could not read class from menu");
        }
        return newElement;
    }
    
    public Image getElementThumbnail(int elementID) {
        String simpleClassName = elementTypeList[elementID].toLowerCase();
        String imagePath = ImageReader.MENU_THUMBNAIL_LOCATION + simpleClassName + ".png";
        return ImageReader.readImage(imagePath);
    }
    
    public int getNumItems() { return elementTypeList.length; }
}
