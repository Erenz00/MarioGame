
package Objects;

import SandBox.Mario;
import com.golden.gamedev.object.AnimatedSprite;
import com.golden.gamedev.object.Timer;
import java.awt.image.BufferedImage;


public class LavaBubble   extends AnimatedSprite implements BasicEnemy{

    
    Mario game ;
    
    public int Gravity = 6;
    

    public LavaBubble(int x, int y, BufferedImage[] Enemy_Image, Mario g) {
        setLocation(x, y);
        setImages(Enemy_Image);
        game = g ;
        
        this.setID(116);
    }

    @Override
        public void update(long l){
        moveY(Gravity);
        
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
        game.player.Decerease();
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
        game.player.Decerease();
    }

    @Override
    public void CollidedWithMarioTORight() {
        game.player.Decerease();
    }

    @Override
    public void EnemyJumperOnMario() {
        game.player.Decerease();
    }

    @Override
    public int Life() {
        return 0 ; 
    }

    @Override
    public void CollidedWithJumping_Brick() {
    }

    @Override
         public boolean MariotoRight() {
        boolean positive ;
        if(game.player.getX() < this.getX()){
            positive = false ;
        }else{
            positive = true ;
        }
        
        return positive;
    }
    
}
