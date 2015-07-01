package ar.edu.um.vj2015;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class LoserScreen extends AbstractScreen {
	private Texture wallpaper3;	
	private OrthographicCamera camera;
	

	public LoserScreen(Galaxy galaxy) {
		super(galaxy);
		camera = new OrthographicCamera();
		camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
	}
	public void show(){
		wallpaper3 = new Texture(Gdx.files.internal("fondoperdedor.png"));
		batch = new SpriteBatch();		
		resources = new SoundResources();
		
	}
	public void render(float delta){
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT );	
		camera.update();
		batch.setProjectionMatrix(camera.combined);		
		batch.begin();
		batch.draw(wallpaper3, 0, 0, wallpaper3.getWidth(), wallpaper3.getHeight());
		
		batch.end();
		
		if(Gdx.input.isTouched()){
			
			Screens.game.setScreen(Screens.mainScreen);
		}
		
	}

	@Override
	public void resize(int width, int height) {
		
	}
	
	@Override
	public void dispose(){
		batch.dispose();
		wallpaper3.dispose();
	}

}
