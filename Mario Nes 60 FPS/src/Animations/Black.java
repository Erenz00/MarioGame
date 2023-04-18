package Animations;

import com.golden.gamedev.object.Sprite;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Black extends Sprite {

    int delay;

    public Black(BufferedImage image, int i, int i0, int del) {
        super(image, i, i0);

        delay = del;

    }

    @Override
    public void render(Graphics2D g) {

        delay--;
        if (delay < 0) {
            super.render(g);
        }

    }
}
