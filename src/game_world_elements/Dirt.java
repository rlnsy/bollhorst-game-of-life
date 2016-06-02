package src.game_world_elements;

public class Dirt extends PhysicsElement {
    public Dirt () {
        super();
        setMovable(true);
    }
    
    public void behave() {
        super.behave();
    }
    
    public void touchedElement(WorldElement other) {}
}