package ar.ed.um.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import ar.ed.um.Arcanoid;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Demo Arcanoid";
		config.useGL30 = false;
		config.width = 300;
		config.height = 600;
		new LwjglApplication(new Arcanoid(), config);
		
				
	}
}
