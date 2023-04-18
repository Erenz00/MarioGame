
package Objects;

import SandBox.Mario;
import com.golden.gamedev.object.AnimatedSprite;
import com.golden.gamedev.object.Timer;
import java.awt.image.BufferedImage;

public class Life extends AnimatedSprite implements BasicEnemy{

    
    boolean PositiveX  = true ;
    
    Mario game ;
    
    public int Gravity = 8;

    // its overlaping the ron and has to cum up slowly
    public Life(int x, int y, BufferedImage[] storedImage , Mario g) {
        
        super(storedImage , x , y );
        this.setAnimate(true);
        this.setLoopAnim(true);
        
        this.setAnimationTimer(new Timer(300));
        
        
        this.setID(12);
        
        PositiveX  = false ;
        
    }
    
    @Override
    public void update(long l){
        moveY(Gravity);
        if( PositiveX  ){
        moveX(-2);
        }else {
        moveX(2); 
        }
        super.update(l);
    }

    @Override
    public void CollidedWithBrick_GoToLeft() {
        PositiveX  = true ;
    }

    @Override
    public void CollidedWithBrick_GoToRight() {
        PositiveX  = false ;
    }

    @Override
    public int getType() {
        return this.getID();
    }

    @Override
    public void MarioJumpedOnEnemy() {
//        game.player.IncreaseLife();
        this.setActive(false);
    }



    @Override
    public void KilledByFireBall() {
    }

    @Override
    public void bounce() {
    }

    @Override
    public void setYloc(double d) {
        this.setY(d);
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
//        game.player.IncreaseLife();
        this.setActive(false);
    }

    @Override
    public void CollidedWithMarioTORight() {
//        game.player.IncreaseLife();
        this.setActive(false);
    }

    @Override
    public void EnemyJumperOnMario() {
        this.setActive(false);
    }

    @Override
    public int  Life() {
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