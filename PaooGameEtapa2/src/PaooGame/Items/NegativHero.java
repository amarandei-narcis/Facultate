package PaooGame.Items;

import PaooGame.Graphics.Animation;
import PaooGame.Graphics.Assets;
import PaooGame.RefLinks;
import PaooGame.Items.Buton;
import PaooGame.States.State;

import java.awt.*;
import java.util.Random;

public class NegativHero extends Character {
    private Animation image;    /*!< Referinta catre imaginea curenta a eroului.*/
    private boolean Attacking = false;
    private int[] buttons = new int[4];
    public static int HP = 200;
    int buttonsActiv = 0;
    int cooldown = 20;
    int cooldown1 = 0;
    Buton[] lista = new Buton[2];
    int curent;

    public NegativHero(RefLinks refLink, float x, float y) {


        ///Apel al constructorului clasei de baza
        super(refLink, x, y, Character.DEFAULT_CREATURE_WIDTH, Character.DEFAULT_CREATURE_HEIGHT);
        ///Seteaza imaginea de start a eroului
        image = Assets.NegativheroRun;
        normalBounds.x = 16;
        normalBounds.y = 16;
        normalBounds.width = 16;
        normalBounds.height = 32;
        attackBounds.x = 10;
        attackBounds.y = 10;
        attackBounds.width = 38;
        attackBounds.height = 38;
        lista[0] = new Buton("Menu");
        lista[1] = new Buton("Exit");
        curent = 0;
    }

    /*! \fn public void Update()
        \brief Actualizeaza pozitia si imaginea eroului.
     */


    public void UpdateAnimation() {
        if (refLink.GetKeyManager().left2 == true) {
            image = Assets.NegativheroBack;
            Attacking = false;
        }
        if (refLink.GetKeyManager().right2 == true) {
            image = Assets.NegativheroFront;
            Attacking = true;
        }
        if (refLink.GetKeyManager().up2 == true) {
            image = Assets.NegativheroRun;
            Attacking = false;
        }
        if (refLink.GetKeyManager().down2 == true) {
            image = Assets.NegativheroDown;
            Attacking = true;
        }
    }


    /*! \fn private void GetInput()
        \brief Verifica daca a fost apasata o tasta din cele stabilite pentru controlul eroului.
     */
    private void GetInput() {
        ///Implicit eroul nu trebuie sa se deplaseze daca nu este apasata o tasta
        xMove = 0;
        yMove = 0;
        ///Verificare apasare tasta "sus"
        if (refLink.GetKeyManager().up2) {
            yMove = -speed;
        }
        ///Verificare apasare tasta "jos"
        if (refLink.GetKeyManager().down2) {
            yMove = speed;
        }
        ///Verificare apasare tasta "left"
        if (refLink.GetKeyManager().left2) {
            xMove = -speed;
        }
        ///Verificare apasare tasta "dreapta"
        if (refLink.GetKeyManager().right2) {
            xMove = speed;
        }
    }

    String modif = "";
    int buttonCooldown = 0;

    public void Hurt() {
        //left=protect right=attack down=attack up=run
        if (Hero.HP > 0) {
            if (buttonCooldown == 0) {
                if (refLink.GetKeyManager().down2 && buttons[3] == 1) {
                    Hero.HP -= 10;
                    modif = "-10Hp";
                    buttons[3] = 0;
                    buttonsActiv--;
                } else {
                    if (refLink.GetKeyManager().down2 && buttons[3] == 0) {
                        NegativHero.HP -= 5;
                    }
                }
                if (refLink.GetKeyManager().right2 && buttons[2] == 1) {
                    Hero.HP -= 30;
                    modif = "-30Hp";
                    buttons[2] = 0;
                    buttonsActiv--;
                } else {
                    if (refLink.GetKeyManager().right2 && buttons[2] == 0) {
                        NegativHero.HP -= 5;
                    }
                }
                if (refLink.GetKeyManager().left2 && buttons[1] == 1) {
                    NegativHero.HP += 20;
                    modif = "+20Hp";
                    buttons[1] = 0;
                    buttonsActiv--;
                } else {
                    if (refLink.GetKeyManager().left2 && buttons[1] == 0) {
                        NegativHero.HP -= 5;

                    }
                }
                if (refLink.GetKeyManager().up2 == true && buttons[0] == 1) {
                    NegativHero.HP += 10;
                    Hero.HP -= 10;
                    modif = "+10Hp";
                    buttons[0] = 0;
                    buttonsActiv--;
                } else {
                    if (refLink.GetKeyManager().up2 && buttons[0] == 0) {
                        NegativHero.HP -= 5;
                    }
                }

                if (Hero.HP < 0) {
                    Hero.HP = 0;
                }
                if (NegativHero.HP < 0) {
                    NegativHero.HP = 0;
                }

                buttonCooldown = 10;
            } else
                buttonCooldown--;
        }
    }

