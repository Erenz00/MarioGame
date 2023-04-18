package Animations;

import Objects.Flower;
import SandBox.Mario;
import com.golden.gamedev.object.AnimatedSprite;
import com.golden.gamedev.object.Timer;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class FlowerAnim extends AnimatedSprite {

    int reachPosY;
    Mario game;
    int GiveSomeTimeToRender = 32;
    boolean start = false;

    public FlowerAnim(int x, int y, BufferedImage[] storedImages, Mario M) {
        setLocation(x, y);
        setImages(storedImages);
        reachPosY = y - 32;

        this.setAnimate(true);
        this.setLoopAnim(true);
        this.setAnimationTimer(new Timer(100));

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

                game.EnemyGroup.add(new Flower((int) this.getX(), reachPosY, game.bsLoader.getStoredImages("Flower"), game));

                this.setActive(false);

            }

        }

        super.update(l);
    }

    @Override
    public void render(Graphics2D g) {
        if (start) {
            super.render(g);
        }

    }
}
