
package Animations;

import com.golden.gamedev.object.AnimatedSprite;
import java.awt.image.BufferedImage;

public class MarioGoingInPump extends AnimatedSprite{

    int delay = 70 ;
    public MarioGoingInPump(BufferedImage[] image, double x, double y) {
        super(image, x, y);
    }

    public MarioGoingInPump() {
        
    }
    
    @Override
        public void update(long l){
            delay-- ;
            if(delay < 0){
                this.setActive(false);
            }
            this.moveX(1);
            
            super.update(l);
        }
    
}
