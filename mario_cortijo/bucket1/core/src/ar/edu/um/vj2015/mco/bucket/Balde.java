package ar.edu.um.vj2015.mco.bucket;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Balde {
	private Texture bucketImage;
	private Texture bucketImage2;
	private Rectangle bucket;
	private int score;
	private String scoreName;


	
	public Balde () {
		// create a Rectangle to logically represent the bucket
		bucket = new Rectangle();
		bucket.x = 200; // center the bucket horizontally
		bucket.y = 20; // bottom left corner of the bucket is 20 pixels above the bottom screen edge
		bucket.width = 64;
		bucket.height = 64;
		score = 0;
		scoreName = "Puntaje: 0";
	}

	//-----------------------------------------------------------------------------------------
	// MÉTODOS
	//-----------------------------------------------------------------------------------------
	public Texture getBucketImage() { return bucketImage; }
    public void setBucketImage(Texture bucketImage) { this.bucketImage = bucketImage; }	
	//-----------------------------------------------------------------------------------------
	public Texture getBucketImage2() { return bucketImage2; }
    public void setBucketImage2(Texture bucketImage2) { this.bucketImage2 = bucketImage2; }	
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
	public String getBucketScoreName() { return scoreName; }
    public void setBucketScoreName(String scoreName) { this.scoreName = scoreName; }	
	//-----------------------------------------------------------------------------------------
	public int getBucketScore() { return score; }
    public void setBucketScore(int score) { this.score = score; }	
	//-----------------------------------------------------------------------------------------
    public void CreateBalde() { 
    	bucketImage = new Texture(Gdx.files.internal("bucket1.png")); 
    	bucketImage2 = new Texture(Gdx.files.internal("bucket2.png")); 	
    }
    //-----------------------------------------------------------------------------------------
    public void SetPlayer2() { 
		bucket.x = 600; // center the bucket horizontally
    }
    //-----------------------------------------------------------------------------------------
}
