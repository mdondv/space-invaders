import java.awt.Color;
import java.awt.Graphics;
import java.util.Calendar;
import java.io.File;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class JugadorDisseny1 extends Nau {
    Clip sonido;

    JugadorDisseny1(int x, int y) {
        this.x = x;
        this.y = y;  
    }

    void moure() {
        x += vx;
        y += vy;
    }
    
    void disparar() {
        bales.add(new Bala(x+16*4, y+7*4+2, 20));
        try {
            sonido = AudioSystem.getClip();
            sonido.open(AudioSystem.getAudioInputStream(new File("disparo.wav")));
            sonido.start();
            while (sonido.isRunning()) Thread.sleep(5);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void disparaMisil() {
        misil.add(new Misil(x+15*3, y, 15));
        misil.add(new Misil(x+15*3, y+17*3, 15));
        try {
            sonido = AudioSystem.getClip();
            sonido.open(AudioSystem.getAudioInputStream(new File("disparo.wav")));
            sonido.start();
            while (sonido.isRunning()) Thread.sleep(5);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    boolean alcanceBala(Nau n) {
        for (int k=0; k<n.bales.size(); k++) {
            if ((this.x+16*4 >= n.bales.get(k).x && this.x <= n.bales.get(k).x && this.y+6*4 <= n.bales.get(k).y && this.y+10*4 >=
                        n.bales.get(k).y && Math.abs(this.y+6*4-n.bales.get(k).y)<=4*4) || 
                (this.x+11*4 >= n.bales.get(k).x && this.x <= n.bales.get(k).x &&
                this.y <= n.bales.get(k).y && this.y+6*4 >= n.bales.get(k).y && Math.abs(this.y-n.bales.get(k).y)<=6*4) || 
                (this.x+11*4 >= n.bales.get(k).x && this.x <= n.bales.get(k).x &&
                this.y+10*4 <= n.bales.get(k).y && this.y+16*4 >= n.bales.get(k).y && Math.abs(this.y+10*4-n.bales.get(k).y)<=6*4)) {  
                n.bales.remove(k);
                return true;
            }
        }
        return false;
    }

    boolean alcanceMisil(Nau n) {
        for (int k=0; k<n.misil.size(); k++) {
            if ((this.x+16*4 >= n.misil.get(k).x && this.x <= n.misil.get(k).x && this.y+6*4 <= n.misil.get(k).y && this.y+10*4 >=
                        n.misil.get(k).y && Math.abs(this.y+6*4-n.misil.get(k).y)<=4*4) || 
                (this.x+11*4 >= n.misil.get(k).x && this.x <= n.misil.get(k).x &&
                this.y <= n.misil.get(k).y && this.y+6*4 >= n.misil.get(k).y && Math.abs(this.y-n.misil.get(k).y)<=6*4) || 
                (this.x+11*4 >= n.misil.get(k).x && this.x <= n.misil.get(k).x &&
                this.y+10*4 <= n.misil.get(k).y && this.y+16*4 >= n.misil.get(k).y && Math.abs(this.y+10*4-n.misil.get(k).y)<=6*4)) {  
                n.misil.remove(k);
                return true;
            }
        }
        return false;
    }

    boolean alcanceBonus(Bonus b) {
        if ((this.x+16*4 >= b.x && this.x <= b.x && this.y+6*4 <= b.y && this.y+10*4 >= b.y &&  Math.abs(this.y+6*4-b.y)<=4*4) || 
            (this.x+11*4 >= b.x && this.x <= b.x && this.y <= b.y && this.y+6*4 >= b.y && Math.abs(this.y-b.y)<=6*4) || 
            (this.x+11*4 >= b.x && this.x <= b.x && this.y+10*4 <= b.y && this.y+16*4 >= b.y && Math.abs(this.y+10*4-b.y)<=6*4)) return true;
        else return false;
    }

    void xocNau(Nau n) {
        if (this.x <= n.x && Math.abs(this.x-n.x) <= 17*4 && Math.abs(this.y-n.y) <= 16*4) {
            if (this.y <= n.y && Math.abs(this.y-n.y) <= 16*4) {
                this.y -= 5; 
                n.y += 5;
            }
            if (this.y >= n.y && Math.abs(this.y-n.y) <= 16*4) {
                this.y += 5;
                n.y -= 5;
            }
            this.x -= 5;
            n.x += 5;
        }
        if (this.x >= n.x && Math.abs(this.x-n.x) <= 17*4 && Math.abs(this.y-n.y) <= 16*4) {
            if (this.y <= n.y && Math.abs(this.y-n.y) <= 16*4) {
                this.y -= 5;
                n.y += 5;
            }
            if (this.y >= n.y && Math.abs(this.y-n.y) <= 16*4) {
                this.y += 5;
                n.y -= 5;
            }
            this.x += 5;
            n.x -= 5;
       }
    }

    void xocAst(Asteroide a) {
        if (this.x <= a.x && Math.abs(this.x-a.x) <= 17*4 && Math.abs(this.y-a.y) <= 16*4) {
            if (this.y <= a.y) {
                this.y -= 5; 
                a.y += 5;
            }
            if (this.y >= a.y && Math.abs(this.y-a.y) <= 16*4) {
                this.y += 5;
                a.y -= 5;
            }
            this.x -= 5;
            a.x += 5;
            a.vx *= -3;
        }
        if (this.x >= a.x && Math.abs(this.x-a.x) <= 17*4 && Math.abs(this.y-a.y) <= 16*4) {
            if (this.y <= a.y && Math.abs(this.y-a.y) <= 16*4) {
                this.y -= 5;
                a.y += 5;
            }
            if (this.y >= a.y && Math.abs(this.y-a.y) <= 16*4) {
                this.y += 5;
                a.y -= 5;
            }
            this.x += 5;
            a.x -= 5;
            a.vx *= -3;
       }
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
                g.drawRect(this.x+2*j-14, y+2*i+45, 1, 1); g.fillRect(this.x+2*j-14, y+2*i+45, 1, 1);
                if (propMat[i][j] != -1) propMat[i][j] = (propMat[i][j]+1) % 3;
           }
        }
    };

    int[][] colMat = {
        {-1, 0, 0, 0,-1, 0, 0,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
        {-1,-1, 1, 1, 2, 1, 1, 1, 0, 2, 6,-1,-1,-1,-1,-1,-1},
        { 6, 2, 1, 0, 1, 0, 1, 1, 1,-1,-1,-1,-1,-1,-1,-1,-1},
        { 6, 2, 0, 0, 0, 0, 0, 0, 0,-1,-1,-1,-1,-1,-1,-1,-1},
        {-1,-1,-1, 0, 0, 2, 2, 2, 0, 0, 0,-1,-1,-1,-1,-1,-1},
        {-1,-1,-1,-1, 2, 2, 2, 2, 2, 2, 2, 2,-1,-1,-1,-1,-1},
        {-1,-1,-1,-1,-1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1,-1,-1},
        {-1,-1,-1,-1,-1,-1, 2, 0, 1, 1, 2, 5, 4, 4, 3, 1, 0},
        {-1,-1,-1,-1,-1,-1, 2, 0, 1, 1, 2, 5, 4, 4, 3, 1, 0},
        {-1,-1,-1,-1,-1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1,-1,-1},
        {-1,-1,-1,-1, 2, 2, 2, 2, 2, 2, 2, 2,-1,-1,-1,-1,-1},
        {-1,-1,-1, 0, 0, 2, 2, 2, 0, 0, 0,-1,-1,-1,-1,-1,-1},
        { 6, 2, 0, 0, 0, 0, 0, 0, 0,-1,-1,-1,-1,-1,-1,-1,-1},
        { 6, 2, 1, 0, 1, 0, 1, 1, 1,-1,-1,-1,-1,-1,-1,-1,-1},
        {-1,-1, 1, 1, 2, 1, 1, 1, 0, 2, 6,-1,-1,-1,-1,-1,-1},
        {-1, 0, 0, 0,-1, 0,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
    };

    void pinta(Graphics g) {
        for (int i=0; i<16; i++) {
            for (int j=0; j<17; j++) {
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
        g.drawRect(x+4, y-9, 25, 4);
        if (vida > 2) g.fillRect(x+5, y-8, 4*vida, 3);
        if (vida == 2) {
            g.setColor(Color.ORANGE);
            g.fillRect(x+5, y-8, 4*vida, 3);
        }    
        if (vida == 1) {
            g.setColor(Color.RED);
            g.fillRect(x+5, y-8, 4*vida, 3);
        }
    }
}

