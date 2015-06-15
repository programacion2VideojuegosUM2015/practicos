package ar.edu.um.vj2015.arcanoid;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;


public class Nave extends Actores{
	public static final int NAVE_ANCHURA = 60;
	public static final int NAVE_ALTURA = 15;
	//constructor de la nave
	public Nave(Texture textura,float x, float y) {
		super(textura,x,y);
		
	}
	
	//metodo para actualizar la posicion de la nave
	public void update(float dt){
		super.update(dt);
		
		//aca le asignamos limites
		if (x <= 0){
			x = 0;
		}
		if (x >= Gdx.graphics.getWidth() -50)
			x = Gdx.graphics.getWidth() - 50;
	}
}
