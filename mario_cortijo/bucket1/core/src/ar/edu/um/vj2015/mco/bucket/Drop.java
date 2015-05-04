package ar.edu.um.vj2015.mco.bucket;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Drop {
	
	private Texture dropImage;
	private Texture dropImage2;
	private Texture dropImage3;
	private Texture dropImage4;
	private Sound dropSound;
	private int dropTipo;
	private float x = 0;
	private float y = 480;
	private int width = 64;
	private int height = 64;
	
	private Rectangle dropHitbox;
	

	public Drop (){
	  dropHitbox = new Rectangle (getDropX(), getDropY(), 32, 30);
	  dropImage = new Texture(Gdx.files.internal("droplet.png"));
	  dropImage2 = new Texture(Gdx.files.internal("alien.png"));
	  dropImage3 = new Texture(Gdx.files.internal("stone.png"));
	  dropImage4 = new Texture(Gdx.files.internal("gift.png"));
	  dropSound = Gdx.audio.newSound(Gdx.files.internal("drop.wav"));	
	}
	
	public void dispose() {
		// dispose of all the native resources
		getDropImage().dispose();
		getDropImage2().dispose();
		getDropImage3().dispose();
		getDropImage4().dispose();
		getDropSound().dispose();
	}
	
	//-----------------------------------------------------------------------------------------
	// MÉTODOS
	//-----------------------------------------------------------------------------------------
	public Texture getDropTestImage() { return dropImage; }
    public void setDropTestImage(Texture dropImage) { this.dropImage = dropImage; }	
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
	public Rectangle getDropHitBox(){ return dropHitbox; }
	//-----------------------------------------------------------    	
	public int getDropTipo() { return dropTipo; }
	public void setDropTipo(int dropTipo) { this.dropTipo = dropTipo; }
	//-----------------------------------------------------------    
	public float getDropX() { return x; }
	public void setDropX(float x) {	this.x = x; }
	//-----------------------------------------------------------    
	public float getDropY() { return y; }
	public void setDropY(float y) {	this.y = y;	}
	//-----------------------------------------------------------    
	public int getWidth() {	return width; }
	public void setWidth(int width) { this.width = width; }
	//-----------------------------------------------------------    
	public int getHeight() { return height; }
	public void setHeight(int height) {	this.height = height; }
	//-----------------------------------------------------------    



}