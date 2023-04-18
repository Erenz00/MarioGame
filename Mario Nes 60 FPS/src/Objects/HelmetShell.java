
package Objects;

import Animations.FallingDeadSprites;
import SandBox.Mario;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.util.ImageUtil;
import java.awt.image.BufferedImage;


public class HelmetShell extends Sprite implements BasicEnemy{


    Mario game ;
    
    boolean PositiveX = true ;
    
    String Color  ;
    

    HelmetShell(double x, double y, String color, Mario g, boolean direction) {
        
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
        this.setID(106);
        PositiveX = direction ;
        
    }
    
    @Override
    public void update(long l){
//        moveY(6);
        
        super.update(l);
    }

    @Override
    public void CollidedWithBrick_GoToLeft(){
    }
    
    @Override
    public void CollidedWithBrick_GoToRight(){
    }
    

    @Override
    public int getType() {
        return this.getID();
    }

    @Override
    public void MarioJumpedOnEnemy() {
         game.parent.amitsAudioPlayer.smb_kick.play();
//        game.player.Jump(-8);
        if(game.getPlayer().getX() < this.getX()){
        game.EnemyGroup.add(new MovingHelmetShell(game.getPlayer().getX()+game.getPlayer().getWidth() , this.getY() , this.Color , game , false ));
        }else{
        game.EnemyGroup.add(new MovingHelmetShell(game.getPlayer().getX()- this.getWidth() , this.getY() , this.Color , game , true ));
        }
        this.setActive(false);
        
    }



    @Override
    public void KilledByFireBall() {
        
//         game.AnimationGroup.add(new FallingDeadSprites(this.getX() , this.getY() , this.getImage()));
//
//         this.setActive(false);
         
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
         game.parent.amitsAudioPlayer.smb_kick.play();
        game.AnimationGroup.add(new FallingDeadSprites(this.getX() , this.getY() , this.getImage() , MariotoRight() ));

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
         game.parent.amitsAudioPlayer.smb_kick.play();
        game.EnemyGroup.add(new MovingHelmetShell(game.getPlayer().getX()+game.getPlayer().getWidth(), this.getY() , this.Color , game , false ));
            
        this.setActive(false);
        
    }

    @Override
    public void CollidedWithMarioTORight() {
         game.parent.amitsAudioPlayer.smb_kick.play();
        game.EnemyGroup.add(new MovingHelmetShell(game.getPlayer().getX()- this.getWidth()  , this.getY() , this.Color , game , true ));
            
        this.setActive(false);
        
    }

    @Override
    public void EnemyJumperOnMario() {
    }

    @Override
    public int Life() {
        return 0 ;
    }

    @Override
    public void CollidedWithJumping_Brick() {
//         game.parent.amitsAudioPlayer.smb_kick.play();
        HelmetShell temp = new HelmetShell(this.getX() , this.getY() , this.Color , game , MariotoRight() );
        temp.setImage(ImageUtil.flipHorizontal(temp.getImage()));
        game.EnemyGroup.add(temp);
            
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
