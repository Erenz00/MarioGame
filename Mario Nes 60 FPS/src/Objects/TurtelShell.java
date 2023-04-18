
package Objects;

import Animations.FallingDeadSprites;
import SandBox.Mario;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.util.ImageUtil;
import java.awt.image.BufferedImage;


public class TurtelShell extends Sprite implements BasicEnemy{

    boolean Jump = false ;
    
    Mario game ;
    
    boolean PositiveX = true ;
    
    private int Gravity = 6;
    
    boolean Standing = true ;
    
    public boolean Green = true ;
    
    String Color  ;
    
    public TurtelShell(double x, double y , Mario g , boolean Direction , boolean standing , boolean isGreen , String color) {
        super( x , y  );
        Color = color ;
        this.setID(103);
        PositiveX = Direction;
        Standing = standing ;
        game = g ;
        
        Green = isGreen ;
        
        if(Standing){
            if(Green){
                if(Color.equals("normal")){
                this.setImage(game.bsLoader.getStoredImage("TurtelShell"));
                }else{
                this.setImage(game.bsLoader.getStoredImage("TurtelShelldark"));
                }
            }else{
                this.setImage(game.bsLoader.getStoredImage("TurtelShellRed"));
            }
            
        }else{
            if(Green){
                if(Color.equals("normal")){
                this.setImage(game.bsLoader.getStoredImage("TurtelShellFilp"));
                }else{
                this.setImage(game.bsLoader.getStoredImage("TurtelShellFilpdark"));
                }
            }else{
                this.setImage(game.bsLoader.getStoredImage("TurtelShellFilpRed"));
                
            }
        }
    }

    TurtelShell(double x, double y, Mario game, boolean PositiveX , boolean Standing, String jump, boolean isGreen) {
        this(x , y , game , PositiveX , Standing , isGreen , "normal");
//        Jump = true ;
        Gravity = -10;
    }

    
    @Override
    public void update(long l){
        
        if(Gravity > 4){ // dont increase gravity
//                Jump = false ;
                moveY(Gravity);
            }else{
                Gravity = Gravity + 1;
                this.moveY(Gravity);
            
                if(PositiveX){
                    this.moveX(-2);
                }else{
                    this.moveX(2);
                }
                
            }
        
//        moveY(Gravity);
        

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
        //get direction 
        // add shell
        if(game.getPlayer().getX() < this.getX()){
        game.EnemyGroup.add(new MovingTurtelShell(game.getPlayer().getX()+game.getPlayer().getWidth() , this.getY() , game , false , Standing ,this.Green , Color));
        }else{
        game.EnemyGroup.add(new MovingTurtelShell(game.getPlayer().getX()- this.getWidth() , this.getY() , game , true , Standing,this.Green , Color ));
        }
        this.setActive(false);
        // System.out.println();
         game.parent.amitsAudioPlayer.smb_kick.play();
    }



    @Override
    public void KilledByFireBall() {
         game.parent.amitsAudioPlayer.smb_kick.play();
        if(this.Green){
        if(Color.equals("normal")){
                game.AnimationGroup.add(new FallingDeadSprites(this.getX() , this.getY() , game.bsLoader.getStoredImage("TurtelShell"), MariotoRight()));
                }else{
                game.AnimationGroup.add(new FallingDeadSprites(this.getX() , this.getY() , game.bsLoader.getStoredImage("TurtelShelldark"), MariotoRight()));
                }
        
         
    }else{
         game.AnimationGroup.add(new FallingDeadSprites(this.getX() , this.getY() , game.bsLoader.getStoredImage("TurtelShellRed"), MariotoRight()));
        }
         this.setActive(false);
         
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
         game.parent.amitsAudioPlayer.smb_kick.play();
        
        game.EnemyGroup.add(new MovingTurtelShell(game.getPlayer().getX()+game.getPlayer().getWidth(), this.getY() , game , false , Standing , this.Green , Color ));
            
        this.setActive(false);
        
    }

    @Override
    public void CollidedWithMarioTORight() {
        
         game.parent.amitsAudioPlayer.smb_kick.play();
        
        game.EnemyGroup.add(new MovingTurtelShell(game.getPlayer().getX()- this.getWidth()  , this.getY() , game , true , Standing , this.Green , Color ));
            
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
        if(this.PositiveX){
            this.PositiveX = false ;
        }else{
            this.PositiveX = true ;
        }
        game.EnemyGroup.add(new TurtelShell(this.getX() , this.getY() , game , MariotoRight(), this.Standing , "Jump" , this.Green));
//            
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
