import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public abstract class Nau { 
    int x, y, vx, vy, vida, intMisil, puntuacio, PIXEL_SIZE=3;
    ArrayList<Bala> bales = new ArrayList<Bala>();
    ArrayList<Misil> misil = new ArrayList<Misil>();
    Color LIGHT_RED = new Color(255, 51, 51);  
    Color DARK_RED = new Color(204, 0, 0);
    Color LIGHT_GREY = new Color(204, 204, 204);
    Color GREY = new Color(153, 153, 153);
    Color DARK_GREY = new Color(95, 95, 95);
    Color VERY_DARK_GREY = new Color(51, 51, 51); 
    Color EVIL_DARK_GREY = new Color(45, 45, 45); 
    Color VERY_LIGHT_BLUE = new Color(51, 204, 255);
    Color LIGHT_BLUE = new Color(51, 153, 255); 
    Color DARK_BLUE = new Color(0, 0, 204);
    Color VERY_DARK_BLUE = new Color(0, 0, 153);
    Color LIGHT_YELLOW = new Color(255, 255, 153);
    Color GOLD = new Color(255, 204, 51);
    Color LIGHT_ORANGE = new Color(255, 153, 0);
    Color ORANGE = new Color(255, 102, 0);
    Color LIGHT_BROWN = new Color(153, 102, 0);
    Color BROWN = new Color(102, 51, 0);
    Color WHITE = new Color(255, 255, 255);
    Color TRANSPARENT =new Color(0f, 0f, 0f, 0f);

    abstract void moure();
    abstract void disparar();
    void disparaMisil() {}
    void explosio(Graphics g) {}
    void xocNau(Nau n) {}
    void xocAst(Asteroide a) {}
    abstract boolean alcanceBala(Nau enemic);
    boolean alcanceMisil(Nau enemic) {return false;}
    abstract void propulsio(Graphics g);
    abstract void pinta(Graphics g);
    boolean alcanceBonus(Bonus b) {return false;}
}
