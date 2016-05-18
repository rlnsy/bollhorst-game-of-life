import javax.swing.*;
import java.awt.*;
import java.io.*;
import javax.imageio.ImageIO;

public class Fire extends WorldElement
{
    private int age;
    public Fire() {
        super("images/element_sprites/fire.png",false);
        age = 0;
    }
    
    public void behave() {
        age++;
        if(age == 20)
            setLocation(10000,10000);
    }
    
    public Image getThumbnail() {
           return Game.readImage("images/toolbar_sprites/fire.png");
    }
}