
package Lifts;

import SandBox.Mario;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.util.ImageUtil;


public class Lift_UpDown extends Sprite implements BasicLift{

    boolean MarioOnLift = false ;

    Mario game ;
    private double DistanceFromCenter = 5*32;
    private double CenterY ;
    
    public Lift_UpDown( int x, int y, Mario g) {
        game = g ;
        this.setImage(ImageUtil.TileImage(game.bsLoader.getStoredImage("Lift"), 6 ));
        this.setLocation(x, y);
        CenterY = y;
    }

    public Lift_UpDown( int x, int y, Mario g , int Points) {
        game = g ;
        this.setImage(ImageUtil.TileImage(game.bsLoader.getStoredImage("Lift"), Points ));
        this.setLocation(x, y);
        CenterY = y;
    }
   
    @Override
    public void update(long elapsedTime) {
        
        int zx = (int)(Math.cos(game.SlowDistance )*DistanceFromCenter) ;
//        int zx = (int)(Math.cos(game.Distance )*DistanceFromCenter) ;
        this.setY(zx+CenterY);
        
//        if(AntiClockWise){
//        this.setX((Math.sin(game.Distance)*DistanceFromCenter)+CenterX);
//        this.setY((Math.cos(game.Distance)*DistanceFromCenter)+CenterY);
//        }
//        else{
//        this.setX((Math.sin(game.InvertDistance)*DistanceFromCenter)+CenterX);
//        this.setY((Math.cos(game.InvertDistance)*DistanceFromCenter)+CenterY);
//        }
//        
//        if(this.getY() > 400){
//            this.setY(100);
//        }
        
        if(MarioOnLift){
            game.player.moveY(4);
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
