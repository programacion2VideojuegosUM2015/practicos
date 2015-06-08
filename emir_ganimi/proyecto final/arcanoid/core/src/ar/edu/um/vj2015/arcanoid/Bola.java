package ar.edu.um.vj2015.arcanoid;


import java.util.Random;

import com.badlogic.gdx.graphics.Texture;

public class Bola extends Actores{

	private boolean bolaInicio;
	private float direccionX;
	private float direccionY;
	private float speed = 250;
	public ManejadorDeSprite manejadorDeSprite;
	
	//constructor de la bola 
	public Bola(Texture textura,float x, float y,ManejadorDeSprite manejadorDeSprite){
		super(textura,x,y);
		direccionX = direccionY = 1; 
		this.manejadorDeSprite = manejadorDeSprite;
		bolaInicio = true;
		
	}

	//metodo para setear la bola al inicio
	public void setBolaInicio(boolean bolaQuieta) {
		this.bolaInicio = bolaQuieta;
	}
	
	//metodo para agregar velocidad a la bola
	public void setSpeed(float speed) {
		this.speed += speed;
	}
	public void determinarVelocidad(float speed){
		this.speed = speed;
	}

	//metodo que actualiza a la bola 
	public void update(Jugador jugador, float dt,Arcanoid arcanoid){	
		
		super.update(dt);
		
		if (bolaInicio)
			return;

	
		x = x + speed * dt * direccionX ;
		y = y + speed * dt * direccionY ;
		
		//si la bola colisiona con nave la direccion cambia
		if(this.detectarColisionConNave()){
			direccionY *= -1;
		}
		this.bolaCae(jugador, dt, arcanoid);
		this.impactoConLadrillos(jugador);
		this.impactoConParedes();

	}
	
	//metodo que detecta colision entre pelota y nave
	public boolean detectarColisionConNave(){
		if(manejadorDeSprite.bola.rectangulo.overlaps(manejadorDeSprite.nave.rectangulo)){
			manejadorDeSprite.bola.y = manejadorDeSprite.nave.rectangulo.y + 20;
			return true;
		}else{
			return false;
		}
	}
	
	//metodo que dice que hacer en caso de que la bola caiga
	public void bolaCae(Jugador jugador,float dt,Arcanoid arcanoid){
		if(y <= 1){
			y = 1;
			jugador.perderVida();
			  if(jugador.getVidaJugador()==0){
				  arcanoid.setScreen(new PantallaGameOver(arcanoid));
					}else{
					manejadorDeSprite.bola = new Bola(ManejadorDeRecursos.getTextura("bola"),0,45,manejadorDeSprite);
				}
		}
	}
	
	//metodo cuando impacta contra las paredes
	public void impactoConParedes(){
		//si la bola pega contra la pared izquierda
		if(x <= 0){
			x = 0;
			direccionX *= -1;
			setSpeed(5);
		}
		//si la bola pega contra la pared derecha
		if((x + textura.getWidth())>= Constantes.PANTALLA_ANCHURA){
			x = Constantes.PANTALLA_ANCHURA -textura.getHeight();
			direccionX *= -1;
			setSpeed(5);
		}	
		//si la bola pega contra el techo del juego
		if((y + textura.getHeight())>=Constantes.PANTALLA_ALTURA){
			y = Constantes.PANTALLA_ALTURA - textura.getHeight();
			direccionY *= -1;
			setSpeed(5);
		}
	}
	
	//metodo cuando impacta contra los ladrillos
	public void impactoConLadrillos(Jugador jugador){
		
		//bola colisiona con el ladrillo lo desaparece y agrega puntaje
		for(Ladrillo ladrillo: manejadorDeSprite.ladrillos){	
			if(ladrillo.rectangulo.overlaps(rectangulo)){
				jugador.setPuntaje(10);
				setSpeed(5);
				ladrillo.sonidoGolpe.play(100);
				manejadorDeSprite.ladrillos.removeValue(ladrillo, true);
				//numero random para marcar probabilidades
				Random random = new Random();
				int numero = random.nextInt(100);
				//si el ladrillo que se rompe cumple con la probabilidad se activa un beneficio
				if(numero>=0 && numero<=3){
					ladrillo.agregarVida(jugador);
				}
				else if(numero>=6 && numero<=13){
					ladrillo.frenarBola(manejadorDeSprite);
				}
				else if(numero>=30 && numero<=40){
					ladrillo.relentizarBola(manejadorDeSprite);
				}
				else if(numero==100){
					ladrillo.ganarJuego(manejadorDeSprite);
				}
				//si la bola pega desde abajo o arriba
				if((rectangulo.y + Constantes.BOLA_ANCHURA)<=(ladrillo.y + Constantes.LADRILLO_ALTURA)){
					direccionX *= -1;
					direccionY *= -1;
					
					}
					else{
						y = rectangulo.y = ladrillo.y +Constantes.LADRILLO_ALTURA;
						direccionY *= -1;
						direccionX *= -1;
						
						}
					
				//si la bola pega desde el costado
				if((rectangulo.x + Constantes.BOLA_ANCHURA)<=(ladrillo.x + Constantes.LADRILLO_ALTURA)){
						direccionX *= -1;
						direccionY *= 1;
						
					}
					else{
						x = rectangulo.x = ladrillo.x + Constantes.LADRILLO_ALTURA;
						direccionX *= -1;
						direccionY *= 1;
						}	
					}
				}
		}
}
