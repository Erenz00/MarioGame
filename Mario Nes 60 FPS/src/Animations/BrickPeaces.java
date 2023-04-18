package Animations;

import com.golden.gamedev.object.AnimatedSprite;
import com.golden.gamedev.object.Timer;
import java.awt.image.BufferedImage;

public class BrickPeaces extends AnimatedSprite {

    private double Gravity = - 5;
    private double XSpeed = - 2;

    public BrickPeaces(double x, double y, int MoveX, int MoveY, BufferedImage[] images) {
        this.setLocation(x + 8, y + 8);
        this.setImages(images);

        this.setAnimate(true);
        this.setLoopAnim(true);

        this.setAnimationTimer(new Timer(200));

        XSpeed = MoveX;

        Gravity = MoveY;

    }

    @Override
    public void update(long l) {

        if (Gravity < 10) {
            Gravity += 1;

        }

        this.moveY(Gravity);

        this.moveX(XSpeed);

        if (this.getY() > 500) {
            this.setActive(false);
        }

        super.update(l);

    }
}
