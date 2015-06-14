package ar.edu.um.vj2015.arcanoid;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

public class Ladrillo extends Actores{
	
	//clase interna con el tipo de ladrillo
	public enum TipoLadrillo{
		AMARILLO,AZUL,BLANCO,GRIS,NEGRO,PURPURA,ROJO,VERDE
	}
	
	public TipoLadrillo tipo;
	public Sound sonidoGolpe;
	public  final int LADRILLO_ANCHURA = 45;
	public  final int LADRILLO_ALTURA = 23;
	
	//constructor de ladrillo
	public Ladrillo(Texture textura,float x, float y,TipoLadrillo tipo) {
		super(textura,x,y);
		this.tipo = tipo;
		sonidoGolpe = ManejadorDeRecursos.getSonido("rompiendo");
		
	}
	//metodo para actualizar el ladrillo
	public void update(float dt){
		super.update(dt);
	}
	//aca empiezan los metodos de los beneficios que brindan los ladrillos
	public void agregarVida(Jugador jugador){
		jugador.setVidaJugador(1);
	}
	public void ganarJuego(ManejadorDeSprite manejadorDeSprite){
		manejadorDeSprite.ladrillos.clear();
	}
	public void relentizarBola(ManejadorDeSprite manejadorDeSprite){
		manejadorDeSprite.bola.determinarVelocidad(250);
	}
	public void frenarBola(ManejadorDeSprite manejadorDeSprite){	
		manejadorDeSprite.bola.setBolaInicio(true);
	}
}
