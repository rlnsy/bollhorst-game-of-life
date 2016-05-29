package src;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import javax.imageio.ImageIO;
import java.util.ArrayList;
import src.popmenu.*;
import src.game_world_elements.WorldElement;
import src.game_world_elements.Dorito;

public class ElementMenu
{
    private int numItems = 8;
    private String[] elementTypeList = {"Water","Dirt","Villager","Fire","Storm","Tree", "Wood", "Bollhorst"};
   
    public ElementPickerItem getPickerItem(int elementID) {
        WorldElement element = getElement(elementID);
        String menuLabel = elementTypeList[elementID];
        return new ElementPickerItem(menuLabel,elementID,element);
    }
    
    public WorldElement getElement(int elementID) {
        WorldElement newElement = new Dorito();
        try {
            newElement = (WorldElement) (Class.forName("src.game_world_elements."+elementTypeList[elementID]).newInstance());
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
