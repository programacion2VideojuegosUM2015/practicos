package ar.edu.um.vj2015.mco.bucket;

import java.util.Random;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
//import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

public class Gota {
	private Texture dropImage;
	private Texture dropImage2;
	private Texture dropImage3;
	private Texture dropImage4;
	private Sound dropSound;
	private Array<Rectangle> raindrops;
	private long lastDropTime;
	Random rand=new Random();

	public Gota () {
		raindrops = new Array<Rectangle>();
		spawnRaindrop();
	}

	//-----------------------------------------------------------------------------------------
	// MÉTODOS
	//-----------------------------------------------------------------------------------------
	public Texture getDropImage() { return dropImage; }
    public void setDropImage(Texture dropImage) { this.dropImage = dropImage; }	
	//-----------------------------------------------------------------------------------------
	public Texture getDropImage2() { return dropImage2; }
    public void setDropImage2(Texture dropImage2) { this.dropImage2 = dropImage2; }	
	//-----------------------------------------------------------------------------------------
	public Texture getDropImage3() { return dropImage3; }
    public void setDropImage3(Texture dropImage3) { this.dropImage3 = dropImage3; }	
	//-----------------------------------------------------------------------------------------
	public Texture getDropImage4() { return dropImage4; }
    public void setDropImage4(Texture dropImage4) { this.dropImage4 = dropImage4; }	
	//-----------------------------------------------------------------------------------------
	public Sound getDropSound() { return dropSound; }
    public void setDropSound(Sound dropSound) { this.dropSound = dropSound; }	
	//-----------------------------------------------------------------------------------------
    public Array<Rectangle> getRaindrops() { return raindrops; }
    public void setRaindrops(Array<Rectangle> raindrops) { this.raindrops = raindrops; }	
	//-----------------------------------------------------------------------------------------
    public long getLastDropTime() { return lastDropTime; }
    public void setLastDropTime(long lastDropTime) { this.lastDropTime = lastDropTime; }	
	//-----------------------------------------------------------------------------------------
	public void CreateGota() {
		dropImage = new Texture(Gdx.files.internal("droplet.png"));
		dropImage2 = new Texture(Gdx.files.internal("droplet2.png"));
		dropImage3 = new Texture(Gdx.files.internal("droplet3.png"));
		dropImage4 = new Texture(Gdx.files.internal("droplet4.png"));
		dropSound = Gdx.audio.newSound(Gdx.files.internal("drop.wav"));	
	}
	//-----------------------------------------------------------------------------------------
	public void spawnRaindrop() {
		Rectangle raindrop = new Rectangle();
		raindrop.x = MathUtils.random(0, 800 - 64);
		raindrop.y = 480;
		raindrop.width = 64;
		raindrop.height = 64;
		raindrops.add(raindrop);
		lastDropTime = TimeUtils.nanoTime();
	}
	//-----------------------------------------------------------------------------------------

}



