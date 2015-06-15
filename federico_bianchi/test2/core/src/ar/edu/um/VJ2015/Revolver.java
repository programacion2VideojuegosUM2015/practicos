package ar.edu.um.VJ2015;



import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Revolver {
	private Texture texturaRevolver;
	private Rectangle contornoRevolver;
	private Balas balas;
	
	//constructor del revolver
	public Revolver(float x, float y){
		texturaRevolver = new Texture(Gdx.files.internal("arma.jpg"));
		contornoRevolver = new Rectangle(x,y,texturaRevolver.getWidth(),texturaRevolver.getHeight());
		balas = new Balas();
		
	}
	
	public void update(float delta){
		if(Gdx.input.isKeyPressed(Keys.DOWN)){
			 this.contornoRevolver.y -= 250 * delta;
			
		}
		if(Gdx.input.isKeyPressed(Keys.UP)){
			this.contornoRevolver.y += 250 * delta;
			
		}
		if (contornoRevolver.y <= 0){
			contornoRevolver.y = 0;
		}
		if (contornoRevolver.y >=Gdx.graphics.getHeight() -50)
			contornoRevolver.y = Gdx.graphics.getHeight() -50;
		disparar(delta);
	}
	public void disparar(float delta){
		balas.update(delta,this);
	}
	public Rectangle getContornoRevolver() {
		return contornoRevolver;
	}
	//metodo para pintar el revolver
	public void draw(SpriteBatch batch){
		balas.draw(batch);
		batch.draw(texturaRevolver, contornoRevolver.getX(), contornoRevolver.getY());
		
	}
	
}
