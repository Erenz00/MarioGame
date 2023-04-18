
package Bricks;

import Animations.LifeAnim;
import Animations.StarAnim;
import Objects.Star;
import SandBox.Mario;
import com.golden.gamedev.object.Sprite;
import java.awt.image.BufferedImage;


public class BrickWithStar  extends Sprite implements BasicBrick{

    private String InsideItem = "Star";

    Mario game ;
    
    public BrickWithStar(int x, int y , BufferedImage b , Mario g) {
    
        this.setLocation(x, y);
        this.setImage(b);
        
        this.setID(11113);
        
        game = g ;
    }

        

    @Override
    public void HitFromDown() {
        
        game.BrickGroup.add(new Iron( (int)this.getX(), (int)this.getY() ,game.bsLoader.getStoredImages("Iron") , game));

        game.VolitileGroup.add(new StarAnim( (int)this.getX() , (int)this.getY() , game.bsLoader.getStoredImages("Star")[0] , game));

//        game.EnemyGroup.add(new Star( (int)this.getX() , (int)this.getY() , game.bsLoader.getStoredImages("Star") , game));
        
        RemoveIt(); // remove this brick
    }

    @Override
    public void RemoveIt() {
        this.setActive(false);
    }

    @Override
    public String getInsideItem() {
        return InsideItem;
    }

    @Override
    public boolean isJump() {
        return false ;
    }
    
}