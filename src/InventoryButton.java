package src;

import javax.swing.*;
import java.awt.event.*;
import res.AudioPlayer;

public class InventoryButton extends JButton {
    private final int CLICK_ELEMENT_ID;
    private final World WORLD;
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
