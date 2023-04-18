
package Lifts;

import SandBox.Mario;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.util.ImageUtil;

public class LiftDown extends Sprite implements BasicLift{

    boolean MarioOnLift = false ;

    Mario game ;

    public LiftDown( int x, int y, Mario g) {
        game = g ;
        this.setImage(ImageUtil.TileImage(game.bsLoader.getStoredImage("Lift"), 6 ));
        this.setLocation(x, y);
    }
    
    public LiftDown( int x, int y, Mario g , int Points) {
        game = g ;
        
        this.setImage(ImageUtil.TileImage(game.bsLoader.getStoredImage("Lift"), Points ));
        this.setLocation(x+24, y);
    }
    

   
    @Override
    public void update(long elapsedTime) {
                
//        // System.out.println("this.getOldX() ="+this.getOldX() +" , x = "+this.getX());
        if(this.getY() > 580){
            this.setY(-100);
        }
        
        this.moveY(1.5);
        
        if(MarioOnLift){
            game.player.moveY(4);
//            game.player.moveX(this.getOldX() - this.getX() );
            MarioOnLift = false ;
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