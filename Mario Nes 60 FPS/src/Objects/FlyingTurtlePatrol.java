package Objects;

import Animations.FallingDeadSprites;
import SandBox.Mario;
import com.golden.gamedev.object.AnimatedSprite;
import com.golden.gamedev.object.Timer;
import com.golden.gamedev.util.ImageUtil;
import java.awt.image.BufferedImage;

public class FlyingTurtlePatrol extends AnimatedSprite implements BasicEnemy {

    boolean PositiveX = true;
    Mario game;
    int Up = 0;
    int Down = 0;
    private boolean FiyUp = false;
    int DistanceFromCenter = 4 * 32;
    int CenterX, CenterY;
    public boolean Green = false;

    public FlyingTurtlePatrol(int x, int y, Mario g, int Length) {
        game = g;
        this.setImages(game.bsLoader.getStoredImages("FlyingTurtlePatrol"));
        this.setLocation(x, y);
        Up = y;
        Down = y + (32 * Length);
        this.setID(101);
        setAnimationTimer(new Timer(300));
        setAnimate(true);
        setLoopAnim(true);
        this.setAnimationFrame(0, 1);

        CenterY = y + (32 * Length);
    }

    @Override
    public void update(long l) {
//            if(this.getY() < Up){
//            this.GoDown();
//            
//        }if(this.getY() > Down){
//            this.GoUp();
//        }
//        
//        if(FiyUp){
//            moveY(-2);
//        }else{
        this.setY((Math.cos(game.SlowDistance) * DistanceFromCenter) + CenterY);
        moveY(game.SlowDistance);
//        }



        super.update(l);
    }

    @Override
    public void bounce() {
    }

    @Override
    public void MarioJumpedOnEnemy() {

        game.player.Jump(-8);

        game.EnemyGroup.add(new EnemyTurtle((int) this.getX(), (int) this.getY(), game, this.Green));

        this.setActive(false);

    }

    @Override
    public void CollidedWithMovingShell() {
         game.parent.amitsAudioPlayer.smb_kick.play();
        BufferedImage HorizontalFilpShell = game.bsLoader.getStoredImage("TurtelShellRed");
        HorizontalFilpShell = ImageUtil.flipHorizontal(HorizontalFilpShell);
        game.AnimationGroup.add(new FallingDeadSprites(this.getX(), this.getY(), HorizontalFilpShell, MariotoRight()));
        this.setActive(false);
    }

    @Override
    public void CollidedWithBrick_GoToLeft() {
        PositiveX = true;
        this.setAnimationFrame(0, 1);
    }

    @Override
    public void CollidedWithBrick_GoToRight() {
        PositiveX = false;
        this.setAnimationFrame(2, 3);
    }

    @Override
    public int getType() {
        return this.getID();
    }

    @Override
    public void KilledByFireBall() {
        CollidedWithMovingShell();
    }

    @Override
    public void setYloc(double d) {
        this.setY(d);
        bounce();
    }

    @Override
    public void CollidedWithShell() {
        // change direction
    }

    @Override
    public void OtherEnemyTouchedFromRight() {
//        PositiveX = false ;
//        this.setAnimationFrame(2, 3);
    }

    @Override
    public void OtherEnemyTouchedFromLeft() {
//        PositiveX = true ;
//        this.setAnimationFrame(0, 1);
    }

    @Override
    public void CollidedWithMarioFromTOLeft() {
        if (game.player.HasStar()) {
            CollidedWithMovingShell();
        } else {
            game.player.Decerease();
        }
    }

    @Override
    public void CollidedWithMarioTORight() {
        if (game.player.HasStar()) {
            CollidedWithMovingShell();
        } else {
            game.player.Decerease();
        }
    }

    public void Kill() {
    }

    @Override
    public void EnemyJumperOnMario() {
        if (game.player.HasStar()) {
            CollidedWithMovingShell();
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

    private void GoDown() {
        FiyUp = false;
    }

    private void GoUp() {
        FiyUp = true;
    }
}
