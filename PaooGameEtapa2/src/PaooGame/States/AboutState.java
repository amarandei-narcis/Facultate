package PaooGame.States;

import PaooGame.Graphics.Assets;
import PaooGame.Items.Buton;
import PaooGame.RefLinks;

import java.awt.*;

/*! \class public class AboutState extends State
    \brief Implementeaza notiunea de credentiale (about)
 */
public class AboutState extends State
{
    Buton[]lista=new Buton[1];
    int cooldown=0;
    public AboutState(RefLinks refLink)
    {
        super(refLink);
        lista[0]=new Buton("Back");
    }
    public  void Input(){
        if(cooldown==0) {

            if (refLink.GetKeyManager().Enter) {

                        State.SetState(refLink.GetGame().menuState);
                }
            cooldown=10;
        }
        else
            cooldown--;
    }
    @Override
    public void Update()
    {
        Input();
    }

    @Override
    public void Draw(Graphics g)
    {
        g.drawImage(Assets.background,0,0, refLink.GetWidth(),refLink.GetHeight(),null);
        Font font0=new Font("da",Font.LAYOUT_NO_LIMIT_CONTEXT,25);
        g.setFont(font0);
        g.drawString("Acest proiect a fost realizat de: Amarandei Narcis-Constantin",50,100);
        g.drawString("Profesor coordonator: Lupu Robert.",50,150);
        g.drawString("Jocul a fost creeat pentru a trece cu usurinta peste carantina.",50,200);
        g.drawString("Univeritatea Tehnica Gheorghe Asachi",50,250);
        Font font=new Font("da",Font.LAYOUT_NO_LIMIT_CONTEXT,40);
        g.setFont(font);
        lista[0].Draw(g,800,450);
        g.drawString("*",770,450);
    }
}
