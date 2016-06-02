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
    private ElementMenu menu;
    private Inventory inventory;
    private WorldClickListener clickListener;
    private BollhorstController bollhorstControl;
    private WorldElement lastPlaced;

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
    
    private JButton createInventoryOpener() {
        JButton invOpener = new JButton("Inventory");
        invOpener.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AudioPlayer.playClip("buttonpush.wav");
                inventory.changeVisibility();
                if(inventory.isVisible())
                    invOpener.setText("Close");
                else
                    invOpener.setText("Inventory");
            }
        });
        invOpener.setBackground(new Color(123,214,248));
        return invOpener;
    }
    
    public void addBollhorstListener(Bollhorst boll) {
        bollhorstControl = new BollhorstController(boll);
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
        addElement(new Point(455, 496), island);
    }

    @Override
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
        
        if(canPlace) {
            element.playSound();
            elements.add(element);
            element.setWorld(this);
        }
        
        if(element instanceof Bollhorst)
            addBollhorstListener((Bollhorst)element);
    }
    
    public void addSecondaryElement(Point location, WorldElement e){
        e.setLocation(location);
        secondaryElements.add(e);
        e.setWorld(this);
    }
    
    @Override
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
    
    public void setMouseElement(int elementID) {
        clickListener.setMouseElement(elementID);
    }
    
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
