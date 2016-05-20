public class Wood extends WorldElement
{
    public Wood(){
        super(true);
    }
    public void behave(){
        gravitate();
    }
}