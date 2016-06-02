package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import res.ImageReader;

public class Scene extends JPanel implements ActionListener {

    private Image backgroundImage;
    private int width,height;
    private Game game;

    /*
     * pre: imagePath points to a valid image in the resource directory
     * post: constructs a new Scene
     */
    public Scene(String imagePath,Game game) {
        super();
        this.game = game;
        backgroundImage = ImageReader.readImage(imagePath);
        width = (int)backgroundImage.getWidth(null);
        height = (int)backgroundImage.getHeight(null);
        //get width/ height from image
    }

    /*
     * pre: width and height initialized
     * post: sets up the scene
     */
    public void init() {
        setPreferredSize(new Dimension(width,height));
        new Timer(20,this).start();
        /*
         * add a timer that runs throughout the entire game, triggering actionPerformed 
         * method and updating the scene
         */
    }
    
    /*
     * pre: backgroundImage is initialized
     * post: draws the background image to the panel
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, this);
    }

    /*
     * [activated by game timer]
     * pre: none
     * post: repaints this panel 
     */
    public void actionPerformed(ActionEvent e) {
        repaint();
    }
    
    public int getWidth() { return width; }
    
    public int getHeight() { return height; }
    
    public Game getGame() { return game; }
}
