package Collusion;

import SandBox.Mario;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.BasicCollisionGroup;

public class Player_InvisibleObjects extends BasicCollisionGroup {

    Mario M;

    public Player_InvisibleObjects(Mario aThis) {
        M = aThis;
    }

    @Override
    public void collided(Sprite player, Sprite invisibleSprite) {


        if (invisibleSprite.getDataID() == "StopScrollAndNumber") {
            M.BackGroundSpriteGroup.add(new Sprite(M.bsLoader.getStoredImage("numbers432"), (178 * 32) + 8, 8 * 32));
            M.Scroll(false);
//            System.out.println("StopScroll");
        }

        // StopScrollAndNumber2 = for clowd level 4-2
        if (invisibleSprite.getDataID() == "StopScrollAndNumber2") {
            M.BackGroundSpriteGroup.add(new Sprite(M.bsLoader.getStoredImage("numbers876"), (50 * 32) + 8, 8 * 32));
            M.Scroll(false);
//            System.out.println("StopScroll");
        }
        
        if (invisibleSprite.getDataID() == "StopScroll") {
            M.Scroll(false);
//            System.out.println("StopScroll");
        }
        if (invisibleSprite.getDataID() == "scroll") {
            M.SmoothScroll();
//            System.out.println("StopScroll");
        }

        invisibleSprite.setActive(false);
    }
}
