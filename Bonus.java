import java.awt.Color;
import java.awt.Graphics;

public abstract class Bonus {
    int x, y, vx, vy, SIZE_PIXEL = 2;
    Color LIGHT_ORANGE = new Color(255, 153, 0);
    Color DARK_RED = new Color(204, 0, 0);
    Color BROWN = new Color(102, 51, 0);
    Color LIGHT_GREY = new Color(204, 204, 204);
    Color GREY = new Color(153, 153, 153);
    Color DARK_GREY = new Color(95, 95, 95);
    Color VERY_DARK_GREY = new Color(51, 51, 51);
    Color EVIL_DARK_GREY = new Color(45, 45, 45); 
    Color TRANSPARENT = new Color(0f, 0f, 0f, 0f);
    
    abstract void moure();
    abstract void pinta(Graphics g);
}

class BonusVida extends Bonus {

    BonusVida(int x, int y, int vx, int vy) {
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
    }

    void moure() {
        x -= vx;
        y -= vy;
    }

    int[][] colMat = {
        {-2,-2,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-2,-2},
        {-2,-1,-1, 0, 0, 0, 0,-1,-1,-1, 0, 0, 0, 0,-1,-1,-2},
        {-1,-1, 0, 1, 1, 1, 1, 0,-1, 0, 1, 1, 1, 1, 0,-1,-1}, 
        {-1, 0, 1, 1, 2, 2, 1, 1, 0, 1, 1, 1, 1, 1, 1, 0,-1},
        {-1, 0, 1, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0,-1},
        {-1, 0, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0,-1},
        {-1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0,-1},
        {-1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0,-1},
        {-1,-1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0,-1,-1},
        {-1,-1,-1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0,-1,-1,-1},
        {-1,-1,-1,-1, 0, 1, 1, 1, 1, 1, 1, 1, 0,-1,-1,-1,-1},
        {-1,-1,-1,-1,-1, 0, 1, 1, 1, 1, 1, 0,-1,-1,-1,-1,-1},
        {-1,-1,-1,-1,-1,-1, 0, 1, 1, 1, 0,-1,-1,-1,-1,-1,-1},
        {-1,-1,-1,-1,-1,-1,-1, 0, 1, 0,-1,-1,-1,-1,-1,-1,-1},
        {-2,-1,-1,-1,-1,-1,-1,-1, 0,-1,-1,-1,-1,-1,-1,-1,-2},
        {-2,-2,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-2,-2},
    };

    void pinta(Graphics g) {
        for (int i=0; i<16; i++) {
            for (int j=0; j<17; j++) {
                if (colMat[i][j] == -2) g.setColor(TRANSPARENT);
                if (colMat[i][j] == -1) g.setColor(LIGHT_GREY);
                if (colMat[i][j] == 0) g.setColor(Color.BLACK);
                if (colMat[i][j] == 1) g.setColor(Color.RED);
                if (colMat[i][j] == 2) g.setColor(Color.WHITE);
                g.drawRect(x+(SIZE_PIXEL)*j, y+(SIZE_PIXEL)*i, SIZE_PIXEL, SIZE_PIXEL);
                g.fillRect(x+(SIZE_PIXEL)*j, y+(SIZE_PIXEL)*i, SIZE_PIXEL, SIZE_PIXEL);
           }
        }
    }
}

class BonusMisil extends Bonus {

    BonusMisil(int x, int y, int vx, int vy) {
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
    }

    void moure() {
        x -= vx;
        y -= vy;
    }

