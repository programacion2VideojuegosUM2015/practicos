package ar.edu.um.vj2015.arcanoid.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import ar.edu.um.vj2015.arcanoid.Arcanoid;


public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Arcanoid";
		config.height = 700;
		config.width = 400;
		new LwjglApplication(new Arcanoid(), config);
	}
}
