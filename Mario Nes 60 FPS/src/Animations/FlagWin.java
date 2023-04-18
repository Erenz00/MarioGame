/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Animations;

import SandBox.Mario;
import com.golden.gamedev.object.Sprite;

/**
 *
 * @author Aryan555
 */
public class FlagWin extends Sprite {

    Mario game ;
    
    public FlagWin(double x, Mario g) {
        game = g ;
        this.setImage(game.bsLoader.getStoredImage("FlagWin"));
        this.setLocation(x-24, 9*32);
    }

    @Override
    public void update(long elapsedTime) {
        if(this.getY() > 7*32){
        this.moveY(-1);
        }
        super.update(elapsedTime);
    }
    
}
