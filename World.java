/**
 * Created by rowli on 5/7/2016.
 */
import java.awt.*;
import java.util.ArrayList;
import java.io.IOException;

public class World extends Scene {

    private ArrayList<WorldElement> elements;

    public World(String imagePath) throws IOException {
        super(imagePath);
        elements = new ArrayList<WorldElement>();
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        for(WorldElement e : elements)
        {
            e.draw(g);
        }
    }

    public void addElement(WorldElement e){
        elements.add(e);
    }
}
