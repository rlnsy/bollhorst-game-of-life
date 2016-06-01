package src.game_world_elements;

public class Dorito extends PhysicsElement {
    public Dorito() {
        super();
        setMovable(true);
    }
    
    public void behave() {
        gravitate();
    }
    
    public void touchedElement(WorldElement other) {
        
    }
 }
