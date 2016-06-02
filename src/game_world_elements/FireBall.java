package src.game_world_elements;

public class FireBall extends PhysicsElement
{
    public void behave() {
        super.behave();
        if(getXVelocity() == 0 || getYVelocity() == 0)
        if(getTouching().size() > 0)
            removeFromWorld();
    }
    
    @Override
    public void accelerate() {}
    
    public void touchedElement(WorldElement other) {
        if(new Fire().checkIfFlammable(other))
                if(!other.isBurning())
                    other.setBurning(true);
    }
}
