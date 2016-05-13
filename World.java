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
        
        clickListener.changeCurrentElement(0);
        
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
    
    public void addElement(int xPos, int yPos, WorldElement e){
        e.setLocation(xPos, yPos);
        elements.add(e);
        e.setWorld(this);
    }
    
    // refresh grpahics whenever action performed
    // uses timer created in game
    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);
        update();
    }
    
    public void update() {
        for(WorldElement e : elements)
            e.update();
    }
    
    public ArrayList<WorldElement> getElements() { return elements; }
    
    public ElementMenu getMenu() { return menu; }
}
