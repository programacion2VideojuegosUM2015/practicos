package com.mygdx.test.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.test.Clase;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = Clase.SCREEN_WIDTH;
		config.height = Clase.SCREEN_HEIGHT;
		//config.fullscreen = false;
		//config.resizable = false;
		//config.vSyncEnabled = true;
		new LwjglApplication(new Clase(), config);
	}
}
