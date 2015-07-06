package ar.edu.um.VJ2015;

import com.badlogic.gdx.graphics.Texture;

public class PoliticoTemplate {
	private int puntaje;
	private int velocidad;
	private Texture textura;
	
	public PoliticoTemplate(int puntaje, int velocidad, Texture textura) {
		this.puntaje = puntaje;
		this.velocidad = velocidad;
		this.textura = textura;
	}
	public int getPuntaje() {
		return puntaje;
	}
	
	public int getVelocidad() {
		return velocidad;
	}
	
	public Texture getTextura() {
		return textura;
	}
}
