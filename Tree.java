public class Tree extends PhysicsElement
{
    public Tree() {
        super(true);
        getPhysics().setYAcceleration(1);
    }
    public void behave() {
        if(isStationary() && !isTouchingIsland() && !isHeld()) {
            changeVisibility(false);
        }
        gravitate();
    }
}
