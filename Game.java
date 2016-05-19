import javax.swing.*;
import java.io.*;
import java.awt.*;
import javax.imageio.ImageIO;

public class Game extends JFrame {
    
    public static final String DEFAULT_SPRITE_LOCATION = "images/element_sprites/";
    public static final String MENU_THUMBNAIL_LOCATION = "images/toolbar_sprites/";
    public static final String BACKGROUND_IMAGE_LOCATION = "images/backgrounds/";
    public static final String EFFECT_SPRITE_LOCATION = "images/effect_sprites/";
    
    private final int WINDOW_WIDTH = 910;
    private final int  WINDOW_HEIGHT = 512;
    
    private World world;
   
    public static void main(String[] args) {
        new Game("Bollhorst's Game of Life").setVisible(true);
    }

    public Game(String header) {
        super(header);
        try {
            world = new World();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        getContentPane().add(world);
        pack();
    }
    
    public static Image readImage(String imagePath) {
        try {
           return ImageIO.read(new File(imagePath));
       } catch (IOException e) {
           throw new RuntimeException(e);
       }    
    }
    
    public static Image getEffectImage(String effectName, int width, int height) {
        String filePath = "images/effect_sprites/" + effectName + "_" + width + "x" + height + ".png";
        try {
           return ImageIO.read(new File(filePath));
        } catch (IOException e) {
           System.out.println("Could not find suitable effect image");
           throw new RuntimeException(e);
        }    
    }
}