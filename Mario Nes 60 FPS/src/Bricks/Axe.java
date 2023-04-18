package Bricks;

import SandBox.Mario;
import com.golden.gamedev.object.Timer;
import com.golden.gamedev.object.sprite.AdvanceSprite;
import java.awt.image.BufferedImage;

public class Axe extends AdvanceSprite implements BasicBrick {

    Mario game;

    public Axe(int x, int y, BufferedImage[] storedImage, Mario g) {

        this.setLocation(x, y);
        this.setImages(storedImage);

        this.setAnimate(true);
        this.setLoopAnim(true);
        int[] a = {0, 1, 2, 3, 2, 1};
        this.setAnimationFrame(a);
        this.setAnimationTimer(new Timer(200));

//        // System.out.println(ImageUtil.getImage(this.getClass().getResource("Images\\QuestionMark.png")).getWidth());

        this.setID(15);

        game = g;

    }

    @Override
    public void update(long elapsedTime) {
        if (game.player.getX() > this.getX()) {
            game.player.setX(this.getX());
        }
        super.update(elapsedTime);
    }

    @Override
    public void HitFromDown() {
    }

    @Override
    public void RemoveIt() {
    }

    @Override
    public String getInsideItem() {
        return "empty";
    }

    @Override
    public boolean isJump() {
        return false;
    }
}