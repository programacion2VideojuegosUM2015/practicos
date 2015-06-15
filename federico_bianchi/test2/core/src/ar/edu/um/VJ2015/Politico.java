package ar.edu.um.VJ2015;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Politico {
	
	private Texture textura;
	private int puntaje;
	private int velocidadSalida;
	private Rectangle rectangulo;
	private float x;
	private float y;
	
	public Politico(Texture textura, int puntaje, int velocidadSalida, float x, float y) {
		this.textura = textura;
		this.puntaje = puntaje;
		this.velocidadSalida = velocidadSalida;
		this.x = x;
		this.y = y;
		rectangulo = new Rectangle(x,y,textura.getWidth(),textura.getHeight());
		
	}
	
	public void setX(float x) {
		this.x = x;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

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

	public int getVelocidadSalida() {
		return velocidadSalida;
	}

	public void setVelocidadSalida(int velocidadSalida) {
		this.velocidadSalida = velocidadSalida;
	}
	
	
}

