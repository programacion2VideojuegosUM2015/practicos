package ar.edu.um.vj2015;


import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class AbstractScreen implements Screen {
	protected Galaxy galaxy;
	protected OrthographicCamera camera;
	protected SpriteBatch batch;
	protected SoundResources resources;
	

	
	public AbstractScreen(Galaxy galaxy) {
		
		this.galaxy = galaxy;
		
		
	}

	@Override
	public void show() {
		
		
		
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resize(int width, int height) {
		
		
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
		// TODO Auto-generated method stub
		
	}

}
