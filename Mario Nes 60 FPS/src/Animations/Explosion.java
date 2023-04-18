
package Animations;

import com.golden.gamedev.object.Timer;
import com.golden.gamedev.object.sprite.VolatileSprite;
import java.awt.image.BufferedImage;


public class Explosion extends VolatileSprite {

    public Explosion(double x, double y, BufferedImage[] storedImages) {
        super(storedImages , x , y );
        
        this.setAnimationTimer(new Timer(100));
        
    }
    
}
