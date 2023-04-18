
package Bricks;

import SandBox.Mario;
import com.golden.gamedev.object.Sprite;
import java.awt.image.BufferedImage;

public class Flag extends Sprite {

    Mario game ;

    public Flag(int i, int i0, BufferedImage storedImage, Mario aThis) {
        super(storedImage , i , i0);
        
        game = aThis ;
        this.setID(-2);
    }
    
}
