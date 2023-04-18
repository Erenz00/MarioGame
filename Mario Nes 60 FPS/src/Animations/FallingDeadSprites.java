
package Animations;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.util.ImageUtil;
import java.awt.image.BufferedImage;

public class FallingDeadSprites extends Sprite {

    double Gravity = -10 ;

    int delay  = 0 ;
    
    boolean ToRight = true ;
    
    boolean StraightFall = false ;
    

    
    public FallingDeadSprites(double x, double y, BufferedImage image , boolean direction  ) {
        
        ToRight = direction ;
                
        this.setLocation(x, y);
        
        this.setImage(ImageUtil.flipVertical(image));

    }

    public FallingDeadSprites(double x, double y, BufferedImage storedImage, int i , String straight  ) {
        this(x , y , storedImage , true );
        
        delay = i ;
        
        Gravity = -10 ;
        
        if("StraightFalling".equals(straight)){
            StraightFall = true ;
        }
        
    }
    
    @Override
    public void update(long l){

        
        if(!StraightFall){
        if(ToRight){
        moveX(-2);
        }else {
        moveX(2); 
        }}
        
        if(delay < 0 ){
            if(Gravity < 10){
                Gravity = Gravity += 0.5;
                
            }
            
            this.moveY(Gravity);
            
        }else{
            delay-- ;
        }
//            this.moveX(XSpeed);
            
            if(this.getY() > 700 ){
                this.setActive(false);
            }
            
            super.update(l);
    }
    
}
