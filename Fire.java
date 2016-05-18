import javax.swing.*;
import java.awt.*;
import java.io.*;
import javax.imageio.ImageIO;

public class Fire extends WorldElement
{
    private int age;
    private final int MAX_AGE = 100;
    public Fire() {
        super("images/element_sprites/fire.png",false);
        age = 0;
    }
    
    public void behave() {
        age++;
        if(age == MAX_AGE)
            setLocation(10000,10000);
        for(WorldElement e : getTouching()) {
            if(!(e instanceof Liquid))
                e.burn();
        }
    }
    
    public Image getThumbnail() {
           return Game.readImage("images/toolbar_sprites/fire.png");
    }
}