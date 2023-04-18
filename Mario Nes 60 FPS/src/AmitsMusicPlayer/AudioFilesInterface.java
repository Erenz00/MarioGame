/*
 *  
 */
package AmitsMusicPlayer;

import javax.sound.sampled.LineEvent;

/**
 *
 * @author Amit
 */
public interface AudioFilesInterface {

    public void intitalize();

    public void update(LineEvent event);

    public void play();

    void stop();
}
