package Collusion;

import Objects.BasicEnemy;
import Objects.Player;
import SandBox.Mario;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.BasicCollisionGroup;
import java.awt.Point;

public class Player_EnemyGroup extends BasicCollisionGroup {

    Mario game;

    public Player_EnemyGroup(Mario aThis) {
        game = aThis;
    }

//    public Player_EnemyGroup() {
//        pixelPerfectCollision = true;
//    }
    @Override
    public void collided(Sprite player, Sprite enemy) {

        Player p = (Player) player;

        BasicEnemy e = (BasicEnemy) enemy;
        boolean collide = true;
        if (p.KeyPressedDown & p.getID() != 1) {

            if (enemy.getY() + enemy.getHeight() < p.getY() + 48) {
                collide = false;

            }
        }

        String side;

        Point player_Center = new Point((int) player.getX() + player.getWidth(), (int) player.getY() + player.getHeight());
        Point enemy_Center = new Point((int) enemy.getX() + enemy.getWidth(), (int) enemy.getY() + enemy.getHeight());

        int angle = (int) Math.toDegrees(Math.atan2(player_Center.getY() - enemy_Center.getY(),
                player_Center.getX() - enemy_Center.getX()));

        game.setDegree(angle);

//        ::Enemy:: Mashroom = 3
//        ::Enemy:: Flower = 4 
//        ::Enemy:: Life = 12
//        ::Enemy:: Star = 20
//        ::Enemy:: Coin = 21
        if (e.getType() == 12) {
            game.parent.amitsAudioPlayer.smb_1_up.play();
        }
        if (e.getType() == 21) {
            e.setActive(false);
            game.parent.CoinInc();
//            game.parent.amitsAudioPlayer.smb_coin.play();
            game.parent.amitsAudioPlayer.smb_coin.play();

        }

        if (e.getType() == 3 || e.getType() == 4) { // that is if mushroom or flower
            e.setActive(false);
            p.Grow();

        } else {                  // Enemys

            if (angle > 30 && angle < 150) {
                side = "down";
                if (collide) {
                    e.EnemyJumperOnMario();
                }
            } else if (angle < -30 && angle > -150) {
                side = "up";
                e.MarioJumpedOnEnemy();
//            M.playSound("music/smb_stomp.play();
//            switch(e.getType()){
//                case 100 : // EnemyMashroom = 100
//                    break ;
//                case 101 ://FlyingTurtle = 101 ;
//                    break ;
//                case 102 ://EnemyTurtle = 102
//                    break ;
//                case 103 ://TurtelShell = 103
//                    break ;
//                case 104 ://MovingTurtelShell = 104
//                    break ;
//                case 105 ://Helmet = 105 ;
//                    break ;
//                case 106 ://HelmetShell = 106 ;
//                    break ;
//                case 107 ://MovingHelmetShell = 107;
//                    break ;
//                case 108 ://Monkey = 108
//                    break ;
//                case 109 ://Rocket = 109
//                    break ;
//                case 110 ://plant = 110
//                    break ;
//                
//            }

//            if(e.getType() == 1 || e.getType() == 2 || e.getType() == 6 || e.getType() == 7 || e.getType() == 13){ // that is enemymushroom or turtle or rocket
//                p.Jump(-8);
//                e.MarioJumpedOnEnemy();
//            }
//            else if(e.getType() == 5 || e.getType() == 14){ // Shell
//                if(e.isMoving()){
//                    p.Jump(-8);
//                    e.MarioJumpedOnEnemy();
//                }else{
//                    if(angle > 90){
//                        enemy.setX(p.getX()-enemy.getWidth()-6);
//                e.CollidedWithMarioFromTOLeft();
//                    }else if(angle < 90){
//                        enemy.setX(p.getX()+p.getWidth()+6);
//                e.CollidedWithMarioTORight();
//                    }
//                }
//            }
            } else if (angle > 150 || angle < -150) {
                side = "left";
                e.CollidedWithMarioFromTOLeft();
//            if(e.getType() == 5 || e.getType() == 14){ // Shell
//                
//                if(e.isMoving()){   // moving shell
//                    p.Decerease();
//                }else{
//                    enemy.setX(p.getX()+p.getWidth()+6);
//                e.CollidedWithMarioTORight();
//                }
//            }
//            
//            else{
//                p.Decerease();
//            }
            } else if (angle < 30 && angle > -30) {
                side = "right";
                e.CollidedWithMarioTORight();
//            if(e.getType() == 5 || e.getType() == 14){ // Shell
//                if(e.isMoving()){   // moving shell
//                    p.Decerease();
//                }else{
//                    enemy.setX(p.getX()-enemy.getWidth()-6);
//                e.CollidedWithMarioFromTOLeft();
//                }
//            }else{
//                p.Decerease();
//            }
            } else {
                side = "null";
            }

            game.setDegree(side);

//        // System.out.println(side);
        }

    }
}
