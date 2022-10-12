import java.awt.Color;
import java.awt.Graphics;
import java.util.Calendar;
import java.awt.Image;
import java.awt.Toolkit;

public class NauEnemigaDisseny4 extends Nau {
    Toolkit t = Toolkit.getDefaultToolkit();
    Image image = t.getImage("explosion.png");

    NauEnemigaDisseny4(int x, int y, int vx) {
        this.x = x;
        this.y = y;  
        this.vx = vx;
    }

    void moure() {
        x -= vx;
    }
    
    void disparar() {
        bales.add(new Bala(x+38, y+36, -10));
        bales.add(new Bala(x+38, y+40, -15));
        bales.add(new Bala(x+38, y+44, -8));
        bales.add(new Bala(x+38, y+104, -8));
        bales.add(new Bala(x+38, y+108, -15));
        bales.add(new Bala(x+38, y+112, -10));
    }

    boolean alcanceBala(Nau enemic) {
        for (int k=0; k<enemic.bales.size(); k++) {
            if ((this.x+8 <= enemic.bales.get(k).x && this.x+27*4 >= enemic.bales.get(k).x &&
                this.y <= enemic.bales.get(k).y && this.y+12 >= enemic.bales.get(k).y
                &&  Math.abs(this.y-enemic.bales.get(k).y)<=12) ||
                (this.x+8 <= enemic.bales.get(k).x && this.x+27*4 >= enemic.bales.get(k).x &&
                this.y+140 <= enemic.bales.get(k).y && this.y+38*4 >= enemic.bales.get(k).y
                &&  Math.abs(this.y+140-enemic.bales.get(k).y)<=12) ||
                (this.x <= enemic.bales.get(k).x && this.x+27*4 >= enemic.bales.get(k).x &&
                this.y+60 <= enemic.bales.get(k).y && this.y+92 >= enemic.bales.get(k).y &&
                Math.abs(this.y+60-enemic.bales.get(k).y)<=32) ||
                (this.x+40 <= enemic.bales.get(k).x && this.x+27*4 >= enemic.bales.get(k).x &&
                this.y+12 <= enemic.bales.get(k).y && this.y+60 >= enemic.bales.get(k).y
                && Math.abs(this.y+12-enemic.bales.get(k).y)<=48) ||
                (this.x+40 <= enemic.bales.get(k).x && this.x+27*4 >= enemic.bales.get(k).x &&
                this.y+92 <= enemic.bales.get(k).y && this.y+140 >= enemic.bales.get(k).y
                && Math.abs(this.y+92-enemic.bales.get(k).y)<=48)) {
                enemic.bales.remove(k);
                return true;
            }
        }
        return false;
    }

    boolean alcanceMisil(Nau enemic) {
        for (int k=0; k<enemic.misil.size(); k++) {
            if ((this.x+8 <= enemic.misil.get(k).x && this.x+27*4 >= enemic.misil.get(k).x &&
                this.y <= enemic.misil.get(k).y && this.y+12 >= enemic.misil.get(k).y
                &&  Math.abs(this.y-enemic.misil.get(k).y)<=12) ||
                (this.x+8 <= enemic.misil.get(k).x && this.x+27*4 >= enemic.misil.get(k).x &&
                this.y+140 <= enemic.misil.get(k).y && this.y+38*4 >= enemic.misil.get(k).y
                &&  Math.abs(this.y+40-enemic.misil.get(k).y)<=12) ||
                (this.x <= enemic.misil.get(k).x && this.x+27*4 >= enemic.misil.get(k).x &&
                this.y+60 <= enemic.misil.get(k).y && this.y+92 >= enemic.misil.get(k).y &&
                Math.abs(this.y+60-enemic.misil.get(k).y)<=32) ||
                (this.x+40 <= enemic.misil.get(k).x && this.x+27*4 >= enemic.misil.get(k).x &&
                this.y+12 <= enemic.misil.get(k).y && this.y+60 >= enemic.misil.get(k).y
                && Math.abs(this.y+12-enemic.misil.get(k).y)<=48) ||
                (this.x+40 <= enemic.misil.get(k).x && this.x+27*4 >= enemic.misil.get(k).x &&
                this.y+92 <= enemic.misil.get(k).y && this.y+140 >= enemic.misil.get(k).y
                && Math.abs(this.y+92-enemic.misil.get(k).y)<=48)) {
                enemic.misil.remove(k);
                return true;
            }
        }
        return false;
    }

