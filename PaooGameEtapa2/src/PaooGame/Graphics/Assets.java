package PaooGame.Graphics;

import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

/*! \class public class Assets
    \brief Clasa incarca fiecare element grafic necesar jocului.

    Game assets include tot ce este folosit intr-un joc: imagini, sunete, harti etc.
 */
public class Assets
{
    public static BufferedImage up;
    public static BufferedImage down;
    public static BufferedImage left;
    public static BufferedImage right;

    public static BufferedImage ActUp;
    public static BufferedImage ActDown;
    public static BufferedImage Actleft;
    public static BufferedImage ActRight;
    public static int defaultHight=70;
    public static int defaultWidth=52;
    public static int defaultWidthAttack=100;
    public static int defaultHightAttack=140;
        /// Referinte catre elementele grafice (dale) utilizate in joc.



    public static BufferedImage background;

    public static Animation heroRun=new Animation(6);
    public static Animation heroBack=new Animation(3);
    public static Animation heroFront=new Animation(3);
    public static Animation heroDown=new Animation(3);
    public static Animation heroDmg=new Animation(1);


    public static Animation NegativheroRun=new Animation(6);
    public static Animation NegativheroBack=new Animation(3);
    public static Animation NegativheroFront=new Animation(3);
    public static Animation NegativheroDown=new Animation(3);
    public static Animation NegativheroDmg=new Animation(1);
    /*! \fn public static void Init()
        \brief Functia initializaza referintele catre elementele grafice utilizate.

        Aceasta functie poate fi rescrisa astfel incat elementele grafice incarcate/utilizate
        sa fie parametrizate. Din acest motiv referintele nu sunt finale.
     */


    public static BufferedImage flip(BufferedImage sprite){
        BufferedImage img = new BufferedImage(sprite.getWidth(),sprite.getHeight(),BufferedImage.TYPE_INT_ARGB);
        for(int xx = sprite.getWidth()-1;xx>0;xx--){
            for(int yy = 0;yy < sprite.getHeight();yy++){
                img.setRGB(sprite.getWidth()-xx, yy, sprite.getRGB(xx, yy));
            }
        }
        return img;
    }



    public static void Init()
    {
            /// Se creaza temporar un obiect SpriteSheet initializat prin intermediul clasei ImageLoader
        SpriteSheet sheet = new SpriteSheet(ImageLoader.LoadImage("/textures/joc.png"));

        background=sheet.crop(718,0,270,280);

        up=sheet.crop(676,852);
        down=sheet.crop(730,852);
        left=sheet.crop(676,924);
        right=sheet.crop(730,924);


        ActUp=sheet.crop(780,852);
        ActDown=sheet.crop(832,852);
        Actleft =sheet.crop(780,923);
        ActRight=sheet.crop(832,923);


        heroRun.AddImage(sheet.crop(0,0));
        heroRun.AddImage(sheet.crop(54,0));
        heroRun.AddImage(sheet.crop(108,0));
        heroRun.AddImage(sheet.crop(162,0));
        heroRun.AddImage(sheet.crop(216,0));
        heroRun.AddImage(sheet.crop(260,0));
        heroBack.AddImage(sheet.crop(0,288));
        heroBack.AddImage(sheet.crop(54,288));
        heroBack.AddImage(sheet.crop(108,288));
        heroFront.AddImage(sheet.cropAttack(312,852));
        heroFront.AddImage(sheet.cropAttack(417,852));
        heroFront.AddImage(sheet.cropAttack(522,852));
        heroDown.AddImage(sheet.cropAttack(676,284));
        heroDown.AddImage(sheet.cropAttack(781,284));
        heroDown.AddImage(sheet.cropAttack(886,284));
        heroDmg.AddImage(sheet.crop(572,288));




        NegativheroRun.AddImage(flip(sheet.crop(0,72)));
        NegativheroRun.AddImage(flip(sheet.crop(54,72)));
        NegativheroRun.AddImage(flip(sheet.crop(108,72)));
        NegativheroRun.AddImage(flip(sheet.crop(162,72)));
        NegativheroRun.AddImage(flip(sheet.crop(216,72)));
        NegativheroRun.AddImage(flip(sheet.crop(260,72)));
        NegativheroBack.AddImage(flip(sheet.crop(0,360)));
        NegativheroBack.AddImage(flip(sheet.crop(54,360)));
        NegativheroBack.AddImage(flip(sheet.crop(108,360)));
        NegativheroFront.AddImage(flip(sheet.cropAttack(312,709)));
        NegativheroFront.AddImage(flip(sheet.cropAttack(417,709)));
        NegativheroFront.AddImage(flip(sheet.cropAttack(522,709)));
        NegativheroDown.AddImage(flip(sheet.cropAttack(676,568)));
        NegativheroDown.AddImage(flip(sheet.cropAttack(781,568)));
        NegativheroDown.AddImage(flip(sheet.cropAttack(886,568)));
        NegativheroDmg.AddImage(flip(sheet.crop(572,360)));
           }
}
