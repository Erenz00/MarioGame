
package Animations;

import SandBox.Mario;
import com.golden.gamedev.object.AnimatedSprite;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.Timer;
import com.golden.gamedev.util.ImageUtil;
import java.awt.image.BufferedImage;

public class MarioSlidingDown extends AnimatedSprite {

    
    int delay  = 100 ; // slide for some amount of time
    
    Mario game ;
    private boolean IsDown = false;
    private boolean DelayDone = false;
    
    public MarioSlidingDown(double x, double y, Mario g ) {
        
        game = g ;
        this.setImages(game.player.getImages());
        this.setLocation(x, y);
        setAnimationTimer(new Timer(200));
        setAnimate(true);
        setLoopAnim(true);
        this.setAnimationFrame(12 , 13);        
        
        

    }
  
    @Override
    public void update(long l){

        
        if(delay > 0 ){
            delay-- ;
            }else{
            DelayDone = true ;
        }
            
       
        if(!IsDown){
            this.moveY(3);   // if not down the move down
        }
//            
            
            if(this.getY() > (12*32)-this.getHeight() ){ // on chocolate
                this.setY((12*32)-this.getHeight());    // stay on chocolate dont move down
                
                IsDown = true ;     // wait for delay time
                this.setAnimate(false);
//                this.setActive(false);
//                game.Restart();
            }
            
            if(DelayDone & IsDown){ // if done delay and is down
                game.player.setLocation(this.getX()-12, this.getY()); // next to animation
                game.player.NoControlling();
                game.player.MoveForward();
                
                game.PlayEnemys();
                this.setActive(false);
            }
            super.update(l);
            
            
            
    }
    
}
