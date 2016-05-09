/**
 * Created by rowli on 5/7/2016.
 */
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ClickInterface extends MouseAdapter {

    public void mouseClicked(MouseEvent e) {
        System.out.println("Click!");
        int x = e.getX();
        int y = e.getY();
        System.out.println(x + ", " + y);
    }
}
