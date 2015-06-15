package com.ucoz.prodandromeda.bomberman.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.ucoz.prodandromeda.bomberman.Bomb;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = Bomb.SCREEN_WIDTH*3;
		config.height = Bomb.SCREEN_HEIGHT*3;
		config.fullscreen = false;
		config.vSyncEnabled = true;
		config.resizable = false;
		new LwjglApplication(new Bomb(), config);
	}
}
