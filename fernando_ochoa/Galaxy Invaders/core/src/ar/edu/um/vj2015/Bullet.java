package ar.edu.um.vj2015;



import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;


public class Bullet {
	private Texture texture;
	private float bulletSpeed;
	private Rectangle bulletOutline;	
   
   
  
	public Bullet(Texture texture, float bulletSpeed, float x, float y) {
		super();
		this.texture = texture;
		this.bulletSpeed = bulletSpeed;
		bulletOutline = new Rectangle(x ,y ,texture.getWidth() ,texture.getHeight());
		
	}

	public Texture getTexture() {
		return texture;
	}

	public float getBulletSpeed() {
		return bulletSpeed;
	}

	public Rectangle getBulletOutline() {
		return bulletOutline;
	}
	

	
    
	
}
    
    

