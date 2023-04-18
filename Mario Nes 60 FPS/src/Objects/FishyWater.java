package Objects;

import Animations.FallingDeadSprites;
import SandBox.Mario;
import com.golden.gamedev.object.AnimatedSprite;
import com.golden.gamedev.object.Timer;

public class FishyWater extends AnimatedSprite implements BasicEnemy {

    // 4 types of fish
    // 1 straight : reg green
    // 2 up down : red green
    // red speed max 
    Mario game;
    double Xspeed = 1;
    String type = "grey";
    boolean UpDown = false;
    int Up = 0, Down = 0;
    boolean goingUp = true;

    public FishyWater(int x, int y, Mario g, int Type) {
        game = g;
        switch (Type) {
            case 1:
                Xspeed = -0.5;
                type = "grey";
                UpDown = false;
                setImages(game.bsLoader.getStoredImages("FishGrey"));
                setAnimationTimer(new Timer(200));
                break;
            case 2:
                Xspeed = -0.5;
                type = "grey";
                UpDown = true;
                setImages(game.bsLoader.getStoredImages("FishGrey"));
                setAnimationTimer(new Timer(200));
                break;
            case 3:
                Xspeed = -1;
                type = "red";
                UpDown = false;
                setImages(game.bsLoader.getStoredImages("FishRed"));
                setAnimationTimer(new Timer(100));
                break;
            case 4:
                Xspeed = -1;
                type = "red";
                UpDown = true;
                setImages(game.bsLoader.getStoredImages("FishRed"));
                setAnimationTimer(new Timer(100));
                break;

        }
        Up = y - 32;
        Down = y + 32;
        setLocation(x, y);

        setAnimate(true);
        setLoopAnim(true);
        this.setAnimationFrame(0, 1);


        this.setID(112);
    }

    @Override
    public void update(long l) {

        moveX(Xspeed);


        if (UpDown) {

            if (this.getY() < Up) {
                goingUp = false;

            }
            if (this.getY() > Down) {
                goingUp = true;

            }

            if (goingUp) {
                this.moveY(-0.5);
            }
            if (!goingUp) {
                this.moveY(0.5);
            }


        }

        super.update(l);
    }

    @Override
    public void MarioJumpedOnEnemy() {

        if (game.player.HasStar()) {
            KilledByFireBall();
        } else {
            game.player.Decerease();
        }

    }

    @Override
    public void KilledByFireBall() {
         game.parent.amitsAudioPlayer.smb_kick.play();
        game.AnimationGroup.add(new FallingDeadSprites(this.getX(), this.getY(), this.getImage(), MariotoRight()));
        this.setActive(false);

    }

    @Override
    public void bounce() {
    }

    @Override
    public void setYloc(double d) {
    }

    @Override
    public void CollidedWithShell() {
        // change direction
    }

    @Override
    public int getType() {
        return this.getID();
    }

    @Override
    public void CollidedWithMovingShell() {
        KilledByFireBall();
    }

    @Override
    public void CollidedWithBrick_GoToLeft() {
    }

    @Override
    public void CollidedWithBrick_GoToRight() {
    }

    @Override
    public void OtherEnemyTouchedFromRight() {
    }

    @Override
    public void OtherEnemyTouchedFromLeft() {
    }

    @Override
    public void CollidedWithMarioFromTOLeft() {
        MarioJumpedOnEnemy();
    }

    @Override
    public void CollidedWithMarioTORight() {
        MarioJumpedOnEnemy();
    }

    @Override
    public void EnemyJumperOnMario() {
        MarioJumpedOnEnemy();
    }

    @Override
    public int Life() {
        return 0;
    }

    @Override
    public void CollidedWithJumping_Brick() {
    }

    @Override
    public boolean MariotoRight() {
        boolean positive;
        if (game.player.getX() < this.getX()) {
            positive = false;
        } else {
            positive = true;
        }

        return positive;
    }
}