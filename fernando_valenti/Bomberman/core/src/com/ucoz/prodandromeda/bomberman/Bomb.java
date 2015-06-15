package com.ucoz.prodandromeda.bomberman;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
 
public class Bomb extends Game {
	
	
	
	public static final int SCREEN_WIDTH = 256;
	public static final int SCREEN_HEIGHT = 240;
	
	
	public static boolean debugmode = false;
	
	public static OrthographicCamera camera;
	public static OrthographicCamera camaramundo;
	public static OrthographicCamera hudcam;
	
	
	
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
		camera.setToOrtho(false, Bomb.SCREEN_WIDTH, Bomb.SCREEN_HEIGHT);
							
		//crear la camara del HUD		
		hudcam = new OrthographicCamera();
		hudcam.setToOrtho(false, Bomb.SCREEN_WIDTH, Bomb.SCREEN_HEIGHT);
							
		//crear la camara del mundo box2d
		camaramundo = new OrthographicCamera();
		camaramundo.setToOrtho(false, Bomb.SCREEN_WIDTH/GameWorld.units, Bomb.SCREEN_HEIGHT/GameWorld.units);
		
		
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