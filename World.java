import java.awt.*;
import java.util.ArrayList;
import java.io.IOException;
import java.awt.event.*;
import javax.swing.*;

public class World extends Scene {

    private ArrayList<WorldElement> elements;
    private ElementMenu menu;
    private Inventory inventory;
    private WorldClickListener clickListener;

    public World() throws IOException {
        super("images/Blue back.png");
        elements = new ArrayList<WorldElement>();
        menu = new ElementMenu();
        inventory = new Inventory(this);
        init();
    }
    
    public void init() {
        super.init();
        addMouseListener(new PopUpListener(this));
        clickListener = new WorldClickListener(this);
        
        clickListener.changeCurrentElement(1); // DEFAULT ITEM
        
        addMouseListener(clickListener);
        JButton invButton = new JButton("Inventory");
        invButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                inventory.changeVisibility();
            }
        });
        add(invButton);
        addMouseMotionListener(clickListener);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        inventory.draw(g);
        for(WorldElement e : elements) {
            e.draw(g);
        }
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
    
    public void addElement(int xPos, int yPos, WorldElement e){
        e.setLocation(xPos, yPos);
        elements.add(e);
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
        //System.out.println(elements.size());
    }
    
    public void update() {
        for(WorldElement e : elements)
            e.update();
    }
    
    public ArrayList<WorldElement> getElements() { return elements; }
    
    public ElementMenu getMenu() { return menu; }
}
