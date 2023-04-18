
package Bricks;

import Objects.Rocket;
import SandBox.Mario;
import com.golden.gamedev.object.Sprite;
import java.awt.image.BufferedImage;

public class RocketLauncher extends Sprite implements BasicBrick{

    int ShootDelay ;
    
    Mario game ;
    
    public RocketLauncher(BufferedImage bufferedImage, int x, int y , Mario M) {
        super(bufferedImage , x , y );
       
        ShootDelay = com.golden.gamedev.util.Utility.getRandom(1, 3)*50;
        game = M ;
        
    }
    

    @Override
    public void update(long l){
        
        
        ShootDelay-- ; 
        
        if(ShootDelay <= 0 ){
            if(game.player.getX() > this.getX()-100 & game.player.getX() < this.getX()+100){
            
            }else{
                ShootandUpDateDelay();
            }
        }
                
    }

    private void ShootandUpDateDelay() {
        
        if(game.player.getX() < this.getX()){ // player to left of RocketLauncher
            game.EnemyGroup.add(new Rocket(this.getX() , this.getY() , "gotoleft" , game.bsLoader.getStoredImages("RocketLauncher")[3], game ) );
        }
        else{
            game.EnemyGroup.add(new Rocket(this.getX() , this.getY() , "gotoright" , game.bsLoader.getStoredImages("RocketLauncher")[3], game ) );
        }
        
        ShootDelay = com.golden.gamedev.util.Utility.getRandom(2, 5)*200;
        
        // System.out.println("Shoot");
    }

    @Override
    public void HitFromDown() {
    }

    @Override
    public void RemoveIt() {
    }

    @Override
    public String getInsideItem() {
        return "empty";
    }

    @Override
    public boolean isJump() {
        return false ;
    }
    
            
}
