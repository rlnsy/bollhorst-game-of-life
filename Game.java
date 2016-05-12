import javax.swing.*;
import java.io.*;
// hello
public class Game extends JFrame{
    
    private World world;
   
    public static void main(String[] args) {
        new Game("Bollhorst's Game of Life").setVisible(true);
    }

    public Game(String header) {
        super(header);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(910, 512);
        try {
            world = new World();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        getContentPane().add(world);
        pack();
    }
}