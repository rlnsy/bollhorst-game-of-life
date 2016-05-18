import javax.swing.*;
import java.awt.*;
import java.io.*;
import javax.imageio.ImageIO;
public class Dorito extends WorldElement {
    public Dorito() {
        super("images/element_sprites/dorito.png",true);
    }
    
    public void behave() {
    }
    
    public Image getThumbnail() {
        try {
           return ImageIO.read(new File("images/toolbar_spites/dorito.png"));
       } catch (IOException e) {
           throw new RuntimeException(e);
       }    
    }
 }
