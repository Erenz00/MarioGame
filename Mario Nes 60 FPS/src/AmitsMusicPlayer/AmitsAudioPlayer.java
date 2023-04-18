/*
 *  
 */
package AmitsMusicPlayer;

import java.io.IOException;
import java.net.URL;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author Amit
 */
public class AmitsAudioPlayer {

    public SimpleSoundFile smb_bowserfalls;
    public SimpleSoundFileWithMultipleClips smb_coin;
    public SimpleSoundFile smb_fireworks;
    public SimpleSoundFile smb_powerup_appears;
    public SimpleSoundFileWithMultipleClips smb_kick;
    public SimpleSoundFile smb_bump;
    public SimpleSoundFile smb_stage_clear;
    public SimpleSoundFile smb_pipe;
    public SimpleSoundFile smb_1_up;
    public SimpleSoundFile smb_flagpole;
//    public SimpleAudioFile smb_bowserfavlls;
    public SimpleSoundFile smb_bowserfire;
    public SimpleSoundFile smb_jump_small;
    public SimpleSoundFile smb_jump_super;
    public SimpleSoundFileWithMultipleClips smb_stomp;
    public SimpleSoundFile smb_powerup;
    public SimpleSoundFile smb_mariodie;
    public SimpleSoundFileWithMultipleClips smb_fireball;
    public SimpleSoundFile smb_pause;
    public SimpleSoundFileWithMultipleClips smb_breakblock;
    public SimpleSoundFile smb_world_clear;

    /*these are music means they will be repeated continuesly till a stop comment is send to them*/
    public SimpleMusicFile Castle;
    public SimpleMusicFile Ground;
    public SimpleMusicFile Sea;
    public SimpleMusicFile Star;
    public SimpleMusicFile UnderGround;
    private boolean DoWantAudio;
    private boolean isAudioSupported;

    public AmitsAudioPlayer(boolean doWantAudio) {
        DoWantAudio = doWantAudio;
    }

    public void LoadAudioFIles() {

        isAudioSupported = CheckifAudioSupported();

        if (!isAudioSupported) {
            DoWantAudio = false;
        }

        if (DoWantAudio) {

            smb_bowserfalls = new SimpleSoundFile("smb_bowserfalls.wav");
            smb_coin = new SimpleSoundFileWithMultipleClips("smb_coin.wav", 10);
            smb_fireworks = new SimpleSoundFile("smb_fireworks.wav");
            smb_powerup_appears = new SimpleSoundFile("smb_powerup_appears.wav");
            smb_kick = new SimpleSoundFileWithMultipleClips("smb_kick.wav", 5);
            smb_bump = new SimpleSoundFile("smb_bump.wav");
            smb_stage_clear = new SimpleSoundFile("smb_stage_clear.wav");
            smb_pipe = new SimpleSoundFile("smb_pipe.wav");
            smb_1_up = new SimpleSoundFile("smb_1-up.wav");
            smb_flagpole = new SimpleSoundFile("smb_flagpole.wav");
//        smb_bowserfavlls = new SimpleAudioFile("smb_bowserfavlls.wav");
            smb_bowserfire = new SimpleSoundFile("smb_bowserfire.wav");
            smb_jump_small = new SimpleSoundFile("smb_jump-small.wav");
            smb_jump_super = new SimpleSoundFile("smb_jump-super.wav");
            smb_stomp = new SimpleSoundFileWithMultipleClips("smb_stomp.wav", 5);
            smb_powerup = new SimpleSoundFile("smb_powerup.wav");
            smb_mariodie = new SimpleSoundFile("smb_mariodie.wav");
            smb_fireball = new SimpleSoundFileWithMultipleClips("smb_fireball.wav", 2);
            smb_pause = new SimpleSoundFile("smb_pause.wav");
            smb_breakblock = new SimpleSoundFileWithMultipleClips("smb_breakblock.wav", 5);
            smb_world_clear = new SimpleSoundFile("smb_world_clear.wav");

            Castle = new SimpleMusicFile("Castle.wav");
            Ground = new SimpleMusicFile("Ground.wav");
            Sea = new SimpleMusicFile("Sea.wav");
            Star = new SimpleMusicFile("Star.wav");
            UnderGround = new SimpleMusicFile("UnderGround.wav");
            /* TODO convert these to .wav files */
//            star = new SimpleAudioFile("star.wav");
            smb_bowserfalls.intitalize();
            smb_coin.intitalize();
            smb_fireworks.intitalize();
            smb_powerup_appears.intitalize();
            smb_kick.intitalize();
            smb_bump.intitalize();
            smb_stage_clear.intitalize();
            smb_pipe.intitalize();
            smb_1_up.intitalize();
            smb_flagpole.intitalize();
//            smb_bowserfavlls.intitalize();
            smb_bowserfire.intitalize();
            smb_jump_small.intitalize();
            smb_jump_super.intitalize();
            smb_stomp.intitalize();
            smb_powerup.intitalize();
            smb_mariodie.intitalize();
            smb_fireball.intitalize();
            smb_pause.intitalize();
            smb_breakblock.intitalize();
            smb_world_clear.intitalize();

            Castle.intitalize();
            Ground.intitalize();
            Sea.intitalize();
            Star.intitalize();
            UnderGround.intitalize();

        } else {

            smb_bowserfalls = new EmptySimpleSoundFile("smb_bowserfalls.wav");
            smb_coin = new EmptySimpleSoundFileWithMultipleClips("smb_coin.wav", 10);
            smb_fireworks = new EmptySimpleSoundFile("smb_fireworks.wav");
            smb_powerup_appears = new EmptySimpleSoundFile("smb_powerup_appears.wav");
            smb_kick = new EmptySimpleSoundFileWithMultipleClips("smb_kick.wav", 5);
            smb_bump = new EmptySimpleSoundFile("smb_bump.wav");
            smb_stage_clear = new EmptySimpleSoundFile("smb_stage_clear.wav");
            smb_pipe = new EmptySimpleSoundFile("smb_pipe.wav");
            smb_1_up = new EmptySimpleSoundFile("smb_1-up.wav");
            smb_flagpole = new EmptySimpleSoundFile("smb_flagpole.wav");
//        smb_bowserfavlls = new EmptySimpleAudioFile("smb_bowserfavlls.wav");
            smb_bowserfire = new EmptySimpleSoundFile("smb_bowserfire.wav");
            smb_jump_small = new EmptySimpleSoundFile("smb_jump-small.wav");
            smb_jump_super = new EmptySimpleSoundFile("smb_jump-super.wav");
            smb_stomp = new EmptySimpleSoundFileWithMultipleClips("smb_stomp.wav", 5);
            smb_powerup = new EmptySimpleSoundFile("smb_powerup.wav");
            smb_mariodie = new EmptySimpleSoundFile("smb_mariodie.wav");
            smb_fireball = new EmptySimpleSoundFileWithMultipleClips("smb_fireball.wav", 2);
            smb_pause = new EmptySimpleSoundFile("smb_pause.wav");
            smb_breakblock = new EmptySimpleSoundFileWithMultipleClips("smb_breakblock.wav", 5);
            smb_world_clear = new EmptySimpleSoundFile("smb_world_clear.wav");

            Castle = new EmptySimpleMusicFile("Castle.wav");
            Ground = new EmptySimpleMusicFile("Ground.wav");
            Sea = new EmptySimpleMusicFile("Sea.wav");
            Star = new EmptySimpleMusicFile("Star.wav");
            UnderGround = new EmptySimpleMusicFile("UnderGround.wav");

        }
    }

