package ar.edu.um.vj2015;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class MainScreen extends AbstractScreen {
	private Texture wallPaper2;
	private Texture buttonTexture;	
	private Button play;
	private Button exit;	
	private SoundResources resources;
	private OrthographicCamera camera;

	public MainScreen(Galaxy galaxy) {
		super(galaxy);
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 450);
		resources = new SoundResources();
		resources.getMainSong().play();
		resources.getMainSong().setLooping(true);
		
	}
	
	public void show(){
		batch = new SpriteBatch();
		wallPaper2 = new Texture(Gdx.files.internal("fondomain2.png"));
		buttonTexture =new Texture(Gdx.files.internal("botonsalir.png"));
		int centerX = 800/2 - buttonTexture.getWidth()/2;
		int centerY = 450/2 - buttonTexture.getHeight()/2;
		play = new PlayButton(centerX -150, centerY -150);
		exit = new ExitButton(centerX +150, centerY -150);
		
		
		
	
	}
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT );		
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		batch.draw(wallPaper2, 0, 0, wallPaper2.getWidth(), wallPaper2.getHeight());
		play.draw(batch);
		exit.draw(batch);
		batch.end();		
		play.update();
		exit.update();
		
		/*if(isTouched())
			resources.getMainSong().stop();*/
			
		
	
		
	}
		
	
	@Override
	public void resize(int width, int height) {
		
	}
	
	public boolean isTouched(){
		if(Gdx.input.isTouched())
			return true;
		else 
		return false;	
		
	}
	
	@Override
	public void dispose(){
		wallPaper2.dispose();
		buttonTexture.dispose();
		resources.getMainSong().dispose();
		batch.dispose();
	}

	
	
}

