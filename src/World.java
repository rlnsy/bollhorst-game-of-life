package src;

import java.awt.*;
import java.util.ArrayList;
import java.io.IOException;
import java.awt.event.*;
import javax.swing.*;
import src.popmenu.PopUpListener;
import src.game_world_elements.*;
import res.ImageReader;
import res.AudioPlayer;

public class World extends Scene {

    private ArrayList<WorldElement> elements, secondaryElements;
    /*  one list stores most of the elements added by the user, the second stores those
     *  added by other elements
     */
    private ElementMenu menu;
    private Inventory inventory;
    private WorldClickListener clickListener;
    private BollhorstController bollhorstControl;
    private WorldElement lastPlaced;
    private boolean hasBollhorst;

    /*
     * pre: islandworld background correctly placed in resource directory
     * post: constructs a new World and initializes attributes
     */
    public World(Game game) {
        super(ImageReader.getBackgroundLocation() + "islandworld.png",game);
        elements = new ArrayList<WorldElement>();
        secondaryElements = new ArrayList<WorldElement>();
        menu = new ElementMenu();
        menu.unlockAll();
        inventory = new Inventory(this);
        clickListener = new WorldClickListener(this);
        init();
    }
    
    /*
     * pre: none
     * post: adds essential listeners to the world as well as the 
     * first WorldElement, Island
     */
    @Override
    public void init() {
        super.init();
        addMouseListener(new PopUpListener(this));
        addMouseListener(clickListener);
        add(createInventoryOpener());
        add(createClearButton());
        addIsland();
        addMouseMotionListener(clickListener);
    }
    
    /*
     * pre: buttonpush audio file present in resource directory
     * post: creates and adds a new button labled "clear all" for
     * resetting the world
     */
    private JButton createClearButton() {
        JButton clearAll = new JButton("Clear all");
        clearAll.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AudioPlayer.playClip("buttonpush.wav");
                reset();
            }
        });
        clearAll.setBackground(new Color(228,28,36));
        return clearAll;
    }
    
    /*
     * pre: buttonpush audio file present in resource directory
     * post: adds a new button with ability to open and close the
     * element inventory
     */
    private JButton createInventoryOpener() {
        JButton invOpener = new JButton("Open/Close Inventory");
        invOpener.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AudioPlayer.playClip("buttonpush.wav");
                inventory.changeVisibility();
            }
        });
        invOpener.setBackground(new Color(123,214,248));
        return invOpener;
    }
    
    /*
     * pre: none
     * post: creates and adds a BollhorstController linked to the Bollhorst
     * 'boll' and makes the world focusable
     */
    public void addBollhorstListener(Bollhorst boll) {
        bollhorstControl = new BollhorstController(boll);
        addKeyListener(bollhorstControl);
        setFocusable(true);
    }
    
    /*
     * pre: none
     * post: removes all elements from the world, then restores the island
     */
    public void reset() {
        removeElements(elements);
        removeElements(secondaryElements);
        hasBollhorst = false;
        addIsland();
    }
    
    /*
     * pre: none
     * post: creates and adds a new Island at a certain location
     */
    private void addIsland() {
        Island island = new Island();
        addElement(new Point(455, 496), island);
    }

    /*
     * paints all objects contained within the panel, including the inventory,
     * elements, and secondary elements
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        inventory.draw(g);
        for(WorldElement e : elements)
            e.draw(g);
        for(WorldElement e : secondaryElements)
            e.draw(g);
    }
    
    /*
     * pre: none
     * post: removes all invisible elements from elementsList, notes
     * if a Bollhorst has been removed
     */
    private void checkForDeadElements(ArrayList<WorldElement> elementList) {
        int i = 0;
        while(i < elementList.size()) {
            WorldElement e = elementList.get(i);
            if(!e.isVisible()) {
                elementList.remove(i);
                if(e instanceof Bollhorst)
                    hasBollhorst = false;
            }
            else
                i++;
        }
    }
    
    /*
     * pre: none
     * post: removes all elements from elementList
     */
    private void removeElements(ArrayList<WorldElement> elementList) {
        int i = 0;
        while(elementList.size() > 0)
            elementList.remove(i);
    }
    
    /*
     * pre: location is a valid point within the world
     * post: adds element to the world's elements list, provided that it does not collide
     * with existing elements, and plays a sound if applicable
     */
    public void addElement(Point location, src.game_world_elements.WorldElement element){
        lastPlaced = element;
        element.setLocation(location);

        boolean canPlace = true;
        
        if(element instanceof PhysicsElement) {
            for(WorldElement e : elements) {
                if(element.isTouching(e))
                    canPlace = false;
            }
        }
        
        if(element instanceof Bollhorst) {
            canPlace = !hasBollhorst && canPlace;
            if(canPlace) {
                hasBollhorst = true;
                addBollhorstListener((Bollhorst)element);
            }
        }
        
        if(canPlace) {
            element.playSound();
            elements.add(element);
            element.setWorld(this);
        }
    }
    
    /*
     * pre: "
     * post: adds element to the addtional elements list
     */
    public void addSecondaryElement(Point location, WorldElement element){
        element.setLocation(location);
        secondaryElements.add(element);
        element.setWorld(this);
    }
    
    @Override
    /*
     * [activated by game timer]
     * pre: none
     * post: repaints the panel by calling super method and updates the world
     */
    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);
        update();
    }
    
    /*
     * pre: elements lists are properly initialized
     * post: checks for invisible elements and updates every element
     *       requests focus to make sure controller listeners are activated
     */
    public void update() {
        requestFocus();
        checkForDeadElements(elements);
        checkForDeadElements(secondaryElements);
        for(WorldElement e : elements)
            e.update();
        for(WorldElement e : secondaryElements)
            e.update();
    }
    
    /*
     * pre: elementID is a valid element ID corresponding to an element in the menu
     * post: sets the default placed element type to that corresponding to elementID
     */
    public void setMouseElement(int elementID) {
        clickListener.setMouseElement(elementID);
    }
    
    //===GET METHODS===
    public ArrayList<WorldElement> getElements() { return elements; }
    
    public ArrayList<WorldElement> getSecondaryElements() { return secondaryElements; }
    
    public ElementMenu getMenu() { return menu; }
    
    public Island getIsland() {
        if(getElements().get(0) instanceof Island)
            return (Island)(getElements().get(0));
        else
            return null;
    }
    
    public WorldElement getLastPlaced() { return lastPlaced; }
}
