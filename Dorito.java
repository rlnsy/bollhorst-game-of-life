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
           return Game.readImage("images/toolbar_sprites/dorito.png");
    }
 }
