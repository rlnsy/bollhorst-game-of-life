import javax.swing.*;
import java.awt.*;
import java.io.*;
import javax.imageio.ImageIO;
public class Villager extends WorldElement
{
    public Villager() {
        super("images/element_sprites/villager.png",true);
    }
    public void behave() {
        gravitate();
        randomMove();
    }
    public Image getThumbnail() {
           return Game.readImage("images/toolbar_sprites/villager.png");
    }
}