    int[][] colMat = {
        {-2,-2,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-2,-2},
        {-2,-1,-1,-1,-1,-1,-1,-1,-1, 0, 0,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-2},
        {-1,-1,-1,-1,-1,-1,-1,-1,-1, 0, 2, 0, 0,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
        {-1,-1,-1,-1, 7, 7, 7,-1,-1, 0, 2, 2, 2, 0,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
        {-1,-1,-1, 7, 5, 6, 6, 7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,-1,-1,-1},
        {-1,-1, 7, 4, 4, 5, 6, 6, 0, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 1, 1, 1, 1, 1, 0,-1,-1},
        {-1, 7, 4, 4, 5, 5, 5, 6, 0, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 1, 1, 1, 1, 1, 1, 0,-1},
        {-1,-1, 7, 5, 5, 5, 6, 6, 0, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 1, 1, 1, 1, 1, 0,-1,-1},
        {-1,-1,-1, 7, 5, 6, 6, 7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,-1,-1,-1},
        {-1,-1,-1,-1, 7, 7, 7,-1,-1, 0, 2, 2, 2, 0,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
        {-1,-1,-1,-1,-1,-1,-1,-1,-1, 0, 2, 0, 0,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
        {-2,-1,-1,-1,-1,-1,-1,-1,-1, 0, 0,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-2},
        {-2,-2,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-2,-2},
    };

    void pinta(Graphics g) {
        for (int i=0; i<13; i++) {
            for (int j=0; j<29; j++) {
                if (colMat[i][j] == -2) g.setColor(TRANSPARENT);
                if (colMat[i][j] == -1) g.setColor(LIGHT_GREY);
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

class BonusMuerte extends Bonus {

    BonusMuerte(int x, int y, int vx, int vy) {
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
    }

    void moure() {
        x -= vx;
        y -= vy;
    }

    int[][] colMat = {
        {-2,-2,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-2,-2},
        {-2,-1,-1,-1,-1,-1, 1, 1, 1, 1, 1, 1, 1,-1,-1,-1,-1,-1,-2},
        {-1,-1,-1,-1,-1, 1, 1, 0, 1, 1, 1, 1, 1, 1,-1,-1,-1,-1,-1},
        {-1,-1,-1,-1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 2, 2,-1,-1,-1,-1},
        {-1,-1,-1,-1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 2, 2,-1,-1,-1,-1},
        {-1,-1,-1,-1, 2, 1, 1,-1, 1, 1, 1,-1, 1, 1, 2,-1,-1,-1,-1},
        {-1,-1,-1,-1, 2, 1,-1,-1,-1, 1,-1,-1,-1, 2, 2,-1,-1,-1,-1},
        {-1,-1, 1,-1, 1, 2,-1,-1, 2, 1, 1,-1,-1, 2, 2,-1, 1,-1,-1},
        {-1, 1, 1,-1,-1, 1, 1, 2, 1,-1, 1, 2, 2, 1,-1,-1, 1, 1,-1},
        {-1,-1,-1, 1,-1,-1,-1, 1, 1, 1, 1, 1,-1,-1,-1, 1,-1,-1,-1},
        {-1,-1,-1,-1,-1,-1,-1, 1,-1, 1,-1, 1,-1,-1,-1,-1,-1,-1,-1},
        {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
        {-1,-1,-1,-1,-1,-1, 1,-1, 1,-1, 1,-1, 1,-1,-1,-1,-1,-1,-1},
        {-1,-1,-1,-1,-1,-1, 1, 1, 1, 1, 1, 1, 1,-1,-1,-1,-1,-1,-1},
        {-1,-1,-1,-1, 1,-1,-1, 1, 1, 1, 1, 1,-1,-1, 1,-1,-1,-1,-1},
        {-1,-1, 1, 1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 1, 1,-1,-1},
        {-2,-1,-1, 1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 1,-1,-1,-2},
        {-2,-2,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-2,-2},
    };

    void pinta(Graphics g) {
        for (int i=0; i<18; i++) {
            for (int j=0; j<19; j++) {
                if (colMat[i][j] == -2) g.setColor(TRANSPARENT);
                if (colMat[i][j] == -1) g.setColor(LIGHT_GREY);
                if (colMat[i][j] == 0) g.setColor(DARK_GREY);
                if (colMat[i][j] == 1) g.setColor(VERY_DARK_GREY);
                if (colMat[i][j] == 2) g.setColor(Color.BLACK);
                g.drawRect(x+(SIZE_PIXEL)*j, y+(SIZE_PIXEL)*i, SIZE_PIXEL, SIZE_PIXEL);
                g.fillRect(x+(SIZE_PIXEL)*j, y+(SIZE_PIXEL)*i, SIZE_PIXEL, SIZE_PIXEL);
           }
        }
    }
}

class BonusFantasma extends Bonus {

    BonusFantasma(int x, int y, int vx, int vy) {
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
    }

    void moure() {
        x -= vx;
        y -= vy;
    }

    int[][] colMat = {
        {-2,-2,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-2,-2},
        {-2,-1,-1,-1,-1,-1,-1, 0, 0, 0, 0, 0,-1,-1,-1,-1,-1,-1,-1,-2},
        {-1,-1,-1,-1,-1, 0, 0, 1, 1, 1, 1, 1, 0, 0,-1,-1,-1,-1,-1,-1}, 
        {-1,-1,-1,-1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0,-1,-1,-1,-1,-1},
        {-1,-1,-1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0,-1,-1,-1,-1},
        {-1,-1,-1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0,-1,-1,-1,-1},
        {-1,-1, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 1, 1, 0,-1,-1,-1},
        {-1,-1, 0, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0,-1,-1,-1},
        {-1,-1, 0, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0,-1,-1,-1},
        {-1,-1, 0, 1, 0, 0, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 0,-1,-1,-1},
        {-1,-1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0,-1,-1,-1},
        {-1,-1, 0, 1, 1, 1, 1, 0, 0, 1, 1, 1, 0, 0, 1, 1, 0,-1,-1,-1},
        {-1, 0, 1, 0, 1, 1, 1, 0, 0, 1, 1, 0, 1, 1, 1, 1, 0,-1,-1,-1},
        {-1, 0, 1, 0, 0, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0,-1,-1},
        {-1,-1, 0,-1,-1, 0, 0, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 0,-1},
        {-1,-1,-1,-1,-1,-1,-1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 0,-1,-1},
        {-2,-1,-1,-1,-1,-1,-1,-1,-1,-1, 0, 0, 0, 0, 0, 0, 0,-1,-1,-2},
        {-2,-2,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-2,-2},
    };

    void pinta(Graphics g) {
        for (int i=0; i<18; i++) {
            for (int j=0; j<20; j++) {
                if (colMat[i][j] == -2) g.setColor(TRANSPARENT);
                if (colMat[i][j] == -1) g.setColor(LIGHT_GREY);
                if (colMat[i][j] == 0) g.setColor(Color.BLACK);
                if (colMat[i][j] == 1) g.setColor(Color.WHITE);
                g.drawRect(x+(SIZE_PIXEL)*j, y+(SIZE_PIXEL)*i, SIZE_PIXEL, SIZE_PIXEL);
                g.fillRect(x+(SIZE_PIXEL)*j, y+(SIZE_PIXEL)*i, SIZE_PIXEL, SIZE_PIXEL);
           }
        }
    }
}
