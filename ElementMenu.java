import java.util.ArrayList;
public class ElementMenu //TODO: remove default values/returns
{
    private int numItems = 1;
    
    public void addItem(String label, int position, WorldElement element) {
        //items.add(new ElementMenuItem(label, position, element));
    }
    
    public ElementMenuItem getItem(int index) { 
        return new ElementMenuItem("Dorito",0,new Dorito(0,0));
    }
    
    public int getNumItems() { return numItems; }
}
