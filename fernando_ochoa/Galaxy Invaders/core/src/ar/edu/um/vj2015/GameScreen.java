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
	private Bullets bullets;
	private Player player;
	
	

	

	public GameScreen(Galaxy galaxy) {
		super(galaxy);	
		
		
		
		
	}

	@Override
	public void show(){
		batch = new SpriteBatch();
		wallPaper = new Texture(Gdx.files.internal("fondo.jpeg"));	
		spaceship = new Spaceship();
		monsters = new Monsters();
		bullets= new Bullets();
		player = new Player();
		/*resources = new Resources();
		resources.getMainSong().stop();
		resources.getGameSong().play();
		resources.getGameSong().setLooping(true);*/
		
	
	}
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT );
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		spaceship.updateMovement();
		bullets.update();		
		bullets.detectCollision(player);
		bullets.detectCollision2();
		batch.begin();
		batch.draw(wallPaper, 0, 0, wallPaper.getWidth()/escala, wallPaper.getHeight()/escala);
		monsters.draw(batch);
		spaceship.draw(batch);
		bullets.draw(batch);
		player.draw(batch);
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
	
	@Override
	public void dispose(){
		batch.dispose();
		wallPaper.dispose();
		
	}
}
