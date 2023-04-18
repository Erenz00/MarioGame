
package Objects;

import Animations.FallingDeadSprites;
import SandBox.Mario;
import com.golden.gamedev.object.Sprite;
import java.awt.image.BufferedImage;

public class MovingHelmetShell extends Sprite implements BasicEnemy{

    boolean PositiveX = true ;
    
    Mario game ;
    
    public int Gravity = 5;
    
    String Color ;
    
//    MovingHelmetShell(double x, double y, BufferedImage storedImage, Mario g, boolean b) {
//        super(storedImage , x , y  );
//        this.setID(107);
//        
//        PositiveX = b ;
//        
//        game = g ;
//    }

    public MovingHelmetShell(double x, double y, String color, Mario g, boolean b) {
        game = g ;
        Color = color ;
        
        if("normal".equals(Color)){
            
        this.setImage(game.bsLoader.getStoredImage("HelmetShell"));
        
        }else if("dark".equals(Color)){
            
        this.setImage(game.bsLoader.getStoredImage("HelmetShelldark"));
        
        }else if("white".equals(Color)){
            
        this.setImage(game.bsLoader.getStoredImage("HelmetShellwhite"));
        
        } 
        this.setLocation(x, y);
        this.setID(107);
        PositiveX = b ;
    }

    @Override
     public void update(long l){
        moveY(Gravity);
        if(PositiveX){
        moveX(-5);
        }else {
        moveX(5); 
        }
        super.update(l);
    }
            
    @Override
    public void CollidedWithBrick_GoToLeft() {
        PositiveX = true ;
        if(this.getScreenX() > -500 & this.getScreenX() < 1200){
         game.parent.amitsAudioPlayer.smb_bump.play();
        }
        
    }

    @Override
    public void CollidedWithBrick_GoToRight() {
        PositiveX = false ;
        if(this.getScreenX() > -500 & this.getScreenX() < 1200){
         game.parent.amitsAudioPlayer.smb_bump.play();
        }
    }

    @Override
    public int getType() {
        return this.getID();
    }

    @Override
    public void MarioJumpedOnEnemy() {
        
        game.player.Jump(-8);
        
        game.EnemyGroup.add(new HelmetShell(this.getX() , this.getY() , this.Color , game , MariotoRight() ));

        this.setActive(false);
    }



    @Override
    public void KilledByFireBall() {
    }

    @Override
    public void bounce() {
        Gravity = 6;
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
         game.parent.amitsAudioPlayer.smb_kick.play();
        game.AnimationGroup.add(new FallingDeadSprites(this.getX() , this.getY() , this.getImage(), MariotoRight() ));
        
        this.setActive(false);
        
        
    }

    @Override
    public void OtherEnemyTouchedFromRight() {
        
    }

    @Override
    public void OtherEnemyTouchedFromLeft() {
       
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
        if(PositiveX ){     // invert direction
            PositiveX = false ;
        }else{
            PositiveX = true ;
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
