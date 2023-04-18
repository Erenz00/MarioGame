package Collusion;

import Animations.Explosion;
import Objects.BasicEnemy;
import Objects.FireBall;
import SandBox.Mario;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.BasicCollisionGroup;
import java.awt.Point;

    
public class FireBall_FlyingFishes extends BasicCollisionGroup {
    
    Mario game ;

    public FireBall_FlyingFishes(Mario g) {
        game = g ;
    }

    @Override
    public void collided(Sprite FireBall, Sprite Bricks) {
        
         FireBall.setActive(false);
         game.AnimationGroup.add(new Explosion(FireBall.getX() , FireBall.getY() , game.bsLoader.getStoredImages("Explosion")));

        BasicEnemy e1 = (BasicEnemy) Bricks;
        e1.KilledByFireBall();
            
    }

}
