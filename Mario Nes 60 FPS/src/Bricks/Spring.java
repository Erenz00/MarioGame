
package Bricks;

import SandBox.Mario;
import com.golden.gamedev.object.sprite.AdvanceSprite;
import java.awt.image.BufferedImage;


public class Spring  extends AdvanceSprite implements BasicBrick{

    Mario game ;
    
    public  Spring(int x, int y , BufferedImage[] b , Mario g) {
    
        this.setLocation(x, y);
        this.setImages(b);
        int anim[] =  {0 , 1 , 2 , 2 , 1 , 0 };
        this.setAnimationFrame(anim);
        this.setID(14);
        
        game = g ;
    }



    @Override
    public void RemoveIt() {
    }

    @Override
    public void HitFromDown() {
    }

    @Override
    public String getInsideItem() {
        return "empty";
    }

    public void PlayAnimation() {
        this.setAnimate(true);
    }

    @Override
    public boolean isJump() {
        return false ;
    }

    
}
    
    