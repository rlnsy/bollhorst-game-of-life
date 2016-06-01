package src.game_world_elements;

public class Ash extends PhysicsElement
{
    public Ash() {
        super();
        setMovable(true);
    }
    public void behave() {
        gravitate();
    }
    public void touchedElement(WorldElement other) {}
}
