import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.imageio.ImageIO;

public class Scene extends JPanel implements ActionListener{

    private Image backgroundImage;
    private int width,height;

    public Scene(String imagePath) throws IOException {
        super();
        backgroundImage = ImageIO.read(new File(imagePath));
        width = (int)backgroundImage.getWidth(null);
        height = (int)backgroundImage.getHeight(null);
    }

    public void init() {
        int width = backgroundImage.getWidth(null);   
        int height = backgroundImage.getHeight(null);
        setPreferredSize(new Dimension(width,height));
        new Timer(1,this).start(); // Timer for repainting
    }
    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, this);
    }

    // refresh grpahics whenever action performed
    // uses timer created in game
    public void actionPerformed(ActionEvent e) {
        repaint();
    }
    
    public int getWidth() { return width; }
    
    public int getHeight() { return height; }
}
