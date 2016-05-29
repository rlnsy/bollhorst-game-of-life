package src;

import java.awt.event.*;
import src.game_world_elements.Bollhorst;

public class BollhorstController implements KeyListener
{
    private Bollhorst bollhorst;
    
    public BollhorstController(Bollhorst bollhorst) {
        this.bollhorst = bollhorst;
    }
    //TODO: add moving functions
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()) {
            case KeyEvent.VK_LEFT :
                bollhorst.moveLeft();
            break;
            case KeyEvent.VK_RIGHT :
                bollhorst.moveRight();
            break;
        }
    }
    public void keyTyped(KeyEvent e) {
    }
    public void keyReleased(KeyEvent e) {
    }
}
