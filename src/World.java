package src;

import java.awt.*;
import java.util.ArrayList;
import java.io.IOException;
import java.awt.event.*;
import javax.swing.*;
import src.popmenu.PopUpListener;

public class World extends Scene {

    private ArrayList<WorldElement> elements, secondaryElements;
    private ElementMenu menu;
    private Inventory inventory;
    private WorldClickListener clickListener;
    private BollhorstController bollhorstControl;
    private WorldElement lastPlaced;

    public World(Game game) {
        super(Game.BACKGROUND_IMAGE_LOCATION + "islandworld.png",game);
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
                if(inventory.isVisible())
                    invOpener.setText("Close");
                else
                    invOpener.setText("Inventory");
            }
        });
        invOpener.setBackground(Game.GLOBAL_BUTTON_COLOR);
        add(invOpener);
        
        JButton clearAll = new JButton("Clear all");
        clearAll.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                reset();
            }
        });
        clearAll.setBackground(Game.GLOBAL_BUTTON_COLOR);
        add(clearAll);
        
        addIsland();
        addMouseMotionListener(clickListener);
    }
    
    public void createBollhorstListener(Bollhorst bollhorst) {
        bollhorstControl = new BollhorstController(bollhorst);
        addKeyListener(bollhorstControl);
        setFocusable(true);
    }
    
    public void reset() {
        removeElements(elements);
        removeElements(secondaryElements);
        addIsland();
    }
    
    private void addIsland() {
        Island island = new Island();
        addElement(455, 496, island);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        inventory.draw(g);
        for(WorldElement e : elements)
            e.draw(g);
        for(WorldElement e : secondaryElements)
            e.draw(g);
    }
    
    private void checkForDeadElements(ArrayList<WorldElement> elementList) {
        int i = 0;
        while(i < elementList.size()) {
            WorldElement e = elementList.get(i);
            if(!e.isVisible()) {
                elementList.remove(i);
            }
            else
                i++;
        }
    }
    
    private void removeElements(ArrayList<WorldElement> elementList) {
        int i = 0;
        while(elementList.size() > 0)
            elementList.remove(i);
    }
    
    public void addElement(int xPos, int yPos, WorldElement element){
        lastPlaced = element;
        element.setLocation(xPos, yPos);
        
        boolean canPlace = true;
        if(element instanceof PhysicsElement) {
            for(WorldElement e : elements) {
                if(element.isTouching(e))
                    canPlace = false;
            }
        }
        
        if(canPlace) {
            elements.add(element);
            element.setWorld(this);
        }
        
        if(element instanceof Bollhorst)
            createBollhorstListener((Bollhorst)element);
    }
    
    public void addSecondaryElement(int xPos, int yPos, WorldElement e){
        e.setLocation(xPos, yPos);
        secondaryElements.add(e);
        e.setWorld(this);
    }
    
    // refresh grpahics whenever action performed
    // uses timer created in game
    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);
        update();
    }
    
    public void update() {
        requestFocus();
        checkForDeadElements(elements);
        checkForDeadElements(secondaryElements);
        for(WorldElement e : elements)
            e.update();
        for(WorldElement e : secondaryElements)
            e.update();
    }
    
    public ArrayList<WorldElement> getElements() { return elements; }
    
    public ElementMenu getMenu() { return menu; }
    
    public WorldElement getLastPlaced() { return lastPlaced; }
    
    public void setMouseElement(int elementID) {
        clickListener.setMouseElement(elementID);
    }
}
