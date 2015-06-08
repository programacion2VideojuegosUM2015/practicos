package ar.edu.um.vj2015.arcanoid;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public abstract class Actores {
	
	protected Texture textura;
	protected float x;
	protected float y;
	protected Rectangle rectangulo;
	
	//constructor donde asignamos textura, coordenadas y un rectangulo a cada actor(sprite)
	public Actores(Texture textura, float x, float y){
		this.textura = textura;
		this.x = x;
		this.y = y;
		rectangulo = new Rectangle (x, y, textura.getWidth(), textura.getHeight());
	}
	//metodo para pintar cada actor
	public void render(SpriteBatch batch){
		batch.draw(textura,x,y);
	}
	//metodo para actualizar el rectangulo de cada actor
	public void update(float dt){
		//actualiza la posicion del rectangulo
		rectangulo.x = x;
		rectangulo.y = y;
	}
}
