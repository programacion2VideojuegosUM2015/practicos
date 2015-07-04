package com.mygdx.nikoo;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class TitleScreen implements Screen {
	final Balloon game;
	
	
	public Texture title;
	public Music b001;
	public BitmapFont nes;
	
	
	
	public TitleScreen(final Balloon gam) {
		this.game = gam;
		
	title = new Texture(Gdx.files.internal("title.png"));
	
	nes = new BitmapFont(Gdx.files.internal("nes.fnt"), Gdx.files.internal("nes.png"), false);
	
	b001 = Gdx.audio.newMusic(Gdx.files.internal("ost/01-balloon-trip.mp3"));
	b001.setLooping(true);
	b001.play();
		
	}	

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(float delta) {//loop
		
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		// tell the camera to update its matrices.
		Balloon.hudcam.update();
			
		game.batch.setProjectionMatrix(Balloon.hudcam.combined);
		
		game.batch.begin();
		
		game.batch.draw(title, //Textura 
				Balloon.SCREEN_WIDTH/2-title.getTextureData().getWidth()/2, //Coordenada X
				Balloon.SCREEN_HEIGHT/2 - title.getTextureData().getHeight()/2 + 35, //Coordenada Y
				title.getTextureData().getWidth(),//Ancho imagen
				title.getTextureData().getHeight());//Alto imagen
		
		
		
		
		
		if(Gdx.app.getType().equals("Android")){
			   nes.draw(game.batch, "Toca la pantalla", 65, 50);
			  }
			  else{
			   nes.draw(game.batch, "Presiona Enter", 70, 50);
			  }
		

		//dibuja todo = clase batch
		game.batch.end();
		
		if(Gdx.input.isKeyJustPressed(Keys.ENTER) || Gdx.input.isTouched() ){
			
		game.setScreen(new Juego(game));
		
		dispose();
		
		}

	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		title.dispose();
		b001.dispose();

	}

}
