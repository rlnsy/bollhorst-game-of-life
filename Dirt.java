public class Dirt extends WorldElement {
    public Dirt () {
        super("images/dirt.png",true);
    }
    
    public void behave() {
        gravitate();
    }
}