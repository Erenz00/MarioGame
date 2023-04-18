package Collusion;

import Bricks.BasicBrick;
import Bricks.Spring;
import Objects.Player;
import SandBox.Mario;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.BasicCollisionGroup;
import java.awt.Point;

public class Player_Brick extends BasicCollisionGroup {

    Mario game;

    public Player_Brick(Mario aThis) {
        game = aThis;
    }

    @Override
    public void collided(Sprite player, Sprite brick) {

        Player p = (Player) player;
        BasicBrick b = (BasicBrick) brick;
        boolean collide = true;
        if (p.KeyPressedDown & p.getID() != 1) {

            if (brick.getY() + brick.getHeight() < p.getY() + 32) {
                collide = false;
            
            }
        }

        String side = "?";

        Point player_Center = new Point((int) player.getX() + player.getWidth(), (int) player.getY() + player.getHeight());
        Point brick_Center;
        brick_Center = new Point((int) brick.getX() + brick.getWidth(), (int) brick.getY() + brick.getHeight());

        int angle = (int) Math.toDegrees(Math.atan2(player_Center.getY() - brick_Center.getY(),
                player_Center.getX() - brick_Center.getX()));

        game.setDegree(angle);

//        if(b.getID() == 16 ){   // WhyYouDOThis
//            
//            p.NoControlling();
//            p.StopMoveForward();
//            p.setX(brick.getX()-32);
//            game.boss.BadGameGlitch();
//            game.VolitileGroup.add(new Sprite(game.bsLoader.getStoredImage("AnotherCastleMessage"), brick.getX() - 224,brick.getY()-192 ));
//        }
        if (p.getID() == 1 ) { // if small mario
            if (angle > 50 && angle < 130) {
                side = "down";

                if (collide) {
                    if (!p.isFalling()) {
                        b.HitFromDown();
                        p.GoDown();
                        switch (b.getID()) {
                            case 2:// iron
                                 game.parent.amitsAudioPlayer.smb_bump.play();
                                break;
                        }
                    }
                }
            }

        } else { // if big mario
            if (angle > 70 && angle < 110) {
                side = "down";

                if (collide) {
                    if (!p.isFalling()) {
                        b.HitFromDown();
                        p.GoDown();
                        switch (b.getID()) {
                            case 2:// iron
                                 game.parent.amitsAudioPlayer.smb_bump.play();
                                break;
                        }
                    }
                }
            }

        }

        if (angle <= -50 && angle >= -130) {
            side = "up";
            if (b.getID() == 15) {   // Axe


                p.NoControlling();
                p.MoveForward();

                if (game.boss.isActive()) {
                    game.pauseEnemys();
                    game.RemoveBridge(brick);
                }

                b.setActive(false);

                for (int i = 0; i < game.EnemyGroup.getSize(); i++) {
                    if (game.EnemyGroup.getSprites()[i] != null) {
                        game.EnemyGroup.getSprites()[i].setActive(false);
                    }
                }
            }
            if (b.getID() != 17) {    // invisible brick

                if (b.getID() == 13) { // bouncer
                    p.Jump(-22);
                }

                if (b.getID() == 14) { // Spring
                    Spring S = (Spring) brick;
                    S.PlayAnimation();
                } else {
                    p.setY(brick.getY() - p.getHeight());
                    if (p.ComingDown()) {
                        p.OnGround();
                    }

                }
            }
        }

        if (angle > 135 || angle < -135) {
            if (b.getID() != 17) {    // invisible brick
                side = "left";
                if (p.speed >= 0) {
                    p.setX(brick.getX() - p.getWidth());
                    p.StopSpeed();
                }
            }
        }
        if (angle < 45 && angle > -45) {
            if (b.getID() != 17) {    // invisible brick
                side = "right";

                if (p.speed <= 0) {
                    p.setX(brick.getX() + brick.getWidth());
                    p.StopSpeed();
                }
            }
        }

//        else{
//            side= "null" ;
//        }

        game.setDegree(side);

    }
}
