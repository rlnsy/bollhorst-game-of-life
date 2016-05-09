import javax.swing.*;
import java.io.*;

public class Game extends JFrame{

    private World world;

    public static void main(String[] args) {
        new Game("Bollhorst's Game of Life").setVisible(true);
    }

    public Game(String header) {
        super(header);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1920, 1080);
        try {
            world = new World();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        getContentPane().add(world);
        pack();
    }
}