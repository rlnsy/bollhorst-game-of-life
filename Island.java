import javax.swing.*;
import java.awt.*;
import java.io.*;
import javax.imageio.ImageIO;
public class Island extends WorldElement {
    public Island () {
        super("images/element_sprites/island.png",false);
    }
    
    public void behave() {}
    
    public Image getThumbnail() {
        try {
           return ImageIO.read(new File("images/toolbar_spites/island.png"));
       } catch (IOException e) {
           throw new RuntimeException(e);
       }    
    }
}
