import java.awt.Color;
import java.awt.Graphics;

public class Misil {
    int x, y, v, SIZE_PIXEL = 1;
    Color LIGHT_GREY = new Color(204, 204, 204);
    Color LIGHT_ORANGE = new Color(255, 153, 0);
    Color DARK_RED = new Color(204, 0, 0);
    Color BROWN = new Color(102, 51, 0);
    Color DARK_GREY = new Color(153, 153, 153);
    Color TRANSPARENT = new Color(0f, 0f, 0f, 0f);

    Misil(int x, int y, int v) {
        this.x = x;
        this.y = y;
        this.v = v;
    }

    void moure() {
        x += v;
    }

    int[][] colMat = {
        {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-2,-2},
        { 0, 0,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-2},
        { 0, 2, 0, 0,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
        { 0, 2, 2, 2, 0,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
        { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,-1,-1,-1},
        { 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 1, 1, 1, 1, 1, 0,-1,-1},
        { 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 1, 1, 1, 1, 1, 1, 0,-1},
        { 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 1, 1, 1, 1, 1, 0,-1,-1},
        { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,-1,-1,-1},
        { 0, 2, 2, 2, 0,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
        { 0, 2, 0, 0,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
        { 0, 0,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-2},
    };

    int[][] propMat = {
        {-1,-1,-1, 1, 2, 2,-1},
        {-1,-1, 0, 0, 1, 2, 2},
        {-1, 0, 0, 1, 1, 2, 2},
        {-1,-1, 0, 0, 1, 2, 2},
        {-1,-1,-1, 1, 2, 2,-1},
    };

    void propulsio(Graphics g) {
        for (int i=0; i<5; i++) {
            for (int j=0; j<7; j++) {
                if (propMat[i][j] == -1) g.setColor(TRANSPARENT);
                if (propMat[i][j] == 0) g.setColor(Color.YELLOW);
                if (propMat[i][j] == 1) g.setColor(Color.ORANGE);
                if (propMat[i][j] == 2) g.setColor(LIGHT_ORANGE);
                if (propMat[i][j] == 3) g.setColor(Color.BLACK);
                g.drawRect(this.x+(SIZE_PIXEL)*j-8, y+(SIZE_PIXEL)*i+3, SIZE_PIXEL, SIZE_PIXEL);
                g.fillRect(this.x+(SIZE_PIXEL)*j-8, y+(SIZE_PIXEL)*i+3, SIZE_PIXEL, SIZE_PIXEL);
                if (propMat[i][j] != -1 && propMat[i][j] != 3) propMat[i][j] = (propMat[i][j]+1) % 3; 
           }
        }
    };

    void pinta(Graphics g) {
        for (int i=0; i<12; i++) {
            for (int j=0; j<20; j++) {
                if (colMat[i][j] == -1) g.setColor(TRANSPARENT);
                if (colMat[i][j] == 0) g.setColor(Color.BLACK);
                if (colMat[i][j] == 1) g.setColor(Color.RED);
                if (colMat[i][j] == 2) g.setColor(DARK_RED);
                if (colMat[i][j] == 3) g.setColor(Color.WHITE);
                if (colMat[i][j] == 4) g.setColor(Color.YELLOW);
                if (colMat[i][j] == 5) g.setColor(Color.ORANGE);
                if (colMat[i][j] == 6) g.setColor(LIGHT_ORANGE);
                if (colMat[i][j] == 7) g.setColor(BROWN);
                g.drawRect(x+(SIZE_PIXEL)*j, y+(SIZE_PIXEL)*i, SIZE_PIXEL, SIZE_PIXEL);
                g.fillRect(x+(SIZE_PIXEL)*j, y+(SIZE_PIXEL)*i, SIZE_PIXEL, SIZE_PIXEL);
           }
        }
    }
}
