
package Lifts;

import Animations.DirectFalling;
import SandBox.Mario;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.util.ImageUtil;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;


public class BalenceLiftParent extends Sprite implements BasicLift{

    boolean MarioOnLift = false ;

    Mario game ;

    BalenceLiftChild Child ;
    
    int MaxY = 3*32 ;
    int MinY = 11*32 ;
    
    double SpeedY = 0 ;
    
    boolean Break = false ;
    
    Sprite SpriteBlackBoxes ;
            
    public BalenceLiftParent(int x, int y , Mario g , int Distance) {
        
        game = g ;
        this.setImage(ImageUtil.TileImage(game.bsLoader.getStoredImage("Lift"), 6 ));
        this.setLocation(x, y);
        
        // now make another Lift
        
        Child = new BalenceLiftChild(x+(32*Distance), y , game , this);
        
        game.LiftGroup.add(Child);
        
        AddChain();
        AddBlackBoxes();
        AddHooksAndChain(Distance);
//        game.BackGroundSpriteGroup.add(new Sprite(game.bsLoader.getStoredImage("LiftChain"), this.getX()+32 , 2*32) );
    }

    @Override
    public void update(long elapsedTime) {
        
        if(!Break){
        if(this.getY() < MaxY){
            this.Break();
            this.Child.Break();
        }
        if(this.getY() > MinY){
            this.Break();
            this.Child.Break();
        }
        
        
        if(MarioOnLift){
            SpeedY += 0.2 ;
            
            if(SpeedY > 15){
                SpeedY = 15 ;
            }
            game.player.moveY(SpeedY/12);
            this.moveY(SpeedY/12);
            
            this.Child.moveY(-SpeedY/12);
//            game.player.moveX(this.getOldX() - this.getX() );
            MarioOnLift = false ;
        }else
        
        if(!MarioOnLift){
            
            if(SpeedY > 0){
                SpeedY -= 0.2 ;
            }
            this.Child.moveY(-SpeedY/12);
            this.moveY(SpeedY/12);
        }
        }
        SpriteBlackBoxes.setY(this.getY());
        super.update(elapsedTime);
    }
    @Override
        public boolean OnLift() {
        return MarioOnLift ;
    }
    
    

    @Override
    public void render(Graphics2D g) {
//        g.setColor(Color.WHITE);
//        g.drawString("SpeedY "+ SpeedY, 100, 100);
        super.render(g);
    }
    @Override
    public void MarioIsOnLift() {
        MarioOnLift  = true;
    }

    public void Break() {
        
        game.AnimationGroup.add(new DirectFalling(this.getX() , this.getY() , this.getImage()));
        game.AnimationGroup.add(new DirectFalling(this.Child.getX() , this.Child.getY() , this.Child.getImage()));
        
        
    
        this.setActive(false);
        this.Child.setActive(false);
    
    }

    private void AddChain() {
        
        BufferedImage Chain = ImageUtil.resize(game.bsLoader.getStoredImages("Chain")[0], 32, 15*32);
        
        game.BackGroundSpriteGroup.add(new Sprite(Chain, this.getX()+32 , 3*32));
        
        
    }

    private void AddBlackBoxes() {
        BufferedImage BlackBoxes ;
        
        if("Blue".equals(game.CurrentLevel.BackGroundColor)){
         BlackBoxes = ImageUtil.createImage( 32, 15*32 , game.BackGroundColor);
        }else {
         BlackBoxes = ImageUtil.createImage( 32, 15*32 );
        }
        SpriteBlackBoxes = new Sprite(BlackBoxes, this.getX()+32 , this.getY()) ;
        
        
        game.BackGroundSpriteGroup.add(SpriteBlackBoxes);
        
        
        
    }

    private void AddHooksAndChain(int Distance) {
        
        int realdis = Distance + 1 ;
        BufferedImage HooksAndChainimage ;
        
        if("Blue".equals(game.CurrentLevel.BackGroundColor)){
         HooksAndChainimage = ImageUtil.createImage( 32*realdis, 32, game.BackGroundColor);
        }else {
         HooksAndChainimage = ImageUtil.createImage( 32*realdis, 32 );
        }
        
        Graphics2D g = (Graphics2D) HooksAndChainimage.getGraphics() ;
        
        for (int i = 0; i <= realdis; i++) {
            
            if(i == 0){
                g.drawImage(game.bsLoader.getStoredImages("Chain")[1], null, i*32 , 0);
            }else if(i == realdis-1){
                g.drawImage(game.bsLoader.getStoredImages("Chain")[3], null, i*32 , 0);
            }else{
        g.drawImage(game.bsLoader.getStoredImages("Chain")[2], null, i*32 , 0);
            }
        }
        
        g.dispose();
        
        
        Sprite HooksAndChain = new Sprite(HooksAndChainimage, this.getX()+32 , 32*2) ;
        
        game.BackGroundSpriteGroup.add(HooksAndChain);
    }
    
}
