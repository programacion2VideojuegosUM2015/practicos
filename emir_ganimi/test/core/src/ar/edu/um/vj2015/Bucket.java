package ar.edu.um.vj2015;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public abstract class Bucket {
	//se pone protected porque sabemos que la usaremos en clases que hereden de esta
	protected Texture texture= new Texture(Gdx.files.internal("bucket.png"));
	
	
	//rectangulo para detectar colisiones
	protected Rectangle bucket;
	

	//creamos metodo para no pasar todos los parametros al metodo render
	public void draw(SpriteBatch batch){
		batch.draw(texture, bucket.x, bucket.y, texture.getWidth(), texture.getHeight());
		
		
	}
	
	public abstract void update();
	
	public Rectangle getBucket(){
		return bucket;
	}
	public void dispose() {
		texture.dispose();
		
	}
		
	
	
}
