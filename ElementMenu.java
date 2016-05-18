import javax.swing.*;
import java.awt.*;
import java.io.*;
import javax.imageio.ImageIO;
import java.util.ArrayList;
public class ElementMenu //TODO: remove default values/returns
{
    private int numItems = 5;
    
    public ElementMenuItem getMenuItem(int elementID) {
        ElementMenuItem item;
        switch(elementID) {
            case 0 :
            item = new ElementMenuItem("Water",0,new Water());
            break;
            case 1 :
            item = new ElementMenuItem("Dirt",1,new Dirt());
            break;
            case 2 :
            item = new ElementMenuItem("Island",2,new Island());
            break;
            case 3 :
            item = new ElementMenuItem("Villager",3,new Villager());
            break;
            default :
            item = new ElementMenuItem("Dorito",0,new Dorito());
            break;
        }
        return item;
    }
    
    public WorldElement getElement(int elementID) {
        WorldElement element;
        switch(elementID) {
            case 0 :
            element = new Water();
            break;
            case 1 :
            element = new Dirt();
            break;
            case 2 :
            element = new Island();
            break;
            case 3 :
            element = new Villager();
            break;
            default :
            element = new Dorito();
            break;
        }
        return element;
    }
    
    public Image getElementThumbnail(int elementID) {
        return getElement(elementID).getThumbnail();
    }
    
    public int getNumItems() { return numItems; }
}
