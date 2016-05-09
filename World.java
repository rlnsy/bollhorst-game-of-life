/**
 * Created by rowli on 5/7/2016.
 */
import java.awt.*;
import java.util.ArrayList;
import java.io.IOException;
import java.awt.event.*;

public class World extends Scene {

    private ArrayList<WorldElement> elements;

    public World() throws IOException {
        super("images/mlgbackground.jpg");
        elements = new ArrayList<WorldElement>();
        
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e){
                addElement(new Dorito(e.getX(),e.getY()));
            }
        });
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for(WorldElement e : elements)
            e.draw(g);
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
            e.doSomething();
    }
}
