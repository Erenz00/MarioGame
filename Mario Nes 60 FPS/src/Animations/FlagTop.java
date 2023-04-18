
package Animations;

import SandBox.Mario;
import com.golden.gamedev.object.Sprite;
import java.awt.image.BufferedImage;

public class FlagTop  extends Sprite {
    private boolean ScrollDown = false;

    public void ScrollDown() {
        ScrollDown = true ;
    }

    
    int delay  = 100 ; // slide for some amount of time
    
    Mario game ;
    private boolean IsDown;
    
    public FlagTop(BufferedImage image ,double x, double y, Mario g ) {
        
        game = g ;
        this.setImage(image);
        this.setLocation(x, y);  
        
        this.setID(-1);
        

    }
  
    @Override
    public void update(long l){

   if(ScrollDown){
            if(this.getY() > 11*32 ){ // on chocolate
                this.setY(11*32);    // stay on chocolate dont move down
                
                IsDown = true ;     // wait for delay time
                
//                this.setActive(false);
//                game.Restart();
            }
            
            if(!IsDown){ // if done delay and is down
                this.moveY(3); 
            }
   }
            super.update(l);
            
//            // System.out.println(this.getX() +"  "+ this.getY());
   
    }
    
}
