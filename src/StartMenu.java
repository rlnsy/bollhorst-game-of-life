package src;

import javax.swing.*;
import java.awt.event.*;
import res.AudioPlayer;

public class StartMenu extends World {
    public StartMenu(Game game) {
        super(game);
        super.init();
        
        JButton startButton = new JButton("Start");
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AudioPlayer.playClip("buttonpush.wav");
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