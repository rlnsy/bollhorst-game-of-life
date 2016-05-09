import javax.swing.*;
import java.awt.event.*;
import java.io.*;

public class Game extends JFrame{

    private World world;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Game("Bollhorst's Game of Life").setVisible(true);
            }
        });
    }

    public Game(String header) {
        super(header);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1800, 900);

        try {
            world = new World("images/background.png");
            //setContentPane(world);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        getContentPane().add(world);

        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e){
                world.addElement(new Rock(world,0,0));
            }
        });

        pack();

        new Timer(20,world).start();
    }
}