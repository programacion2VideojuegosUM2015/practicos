package ar.edu.um.vj2015;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class WinnerScreen extends AbstractScreen {
	private Texture wallpaper4;	
	private OrthographicCamera camera;

	public WinnerScreen(Galaxy galaxy) {
		super(galaxy);
		camera = new OrthographicCamera();
		camera.setToOrtho(false,800,450);
	}
	public void show(){
		wallpaper4 = new Texture(Gdx.files.internal("fondoganador.png"));
		batch = new SpriteBatch();
		
		
	}
	public void render(float delta){
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT );	
		camera.update();
		batch.setProjectionMatrix(camera.combined);	
		batch.begin();
		batch.draw(wallpaper4, 0, 0, wallpaper4.getWidth(), wallpaper4.getHeight());
		
		batch.end();
		
		if(Gdx.input.isTouched()){
			Screens.game.setScreen(Screens.mainScreen);
		}
		
	}

	@Override
	public void resize(int width, int height) {
		
	}
	public void dispose(){
		wallpaper4.dispose();
		batch.dispose();
	}

}
