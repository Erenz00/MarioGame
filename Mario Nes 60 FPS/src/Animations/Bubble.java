
package Animations;

import SandBox.Mario;
import com.golden.gamedev.object.sprite.AdvanceSprite;
import com.golden.gamedev.object.Timer;

public class Bubble extends AdvanceSprite{

    Mario game ;
    
    public Bubble(int x, int y, Mario M) {
        game = M ;
        setLocation(x, y);
        setImages(game.bsLoader.getStoredImages("Bubble"));
        setAnimationTimer(new Timer(100));
        setAnimate(true);
        setLoopAnim(true);
        
        int anim[] = {0 , 1, 2 , 3 , 2 , 1 , 0};
        this.setAnimationFrame(anim);
        
    }
    
       
    @Override
        public void update(long l){

            this.moveY(-1);
            if(this.getY() < 64){
                this.setActive(false);
            }
            
            super.update(l);
        }
       
}
