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
 * in this class we can play the same clip ( clip with same sound) more then 1
 * instance
 *
 * like of mario touchs coin and again in 10-20 frames later he touches another
 * coin
 *
 * then if the first clip is not yet finished and we need to play 2nd clip at
 * that 2nd coin touch so ,
 *
 * we gotta play another clip with coin sound
 *
 * --- i have added code where if mario touch 2 coins at same time , 2 clips are
 * played which is not good .
 *
 * so if a clip is playing and had not played at least 300 milliseconds then the
 * play() will not play any new clips (means it will ignore the given request to
 * play clip)
 *
 * @author Amit
 */
public class SimpleSoundFileWithMultipleClips implements LineListener, AudioFilesInterface {

    protected final String AudioFileName;
//    private URL audioFile;
//
//    protected AudioInputStream audioStream;
//
//    private AudioFormat format;
//
//    protected DataLine.Info info;
    private final Clip audioClip[];

    SimpleSoundFileWithMultipleClips(String AudioFileName, int number_of_clips) {
        this.AudioFileName = AudioFileName;
        audioClip = new Clip[number_of_clips];
    }

    @Override
    public void intitalize() {

        System.out.println("Loading Sound file \"" + AudioFileName + "\"");

        try {

            for (int i = 0; i < audioClip.length; i++) {

                URL audioFile = this.getClass().getResource(AudioFileName);

                AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);

                AudioFormat format = audioStream.getFormat();

                DataLine.Info info = new DataLine.Info(Clip.class, format);

                audioClip[i] = (Clip) AudioSystem.getLine(info);
                audioClip[i].open(audioStream);
            }

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
//            audioClip.setFramePosition(0);
            // System.out.println("Playback completed.");

        }
    }

    @Override
    public void play() {

        for (int i = 0; i < audioClip.length; i++) {

            /* here is the code
             we dont want 2 or more clips to play at the same time so
             this code will not make any more clips to play if any clip is 
             low then 300 frame position (means 300 millisecond i think )
             */
            if (audioClip[i].isRunning()) {

                if (audioClip[i].getFramePosition() < 300) {
//                    System.out.println("skipping playing of audioClip[" + i + "] frame getFramePosition = " + audioClip[i].getFramePosition());

                    /* this will help to completly not run the any clip */
                    break;
                }
            }

            if (!audioClip[i].isRunning()) {
                audioClip[i].setFramePosition(0);
                audioClip[i].start();

//                System.out.println("playing audioClip[" + i + "]");
//                System.out.println("playing audioClip[" + i + "] frame length = " + audioClip[i].getFrameLength());
                break;
            }
        }

    }

    @Override
    public void stop() {
    }
}
