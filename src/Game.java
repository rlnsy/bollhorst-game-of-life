package src;

import javax.swing.*;
import java.io.*;
import java.awt.*;
import javax.imageio.ImageIO;
import java.awt.event.*;
import res.AudioPlayer;

public class Game extends JFrame {
    //game constants:
    public static final Color GLOBAL_BUTTON_COLOR = Color.white;
    private static final int DEFAULT_WINDOW_WIDTH = 910;
    private static final int  DEFAULT_WINDOW_HEIGHT = 512;
    
    JPanel defaultPanel; //contains the layout for all scenes
    private Scene[] scenes; //stores scenes
    
    /*
    * Pre: none
    * Post: creates and opens a new game window
    */
    public static void main(String[] args) {
        new Game("Bollhorst's Game of Life").setVisible(true);
    }

    /*
     * Pre: none
     * Post: new Game constructed with a panel containaing 
     * additional scene panels
     */
    public Game(String header) {
        super(header);
        
        scenes = new Scene[2];
        scenes[0] = new StartMenu(this); 
        scenes[1] = new World(this);
        //define game scenes
        
        defaultPanel = new JPanel(new CardLayout());
        for(Scene scene : scenes)
            defaultPanel.add(scene,scene.getClass().getName());
        defaultPanel.setVisible(true);
        add(defaultPanel);
        //fill and display default panel
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(DEFAULT_WINDOW_WIDTH, DEFAULT_WINDOW_HEIGHT);
        pack();
        
        setLevel(0);
        AudioPlayer.playClip("waves.wav");
    }  
    
    /*
     * Pre: levelID is a valid index of a scene in scenes
     * Post: switches default panel to display chosen scene
     */
    public void setLevel(int levelID) {
        CardLayout cl = (CardLayout)(defaultPanel.getLayout());
        cl.show(defaultPanel,scenes[levelID].getClass().getName());
        //display scene using card layout
    }
}