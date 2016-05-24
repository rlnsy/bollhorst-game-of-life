public class Tree extends PhysicsElement
{
    public Tree() {
        super(true);
    }
    public void behave() {
        if(isStationary() && !isTouchingIsland() && !isHeld()) {
            changeVisibility(false);
        }
        gravitate();
    }
}
