package ar.edu.um.vj2015;



import com.badlogic.gdx.graphics.Texture;


public class BulletTemplate {
	private Texture texture;
	private float bulletSpeed;
   
   
  
	public BulletTemplate(Texture texture, float bulletSpeed) {
		super();
		this.texture = texture;
		this.bulletSpeed = bulletSpeed;
		
		
	}

	public Texture getTexture() {
		return texture;
	}

	public float getBulletSpeed() {
		return bulletSpeed;
	}

	
    
	
}
    
    

