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
           return Game.readImage("images/toolbar_sprites/island.png");
    }
}