    public void Input() {
        if (cooldown1 == 0) {
            if (refLink.GetKeyManager().down) {
                if (curent == 1) {
                    curent = 0;
                } else
                    curent++;
            }
            if (refLink.GetKeyManager().up) {
                if (curent == 0) {
                    curent = 1;
                } else
                    curent--;
            }
            if (refLink.GetKeyManager().Enter) {
                switch (curent) {
                    case 0: {
                        State.SetState(refLink.GetGame().menuState);
                        Hero.HP=200;
                        NegativHero.HP=200;
                        modif="";
                        break;
                    }
                    case 1: {
                        refLink.GetGame().StopGame();
                        break;
                    }
                }
            }
            cooldown1 = 10;
        } else
            cooldown1--;
    }

    public void UpdateButtons() {

        if (cooldown == 0) {
            ActivateButton();
            cooldown = 120;
        } else {
            cooldown--;
        }

    }

    public void ActivateButton() {
        Random random = new Random();
        int test = random.nextInt(4);
        while (buttons[test] == 1 && buttonsActiv < 4) {
            test = random.nextInt(4);
        }
        buttons[test] = 1;
        buttonsActiv++;

    }

    @Override
    public void Update() {
        GetInput();
        Hurt();
        Input();
        UpdateAnimation();
        UpdateButtons();
    }

    @Override
    public void Draw(Graphics g) {

        if (Hero.HP > 0) {
            if (NegativHero.HP > 0) {
                if (Attacking == false)
                    image.Draw(g, this.x, this.y, 100, 100);
                else
                    image.Draw(g, this.x - 27, this.y - 32, 200, 200);

                if (buttons[0] == 0)
                    g.drawImage(Assets.up, 700, 0, 50, 50, null);
                else
                    g.drawImage(Assets.ActUp, 700, 0, 50, 50, null);
                if (buttons[1] == 0)
                    g.drawImage(Assets.left, 750, 0, 50, 50, null);
                else
                    g.drawImage(Assets.Actleft, 750, 0, 50, 50, null);
                if (buttons[2] == 0)
                    g.drawImage(Assets.right, 800, 0, 50, 50, null);
                else
                    g.drawImage(Assets.ActRight, 800, 0, 50, 50, null);
                if (buttons[3] == 0)
                    g.drawImage(Assets.down, 850, 0, 50, 50, null);
                else
                    g.drawImage(Assets.ActDown, 850, 0, 50, 50, null);

                Font font = new Font("da", Font.TRUETYPE_FONT, 24);
                g.setFont(font);
                g.setColor(Color.BLACK);
                g.drawString(Integer.toString(HP), 700, 70);
                if (modif.contains("+")) {
                    g.setColor(Color.green);
                    g.drawString(modif, 820, 70);
                }


                if (modif.contains("-")) {
                    g.setColor(Color.red);
                    g.drawString(modif, 170, 70);
                }


            } else {
                buttons[0] = 0;
                buttons[1] = 0;
                g.drawImage(Assets.background, 0, 0, refLink.GetWidth(), refLink.GetHeight(), null);
                Font EndGame = new Font("da", Font.TRUETYPE_FONT, 40);
                g.setFont(EndGame);
                g.drawString("Meliodas: I defeat you", 300, 100);
                g.drawString("You are nothing for me", 300, 150);
                g.drawString("HA HA HA!", 300, 200);
                for (int i = 0; i < 2; i++) {
                    lista[i].Draw(g, 400, 70 * i + 250);
                }
                g.drawString("*", 370, curent * 70 + 250);
            }
        }
    }
}



