package ar.edu.um.VJ2015;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

public class Balas {
	
	private Array<Bala> balas;
	private boolean estadoBala;
	private float velocidadBala;
	private float direccion;
	protected long lastDropTime;	
		
	//constructor de balas
	public Balas(){
			balas = new Array<Bala>();
			
			estadoBala = true;
			velocidadBala = 250;
			direccion = 1;
			
		}
	
	//metodo para pintar la bala
	public void draw(SpriteBatch batch){
		for (Bala bala : balas) {
			batch.draw(bala.getTexturaBala(), bala.getContornoBala().getX(), bala.getContornoBala().getY(),bala.getTexturaBala().getWidth(),bala.getTexturaBala().getHeight());
		}
	}
	//metodo para actualizar la bala
	public void update(float delta,Revolver revolver){
		//con la tecla de espacio dispara la bala
		if (Gdx.input.isKeyPressed(Keys.SPACE)) {
			this.setEstadoBala(false);
			if (TimeUtils.nanoTime() - lastDropTime > 100000000)
			agregarBala(revolver);
		}
		//si el estado de bala es false devuelve el movimiento de la bala
		if (estadoBala)
			return;
		for (Bala bala : balas) {
			bala.getContornoBala().x = bala.getContornoBala().x - velocidadBala * delta * direccion ;
		if (bala.getContornoBala().x <= 0){
				balas.removeValue(bala, true);
			}
		
		}
	}
	//metodo para agregar bala al revolver
	private void agregarBala(Revolver revolver){
		float x = revolver.getContornoRevolver().getX()+ 50;
		float y = revolver.getContornoRevolver().getY()+20;
		Bala bala = new Bala(x,y);
		balas.add(bala);
		lastDropTime = TimeUtils.nanoTime();
	}
	//setea el estado de la bala
	public void setEstadoBala(boolean estado) {
		this.estadoBala = estado;
	}
	//colision de la bala con el politico
	public void colisionConPoliticos(PoliticosAparecen politicos,Jugador jugador){
			for(Politico politico : politicos.politicos){
			for(Bala bala : balas){	
			if(bala.getContornoBala().overlaps(politico.getRectangulo())){
					balas.removeValue(bala, false);
					politicos.politicos.removeValue(politico, false);
				}
			}
		}	
	}
}
	

