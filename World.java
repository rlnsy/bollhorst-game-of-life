import java.awt.*;
import java.util.ArrayList;
import java.io.IOException;
import java.awt.event.*;
import javax.swing.*;

public class World extends Scene {

    private ArrayList<WorldElement> elements;
    private ArrayList<WorldElement> secondaryElements;
    private ElementMenu menu;
    private Inventory inventory;
    private WorldClickListener clickListener;

    public World() throws IOException {
        super(Game.BACKGROUND_IMAGE_LOCATION + "Blue back.png");
        elements = new ArrayList<WorldElement>();
        secondaryElements = new ArrayList<WorldElement>();
        menu = new ElementMenu();
        inventory = new Inventory(this);
        init();
    }
    
    public void init() {
        super.init();
        addMouseListener(new PopUpListener(this));
        clickListener = new WorldClickListener(this);
        addMouseListener(clickListener);
        
        JButton invOpener = new JButton("Inventory");
        invOpener.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                inventory.changeVisibility();
            }
        });
        invOpener.setBackground(Color.white);
        add(invOpener);
        Island island = new Island();
        addElement(455, 490, island);
        addMouseMotionListener(clickListener);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        inventory.draw(g);
        for(WorldElement e : elements)
            e.draw(g);
        for(WorldElement e : secondaryElements)
            e.draw(g);
   }
    
    public void checkForDeadElements() {
        int i = 0;
        while(i < elements.size()) {
            WorldElement e = elements.get(i);
            if(!e.isVisible()) {
                removeElement(i);
            }
            else
                i++;
        }
    }
    
    public void addElement(int xPos, int yPos, WorldElement element){
        element.setLocation(xPos, yPos);
        boolean canPlace = true;
        if(!(element instanceof NonCollidable)) {
            for(WorldElement e : elements) {
                if(element.isTouching(e))
                    canPlace = false;
            }
        }
        if(canPlace) {
            elements.add(element);
            element.setWorld(this);
        }
    }
    
    public void addSecondaryElement(int xPos, int yPos, WorldElement e){
        e.setLocation(xPos, yPos);
        secondaryElements.add(e);
        e.setWorld(this);
    }
    
    public void removeElement(int index) {
        elements.remove(index);
    }
    
    // refresh grpahics whenever action performed
    // uses timer created in game
    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);
        checkForDeadElements();
        update();
    }
    
    public void update() {
        for(WorldElement e : elements)
            e.update();
        for(WorldElement e : secondaryElements)
            e.update();
    }
    
    public ArrayList<WorldElement> getElements() { return elements; }
    
    public ElementMenu getMenu() { return menu; }
    
    public void setMouseElement(int elementID) {
        clickListener.setMouseElement(elementID);
    }
}
