
package Collusion;

import Bricks.BasicBrick;
import Bricks.Spring;
import Lifts.BasicLift;
import Objects.Player;
import SandBox.Mario;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.BasicCollisionGroup;
import java.awt.Point;


public class Player_Lift  extends BasicCollisionGroup {

    Mario M ;
    
 public   Player_Lift(Mario aThis) {
        M = aThis ;
    }

    
    
    @Override
    public void collided(Sprite player, Sprite basiclift) {
        
        Player p = (Player) player ;
        BasicLift b = (BasicLift) basiclift;
        
        String side = "?";
        
        Point player_Center = new Point((int)player.getX()+player.getWidth() , (int)player.getY()+player.getHeight());
        Point basiclift_Center;
        basiclift_Center = new Point((int)basiclift.getX()+ basiclift.getWidth() , (int)basiclift.getY()+basiclift.getHeight());
        
        int angle = (int) Math.toDegrees(Math.atan2(  player_Center.getY() - basiclift_Center.getY(),
                 player_Center.getX() - basiclift_Center.getX()
                ));
        
        M.setDegree(angle);
        
//        if(b.getID() == 16 ){   // WhyYouDOThis
//            
//            p.NoControlling();
//            p.StopMoveForward();
//            p.setX(basiclift.getX()-32);
//            M.boss.BadGameGlitch();
//            M.VolitileGroup.add(new Sprite(M.bsLoader.getStoredImage("AnotherCastleMessage"), basiclift.getX() - 224,basiclift.getY()-192 ));
//        }
        
//         if(angle > 10 && angle < 170){
//            side = "down" ;
//            if(!p.isFalling()){
//                
//                p.GoDown();
//            }
//            
//        }
         
//        if(angle <= -10 && angle >= -170){
//            side = "up" ;
           if(p.isFalling()){
            p.setY(basiclift.getY()-p.getHeight());
            b.MarioIsOnLift();
            p.OnGround();
            p.OnLift();
           }
            
//        }
//         if(angle > 160 || angle < -160){
//            
//            side = "left" ;
//           if(p.speed > 0){
//            p.setX(basiclift.getX()-p.getWidth());
//            p.StopSpeed();
//            }
//        
//        }
//         if(angle < 20 && angle > -20){
//            
//            side = "right" ;
//            if(p.speed < 0){
//            p.setX(basiclift.getX()+basiclift.getWidth());
//            p.StopSpeed();
//            }
//        
//        }
        
         
        M.setDegree(side);
        
//        // System.out.println(" lift " + side);
    }
    
}