    int[][] propMat = {
        {-1, 3, 3, 3,-1,-1,-1},
        { 3, 2, 2, 1, 2,-1,-1},
        { 2, 2, 1, 0, 0, 3,-1},
        { 2, 1, 1, 1, 0, 0, 3},
        { 2, 2, 1, 1, 1, 3,-1},
        { 3, 2, 2, 1, 3,-1,-1},
        {-1, 3, 3, 3,-1,-1,-1},
    };

    void propulsio(Graphics g) {
        for (int i=0; i<7; i++) {
            for (int j=0; j<7; j++) {
                if (propMat[i][j] == -1) g.setColor(TRANSPARENT);
                if (propMat[i][j] == 0) g.setColor(Color.YELLOW);
                if (propMat[i][j] == 1) g.setColor(LIGHT_ORANGE);
                if (propMat[i][j] == 2) g.setColor(Color.ORANGE);
                if (propMat[i][j] == 3) g.setColor(GOLD);
                g.drawRect(this.x+2*j+84, y+2*i+13, 1, 1); g.fillRect(this.x+2*j+84, y+2*i+13, 1, 1);
                g.drawRect(this.x+2*j+84, y+2*i+125, 1, 1); g.fillRect(this.x+2*j+84, y+2*i+125, 1, 1);
                g.drawRect(this.x+2*j+104, y+2*i+57, 1, 1); g.fillRect(this.x+2*j+104, y+2*i+57, 1, 1);
                g.drawRect(this.x+2*j+104, y+2*i+81, 1, 1); g.fillRect(this.x+2*j+104, y+2*i+81, 1, 1);
                if (propMat[i][j] != -1) propMat[i][j] = (propMat[i][j]+1) % 3;
           }
        }
    }   

    void explosio(Graphics g) {
        g.drawImage(image, this.x, this.y, 75, 75, null);
    }

