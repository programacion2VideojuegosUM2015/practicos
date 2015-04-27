package com.prodandromeda.newbucket;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
 
public class MainMenuScreen implements Screen {
 
  final Drop game;
  Texture PantallaPrincipal;
  Music selectMusic;
  public static int numeroJugadores = 1;
 
	OrthographicCamera camera;
 
	public MainMenuScreen(final Drop gam) {
		game = gam;
 
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);
		
		PantallaPrincipal = new Texture(Gdx.files.internal("MainScreen.png"));
		selectMusic = Gdx.audio.newMusic(Gdx.files.internal("select.mp3"));
		selectMusic.setLooping(true);
		
 
	}
 
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
 
		camera.update();
		game.batch.setProjectionMatrix(camera.combined);
 
		game.batch.begin();
		
		game.batch.draw(PantallaPrincipal, 0, 0);
		
		game.batch.end();
		
		if (Gdx.input.isKeyPressed(Keys.NUMPAD_2) || Gdx.input.isKeyPressed(Keys.NUM_2) ) {
			numeroJugadores = 2;
			game.setScreen(new GameScreen(game));
			dispose();
		}
		
		if (Gdx.input.isKeyPressed(Keys.NUMPAD_1) || Gdx.input.isKeyPressed(Keys.NUM_1) ) {
			numeroJugadores = 1;
			game.setScreen(new GameScreen(game));
			dispose();
		}
 
		if (Gdx.input.isTouched()) {
			game.setScreen(new GameScreen(game));
			dispose();
		}
	}
 
	@Override
	public void resize(int width, int height) {
	}
 
	@Override
	public void show() {
		selectMusic.play();
	}
 
	@Override
	public void hide() {
	}
 
	@Override
	public void pause() {
	}
 
	@Override
	public void resume() {
	}
 
	@Override
	public void dispose() {
		
	PantallaPrincipal.dispose();
	selectMusic.dispose();

	}
	
	
	
	public int getNumeroJugadores() {
		return numeroJugadores;
	}
	
	
	
	
}