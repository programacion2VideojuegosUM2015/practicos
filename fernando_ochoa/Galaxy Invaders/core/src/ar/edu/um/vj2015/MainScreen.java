package ar.edu.um.vj2015;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MainScreen extends AbstractScreen {
	private Texture wallPaper2;
	private SpriteBatch batch;
	private float escala;

	public MainScreen(Galaxy galaxy) {
		super(galaxy);
		
	
	}
	
	public void show(){
		batch = new SpriteBatch();
		wallPaper2 = new Texture(Gdx.files.internal("fondomain.png"));	
		
		
	
	}
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT );
		batch.begin();
		batch.draw(wallPaper2, 0, 0, wallPaper2.getWidth()/escala, wallPaper2.getHeight()/escala);
		batch.end();
		if (Gdx.input.isKeyPressed(Keys.ENTER)) {
			Screens.game.setScreen(Screens.gameScreen);
			dispose();
		}
		if (Gdx.input.isKeyPressed(Keys.ESCAPE)) {
			dispose();
			System.exit(0);
		}
		
	}
		
	
	@Override
	public void resize(int width, int height) {
		float widthImage =  wallPaper2.getWidth();
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
		escala = width/widthImage;
	}
}
