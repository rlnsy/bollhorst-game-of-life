import javax.swing.*;
import java.awt.event.*;
public class StartMenu extends Scene {
    public StartMenu(Game game) {
        super(Game.BACKGROUND_IMAGE_LOCATION + "background2.png",game);
        super.init();
        
        JButton startButton = new JButton("Start");
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                getGame().setLevel(1);
            }
        });
        add(startButton);
    }
}