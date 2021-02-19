package PaooGame.Graphics;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Animation {
    int nrFrame;
    int noFrame=0;
    int curentFrame=0;
    BufferedImage[]Frame;
    Animation(int nrFrame){
        this.nrFrame= nrFrame;
        Frame=new BufferedImage[nrFrame];
        this.nrFrame=0;
    };

    public void AddImage(BufferedImage img){
        Frame[nrFrame]=img;
        nrFrame++;
    }

    public void Draw(Graphics g, float x, float y, int width, int height){
        Update();
        g.drawImage(Frame[curentFrame],(int)x,(int)y,width,height,null);

    }
    public void Update(){

        if(noFrame==10) {
            if (curentFrame == nrFrame - 1) {
                curentFrame = 0;
            } else {
                curentFrame++;

            }
            noFrame=0;

        }
        else
            noFrame++;
    }
}
