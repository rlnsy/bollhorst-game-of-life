public class Storm extends ProductionElement {
    public Storm() {
        super();
    }
    public void behave() {
        setLocation(getX()+1,getY());
        int dropSpot = (int)(Math.random()*getWidth()) + getX() - (getWidth()/2);
        getWorld().addSecondaryElement(dropSpot,getY()+(getHeight()/2),new Water());
    }
}
