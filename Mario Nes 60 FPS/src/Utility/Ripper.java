package Utility;

import com.golden.gamedev.util.ImageUtil;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;

public class Ripper {

    public static final void main(String[] args) {

        Ripper r = new Ripper();

        r.Start();
//        
//        r.GetColor();

//        r.Test();
    }

    private void Start() {

        BufferedImage map = ImageUtil.getImage(this.getClass().getResource("mario-8-44.gif"));

        int color1[][];

        color1 = GetImage("stone_Castle.png");

        for (int i = 0; i < map.getWidth() / 32; i++) {
            for (int j = 0; j < map.getHeight() / 32; j++) {
//                try {
//                    Thread.sleep(10);
//                } catch (InterruptedException ex) {
//                    ex.printStackTrace();
//                }
                BufferedImage image2 = map.getSubimage(i * 32, j * 32, 32, 32);

                int color2[][] = new int[32][32];
                for (int x = 0; x < 32; x++) {
                    for (int y = 0; y < 32; y++) {
                        color2[x][y] = image2.getRGB(y, x);
                    }

                }

                int percentage = 0;

                for (int x = 0; x < 32; x++) {
                    for (int y = 0; y < 32; y++) {
                        if (color1[x][y] == color2[x][y]) {
                            percentage++;
                        }
                    }

                }

                if (percentage > 200) {
                    System.out.println("this.AddStone( " + i + " , " + j + "  );");
                }

            }
        }
    }

    private void GetColor() {

        BufferedImage image = ImageUtil.getImage(this.getClass().getResource("mario-3-1.gif"));

        for (int i = 0; i < 15; i++) {


            Color c = new Color(image.getRGB(2, i * 32));
            // System.out.println(c.getRed() +", "+ c.getGreen() +", " +c.getBlue());

        }
//                // System.out.println(c.getRGB());
    }

    private void Test() {

        BufferedImage image1 = ImageUtil.getImage(this.getClass().getResource("brickcastle.png"));

        BufferedImage image2 = ImageUtil.getImage(this.getClass().getResource("ddd.png")); // 648

        int color1[][] = new int[32][32];
        for (int x = 0; x < 32; x++) {
            for (int y = 0; y < 32; y++) {
                color1[x][y] = image1.getRGB(y, x);
            }

        }

        int color2[][] = new int[32][32];
        for (int x = 0; x < 32; x++) {
            for (int y = 0; y < 32; y++) {
                color2[x][y] = image2.getRGB(y, x);
            }

        }

        int percentage = 0;

        for (int x = 0; x < 32; x++) {
            for (int y = 0; y < 32; y++) {
                if (color1[x][y] == color2[x][y]) {
                    percentage++;
//                // System.out.println("x " + x*4 +" | y "+ y*7);
                }
            }
        }
        // System.out.println("percentage " + percentage);
//        if(color1 == color2){
//            // System.out.println("yep");
//            // System.out.println("brick "+ color1);
//        }else{
//            // System.out.println("nop");
//        }


    }

    private int[][] GetImage(String stonepng) {

        BufferedImage image1 = ImageUtil.getImage(this.getClass().getResource(stonepng));

        int color1[][] = new int[32][32];
        for (int x = 0; x < 32; x++) {
            for (int y = 0; y < 32; y++) {
                color1[x][y] = image1.getRGB(y, x);
            }

        }
        return color1;
    }
}
