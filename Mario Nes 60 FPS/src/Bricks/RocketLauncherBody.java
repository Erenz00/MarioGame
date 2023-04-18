
package Bricks;

import SandBox.Mario;
import com.golden.gamedev.object.Sprite;
import java.awt.image.BufferedImage;


public class RocketLauncherBody extends Sprite implements BasicBrick{

    Mario game ;
    
    public RocketLauncherBody(BufferedImage bufferedImage, int x, int y , Mario M) {
        super(bufferedImage , x , y );
       
        game = M ;
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
