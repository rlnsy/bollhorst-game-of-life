public class Dirt extends WorldElement {
    public Dirt () {
        super("images/element_sprites/dirt.png");
    }
    
    public void behave() {
        gravitate();
    }
}