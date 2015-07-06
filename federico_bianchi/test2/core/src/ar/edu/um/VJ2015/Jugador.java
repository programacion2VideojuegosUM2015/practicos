package ar.edu.um.VJ2015;



import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Jugador {
	private int dinero;
	private BitmapFont fuentePuntaje;

	//constructor de jugador
	public Jugador(){ 
		dinero = 0;
		fuentePuntaje = new BitmapFont(Gdx.files.internal("fuente.fnt"),Gdx.files.internal("fuente.png"),false);
	}
	//metodo para pintar el jugador
	public void draw(SpriteBatch batch){
		fuentePuntaje.draw(batch,"$: " + Integer.toString(dinero),300,650);
	}
	//metodo para setear el dinero
	public void setDinero(int dinero) {
		this.dinero += dinero;
	}
	//metodo para obtener el dinero
	public int getDinero() {
		return dinero;
	}
	
}
