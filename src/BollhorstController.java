package src;

import java.awt.event.*;
import src.game_world_elements.Bollhorst;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.KeyStroke;
import javax.swing.Action;
import javax.swing.AbstractAction;

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
                bollhorst.changeXVelocity(-10);
            break;
            case KeyEvent.VK_RIGHT :
                bollhorst.changeXVelocity(10);
            break;
            case KeyEvent.VK_UP :
                bollhorst.changeYVelocity(10);
            break;
            case KeyEvent.VK_DOWN :
                bollhorst.changeYVelocity(-10);
            break;
        }
    }
    public void keyTyped(KeyEvent e) {
    }
    public void keyReleased(KeyEvent e) {
    }
}
