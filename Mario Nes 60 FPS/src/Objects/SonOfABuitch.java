package Objects;

import Animations.DirectFalling;
import SandBox.Mario;
import com.golden.gamedev.object.AnimatedSprite;
import com.golden.gamedev.util.Utility;
import java.awt.image.BufferedImage;

public class SonOfABuitch extends AnimatedSprite implements BasicEnemy {

    boolean PositiveX = true;
    Mario game;
    int Movement;
    boolean goLeft = true;
    int Delay = 100;

    public SonOfABuitch(int x, int y, BufferedImage[] Enemy_Image, Mario g) {
        setLocation(x, 80);
        setImages(Enemy_Image);
        game = g;

        this.setID(115);

        Movement = 100; // -100 and +100
    }

    @Override
    public void update(long l) {

        Delay--;
        if (Delay < 0) {
            ThrowSpikes();
            this.setFrame(0);
        }
        if (Delay > 1 & Delay < 10) {
            this.setFrame(1);
        }

        if (true) {   // not near to home and flag

            if (goLeft) {
                Movement--;
            } else {
                Movement++;
            }

            if (Movement < -100) {
                goLeft = false;
            } else if (Movement > 100) {
                goLeft = true;
            }

            this.setX(game.player.getX() + (Movement * 2));
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
    public void MarioJumpedOnEnemy() {
        game.player.Jump(-8);
        game.AnimationGroup.add(new DirectFalling(this.getImage(), this.getX(), this.getY()));
        this.setActive(false);
    }

    @Override
    public void KilledByFireBall() {
         game.parent.amitsAudioPlayer.smb_kick.play();
        game.AnimationGroup.add(new DirectFalling(this.getImage(), this.getX(), this.getY()));
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
            game.player.Decerease();
        }
    }

    @Override
    public void CollidedWithMarioTORight() {
        if (game.player.HasStar()) {
            KilledByFireBall();
        } else {
            game.player.Decerease();
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

    private void ThrowSpikes() {
        Delay = Utility.getRandom(1, 5) * 100;

        game.EnemyGroup.add(new SpikeyEgg((int) this.getX(), (int) this.getY(), game.bsLoader.getStoredImages("SpikeyEgg"), game, PositiveX));
    }

    @Override
    public int Life() {
        return 0;
    }

    @Override
    public void CollidedWithJumping_Brick() {
        // this doesnot collide with anything thats why the name
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