    int[][] colMat = { // size 38x27
        {-1,-1, 6, 2, 0, 1, 0, 0,-1, 0, 0, 2, 0, 0,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
        {-1,-1,-1, 6, 2, 0, 1, 1, 2, 1, 1, 1, 1, 1, 1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
        {-1,-1,-1,-1,-1,-1, 1, 1, 1, 0, 1, 1, 0, 0, 0, 0, 0, 1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
        {-1,-1,-1,-1,-1,-1,-1, 0, 0, 0, 0, 0, 2, 2, 2, 2, 2, 2,-1,-1,-1,-1,-1,-1,-1,-1,-1},
        {-1,-1,-1,-1,-1,-1,-1,-1,-1, 0, 0, 2, 0, 0, 0, 0, 0, 0, 1, 2, 6,-1,-1,-1,-1,-1,-1},
        {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 2, 2, 3, 3, 2, 2, 0, 2, 2, 6,-1,-1,-1,-1,-1,-1},
        {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 1, 4, 4, 5, 0, 0, 0, 1,-1,-1,-1,-1,-1,-1,-1},
        {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 2, 1, 4, 4, 5, 0, 0, 0, 0,-1,-1,-1,-1,-1,-1,-1},
        {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 2, 3, 3, 1, 1, 1, 1, 1,-1,-1,-1,-1,-1,-1,-1},
        {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 6, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 2,-1,-1,-1,-1},
        {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 6, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 2,-1,-1,-1,-1,-1},
        {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 6, 2, 0, 2, 2, 2, 2, 2, 2, 2, 2,-1,-1,-1,-1,-1},
        {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 0, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1,-1,-1,-1,-1},
        {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2,-1,-1,-1},
        {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2,-1,-1,-1},
        {-1,-1,-1, 2, 2, 2,-1,-1,-1,-1,-1,-1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 6,-1},
        {-1,-1, 0, 0, 0, 0, 1,-1,-1,-1,-1,-1,-1, 0, 3, 3, 4, 4, 5, 0, 0, 2, 0, 0, 2, 6,-1},
        {-1,-1, 1, 4, 4, 0, 0, 0, 0, 0, 2, 1, 1, 3, 3, 4, 4, 4, 4, 5, 0, 2, 1, 0,-1,-1,-1},
        { 0, 1, 3, 4, 4, 5, 1, 2, 2, 2, 2, 2, 1, 3, 3, 4, 4, 4, 4, 5, 1, 2, 2,-1,-1,-1,-1},
        { 0, 1, 3, 4, 4, 5, 1, 2, 2, 2, 2, 2, 1, 3, 3, 4, 4, 4, 4, 5, 1, 2, 2,-1,-1,-1,-1},
        {-1,-1, 1, 4, 4, 0, 0, 0, 0, 0, 2, 1, 1, 3, 3, 4, 4, 4, 4, 5, 0, 2, 1, 0,-1,-1,-1},
        {-1,-1, 0, 0, 0, 0, 1,-1,-1,-1,-1,-1,-1, 0, 3, 3, 4, 4, 5, 0, 0, 2, 0, 0, 2, 6,-1},
        {-1,-1,-1, 2, 2, 2,-1,-1,-1,-1,-1,-1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 6,-1},
        {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2,-1,-1,-1},
        {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2,-1,-1,-1},
        {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 0, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1,-1,-1,-1,-1},
        {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 6, 2, 0, 2, 2, 2, 2, 2, 2, 2, 2,-1,-1,-1,-1,-1},
        {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 6, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 2,-1,-1,-1,-1,-1},
        {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 6, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 2,-1,-1,-1,-1},
        {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 2, 3, 3, 1, 1, 1, 1, 1,-1,-1,-1,-1,-1,-1,-1},
        {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 2, 1, 4, 4, 5, 0, 0, 0, 0,-1,-1,-1,-1,-1,-1,-1},
        {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 1, 4, 4, 5, 0, 0, 0, 1,-1,-1,-1,-1,-1,-1,-1},
        {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 2, 2, 3, 3, 2, 2, 0, 2, 2, 6,-1,-1,-1,-1,-1,-1},
        {-1,-1,-1,-1,-1,-1,-1,-1,-1, 0, 0, 2, 0, 0, 0, 0, 0, 0, 1, 2, 6,-1,-1,-1,-1,-1,-1},
        {-1,-1,-1,-1,-1,-1,-1, 0, 0, 0, 0, 0, 2, 2, 2, 2, 2, 2,-1,-1,-1,-1,-1,-1,-1,-1,-1},
        {-1,-1,-1,-1,-1,-1, 1, 1, 1, 0, 1, 1, 0, 0, 0, 0, 0, 1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
        {-1,-1,-1, 6, 2, 0, 1, 1, 2, 1, 1, 1, 1, 1, 1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
        {-1,-1, 6, 2, 0, 1, 0, 0,-1, 0, 0, 2, 0, 0,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
    };
    
    void pinta(Graphics g) {
        for (int i=0; i<38; i++) {
            for (int j=0; j<27; j++) {
                if (colMat[i][j] == -1) g.setColor(TRANSPARENT);
                if (colMat[i][j] == 0) g.setColor(GREY);
                if (colMat[i][j] == 1) g.setColor(DARK_GREY);
                if (colMat[i][j] == 2) g.setColor(VERY_DARK_GREY);
                if (colMat[i][j] == 3) g.setColor(Color.BLUE);
                if (colMat[i][j] == 4) g.setColor(DARK_BLUE);
                if (colMat[i][j] == 5) g.setColor(VERY_DARK_BLUE);
                if (colMat[i][j] == 6) g.setColor(DARK_RED);
                g.drawRect(x+4*j, y+4*i, 3, 3);
                g.fillRect(x+4*j, y+4*i, 3, 3);
           }
        }
        g.setColor(Color.GREEN);
        g.drawRect(x+8, y-9, 46, 4);
        if (vida > 2) g.fillRect(x+9, y-8, 2*vida, 3);
        if (vida == 2) {
            g.setColor(Color.ORANGE);
            g.fillRect(x+9, y-8, 2*vida, 3);
        }    
        if (vida == 1) {
            g.setColor(Color.RED);
            g.fillRect(x+9, y-8, 2*vida, 3);
        }
    }
}

