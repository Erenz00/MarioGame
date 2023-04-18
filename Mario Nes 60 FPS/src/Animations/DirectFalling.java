package Animations;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.util.ImageUtil;
import java.awt.image.BufferedImage;

public class DirectFalling extends Sprite {

    double Gravity = 0;
    boolean ToRight = true;
    boolean StraightDown = true;

    public DirectFalling(BufferedImage image, double x, double y, boolean direction) {
        this.setLocation(x, y);
        this.setImage(ImageUtil.flipVertical(image));
        ToRight = direction;
        StraightDown = false;
    }

    public DirectFalling(BufferedImage image, double x, double y) {
        this.setLocation(x, y);
        this.setImage(ImageUtil.flipVertical(image));
    }

    public DirectFalling(BufferedImage image, double x, double y, int Gravity) {
        this.setLocation(x, y);
        this.setImage(ImageUtil.flipVertical(image));
        this.Gravity = Gravity;
    }

    public DirectFalling(double x, double y, BufferedImage image) {
        this(image, x, y);
    }

    @Override
    public void update(long l) {

        if (!StraightDown) {
            if (ToRight) {
                moveX(1);
            } else {
                moveX(-1);
            }
        }

        if (Gravity < 3) {
            Gravity = Gravity + 0.25;
        }
        this.moveY(Gravity);

        if (this.getY() > 500) {
            this.setActive(false);
        }
        super.update(l);

    }
}
