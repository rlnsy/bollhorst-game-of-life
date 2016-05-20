public class Storm extends WorldElement {
    public Storm() {
        super(false);
    }
    public void behave() {
        setLocation(getX()+1,getY());
    }
}
