package SandBox;

import AmitsMusicPlayer.AmitsAudioPlayer;
import CheckPoint.CheckPoints;
import com.golden.gamedev.GameEngine;
import com.golden.gamedev.GameLoader;
import com.golden.gamedev.GameObject;
import com.golden.gamedev.funbox.GameSettings;
import java.awt.Dimension;
import java.awt.Point;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

// 640×480, 800×600, 960×720, 1024×768, 1280×960, 1400×1050, 1440×1080, 1600×1200, 1856×1392, 1920×1440, and 2048×1536
public class WholeGame extends GameEngine {

    private static boolean fullscreen;
    private static boolean bufferStrategy;
    private static boolean sound;
    private static boolean Skip;
//    private static int resolution = 0; // 640 x 480
    private static Dimension dimension;
    private static int game_width;
    private static int game_height;

    private static boolean DoWantAudio;

    @Override
    public void initResources() {

//        boolean isAudioSupportedByTheComputer = CheckifAudioSupported();

        amitsAudioPlayer = new AmitsAudioPlayer(DoWantAudio);

        amitsAudioPlayer.LoadAudioFIles();

        LoadImages();
        nextGameID = new CheckPoints(3, 11, 10, new Point(3, 11));
//        nextGameID = new CheckPoints(3, 11, 10, new Point(3, 11));
        currentGameID = new CheckPoints(0, 0, 0, new Point(0, 0));

    }

    @Override
    public GameObject getGame(int GameID) {

//        System.gc();
        // System.out.println(GameID);
        return new Mario(dimension, this, nextGameID);

    }

    public static void main(String args[]) throws FileNotFoundException, IOException {

        File SETTINGS = new File("Setting.txt");
        if (SETTINGS.exists()) {
            FileInputStream fin = new FileInputStream(SETTINGS);
            DataInputStream in = new DataInputStream(fin);

            try {
                fullscreen = in.readBoolean();
                bufferStrategy = in.readBoolean();
                sound = in.readBoolean();
                Skip = in.readBoolean();
                game_width = in.readInt();
                game_height = in.readInt();
                in.close();
//            SETTINGS.delete();

            } catch (java.io.EOFException e) {
                fullscreen = false;
                bufferStrategy = false;
                sound = false;
                Skip = false;
                game_width = 640;
                game_height = 480;

            }

            dimension = new Dimension(game_width, game_height);

        } else {
            dimension = new Dimension(640, 480);
        }

        if (Skip && SETTINGS.exists()) {

            GameSettings settings = new GameSettings(fullscreen, bufferStrategy, sound) {

                @Override
                public void start() {

                    dimension = new Dimension(Integer.parseInt(Game_Width.getText()), Integer.parseInt(Game_Height.getText()));

                    // here goes the usual game initialization
                    GameLoader game = new GameLoader();
                    game.setup(new WholeGame() {

                        @Override
                        protected void initEngine() {
                            super.initEngine();
                            // set active sound base on user setting
                            /* TODO turn on and off music / sound */
//                            bsSound.setActive(sound.isSelected());
//                            bsMusic.setActive(sound.isSelected());
                            DoWantAudio = sound.isSelected();

                        }
                    }, dimension, fullscreen.isSelected(), bufferstrategy.isSelected());
                    game.start();
                }
            };
        } else {

            GameSettings settings = new GameSettings() {

                @Override
                public void start() {

                    dimension = new Dimension(Integer.parseInt(Game_Width.getText()), Integer.parseInt(Game_Height.getText()));

                    // here goes the usual game initialization
                    GameLoader game = new GameLoader();
                    game.setup(new WholeGame() {

                        @Override
                        protected void initEngine() {
                            super.initEngine();
                            /* TODO turn on and off music / sound */
                            // set active sound base on user setting
//                            bsSound.setActive(sound.isSelected());
//                            bsMusic.setActive(sound.isSelected());
                            DoWantAudio = sound.isSelected();
                        }
                    }, dimension, fullscreen.isSelected(), bufferstrategy.isSelected());
                    game.start();
                }
            };
        }

    }

