/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Objects;

import SandBox.Mario;
import com.golden.gamedev.object.AnimatedSprite;
import com.golden.gamedev.object.Timer;
import java.awt.image.BufferedImage;

public class Flower extends AnimatedSprite implements BasicEnemy {

    Mario game;

    public Flower(int x, int y, BufferedImage[] storedImages, Mario M) {
        setLocation(x, y);
        setImages(storedImages);

        this.setAnimate(true);
        this.setLoopAnim(true);
        this.setAnimationTimer(new Timer(100));

        game = M;

        this.setID(4);

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