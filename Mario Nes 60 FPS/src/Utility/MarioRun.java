
package Utility;

import SandBox.Mario;
import com.golden.gamedev.Game;
import com.golden.gamedev.GameLoader;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;


public class MarioRun extends Game{

    int Frame = 1 ;
    
    int something = 1 ;
    
    
    @Override
    public void initResources() {
        setFPS(30);
    }

    
    @Override
    public void update(long elapsedTime) {
        
        if(this.bsInput.isKeyDown(KeyEvent.VK_RIGHT)){
            something++ ;
        }
        
        if(this.bsInput.isKeyDown(KeyEvent.VK_LEFT)){
            
            something-- ;
        }
        
        if(something > 10){
            something = 1 ;
            AdvanceFrame();
        }
        if(something < -10){
            something = -1 ;
            DeAdvanceFrame();
        }
        
    }

    
    @Override
    public void render(Graphics2D g) {
        
        g.setColor(Color.blue);
        g.fillRect(0, 0, 640, 480);
        
        
        g.setColor(Color.white);
        g.drawString(" > "+Frame+" < ", 100, 100);
    }
    
        public static void main(String args[]){
        
        GameLoader GL = new GameLoader();
        GL.setup(new MarioRun(), new Dimension(640, 480), false);
        GL.start();
        
    }

    private void AdvanceFrame() {
        if(Frame == 1){
            Frame = 2 ;
        }else if(Frame == 2){
            Frame = 3 ;
        }else if(Frame == 3){
            Frame = 1 ;
        }
    }

    private void DeAdvanceFrame() {
        if(Frame == 1){
            Frame = 2 ;
        }else if(Frame == 2){
            Frame = 3 ;
        }else if(Frame == 3){
            Frame = 1 ;
        }
    }
}
