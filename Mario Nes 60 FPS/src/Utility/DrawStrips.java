
package Utility;

import com.golden.gamedev.util.ImageUtil;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;


public class DrawStrips {
    
    BufferedImage back , backScaled;
    
    BufferedImage Resxedback ;
    
            public static void main(String args[]) throws FileNotFoundException, IOException{

                DrawStrips drawstrips = new DrawStrips();
                
                drawstrips.Draw();
                
            }
            
    private void Draw() throws MalformedURLException{
        
        int numbers = 1 ;
        
        String FileName = "mario-6-4.gif";
                
        File f = new File("E:\\Mario\\New 60 FPS Mario no GamePad\\Mario\\"+FileName);
        
        back = ImageUtil.getImage(f.toURI().toURL());
        
        backScaled = ImageUtil.resize(back, back.getWidth()*2, back.getHeight()*2);
                
        Resxedback = ImageUtil.createImage((back.getWidth()*2)+100, 480);
                
        Font font = new Font("Tahoma" , 1 ,14);
        
        Graphics2D g = (Graphics2D) Resxedback.getGraphics();
        
        g.drawImage(backScaled, null, 0, 16);
        g.setColor(Color.red);
        
        if(numbers == 1){
        for(int y = 0 ; y <= backScaled.getWidth()/32 ; y++){
        g.drawLine( 0 , y*32, backScaled.getWidth() , y*32 );
        }
//        
        for(int y = 0 ; y <= Resxedback.getWidth()/32 ; y++){
        g.drawLine(y*32, 0, y*32, Resxedback.getHeight());
        }
        
        g.setFont(font);
        
        for(int X_Pos = 0 ; X_Pos <= Resxedback.getWidth()/32 ; X_Pos++){
          
            for(int Y_Pos = 0 ; Y_Pos <= Resxedback.getHeight()/32 ; Y_Pos++){
            g.setColor(Color.green.darker());
                g.drawString(X_Pos+"", (X_Pos*32)+ 5, (Y_Pos*32)+ 11);
//                g.drawString(X_Pos+"", (X_Pos*32)+ 1, (Y_Pos*32)+ 9);
//                g.drawString(X_Pos+"", (X_Pos*32)+ 1, (Y_Pos*32)+ 11);
//                g.drawString(X_Pos+"", (X_Pos*32)+ 4, (Y_Pos*32)+ 9);
            g.setColor(Color.green);
                g.drawString(X_Pos+"", (X_Pos*32)+ 4, (Y_Pos*32)+ 10);
//                g.drawString(X_Pos+"", (X_Pos*32)+ 3, (Y_Pos*32)+ 10);
                 
                
           g.setColor(Color.white.darker());
                g.drawString(Y_Pos+"", (X_Pos*32)+ 5, (Y_Pos*32)+ 25);
//                g.drawString(Y_Pos+"", (X_Pos*32)+ 1, (Y_Pos*32)+ 23);
//                g.drawString(Y_Pos+"", (X_Pos*32)+ 1, (Y_Pos*32)+ 25);
//                g.drawString(Y_Pos+"", (X_Pos*32)+ 4, (Y_Pos*32)+ 23);
            g.setColor(Color.white);
                g.drawString(Y_Pos+"", (X_Pos*32)+ 4, (Y_Pos*32)+ 24);
//                g.drawString(Y_Pos+"", (X_Pos*32)+ 3, (Y_Pos*32)+ 24);
                
        }
            
        }
        }
        g.dispose();
        
        ImageUtil.saveImage(Resxedback, new File(FileName));
    }
    
}
