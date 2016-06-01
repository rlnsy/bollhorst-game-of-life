package src.game_world_elements;

public class Tree extends PhysicsElement
{
    public Tree() {
        super(false);
        getPhysics().setYAcceleration(1);
    }
    public void behave() {
        if(isStationary() && !isTouchingIsland() && !isHeld()) {
            changeVisibility(false);
        }
        gravitate();
    }
}
