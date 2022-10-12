import java.awt.Color;
import java.awt.Graphics;

public class Asteroide {
    int x, y, vx, vy, SIZE_PIXEL = 3;
    Color LIGHT_GREY = new Color(204, 204, 204);
    Color GREY = new Color(153, 153, 153);
    Color DARK_GREY = new Color(102, 102, 102);
    Color VERY_DARK_GREY = new Color(51, 51, 51); 
    Color TRANSPARENT =new Color(0f, 0f, 0f, 0f);

    Asteroide(int x, int y, int vx, int vy) {
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
    }

    void moure() {
        x -= vx;
        y -= vy;
    }

    boolean desintegracio(Nau jugador) {
        for (int k=0; k<jugador.bales.size(); k++) {
            if ((jugador.bales.get(k).x+4 >= this.x && jugador.x <= this.x && jugador.bales.get(k).y >= this.y && 
                    Math.abs(this.y-jugador.bales.get(k).y)< 16*this.SIZE_PIXEL) ||
                    (jugador.bales.get(k).x+4 <= this.x+16*this.SIZE_PIXEL && jugador.x >= this.x && jugador.bales.get(k).y >= this.y && 
                    Math.abs(this.y-jugador.bales.get(k).y)< 16*this.SIZE_PIXEL)) {
                jugador.bales.remove(k); 
                return true;
            }
        }
        return false;
    } 

    int[][] colMat = {
        {-1,-1,-1,-1, 2, 2, 2, 2, 2, 2, 2, 2, 2,-1,-1,-1,-1},
        {-1,-1,-1, 2, 2, 2, 2, 1, 1, 1, 2, 1, 0, 1,-1,-1,-1}, 
        {-1,-1, 2, 2, 2, 2, 1, 1, 1, 2, 1, 0, 1, 1, 1,-1,-1},
        {-1, 2, 2, 2, 2, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1,-1},
        { 2, 2, 3, 3, 2, 1, 1, 2, 1, 0, 1, 1, 2, 1, 1, 1, 1}, 
        { 2, 3, 3, 3, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, 
        { 2, 3, 3, 2, 2, 1, 1, 1, 2, 2, 1, 1, 1, 1, 1, 1, 1},
        { 2, 3, 3, 2, 2, 2, 1, 0, 1, 1, 2, 1, 1, 2, 1, 1, 1},
        { 2, 3, 3, 1, 2, 2, 1, 0, 1, 1, 2, 1, 1, 1, 1, 1, 1},
        { 2, 3, 3, 1, 2, 2, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1},
        { 2, 3, 3, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
        { 2, 3, 3, 3, 2, 1, 2, 2, 1, 1, 1, 2, 1, 1, 1, 1, 1},
        {-1, 2, 3, 3, 3, 2, 2, 2, 2, 1, 0, 1, 2, 1, 1, 1,-1},
        {-1,-1, 2, 3, 3, 3, 2, 1, 2, 1, 1, 2, 1, 1, 1,-1,-1}, 
        {-1,-1,-1, 2, 3, 3, 3, 2, 2, 2, 2, 2, 2, 2,-1,-1,-1},
        {-1,-1,-1,-1, 2, 2, 2, 2, 2, 2, 2, 2, 2,-1,-1,-1,-1},
    };

    void pinta(Graphics g) {
        for (int i=0; i<16; i++) {
            for (int j=0; j<17; j++) {
                if (colMat[i][j] == -1) g.setColor(TRANSPARENT);
                if (colMat[i][j] == 0) g.setColor(LIGHT_GREY);
                if (colMat[i][j] == 1) g.setColor(GREY);
                if (colMat[i][j] == 2) g.setColor(DARK_GREY);
                if (colMat[i][j] == 3) g.setColor(VERY_DARK_GREY);
                g.drawRect(x+(SIZE_PIXEL)*j, y+(SIZE_PIXEL)*i, SIZE_PIXEL, SIZE_PIXEL);
                g.fillRect(x+(SIZE_PIXEL)*j, y+(SIZE_PIXEL)*i, SIZE_PIXEL, SIZE_PIXEL);
           }
        }
    }
}
