package ar.edu.um.vj2015;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;


public class MouseControlledBucket extends Bucket {

	public MouseControlledBucket(float x, float y) {
		bucket = new Rectangle();
		bucket.x=x;
		bucket.y=y;
		bucket.width = 64;
		bucket.height = 64;
		
	}

	@Override
	public void update() {
		
		float coordenadaBucket = bucket.x + texture.getHeight()/2;
		if(Gdx.input.isTouched()){
			float coordenadaX = (Gdx.graphics.getHeight() + Gdx.input.getX())/2;
			if(coordenadaBucket >= coordenadaX -5 && coordenadaBucket<= coordenadaX+5){
				coordenadaX = coordenadaBucket;
			}
			if(coordenadaX < coordenadaBucket){
				bucket.x -= 200 * Gdx.graphics.getDeltaTime();
			}
			if(coordenadaX > coordenadaBucket){
				bucket.x += 200 * Gdx.graphics.getDeltaTime();
			}
		}
		if (bucket.x < 385 - 64)
			bucket.x = 385 - 64;
		if (bucket.x > 639 - 64)
			bucket.x = 639 - 64;
		
	}
	
	
	

	
}
