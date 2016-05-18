import javax.swing.*;
import java.awt.*;
import java.io.*;
import javax.imageio.ImageIO;
public class Villager extends WorldElement
{
    public Villager() {
        super("images/element_sprites/villager.png");
    }
    public void behave() {
        gravitate();
    }
    public Image getThumbnail() {
        try {
           return ImageIO.read(new File("images/toolbar_spites/dirt.png"));
       } catch (IOException e) {
           throw new RuntimeException(e);
       }    
    }
}