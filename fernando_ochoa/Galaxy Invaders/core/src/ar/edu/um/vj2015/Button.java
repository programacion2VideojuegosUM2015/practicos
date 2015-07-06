package ar.edu.um.vj2015;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public abstract class Button {
	protected Texture buttonTexture;
	protected Rectangle outline;
	protected float xMax;
	protected float xMin;
	protected float yMax;
	protected float yMin;
	
	public Button (int x , int y){
		Texture buttonTexture = new Texture(Gdx.files.internal("botonjugar.png"));
		outline = new Rectangle(x ,y , buttonTexture.getWidth(), buttonTexture.getHeight());
		
		xMin = x;
		yMax = Gdx.graphics.getHeight() - y;
		xMax = x + outline.width;
		yMin = Gdx.graphics.getHeight() - (y + outline.height);
	}
	
	public void draw(SpriteBatch batch){
		batch.draw(buttonTexture, outline.x, outline.y, outline.width, outline.height);
	}
	public void update(){
		if(pressButton())
			performance();
		
	}
	private boolean pressButton(){
		return Gdx.input.isTouched() && Gdx.input.getX() >= xMin && Gdx.input.getX() <= xMax &&
				Gdx.input.getY() >= yMin && Gdx.input.getY() <= yMax;
	}
	
	public abstract void performance();
	
	
	

	public void dispose(){
		buttonTexture.dispose();
		
	}
	}


