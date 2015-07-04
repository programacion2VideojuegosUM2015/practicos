package com.mygdx.nikoo.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.nikoo.Balloon;


public class DesktopLauncher {
 public static void main (String[] arg) {
  LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
  config.title = "Ballon Fight";
  config.width = Balloon.SCREEN_WIDTH*3;
  config.height = Balloon.SCREEN_HEIGHT*3;
  config.fullscreen = false;
  config.vSyncEnabled = true;
  config.resizable = false;
  new LwjglApplication(new Balloon(), config);
 }
}