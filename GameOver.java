import java.awt.Frame;
import java.awt.Graphics;
import java.util.ArrayList;
import java.awt.Color;
import java.util.Random;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Font;
import java.util.Calendar;
import java.io.File;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


public class GameOver { 
    Graphics g;
    Finestra f;
    Fons fons = new Fons(0, 650, 1);
    Toolkit t = Toolkit.getDefaultToolkit();
    Image image = t.getImage("GameOver.png");
    int x, y;
    ArrayList<Nau> jugadors = new ArrayList<Nau>();
    ArrayList<Nau> enemics = new ArrayList<Nau>();
    ArrayList<Asteroide> asteroides = new ArrayList<Asteroide>();
    ArrayList<Asteroide> miniAsteroides = new ArrayList<Asteroide>();
    ArrayList<Asteroide> miniDobleAsteroides = new ArrayList<Asteroide>();
    ArrayList<Estrella> estrella = new ArrayList<Estrella>();
    ArrayList<Bonus> bonus = new ArrayList<Bonus>();
    Random r = new Random();
    Clip sonido;

    Color WHITE = new Color(255, 255, 255);
    Color TRANSPARENT =new Color(0f, 0f, 0f, 0f);
    Color VERY_LIGHT_BLUE = new Color(51, 204, 255);
    Color LIGHT_BLUE = new Color(51, 153, 255);
    Color DARK_BLUE = new Color(0, 0, 204);
    Color VERY_DARK_BLUE = new Color(0, 0, 153);
    Color VERY_DARK_GREEN = new Color(0, 102, 0);
    Color DARK_GREY = new Color(51, 51, 51);

    GameOver(Finestra f) {
        this.f = f;
        this.g = f.g;
    }

