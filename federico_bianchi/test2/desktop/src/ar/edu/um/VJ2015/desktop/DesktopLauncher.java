package ar.edu.um.VJ2015.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import ar.edu.um.VJ2015.MataCorrupto;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 400;
		config.height = 700;
		new LwjglApplication(new MataCorrupto(), config);
	}
}
