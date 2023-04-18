package Objects;

import Animations.BigToFireMarioAnim;
import Animations.BigToSmallMarioAnim;
import Animations.Bubble;
import Animations.FallingDeadMario;
import Animations.FireToSmallMarioAnim;
import Animations.SmallToBigMarioAnim;
import Animations.SmallToBigStarMaroAnim;
import SandBox.Mario;
import com.golden.gamedev.object.AnimatedSprite;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.Timer;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Player extends AnimatedSprite {

    public boolean Water = false;
    int swimmDelay = 4;
    int delay = 500; // time to play
    int Fps = 2; // prame play per second
    BufferedImage[] BigBlackMario = new BufferedImage[12];
    BufferedImage[] FirePlayer = new BufferedImage[12];
    BufferedImage[] BigGreenMario = new BufferedImage[12];
    BufferedImage[] BigRedMario = new BufferedImage[12];
    private BufferedImage[] SmallBlackMario = new BufferedImage[12];
    private BufferedImage[] player2 = new BufferedImage[12];
    private BufferedImage[] SmallGreenMario = new BufferedImage[12];
    private BufferedImage[] SmallRedMario = new BufferedImage[12];
    private int currentFrame = 1;
    int type = 1; // small mario gets star , 2 = small to big
    int Frame = 1;
    double something = 1;
    public int speed = 0;
    private double Double_speed;
    boolean breaks = true; // like vehical break
    public boolean OnGround = true;
    double Gravity = 0;
    boolean turbo = false;
    private boolean LeftActivated = false;
    private boolean RightActivated = true;
    private boolean skating = false;
    Mario game;
    int invincible = 0;
    boolean blink = true;
    private int Life = 0;
    private boolean ControlleByKeyboard = true;
    ;
    private boolean AutomaticMoveForward = false;
    ;
    private boolean Star;
    public boolean KeyPressedDown = false;
    public boolean KeyPressedRight = false;
    private int BubbleDelay;
    Timer save;
    private boolean OnLift = false;
    public boolean KeyPressedUP;

    public Player(int x, int y, BufferedImage[] Player_Image, Mario g) {

        save = new Timer(1000);

        setLocation(x, y);
        setImages(Player_Image);
        setAnimate(false);

        game = g;

        this.setID(game.parent.MarioStatus);
        this.SetStatus(game.parent.MarioStatus);
        // System.out.println("game.parent.MarioStatus "+game.parent.MarioStatus +"Player getID "+this.getID());

        //
        BigBlackMario = game.bsLoader.getStoredImages("BigBlackMario");
        FirePlayer = game.bsLoader.getStoredImages("FirePlayer");
        BigGreenMario = game.bsLoader.getStoredImages("BigGreenMario");
        BigRedMario = game.bsLoader.getStoredImages("BigRedMario");

        SmallBlackMario = game.bsLoader.getStoredImages("SmallBlackMario");
        player2 = game.bsLoader.getStoredImages("player");
        SmallGreenMario = game.bsLoader.getStoredImages("SmallGreenMario");
        SmallRedMario = game.bsLoader.getStoredImages("SmallRedMario");

        //
    }

    // Most important part
    @Override
    public void update(long l) {
        // gravity
//        KeyPressedDown = false ;

        if (save.action(l)) {
            if (this.OnGround & !OnLift) {
                Sprite dis = new Sprite(game.PlayetX, game.PlayerY);

                if (this.getDistance(dis) > 1000) {
                    game.PlayetX = (int) this.getX();
                    game.PlayerY = (int) this.getY();
                }
            }
        }
        OnLift = false;
        if (Water) {

            // bubbles
            AddBubbles();

            if (!this.OnGround) {
                swimmDelay--;
                if (swimmDelay < 0) {
                    Swim();
                    WaterJump();
                    swimmDelay = 4;
                }
            } else {
                if (this.speed > 30) {
                    this.speed = 30;
                } else if (this.speed < -30) {
                    this.speed = -30;
                }
            }

            if (this.getY() < 32) {
                Gravity = 2;
            }

            if (this.getY() < 64) {
                this.setY(64);
            }
        }
        if (invincible > 0) { // decerease it
            invincible--;
        }

        AutomaticGoRight();

        Double_speed = (double) speed / 20;

        this.moveX(Double_speed);
//        System.out.println(Double_speed);
        Gravity();

        this.moveY(Gravity);

        if (!Water) {

            if (breaks) {
                if (speed < 0) {
                    speed += 1;
                } else if (speed > 0) {
                    speed -= 1;

                }
            }
        } else if (Water & !OnGround) {
        } else if (OnGround) {
            if (breaks) {
                if (speed < 0) {
                    speed += 1;
                } else if (speed > 0) {
                    speed -= 1;

                }
            }
        }

        something = something + speed;

        if (OnGround) {

            if (something > 160) {
                something = 0;
                AdvanceFrame();
            }
            if (something < -160) {
                something = 0;
                DeAdvanceFrame();
            }

        }
        if (speed == 0) {

            if (LeftActivated && OnGround) {
                this.setFrame(1);
            } else if (RightActivated && OnGround) {
                this.setFrame(0);
            }

        }

        if (this.KeyPressedDown) {
            if (LeftActivated) {
                this.setFrame(25);
            } else if (RightActivated) {
                this.setFrame(24);
            }
        }

        OnGround = false;
        breaks = true;

        super.update(l);

    }

    private void nextFrame() {

        if (currentFrame == 1) {
            currentFrame = 2;
        } else if (currentFrame == 2) {
            currentFrame = 3;
        } else if (currentFrame == 3) {
            currentFrame = 4;
        } else if (currentFrame == 4) {
            currentFrame = 1;
        }

    }

    @Override
    public void render(Graphics2D g) {

        if (invincible > 0) { // blink

            if (blink) {
                super.render(g);

            }

            if (blink) {
                blink = false;
            } else {
                blink = true;
            }
        } else {
            super.render(g);
        }

        if (Star) {
            // star

            Fps--;
            if (Fps < 0) {
                nextFrame();
                if (delay > 200 & delay < 510) {
                    Fps = 2;
                } else if (delay > 100 & delay < 200) {
                    Fps = 5;
                } else if (delay > -10 & delay < 100) {
                    Fps = 10;
                }

            }

            delay--;
            if (delay < 0) {
                game.player.HasStar(false);
                game.parent.amitsAudioPlayer.stopAllMusic();
                game.parent.amitsAudioPlayer.playMusic( game.CurrentLevel.attribute );
            }

            if (this.getID() == 1) {  // 

                if (currentFrame == 1) {
                    g.drawImage(SmallBlackMario[game.player.getFrame()], null, (int) getScreenX(), (int) getScreenY());
//                    this.setLocation(game.player.getX(),  (int)game.player.getY());
                } else if (currentFrame == 2) {
                    g.drawImage(player2[game.player.getFrame()], null, (int) getScreenX(), (int) getScreenY());

                } else if (currentFrame == 3) {
                    g.drawImage(SmallGreenMario[game.player.getFrame()], null, (int) getScreenX(), (int) getScreenY());

                } else if (currentFrame == 4) {
                    g.drawImage(SmallRedMario[game.player.getFrame()], null, (int) getScreenX(), (int) getScreenY());

                }

            } else {
                if (currentFrame == 1) {
                    g.drawImage(BigBlackMario[game.player.getFrame()], null, (int) getScreenX(), (int) getScreenY());

                } else if (currentFrame == 2) {
                    g.drawImage(FirePlayer[game.player.getFrame()], null, (int) getScreenX(), (int) getScreenY());

                } else if (currentFrame == 3) {
                    g.drawImage(BigGreenMario[game.player.getFrame()], null, (int) getScreenX(), (int) getScreenY());

                } else if (currentFrame == 4) {
                    g.drawImage(BigRedMario[game.player.getFrame()], null, (int) getScreenX(), (int) getScreenY());

                }

            }

        }
        //*********
//        g.setColor(Color.WHITE);
//                g.drawString("KeyPressRight " +KeyPressRight() + "        OnGround " + OnGround, 100, 10 );
//        g.drawString("speed " + this.speed , 100, 10 );
//        g.drawString("player pos  " + (int)this.getX()/32 , 100, 30 );
    }

    public void GoToLeft() {

        if (ControlleByKeyboard & !KeyPressedDown) {

            if (speed > 0) {
                if (this.OnGround) {
                    this.setFrame(11);
                    skating = true;
                }
            } else {
                if (this.OnGround) {
                    skating = false;
                }
            }
            LeftActivated = true;
            RightActivated = false;
            if (turbo) {
                if (speed < -100) {
                    speed = -100;
                } else {
                    speed -= 2;
                }
            } else {

                if (speed < -60) {
                    speed = -60;
                } else {
                    speed -= 2;
                }
            }

            breaks = false;

        }
    }

    public void GoToRight() {

        KeyPressedRight = true;
        if (ControlleByKeyboard & !KeyPressedDown) {

            if (speed < 0) { // means player is negative speed going to left <<<<
                if (this.OnGround) {
                    this.setFrame(7);
                    skating = true;
                }
            } else { // else positive speed going to right >>>>
                if (this.OnGround) {
                    skating = false;
                }
            }

            RightActivated = true;
            LeftActivated = false;
            if (turbo) {
                if (speed > 100) { // original was 80
                    speed = 100; // original was 80
                } else {
                    speed += 2;
                }
            } else {
                if (speed > 60) {// original was 40
                    speed = 60;// original was 40
                } else {
                    speed += 2;
                }
            }

            breaks = false;

        }
    }

    public void Down() {
        // release left and right keys 
        KeyPressedDown = true;

        if (this.getID() != 1) {  // big marios
            this.setFrame(24);
        }
    }

    public void Up() {
        KeyPressedUP = true;
    }

    public void Jump() {

        // System.out.println("Water");
        if (ControlleByKeyboard) {

            if (Water) {
                Gravity = -3.5;
            } else {

                if (OnGround) {
                    if (speed > 0) {
                        Gravity = -11 - ((double) speed / 60);
                    } else if (speed < 0) {
                        Gravity = -11 + ((double) speed / 60);
                    } else {
                        Gravity = -11;
                    }

                    if (getID() != 0) {   // if not dead mario

                        if (this.getID() == 1) {
                            game.parent.amitsAudioPlayer.smb_jump_small.play();
                        } else {
                            game.parent.amitsAudioPlayer.smb_jump_super.play();
                        }

                    }

                }
                if (LeftActivated && !OnGround) {
                    this.setFrame(3);
                } else if (RightActivated && !OnGround) {
                    this.setFrame(2);
                }

            }

        }
    }

    public void OnGround() {
        OnGround = true;
        Gravity = 0;

    }

    public void StopSpeed() {
        speed = 0;
    }

    private void Gravity() {

        if (Water) {
            if (Gravity < 2) {
                Gravity = Gravity + 0.1;
            }
        } else {
            if (Gravity < 10) {
                Gravity = Gravity + 0.42;
            }
        }
    }

    public boolean ComingDown() {
        if (Gravity < 0) {
            return false;
        } else {
            return true;
        }
    }

    public void Jump(int i) {

        Gravity = i;
        game.parent.amitsAudioPlayer.smb_stomp.play();
    }

    public void GoDown() {

        Gravity = 0;

    }

    public boolean isFalling() {
        if (Gravity < 0) {
            return false;
        } else {
            return true;
        }
    }

    public void Speed(boolean b) {

        if (ControlleByKeyboard) {
            if (Water) {
                turbo = false;
            } else {
                turbo = b;
            }
        }
    }

    private void AdvanceFrame() {
        if (Frame == 4) {
            Frame = 5;
        } else if (Frame == 5) {
            Frame = 6;
        } else if (Frame == 6) {
            Frame = 4;
        } else {
            Frame = 4;
        }

        if (!skating) {
            this.setFrame(Frame);
        }
//        // System.out.println(Frame);
    }

    private void DeAdvanceFrame() {
        if (Frame == 8) {
            Frame = 9;
        } else if (Frame == 9) {
            Frame = 10;
        } else if (Frame == 10) {
            Frame = 8;
        } else {
            Frame = 8;
        }

        if (!skating) {
            this.setFrame(Frame);
        }
    }

    public void Grow() {

        // PowerUpType
        // 3 = mushroom
        // 4 = flower
        // but when growing pause everything in game 
        // add mario growing animation 
        if (this.getID() == 1) {  // if small mario

            this.setID(2);      // big mario
            game.parent.MarioStatus = 2;
            game.pauseEnemys();                    // small to big animation expect animation group
            this.setY(this.getY() - 32);
            if (this.Star) {
                game.AnimationGroup.add(new SmallToBigStarMaroAnim(this.getX(), this.getY(), game.bsLoader.getStoredImages("SmallToBigStarMaroAnim"), game, RightActivated));
            } else {
                game.AnimationGroup.add(new SmallToBigMarioAnim(this.getX(), this.getY(), game.bsLoader.getStoredImages("SmallToBigMarioAnim"), game, RightActivated));
            }
            game.parent.amitsAudioPlayer.smb_powerup.play();

        } else if (this.getID()
                == 2) {  // if big mario
            this.setID(3);      // big mario
            game.parent.MarioStatus = 3;
            game.pauseEnemys();                    // big to fire animation expect animation group

            game.AnimationGroup.add(new BigToFireMarioAnim(this.getX(), this.getY(), game));
            game.parent.amitsAudioPlayer.smb_powerup.play();
        } else if (this.getID()
                == 3) {  // if fire mario
            this.setID(3);      // big mario
            game.parent.MarioStatus = 3;
            // no animation
            game.parent.amitsAudioPlayer.smb_powerup.play();

        }
    }

    public void Decerease() {

        if (invincible <= 0) {

            if (this.getID() == 1) {  // if small mario
                this.setID(0);      // dead mario
                game.parent.MarioStatus = 0;
                game.pauseEnemys();                    // small to big animation expect animation group

                game.AnimationGroup.add(new FallingDeadMario(this.getX(), this.getY(), game));
                game.parent.amitsAudioPlayer.smb_mariodie.play();
                game.parent.amitsAudioPlayer.stopAllMusic();
            } else if (this.getID() == 2) {  // if big mario
                this.setID(1);      // small mario
                game.parent.MarioStatus = 1;
                game.pauseEnemys();                    // big to fire animation expect animation group

                game.AnimationGroup.add(new BigToSmallMarioAnim(this.getX(), this.getY(), game.bsLoader.getStoredImages("BigToSmallMarioAnim"), game, RightActivated));

                invincible = 400;
                game.parent.amitsAudioPlayer.smb_pipe.play();
            } else if (this.getID() == 3) {  // if fire mario
                this.setID(1);      // big mario
                game.parent.MarioStatus = 1;
                game.pauseEnemys();                    // big to fire animation expect animation group

                game.AnimationGroup.add(new FireToSmallMarioAnim(this.getX(), this.getY(), game.bsLoader.getStoredImages("FireToSmallMarioAnim"), game, RightActivated));

                invincible = 400;
                game.parent.amitsAudioPlayer.smb_pipe.play();
            }

        }
    }

    public boolean isInvincible() {
        if (invincible > 0) {
            return true;
        } else {
            return false;
        }
    }

    public void setInvincible() {
        this.invincible = 100;
    }

    public void Fire() {
        game.FireBall.removeImmutableSprites();
//        // System.out.println(game.FireBall.getSize());
        if (this.getID() == 3) {
            if (game.FireBall.getSize() < 2) {
                game.FireBall.add(new FireBall(this.getX(), this.getY(), RightActivated, game.bsLoader.getStoredImages("FireBall"), game));
                game.parent.amitsAudioPlayer.smb_fireball.play();
            }
        }

    }

    public boolean HasStar() {
        return Star;
    }

    void IncreaseLife() {
//        Life++;
    }

    private void AutomaticGoRight() {
        if (AutomaticMoveForward) {

            // automatic
            if (speed < 0) {
                this.setFrame(7);
                skating = true;
            } else {
                skating = false;
            }

            RightActivated = true;
            LeftActivated = false;
            {
                if (speed > 40) {
                    speed = 40;
                } else {
                    speed += 1;
                }
            }

            breaks = false;

        }

    }

    public void NoControlling() {
        ControlleByKeyboard = false;
        speed = 0;
        this.Gravity = 0;
    }

    public void MoveForward() {

//        if(this.OnGround){
        AutomaticMoveForward = true;
//        }
    }

    public void StopMoveForward() {
        AutomaticMoveForward = false;
        speed = 0;
        this.Gravity = 0;
    }

    public boolean KeyPressedDown() {
        return KeyPressedDown;
    }

    public void ResetStatus() {

        invincible = 400;
        Frame = 1;
        something = 1;

        speed = 0;
        breaks = true;
        OnGround = true;

        Gravity = 0;

        turbo = false;
        LeftActivated = false;
        RightActivated = true;
        skating = false;

        blink = true;
        Star = false;
        Life = 0;
        ControlleByKeyboard = true;;
        AutomaticMoveForward = false;;
        KeyPressedDown = false;

        game.getPlayer().setImages(game.bsLoader.getStoredImages("player"));
        this.setID(1);
        game.parent.MarioStatus = 1;

    }

    public void SetStatus(int MarioStatus) {
        if (MarioStatus == 1) {  // if small mario
            this.setID(MarioStatus);      // big mario
            this.setY(this.getY() - 32);

        } else if (MarioStatus == 2) {  // if big mario
            this.setID(MarioStatus);      // big mario
            setImages(game.bsLoader.getStoredImages("BigPlayer"));
        } else if (MarioStatus == 3) {  // if fire mario
            this.setID(MarioStatus);      // big mario
            setImages(game.bsLoader.getStoredImages("FirePlayer"));
            // no animation
        } else if (MarioStatus == 4) {  // if fire mario
            this.setID(MarioStatus);      // big mario
            setImages(game.bsLoader.getStoredImages("SmallRedMario"));
            // no animation
        }
    }

    public void STAR() {
        // System.out.println();
        Star = true;
        delay = 1000;
        //
        game.parent.amitsAudioPlayer.stopAllMusic();
//        game.bsMusic.play("music/Star.mp3");
        game.parent.amitsAudioPlayer.playMusic("Star");

        game.parent.amitsAudioPlayer.smb_powerup.play();

    }

    public void HasStar(boolean b) {
        Star = false;
    }

    private void Swim() {

        if (this.isFalling()) {
            if (this.LeftActivated) {
                if (this.getFrame() == 18) {
                    this.setFrame(19);
                } else if (this.getFrame() == 19) {
                    this.setFrame(18);
                } else {
                    this.setFrame(18);
                }
            } else if (this.RightActivated) {
                if (this.getFrame() == 16) {
                    this.setFrame(17);
                } else if (this.getFrame() == 17) {
                    this.setFrame(16);
                } else {
                    this.setFrame(16);
                }
            }
        }

    }

    private void WaterJump() {

        if (!this.isFalling() & !this.OnGround) {
            if (this.LeftActivated) {
                if (this.getFrame() == 22) {
                    this.setFrame(23);
                } else if (this.getFrame() == 23) {
                    this.setFrame(22);
                } else {
                    this.setFrame(22);
                }
            } else if (this.RightActivated) {
                if (this.getFrame() == 20) {
                    this.setFrame(21);
                } else if (this.getFrame() == 21) {
                    this.setFrame(20);
                } else {
                    this.setFrame(20);
                }
            }
        }

    }

    private void AddBubbles() {
        BubbleDelay--;
        if (BubbleDelay < 0) {
            game.BackGroundSpriteGroup.add(new Bubble((int) this.getX(), (int) this.getY(), game));
            BubbleDelay = com.golden.gamedev.util.Utility.getRandom(1, 2) * 60;
            // System.out.println("AddBubbles");
        }

    }

    public void OnLift() {
        OnLift = true;
    }

    public boolean KeyPressRight() {
        return this.KeyPressedRight;
    }
}
