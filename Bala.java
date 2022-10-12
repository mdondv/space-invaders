import java.awt.Color;
import java.awt.Graphics;

public class Bala {
    int x, y, v;
    Color GOLD = new Color(255, 204,51);

    Bala(int x, int y, int v) {
        this.x = x;
        this.y = y;
        this.v = v;
    }

    void moure() {
        x += v;
    }

    void pinta(Graphics g) {
        g.setColor(GOLD);
        g.drawRect(x, y, 4, 2);
        g.fillRect(x, y, 4, 2);
    }
}
