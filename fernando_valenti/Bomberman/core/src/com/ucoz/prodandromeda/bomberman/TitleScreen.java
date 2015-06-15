package com.ucoz.prodandromeda.bomberman;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;

public class TitleScreen implements Screen {
	final Bomb game;
	
	
	public Texture title;
	public Music b001;
	
	
	
	public TitleScreen(final Bomb gam) {
		this.game = gam;
		
	title = new Texture(Gdx.files.internal("title.png"));	
	
	b001 = Gdx.audio.newMusic(Gdx.files.internal("ost/001title.mp3"));
	b001.setLooping(false);
	b001.play();
		
	}	

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(float delta) {
		
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		// tell the camera to update its matrices.
		Bomb.hudcam.update();
			
		game.batch.setProjectionMatrix(Bomb.hudcam.combined);
		
		game.batch.begin();
		
		game.batch.draw(title, //Textura 
				Bomb.SCREEN_WIDTH/2-title.getTextureData().getWidth()/2, //Coordenada X
				Bomb.SCREEN_HEIGHT/2 - title.getTextureData().getHeight()/2 + 35, //Coordenada Y
				title.getTextureData().getWidth(),//Ancho imagen
				title.getTextureData().getHeight());//Alto imagen
		
		game.font.draw(game.batch, "Presiona Enter", 83, 50);
		
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
