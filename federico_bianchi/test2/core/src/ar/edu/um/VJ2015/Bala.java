package ar.edu.um.VJ2015;



import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;


public class Bala {
	private Rectangle contornoBala;
	private Texture texturaBala;
	
	//constructor de la bala
	public Bala(float x, float y){
		texturaBala = new Texture(Gdx.files.internal("bala.jpg"));
		contornoBala = new Rectangle(x,y,texturaBala.getWidth(),texturaBala.getHeight());
		
	}
	//metodo para obtener rectangulo
	public Rectangle getContornoBala() {
		return contornoBala;
	}
	//metodo para obtener la textura de la bala
	public Texture getTexturaBala() {
		return texturaBala;
	}
	
}

	
