import java.awt.event.*;
public class Bollhorst extends PhysicsElement implements KeyListener
{
    public Bollhorst()
    {
        super(true);
    }
    public void behave()
    {
        gravitate();
    }
    public void keyPressed(KeyEvent e)
    {
        /*int keyCode = e.getKeyCode();
        switch(keyCode) {
            case KeyEvent.VK_A:
                Bollhorst.setLocation((Bollhorst.getX()-10), (Bollhorst.getY()-10));
                break;
            case KeyEvent.VK_D:
            
                break;
            case KeyEvent.VK_W:
            
                break;
            case KeyEvent.VK_S:
            
                break;
        }
        */
    }
    public void keyReleased(KeyEvent e) {}
    public void keyTyped(KeyEvent e) {}
}