    private void LoadImages() {

        System.out.println("loading images");
        this.bsLoader.storeImages("Iron", this.getImages("Iron.png", 4, 1));
        this.bsLoader.storeImages("QuestionMark", this.getImages("QuestionMark.png", 3, 1));
        this.bsLoader.storeImages("QuestionMarkGrey", this.getImages("QuestionMarkGrey.png", 3, 1));

        this.bsLoader.storeImage("Mashroom", this.getImage("Mashroom.png"));
        this.bsLoader.storeImages("Mashrooms", this.getImages("Mashrooms.png", 2, 1));

        this.bsLoader.storeImage("chocolate_Sea", this.getImage("chocolate_Sea.png"));
        this.bsLoader.storeImage("chocolate", this.getImage("chocolate.png"));
        this.bsLoader.storeImage("chocolate_UnderGround", this.getImage("chocolate_UnderGround.png"));
        this.bsLoader.storeImage("chocolate_Castle", this.getImage("chocolate_Castle.png"));

        this.bsLoader.storeImages("CoinAnim", this.getImages("CoinAnim.png", 4, 1));

        this.bsLoader.storeImages("BrickPeaces", this.getImages("BrickPeaces.png", 2, 4));

        this.bsLoader.storeImages("Flower", this.getImages("Flower.png", 4, 1));

        this.bsLoader.storeImages("SmallToBigStarMaroAnim", this.getImages("SmallToBigStarMaroAnim.png", 12, 1));

        this.bsLoader.storeImages("SmallToBigMarioAnim", this.getImages("SmallToBigMarioAnim.png", 12, 1));

        this.bsLoader.storeImages("BigToFireMarioAnim", this.getImages("BigToFireMarioAnim.png", 10, 1));

        this.bsLoader.storeImages("BigBlackMario", this.getImages("BigBlackMario.png", 4, 7));
        this.bsLoader.storeImages("BigPlayer", this.getImages("BigPlayer.png", 4, 7));
        this.bsLoader.storeImages("BigGreenMario", this.getImages("BigGreenMario.png", 4, 7));
        this.bsLoader.storeImages("BigRedMario", this.getImages("BigRedMario.png", 4, 7));

        this.bsLoader.storeImages("SmallBlackMario", this.getImages("SmallBlackMario.png", 4, 7));
        this.bsLoader.storeImages("player", this.getImages("player.png", 4, 7));
        this.bsLoader.storeImages("SmallGreenMario", this.getImages("SmallGreenMario.png", 4, 7));
        this.bsLoader.storeImages("SmallRedMario", this.getImages("SmallRedMario.png", 4, 7));

        this.bsLoader.storeImages("FirePlayer", this.getImages("FirePlayer.png", 4, 7));

        this.bsLoader.storeImages("enemy", this.getImages("enemy.png", 2, 4));

        this.bsLoader.storeImages("turtle", this.getImages("turtle.png", 4, 1));
        this.bsLoader.storeImages("turtledark", this.getImages("turtledark.png", 4, 1));

        this.bsLoader.storeImage("brick_Sea", this.getImage("brick_Sea.png"));
        this.bsLoader.storeImage("brick", this.getImage("brick.png"));
        this.bsLoader.storeImage("brick_UnderGround", this.getImage("brick_UnderGround.png"));
        this.bsLoader.storeImage("brick_Castle", this.getImage("brick_Castle.png"));

        this.bsLoader.storeImage("stone_Sea", this.getImage("stone_Sea.png"));
        this.bsLoader.storeImage("stone", this.getImage("stone.png"));
        this.bsLoader.storeImage("stone_UnderGround", this.getImage("stone_UnderGround.png"));
        this.bsLoader.storeImage("stone_Castle", this.getImage("stone_Castle.png"));
        this.bsLoader.storeImage("stone_Clowd", this.getImage("stone_Clowd.png"));

//            this.bsLoader.storeImage("big mountain" ,  this.getImage("big mountain.png") );
//            
//            this.bsLoader.storeImage("small mountain" ,  this.getImage("small mountain.png") );
//            
//            this.bsLoader.storeImage("left grass" ,  this.getImage("left grass.png") );
//            
//            this.bsLoader.storeImage("right grass" ,  this.getImage("right grass.png") );
//            
//            this.bsLoader.storeImage("middle grass" ,  this.getImage("middle grass.png") );
//            
//            this.bsLoader.storeImage("left sky" ,  this.getImage("left sky.png") );
//            this.bsLoader.storeImage("right sky" ,  this.getImage("right sky.png") );
//            
//            this.bsLoader.storeImage("middle sky" ,  this.getImage("middle sky.png") );
        this.bsLoader.storeImage("pump top", this.getImage("pump top.png"));
        this.bsLoader.storeImage("pump Castle", this.getImage("pump Castle.png"));
        this.bsLoader.storeImage("pump Sea", this.getImage("pump Sea.png"));

        this.bsLoader.storeImage("pump", this.getImage("pump.png"));
        this.bsLoader.storeImage("pump top Castle", this.getImage("pump top Castle.png"));
        this.bsLoader.storeImage("pump top Sea", this.getImage("pump top Sea.png"));

        this.bsLoader.storeImage("OrangePump", this.getImage("OrangePump.png"));
        this.bsLoader.storeImage("OrangeTopPump", this.getImage("OrangeTopPump.png"));

        this.bsLoader.storeImage("TurtelShell", this.getImage("TurtelShell.png"));
        this.bsLoader.storeImage("TurtelShelldark", this.getImage("TurtelShelldark.png"));
        this.bsLoader.storeImage("TurtelShellRed", this.getImage("TurtelShellRed.png"));

        this.bsLoader.storeImages("BigToSmallMarioAnim", this.getImages("BigToSmallMarioAnim.png", 10, 1));

        this.bsLoader.storeImages("FireToSmallMarioAnim", this.getImages("FireToSmallMarioAnim.png", 10, 1));

        this.bsLoader.storeImage("SmallDeadMario", this.getImage("SmallDeadMario.png"));

        this.bsLoader.storeImages("FireBall", this.getImages("FireBall.png", 4, 1));

        this.bsLoader.storeImages("Explosion", this.getImages("Explosion.png", 3, 1));

        this.bsLoader.storeImage("SmallCastle", this.getImage("SmallCastle.png"));

        this.bsLoader.storeImage("BigCastle", this.getImage("BigCastle.png"));

        this.bsLoader.storeImages("tree", this.getImages("tree.png", 5, 2));

        this.bsLoader.storeImages("Wall", this.getImages("Wall.png", 2, 1));

        this.bsLoader.storeImages("RocketLauncher", this.getImages("RocketLauncher.png", 1, 4));

        this.bsLoader.storeImages("FlyingTurtle", this.getImages("FlyingTurtle.png", 4, 1));
        this.bsLoader.storeImages("FlyingTurtledark", this.getImages("FlyingTurtledark.png", 4, 1));

        this.bsLoader.storeImages("plant", this.getImages("plant.png", 2, 1));
        this.bsLoader.storeImages("plantdark", this.getImages("plantdark.png", 2, 1));

        this.bsLoader.storeImages("Monkey", this.getImages("Monkey.png", 3, 2));

        this.bsLoader.storeImages("Hammer", this.getImages("Hammer.png", 4, 1));

        this.bsLoader.storeImages("1UP", this.getImages("1UP.png", 2, 1));

        this.bsLoader.storeImages("Helmet", this.getImages("Helmet.png", 4, 1));
        this.bsLoader.storeImages("Helmetdark", this.getImages("Helmetdark.png", 4, 1));
        this.bsLoader.storeImages("Helmetwhite", this.getImages("Helmetwhite.png", 4, 1));

        this.bsLoader.storeImage("HelmetShell", this.getImage("HelmetShell.png"));
        this.bsLoader.storeImage("HelmetShelldark", this.getImage("HelmetShelldark.png"));
        this.bsLoader.storeImage("HelmetShellwhite", this.getImage("HelmetShellwhite.png"));

        this.bsLoader.storeImages("SonOfABuitch", this.getImages("SonOfABuitch.png", 2, 1));

        this.bsLoader.storeImages("SpikeyEgg", this.getImages("SpikeyEgg.png", 2, 1));

        this.bsLoader.storeImages("Spikey", this.getImages("Spikey.png", 4, 1));

        this.bsLoader.storeImage("Bouncer", this.getImage("Bouncer.png"));

        this.bsLoader.storeImages("Spring", this.getImages("Spring.png", 3, 1));

        this.bsLoader.storeImage("Flag", this.getImage("Flag.png"));
        this.bsLoader.storeImage("FlagFence", this.getImage("FlagFence.png"));

        this.bsLoader.storeImage("SlidingFlagDownAnim", this.getImage("SlidingFlagDownAnim.png"));

        this.bsLoader.storeImages("Axe", this.getImages("Axe.png", 4, 1));

        this.bsLoader.storeImages("WhyYouDOThis", this.getImages("WhyYouDOThis.png", 2, 1));

        this.bsLoader.storeImage("AnotherCastleMessage", this.getImage("AnotherCastleMessage.png"));

        this.bsLoader.storeImages("Boss", this.getImages("Boss.png", 3, 2));

        this.bsLoader.storeImages("BossFire", this.getImages("BossFire.png", 2, 1));

        this.bsLoader.storeImage("BridgeBloks", this.getImage("BridgeBloks.png"));

        this.bsLoader.storeImages("Star", this.getImages("Star.png", 4, 1));

        this.bsLoader.storeImages("Coin", this.getImages("Coin.png", 3, 1));

        this.bsLoader.storeImage("Lava", this.getImage("Lava.png"));

        this.bsLoader.storeImage("TurtelShellFilp", this.getImage("TurtelShellFilp.png"));
        this.bsLoader.storeImage("TurtelShellFilpdark", this.getImage("TurtelShellFilpdark.png"));
        this.bsLoader.storeImage("TurtelShellFilpRed", this.getImage("TurtelShellFilpRed.png"));

        this.bsLoader.storeImages("HoriImage", this.getImages("HoriImage.png", 2, 1));

        this.bsLoader.storeImage("Lift", this.getImage("Lift.png"));

        this.bsLoader.storeImages("EnemyTurtlePatrol", this.getImages("EnemyTurtlePatrol.png", 4, 1));

        this.bsLoader.storeImages("FlyingTurtlePatrol", this.getImages("FlyingTurtlePatrol.png", 4, 1));

        this.bsLoader.storeImage("FlagTop", this.getImage("FlagTop.png"));

        this.bsLoader.storeImage("FlagSphere", this.getImage("FlagSphere.png"));
        this.bsLoader.storeImage("FlagSphereFence", this.getImage("FlagSphereFence.png"));

        this.bsLoader.storeImages("FishGrey", this.getImages("FishGrey.png", 2, 1));
        this.bsLoader.storeImages("FishRed", this.getImages("FishRed.png", 2, 1));

        this.bsLoader.storeImages("OctoPussy", this.getImages("OctoPussy.png", 2, 1));

        this.bsLoader.storeImages("Bubble", this.getImages("Bubble.png", 4, 1));

        this.bsLoader.storeImage("WoodenBridge", this.getImage("WoodenBridge.png"));

        this.bsLoader.storeImage("Rope", this.getImage("Rope.png"));
        this.bsLoader.storeImage("RopeFence", this.getImage("RopeFence.png"));

        this.bsLoader.storeImages("LavaBall", this.getImages("LavaBall.png", 2, 1));

        this.bsLoader.storeImage("WhiteLine", this.getImage("WhiteLine.png"));

        this.bsLoader.storeImage("Water", this.getImage("Water.png"));

        this.bsLoader.storeImages("Chain", this.getImages("Chain.png", 4, 1));

        this.bsLoader.storeImage("stone_Castle_Sea", this.getImage("stone_Castle_Sea.png"));

        this.bsLoader.storeImage("Start", this.getImage("Start.png"));

        this.bsLoader.storeImage("bend", this.getImage("bend.png"));

        this.bsLoader.storeImage("Princess", this.getImage("Princess.png"));
        this.bsLoader.storeImage("clowd_checkpoint", this.getImage("clowd_checkpoint.png"));

        this.bsLoader.storeImage("QuestComplete", this.getImage("QuestComplete.png"));

        this.bsLoader.storeImage("FlagWin", this.getImage("FlagWin.png"));

        this.bsLoader.storeImage("numbers432", this.getImage("numbers432.png"));

        this.bsLoader.storeImage("numbers876", this.getImage("numbers876.png"));

        //------- black and white images 
        this.bsLoader.storeImage("BWBigCastle", this.getImage("CloudsNight/BigCastle.png"));
        this.bsLoader.storeImages("BWRocketLauncher", this.getImages("CloudsNight/RocketLauncher.png", 1, 4));
        this.bsLoader.storeImage("BWBouncer", this.getImage("CloudsNight/Bouncer.png"));
        this.bsLoader.storeImage("BWstone", this.getImage("CloudsNight/stone.png"));
        this.bsLoader.storeImages("BWtree", this.getImages("CloudsNight/tree.png", 5, 2));
        this.bsLoader.storeImage("BWSmallCastle", this.getImage("CloudsNight/SmallCastle.png"));
        this.bsLoader.storeImages("BWHammer", this.getImages("CloudsNight/Hammer.png", 4, 1));

        //---- Font
        this.bsLoader.storeImages("Font", this.getImages("Font.png", 16, 3));

        this.bsLoader.storeImage("Info", this.getImage("Info.png"));
        this.bsLoader.storeImage("Clowd", this.getImage("Clowd.png"));

    }


}
