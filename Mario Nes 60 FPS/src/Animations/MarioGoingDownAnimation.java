
package Animations;

import com.golden.gamedev.object.AnimatedSprite;
import com.golden.gamedev.object.Sprite;
import java.awt.image.BufferedImage;

public class MarioGoingDownAnimation extends Sprite{

    int delay = 100 ;
    public MarioGoingDownAnimation(BufferedImage image, double x, double y) {
        super(image, x, y);
    }

    public MarioGoingDownAnimation() {
        
    }
    
    @Override
        public void update(long l){
            delay-- ;
            if(delay < 0){
                this.setActive(false);
            }
            this.moveY(1);
            
            super.update(l);
        }
    
}
