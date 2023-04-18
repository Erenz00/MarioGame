package Bricks;

import SandBox.Mario;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.util.ImageUtil;
import java.awt.image.BufferedImage;

public class Brick extends Sprite implements BasicBrick {

    int Gravity = 4;
    int[] yloc = {};
    boolean Jump = false;
    Mario game;

    public Brick(int x, int y, BufferedImage b, Mario g) {

        this.setLocation(x, y);
        this.setImage(b);

        this.setID(3);

        game = g;
    }

    @Override
    public void update(long l) {

        if (Gravity >= 4) {
            Jump = false;
        } else {
            Gravity = Gravity + 1;
            this.moveY(Gravity);
        }

    }

    @Override
    public void HitFromDown() {


        if (game.player.getID() == 2 || game.player.getID() == 3) { // if big or fire Amrio
            game.addSomeBrickFragmends(this.getX(), this.getY());
            // if some enemys ar on that brick they will fall on player ... so kill em
            game.BrickGroup.add(new TemporaryAndInvisibleBrick((int) this.getX(), (int) this.getY(), ImageUtil.createImage(32, 32, 3), game));

            RemoveIt(); // remove this brick
        }
        if (Gravity <= 0 & Jump) {        // going up don nothing
        } else if (Gravity > 0 & Jump) {  // coming down = invert gravity
            Gravity = ~Gravity;
        } else {
            Gravity = -5;  // what if already jumping
            Jump = true;
            game.player.GoDown();
        }
    }

    @Override
    public void RemoveIt() {
        this.setActive(false);
    }

    @Override
    public String getInsideItem() {
        return "empty";
    }

    public void Bounce() {
        Gravity = -5;
        Jump = true;
    }

    @Override
    public boolean isJump() {
        return Jump;
    }
}
