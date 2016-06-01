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
                bollhorst.addLeftVelocity();
            break;
            case KeyEvent.VK_RIGHT :
                bollhorst.addRightVelocity();
            break;
            case KeyEvent.VK_UP :
                bollhorst.addUpVelocity();
        }
    }
    public void keyTyped(KeyEvent e) {
    }
    public void keyReleased(KeyEvent e) {
    }
}
