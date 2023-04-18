package Animations;

import SandBox.Mario;
import com.golden.gamedev.object.AnimatedSprite;
import com.golden.gamedev.object.Timer;
import java.awt.image.BufferedImage;

public class BossFallingAnim extends AnimatedSprite {

    Mario game;
    int delay = 180; // wait till bridge explodes

    public BossFallingAnim(BufferedImage[] storedImages, double x, double y, Mario g) {
        super(storedImages, x, y);
        setAnimationTimer(new Timer(100));
        setAnimate(true);
        setLoopAnim(true);
        setAnimationFrame(4, 5);

        game = g;
    }

    @Override
    public void update(long l) {
        delay--;
        if (delay < 0) {
            this.moveY(3);
            setAnimate(false);
        }

        if (delay == 2) {
             game.parent.amitsAudioPlayer.smb_bowserfalls.play();
//            game.parent.amitsAudioPlayer.smb_bowserfalls.play();
        }
        super.update(l);

        if (this.getY() > 700) {
            game.PlayEnemys();
            game.player.MoveForward();
            this.setActive(false);
            game.removeDemoMario();

        }
    }
}
