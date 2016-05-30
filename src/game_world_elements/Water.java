package src.game_world_elements;

public class Water extends Liquid {
    public Water () {
        super(true);
    }
    
    public void behave() {
        super.behave();
        for(WorldElement e : getTouching()) {
            if(e.isBurning())
                e.setBurning(false);
        }
    }
}