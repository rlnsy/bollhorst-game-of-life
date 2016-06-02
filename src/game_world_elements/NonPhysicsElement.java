package src.game_world_elements;

public abstract class NonPhysicsElement extends WorldElement
{
    //Element has very simple funcionality
    
    public void behave() {}
    
    public boolean canMoveRight() { return true; }
    
    public boolean canMoveLeft() { return true; }
}