    void run(Finestra f) {
        Calendar c = Calendar.getInstance();
        int seconds, cont = 0, cont2 = 0;
        try {
            sonido = AudioSystem.getClip();
            sonido.open(AudioSystem.getAudioInputStream(new File("gameover.wav")));
            sonido.start();
            while (sonido.isRunning()) Thread.sleep(5);
        } catch (Exception e) {
            e.printStackTrace();
        }
        inicialitzacio();
        while (f.mode == 3) {
            seconds = c.get(Calendar.SECOND);
            moviments();
            if (seconds % 4 == 0 && cont == 0) {
                for (int i=0; i<enemics.size(); i++) {
                    enemics.get(i).disparar();
                    add();
                }
                cont = 1;
            }
            if (seconds % 4 != 0) cont = 0;
            repintar();
            pintaTitol(g);
            f.repaint();
            try {
                Thread.sleep(50);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        sonido.close();
    }

    int[][] menuMat = {
        {0,1,0,0,0,1,1,1,0,1,0,0,0,1,1,1,0,1,0,1,0,1,1,1,0,1,1,1,0,0,0,0},
        {0,1,0,0,0,1,0,1,0,1,0,0,0,1,0,1,0,1,0,1,0,1,0,0,0,1,0,1,0,0,0,0},
        {0,1,0,0,0,1,1,1,0,1,0,0,0,1,1,1,0,1,0,1,0,1,1,1,0,1,1,0,0,0,0,0},
        {0,1,0,0,0,1,0,0,0,1,0,0,0,1,0,1,0,0,1,0,0,1,0,0,0,1,0,1,0,0,0,0},
        {0,1,0,0,0,1,0,0,0,1,1,1,0,1,0,1,0,0,1,0,0,1,1,1,0,1,0,1,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {1,1,1,0,0,1,1,1,0,1,0,0,0,1,1,1,0,1,0,1,0,1,1,1,0,1,1,1,0,1,1,1},
        {0,0,1,0,0,1,0,1,0,1,0,0,0,1,0,1,0,1,0,1,0,1,0,0,0,1,0,1,0,1,0,0},
        {0,1,0,0,0,1,1,1,0,1,0,0,0,1,1,1,0,1,0,1,0,1,1,1,0,1,1,0,0,0,1,0},
        {1,0,0,0,0,1,0,0,0,1,0,0,0,1,0,1,0,0,1,0,0,1,0,0,0,1,0,1,0,0,0,1},
        {1,1,1,0,0,1,0,0,0,1,1,1,0,1,0,1,0,0,1,0,0,1,1,1,0,1,0,1,0,1,1,1},
    };

    void inicialitzacio() {
        jugadors.add(new JugadorDisseny1(100, 300));
        jugadors.get(0).vida = 6;
        for (int i=0; i<700; i++) estrella.add(new EstrellaBloc(Math.abs(r.nextInt() % 1400), Math.abs(r.nextInt() % 750), 1));
        for (int i=0; i<30; i++) estrella.add(new EstrellaTitilante(Math.abs(r.nextInt() % 1400), Math.abs(r.nextInt() % 750), 1));
    }

    void add() {
        int posicioY = r.nextInt();
        asteroides.add(new Asteroide(Math.abs(r.nextInt() % 100 + 900),
                    Math.abs(r.nextInt() % 300 + 200), Math.abs(r.nextInt() % 3 + 3), r.nextInt() % 4 + 4));
        enemics.add(new NauEnemigaDisseny1(Math.abs(r.nextInt() % 100 + 900), Math.abs(r.nextInt() % 500 + 200), 4));
        enemics.get(enemics.size()-1).vida = 5;
        bonus.add(new BonusVida(Math.abs(r.nextInt() % 100 + 900),
                    Math.abs(r.nextInt() % 500 + 200), Math.abs(r.nextInt() % 4 + 3), Math.abs(r.nextInt() % 4+3)));
        bonus.add(new BonusMisil(Math.abs(r.nextInt() % 100 + 900),
                    Math.abs(r.nextInt() % 500 + 200), Math.abs(r.nextInt() % 4 + 3), Math.abs(r.nextInt() % 4+3)));
    }

    void moviments() {
        for (int i=0; i<jugadors.size(); i++) {
            jugadors.get(i).moure();
            for (int j=0; j<jugadors.get(i).bales.size(); j++) jugadors.get(i).bales.get(j).moure();
            for (int j=0; j<jugadors.get(i).misil.size(); j++) jugadors.get(i).misil.get(j).moure();
        }
        for (int i=0; i<enemics.size(); i++) {
            enemics.get(i).moure();
            for (int j=0; j<enemics.get(i).bales.size(); j++) enemics.get(i).bales.get(j).moure();
        }
        for (int i=0; i<estrella.size(); i++) {
            estrella.get(i).moure();
        }
        for (int i=0; i<asteroides.size(); i++) {
            asteroides.get(i).moure();
        }
        for (int i=0; i<miniAsteroides.size(); i++) {
            miniAsteroides.get(i).moure();
        }
        for (int i=0; i<miniDobleAsteroides.size(); i++) {
            miniDobleAsteroides.get(i).moure();
        }
        fons.moure();
        for (int i=0; i<bonus.size(); i++) {
            bonus.get(i).moure();
        }
    }

    void repintar() {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, f.AMPLADA, f.ALTURA);
        for (int i=0; i<estrella.size(); i++) {
            if (estrella.get(i).x <= 0) estrella.get(i).x += 1435;
            estrella.get(i).pinta(g);
        }
        fons.pintaImatge(g);
        for (int i=0; i<jugadors.size(); i++) {
            jugadors.get(i).pinta(g);
            jugadors.get(i).propulsio(g);
            for (int j=0; j<jugadors.get(i).bales.size(); j++) {
                jugadors.get(i).bales.get(j).pinta(g);
            }
            for (int j=0; j<jugadors.get(i).misil.size(); j++) {
                jugadors.get(i).misil.get(j).pinta(g);
                jugadors.get(i).misil.get(j).propulsio(g);
            }
        }
        for (int i=0; i<enemics.size(); i++) {
            enemics.get(i).pinta(g);
            enemics.get(i).propulsio(g);
            for (int j=0; j<enemics.get(i).bales.size(); j++)
                enemics.get(i).bales.get(j).pinta(g);
        }
        for (int i=0; i<asteroides.size(); i++) {
            asteroides.get(i).pinta(g);
        }
        for (int i=0; i<miniAsteroides.size(); i++) {
            miniAsteroides.get(i).pinta(g);
        }
        for (int i=0; i<miniDobleAsteroides.size(); i++) {
            miniDobleAsteroides.get(i).pinta(g);
        }
        for (int i=0; i<bonus.size(); i++) {
            bonus.get(i).pinta(g);
        }
    }

    void pintaTitol(Graphics g) {
        g.drawImage(image, 500, 100, 400, 400, null);
        for (int i=0; i<14; i++) {
            for (int j=0; j<32; j++) {
                if (menuMat[i][j] == 0) g.setColor(TRANSPARENT);
                if (menuMat[i][j] == 1) g.setColor(Color.GREEN);
                x=580; y=475;
                g.drawRect(x+8*j, y+8*i, 7, 7);
                g.fillRect(x+8*j, y+8*i, 7, 7);
           }
        }
        for (int i=0; i<23; i++) {
            for (int j=0; j<19; j++) {
                if (exitMat[i][j] == 0) g.setColor(WHITE);
                if (exitMat[i][j] == -1) g.setColor(Color.GREEN);
                g.drawRect(1395+2*j, 35+2*i, 1, 1);
                g.fillRect(1395+2*j, 35+2*i, 1, 1);
           }
        }
    }

    int[][] exitMat = {
        {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
        {-1,-1,-1,-1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,-1,-1},
        {-1,-1,-1,-1, 0, 0, 0, 0, 0, 0, 0, 0,-1,-1, 0, 0, 0,-1,-1},
        {-1,-1,-1,-1, 0, 0, 0, 0, 0, 0, 0, 0,-1,-1, 0, 0, 0,-1,-1},
        {-1,-1,-1,-1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,-1,-1},
        {-1,-1,-1,-1, 0, 0, 0,-1,-1,-1,-1,-1,-1,-1, 0, 0, 0,-1,-1},
        {-1,-1,-1,-1, 0, 0,-1,-1,-1,-1,-1,-1,-1,-1,-1, 0, 0,-1,-1},
        {-1,-1,-1,-1, 0,-1,-1, 0, 0,-1,-1,-1,-1,-1,-1, 0, 0,-1,-1},
        {-1,-1,-1,-1, 0,-1,-1, 0,-1,-1,-1,-1,-1, 0,-1,-1,-1,-1,-1},
        {-1,-1,-1,-1, 0, 0, 0, 0,-1,-1,-1,-1, 0, 0, 0,-1,-1,-1,-1},
        {-1,-1,-1,-1, 0, 0, 0, 0,-1,-1,-1,-1, 0, 0, 0, 0, 0,-1,-1},
        {-1,-1,-1,-1, 0, 0, 0, 0,-1,-1,-1,-1, 0, 0, 0, 0, 0,-1,-1},
        {-1,-1,-1,-1, 0, 0, 0, 0,-1,-1,-1,-1,-1, 0, 0, 0, 0,-1,-1},
        {-1,-1, 0, 0, 0, 0, 0, 0,-1,-1, 0,-1,-1, 0, 0, 0, 0,-1,-1},
        {-1, 0, 0,-1,-1,-1,-1,-1,-1,-1, 0,-1,-1,-1, 0, 0, 0,-1,-1},
        {-1, 0,-1,-1,-1,-1,-1,-1,-1, 0, 0, 0,-1,-1, 0, 0, 0,-1,-1},
        {-1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,-1,-1, 0, 0, 0,-1,-1},
        {-1,-1,-1,-1, 0, 0, 0, 0, 0, 0, 0, 0, 0,-1,-1, 0, 0,-1,-1},
        {-1,-1,-1,-1, 0, 0, 0, 0, 0, 0, 0, 0, 0,-1,-1, 0, 0,-1,-1},
        {-1,-1,-1,-1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,-1,-1},
        {-1,-1,-1, 0, 0, 0, 0, 0, 0, 0, 0, 0,-1,-1, 0, 0,-1,-1,-1},
        {-1,-1, 0, 0, 0, 0, 0, 0, 0, 0, 0,-1,-1, 0, 0,-1,-1,-1,-1},
        {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
    };
}
