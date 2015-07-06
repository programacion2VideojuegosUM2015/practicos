package control;

/**
 * Created by IntelliJ IDEA.
 * User: Romina
 * Date: 20-jun-2005
 * Time: 22:12:21
 * To change this template use File | Settings | File Templates.
 */

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.net.URL;

public class SnakeSound {
    AudioClip Clip;
    URL Url;

    public SnakeSound(String fileName) {
        try {
            Url = new URL("file:" + new File(".").getCanonicalPath()
                    + "/" + fileName);
            Clip = Applet.newAudioClip(Url);

        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public void play() {
        Clip.play();
    }
    public void stop() {
        Clip.stop();
    }
    public void loop() {
        Clip.loop();
    }

}