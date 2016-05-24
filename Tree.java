public class Tree extends PhysicsElement
{
    public Tree() {
        super(true);
    }
    public void behave() {
        if(isStationary()) {
            setVisible(false); // NOT WORKING FOR SOME REASON
        }
        gravitate();
    }
}
