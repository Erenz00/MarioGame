package Animations;

import SandBox.Mario;
import com.golden.gamedev.object.AnimatedSprite;
import com.golden.gamedev.object.Timer;
import com.golden.gamedev.util.ImageUtil;
import java.awt.image.BufferedImage;

public class BigToSmallMarioAnim extends AnimatedSprite {

    Mario game;
    boolean Right = true;

    public BigToSmallMarioAnim(double x, double y, BufferedImage[] storedImages, Mario g, boolean RightActivated) {

        Right = RightActivated;

        if (Right) {
            this.setImages(storedImages);
        } else {

            BufferedImage[] Allimages = storedImages;

            for (int i = 0; i < Allimages.length; i++) {
                Allimages[i] = ImageUtil.flipHorizontal(Allimages[i]);
            }

            this.setImages(Allimages);

        }


        setLocation(x, y);

        setAnimationTimer(new Timer(120));
        setAnimate(true);
        setLoopAnim(true);

        game = g;


    }

    @Override
    public void update(long l) {

        if (this.getFrame() + 1 == this.getImages().length) {

            game.PlayEnemys();

            game.getPlayer().setImages(game.bsLoader.getStoredImages("player"));

            this.setActive(false);

        }

        super.update(l);

//        // System.out.println("SmallToBigMarioAnim " + this.getFrame());
    }
}
