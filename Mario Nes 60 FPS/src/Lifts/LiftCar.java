
package Lifts;

import SandBox.Mario;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.util.ImageUtil;

public class LiftCar extends Sprite implements BasicLift{

    boolean MarioOnLift = false ;

    Mario game ;
    
    boolean isTouched = false ;

    public LiftCar( int x, int y, Mario g) {
        game = g ;
        this.setImage(ImageUtil.TileImage(game.bsLoader.getStoredImage("Lift"), 6 ));
        this.setLocation(x, y);
    }
    
    public LiftCar( int x, int y, Mario g , int Points) {
        game = g ;
        
        this.setImage(ImageUtil.TileImage(game.bsLoader.getStoredImage("Lift"), Points ));
        this.setLocation(x+24, y);
    }
    

   
    @Override
    public void update(long elapsedTime) {
                
//        // System.out.println("this.getOldX() ="+this.getOldX() +" , x = "+this.getX());

        if(this.getX()>(32*100)){
            this.setActive(false);
        }
        if(MarioOnLift){
            isTouched = true ;
            
//            this.moveX(1);
            game.player.moveX(2);
//            game.player.moveX(this.getOldX() - this.getX() );
            MarioOnLift = false ;
        }
        
        if(isTouched){
            this.moveX(2);
        }
//        this.moveX(1);
//        
//        if(this.getX() > 400){
//            this.setX(100);
//        }
//        
//        if(MarioOnLift){
//            game.player.moveX(1);
//            MarioOnLift = false ;
//        }
        super.update(elapsedTime);
    }
    @Override
    public boolean OnLift() {
        return MarioOnLift ;
    }
    
    @Override
    public void MarioIsOnLift() {
        MarioOnLift  = true;
    }

    
}