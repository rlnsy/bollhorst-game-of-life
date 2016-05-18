import javax.swing.*;
import java.io.*;
import java.awt.*;
import javax.imageio.ImageIO;

public class Game extends JFrame {
    
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
}