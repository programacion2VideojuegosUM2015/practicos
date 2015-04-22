package ar.edu.um.vj2015.mco.bucket;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Balde {
	private Texture bucketImage;
	private Rectangle bucket;
	
	public Balde () {
		// create a Rectangle to logically represent the bucket
		bucket = new Rectangle();
		bucket.x = 800 / 2 - 64 / 2; // center the bucket horizontally
		bucket.y = 20; // bottom left corner of the bucket is 20 pixels above the bottom screen edge
		bucket.width = 64;
		bucket.height = 64;   
	}

	//-----------------------------------------------------------------------------------------
	// MÉTODOS
	//-----------------------------------------------------------------------------------------
	public Texture getBucketImage() { return bucketImage; }
    public void setBucketImage(Texture bucketImage) { this.bucketImage = bucketImage; }	
	//-----------------------------------------------------------------------------------------
	public Rectangle getBucket() { return bucket; }
    public void setBucket(Rectangle bucket) { this.bucket = bucket; }	
	//-----------------------------------------------------------------------------------------
	public float getBucketX() { return bucket.x; }
    public void setBucketX(float bucketx) { this.bucket.x = bucketx; }	
	//-----------------------------------------------------------------------------------------
	public float getBucketY() { return bucket.y; }
    public void setBucketY(float buckety) { this.bucket.y = buckety; }	
	//-----------------------------------------------------------------------------------------
    public void CreateBalde() { bucketImage = new Texture(Gdx.files.internal("bucket.png")); }
    //-----------------------------------------------------------------------------------------
}
