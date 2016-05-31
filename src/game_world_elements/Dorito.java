package src.game_world_elements;

public class Dorito extends PhysicsElement {
    public Dorito() {
        super(true);
    }
    
    public void behave() {
        gravitate();
    }
 }
