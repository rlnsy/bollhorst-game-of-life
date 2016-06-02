package src.game_world_elements;

public class Tree extends PhysicsElement
{
    public Tree() {
        super();
    }
    public void behave() {
        super.behave();
        if(isStationary() && !isTouching(getWorld().getIsland()) && !isHeld()) {
            changeVisibility(false);
        }
    }
    
    public void touchedElement(WorldElement other) {}
}
