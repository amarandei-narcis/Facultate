package PaooGame.Items;

import java.awt.*;

public class Buton {
    String nume;

    public Buton(String nume){
        this.nume=nume;
    }
    public void Draw(Graphics g,int x,int y){
        g.drawString(nume,x,y);
    }
}
