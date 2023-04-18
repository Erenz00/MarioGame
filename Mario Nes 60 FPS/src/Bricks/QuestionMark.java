package Bricks;

import Animations.CoinAnim;
import Animations.FlowerAnim;
import Animations.MashroomAnim;
import Gears.Codes;
import SandBox.Mario;
import com.golden.gamedev.object.Timer;
import com.golden.gamedev.object.sprite.AdvanceSprite;
import java.awt.image.BufferedImage;

public class QuestionMark extends AdvanceSprite implements BasicBrick {

    private String InsideItem = "CoinInside";
    Mario game;

    public QuestionMark(int x, int y, Mario g) {

        this.setLocation(x, y);

        game = g;

        // System.out.println(game.GetAttribute());
        if ("UnderGround".equals(game.GetAttribute()) || "Castle".equals(game.GetAttribute())) { // darkBackground
            setImages(game.bsLoader.getStoredImages("QuestionMarkGrey"));

        } else {
            setImages(game.bsLoader.getStoredImages("QuestionMark"));
        }

        this.setAnimate(true);
        this.setLoopAnim(true);
        int[] a = {0, 0, 1, 2, 1, 0};
        this.setAnimationFrame(a);
        this.setAnimationTimer(new Timer(200));

//        // System.out.println(ImageUtil.getImage(this.getClass().getResource("Images\\QuestionMark.png")).getWidth());

        this.setID(1);


    }

    public QuestionMark(int x, int y, BufferedImage[] storedImages, String item, Mario g) {
        this(x, y, g);
        InsideItem = item;
    }

    @Override
    public void RemoveIt() {
        this.setActive(false);
    }

    @Override
    public String getInsideItem() {

        return InsideItem;

    }

    public void setInsideItem(String insideitem) {

        InsideItem = insideitem;

    }

    @Override
    public void HitFromDown() {

//        int x = (int) brick.getX() , y = (int) brick.getY();


        game.BrickGroup.add(new Iron((int) this.getX(), (int) this.getY(), game.bsLoader.getStoredImages("Iron"), "red", game));

        this.setActive(false);

        switch (Codes.GetCode(this.getInsideItem())) {

            case 1: // if big mario 2 or 3 ... then come out flower..else u\mushroom ...but will grow level

                if (game.player.getID() == 1 || game.player.getID() == 4) { // small mario .... come out mushroom
                    game.VolitileGroup.add(new MashroomAnim((int) this.getX(), (int) this.getY(), game.bsLoader.getStoredImage("Mashroom"), game));
                } else if (game.player.getID() == 2 || game.player.getID() == 3) {   // if big or filr mario .. come out flower
                    game.VolitileGroup.add(new FlowerAnim((int) this.getX(), (int) this.getY(), game.bsLoader.getStoredImages("Flower"), game));
                }else { // if non of these then mushroom
                    game.VolitileGroup.add(new MashroomAnim((int) this.getX(), (int) this.getY(), game.bsLoader.getStoredImage("Mashroom"), game));
                }

                break;
            case 2: // CoinInside
                game.AnimationGroup.add(new CoinAnim((int) this.getX(), (int) this.getY(), game.bsLoader.getStoredImages("CoinAnim"), game));
                break;
        }


    }

    @Override
    public boolean isJump() {
        return false;
    }
}
