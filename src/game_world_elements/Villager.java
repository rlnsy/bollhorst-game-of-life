package src.game_world_elements;

import res.AudioPlayer;

public class Villager extends PhysicsElement
{
    private double playerMovement;
    private int stepsWalked;
    public Villager() {
        super(true);
        stepsWalked = 0;
    }
    public void behave() {
        gravitate();
        if(onGround())
            randomMove();
    }
    public boolean onGround() {
        for(WorldElement e : getTouching()){
            if(e instanceof Island)
                return true;
        }
        return false;
    }
    public void randomMove() {
        double randomNum = Math.random();
        int numMove = 0;
        if(randomNum < 0.990) {
            if(numMove%100 == 0)
               if((playerMovement == 1 && canMoveRight()) || (playerMovement == -1 && canMoveLeft())) {
                   if(stepsWalked % 25 == 0) {
                       AudioPlayer.playClip("step.wav");
                   }
                   setLocation((int)(getX() + playerMovement),getY());
                   stepsWalked++;
               }
        }
        else if(randomNum <= 0.993 && randomNum >= 0.990) {
            playerMovement = 1;
            numMove++;
        }
        else if(randomNum > 0.993 && randomNum <= 0.997) {
            playerMovement = 0;
            numMove++;
        }
        else if(randomNum > 0.997 && canMoveLeft()) {
            playerMovement = -1;
            numMove++;
        }
    }
    
    public void touchedElement(WorldElement other) {}
}