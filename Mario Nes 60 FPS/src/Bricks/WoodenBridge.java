package Bricks;

import SandBox.Mario;
import com.golden.gamedev.object.Sprite;

public class WoodenBridge extends Sprite implements BasicBrick {

    Mario game;

    public WoodenBridge(int x, int y, Mario g) {

        this.setLocation(x, y);
        game = g;
        this.setImage(game.bsLoader.getStoredImage("WoodenBridge"));
        this.setID(2);



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
        return false;
    }
}