    public void stopAllMusic() {

//        if (DoWantAudio) {
        Castle.stop();
        Ground.stop();
        Sea.stop();
        Star.stop();
        UnderGround.stop();
//        }
    }

    public void playMusic(String attribute) {

//        if (DoWantAudio) {
//        stopAllMusic();
        if (attribute == "Castle") {
            Castle.play();

            Ground.stop();
            Sea.stop();
            Star.stop();
            UnderGround.stop();

        } else if (attribute == "Ground") {
            Ground.play();

            Castle.stop();
            Sea.stop();
            Star.stop();
            UnderGround.stop();

        } else if (attribute == "Sea") {
            Sea.play();

            Castle.stop();
            Ground.stop();
            Star.stop();
            UnderGround.stop();

        } else if (attribute == "Star") {
            Star.play();

            Castle.stop();
            Ground.stop();
            Sea.stop();
            UnderGround.stop();

        } else if (attribute == "UnderGround") {
            UnderGround.play();

            Castle.stop();
            Ground.stop();
            Sea.stop();
            Star.stop();
        }

    }

    private boolean CheckifAudioSupported() {
        try {
            URL audioFile;

            AudioInputStream audioStream = null;

            AudioFormat format;

            DataLine.Info info;

            Clip audioClip;

            audioFile = this.getClass().getResource("smb_coin.wav");

            audioStream = AudioSystem.getAudioInputStream(audioFile);

            format = audioStream.getFormat();

            info = new DataLine.Info(Clip.class, format);

            audioClip = (Clip) AudioSystem.getLine(info);

        } catch (LineUnavailableException ex) {
            System.err.println("audio not supported");
            return false;
        } catch (UnsupportedAudioFileException ex) {
            System.err.println("audio not supported");
            return false;
        } catch (IOException ex) {
            System.err.println("audio not supported");
            return false;
        } catch (IllegalArgumentException ex) {
            System.err.println("audio not supported");
            return false;
        }

        System.out.println("audio is supported");

        return true;
    }
    /**
     * Gets a list of all audio output devices in the system
     */
//    public static List<Mixer> getAvailableAudioOutputDevices() {
//        final ArrayList<Mixer> available = new ArrayList<>();
//        final Mixer.Info[] devices = AudioSystem.getMixerInfo();
//        final Line.Info sourceInfo = new Line.Info(SourceDataLine.class);
//        for (int i = 0; i < devices.length; ++i) {
//            final Mixer.Info mixerInfo = devices[i];
//            final Mixer mixer = AudioSystem.getMixer(mixerInfo);
//            if (mixer.isLineSupported(sourceInfo)) {
//                // the device supports output, add as suitable
//                available.add(mixer);
//                System.out.println(mixer);
//            }
//        }
//        return available;
//    }
}
