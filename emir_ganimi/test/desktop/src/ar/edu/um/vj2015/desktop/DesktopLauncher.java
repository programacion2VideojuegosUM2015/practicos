package ar.edu.um.vj2015.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import ar.edu.um.vj2015.ClasePrincipalJuego;
import ar.edu.um.vj2015.ClasePrincipalJuego2;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.height = 389;
		config.width = 639;
		new LwjglApplication(new ClasePrincipalJuego(), config);
	}
}
