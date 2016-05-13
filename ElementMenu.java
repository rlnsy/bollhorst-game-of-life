import java.util.ArrayList;
public class ElementMenu //TODO: remove default values/returns
{
    private int numItems = 2;
    
    public ElementMenuItem getMenuItem(int index) {
        ElementMenuItem item;
        switch(index) {
            case 0 :
            item = new ElementMenuItem("Water",0,new Water());
            break;
            case 1 :
            item = new ElementMenuItem("Dirt",1,new Dirt());
            break;
            default :
            item = new ElementMenuItem("Dorito",0,new Dorito());
            break;
        }
        return item;
    }
    
    public WorldElement getElement(int index) {
        WorldElement element;
        switch(index) {
            case 0 :
            element = new Water();
            break;
            case 1 :
            element = new Dirt();
            break;
            default :
            element = new Dorito();
            break;
        }
        return element;
    }
    
    public int getNumItems() { return numItems; }
}
