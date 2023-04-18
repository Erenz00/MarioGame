
package Teleport;

import SandBox.Mario;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.util.ImageUtil;


public class SpriteTeleport extends Sprite {

    public Teleport teleport ;

    Mario game ;
    
    public SpriteTeleport(Teleport tele, Mario aThis) {
        game = aThis ;
        teleport = tele ;
        
        this.setImage(ImageUtil.createImage(32, 32*3 , 3));
        
        this.setLocation(teleport.getINx()+32, teleport.INy);
        
    }
    
}
