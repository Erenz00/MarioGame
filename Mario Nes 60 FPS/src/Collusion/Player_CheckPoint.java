package Collusion;

import Animations.FlagWin;
import Animations.FireWorks;
import Objects.Player;
import SandBox.Mario;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.BasicCollisionGroup;
import com.golden.gamedev.object.sprite.VolatileSingleImage;
import com.golden.gamedev.util.ImageUtil;
import java.util.Random;

public class Player_CheckPoint extends BasicCollisionGroup {

    Mario game;

    public Player_CheckPoint(Mario f) {
        game = f;
    }

    @Override
    public void collided(Sprite player, Sprite checkpoint) {

        Player p = (Player) player;

//        String side = "?";
//        Point player_Center = new Point((int)player.getX()+player.getWidth() , (int)player.getY()+player.getHeight());
//        Point checkpoint_Center;
//        checkpoint_Center = new Point((int)checkpoint.getX()+ checkpoint.getWidth() , (int)checkpoint.getY()+checkpoint.getHeight());
//        
//        int angle = (int) Math.toDegrees(Math.atan2(  player_Center.getY() - checkpoint_Center.getY(),
//                 player_Center.getX() - checkpoint_Center.getX()
//                ));
//        
//        game.setDegree(angle);
//         if(angle > 50 && angle < 130){
//            side = "down" ;
//            p.GoDown();
//        }
//        else if(angle <= -30 && angle >= -150){
//            side = "up" ;
//            p.setY(checkpoint.getY()-p.getHeight());
////            if(checkpoint.getID() == 25){ // InsidePumpvertically
////                /// add animated mario going down
////                game.MarioGoingDownAnimation();
////                p.setActive(false);
////            }
//        }
//        else if(angle > 150 || angle < -150){
//            side = "left" ;
        // System.out.println("CheckPoint :"+checkpoint.getID() );
        switch (checkpoint.getID()) {

            case 23: // CheckPoints
                Random r = new Random();
                if (r.nextBoolean()) {
                    game.AnimationGroup.add(new FireWorks((int) checkpoint.getX(), (int) checkpoint.getY(), game));
                }// random fireworks
                game.LevelComplete(checkpoint);
                p.setActive(false);

                game.UnderGroup.add(new FlagWin(checkpoint.getX(), game));
                // victory mario 
                // flag
                game.DelayToNextCheckPoint = 400;
                game.parent.amitsAudioPlayer.stopAllMusic();
                game.parent.amitsAudioPlayer.smb_stage_clear.play();
                break;

            case 16: // WhyYouDOThis

                game.boss.BadGameGlitch();
                game.VolitileGroup.add(new Sprite(game.bsLoader.getStoredImage("AnotherCastleMessage"), checkpoint.getX() - 224, checkpoint.getY() - 192));
                VolatileSingleImage black = new VolatileSingleImage(ImageUtil.createImage(384, 96), checkpoint.getX() - 224, checkpoint.getY() - 164, 50);
                game.VolitileGroup.add(black);
                p.setX(checkpoint.getX() - p.getWidth());
                p.StopSpeed();
                p.NoControlling();
                p.StopMoveForward();
                game.LevelComplete(checkpoint, 400);
                break;

            case 24: // InsidePumpHorzontally

//                boolean ok = false ;
//                
////                if(p.getID() == 1){ // small // height
//                    if(p.getY() < checkpoint.getY() ){
//                        ok = true ;
//                    }
////                }
//                
//                if(ok){
                if (p.KeyPressRight() && p.OnGround) {
                    game.MarioGoingInsidePumpToRight();
                    game.LevelComplete(checkpoint);

                    // remove staranimation
                    game.player.setActive(false);
                    game.parent.amitsAudioPlayer.stopAllMusic();
                    game.parent.amitsAudioPlayer.smb_pipe.play();
                    p.setActive(false);
//        }
                }
                break;

            case 25: // InsidePumpvertically

                if (p.getX() < checkpoint.getX() + 10
                        && p.getX() > checkpoint.getX() - 10) {

                    if (p.KeyPressedDown()) {

                        game.MarioGoingDownAnimation();
                        game.LevelComplete(checkpoint);
                        game.parent.amitsAudioPlayer.stopAllMusic();
                        game.parent.amitsAudioPlayer.smb_pipe.play();
                        p.setActive(false);
                    }

                }
                break;

            case 26: //  Princess

                game.boss.BadGameGlitch();
                game.VolitileGroup.add(new Sprite(game.bsLoader.getStoredImage("QuestComplete"), checkpoint.getX() - 224, checkpoint.getY() - 192));
                p.setX(checkpoint.getX() - p.getWidth());
                p.StopSpeed();
                p.NoControlling();
                p.StopMoveForward();
//            game.LevelComplete(checkpoint , 200);

                break;

            case 27:

                game.LevelComplete(checkpoint);
                p.setActive(false);

                game.DelayToNextCheckPoint = 200;
                game.parent.amitsAudioPlayer.stopAllMusic();
//                 game.parent.amitsAudioPlayer.smb_stage_clear.play();
                break;

            case 28:

                if (p.KeyPressedUP) {
                    game.LevelComplete(checkpoint);
                    p.setActive(false);
                    game.DelayToNextCheckPoint = 200;
                    game.parent.amitsAudioPlayer.stopAllMusic();
                }
//                 game.parent.amitsAudioPlayer.smb_stage_clear.play();
                break;

        }
//            if(checkpoint.getID() == 23){ // CheckPoints
//            p.setActive(false);
//            
//            
//            }
//            else if(checkpoint.getID() == 16){ // WhyYouDOThis
//                
//            game.boss.BadGameGlitch();
//            game.VolitileGroup.add(new Sprite(game.bsLoader.getStoredImage("AnotherCastleMessage"), checkpoint.getX() - 224,checkpoint.getY()-192 )); 
//            
//            }
//            if(checkpoint.getID() == 24){ // InsidePumpvertically
        /// add animated mario going down
//            }
//            
//            
//            
//        }
//        else if(angle < 30 && angle > -30){
//            side = "right" ;
//            p.setX(checkpoint.getX()+checkpoint.getWidth());
//            p.StopSpeed();
//            
//        }
//        
//        else{
//            side= "null" ;
//        }
//        game.setDegree(side);
//        // System.out.println("CheckPoint :"+checkpoint.getID() );
    }
}
