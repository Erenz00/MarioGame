package Animations;

import Objects.Life;
import Objects.Mashroom;
import Objects.Star;
import SandBox.Mario;
import com.golden.gamedev.object.Sprite;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class StarAnim extends Sprite {

    int reachPosY;
    Mario game;
    int GiveSomeTimeToRender = 32;
    boolean start = false;

    public StarAnim(int x, int y, BufferedImage storedImages, Mario M) {
        setLocation(x, y);
        setImage(storedImages);
        reachPosY = y - 32;

        game = M;
         game.parent.amitsAudioPlayer.smb_powerup_appears.play();
    }

    @Override
    public void update(long l) {
        if (GiveSomeTimeToRender > 0) {
            GiveSomeTimeToRender--;

        } else {
            start = true;
        }

        if (start) {

            if (this.getY() >= reachPosY) {
                this.moveY(-0.5);
            } else {

                game.EnemyGroup.add(new Star((int) this.getX(), reachPosY, game.bsLoader.getStoredImages("Star"), game));

                this.setActive(false);

            }

        }
    }

    @Override
    public void render(Graphics2D g) {
        if (start) {
            super.render(g);
        }

    }
}
