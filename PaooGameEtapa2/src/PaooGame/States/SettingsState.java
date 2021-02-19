package PaooGame.States;

import PaooGame.Graphics.Assets;
import PaooGame.RefLinks;

import java.awt.*;

/*! \class public class SettingsState extends State
    \brief Implementeaza notiunea de settings pentru joc.

    Aici setarile vor trebui salvate/incarcate intr-un/dintr-un fisier/baza de date sqlite.
 */
public class SettingsState extends State
{
    public SettingsState(RefLinks refLink)
    {
            ///Apel al construcotrului clasei de baza.
        super(refLink);
    }

    @Override
    public void Update()
    {

    }

    @Override
    public void Draw(Graphics g)
    {
        g.drawImage(Assets.background,0,0, refLink.GetWidth(),refLink.GetHeight(),null);
    }
}
