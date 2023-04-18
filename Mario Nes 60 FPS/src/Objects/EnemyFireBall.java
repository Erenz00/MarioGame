package Objects;

import SandBox.Mario;
import com.golden.gamedev.object.AnimatedSprite;
import com.golden.gamedev.object.Timer;
import java.awt.image.BufferedImage;

public class EnemyFireBall extends AnimatedSprite implements BasicEnemy {

    Mario game;
    int DistanceFromCenter;
    int CenterX, CenterY;
    boolean AntiClockWise = true;

    public EnemyFireBall(int x, int y, BufferedImage[] Enemy_Image, Mario g) {
        setLocation(x, y);
        CenterX = x;
        CenterY = y;
        setImages(Enemy_Image);
        setAnimationTimer(new Timer(100));
        setAnimate(true);
        setLoopAnim(true);
        this.setAnimationFrame(0, 3);
        game = g;

        this.setID(114);
    }

    public EnemyFireBall(int CenterX, int CenterY, int dis, Mario game, boolean Anticlockwise) {

        this(CenterX, CenterY, game.bsLoader.getStoredImages("FireBall"), game);

        DistanceFromCenter = dis;

        AntiClockWise = Anticlockwise;

    }

    @Override
    public void update(long l) {

        if (AntiClockWise) {
            this.setX((Math.sin(game.Distance) * DistanceFromCenter) + CenterX);
            this.setY((Math.cos(game.Distance) * DistanceFromCenter) + CenterY);
        } else {
            this.setX((Math.sin(game.InvertDistance) * DistanceFromCenter) + CenterX);
            this.setY((Math.cos(game.InvertDistance) * DistanceFromCenter) + CenterY);
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
        if (game.player.HasStar()) {
        } else {
            game.player.Decerease();
        }
    }

    @Override
    public void KilledByFireBall() {
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
        } else {
            game.player.Decerease();
        }
    }

    @Override
    public void CollidedWithMarioTORight() {
        if (game.player.HasStar()) {
        } else {
            game.player.Decerease();
        }
    }

    @Override
    public void EnemyJumperOnMario() {
        if (game.player.HasStar()) {
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