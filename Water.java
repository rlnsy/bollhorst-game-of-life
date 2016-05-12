public class Water extends WorldElement {
    public Water () {
        super("images/water.png");
    }
    
    public void update() {
        gravitate();
    }
}