import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import java.util.Calendar;
import java.util.ArrayList;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Joc {
    Graphics g;
    Finestra f;
    Fons fons = new Fons(0, 650, 1);
    int puntuacio = 0, intMisil=0, iteracions=2000;
    Nau jugadors[] = new Nau[2];
    ArrayList<Nau> enemics = new ArrayList<Nau>();
    ArrayList<Asteroide> asteroides = new ArrayList<Asteroide>();
    ArrayList<Asteroide> miniAsteroides = new ArrayList<Asteroide>();
    ArrayList<Asteroide> miniDobleAsteroides = new ArrayList<Asteroide>();
    ArrayList<Estrella> estrella = new ArrayList<Estrella>();
    ArrayList<Bonus> bonus = new ArrayList<Bonus>();
    Random r = new Random();
    Clip sonido; Font pixelMplus;

    Color WHITE = new Color(255, 255, 255);
    Color TRANSPARENT = new Color(0f, 0f, 0f, 0f);

    Joc(Finestra f) {
        this.f = f;
        this.g = f.g;
    }

    void run(Finestra f) {
        try {
            sonido = AudioSystem.getClip();
            sonido.open(AudioSystem.getAudioInputStream(new File("joc.wav")));
            sonido.start();
            while (sonido.isRunning()) Thread.sleep(5);
        } catch (Exception e) {
            e.printStackTrace();
        }
        while (f.mode == 1 || f.mode == 2) {
            moviments();
            if (iteracions % 150 == 0) addBonus();
            if (iteracions >= 0 && iteracions % 110==0) addNau1(); 
            if (iteracions >= 2000 && iteracions <= 4000 && iteracions % 120==0) addNau2(); 
            if (iteracions >= 3000 && iteracions <= 5000 && iteracions % 130==0) addNau3(); 
            if (iteracions >= 5000 && iteracions <= 7000 && iteracions % 140==0) addNau4(); 
            if (iteracions >= 7000 && iteracions <= 10000 && iteracions % 150==0) addNau5(); 
            if (iteracions % 60 == 0) addAsteroides(); 
            if (iteracions % 40 == 0) {
                for (int i=0; i<enemics.size(); i++) {
                    enemics.get(i).disparar();
                }
            }
            xocs();
            alcance();
            desintegracio();
//            remove();
            repintar();
            f.repaint();
            try {
                Thread.sleep(50);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
            iteracions += 1;
            if (f.mode == 1 && jugadors[0] == null) {
                f.mode = 3; 
            }
            if (f.mode == 2 && jugadors[0] == null && jugadors[1] == null) {
                f.mode = 3; 
            }
        }
        sonido.close();
    }

    void inicialitzacio(Finestra f) {
        jugadors[0] = new JugadorDisseny1(100, 300);
        jugadors[0].vida = 6; jugadors[0].puntuacio = 0;
        if (f.mode == 2) {
            jugadors[1] = new JugadorDisseny1(100, 500);
            jugadors[1].vida = 6; jugadors[1].puntuacio = 0;
        }   
        for (int i=0; i<700; i++) estrella.add(new EstrellaBloc(Math.abs(r.nextInt() % 1400), Math.abs(r.nextInt() % 750), 1));
        for (int i=0; i<30; i++) estrella.add(new EstrellaTitilante(Math.abs(r.nextInt() % 1400), Math.abs(r.nextInt() % 750), 1));
    }

    void addAsteroides() {
        asteroides.add(new Asteroide(1450, Math.abs(r.nextInt() % 600) + 100, Math.abs(r.nextInt() % 3) + 4, 
                        r.nextInt() % 2));
    }

    void addBonus() {
        bonus.add(new BonusVida(1450, Math.abs(r.nextInt() % 600) + 100, Math.abs(r.nextInt() % 3) + 2, r.nextInt() % 1));
        bonus.add(new BonusMisil(1450, Math.abs(r.nextInt() % 600) + 100, Math.abs(r.nextInt() % 3) + 2,r.nextInt() % 1));
        bonus.add(new BonusMuerte(1450, Math.abs(r.nextInt() % 600) + 100, Math.abs(r.nextInt() % 3) + 2,r.nextInt() % 1));
        bonus.add(new BonusFantasma(1450, Math.abs(r.nextInt() % 600) + 100, Math.abs(r.nextInt() % 3) + 2,r.nextInt() % 1));
    }
    
    void addNau1() {
        enemics.add(new NauEnemigaDisseny1(1450, Math.abs(r.nextInt() % 500) + 200, 2));
        enemics.get(enemics.size()-1).vida = 6;
    }
    
    void addNau2() {
        enemics.add(new NauEnemigaDisseny2(1450, Math.abs(r.nextInt() % 500) + 200, 2));
        enemics.get(enemics.size()-1).vida = 10;
    }

    void addNau3() {
        enemics.add(new NauEnemigaDisseny3(1450, Math.abs(r.nextInt() % 500) + 200, 2));
        enemics.get(enemics.size()-1).vida = 15;
    }

    void addNau4() {
        enemics.add(new NauEnemigaDisseny4(1450, Math.abs(r.nextInt() % 500) + 200, 2));
        enemics.get(enemics.size()-1).vida = 23;
    }
    
    void addNau5() {
        enemics.add(new NauEnemigaDisseny5(1450, Math.abs(r.nextInt() % 500) + 200, 2));
        enemics.get(enemics.size()-1).vida = 28;
    }

    void remove() {
        for (int i=0; i<jugadors.length; i++) {
            if (jugadors[i] != null) {
                for (int j=0; j<jugadors[i].bales.size(); j++) {
                    if (jugadors[i].bales.get(j).x >= 1450) jugadors[i].bales.remove(j);
                    if (jugadors[i].misil.get(j).x >= 1450) jugadors[i].misil.remove(j);
                }
            }
        }
        for (int i=0; i<enemics.size(); i++) {
            for (int j=0; j<enemics.get(i).bales.size(); j++) {
                if (enemics.get(i).bales.get(j).x <= -4) enemics.get(i).bales.remove(j);
            }
            if (enemics.get(i).x <= - 50) enemics.remove(i);
        }
    }

    void moviments() {
        for (int i=0; i<jugadors.length; i++) {
            if (jugadors[i] != null) {
                jugadors[i].moure();
                for (int j=0; j<jugadors[i].bales.size(); j++) jugadors[i].bales.get(j).moure();
                for (int j=0; j<jugadors[i].misil.size(); j++) jugadors[i].misil.get(j).moure();
            }
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

    void alcance() {
        for (int i=0; i<jugadors.length; i++) {
            if (jugadors[i] != null) {
                for (int j=0; j<enemics.size(); j++) {
                    if (jugadors[i].alcanceBala(enemics.get(j)) == true) jugadors[i].vida -= 1;
                    if (enemics.get(j).alcanceBala(jugadors[i]) == true) enemics.get(j).vida -= 1;
                    if (enemics.get(j).alcanceMisil(jugadors[i]) == true) enemics.get(j).vida -= 4;
                    if (enemics.get(j).vida <= 0) {
                        if (enemics.get(j) instanceof NauEnemigaDisseny1) jugadors[i].puntuacio += 10;
                        if (enemics.get(j) instanceof NauEnemigaDisseny2) jugadors[i].puntuacio += 50;
                        if (enemics.get(j) instanceof NauEnemigaDisseny3) jugadors[i].puntuacio += 100;
                        if (enemics.get(j) instanceof NauEnemigaDisseny4) jugadors[i].puntuacio += 400;
                        if (enemics.get(j) instanceof NauEnemigaDisseny5) jugadors[i].puntuacio += 1000;
                    }
                }
                for (int j=0; j<bonus.size(); j++) {
                    if (jugadors[i].alcanceBonus(bonus.get(j)) == true) {
                        if (!(bonus.get(j) instanceof BonusMuerte)) {
                            try {
                                sonido = AudioSystem.getClip();
                                sonido.open(AudioSystem.getAudioInputStream(new File("bonus.wav")));
                                sonido.start();
                                while (sonido.isRunning()) Thread.sleep(5);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            if (bonus.get(j) instanceof BonusVida && jugadors[i].vida < 6) jugadors[i].vida += 1;
                            if (bonus.get(j) instanceof BonusMisil) jugadors[i].intMisil += 1;
                            bonus.remove(j);
                            break;
                        }
                        if (bonus.get(j) instanceof BonusMuerte) {
                            jugadors[i] = null;
                            bonus.remove(j);
                            break;
                        }
                    }
                }
            }
        }
        for (int i=0; i<jugadors.length; i++) 
            if (jugadors[i] != null && jugadors[i].vida <= 0) { 
                jugadors[i] = null;
                sonido.close();     
            }
    }

    void xocs() {
        for (int i=0; i<jugadors.length; i++) {
            for (int j=0; j<jugadors.length; j++) {
                if (jugadors[i] != null && jugadors[j] != null) {
                    if (i != j) jugadors[i].xocNau(jugadors[j]);
                }
            }
            for (int j=0; j<enemics.size(); j++) {
                if (jugadors[i] != null) jugadors[i].xocNau(enemics.get(j));
            }
            for (int j=0; j<asteroides.size(); j++) {
                if (jugadors[i] != null) jugadors[i].xocAst(asteroides.get(j));
            }
        }
    }

    void desintegracio() {
        for (int i=0; i<jugadors.length; i++) {
            if (jugadors[i] != null) {
                for (int j=0; j<asteroides.size(); j++) {
                    if (asteroides.get(j).desintegracio(jugadors[i]) == true) {
                        miniAsteroides.add(new MiniAsteroide(asteroides.get(j).x, asteroides.get(j).y-8, Math.abs(r.nextInt() % 10+2), 
                                    Math.abs(r.nextInt() % 4+1)));
                        miniAsteroides.add(new MiniAsteroide(asteroides.get(j).x, asteroides.get(j).y+8, Math.abs(r.nextInt() % 10+2), 
                                            Math.abs(r.nextInt() % 4+1)));
                        asteroides.remove(j);
                        break;
                    }
                }
                for (int j=0; j<miniAsteroides.size(); j++) {
                    if (miniAsteroides.get(j).desintegracio(jugadors[i]) == true) {
                        miniDobleAsteroides.add(new MiniDobleAsteroide(miniAsteroides.get(j).x, miniAsteroides.get(j).y-8, Math.abs(r.nextInt() % 10+2), 
                                    Math.abs(r.nextInt() % 4+1)));
                        miniDobleAsteroides.add(new MiniDobleAsteroide(miniAsteroides.get(j).x, miniAsteroides.get(j).y+8, Math.abs(r.nextInt() % 10+2), 
                                            Math.abs(r.nextInt() % 4+1)));
                        miniAsteroides.remove(j);
                        break;
                    }
                }
                for (int j=0; j<miniDobleAsteroides.size(); j++) {
                    if (miniDobleAsteroides.get(j).desintegracio(jugadors[i]) == true) {
                        miniDobleAsteroides.remove(j);
                        break;
                    }
                }
            }
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
        for (int i=0; i<jugadors.length; i++) {
            if (jugadors[i] != null) {
                jugadors[i].pinta(g);
                jugadors[i].propulsio(g);
                for (int j=0; j<jugadors[i].bales.size(); j++) {
                    jugadors[i].bales.get(j).pinta(g);
                }
                for (int j=0; j<jugadors[i].misil.size(); j++) { 
                    jugadors[i].misil.get(j).pinta(g);
                    jugadors[i].misil.get(j).propulsio(g);
                }
            }
        }
        for (int i=0; i<enemics.size(); i++) {
            if (enemics.get(i).vida <= 0) {
                enemics.get(i).explosio(g);
                enemics.remove(i);
                try {
                    sonido = AudioSystem.getClip();
                    sonido.open(AudioSystem.getAudioInputStream(new File("explosion.wav")));
                    sonido.start();
                    while (sonido.isRunning()) Thread.sleep(5);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
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
        pintaExit(g);
        try{
            pixelMplus = Font.createFont(Font.TRUETYPE_FONT, new File("PixelMPlus10-Regular.ttf")).deriveFont(23f);	
        } catch(Exception e){}
        g.setFont(pixelMplus);
        if (jugadors[0] != null) g.drawString("Player 1: "+jugadors[0].puntuacio, 50, 60);
        if (jugadors[1] != null && f.mode == 2) g.drawString("Player 2: "+jugadors[1].puntuacio, 50, 90);
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

    void pintaExit(Graphics g) {
        for (int i=0; i<23; i++) {
            for (int j=0; j<19; j++) {
                if (exitMat[i][j] == 0) g.setColor(WHITE);
                if (exitMat[i][j] == -1) g.setColor(Color.GREEN);
                g.drawRect(1395+2*j, 35+2*i, 1, 1);
                g.fillRect(1395+2*j, 35+2*i, 1, 1);
           }
        }
    }
}
