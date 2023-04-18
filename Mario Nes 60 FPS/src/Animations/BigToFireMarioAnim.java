package Animations;

import SandBox.Mario;
import com.golden.gamedev.object.Sprite;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class BigToFireMarioAnim extends Sprite {

    Mario game;

    boolean Right = true;

    int delay = 60; // time to play
    int Fps = 2; // prame play per second

    // 4 buffered images each with 12 images
    BufferedImage[] BigBlackMario = new BufferedImage[4];
    BufferedImage[] FirePlayer = new BufferedImage[4];
    BufferedImage[] BigGreenMario = new BufferedImage[4];
    BufferedImage[] BigRedMario = new BufferedImage[4];

    private int currentFrame = 1;

    int MariosCurrentFrame;

    public BigToFireMarioAnim(double x, double y, Mario g) {

        setLocation(x, y);

        game = g;

        BigBlackMario = game.bsLoader.getStoredImages("BigBlackMario");
        FirePlayer = game.bsLoader.getStoredImages("FirePlayer");
        BigGreenMario = game.bsLoader.getStoredImages("BigGreenMario");
        BigRedMario = game.bsLoader.getStoredImages("BigRedMario");

        MariosCurrentFrame = game.player.getFrame();

    }

    @Override
    public void update(long l) {

        Fps--;
        if (Fps < 0) {
            nextFrame();
            Fps = 2;
        }

        delay--;
        if (delay < 0) {

            game.PlayEnemys();
            game.getPlayer().setImages(game.bsLoader.getStoredImages("FirePlayer"));

            this.setActive(false);

        }

        super.update(l);

//        // System.out.println("SmallToBigMarioAnim " + this.getFrame());
    }

    @Override
    public void render(Graphics2D g) {

        if (currentFrame == 1) {
            this.setImage(BigBlackMario[MariosCurrentFrame]);
//, null, (int)this.getX(),  (int)this.getY());
        } else if (currentFrame == 2) {
            this.setImage(FirePlayer[MariosCurrentFrame]);
//            g.drawImage(FirePlayer[MariosCurrentFrame], null, (int)this.getX(),  (int)this.getY());
        } else if (currentFrame == 3) {
            this.setImage(BigGreenMario[MariosCurrentFrame]);
//            g.drawImage(BigGreenMario[MariosCurrentFrame], null, (int)this.getX(),  (int)this.getY());
        } else if (currentFrame == 4) {
            this.setImage(BigRedMario[MariosCurrentFrame]);
//            g.drawImage(BigRedMario[MariosCurrentFrame], null, (int)this.getX(),  (int)this.getY());
        }

//        null);
        super.render(g);
    }

    private void nextFrame() {

        if (currentFrame == 1) {
            currentFrame = 2;
        } else if (currentFrame == 2) {
            currentFrame = 3;
        } else if (currentFrame == 3) {
            currentFrame = 4;
        } else if (currentFrame == 4) {
            currentFrame = 1;
        }

    }

}
