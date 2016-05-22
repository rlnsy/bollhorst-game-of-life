import javax.swing.*;
import java.awt.*;
import java.io.*;
import javax.imageio.ImageIO;
public class Dirt extends PhysicsElement {
    public Dirt () {
        super(true);
    }
    
    public void behave() {
        gravitate();
    }
}