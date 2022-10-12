import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class Fons {
    int x, y, v;

    Fons(int x, int y, int v) {
        this.x = x;
        this.y = y;
        this.v = v;
    }

    void moure() {
        x -= v;
    }

    public void pintaImatge(Graphics g) {
        Toolkit t = Toolkit.getDefaultToolkit();
        Image i1= t.getImage("montana1.png");
        Image i2= t.getImage("montana2.png");
        g.drawImage(i1, x, 650, null);
        g.drawImage(i2, x+478, 650, null);
        g.drawImage(i1, x+956, 650, null);
        g.drawImage(i2, x+1435, 650, null);
        g.drawImage(i1, x+1912, 650, null);
        g.drawImage(i2, x+2392, 650, null);
        g.drawImage(i1, x+2870, 650, null);
        g.drawImage(i2, x+3348, 650, null);
        g.drawImage(i1, x+3827, 650, null);
        if (Math.abs(this.x) % 2870 == 0) x = 0;
    }
}

