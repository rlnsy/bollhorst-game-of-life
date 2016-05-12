import java.awt.*;
import java.util.ArrayList;
import java.io.IOException;
import java.awt.event.*;

public class World extends Scene {

    private ArrayList<WorldElement> elements;
    private ElementMenu menu;

    public World() throws IOException {
        super("images/Blue back.png");
        elements = new ArrayList<WorldElement>();
        menu = new ElementMenu();
        init();
    }
    
    public void init() {
        super.init();
        addMouseListener(new PopUpListener(this));
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for(WorldElement e : elements) {
            e.draw(g);
        }
    }
    
    public void addElement(WorldElement e){
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
