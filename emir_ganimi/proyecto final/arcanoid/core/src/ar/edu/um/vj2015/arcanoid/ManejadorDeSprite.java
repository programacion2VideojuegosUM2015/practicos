package ar.edu.um.vj2015.arcanoid;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;


public class ManejadorDeSprite {
	
	public  Nave nave;
	public  Bola bola;
	public  Array<Ladrillo> ladrillos;
	public  SpriteBatch batch;

	
	//constructor donde inicializamos todos los sprites
	public  ManejadorDeSprite(SpriteBatch batch){
		
		this.batch = batch;
		bola = new Bola(ManejadorDeRecursos.getTextura("bola"),0,45,this);
		nave = new Nave(ManejadorDeRecursos.getTextura("nave"), 180, 30);
		ladrillos = new Array<Ladrillo>();
	
  	  	

	}
	//metodo para renderizar sprites
	public  void render(){
		
		batch.begin();
			nave.render(batch);
			bola.render(batch);
			
			for(Ladrillo ladrillo : ladrillos){
				ladrillo.render(batch);
			}
			
		batch.end();
	}
	
	//metodo para actualizar sprites
	public  void update(float dt,Arcanoid arcanoid,Jugador jugador){
		nave.update(dt);
		bola.update(jugador, dt,arcanoid);
		for(Ladrillo ladrillo : ladrillos){
			ladrillo.update(dt);
		}
	}
}
