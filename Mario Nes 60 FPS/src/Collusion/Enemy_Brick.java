package Collusion;


import Bricks.BasicBrick;
import Objects.BasicEnemy;
import SandBox.Mario;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.BasicCollisionGroup;
import java.awt.Point;


 public class Enemy_Brick extends BasicCollisionGroup {

    Mario M ;
    
 public   Enemy_Brick(Mario aThis) {
        M = aThis ;
    }

    
    @Override
    public void collided(Sprite enemy, Sprite brick) {
        
        BasicEnemy e = (BasicEnemy) enemy ;
        
        BasicBrick bri = (BasicBrick) brick ;
        
        boolean NoCollusion = true ;
        
        switch(e.getType()){
                case 100 : // EnemyMashroom = 100
                    break ;
                case 101 ://FlyingTurtle = 101 
                    break ;
                case 102 ://EnemyTurtle = 102   
                    break ;
                case 103 ://TurtelShell = 103 
                    break ;
                case 104 ://MovingTurtelShell = 104 
                    if(enemy.getDistance(brick) < 16){
                        e.CollidedWithMovingShell();
                    }
                    break ;
                case 105 ://Helmet = 105 ;  
                    break ;
                case 106 ://HelmetShell = 106 ; 
                    break ;
                case 107 ://MovingHelmetShell = 107; 
                    if(enemy.getDistance(brick) < 16){
                        e.CollidedWithMovingShell();
                    }
                    break ;
                case 108 ://Monkey = 108    
                    break ;
                case 109 ://Rocket = 109    
                    NoCollusion = false ;
                    break ;
                case 110 ://plant = 110
                    NoCollusion = false ;
                    break ;
                    
                    
                case 111 : // OctoPussy
                    NoCollusion = false ;
                    break ;
                case 112 : // FishyWater
                    NoCollusion = false ;
                    break ;
                case 113 : // FishyGround
                    NoCollusion = false ;
                    break ;
                case 114 : // EnemyFireBall
                    NoCollusion = false ;
                    break ;
                case 115 : // SonOfABuitch
                    NoCollusion = false ;
                    break ;
                case 116 : // LavaBubble 
                    NoCollusion = false ;
                    break ;
                case 117 : // Spikey 
                    break ;
                case 118 : // SpikeyEgg
                    break ;
                case 119 : // Bowser
//                    NoCollusion = false ;
                    break ;
                    
                case 3 : // Mashroom 
                    break ;
                    
                case 21 : // Coin
//                    NoCollusion = false ;
                    break ;
                default :      ;
            }
        
        if(NoCollusion){
        String side ;
        
        Point enemy_Center = new Point((int)enemy.getX()+enemy.getWidth() , (int)enemy.getY()+enemy.getHeight());
        Point brick_Center = new Point((int)brick.getX()+brick.getWidth() , (int)brick.getY()+brick.getHeight());
        
        int angle = (int) Math.toDegrees(Math.atan2(  enemy_Center.getY() - brick_Center.getY(),
                 enemy_Center.getX() - brick_Center.getX()
                ));
        
        M.setDegree(angle);
        
        
         if(angle > 30 && angle < 150){
            side = "down" ;
        }
        else if(angle < -30 && angle > -150){
            side = "up" ;
            if(bri.isJump()){ // and enemy is on it // it will only certen kill enemy 
            
                switch(e.getType()){
                case 100 : // EnemyMashroom = 100   // kill
                    e.CollidedWithJumping_Brick();
                    // e2.OtherEnemyTouchedFromLeft();
                    break ;
                case 101 ://FlyingTurtle = 101 ;
                    e.CollidedWithJumping_Brick();
                    // e2.OtherEnemyTouchedFromLeft();
                    break ;
                case 102 ://EnemyTurtle = 102   // fall upside down shell gets active after sometime
                    e.CollidedWithJumping_Brick();
                    // e2.OtherEnemyTouchedFromLeft();
                    break ;
                case 103 ://TurtelShell = 103 // bounces upside down shell
                    // e2.CollidedWithShell();
                    e.CollidedWithJumping_Brick();
                    break ;
                case 104 ://MovingTurtelShell = 104 // upside down shell MovingTurtelShell
                    // e2.CollidedWithMovingShell();
                    break ;
                case 105 ://Helmet = 105 ;  // fall upside down shell   gets active after sometime
                    e.CollidedWithJumping_Brick();
                    // e2.OtherEnemyTouchedFromLeft();
                    break ;
                case 106 ://HelmetShell = 106 ; // bounces  upside down shell
                    // e2.CollidedWithShell();
                    e.CollidedWithJumping_Brick();
                    break ;
                case 107 ://MovingHelmetShell = 107; // upside down shell MovingTurtelShell
                    e.CollidedWithJumping_Brick();
//                    e.CollidedWithMovingShell();
                    break ;
                case 108 ://Monkey = 108    // kill
                    e.CollidedWithJumping_Brick();
                    break ;
                case 109 ://Rocket = 109    // simply nothing
                    break ;
                case 110 ://plant = 110
                    break ;
                    
                    
                case 111 : // OctoPussy
                    break ;
                case 112 : // FishyWater
                    break ;
                case 113 : // FishyGround
                    break ;
                case 114 : // EnemyFireBall
                    break ;
                case 115 : // SonOfABuitch
                    break ;
                case 116 : // LavaBubble 
                    break ;
                case 117 : // Spikey // bounce & change Direction
                    e.CollidedWithJumping_Brick();
                    // e2.OtherEnemyTouchedFromLeft();
                    break ;
                case 118 : // SpikeyEgg
                    break ;
                case 119 : // Bowser
                    break ;
                    
                case 3 : // Mashroom // bounce & change Direction
                    e.CollidedWithJumping_Brick();
                    break ;
                    
                case 21 : // Coin
                    e.CollidedWithJumping_Brick();
                    break ;
                default :      ;
            }
                
                
            }
                e.setYloc(brick.getY()-enemy.getHeight());
            
        }
        else if(angle > 150 || angle < -150){
            side = "left" ;
            e.CollidedWithBrick_GoToLeft();
            
//            switch(e.getType()){
//            case 104 ://MovingTurtelShell 
//            case 107 ://MovingHelmetShell 
//                    M.playSound("music/smb_bump.play();
//                    break ;
//            }
//            
        }
        else if(angle < 30 && angle > -30){
            side = "right" ;
            e.CollidedWithBrick_GoToRight();
//            enemy.setX(brick.getX()+brick.getWidth());
//            switch(e.getType()){
//            case 104 ://MovingTurtelShell 
//            case 107 ://MovingHelmetShell 
//                    M.playSound("music/smb_bump.play();
//                    break ;
//            }
        }
        
        else{
            side= "null" ;
        }
         
        M.setDegree(side);
        
        }
    }
    
}
