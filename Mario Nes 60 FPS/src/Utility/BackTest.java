package Utility;


import com.golden.gamedev.Game;
import com.golden.gamedev.GameLoader;
import com.golden.gamedev.object.Sprite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class BackTest  extends Game{
    
    
//    ImageBackground color_background ;

//    PlayField  playfield ;
    
//    Sprite Fireball ;
    Sprite Black ;
    
//    int CenterX = 320, CenterY = 240;
    
    int Distance = 12345 ;
    Font font = new Font("Tahoma" , 1 ,14);
        
    
    @Override
    public void initResources() {
        

//        configurator.startCalibrating();
//        color_background = new ImageBackground(this.getImage("background.png"),700, 480);
//        color_background.setClip(0, 0, 640, 480);
        BufferedImage image = this.getImage("background.png");
//        BufferedImage bw = this.getImage("background.png");
//        
//        Graphics2D srcG = image.createGraphics();
//        RenderingHints rhs = srcG.getRenderingHints();
//        srcG.setRenderingHints(rhs);
//        
//        ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_GRAY);
//        ColorConvertOp theOp = new ColorConvertOp(cs, rhs);
//        
//        theOp.filter(image, bw);
        Black = new Sprite(image,0, 0);
        
//        Fireball = new Sprite(this.getImages("FireBall.png" , 4 , 1)[0]);
//        playfield = new PlayField(color_background);
        
    }

    
    @Override
    public void update(long elapsedTime) {
//        color_background.move(1, 0);
        
        Black.update(elapsedTime);
        Distance++;
        
//        Fireball.update(elapsedTime);
        
//        Distance = Distance+ 0.01;
//        if(Distance >= Math.PI*2){
//            Distance = 0 ;
//        }
//        Fireball.setX((Math.sin(Distance)*150)+CenterX);
//        Fireball.setY((Math.cos(Distance)*150)+CenterY);
    }

    
    @Override
    public void render(Graphics2D g) {
//        color_background.render(g);
//        Fireball.render(g);
        g.setColor(Color.blue);
        g.fillRect(0, 0, 640, 480);
        Black.render(g);
        g.setColor(Color.white);
        g.setFont(font);
//        g.drawLine(320, 0,320, 480 );
//        g.drawLine(0, 240 ,640, 240   );
        g.drawString("Distance "+ Distance, 50, 50);
    }
      
    public static void main(String args[]){
        
        GameLoader GL = new GameLoader();
        GL.setup(new BackTest(), new Dimension(640, 480), false);
        GL.start();
        
    }

}
