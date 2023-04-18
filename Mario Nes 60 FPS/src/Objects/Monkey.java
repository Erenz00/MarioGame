package Objects;

import Animations.DirectFalling;
import Animations.FallingDeadSprites;
import SandBox.Mario;
import com.golden.gamedev.object.AnimatedSprite;
import com.golden.gamedev.object.Timer;
import java.awt.image.BufferedImage;

public class Monkey extends AnimatedSprite implements BasicEnemy {

    Mario game;
    int left = 64;
    int right = 64;
    boolean PositiveX = true;
    int JumpTime = 20;
    int ComeDownTime = 40;
    private double Gravity;
    boolean ComeDown = false; // true only if in hgher places = y < 256
    private int ComeDownFor;
    int HammerThrowTime;

    public Monkey(BufferedImage[] storedImages, int x, int y, Mario g) {
        setLocation(x, y);
        setImages(storedImages);
        setAnimationTimer(new Timer(300));
        setAnimate(true);
        setLoopAnim(true);

        this.setID(108);
        game = g;

        left = x - 32;
        right = x + 32;

        JumpTime = com.golden.gamedev.util.Utility.getRandom(3, 6) * 20;
        ComeDownTime = com.golden.gamedev.util.Utility.getRandom(3, 6) * 40;
        HammerThrowTime = com.golden.gamedev.util.Utility.getRandom(1, 10) * 10;
    }

    @Override
    public void update(long l) {

        if(this.isOnScreen()){
        HammerThrow();
        }

        ComeDown();
        
        
        JumpTime--;
        if (JumpTime < 0) {
            Jump();
        }

        if (Gravity < 5) {
            Gravity += 0.3;

        }

        moveY(Gravity);
        if (this.getX() < left) {
            PositiveX = false;
        }
        if (this.getX() > right) {

            PositiveX = true;

        }

        if (PositiveX) {


            this.moveX(-0.5);

        } else {
            this.moveX(0.5);
        }

        LookAtMario();

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
        game.AnimationGroup.add(new DirectFalling(this.getImage(), this.getX(), this.getY(), MariotoRight()));
        this.setActive(false);
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
        if (ComeDown && this.getY() < 320) {
        } else {
            this.setY(d);
        }
    }

    private void LookAtMario() {
        if (game.player.getX() < this.getX()) { // look at left

            this.setAnimationFrame(0, 1);

        } else {

            this.setAnimationFrame(4, 5);

        }
    }

    private void Jump() {
        Gravity = -9;
        JumpTime = com.golden.gamedev.util.Utility.getRandom(3, 6) * 40;
    }

    private void ComeDown() {


        ComeDownTime--;
        if (ComeDownTime < 0) { // can come down
            ComeDown = true;
            ComeDownFor = 10;
            ComeDownTime = com.golden.gamedev.util.Utility.getRandom(3, 6) * 30;
            
        }

        if (ComeDown) {
            ComeDownFor--;
        }

        if (ComeDownFor < 0) {
            ComeDown = false;
        }

    }

    private void HammerThrow() {


        HammerThrowTime--;

        if (HammerThrowTime < 0) {

            HammerThrowTime = com.golden.gamedev.util.Utility.getRandom(1, 10) * 20;

            if (game.player.getX() < this.getX()) { // throw to left hammer
                game.HammerGroup.add(new Hammer(this.getX(), this.getY(), true, game.bsLoader.getStoredImages("Hammer")));
//            this.setFrame(2);
            } else {
                game.HammerGroup.add(new Hammer(this.getX(), this.getY(), false, game.bsLoader.getStoredImages("Hammer")));
//            this.setFrame(5);
            }


        }

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

    @Override
    public int Life() {
        return 0;
    }

    @Override
    public void CollidedWithJumping_Brick() {
        KilledByFireBall();
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
