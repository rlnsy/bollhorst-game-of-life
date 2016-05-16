public class Villager extends WorldElement
{
    public Villager() {
        super("images/villager.png");
    }
    public void behave() {
        gravitate();
    }
}
