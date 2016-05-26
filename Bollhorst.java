import java.awt.event.*;
public class Bollhorst extends PhysicsElement {
    public Bollhorst()
    {
        super(true);
    }
    public void behave()
    {
        gravitate();
    }
}