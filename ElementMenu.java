import javax.swing.*;
import java.awt.*;
import java.io.*;
import javax.imageio.ImageIO;
import java.util.ArrayList;
public class ElementMenu //TODO: remove default values/returns
{
    private int numItems = 5;
    private String[] elementTypeList = {"Water","Dirt","Island","Villager","Fire"};
   
    public ElementMenuItem getMenuItem(int elementID) {
        WorldElement element = getElement(elementID);
        String menuLabel = element.getClass().getName();
        return new ElementMenuItem(menuLabel,elementID,element);
    }
    
    public WorldElement getElement(int elementID) {
        WorldElement newElement = new Dorito();
        try {
            newElement = (WorldElement) (Class.forName(elementTypeList[elementID]).newInstance());
        }
        catch(ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            System.out.println("Could not read class from menu");
        }
        return newElement;
    }
    
    public Image getElementThumbnail(int elementID) {
        return getElement(elementID).getThumbnail();
    }
    
    public int getNumItems() { return numItems; }
}
