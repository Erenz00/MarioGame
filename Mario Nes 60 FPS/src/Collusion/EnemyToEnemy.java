
package Collusion;

import Animations.FallingDeadSprites;
import Objects.BasicEnemy;
import SandBox.Mario;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.BasicCollisionGroup;
import java.awt.Point;

public class EnemyToEnemy  extends BasicCollisionGroup {

    Mario game ;
    
 public   EnemyToEnemy(Mario aThis) {
        game = aThis ;
    }

    @Override
    public void collided(Sprite Enemy1, Sprite Enemy2) {

        boolean NoCollusion = false ;
        
        String side = "?" ;
        
        BasicEnemy e1 = (BasicEnemy) Enemy1;
        BasicEnemy e2 = (BasicEnemy) Enemy2;
        
        Point Enemy1_Center = new Point((int)Enemy1.getX()+Enemy1.getWidth() , (int)Enemy1.getY()+Enemy1.getHeight());
        Point Enemy2_Center = new Point((int)Enemy2.getX()+Enemy2.getWidth() , (int)Enemy2.getY()+Enemy2.getHeight());
        
        int angle = (int) Math.toDegrees(Math.atan2(  Enemy1_Center.getY() - Enemy2_Center.getY(),
                 Enemy1_Center.getX() - Enemy2_Center.getX()
                ));
        
        game.setDegree(angle);
        
        switch(e1.getType()) { // no collusion to items
        
            case  3 : // Mashroom = 3
            case  4 : // Flower = 4 
            case  12 : // Life = 12
            case  20 : // Star = 20
            case  21 : // Coin = 20
            NoCollusion = true ;
        
                break ;
            // no collusion
        } 
        switch(e2.getType()) { // no collusion to items
        
            case  3 : // Mashroom = 3
            case  4 : // Flower = 4 
            case  12 : // Life = 12
            case  20 : // Star = 20
            case  21 : // Coin = 20
            NoCollusion = true ;
        
                break ;
            // no collusion
        }
            if(NoCollusion){ // itens
        }
        else
                
                
        {
        
         if(angle > 50 && angle < 130){
            side = "down" ;
            
        }
        else if(angle <= -30 && angle >= -150){
            side = "up" ;
            
        }
        else if(angle > 150 || angle < -150){
            side = "left" ;
            switch(e1.getType()){
                case 100 : // EnemyMashroom = 100
                    e1.OtherEnemyTouchedFromRight();
                    e2.OtherEnemyTouchedFromLeft();
                    break ;
                case 101 ://FlyingTurtle = 101 ;
                    e1.OtherEnemyTouchedFromRight();
                    e2.OtherEnemyTouchedFromLeft();
                    break ;
                case 102 ://EnemyTurtle = 102
                    e1.OtherEnemyTouchedFromRight();
                    e2.OtherEnemyTouchedFromLeft();
                    break ;
                case 103 ://TurtelShell = 103
                    e2.CollidedWithShell();
                    e1.CollidedWithShell();
                    break ;
                case 104 ://MovingTurtelShell = 104
                    e2.CollidedWithMovingShell();
                     game.parent.amitsAudioPlayer.smb_kick.play();
                    break ;
                case 105 ://Helmet = 105 ;
                    e1.OtherEnemyTouchedFromRight();
                    e2.OtherEnemyTouchedFromLeft();
                    break ;
                case 106 ://HelmetShell = 106 ;
                    e2.CollidedWithShell();
                    e1.CollidedWithShell();
                    break ;
                case 107 ://MovingHelmetShell = 107;
                    e2.CollidedWithMovingShell();
                     game.parent.amitsAudioPlayer.smb_kick.play();
//                    e1.CollidedWithMovingShell();
                    break ;
                case 108 ://Monkey = 108
                    break ;
                case 109 ://Rocket = 109
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
                case 117 : // Spikey
                    e1.OtherEnemyTouchedFromRight();
                    e2.OtherEnemyTouchedFromLeft();
                    break ;
                case 118 : // SpikeyEgg
                    break ;
                case 119 : // Bowser
                    break ;
                
                default :      ;
            }
            
            
           
        }
        else if(angle < 30 && angle > -30){
            
            side = "right" ;
             switch(e1.getType()){
                case 100 : // EnemyMashroom = 100
                    e1.OtherEnemyTouchedFromLeft();
                    e2.OtherEnemyTouchedFromRight();
                    break ;
                case 101 ://FlyingTurtle = 101 ;
                    e1.OtherEnemyTouchedFromLeft();
                    e2.OtherEnemyTouchedFromRight();
                    break ;
                case 102 ://EnemyTurtle = 102
                    e1.OtherEnemyTouchedFromLeft();
                    e2.OtherEnemyTouchedFromRight();
                    break ;
                case 103 ://TurtelShell = 103
                    e2.CollidedWithShell();
                    e1.CollidedWithShell();
                    break ;
                case 104 ://MovingTurtelShell = 104
                    e2.CollidedWithMovingShell();
                     game.parent.amitsAudioPlayer.smb_kick.play();
                    break ;
                case 105 ://Helmet = 105 ;
                    e1.OtherEnemyTouchedFromLeft();
                    e2.OtherEnemyTouchedFromRight();
                    break ;
                case 106 ://HelmetShell = 106 ;
                    e2.CollidedWithShell();
                    e1.CollidedWithShell();
                    break ;
                case 107 ://MovingHelmetShell = 107;
                    e2.CollidedWithMovingShell();
                     game.parent.amitsAudioPlayer.smb_kick.play();
                    //                    e1.CollidedWithMovingShell();
                    break ;
                case 108 ://Monkey = 108
                    break ;
                case 109 ://Rocket = 109
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
                case 117 : // Spikey
                    e1.OtherEnemyTouchedFromLeft();
                    e2.OtherEnemyTouchedFromRight();
                    break ;
                case 118 : // SpikeyEgg
                    break ;
                case 119 : // Bowser
                    break ;
                
                default :      ;        
            
            }
        }
        
        else{
            side= "null" ;
        }
         
         }
         
        game.setDegree(side);
    
            
    }

}
