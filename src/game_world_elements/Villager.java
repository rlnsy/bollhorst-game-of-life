package src.game_world_elements;

import res.AudioPlayer;

public class Villager extends PhysicsElement
{
    private double playerMovement;
    
    /*
     * pre: none
     * post: constructs a new Villager
     */
    public Villager() {
        super();
        setMovable(true);
    }
    
    /*
     * pre: none
     * post: moves randomly if in contact with the ground (island)
     */
    public void behave() {
        super.behave();
        if(onGround())
            randomMove();
    }
    
    /*
     * pre: none
     * post: returns true if touching the island
     */
    public boolean onGround() {
        for(WorldElement e : getTouching()){
            if(e instanceof Island)
                return true;
        }
        return false;
    }
    
    /*
     * pre: none
     * post: generates a random number and stops or starts movement depending 
     * on the numeber
     */
    public void randomMove() {
        double randomNum = Math.random();
        int newVel = 0;
        if(randomNum >= 0.98 && randomNum < 0.990)
            setXVelocity(0);    
        else if(randomNum <= 0.995 && randomNum >= 0.990)
            setXVelocity(1);
        else if(randomNum > 0.995)
            setXVelocity(-1);
    }
    
    public void touchedElement(WorldElement other) {}
}