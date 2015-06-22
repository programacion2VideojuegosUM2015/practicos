package ar.edu.um.vj2015;

import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class MainScreen extends AbstractScreen {
	private Texture wallPaper2;
	private Texture buttonTexture;
	private SpriteBatch batch;	
	private Button play;
	private Button exit;
	private float escala;
	private Resources resources;

	public MainScreen(Galaxy galaxy) {
		super(galaxy);
		
	}
	
	public void show(){
		batch = new SpriteBatch();
		wallPaper2 = new Texture(Gdx.files.internal("fondomain2.png"));
		buttonTexture =new Texture(Gdx.files.internal("botonsalir.png"));
		int centerX = Gdx.graphics.getWidth()/2 - buttonTexture.getWidth()/2;
		int centerY = Gdx.graphics.getHeight()/2 - buttonTexture.getHeight()/2;
		play = new PlayButton(centerX -150, centerY -150);
		exit = new ExitButton(centerX +150, centerY -150);
		resources = new Resources();		
		resources.getMainSong().play();
		resources.getMainSong().setLooping(true);

	
	
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
		
	
		
	}
		
	
	@Override
	public void resize(int width, int height) {
		/*float widthImage =  wallPaper2.getWidth();
		float heigthImage = wallPaper2.getHeight();
		float p = heigthImage/widthImage; // sacamos proporcion
		if(heigthImage > height){
			heigthImage = height;
	        widthImage = heigthImage/p;	
		}
		if(widthImage > width){
			widthImage = width;
		    heigthImage = widthImage*p;
		}
		escala = width/widthImage;	*/
	}
	
	@Override
	public void dispose(){
		wallPaper2.dispose();
		buttonTexture.dispose();
		resources.getMainSong().dispose();
	}

	
	
}

