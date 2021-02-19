package PaooGame.States;

import PaooGame.Graphics.Assets;
import PaooGame.Items.Buton;
import PaooGame.RefLinks;

import java.awt.*;


public class MenuState extends State
{

    Buton []lista=new Buton[4];
    int curent;
    int cooldown=0;
    public MenuState(RefLinks refLink)
    {
        ///Apel al constructorului clasei de baza.
        super(refLink);
        lista[0]=new Buton("Start Game");
        lista[1]=new Buton("About");
        lista[2]=new Buton("Settings");
        lista[3]=new Buton("Exit");
        curent=0;


    }
    public  void Input(){
        if(cooldown==0) {
            if (refLink.GetKeyManager().down) {
                if(curent==3){
                    curent=0;
                }
                else
                    curent++;
            }
            if (refLink.GetKeyManager().up) {
                if(curent==0){
                    curent=3;
                }
                else
                    curent--;
            }
            if (refLink.GetKeyManager().Enter) {
                switch (curent){
                    case 0:{
                        State.SetState(refLink.GetGame().playState);
                        break;

                    }
                    case 1:{
                        State.SetState(refLink.GetGame().aboutState);
                        break;
                    }
                    case 2:{
                        State.SetState(refLink.GetGame().settingsState);
                        break;
                    }
                    case 3:{
                        refLink.GetGame().StopGame();
                        break;
                    }
                }
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

    /*! \fn public void Draw(Graphics g)
        \brief Deseneaza (randeaza) pe ecran starea curenta a meniului.

        \param g Contextul grafic in care trebuie sa deseneze starea jocului pe ecran.
     */
    @Override
    public void Draw(Graphics g)
    {
        g.drawImage(Assets.background,0,0, refLink.GetWidth(),refLink.GetHeight(),null);
        Font font=new Font("da",Font.TRUETYPE_FONT,40);
        g.setFont(font);
        for (int i=0;i<4;i++){
            lista[i].Draw(g,400,70*i+150);
        }
        g.drawString("*",370,curent*70+150);
    }
}
