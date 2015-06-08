package ar.edu.um.vj2015.arcanoid.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import ar.edu.um.vj2015.arcanoid.Arcanoid;
import ar.edu.um.vj2015.arcanoid.Constantes;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Arcanoid";
		config.height = Constantes.PANTALLA_ALTURA;
		config.width = Constantes.PANTALLA_ANCHURA;
		new LwjglApplication(new Arcanoid(), config);
	}
}
