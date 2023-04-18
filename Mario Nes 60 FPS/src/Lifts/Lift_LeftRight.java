
package Lifts;

import SandBox.Mario;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.util.ImageUtil;


public class Lift_LeftRight extends Sprite implements BasicLift{

    boolean MarioOnLift = false ;

    Mario game ;
    private double DistanceFromCenter = 2*32;
    private double CenterX ;
    
    double x1 ;
    public Lift_LeftRight( int x, int y, Mario g) {
        game = g ;
        this.setImage(ImageUtil.TileImage(game.bsLoader.getStoredImage("Lift"), 6 ));
        this.setLocation(x, y);
        CenterX = x;
    }

    public Lift_LeftRight( int x, int y, Mario g , int Points) {
        game = g ;
        this.setImage(ImageUtil.TileImage(game.bsLoader.getStoredImage("Lift"), Points ));
        this.setLocation(x, y);
        CenterX = x;
    }
   
    @Override
    public void update(long elapsedTime) {
                
//        // System.out.println("this.getOldX() ="+this.getOldX() +" , x = "+this.getX());
        
        x1 = this.getOldX() ;//- this.getX() ;
        this.setX((Math.cos(game.Distance)*DistanceFromCenter)+CenterX);
        x1 = this.getX() - x1 ;
        if(MarioOnLift){
            game.player.moveX( x1);
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
