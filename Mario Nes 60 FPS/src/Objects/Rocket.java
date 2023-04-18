
package Objects;

import Animations.DirectFalling;
import Animations.FallingDeadSprites;
import SandBox.Mario;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.util.ImageUtil;
import java.awt.image.BufferedImage;

public class Rocket extends Sprite implements BasicEnemy{

    Mario game ;
    
    double XSpeed = -5 ;
    
    public Rocket(double x, double y, String direction, BufferedImage bufferedImage , Mario g) {
        
        if("gotoleft".equals(direction) ){
             XSpeed = -3 ;
             this.setImage(bufferedImage);
        }else{
             XSpeed = 3 ;
             this.setImage(ImageUtil.flipHorizontal(bufferedImage));
        }
        
        this.setLocation(x, y);
        
        this.setID(109);
        
        game = g ;
        
        // System.out.println("Rocket " + x +" " +y);
        
    }

    @Override
    public void update(long l){
            
        this.moveX(XSpeed);
        
        RemoveIfTooAway();
        
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
         game.parent.amitsAudioPlayer.smb_kick.play();
        game.player.Jump(-8);
        game.AnimationGroup.add(new DirectFalling( this.getImage() , this.getX() , this.getY() ));
        this.setActive(false);
        
    }



    @Override
    public void KilledByFireBall() {
    }

    private void RemoveIfTooAway() {
        
        if(this.getScreenX() < -32){
            this.setActive(false);
        }
        if(this.getScreenX() > 840){
            this.setActive(false);
        }
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
         game.parent.amitsAudioPlayer.smb_kick.play();
        game.AnimationGroup.add(new FallingDeadSprites(this.getX() , this.getY() , this.getImage() , false));
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
