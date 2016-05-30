import java.awt.event.KeyEvent;
import java.awt.*;
import java.util.*;

public class BollhorstListener
{
    public void keyPressed(KeyEvent e)
    {
        int keyCode = e.getKeyCode();
        switch(keyCode) {
            case KeyEvent.VK_A:
                Bollhorst.setLocation((getX()-10), getY());
            case KeyEvent.VK_LEFT:
                setLocation((getX()-10), getY());
                break;
            case KeyEvent.VK_D:
            case KeyEvent.VK_RIGHT:
            
                break;
            case KeyEvent.VK_W:
            case KeyEvent.VK_UP:
            
                break;
            case KeyEvent.VK_S:
            case KeyEvent.VK_DOWN:
            
                break;
        }
    }
    public void keyTyped(KeyEvent e)
    {
    }
    public void keyReleased(KeyEvent e)
    {
    }
}