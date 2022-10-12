import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Finestra extends Frame implements KeyListener, MouseListener {
    Joc j;
    Menu m;
    GameOver go;
    int x, y, AMPLADA=1440, ALTURA=900;
    double mode=0;
    Image im;
    Graphics g;

    public static void main(String argv[]) {
        new Finestra();
    }

    Finestra() {
        this.setSize(AMPLADA, ALTURA);
        this.setVisible(true);
        this.addMouseListener(this);
        im = this.createImage(AMPLADA, ALTURA);
        g = im.getGraphics();
        while (true) {
            if (mode == 0) {
                m = new Menu(this);
                m.run(this);
            }
            if (mode == 1) {
                this.addKeyListener(this);
                j = new Joc(this);
                j.inicialitzacio(this);
                j.run(this);
                j.sonido.close();
            }
            if (mode == 2) {
                this.addKeyListener(this);
                j = new Joc(this);
                j.inicialitzacio(this);
                j.run(this);
                j.sonido.close();
            }
            if (mode == 3) {
                go = new GameOver(this);
                go.run(this);
                go.sonido.close();
            }
        }
    }

    public void update(Graphics g) {
        paint(g);
    }

    public void paint(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawImage(im, 0, 0, null);
    }

    public void keyPressed(KeyEvent e) {
        if (j.jugadors[0] != null) {
            if (e.getKeyCode() == KeyEvent.VK_F) j.jugadors[0].disparar();
            if (e.getKeyCode() == KeyEvent.VK_C && j.jugadors[0].intMisil >= 1) {
                j.jugadors[0].disparaMisil(); 
                j.jugadors[0].intMisil -= 1;
            }
            if (e.getKeyCode() == KeyEvent.VK_W && j.jugadors[0].vy >= -15) {
                j.jugadors[0].vy -= 4;
                if (j.jugadors[0].vy == 0) j.jugadors[0].vy -= 1;
            }
            if (e.getKeyCode() == KeyEvent.VK_S && j.jugadors[0].vy <= 15) {
                j.jugadors[0].vy += 4;
                if (j.jugadors[0].vy == 0) j.jugadors[0].vy += 1;
            }
            if (e.getKeyCode() == KeyEvent.VK_A && j.jugadors[0].vx >= -15) {
                j.jugadors[0].vx -= 4;
                if (j.jugadors[0].vx == 0) j.jugadors[0].vx -= 1;
            }
            if (e.getKeyCode() == KeyEvent.VK_D && j.jugadors[0].vx <= 15) {
                j.jugadors[0].vx += 4;
                if (j.jugadors[0].vx == 0) j.jugadors[0].vx += 1;
            }
        }
        if (j.jugadors[1] != null) {
            if (e.getKeyCode() == KeyEvent.VK_SHIFT) j.jugadors[1].disparar();
            if (e.getKeyCode() == KeyEvent.VK_ENTER && j.jugadors[1].intMisil >= 1) {
                j.jugadors[1].disparaMisil(); 
                j.jugadors[1].intMisil -= 1;
            }
            if (e.getKeyCode() == KeyEvent.VK_UP && j.jugadors[1].vy >= -15) {
                j.jugadors[1].vy -= 4;
                if (j.jugadors[1].vy == 0) j.jugadors[1].vy -= 1;
            }
            if (e.getKeyCode() == KeyEvent.VK_DOWN && j.jugadors[1].vy <= 15) {
                j.jugadors[1].vy += 4;
                if (j.jugadors[1].vy == 0) j.jugadors[1].vy += 1;
            }
            if (e.getKeyCode() == KeyEvent.VK_LEFT && j.jugadors[1].vx >= -15) {
                j.jugadors[1].vx -= 4;
                if (j.jugadors[1].vx == 0) j.jugadors[1].vx -= 1;
            }
            if (e.getKeyCode() == KeyEvent.VK_RIGHT && j.jugadors[1].vx <= 15) {
                j.jugadors[1].vx += 4;
                if (j.jugadors[1].vx == 0) j.jugadors[1].vx += 1;
            }
        }
    }
    public void keyReleased(KeyEvent e) {}
    public void keyTyped(KeyEvent e) {}
    public void mouseClicked(MouseEvent e) { 
        if (mode == 0) {
            if (e.getX() >= 580 && e.getX() <= 835  && e.getY() >= 450 && e.getY() <= 490) {
                mode = 1; this.removeKeyListener(this); 
                m.sonido.close();
            }
            if (e.getX() >= 580 && e.getX() <= 804 && e.getY() >= 530 && e.getY() <= 570) {
                mode = 2; this.removeKeyListener(this); 
                m.sonido.close();
            }
        }
        if (mode == 3) {
            if (e.getX() >= 580 && e.getX() <= 835  && e.getY() >= 475 && e.getY() <= 515) {
                mode = 1; this.removeKeyListener(this); 
            }
            if (e.getX() >= 580 && e.getX() <= 804 && e.getY() >= 555 && e.getY() <= 595) {
                mode = 2; this.removeKeyListener(this); 
            }
        }
        if ((mode == 1 || mode == 2) && e.getX() > 1395 && e.getX() < 1433 && e.getY() > 35 && e.getY() < 81) mode = 0;
        if (mode == 3 && e.getX() > 1395 && e.getX() < 1433 && e.getY() > 35 && e.getY() < 81) System.exit(1);
    }
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
}
