package Animations;

import SandBox.Mario;
import com.golden.gamedev.object.AnimatedSprite;
import com.golden.gamedev.object.Timer;
import java.awt.image.BufferedImage;

public class CoinAnim extends AnimatedSprite {

    int Gravity = -15;
    Mario game;

    public CoinAnim(int x, int y, BufferedImage[] storedImages, Mario M) {
        setLocation(x, y);
        setImages(storedImages);
        setAnimationTimer(new Timer(100));
        setAnimate(true);
        setLoopAnim(true);

        game = M;
        game.parent.CoinInc();
         game.parent.amitsAudioPlayer.smb_coin.play();
    }

    @Override
    public void update(long l) {

        if (Gravity < 10) {
            Gravity = Gravity + 1;
            this.moveY(Gravity);
        } else {
            this.setActive(false);
        }

        super.update(l);
    }
}
