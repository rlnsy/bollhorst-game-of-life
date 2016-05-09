/**
 * Created by rowli on 5/7/2016.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.imageio.ImageIO;

public class Scene extends JPanel implements ActionListener{

    private Image backgroundImage;

    public Scene() {}

    public Scene(String imagePath) throws IOException {
        super();
        backgroundImage = ImageIO.read(new File(imagePath));
        setPreferredSize(new Dimension(1800,900));
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, this);
    }

    public void actionPerformed(ActionEvent e) {
        repaint();
    }
}
