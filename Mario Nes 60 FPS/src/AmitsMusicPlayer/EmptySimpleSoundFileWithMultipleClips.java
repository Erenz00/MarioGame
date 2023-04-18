/*
 *  
 */
package AmitsMusicPlayer;

import javax.sound.sampled.LineEvent;

/**
 *
 * @author Amit
 */
public class EmptySimpleSoundFileWithMultipleClips extends SimpleSoundFileWithMultipleClips implements AudioFilesInterface {

    public EmptySimpleSoundFileWithMultipleClips(String AudioFileName, int number_of_clips) {
        super(AudioFileName, number_of_clips);
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
