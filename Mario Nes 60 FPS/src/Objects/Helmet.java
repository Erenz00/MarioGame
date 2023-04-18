
package Objects;

import Animations.FallingDeadSprites;
import SandBox.Mario;
import com.golden.gamedev.object.AnimatedSprite;
import com.golden.gamedev.object.Timer;
import com.golden.gamedev.util.ImageUtil;


public class Helmet extends AnimatedSprite implements BasicEnemy{

    boolean PositiveX = true ;
    
    Mario game ;
    
    public int Gravity = 5;
    
    String Color  ;
    
    public Helmet(int x, int y, String color , Mario g) {
        Color = color ;
        game = g ;
        
        if("normal".equals(Color)){
            
        this.setImages(game.bsLoader.getStoredImages("Helmet"));
        
        }else if("dark".equals(Color)){
            
        this.setImages(game.bsLoader.getStoredImages("Helmetdark"));
        
        }else if("white".equals(Color)){
            
        this.setImages(game.bsLoader.getStoredImages("Helmetwhite"));
        
        } 
            
        this.setLocation(x, y);
        setAnimationTimer(new Timer(300));
        setAnimate(true);
        setLoopAnim(true);
        this.setAnimationFrame(0, 1);
        this.setID(105);
        
    }

    @Override
        public void update(long l){
        moveY(Gravity);
        if(PositiveX){
        moveX(-1);
        }else {
        moveX(1); 
        }
        super.update(l);
    }
            
    @Override
    public void MarioJumpedOnEnemy() {
        
            game.player.Jump(-8);
            
            game.EnemyGroup.add(new HelmetShell(this.getX() , this.getY() , this.Color , game , MariotoRight() ));
        
            this.setActive(false);
        
         
    }

    @Override
    public void CollidedWithShell() {
        // change Direction
    }

    @Override
    public void CollidedWithMovingShell() {
        
         game.parent.amitsAudioPlayer.smb_kick.play();
        
        game.AnimationGroup.add(new FallingDeadSprites(this.getX() , this.getY() , this.getImage() , MariotoRight() ));

        this.setActive(false);
    }

    @Override
    public void CollidedWithBrick_GoToLeft() {
        PositiveX = true ;
        this.setAnimationFrame(0, 1);
    }

    @Override
    public void CollidedWithBrick_GoToRight() {
        PositiveX = false ;
        this.setAnimationFrame(2, 3);
    }
    
    @Override
    public int getType() {
        return this.getID();
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
    public void OtherEnemyTouchedFromRight() {
        PositiveX = true ;
        this.setAnimationFrame(0, 1);
    }

    @Override
    public void OtherEnemyTouchedFromLeft() {
        PositiveX = false ;
        this.setAnimationFrame(2, 3);
    }

    @Override
    public void CollidedWithMarioFromTOLeft() {
        if(game.player.HasStar()){
        CollidedWithMovingShell();
        }else{
            game.player.Decerease();
        }
    }

    @Override
    public void CollidedWithMarioTORight() {
        if(game.player.HasStar()){
        CollidedWithMovingShell();
        }else{
            game.player.Decerease();
        }
    }

    @Override
    public void EnemyJumperOnMario() {
        if(game.player.HasStar()){
        CollidedWithMovingShell();
        }else{
            game.player.Decerease();
        }
    }

    @Override
    public int Life() {
        return 0 ;
    }

    @Override
    public void CollidedWithJumping_Brick() {
//         game.parent.amitsAudioPlayer.smb_kick.play();
        game.EnemyGroup.add(new HelmetShell(this.getX() , this.getY() , this.Color , game , MariotoRight() ));
            
        this.setActive(false);
        
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
