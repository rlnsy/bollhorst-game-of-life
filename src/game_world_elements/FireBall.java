package src.game_world_elements;

public class FireBall extends PhysicsElement
{
    @Override
    /*
     * pre: none
     * post: element moves and disapears if touching another element
     */
    public void behave() {
        super.behave();
        if(getTouching().size() > 0)
            removeFromWorld();
    }
    
    @Override
    public void accelerate() {}
    
    @Override
    /*
     * pre: none
     * post: if other is flammable, sets other burning
     */
    public void touchedElement(WorldElement other) {
        if(new Fire().checkIfFlammable(other))
                if(!other.isBurning())
                    other.setBurning(true);
    }
}
