/*
 *  
 */
package AmitsMusicPlayer;

import javax.sound.sampled.LineEvent;

/**
 *
 * @author Amit
 */
public class EmptySimpleSoundFile extends SimpleSoundFile implements AudioFilesInterface {

    EmptySimpleSoundFile(String smb_bowserfallswav) {
        super(smb_bowserfallswav);
    }

    @Override
    public void intitalize() {

    }

    @Override
    public void update(LineEvent event) {

    }

    @Override
    public void play() {

    }

    @Override
    public void stop() {
    }
}
