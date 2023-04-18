
package Animations;

import SandBox.Mario;
import com.golden.gamedev.object.Timer;
import com.golden.gamedev.object.sprite.AdvanceSprite;

public class FireWorks extends AdvanceSprite {
    Mario game ; 
    
    int basex ;
    
    int AnimationTimes ;
    
    
    public FireWorks(int x, int y, Mario g) {
        game = g ;
        this.setImages(game.bsLoader.getStoredImages("Explosion"));
        AnimationTimes = com.golden.gamedev.util.Utility.getRandom(4, 7) ;
        
        int anim[] = {0 ,  0 , 1 ,  1 , 2 ,2};
        // default 120
//        game.DelayToNextCheckPoint = game.DelayToNextCheckPoint +  (AnimationTimes * 30);
        
        basex = x ;
        this.setAnimationTimer(new Timer(100));
        this.setAnimate(true);
        
        this.setAnimationFrame(anim);
        UdpatePos();
    }

    @Override
    public void update(long elapsedTime) {
        
        if(this.getFrame() == this.getFinishAnimationFrame()){
            UdpatePos();
        }
        
        super.update(elapsedTime);
        
    }
    
    public void UdpatePos() {
        int newX , newY ;
        newX = com.golden.gamedev.util.Utility.getRandom(basex-128, basex+128);
        newY = com.golden.gamedev.util.Utility.getRandom(3, 7)*32;
        
        this.setLocation(newX, newY);
        this.setFrame(0);
        this.setAnimate(true);
        
        AnimationTimes-- ;
        
        if(AnimationTimes < 0 ){
            this.setActive(false);
        }
        else{
             game.parent.amitsAudioPlayer.smb_fireworks.play();
        }
        
    }

}
