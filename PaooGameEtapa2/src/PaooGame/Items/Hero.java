package PaooGame.Items;
import java.awt.*;
import java.util.Random;
import PaooGame.Graphics.Animation;
import PaooGame.RefLinks;
import PaooGame.Graphics.Assets;
import PaooGame.States.State;


public class Hero extends Character {
    private Animation image;    /*!< Referinta catre imaginea curenta a eroului.*/
    private boolean Attacking = false;
    public static int HP = 200;
    private int[] buttons = new int[4];
    int buttonsActiv = 0;
    int cooldown = 20;
    int cooldown1 = 0;
    Buton[] lista = new Buton[2];
    int curent;

    public Hero(RefLinks refLink, float x, float y) {
        ///Apel al constructorului clasei de baza
        super(refLink, x, y, Character.DEFAULT_CREATURE_WIDTH, Character.DEFAULT_CREATURE_HEIGHT);
        ///Seteaza imaginea de start a eroului
        image = Assets.heroRun;
        ///Stabilieste pozitia relativa si dimensiunea dreptunghiului de coliziune, starea implicita(normala)
        normalBounds.x = 16;
        normalBounds.y = 16;
        normalBounds.width = 16;
        normalBounds.height = 32;

        ///Stabilieste pozitia relativa si dimensiunea dreptunghiului de coliziune, starea de atac
        attackBounds.x = 10;
        attackBounds.y = 10;
        attackBounds.width = 38;
        attackBounds.height = 38;
        lista[0] = new Buton("Menu");
        lista[1] = new Buton("exit");
        curent = 0;
    }

    String modif = "";
    int buttonCooldown = 0;

    public void Hurt() {
        //left=protect right=attack down=attack up=run
        if (NegativHero.HP > 0) {
            if (buttonCooldown == 0) {
                if (refLink.GetKeyManager().down && buttons[3] == 1) {
                    NegativHero.HP -= 10;
                    modif = "-10Hp";
                    buttons[3] = 0;
                    buttonsActiv--;
                } else {
                    if (refLink.GetKeyManager().down && buttons[3] == 0) {
                        Hero.HP -= 5;
                    }
                }
                if (refLink.GetKeyManager().right && buttons[2] == 1) {
                    NegativHero.HP -= 30;
                    modif = "-30Hp";
                    buttons[2] = 0;
                    buttonsActiv--;
                } else {
                    if (refLink.GetKeyManager().right && buttons[2] == 0) {
                        Hero.HP -= 5;
                    }
                }

                if (refLink.GetKeyManager().left && buttons[1] == 1) {
                    Hero.HP += 20;
                    modif = "+20Hp";
                    buttons[1] = 0;
                    buttonsActiv--;
                } else {
                    if (refLink.GetKeyManager().left && buttons[1] == 0) {
                        Hero.HP -= 5;
                    }
                }

                if (refLink.GetKeyManager().up == true && buttons[0] == 1) {
                    NegativHero.HP -= 10;
                    Hero.HP += 10;
                    modif = "+10Hp";
                    buttons[0] = 0;
                    buttonsActiv--;
                } else {
                    if (refLink.GetKeyManager().up && buttons[0] == 0) {
                        Hero.HP -= 5;
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

    public void UpdateAnimation() {
        if (refLink.GetKeyManager().left == true) {
            image = Assets.heroBack;
            Attacking = false;
        }
        if (refLink.GetKeyManager().right == true) {
            image = Assets.heroFront;
            Attacking = true;
        }
        if (refLink.GetKeyManager().up == true) {
            image = Assets.heroRun;
            Attacking = false;
        }
        if (refLink.GetKeyManager().down == true) {
            image = Assets.heroDown;
            Attacking = true;
        }
    }

    @Override
    public void Update() {
        ///Verifica daca a fost apasata o tasta
        GetInput();
        Hurt();
        UpdateAnimation();
        UpdateButtons();
        Input();
    }

    /*! \fn private void GetInput()
        \brief Verifica daca a fost apasata o tasta din cele stabilite pentru controlul eroului.
     */
    private void GetInput() {
        ///Implicit eroul nu trebuie sa se deplaseze daca nu este apasata o tasta
        xMove = 0;
        yMove = 0;
        ///Verificare apasare tasta "sus"
        if (refLink.GetKeyManager().up) {
            yMove = -speed;
        }
        ///Verificare apasare tasta "jos"
        if (refLink.GetKeyManager().down) {
            yMove = speed;
        }
        ///Verificare apasare tasta "left"
        if (refLink.GetKeyManager().left) {
            xMove = -speed;
        }
        ///Verificare apasare tasta "dreapta"
        if (refLink.GetKeyManager().right) {
            xMove = speed;
        }
    }

    @Override
    public void Draw(Graphics g) {

        if (NegativHero.HP > 0) {
            if (Hero.HP > 0) {
                if (Attacking == false)
                    image.Draw(g, this.x, this.y, 100, 100);
                else
                    image.Draw(g, this.x - 27, this.y - 32, 200, 200);

                if (buttons[0] == 0)
                    g.drawImage(Assets.up, 50, 0, 50, 50, null);
                else
                    g.drawImage(Assets.ActUp, 50, 0, 50, 50, null);
                if (buttons[1] == 0)
                    g.drawImage(Assets.left, 100, 0, 50, 50, null);
                else
                    g.drawImage(Assets.Actleft, 100, 0, 50, 50, null);
                if (buttons[2] == 0)
                    g.drawImage(Assets.right, 150, 0, 50, 50, null);
                else
                    g.drawImage(Assets.ActRight, 150, 0, 50, 50, null);
                if (buttons[3] == 0)
                    g.drawImage(Assets.down, 200, 0, 50, 50, null);
                else
                    g.drawImage(Assets.ActDown, 200, 0, 50, 50, null);


                Font font = new Font("da", Font.TRUETYPE_FONT, 24);
                g.setFont(font);
                g.setColor(Color.BLACK);
                g.drawString(Integer.toString(HP), 50, 70);


                if (modif.contains("+")) {
                    g.setColor(Color.green);
                    g.drawString(modif, 90, 70);
                }

                if (modif.contains("-")) {
                    g.setColor(Color.red);
                    g.drawString(modif, 740, 70);
                }

            } else {

                buttons[0] = 0;
                buttons[1] = 0;
                g.drawImage(Assets.background, 0, 0, refLink.GetWidth(), refLink.GetHeight(), null);
                Font EndGame = new Font("da", Font.TRUETYPE_FONT, 40);
                g.setFont(EndGame);
                g.drawString("Ban: I'm far above you", 300, 100);
                g.drawString("You are a shame to demons", 300, 150);
                g.drawString("Don't fight again with me!", 300, 200);
                for (int i = 0; i < 2; i++) {
                    lista[i].Draw(g, 400, 70 * i + 250);
                }
                g.drawString("*", 370, curent * 70 + 250);
            }
        }
    }
}




