import java.awt.Color;
import java.awt.Graphics;
import java.util.Calendar;
import java.util.ArrayList;

public class JugadorDisseny2 extends Nau {

    JugadorDisseny2(int x, int y) {
        this.x = x;
        this.y = y;  
    }

    void moure() {
        x += vx;
        y += vy;
    }
    
    void disparar() {
        bales.add(new Bala(x+11*4, y+24, 15));
        bales.add(new Bala(x+11*4, y+68, 15));
    }

    boolean alcanceBala(Nau enemic) {
        for (int k=0; k<enemic.bales.size(); k++) {
            if ((this.x+11*4 >= enemic.bales.get(k).x && this.x <= enemic.bales.get(k).x &&
                this.y <= enemic.bales.get(k).y && this.y+36 >= enemic.bales.get(k).y &&  Math.abs(this.y+36-enemic.bales.get(k).y)<=36) || 
                (this.x+21*4 >= enemic.bales.get(k).x && this.x <= enemic.bales.get(k).x &&
                this.y+36 <= enemic.bales.get(k).y && this.y+60 >= enemic.bales.get(k).y && Math.abs(this.y+36-enemic.bales.get(k).y)<=24) || 
                (this.x+11*4 >= enemic.bales.get(k).x && this.x <= enemic.bales.get(k).x &&
                this.y+60 <= enemic.bales.get(k).y && this.y+24*4 >= enemic.bales.get(k).y && Math.abs(this.y+60-enemic.bales.get(k).y)<=32)) {  
                enemic.bales.remove(k);
                return true; 
            }
        }
        return false;
    }

    int[][] propMat = {
        {-1,-1,-1, 3, 3, 3,-1},
        {-1,-1, 3, 1, 2, 2, 3},
        {-1, 3, 0, 0, 1, 2, 2},
        { 3, 0, 0, 1, 1, 1, 2},
        {-1, 3, 1, 1, 1, 2, 2},
        {-1,-1, 3, 1, 2, 2, 3},
        {-1,-1,-1, 3, 3, 3,-1},
    };

    void propulsio(Graphics g) {
        for (int i=0; i<7; i++) {
            for (int j=0; j<7; j++) {
                if (propMat[i][j] == -1) g.setColor(TRANSPARENT);
                if (propMat[i][j] == 0) g.setColor(Color.YELLOW);
                if (propMat[i][j] == 1) g.setColor(LIGHT_ORANGE);
                if (propMat[i][j] == 2) g.setColor(Color.ORANGE);
                if (propMat[i][j] == 3) g.setColor(GOLD);
                g.drawRect(this.x+2*j-14, y+2*i+5, 1, 1); g.fillRect(this.x+2*j-14, y+2*i+5, 1, 1);
                g.drawRect(this.x+2*j-14, y+2*i+77, 1, 1); g.fillRect(this.x+2*j-14, y+2*i+77, 1, 1);
                if (propMat[i][j] != -1) propMat[i][j] = (propMat[i][j]+1) % 3;
           }
        }
    };

    int[][] colMat = { 
        {-1, 0, 0, 0,-1, 0, 0,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
        {-1,-1, 1, 1, 2, 1, 1, 1, 0, 2, 6,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
        { 6, 2, 1, 0, 1, 0, 1, 1, 1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
        { 6, 2, 0, 0, 0, 0, 0, 0, 0,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
        {-1,-1,-1, 0, 0, 2, 2, 2, 0, 0,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
        {-1,-1,-1,-1, 0, 0, 2, 2, 2, 0, 0,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
        {-1,-1,-1,-1, 0, 0, 2, 2, 2, 0, 0, 2, 2, 6,-1,-1,-1,-1,-1,-1,-1},
        {-1,-1,-1,-1,-1, 0, 2, 2, 2, 2, 0, 0,-1,-1,-1,-1,-1,-1,-1,-1,-1},
        {-1,-1,-1,-1,-1, 0, 2, 2, 2, 2, 2,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
        {-1,-1, 6, 2, 2, 2, 2, 2, 2, 2, 1, 1, 1, 2, 2, 2,-1,-1,-1,-1,-1},
        {-1,-1,-1,-1, 0, 0, 0, 2, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1,-1,-1},
        {-1,-1,-1,-1,-1,-1, 2, 2, 1, 1, 5, 4, 3, 1, 2, 5, 4, 4, 3, 1, 0},
        {-1,-1,-1,-1,-1,-1, 2, 2, 1, 1, 5, 4, 3, 1, 2, 5, 4, 4, 3, 1, 0},
        {-1,-1,-1,-1, 0, 0, 0, 2, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1,-1,-1},
        {-1,-1, 6, 2, 2, 2, 2, 2, 2, 2, 1, 1, 1, 2, 2, 2,-1,-1,-1,-1,-1},
        {-1,-1,-1,-1,-1, 0, 2, 2, 2, 2, 2,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
        {-1,-1,-1,-1,-1, 0, 2, 2, 2, 2, 0, 0,-1,-1,-1,-1,-1,-1,-1,-1,-1},
        {-1,-1,-1,-1, 0, 0, 2, 2, 2, 0, 0, 2, 2, 6,-1,-1,-1,-1,-1,-1,-1},
        {-1,-1,-1,-1, 0, 0, 2, 2, 2, 0, 0,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
        {-1,-1,-1, 0, 0, 2, 2, 2, 0, 0,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
        { 6, 2, 0, 0, 0, 0, 0, 0, 0,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
        { 6, 2, 1, 0, 1, 0, 1, 1, 1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
        {-1,-1, 1, 1, 2, 1, 1, 1, 0, 2, 6,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
        {-1, 0, 0, 0,-1, 0, 0,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
    };

    void pinta(Graphics g) {
        for (int i=0; i<24; i++) {
            for (int j=0; j<21; j++) {
                if (colMat[i][j] == -1) g.setColor(TRANSPARENT);
                if (colMat[i][j] == 0) g.setColor(WHITE);
                if (colMat[i][j] == 1) g.setColor(LIGHT_GREY);
                if (colMat[i][j] == 2) g.setColor(GREY);
                if (colMat[i][j] == 3) g.setColor(VERY_LIGHT_BLUE);
                if (colMat[i][j] == 4) g.setColor(LIGHT_BLUE);
                if (colMat[i][j] == 5) g.setColor(Color.BLUE);
                if (colMat[i][j] == 6) g.setColor(LIGHT_RED);
                g.drawRect(x+4*j, y+4*i, 3, 3);
                g.fillRect(x+4*j, y+4*i, 3, 3);
           }
        }
        g.setColor(Color.GREEN);
        g.drawRect(x+6, y-9, 30, 4);
        if (vida > 2) g.fillRect(x+7, y-8, 3*vida, 3);
        if (vida == 2) {
            g.setColor(Color.ORANGE);
            g.fillRect(x+7, y-8, 3*vida, 3);
        }    
        if (vida == 1) {
            g.setColor(Color.RED);
            g.fillRect(x+7, y-8, 3*vida, 3);
        }
    }
}


