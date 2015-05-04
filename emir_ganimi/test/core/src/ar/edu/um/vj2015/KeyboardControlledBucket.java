package ar.edu.um.vj2015;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Rectangle;



public class KeyboardControlledBucket extends Bucket {

	public KeyboardControlledBucket(float x, float y) {
		bucket = new Rectangle();
		bucket.x=x;
		bucket.y=y;
		bucket.width = 64;
		bucket.height = 64;
	}

	@Override
	public void update() {
		 
		//aca le asignamos los comportamientos 
		if(Gdx.input.isKeyPressed(Keys.LEFT)){
			bucket.x -= 200 * Gdx.graphics.getDeltaTime();
		}
		if(Gdx.input.isKeyPressed(Keys.RIGHT)){
			bucket.x += 200 * Gdx.graphics.getDeltaTime();
		}
		//aca le asignamos dos limites
		if (bucket.x < 0)
			bucket.x = 0;
		if (bucket.x > 320 - 64)
			bucket.x = 320 - 64;
	}

	


}
