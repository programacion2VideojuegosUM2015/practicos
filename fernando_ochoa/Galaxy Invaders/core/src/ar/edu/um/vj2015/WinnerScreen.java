package ar.edu.um.vj2015;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class WinnerScreen extends AbstractScreen {
	private Texture wallpaper4;
	private Player player;

	public WinnerScreen(Galaxy galaxy) {
		super(galaxy);
		
	}
	public void show(){
		wallpaper4 = new Texture(Gdx.files.internal("fondoganador.png"));
		batch = new SpriteBatch();
		player = new Player();
		camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	}
	public void render(float delta){
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT );				
		batch.setProjectionMatrix(camera.combined);
		camera.update();
		batch.begin();
		batch.draw(wallpaper4, 0, 0, wallpaper4.getWidth(), wallpaper4.getHeight());
		player.draw2(batch);
		batch.end();
		
		if(Gdx.input.isTouched()){
			Screens.game.setScreen(Screens.mainScreen);
		}
		
	}

	@Override
	public void resize(int width, int height) {
		
	}

}
