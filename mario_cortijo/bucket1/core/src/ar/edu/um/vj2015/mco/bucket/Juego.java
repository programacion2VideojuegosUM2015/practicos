package ar.edu.um.vj2015.mco.bucket;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
//import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Iterator;
//import java.util.Random;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
//import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
//import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
//import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

public class Juego extends ApplicationAdapter {
	private Music rainMusic;
	private SpriteBatch batch;
	private OrthographicCamera camera;

	static Balde balde1 = new Balde();
	static Gota gota1 = new Gota(); 
	
	@Override
	public void create() {

		// load the drop sound effect and the rain background "music"
		rainMusic = Gdx.audio.newMusic(Gdx.files.internal("rain.mp3"));

		// start the playback of the background music immediately
		rainMusic.setLooping(true);
		rainMusic.play();

		// create the camera and the SpriteBatch
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);
		batch = new SpriteBatch();

		getBalde().CreateBalde();
		getGota().CreateGota();
		getGota().spawnRaindrop();
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
		batch.draw(getBalde().getBucketImage(), getBalde().getBucketX(), getBalde().getBucketY());
		for (Rectangle raindrop : getGota().getRaindrops()) {
			if(raindrop.x%2 == 0) { batch.draw(getGota().getDropImage(), raindrop.x, raindrop.y); }
			else if(raindrop.x%3 == 0) { batch.draw(getGota().getDropImage2(), raindrop.x, raindrop.y); }
			else if(raindrop.x%5 == 0) { batch.draw(getGota().getDropImage3(), raindrop.x, raindrop.y); }
			else { batch.draw(getGota().getDropImage4(), raindrop.x, raindrop.y); }
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
			getBalde().setBucketX(getBalde().getBucketX() - 200 * Gdx.graphics.getDeltaTime());
		if (Gdx.input.isKeyPressed(Keys.RIGHT))
			getBalde().setBucketX(getBalde().getBucketX() + 200 * Gdx.graphics.getDeltaTime());

		// make sure the bucket stays within the screen bounds
		if (getBalde().getBucketX() < 0)
			getBalde().setBucketX(0);
		if (getBalde().getBucketX() > 800 - 64)
			getBalde().setBucketX(800 - 64);

		// check if we need to create a new raindrop
		if (TimeUtils.nanoTime() - getGota().getLastDropTime() > 1000000000)
			getGota().spawnRaindrop();

		// move the raindrops, remove any that are beneath the bottom edge of
		// the screen or that hit the bucket. In the later case we play back
		// a sound effect as well.
		Iterator<Rectangle> iter = getGota().getRaindrops().iterator();
		while (iter.hasNext()) {
			Rectangle raindrop = iter.next();
			raindrop.y -= 200 * Gdx.graphics.getDeltaTime();
			if (raindrop.y + 64 < 0)
				iter.remove();
			if (raindrop.overlaps(getBalde().getBucket())) {
				getGota().getDropSound().play();
				iter.remove();
			}
		}
	}

	@Override
	public void dispose() {
		// dispose of all the native resources
		getGota().getDropImage().dispose();
		getBalde().getBucketImage().dispose();
		getGota().getDropSound().dispose();
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

	
	public Balde getBalde() { return balde1; }
	public Gota getGota() { return gota1; }
	
}