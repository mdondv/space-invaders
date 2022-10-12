import java.awt.Color;
import java.awt.Graphics;

public abstract class Estrella {
    int x, y, v, SIZE_PIXEL = 1;
    Color LIGHT_GREY = new Color(204, 204, 204);
    Color GREY = new Color(153, 153, 153);
    Color DARK_GREY = new Color(102, 102, 102);
    Color VERY_DARK_GREY = new Color(51, 51, 51); 
    Color GOLD = new Color(255, 204, 51);
    Color TRANSPARENT =new Color(0f, 0f, 0f, 0f);

    abstract void moure();
    abstract void pinta(Graphics g);
}

class EstrellaTitilante extends Estrella { 
    
    EstrellaTitilante(int x, int y, int v) {
        this.x = x;
        this.y = y;
        this.v = v;
    }

    void moure() {
        x -= v;
    }

    int[][] colMat1 = {
        {-1,-1,-1,-1,-1,-1,-1,},
        {-1,-1,-1,-1,-1,-1,-1,},
        {-1,-1,-1, 0,-1,-1,-1,},
        {-1,-1,-1, 0,-1,-1,-1,},
        {-1, 0, 0, 0, 0, 0,-1,},
        {-1,-1, 0, 0, 0,-1,-1,},
        {-1,-1,-1, 0,-1,-1,-1,},
        {-1,-1,-1,-1,-1,-1,-1,},
        {-1,-1,-1,-1,-1,-1,-1,},
    };

    int[][] colMat2 = {
        {-1,-1,-1, 0,-1,-1,-1},
        {-1,-1,-1, 0,-1,-1,-1},
        {-1,-1,-1, 0,-1,-1,-1},
        {-1,-1, 0, 0, 0,-1,-1},
        { 0, 0, 0, 0, 0, 0, 0},
        {-1,-1, 0, 0, 0,-1,-1},
        {-1,-1,-1, 0,-1,-1,-1},
        {-1,-1,-1, 0,-1,-1,-1},
        {-1,-1,-1, 0,-1,-1,-1},
    };

    void pinta(Graphics g) {
        if (System.currentTimeMillis() % 2 == 0) {
            for (int i=0; i<9; i++) {
                for (int j=0; j<7; j++) {
                    if (colMat1[i][j] == -1) g.setColor(TRANSPARENT);
                    if (colMat1[i][j] == 0) g.setColor(LIGHT_GREY);
                    g.drawRect(x+(SIZE_PIXEL)*j, y+(SIZE_PIXEL)*i, SIZE_PIXEL, SIZE_PIXEL);
                    g.fillRect(x+(SIZE_PIXEL)*j, y+(SIZE_PIXEL)*i, SIZE_PIXEL, SIZE_PIXEL);
               }
            }
        }
        else {
            for (int i=0; i<9; i++) {
                for (int j=0; j<7; j++) {
                    if (colMat2[i][j] == -1) g.setColor(TRANSPARENT);
                    if (colMat2[i][j] == 0) g.setColor(LIGHT_GREY);
                    g.drawRect(x+(SIZE_PIXEL)*j, y+(SIZE_PIXEL)*i, SIZE_PIXEL, SIZE_PIXEL);
                    g.fillRect(x+(SIZE_PIXEL)*j, y+(SIZE_PIXEL)*i, SIZE_PIXEL, SIZE_PIXEL);
               }
            }
        }
    } 
}

class EstrellaBloc extends Estrella {

    EstrellaBloc(int x, int y, int v) {
        this.x = x;
        this.y = y;
        this.v = v;
    }

    void moure() {
        x -= v;
    }

    void pinta(Graphics g) {
        g.setColor(LIGHT_GREY);
        g.drawRect(x, y, 1, 1);
        g.fillRect(x, y, 1, 1);
    }
}

