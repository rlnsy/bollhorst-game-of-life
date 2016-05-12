import java.awt.event.*;

public class WorldClickListener extends MouseAdapter {
    private World world;
    
    public WorldClickListener(World world) {
        this.world = world;
    }
    
    public void mouseClicked(MouseEvent e) {
        world.addElement(e.getX(),e.getY(),new Water());
    }
}
