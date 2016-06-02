package src;

import javax.swing.*;
import java.awt.event.*;
import res.AudioPlayer;

public class InventoryButton extends JButton {
    private final int CLICK_ELEMENT_ID;
    //single ID pertains to the element selected by pressing this button
    private final World WORLD;
    
    /*
     * pre: none
     * post: constructs a new InventoryButton with the given elementID and linked to world
     */
    public InventoryButton(int elementID, World world) {
        CLICK_ELEMENT_ID = elementID;
        WORLD = world;
        addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AudioPlayer.playClip("buttonpush.wav");
                if(WORLD.getMenu().hasUnlocked(WORLD.getMenu().getElement(CLICK_ELEMENT_ID)))
                    WORLD.setMouseElement(CLICK_ELEMENT_ID);
            }
        });
    }
}
