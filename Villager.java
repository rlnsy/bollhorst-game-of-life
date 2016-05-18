public class Villager extends WorldElement
{
    public Villager() {
        super("images/element_sprites/villager.png");
    }
    public void behave() {
        gravitate();
    }
}