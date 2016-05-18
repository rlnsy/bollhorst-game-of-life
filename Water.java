import java.awt.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import javax.imageio.ImageIO;
import java.util.ArrayList;

public class Water extends Liquid {
    public Water () {
        super("images/element_sprites/water.png",true);
    }
    
    public Image getThumbnail() {
           return Game.readImage("images/toolbar_sprites/water.png");
    }
}