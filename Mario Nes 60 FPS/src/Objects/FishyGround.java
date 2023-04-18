package Objects;

import Animations.DirectFalling;
import Animations.FallingDeadSprites;
import SandBox.Mario;
import com.golden.gamedev.object.AnimatedSprite;
import com.golden.gamedev.object.Timer;
import com.golden.gamedev.util.ImageUtil;
import java.awt.image.BufferedImage;
import java.util.Random;

// fish which jump outside water 
// boolean FlyingFishes = false; in Mario.java can activate or deactivate it
public class FishyGround extends AnimatedSprite implements BasicEnemy {

    boolean PositiveX = true;
    Mario game;
    double Gravity = -7.65;
    public double XSpeed = 0;

    public FishyGround(int x, int y, BufferedImage[] Enemy_Image, Mario g) {
        setLocation(x, y);
        setImages(Enemy_Image);
        setAnimationTimer(new Timer(300));
        setAnimate(true);
        setLoopAnim(true);
        this.setAnimationFrame(0, 1);
        game = g;

        this.setID(100);
    }

    public FishyGround(Mario g) {
        game = g;
        setLocation(game.player.getX(), 480 + 32);

        Random r = new Random();

        if (r.nextBoolean()) {
            PositiveX = true;
            XSpeed = (double)com.golden.gamedev.util.Utility.getRandom(1, 3)/2;
            setImages(ImageUtil.flipHorizontal(game.bsLoader.getStoredImages("FishRed")));
        } else {
            PositiveX = false;
            XSpeed = (double)com.golden.gamedev.util.Utility.getRandom(-3, -1)/2;
            setImages(game.bsLoader.getStoredImages("FishRed"));

        }

        if (XSpeed < 0) {
            this.moveX(com.golden.gamedev.util.Utility.getRandom(32 * 4, 32 * 8));

        } else {
            this.moveX(com.golden.gamedev.util.Utility.getRandom(-32 * 8, -32 * 4));
        }

        setAnimationTimer(new Timer(300));
        setAnimate(true);
        setLoopAnim(true);
//        System.out.println("FishyGround");
    }

    @Override
    public void update(long l) {

        if (Gravity < 8) {
                Gravity = Gravity + 0.06;
        }

        moveY(Gravity);
//        this.setX(this.getScreenX() + game.playfield.getBackground().getX()  );
        moveX(XSpeed + ((double)game.player.speed / 25));

        if (this.getY() > 576 || this.getScreenX() < -32 || this.getScreenX() > 640) {
            this.setActive(false);
//            game.FlyingFishesGroup.removeImmutableSprites();
        }


        super.update(l);
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
        game.AnimationGroup.add(new FallingDeadSprites(this.getX(), this.getY(), this.getImage(), MariotoRight()));
        this.setActive(false);
        System.out.println("Asd");
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
