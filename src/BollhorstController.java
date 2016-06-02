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
    
    /*
     * pre: none
     * post: constructs a new BollhorstController liked to bollhorst
     */
    public BollhorstController(Bollhorst bollhorst) {
        this.bollhorst = bollhorst;
    }

    /*
     * pre: none
     * post: reponds to key input from the user and activates certain methods in bollhorst
     */
    @Override
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
            case KeyEvent.VK_SPACE :
                bollhorst.spitFire();
        }
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
    }
}
