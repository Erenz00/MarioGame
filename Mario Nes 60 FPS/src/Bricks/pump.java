
package Bricks;

import SandBox.Mario;
import com.golden.gamedev.object.Sprite;
import java.awt.image.BufferedImage;


public class pump extends Sprite implements BasicBrick{

    public pump(BufferedImage storedImage, int x, int y, Mario g) {
        super(storedImage , x , y );
    }

    @Override
    public void HitFromDown() {
    }

    @Override
    public void RemoveIt() {
    }

    @Override
    public String getInsideItem() {
        return null ;
    }

    @Override
    public boolean isJump() {
        return false ;
    }
    
}
