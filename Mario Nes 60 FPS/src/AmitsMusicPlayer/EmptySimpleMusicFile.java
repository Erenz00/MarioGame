/*
 *  
 */
package AmitsMusicPlayer;

import javax.sound.sampled.LineEvent;

/**
 *
 * @author Amit
 */
public class EmptySimpleMusicFile extends SimpleMusicFile implements AudioFilesInterface {

    public EmptySimpleMusicFile(String AudioFileName) {
        super(AudioFileName);
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
