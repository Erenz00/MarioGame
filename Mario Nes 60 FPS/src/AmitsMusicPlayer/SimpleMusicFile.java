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
public class SimpleMusicFile implements LineListener, AudioFilesInterface {

    protected final String AudioFileName;
    private URL audioFile;
    protected AudioInputStream audioStream;
    private AudioFormat format;
    protected DataLine.Info info;
    private Clip audioClip;
    private boolean repeatMusic = true;

    public SimpleMusicFile(String AudioFileName) {
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
        try {
            System.out.println("Loading Sound file \"" + AudioFileName + "\"");

            audioFile = this.getClass().getResource(AudioFileName);

            audioStream = AudioSystem.getAudioInputStream(audioFile);

            format = audioStream.getFormat();

            info = new DataLine.Info(Clip.class, format);

            audioClip = (Clip) AudioSystem.getLine(info);

            audioClip.loop(Clip.LOOP_CONTINUOUSLY);
            audioClip.addLineListener(this);
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

            if (repeatMusic) {
                audioClip.setFramePosition(0);
                audioClip.start();
            }
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
            repeatMusic = true;
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
        repeatMusic = false;
        audioClip.stop();
    }
}
