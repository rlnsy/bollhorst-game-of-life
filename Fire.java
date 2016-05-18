import javax.swing.*;
import java.awt.*;
import java.io.*;
import javax.imageio.ImageIO;

public class Fire extends WorldElement
{
    private int age;
    public Fire() {
        super("images/element_sprites/dirt.png",false);
        age = 0;
    }
    
    public void behave() {
        age++;
        if(age == 20) {
           try {
               setSprite(ImageIO.read(new File("images/element_sprites/water.png")));
           } catch (IOException e) {
               throw new RuntimeException(e);
           }  
        }
    }
    
    public Image getThumbnail() {
       try {
           return ImageIO.read(new File("images/toolbar_sprites/dirt.png"));
       } catch (IOException e) {
           throw new RuntimeException(e);
       }  
    }
}