import javax.swing.*;
import java.awt.*;
import java.io.*;
import javax.imageio.ImageIO;
public class Villager extends PhysicsElement
{
    public Villager() {
        super(true);
    }
    public void behave() {
        gravitate();
        if(onGround())
            randomMove();
    }
    public boolean onGround() {
        for(WorldElement e : getTouching()){
            if(e instanceof Island)
                return true;
        }
        return false;
    }
}