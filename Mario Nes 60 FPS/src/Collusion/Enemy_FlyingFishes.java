
package Collusion;

import Objects.BasicEnemy;
import Objects.Player;
import SandBox.Mario;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.BasicCollisionGroup;


public class Enemy_FlyingFishes  extends BasicCollisionGroup {

    Mario game ;
    
 public   Enemy_FlyingFishes(Mario aThis) {
        game = aThis ;
    }

    @Override
    public void collided(Sprite enemy, Sprite flyingfishes) {
       
        BasicEnemy e1 = (BasicEnemy) flyingfishes;
        
        if(enemy.getID() == 107 | enemy.getID() == 104){
            e1.KilledByFireBall();
        }
        
    }

    
}
