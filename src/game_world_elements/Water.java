package src.game_world_elements;

public class Water extends Liquid {
    public Water () {
        super();
    }
    
    public void behave() {
        super.behave();
    }
    
    public void touchedElement(WorldElement other) {
        if(other.isBurning())
                other.setBurning(false);
    }
}