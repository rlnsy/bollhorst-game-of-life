import java.awt.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import javax.imageio.ImageIO;
import java.util.ArrayList;

public class Water extends Liquid {
    public Water () {
        super("images/element_sprites/water.png");
    }
    
    public Image getThumbnail() {
        try {
           return ImageIO.read(new File("images/toolbar_spites/water.png"));
       } catch (IOException e) {
           throw new RuntimeException(e);
       }    
    }
}