package Bricks;

import Animations.CoinAnim;
import SandBox.Mario;
import java.awt.image.BufferedImage;

public class Bank extends Brick implements BasicBrick {

    int ActiveCoins = 100;
    boolean active = false;

    public Bank(int x, int y, BufferedImage storedImage, Mario g) {
        super(x, y, storedImage, g);
        this.setID(10);

        game = g;
    }

    @Override
    public void update(long l) {

        if (active) {
            ActiveCoins--;
        }

        super.update(l);
    }

    @Override
    public void HitFromDown() {

        if (ActiveCoins < 0) {
            game.BrickGroup.add(new Iron((int) this.getX(), (int) this.getY(), game.bsLoader.getStoredImages("Iron"), game));
            this.setActive(false);
        } else {
            active = true;
            game.AnimationGroup.add(new CoinAnim((int) this.getX(), (int) this.getY(), game.bsLoader.getStoredImages("CoinAnim"), game));
            Bounce();
            //        super.HitFromDown();

        }
    }

    @Override
    public String getInsideItem() {
        return "empty";
    }

    @Override
    public boolean isJump() {
        return Jump;
    }
}
