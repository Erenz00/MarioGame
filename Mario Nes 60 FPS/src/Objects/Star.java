package Objects;

import SandBox.Mario;
import com.golden.gamedev.object.AnimatedSprite;
import com.golden.gamedev.object.Timer;
import java.awt.image.BufferedImage;

public class Star extends AnimatedSprite implements BasicEnemy {

    boolean PositiveX = true;
    Mario game;
    public double Gravity = -5;
    private double XSpeed;

    // its overlaping the ron and has to cum up slowly
    public Star(int x, int y, BufferedImage[] storedImage, Mario g) {
        super(storedImage, x, y);
        game = g;
        this.setAnimate(true);
        this.setLoopAnim(true);

        this.setAnimationTimer(new Timer(100));


        this.setID(20);

        PositiveX = false;
        XSpeed = 2;

    }

    @Override
    public void update(long l) {
        if (Gravity < 5) {
            Gravity = Gravity + 0.25;
        }
        moveY(Gravity);

        if (PositiveX) {
            moveX(-1.5);
        } else {
            moveX(1.5);
        }

        if (getY() > 700D) {
            setActive(false);
        }
        super.update(l);
    }

    @Override
    public void bounce() {
        Gravity = -6;
    }

    @Override
    public void CollidedWithBrick_GoToLeft() {
        PositiveX = true;
    }

    @Override
    public void CollidedWithBrick_GoToRight() {
        PositiveX = false;
    }

    @Override
    public int getType() {
        return this.getID();
    }

    @Override
    public void MarioJumpedOnEnemy() {
        game.player.STAR();
        super.setActive(false);

    }

    @Override
    public void KilledByFireBall() {
    }

    @Override
    public void CollidedWithShell() {
    }

    @Override
    public void CollidedWithMovingShell() {
    }

    @Override
    public void OtherEnemyTouchedFromRight() {
    }

    @Override
    public void OtherEnemyTouchedFromLeft() {
    }

    @Override
    public void CollidedWithMarioFromTOLeft() {
        game.player.STAR();
        super.setActive(false);

    }

    @Override
    public void CollidedWithMarioTORight() {
        game.player.STAR();
        super.setActive(false);

    }

    @Override
    public void EnemyJumperOnMario() {
        game.player.STAR();
        super.setActive(false);

    }

    @Override
    public int Life() {
        return 0;
    }

    @Override
    public void setYloc(double d) {
        bounce();
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
