package Objects;

import Animations.CoinAnim;
import SandBox.Mario;
import com.golden.gamedev.object.Timer;
import com.golden.gamedev.object.sprite.AdvanceSprite;
import java.awt.image.BufferedImage;

public class Coin extends AdvanceSprite implements BasicEnemy {

    Mario game;

    public Coin(int x, int y, BufferedImage[] storedImages, Mario M) {
        setLocation(x, y);
        setImages(storedImages);
        int[] animation = {0, 0, 0, 0, 1, 2, 1, 0};
        this.setAnimationFrame(animation);
        this.setAnimate(true);
        this.setLoopAnim(true);
        this.setAnimationTimer(new Timer(150));

        game = M;

        this.setID(21);

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
        super.setActive(false);
        game.parent.CoinInc();
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
    }

    @Override
    public void CollidedWithMarioTORight() {
    }

    @Override
    public void EnemyJumperOnMario() {
//        game.Score();
        super.setActive(false);
        game.parent.CoinInc();
    }

    @Override
    public int Life() {
        return 0;
    }

    @Override
    public void CollidedWithJumping_Brick() {

        game.AnimationGroup.add(new CoinAnim((int) this.getX(), (int) this.getY(), game.bsLoader.getStoredImages("CoinAnim"), game));

        this.setActive(false);
        game.parent.CoinInc();
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
