package Objects;

import Animations.FallingDeadSprites;
import SandBox.Mario;
import com.golden.gamedev.object.AnimatedSprite;
import com.golden.gamedev.object.Timer;
import java.awt.image.BufferedImage;

public class SpikeyEgg extends AnimatedSprite implements BasicEnemy {

    Mario game;
    public double Gravity = -6;
    boolean PositiveX = true;

    public SpikeyEgg(int x, int y, BufferedImage[] Enemy_Image, Mario g, boolean direction) {
        setLocation(x, y);
        setImages(Enemy_Image);
        setAnimationTimer(new Timer(160));
        setAnimate(true);
        setLoopAnim(true);
        this.setAnimationFrame(0, 1);
        PositiveX = direction;
        game = g;

        this.setID(118);
    }

    @Override
    public void update(long l) {

        if (Gravity < 5) {
            Gravity += 0.25;
        }
        moveY(Gravity);

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
        game.AnimationGroup.add(new FallingDeadSprites(this.getX(), this.getY(),
                game.bsLoader.getStoredImages("Spikey")[0], MariotoRight()));
        this.setActive(false);

    }

    @Override
    public void bounce() {
    }

    @Override
    public void setYloc(double d) {

        game.EnemyGroup.add(new Spikey((int) this.getX(), (int) this.getY(), game.bsLoader.getStoredImages("Spikey"), game));

        this.setActive(false);

        this.setY(d);
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
