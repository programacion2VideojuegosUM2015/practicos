package ar.edu.um.vj2015;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameScreen extends AbstractScreen {
	private SpriteBatch batch;
	private Texture wallPaper;
	private float escala;
	private Spaceship spaceship;
	private Monsters monsters;

	public GameScreen(Galaxy galaxy) {
		super(galaxy);		
	}

	@Override
	public void show(){
		batch = new SpriteBatch();
		wallPaper = new Texture(Gdx.files.internal("fondo.jpeg"));	
		spaceship = new Spaceship(400,10);
		monsters = new Monsters();
	}
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT );
		spaceship.updateMovement();
		monsters.MonsterSpawn();
		batch.begin();
		batch.draw(wallPaper, 0, 0, wallPaper.getWidth()/escala, wallPaper.getHeight()/escala);
		spaceship.draw(batch);
		monsters.draw(batch);
		batch.end();
	}
	@Override
	public void resize(int width, int height) {
		float widthImage =  wallPaper.getWidth();
		float heigthImage = wallPaper.getHeight();
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