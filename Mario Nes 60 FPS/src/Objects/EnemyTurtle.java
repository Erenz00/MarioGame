
package Objects;

import Animations.FallingDeadSprites;
import SandBox.Mario;
import com.golden.gamedev.object.AnimatedSprite;
import com.golden.gamedev.object.Timer;


public class EnemyTurtle extends AnimatedSprite implements BasicEnemy{

    boolean PositiveX = true ;
    
    Mario game ;
    
    public int Gravity = 6;
    
    boolean Standing = true ;

    public boolean Green = true ;
    
    String Color  ;
    
    public EnemyTurtle(double x, double y , Mario g , boolean isGreen) {
        game = g ;
        
        Color = "normal";
        
        if(isGreen){
            this.setImages(game.bsLoader.getStoredImages("turtle"));
        }else{
            this.setImages(game.bsLoader.getStoredImages("EnemyTurtlePatrol"));
            
        }
        
        this.setLocation(x, y);
        setAnimationTimer(new Timer(300));
        setAnimate(true);
        setLoopAnim(true);
        this.setAnimationFrame(0, 1);
        
        this.setID(102);
        Green = isGreen ;
        
        
        
    }

    public EnemyTurtle(double x, double y , Mario g , boolean isGreen , String color) {
        game = g ;
        
        if(isGreen){
            this.setImages(game.bsLoader.getStoredImages("turtledark"));
            Color = "dark";
        }else{
            this.setImages(game.bsLoader.getStoredImages("EnemyTurtlePatrol"));
            
        }
        
        this.setLocation(x, y);
        setAnimationTimer(new Timer(300));
        setAnimate(true);
        setLoopAnim(true);
        this.setAnimationFrame(0, 1);
        
        this.setID(102);
        Green = isGreen ;
        
//        this.setY(this.getY()-100);
        
    }

    @Override
    public void update(long l){
        moveY(Gravity);
        if( PositiveX  ){
        moveX(-1);
        }else {
        moveX(1); 
        }
        super.update(l);
    }
    
    @Override
    public void CollidedWithBrick_GoToLeft() {
        PositiveX  = true ;
        this.setAnimationFrame(0, 1);
    }

    @Override
    public void CollidedWithBrick_GoToRight() {
        PositiveX  = false ;
        this.setAnimationFrame(2, 3);
    }

    @Override
    public void MarioJumpedOnEnemy() {
        
        
        game.player.Jump(-8);
        
        
            game.EnemyGroup.add(new TurtelShell(this.getX() , this.getY()+16 , game , MariotoRight() , Standing , this.Green  , Color));
         
//            
            this.setActive(false);
        
    }


    @Override
    public void KilledByFireBall() {
        
         game.parent.amitsAudioPlayer.smb_kick.play();
        
        if(this.Green){
            if(Color.equals("normal")){
                game.AnimationGroup.add(new FallingDeadSprites(this.getX() , this.getY() , game.bsLoader.getStoredImage("TurtelShell")  , MariotoRight()));       
                }else{
                game.AnimationGroup.add(new FallingDeadSprites(this.getX() , this.getY() , game.bsLoader.getStoredImage("TurtelShelldark")  , MariotoRight()));       
                }
           
        }else{
        game.AnimationGroup.add(new FallingDeadSprites(this.getX() , this.getY() , game.bsLoader.getStoredImage("TurtelShellRed")  , MariotoRight()));
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
        // change direction
    }

    @Override
    public int getType() {
        return this.getID();
    }

    @Override
    public void CollidedWithMovingShell() {

        KilledByFireBall();
        
    }

    @Override
    public void OtherEnemyTouchedFromRight() {
        PositiveX  = true ;
        this.setAnimationFrame(0, 1);
        // System.out.println("Turtle OtherEnemyTouchedFromRight");
    }

    @Override
    public void OtherEnemyTouchedFromLeft() {
        PositiveX  = false ;
        this.setAnimationFrame(2, 3);
        // System.out.println("Turtle OtherEnemyTouchedFromLeft");
    }

    @Override
    public void CollidedWithMarioFromTOLeft() {
        if(game.player.HasStar()){
            KilledByFireBall();
        }else{
            game.player.Decerease();
        }
    }

    @Override
    public void CollidedWithMarioTORight() {
        if(game.player.HasStar()){
            KilledByFireBall();
        }else{
            game.player.Decerease();
        }
    }

    @Override
    public void EnemyJumperOnMario() {
        if(game.player.HasStar()){
            KilledByFireBall();
        }else{
            game.player.Decerease();
        }
    }

    @Override
    public int Life() {
        return 0;
    }

    @Override
    public void CollidedWithJumping_Brick() {
        
         game.parent.amitsAudioPlayer.smb_kick.play();
        
        game.EnemyGroup.add(new TurtelShell(this.getX() , this.getY()+16 , game  , this.MariotoRight() , false , "Jump" , this.Green));
        Standing = false ;
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
