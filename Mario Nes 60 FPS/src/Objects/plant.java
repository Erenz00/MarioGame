package Objects;

import Animations.FallingDeadSprites;
import SandBox.Mario;
import com.golden.gamedev.object.AnimatedSprite;
import com.golden.gamedev.object.Timer;
import java.awt.image.BufferedImage;

public class plant extends AnimatedSprite implements BasicEnemy {

    Mario game;
    int Upheight = 48;
    int DownHeight = 48;
    boolean MoveUp = true;
    boolean MarioIsNear = false;
    boolean CanStopMovingUp = false;

    public plant(BufferedImage storedImage[], int x, int y, Mario g) {
        this.setLocation(x, y);
        this.setID(110);

        game = g;

        this.setImages(storedImage);

        Upheight = y - 96;
        DownHeight = y;

        setAnimationTimer(new Timer(300));
        setAnimate(true);
        setLoopAnim(true);
    }

    @Override
    public void update(long l) {

        if (this.getY() < Upheight) {
            MoveUp = false;
        }
        if (this.getY() > DownHeight) {

            MoveUp = true;

        }

        if (MoveUp) {

            if (game.player.getX() > this.getX() - 100 & game.player.getX() < this.getX() + 100) {
                MarioIsNear = true;
            } else {
                MarioIsNear = false;
            }

            if (this.getY() > DownHeight - 32) { // is down
                CanStopMovingUp = true;
            } else {
                CanStopMovingUp = false;
            }

            if (MarioIsNear && CanStopMovingUp) {
            } else {
                this.moveY(-0.5); // moving  up
            }
//            }
        } else {
            this.moveY(0.5);
        }

        super.update(l);

    }

    @Override
    public void CollidedWithBrick_GoToLeft() {
    }

    @Override
    public void CollidedWithBrick_GoToRight() {
    }

    @Override
    public int getType() {
        return this.getID();
    }

    @Override
    public void setActive(boolean b) {
        super.setActive(false);
    }

    @Override
    public void MarioJumpedOnEnemy() {
        if (game.player.HasStar()) {
            KilledByFireBall();
        } else {
            if (game.player.getY() + game.player.getHeight() > this.getY() + 16) {
                game.player.Decerease();
            }
        }
    }

    @Override
    public void KilledByFireBall() {
        super.setActive(false);
         game.parent.amitsAudioPlayer.smb_kick.play();
    }

    @Override
    public void bounce() {
    }

    @Override
    public void setYloc(double d) {
    }

    @Override
    public void CollidedWithShell() {
        KilledByFireBall();
    }

    @Override
    public void CollidedWithMovingShell() {

        KilledByFireBall();
    }

    @Override
    public void OtherEnemyTouchedFromRight() {
    }

    @Override
    public void OtherEnemyTouchedFromLeft() {
    }

    @Override
    public void CollidedWithMarioFromTOLeft() {
        if (game.player.HasStar()) {

            KilledByFireBall();
        } else {
            if (game.player.getY() + game.player.getHeight() > this.getY() + 16) {
                game.player.Decerease();
            }
        }
    }

    @Override
    public void CollidedWithMarioTORight() {
        if (game.player.HasStar()) {

            KilledByFireBall();
        } else {
            if (game.player.getY() + game.player.getHeight() > this.getY() + 16) {
                game.player.Decerease();
            }
        }
    }

    @Override
    public void EnemyJumperOnMario() {
        if (game.player.HasStar()) {

            KilledByFireBall();
        } else {
            game.player.Decerease();
        }
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
