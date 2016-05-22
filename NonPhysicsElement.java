public class NonPhysicsElement extends WorldElement
{
    public NonPhysicsElement(boolean isMovable) {
        super(isMovable);
    }
    
    public void behave() {}
    
    public boolean canMoveRight() { return true; }
    public boolean canMoveLeft() { return true; }
}
