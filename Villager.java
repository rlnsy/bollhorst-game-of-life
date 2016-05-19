import javax.swing.*;
import java.awt.*;
import java.io.*;
import javax.imageio.ImageIO;
public class Villager extends WorldElement
{
    public Villager() {
        super(true);
    }
    public void behave() {
        gravitate();
        randomMove();
    }
}