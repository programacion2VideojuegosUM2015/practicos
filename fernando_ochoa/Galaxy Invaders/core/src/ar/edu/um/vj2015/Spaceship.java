package ar.edu.um.vj2015;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;



public class Spaceship {
    private Texture spaceship;
	private Rectangle spaceshipOutline;
	private int movementSpeed;
	private int x;
	private int y;
	
	public Spaceship(){
	spaceship = new Texture(Gdx.files.internal("spaceship.png"));
	x= Gdx.graphics.getWidth()/2 - spaceship.getWidth()/2;
	y= 10;
	spaceshipOutline = new Rectangle(x ,y ,64 ,64);
	movementSpeed = 300;
	}
	public void draw(SpriteBatch batch){
	
		batch.draw(spaceship ,spaceshipOutline.getX() ,spaceshipOutline.getY() ,64 , 64);
		
	}
	
	public void updateMovement(){
		float deltaTime = Gdx.graphics.getDeltaTime();
		
		if(Gdx.app.getType() == ApplicationType.Desktop)
			inputDesktop(deltaTime);
		else if(Gdx.app.getType() == ApplicationType.Android)
			inputAndroid(deltaTime);
		
		if (spaceshipOutline.x < 0)
			spaceshipOutline.x = 0;
		if (spaceshipOutline.x > 800 - 64)
			spaceshipOutline.x = 800 - 64;	
			
		
	}
	private void inputDesktop(float deltaTime){
		
		if (Gdx.input.isKeyPressed(Keys.LEFT))
			spaceshipOutline.x -= movementSpeed * deltaTime;
		if (Gdx.input.isKeyPressed(Keys.RIGHT))
			spaceshipOutline.x += movementSpeed * deltaTime;
			
	}
	
	private void inputAndroid(float deltaTime){
		
		float coordX = Gdx.input.getX();
		
		if(Gdx.input.isTouched()){
			if(coordX < 100)
				spaceshipOutline.x -= movementSpeed * deltaTime;
			if(coordX >100 && coordX < 200)
				spaceshipOutline.x += movementSpeed * deltaTime;
		}
		
	}
	public Rectangle getSpaceshipOutline() {
		return spaceshipOutline;
	}
	
	public void dispose(){
		
		spaceship.dispose();
		
	}
	
	
	
}
