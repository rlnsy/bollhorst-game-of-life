package src.game_world_elements;

public class Water extends Liquid {
    public Water () {
        super();
    }
    
    public void behave() {
        super.behave();
    }
    
    /*
     * pre: none
     * post: if other is burning, sets it to stop burning
     */
    public void touchedElement(WorldElement other) {
        if(other.isBurning())
                other.setBurning(false);
    }
}