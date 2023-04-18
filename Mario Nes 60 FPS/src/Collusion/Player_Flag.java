package Collusion;

import Animations.MarioSlidingDown;
import Objects.Player;
import SandBox.Mario;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.BasicCollisionGroup;

public class Player_Flag extends BasicCollisionGroup {

    Mario game;
    boolean FirstTime = true;

    public Player_Flag(Mario f) {
        game = f;
    }

    @Override
    public void collided(Sprite Flag, Sprite player) {

        Player p = (Player) player;
        // spets
        //pause game
        if (FirstTime & Flag.getID() == -2) {

            for (int i = 0; i < game.EnemyGroup.getSize(); i++) {
                if (game.EnemyGroup.getSprites()[i] != null) {
                    game.EnemyGroup.getSprites()[i].setActive(false);
                }
            }
            game.pauseEnemys();

            // 2 animate for some time
            game.AnimationGroup.add(new MarioSlidingDown(Flag.getX() - 24, p.getY(), game));
            game.StartFlagScrollingDown();
            game.parent.amitsAudioPlayer.stopAllMusic();
             game.parent.amitsAudioPlayer.smb_flagpole.play();
            FirstTime = false;


        }
    }
}
