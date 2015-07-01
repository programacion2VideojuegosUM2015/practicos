package ar.edu.um.vj2015;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameScreen extends AbstractScreen {

	private Texture wallPaper;	
	private Spaceship spaceship;
	private Monsters monsters;
	private Bullets bullets;
	private Player player;
	private OrthographicCamera camera;
	

	

	public GameScreen(Galaxy galaxy) {
		super(galaxy);	
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
		
	}

	@Override
	public void show(){
		batch = new SpriteBatch();
		wallPaper = new Texture(Gdx.files.internal("fondo.jpeg"));	
		spaceship = new Spaceship();
		monsters = new Monsters();
		bullets= new Bullets();
		player = new Player();
		/*resources = new SoundResources();		
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
		monsters.detectCollision(bullets, player);
		batch.begin();
		batch.draw(wallPaper, 0, 0, wallPaper.getWidth(), wallPaper.getHeight());
		monsters.draw(batch);
		spaceship.draw(batch);
		bullets.draw(batch);
		player.draw(batch);
		batch.end();
		/*if(player.getLives() == 0)			
				resources.getGameSong().stop();*/
		
	}
	@Override
	public void resize(int width, int height) {
		
	}
	
	
	@Override
	public void dispose(){
		batch.dispose();
		wallPaper.dispose();
		resources.getGameSong().dispose();
		batch.dispose();
		
	}
}
