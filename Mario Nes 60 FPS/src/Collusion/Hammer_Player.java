package Collusion;

import Objects.Player;
import SandBox.Mario;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.BasicCollisionGroup;

public class Hammer_Player extends BasicCollisionGroup {

    Mario game;

    public Hammer_Player(Mario aThis) {
        game = aThis;
    }

    @Override
    public void collided(Sprite hammer, Sprite player) {
        Player p = (Player) player;

        boolean collide = true;
        if (p.KeyPressedDown & player.getID() != 1) {

            if (player.getID() != 4) {
                if (hammer.getY() + hammer.getHeight() < p.getY() + 48) {
                    collide = false;
                }
            }
        }

        if (game.player.HasStar()) {
        } else {
            if (collide) {
                game.player.Decerease();
            }
        }

    }
}
