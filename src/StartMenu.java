package src;

import javax.swing.*;
import java.awt.event.*;
public class StartMenu extends World {
    public StartMenu(Game game) {
        //super(Game.BACKGROUND_IMAGE_LOCATION + "background2.png",game);
        super(game);
        super.init();
        
        JButton startButton = new JButton("Start");
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                reset();
                getGame().setLevel(1);
            }
        });
        startButton.setBackground(Game.GLOBAL_BUTTON_COLOR);
        setLayout(null);
        startButton.setBounds(405,231,100,50);
        add(startButton);
    }
}