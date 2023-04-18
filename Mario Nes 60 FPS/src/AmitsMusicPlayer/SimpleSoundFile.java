/*
 *  
 */
package AmitsMusicPlayer;

import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * clips wont be stopped while playing when play() comment is send to this class
 *
 * clip will only play when it the clip is not playing or is not isRunning() (if
 * its stopped)
 *
 * @author Amit
 */
public class SimpleSoundFile implements LineListener ,  AudioFilesInterface  {

    protected final String AudioFileName;

    private URL audioFile;

    protected AudioInputStream audioStream;

    private AudioFormat format;

    protected DataLine.Info info;

    private Clip audioClip;


    public SimpleSoundFile(String AudioFileName) {
        this.AudioFileName = AudioFileName;

//        
//        audioClip.open(audioStream);
//        audioClip.start();
    }

    /**
     * Load the clip here into memory
     */
    @Override
    public void intitalize() {

        System.out.println("Loading Sound file \"" + AudioFileName + "\"");

        try {

            audioFile = this.getClass().getResource(AudioFileName);

            audioStream = AudioSystem.getAudioInputStream(audioFile);

            format = audioStream.getFormat();

            info = new DataLine.Info(Clip.class, format);

            audioClip = (Clip) AudioSystem.getLine(info);

//            audioClip.loop(Clip.LOOP_CONTINUOUSLY);
//        audioClip.addLineListener(this);
            audioClip.open(audioStream);

        } catch (LineUnavailableException ex) {
            Logger.getLogger(SimpleMusicFile.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("sound file not found \"" + AudioFileName + "\"");
        } catch (UnsupportedAudioFileException ex) {
            Logger.getLogger(SimpleMusicFile.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("sound file not found \"" + AudioFileName + "\"");
        } catch (IOException ex) {
            Logger.getLogger(SimpleMusicFile.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("sound file not found \"" + AudioFileName + "\"");

        }
    }

    @Override
    public void update(LineEvent event) {
        if (LineEvent.Type.START == event.getType()) {
            // System.out.println("Playback started.");
        } else if (LineEvent.Type.STOP == event.getType()) {
            audioClip.setFramePosition(0);
            // System.out.println("Playback completed.");

        }
    }

    /**
     * plays the clip only if its not playing means if its not isRunning()
     */
    @Override
    public void play() {

        if (!audioClip.isRunning()) {
            audioClip.setFramePosition(0);
            audioClip.start();

        }

    }

//    boolean isFinishedPlaying() {
//
//        if (audioClip.isRunning()) {
//            return false;
//        } else {
//            return true;
//        }
//    }

    @Override
    public void stop() {
    }
}
