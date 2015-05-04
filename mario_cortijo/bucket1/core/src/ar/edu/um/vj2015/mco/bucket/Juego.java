package ar.edu.um.vj2015.mco.bucket;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.Iterator;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;



public class Juego extends ApplicationAdapter {
	private Music rainMusic;
	private SpriteBatch batch;
	private OrthographicCamera camera;
	private BitmapFont fuente;
	Array<Drop> arrayDrop;
	long lastDropTime;
	long tiempo;
	
	static Balde balde2 = new Balde();
	static Balde balde1 = new Balde();
	
	@Override
	public void create() {

		// load the drop sound effect and the rain background "music"
		rainMusic = Gdx.audio.newMusic(Gdx.files.internal("rain.mp3"));
		fuente = new BitmapFont();

		// start the playback of the background music immediately
		rainMusic.setLooping(true);
		rainMusic.play();

		// create the camera and the SpriteBatch
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);
		batch = new SpriteBatch();

		//CREO BALDES Y GOTAS
		getBalde().CreateBalde();
		getBalde2().CreateBalde();
		getBalde2().SetPlayer2();
		arrayDrop = new Array<Drop>();
		spawnDrop();
	}

	private void spawnDrop() {
		Drop oneDrop = new Drop();
		oneDrop.setDropX(MathUtils.random(0, 800 - 64));
		oneDrop.setDropY(480);
		oneDrop.setWidth(64);
		oneDrop.setHeight(64);
		oneDrop.setDropTipo(MathUtils.random(0,800));
		arrayDrop.add(oneDrop);	
		lastDropTime = TimeUtils.nanoTime();
	}

	
	@Override
	public void render() {
		// clear the screen with a dark blue color. The
		// arguments to glClearColor are the red, green
		// blue and alpha component in the range [0,1]
		// of the color to be used to clear the screen.
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		// tell the camera to update its matrices.
		camera.update();

		// tell the SpriteBatch to render in the
		// coordinate system specified by the camera.
		batch.setProjectionMatrix(camera.combined);

		// begin a new batch and draw the bucket and
		// all drops
		batch.begin();
		
		//DIBUJO PUNTAJES
		fuente.draw(batch, getBalde().getBucketScoreName(), 20, 470);
		fuente.draw(batch, getBalde2().getBucketScoreName(), 710, 470); 
		
		//DIBUJO BALDES
		batch.draw(getBalde().getBucketImage(), getBalde().getBucketX(), getBalde().getBucketY());
		batch.draw(getBalde2().getBucketImage2(), getBalde2().getBucketX(), getBalde2().getBucketY());	
	
		//DIBUJO GOTAS
		for (Drop oneDrop : arrayDrop) {
			if(oneDrop.getDropTipo()%100 == 0){ batch.draw(oneDrop.getDropImage4(), oneDrop.getDropX(), oneDrop.getDropY()); }
			else if(oneDrop.getDropTipo()%3 == 0) { batch.draw(oneDrop.getDropImage2(), oneDrop.getDropX(), oneDrop.getDropY()); } 	
			else if(oneDrop.getDropTipo()%2 == 0) { batch.draw(oneDrop.getDropImage3(), oneDrop.getDropX(), oneDrop.getDropY()); } 
			else { batch.draw(oneDrop.getDropImage(), oneDrop.getDropX(), oneDrop.getDropY()); }
		}
		batch.end();

		// process user input
		if (Gdx.input.isTouched()) {
			Vector3 touchPos = new Vector3();
			touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(touchPos);
			getBalde().setBucketX(touchPos.x - 64 / 2);
		}
		if (Gdx.input.isKeyPressed(Keys.LEFT))
			getBalde2().setBucketX(getBalde2().getBucketX() - 400 * Gdx.graphics.getDeltaTime());
		if (Gdx.input.isKeyPressed(Keys.RIGHT))
			getBalde2().setBucketX(getBalde2().getBucketX() + 400 * Gdx.graphics.getDeltaTime());

		// make sure the bucket stays within the screen bounds
		//PLAYER 1
		if (getBalde().getBucketX() < 0)
			getBalde().setBucketX(0);
		if (getBalde().getBucketX() > 390 - 64)
			getBalde().setBucketX(390 - 64);
		//PLAYER 2
		if (getBalde2().getBucketX() < 410)
			getBalde2().setBucketX(410);
		if (getBalde2().getBucketX() > 800 - 64)
			getBalde2().setBucketX(800 - 64);

		//CREAR GOTA NUEVA
		if ((TimeUtils.nanoTime() - lastDropTime > ((1000000000/1)-(tiempo*1000000000/100))) && (tiempo < 120))
			spawnDrop();
		Iterator<Drop> iter2 = arrayDrop.iterator();
		while (iter2.hasNext()) {
			Drop oneDrop = iter2.next();
				
			    //VELOCIDADES GOTA DEPENDIENDO DEL TIPO
				if(oneDrop.getDropTipo()%100 == 0){ oneDrop.setDropY(oneDrop.getDropY() - 300 * Gdx.graphics.getDeltaTime()); }
				else if(oneDrop.getDropTipo()%3 == 0) { oneDrop.setDropY(oneDrop.getDropY() - 220 * Gdx.graphics.getDeltaTime()); } 	
				else if(oneDrop.getDropTipo()%2 == 0) { oneDrop.setDropY(oneDrop.getDropY() - 215 * Gdx.graphics.getDeltaTime()); } 
				else { oneDrop.setDropY(oneDrop.getDropY() - 200 * Gdx.graphics.getDeltaTime()); }
				
				oneDrop.getDropHitBox().y = oneDrop.getDropY();
				oneDrop.getDropHitBox().x = oneDrop.getDropX()+((oneDrop.getWidth()/2)-(oneDrop.getDropHitBox().getWidth()/2));
			    //DESTRUYO GOTA SI PASA DE BORDE
			    if (oneDrop.getDropY() + 64 < 0){
				  iter2.remove();
		        }
			
			//COLISION P1    
			if (oneDrop.getDropHitBox().overlaps(getBalde().getBucket())) {
			   	//PUNTAJES	
				if(oneDrop.getDropTipo()%100 == 0) { getBalde().setBucketScore(getBalde().getBucketScore() + MathUtils.random(25,50)); }
				else if(oneDrop.getDropTipo()%3 == 0) { getBalde().setBucketScore(getBalde().getBucketScore() + 3); }
				else if(oneDrop.getDropTipo()%2 == 0) { getBalde().setBucketScore(getBalde().getBucketScore() + 2); }
				else { getBalde().setBucketScore(getBalde().getBucketScore() + 1); }
			    getBalde().setBucketScoreName("Puntaje: " + getBalde().getBucketScore());
				
			    oneDrop.getDropSound().play();
				iter2.remove();
			}
			//COLISION P2
			if (oneDrop.getDropHitBox().overlaps(getBalde2().getBucket())) {
			   	//PUNTAJES	
				if(oneDrop.getDropTipo()%100 == 0) { getBalde2().setBucketScore(getBalde2().getBucketScore() + MathUtils.random(25,50)); }
				else if(oneDrop.getDropTipo()%3 == 0) { getBalde2().setBucketScore(getBalde2().getBucketScore() + 3); }
				else if(oneDrop.getDropTipo()%2 == 0) { getBalde2().setBucketScore(getBalde2().getBucketScore() + 2); }
				else { getBalde2().setBucketScore(getBalde2().getBucketScore() + 1); }	
			    getBalde2().setBucketScoreName("Puntaje: " + getBalde2().getBucketScore());
				
			    oneDrop.getDropSound().play();
				iter2.remove();
			}
		}
		
	}

	@Override
	public void dispose() {
		// dispose of all the native resources
		getBalde().getBucketImage().dispose();
		getBalde().getBucketImage2().dispose();
		rainMusic.dispose();
		batch.dispose();
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	public Balde getBalde2() { return balde2; }
	public Balde getBalde() { return balde1; }
	
}