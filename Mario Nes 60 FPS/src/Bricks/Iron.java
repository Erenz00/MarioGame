package Bricks;

import SandBox.Mario;
import com.golden.gamedev.object.Sprite;
import java.awt.image.BufferedImage;

public class Iron extends Sprite implements BasicBrick {

    int Gravity = -5;
    boolean Jump = true;
    Mario game;

    Iron(int x, int y, BufferedImage[] storedImage, Mario g) {

        this.setLocation(x, y);

        game = g;

        if ("Sea".equals(game.GetAttribute())) {
            this.setImage(storedImage[0]);
        } else if ("Ground".equals(game.GetAttribute())) {
            this.setImage(storedImage[1]);
        } else if ("UnderGround".equals(game.GetAttribute())) {
            this.setImage(storedImage[2]);
        } else if ("Castle".equals(game.GetAttribute())) {
            this.setImage(storedImage[3]);
        }

        this.setID(2);



    }

    public Iron(int x, int y, BufferedImage[] storedImage, String color, Mario g) {

        this.setLocation(x, y);

        game = g;

        if ("UnderGround".equals(game.GetAttribute()) || "Castle".equals(game.GetAttribute())) { // darkBackground
            this.setImage(storedImage[0]);

        } else {
            this.setImage(storedImage[1]);
        }

        this.setID(2);

    }

    public Iron(int x, int y, BufferedImage[] storedImage, String color, String bounce, Mario g) {

        this.setLocation(x, y);

        game = g;

        if ("UnderGround".equals(game.GetAttribute()) || "Castle".equals(game.GetAttribute())) { // darkBackground
            this.setImage(storedImage[0]);

        } else {
            this.setImage(storedImage[1]);
        }

        this.setID(2);

        if ("noBounce".equals(bounce)) {
            Jump = false;
            Gravity = 5;
        }

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
        // nothing will happen
    }

    @Override
    public void RemoveIt() {
    }

    @Override
    public String getInsideItem() {
        // its always empty
        return "empty";
    }

    @Override
    public boolean isJump() {
        return Jump;
    }
}
