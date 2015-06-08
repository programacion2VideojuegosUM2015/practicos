package ar.edu.um.vj2015.arcanoid;


import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Jugador {
	private int vidaJugador;
	private int puntaje;
	private BitmapFont fuenteVida;
	private BitmapFont fuentePuntaje;
	
	//constructor del jugador donde inicializamos la vida,puntaje y los recursos de fuentes
	public Jugador(){
		
		fuenteVida = ManejadorDeRecursos.getFuente("tipografia");
		fuentePuntaje = ManejadorDeRecursos.getFuente("tipografia");
		vidaJugador = 3;
		puntaje = 0;
	}
	
	//metodo para pintar 
	public void draw(SpriteBatch batch){
		fuenteVida.draw(batch,"vidas: " + Integer.toString(vidaJugador),250,700);
		fuentePuntaje.draw(batch,"puntaje: " + Integer.toString(puntaje),10,700);
	}
	//metodo para agregar puntaje al jugador
	public void setPuntaje(int puntaje) {
		this.puntaje += puntaje;
	}
	//metodo para obtener la vida actual del jugador
	public int getVidaJugador() {
		return vidaJugador;
	}
	//metodo para restar vida al jugador
	public void perderVida(){
		vidaJugador -= 1;
	}
	//metodo para agregar vidas al jugador
	public void setVidaJugador(int vidaJugador) {
		this.vidaJugador += vidaJugador;
	}	
}
