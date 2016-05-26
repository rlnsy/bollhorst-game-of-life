import javax.swing.*;
import java.io.*;
import java.awt.*;
import javax.imageio.ImageIO;
import java.awt.event.*;

public class Game extends JFrame {
    
    public static final String DEFAULT_SPRITE_LOCATION = "images/element_sprites/";
    public static final String MENU_THUMBNAIL_LOCATION = "images/toolbar_sprites/";
    public static final String BACKGROUND_IMAGE_LOCATION = "images/backgrounds/";
    public static final String EFFECT_SPRITE_LOCATION = "images/effect_sprites/";
    
    public static final Color GLOBAL_BUTTON_COLOR = Color.white;
    
    private final int WINDOW_WIDTH = 910;
    private final int  WINDOW_HEIGHT = 512;
    
    JPanel defaultPanel = new JPanel(new CardLayout());
    private Scene[] scenes;
   
    public static void main(String[] args) {
        new Game("Bollhorst's Game of Life").setVisible(true);
    }

    public Game(String header) {
        super(header);
        
        scenes = new Scene[2];
        
        scenes[0] = new StartMenu(this); 
        scenes[1] = new World(this);
        
        for(Scene scene : scenes)
            defaultPanel.add(scene,scene.getClass().getName());
        
        defaultPanel.setVisible(true);
        add(defaultPanel); 
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        pack();
        
        setLevel(0);
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
           System.out.println("Could not find suitable image for effect '"+ effectName +
           "' - size " + width + " x " + height);
           throw new RuntimeException(e);
        }    
    }
    
    public void setLevel(int levelID) {
        CardLayout cl = (CardLayout)(defaultPanel.getLayout());
        cl.show(defaultPanel,scenes[levelID].getClass().getName());
    }
}