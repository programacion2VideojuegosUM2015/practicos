package com.mygdx.nikoo;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
 
public class Balloon extends Game {
	
	
	//resolucion
	public static final int SCREEN_WIDTH = 256;
	public static final int SCREEN_HEIGHT = 240;
	
	
	public static boolean debugmode = false;
	
	public static OrthographicCamera camera;//todo
	public static OrthographicCamera camaramundo;//colisiones
	public static OrthographicCamera hudcam;//puntos, etc
	
	
	//se empieza con:
	public static int puntos = 0;
	public static int nivel = 1;
	public static int vidas = 3;
	
	
	//se crea el mundo
	GameWorld mundo = new GameWorld();
	
 
	

	
  SpriteBatch batch;
  SpriteBatch bh;
  BitmapFont font;
 
	public void create() {
		
		batch = new SpriteBatch();
		font = new BitmapFont();
		bh = new SpriteBatch();
		
		//crear la camara
		camera = new OrthographicCamera();
		camera.setToOrtho(false, Balloon.SCREEN_WIDTH, Balloon.SCREEN_HEIGHT);
							
		//crear la camara del HUD		
		hudcam = new OrthographicCamera();
		hudcam.setToOrtho(false, Balloon.SCREEN_WIDTH, Balloon.SCREEN_HEIGHT);
							
		//crear la camara del mundo box2d
		camaramundo = new OrthographicCamera();
		camaramundo.setToOrtho(false, Balloon.SCREEN_WIDTH/GameWorld.units, Balloon.SCREEN_HEIGHT/GameWorld.units);
		
		//inicia la pantalla principal
		this.setScreen(new TitleScreen(this)); 
		

	}
 
	public void render() {
		super.render(); // important!
	}
 
	public void dispose() {
		batch.dispose();
		font.dispose();
	}
 
}