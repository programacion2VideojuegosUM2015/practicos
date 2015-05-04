package ar.edu.um.vj2015;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Drop {
	private Texture textura;
	private int puntaje;
	private int velocidadCaida;
	private Rectangle rectangulo;
	private static Sound dropSound = Gdx.audio.newSound(Gdx.files.internal("drop.wav"));
	public Texture getTextura() {
		return textura;
	}
	public Rectangle getRectangulo() {
		return rectangulo;
	}
	public void setRectangulo(Rectangle rectangulo) {
		this.rectangulo = rectangulo;
	}
	public void setTextura(Texture textura) {
		this.textura = textura;
	}
	public int getPuntaje() {
		return puntaje;
	}
	public void setPuntaje(int puntaje) {
		this.puntaje = puntaje;
	}
	public int getVelocidadCaida() {
		return velocidadCaida;
	}
	public void setVelocidadCaida(int velocidadCaida) {
		this.velocidadCaida = velocidadCaida;
	}
	
	public Drop(Texture textura, int puntaje, int velocidadCaida, float x, float y) {
		super();
		this.textura = textura;
		this.puntaje = puntaje;
		this.velocidadCaida = velocidadCaida;
		rectangulo = new Rectangle(x,y,64,61);
		
	}
	
	
}
