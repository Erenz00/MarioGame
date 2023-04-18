// Decompiled by DJ v3.12.12.96 Copyright 2011 Atanas Neshkov  Date: 9/6/2015 7:17:20 AM
// Home Page: http://members.fortunecity.com/neshkov/dj.html  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   FireBall.java
package Objects;

import SandBox.Mario;
import com.golden.gamedev.object.AnimatedSprite;
import com.golden.gamedev.object.Timer;
import java.awt.image.BufferedImage;

public class FireBall extends AnimatedSprite {

    Mario game;

    public FireBall(double x, double y, boolean MoveX, BufferedImage images[], Mario g) {
        Gravity = 8;
        XSpeed = -8;
        setLocation(x, y);
        setImages(images);
        setAnimate(true);
        setLoopAnim(true);
        setAnimationTimer(new Timer(100));
        if (MoveX) {
            XSpeed = 8;
        } else {
            XSpeed = -8;
        }
        game = g;
    }

    @Override
    public void update(long l) {
        if (Gravity < 8) {
            Gravity = Gravity + 1;
        }
        moveY(Gravity);
        moveX(XSpeed);
        if (getY() > 700D) {
            setActive(false);
        }
        super.update(l);
    }

    public void bounce() {
        Gravity = -8;
    }
    private int Gravity;
    private int XSpeed;
}
