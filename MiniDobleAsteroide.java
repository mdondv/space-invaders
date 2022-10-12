import java.awt.Color;
import java.awt.Graphics;

public class MiniDobleAsteroide extends MiniAsteroide {
    int SIZE_PIXEL = 1;

    MiniDobleAsteroide(int x, int y, int vx, int vy) {
        super(x, y, vx, vy);
    }

    void moure() {
        x -= vx;
        y -= vy;
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

