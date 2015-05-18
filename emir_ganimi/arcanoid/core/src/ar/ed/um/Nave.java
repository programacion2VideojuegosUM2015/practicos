package ar.ed.um;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Nave {
	
	private Texture imagenNave= new Texture(Gdx.files.internal("nave.gif"));
	private Rectangle contornoNave;
	
	public Nave(float x, float y){
		
		contornoNave = new Rectangle(x,y,36,36);
	}

	public Rectangle getContornoNave() {
		return contornoNave;
	}

	public void setContornoNave(Rectangle contornoNave) {
		this.contornoNave = contornoNave;
	}
	
	
	public void update() {
		 
		//aca le asignamos los comportamientos 
		if(Gdx.input.isKeyPressed(Keys.LEFT)){
			contornoNave.x -= 200 * Gdx.graphics.getDeltaTime();
		}
		if(Gdx.input.isKeyPressed(Keys.RIGHT)){
			contornoNave.x += 200 * Gdx.graphics.getDeltaTime();
		}
		//aca le asignamos dos limites
		if (contornoNave.x < 0)
			contornoNave.x = 0;
		if (contornoNave.x > 800 - 64)
			contornoNave.x = 800 - 64;
	}
	
	public void draw(SpriteBatch batch){
		batch.draw(imagenNave, contornoNave.x, contornoNave.y, imagenNave.getWidth(), imagenNave.getHeight());
	}
	
	public void dispose() {
		imagenNave.dispose();
	
	}
}

