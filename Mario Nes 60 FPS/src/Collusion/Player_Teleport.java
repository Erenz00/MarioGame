
package Collusion;

import Objects.Player;
import SandBox.Mario;
import Teleport.SpriteTeleport;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.BasicCollisionGroup;


public class Player_Teleport  extends BasicCollisionGroup {

    Mario M ;
    
 public   Player_Teleport(Mario aThis) {
        M = aThis ;
    }

    
    
    @Override
    public void collided(Sprite player, Sprite Teleport) {
        
        Player p = (Player) player ;
        SpriteTeleport t =  (SpriteTeleport) Teleport;
        
//        String side = "?";
//        
//        Point player_Center = new Point((int)player.getX()+player.getWidth() , (int)player.getY()+player.getHeight());
//        Point basiclift_Center;
//        basiclift_Center = new Point((int)basiclift.getX()+ basiclift.getWidth() , (int)basiclift.getY()+basiclift.getHeight());
//        
//        int angle = (int) Math.toDegrees(Math.atan2(  player_Center.getY() - basiclift_Center.getY(),
//                 player_Center.getX() - basiclift_Center.getX()
//                ));
//        
//        M.setDegree(angle);
        
//        p.setX(107*32);
        p.setX(t.teleport.getOUTx());
        
    }
    
}
