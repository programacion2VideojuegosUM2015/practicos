package ar.edu.um.vj2015;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;



public class Spaceship {
    private Texture spaceship;
	private Rectangle spaceshipOutline;
	private int movementSpeed;
	
	
	public Spaceship(float x, float y){
	spaceship = new Texture(Gdx.files.internal("spaceship.png"));
	spaceshipOutline = new Rectangle(x ,y ,64 ,64);
	movementSpeed = 300;
	}
	public void draw(SpriteBatch batch){
	
		batch.draw(spaceship ,spaceshipOutline.x ,spaceshipOutline.y ,64 , 64);
		
	}
	
	public void updateMovement(){
		if (Gdx.input.isKeyPressed(Keys.LEFT))
			spaceshipOutline.x -= movementSpeed * Gdx.graphics.getDeltaTime();
		if (Gdx.input.isKeyPressed(Keys.RIGHT))
			spaceshipOutline.x += movementSpeed * Gdx.graphics.getDeltaTime();
		if (spaceshipOutline.x < 0)
			spaceshipOutline.x = 0;
		if (spaceshipOutline.x > 800 - 64)
			spaceshipOutline.x = 800 - 64;
	}
}