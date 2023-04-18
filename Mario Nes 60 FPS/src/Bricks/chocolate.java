package Bricks;


import com.golden.gamedev.object.Sprite;
import java.awt.image.BufferedImage;


public class chocolate extends Sprite implements BasicBrick{


    public chocolate(int x, int y, BufferedImage storedImage) {
        
        this.setLocation(x, y);
        this.setImage(storedImage);
        
        this.setID(2);
        
        
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
