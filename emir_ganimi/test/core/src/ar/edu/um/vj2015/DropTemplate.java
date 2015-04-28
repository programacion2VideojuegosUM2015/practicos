package ar.edu.um.vj2015;

import com.badlogic.gdx.graphics.Texture;

public class DropTemplate {
	private int puntaje;
	private int velocidadCaida;
	private Texture textura;
	
	public DropTemplate(int puntaje, int velocidadCaida, Texture textura) {
		super();
		this.puntaje = puntaje;
		this.velocidadCaida = velocidadCaida;
		this.textura = textura;
	}
	
	public int getPuntaje() {
		return puntaje;
	}
	
	public int getVelocidadCaida() {
		return velocidadCaida;
	}
	
	public Texture getTextura() {
		return textura;
	}
	
	
}
