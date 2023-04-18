package Collusion;

import Animations.Explosion;
import Objects.BasicEnemy;
import SandBox.Mario;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.BasicCollisionGroup;


public class FireBallToEnemys extends BasicCollisionGroup {

     Mario game ;
     
    public FireBallToEnemys(Mario g) {
        game = g ;
    }

     @Override
    public void collided(Sprite FireBall, Sprite Enemys) {
        
         
         BasicEnemy e1 = (BasicEnemy) Enemys;
         
            boolean Kill = false ;
            
            switch(e1.getType()){
                case 100 : // EnemyMashroom = 100
                    Kill = true ;
                    break ;
                case 101 ://FlyingTurtle = 101 ;
                    Kill = true ;
                    break ;
                case 102 ://EnemyTurtle = 102
                    Kill = true ;
                    break ;
                case 103 ://TurtelShell = 103
                    Kill = true ;
                    break ;
                case 104 ://MovingTurtelShell = 104
                    Kill = true ;
                    break ;
                case 105 ://Helmet = 105 ;
//                    e1.OtherEnemyTouchedFromRight();
                    Kill = true ;
                    break ;
                case 106 ://HelmetShell = 106 ;
//                    e1.CollidedWithShell();
                    Kill = true ;
                    break ;
                case 107 ://MovingHelmetShell = 107;
//                    e1.CollidedWithMovingShell();
                    Kill = true ;
                    break ;
                case 108 ://Monkey = 108
                    Kill = true ;
                    break ;
                case 109 ://Rocket = 109
                    Kill = true ;
                    break ;
                case 110 ://plant = 110
                    Kill = true ;
                    break ;
                    
                    
                case 111 : // OctoPussy
                    Kill = true ;
                    break ;
                case 112 : // FishyWater
                    Kill = true ;
                    break ;
                case 113 : // FishyGround
                    Kill = true ;
                    break ;
                case 114 : // EnemyFireBall
                    Kill = true ;
                    break ;
                case 115 : // SonOfABuitch
                    Kill = true ;
                    break ;
                case 116 : // LavaBubble
                    Kill = true ;
                    break ;
                case 117 : // Spikey
//                    break ;
                case 118 : // SpikeyEgg
//                    break ;
                case 119 : // Bowser
                    e1.KilledByFireBall();
                    FireBall.setActive(false);
                    game.AnimationGroup.add(new Explosion(FireBall.getX() , FireBall.getY() , game.bsLoader.getStoredImages("Explosion")));

                    break ;
                
            }
            
            if(Kill){
                
                e1.KilledByFireBall();
                
                FireBall.setActive(false);
//                 game.parent.amitsAudioPlayer.smb_kick.play();
////                Enemys.setActive(false);
                
                game.AnimationGroup.add(new Explosion(FireBall.getX() , FireBall.getY() , game.bsLoader.getStoredImages("Explosion")));

            }
        
    }
    
}
