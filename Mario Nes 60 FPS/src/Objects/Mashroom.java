
package Objects;

import SandBox.Mario;
import com.golden.gamedev.object.AnimatedSprite;
import com.golden.gamedev.object.Timer;
import java.awt.image.BufferedImage;

public class Mashroom  extends AnimatedSprite implements BasicEnemy{

    
    boolean PositiveX  = true ;
    
    Mario game ;
    
    public double Gravity = 5;
    

    // its overlaping the ron and has to cum up slowly
    public Mashroom(int x, int y, BufferedImage[] storedImage , Mario g) {
        
        super(storedImage , x , y );
        this.setAnimate(true);
        this.setLoopAnim(true);
        
        this.setAnimationTimer(new Timer(300));
        
        
        this.setID(3);
        
        PositiveX  = false ;
        
    }
    
    @Override
    public void update(long l){
        if(Gravity < 5) {
            Gravity += 0.5;
        }
        moveY(Gravity);
        if(PositiveX ){
        moveX(-2);
        }else {
        moveX(2); 
        }
        super.update(l);
    }

    @Override
    public void CollidedWithBrick_GoToLeft() {
        PositiveX = true ;
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
        game.player.Grow();
        this.setActive(false);
    }



    @Override
    public void KilledByFireBall() {
    }

    @Override
    public void bounce() {
        Gravity = -5;
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
         game.player.Grow();
        this.setActive(false);
    }

    @Override
    public void CollidedWithMarioTORight() {
         game.player.Grow();
        this.setActive(false);
    }

    @Override
    public void EnemyJumperOnMario() {
         game.player.Grow();
        this.setActive(false);
    }

    @Override
    public int Life() {
        return 0 ;
    }

    @Override
    public void CollidedWithJumping_Brick() {
        if(PositiveX  ){     // invert direction
            PositiveX  = false ;
        }else{
            PositiveX  = true ;
        }
        
        bounce();
        
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