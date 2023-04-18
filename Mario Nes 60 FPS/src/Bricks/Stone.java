
package Bricks;

import com.golden.gamedev.object.Sprite;
import java.awt.image.BufferedImage;


public class Stone extends Sprite implements BasicBrick{

    public Stone(int x, int y, BufferedImage Stone_Image) {
        this.setLocation(x, y);
        this.setImage(Stone_Image);
        // will not bounce when hit from down
        this.setID(4);
    }

    @Override
    public void HitFromDown() {
    }

    @Override
    public void RemoveIt() {
    }

    @Override
    public String getInsideItem() {
         return "empty" ;
    }

    @Override
    public boolean isJump() {
        return false ;
    }
    
}
