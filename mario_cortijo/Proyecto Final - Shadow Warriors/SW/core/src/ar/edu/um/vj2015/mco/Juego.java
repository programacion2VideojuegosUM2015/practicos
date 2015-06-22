package ar.edu.um.vj2015.mco;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureWrap;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Iterator;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;


public class Juego extends ApplicationAdapter {
	private Music musicChasing;
	private Music musicGameStart;
	private Music musicUnexpected;
	private Music soundGong;
	private SpriteBatch batch;
	private OrthographicCamera camera;
	private BitmapFont fuente;
	Array<NinjaEnemigo> arrayEnemy;
	long lastEnemySpawn;
	long tiempo;
	private Texture bosque1;
	private Texture swTitle;
	private float posicionbosque;
	private int animataque;
	private int debug;
	//GRAFICOS INTERFAZ
	private Texture fondoHP;
	private Texture androidPad01;
	private Texture androidPad02;
	private Texture androidPad03;
	private Texture androidPad;
	private Texture letreroFight;
	private Texture letreroGameOver;
	private Texture letreroGo;
	private Texture letreroStageClear;
	private Texture letreroContinue9;
	private Texture letreroContinue8;
	private Texture letreroContinue7;
	private Texture letreroContinue6;
	private Texture letreroContinue5;
	private Texture letreroContinue4;
	private Texture letreroContinue3;
	private Texture letreroContinue2;
	private Texture letreroContinue1;
	private Texture letreroContinue0;
	private Texture puertaIzq;
	private Texture puertaDer;
	private Texture crosshair;
	private Texture pixelNegro;
	private int enemigosOnScreen;
	static public int jugador1Ocupado;
	public int stageClear;
	static public int gameOver;
	public int firstTime = 0;
	public int creditos = 3;
	static public int titleScreen = 1;
	public int autoWalk = 0;
	public int distanceTo;
	
	
	static Jugador jugador2 = new Jugador();
	static Jugador jugador1 = new Jugador();
	static Respawn ControlRespawn = new Respawn();
	static CambioPantalla Puertas = new CambioPantalla();
	static PantallaGameOver GameOver = new PantallaGameOver();

	
	@Override
	public void create() {
		
//----------------------------------------------------------------------------------------------------
// CREATE: CARGO RESOURCES
//----------------------------------------------------------------------------------------------------
		// FONDOS
		bosque1 = new Texture(Gdx.files.internal("bosque1.png"));
		swTitle = new Texture(Gdx.files.internal("interfaz/SWTitle.png")); 
		bosque1.setWrap(TextureWrap.Repeat, TextureWrap.Repeat);
		posicionbosque = 0;
		// GRAFICOS INTERFAZ
		fondoHP = new Texture(Gdx.files.internal("interfaz/fondoHP.png"));
		androidPad01 = new Texture(Gdx.files.internal("interfaz/androidPad01.png"));
		androidPad02 = new Texture(Gdx.files.internal("interfaz/androidPad02.png"));
		androidPad03 = new Texture(Gdx.files.internal("interfaz/androidPad03.png"));
		androidPad = new Texture(Gdx.files.internal("interfaz/androidPad04.png"));
		puertaIzq = new Texture(Gdx.files.internal("interfaz/puertaIzq.png"));
		puertaDer = new Texture(Gdx.files.internal("interfaz/puertaDer.png"));
		crosshair = new Texture(Gdx.files.internal("crosshair.png"));
		letreroFight = new Texture(Gdx.files.internal("interfaz/letreroFight.png"));
		letreroGameOver = new Texture(Gdx.files.internal("interfaz/letreroGameOver.png"));
		letreroGo = new Texture(Gdx.files.internal("interfaz/letreroGo.png"));
		letreroStageClear = new Texture(Gdx.files.internal("interfaz/letreroStageClear.png"));
		letreroContinue9 = new Texture(Gdx.files.internal("interfaz/letreroContinue9.png"));
		letreroContinue8 = new Texture(Gdx.files.internal("interfaz/letreroContinue8.png"));
		letreroContinue7 = new Texture(Gdx.files.internal("interfaz/letreroContinue7.png"));
		letreroContinue6 = new Texture(Gdx.files.internal("interfaz/letreroContinue6.png"));
		letreroContinue5 = new Texture(Gdx.files.internal("interfaz/letreroContinue5.png"));
		letreroContinue4 = new Texture(Gdx.files.internal("interfaz/letreroContinue4.png"));
		letreroContinue3 = new Texture(Gdx.files.internal("interfaz/letreroContinue3.png"));
		letreroContinue2 = new Texture(Gdx.files.internal("interfaz/letreroContinue2.png"));
		letreroContinue1 = new Texture(Gdx.files.internal("interfaz/letreroContinue1.png"));
		letreroContinue0 = new Texture(Gdx.files.internal("interfaz/letreroContinue0.png"));
		pixelNegro = new Texture(Gdx.files.internal("interfaz/pixelNegro.png"));
		enemigosOnScreen = 0;
		jugador1Ocupado = 0;
		stageClear = 0;
		gameOver=0;
		debug = 0;
		// MUSICA SONIDO Y FUENTE
		musicChasing = Gdx.audio.newMusic(Gdx.files.internal("musica/chasing.mp3"));
		musicGameStart = Gdx.audio.newMusic(Gdx.files.internal("musica/gameStart.mp3"));
		musicUnexpected = Gdx.audio.newMusic(Gdx.files.internal("musica/unexpected.mp3"));
		soundGong = Gdx.audio.newMusic(Gdx.files.internal("sonidos/gong.mp3"));
		fuente = new BitmapFont(Gdx.files.internal("interfaz/yiear.fnt"),Gdx.files.internal("interfaz/yiear.png"), false);
		// REPRODUZCO LA MUSICA
		musicChasing.setLooping(true);
		musicUnexpected.setLooping(true);
		musicGameStart.setLooping(false);
		if(titleScreen==1){musicGameStart.play();}
		if(titleScreen!=1){musicChasing.play();}
		
	      //getPuertas().setEstadoPuerta(1);
	      //getPuertas().setLetreroPuerta(2);
	      //getPuertas().setTics(0);
		

//----------------------------------------------------------------------------------------------------
// CREATE: CREO JUGADORES Y ENEMIGOS
//----------------------------------------------------------------------------------------------------
		//CREO CAMARA Y BATCH
		camera = new OrthographicCamera(320, 240);  
		camera.setToOrtho(false, 320, 240);
		batch = new SpriteBatch();
		//CREO JUGADORES Y ENEMIGOS
		getJugador1().createJugador();
		getJugador2().createJugador();
		getJugador2().SetPlayer2();	
		arrayEnemy = new Array<NinjaEnemigo>();
		spawnEnemy();
		if(firstTime==0){
			firstTime=1;
			getJugador1().TestAnimation();
			getRespawn().RespawnEnemy();
			getPuertas().animacionPuerta();
			getGameOver().animacionGameOver();
			getPuertas().setDibujarLetrero(0);
		}	
	}

//----------------------------------------------------------------------------------------------------
// SPAWMEO DE ENEMIGOS
//----------------------------------------------------------------------------------------------------
	private void spawnEnemy() {
		if(getRespawn().getEnemigoSpamea()==true){
		  NinjaEnemigo oneEnemy = new NinjaEnemigo();
		  if(enemigosOnScreen<3){ enemigosOnScreen++; }
		  if(enemigosOnScreen>=3) { getRespawn().setEnemigoSpamea(false); }
		  oneEnemy.setEnemyX(MathUtils.random(0, 320 - 64));
		  oneEnemy.setEnemyY(480);
		  oneEnemy.setWidth(64);
		  oneEnemy.setHeight(64);	  
		  if(enemigosOnScreen==0){oneEnemy.setAlturaCaida(150);}
		  if(enemigosOnScreen==1){oneEnemy.setAlturaCaida(170);}
		  if(enemigosOnScreen==2){oneEnemy.setAlturaCaida(190);}  
		  if(enemigosOnScreen==3){oneEnemy.setAlturaCaida(210);}   
		  //oneEnemy.setAlturaCaida(MathUtils.random(140,210));
		  oneEnemy.AnimationEnemy();
		  arrayEnemy.add(oneEnemy);	
		  lastEnemySpawn = TimeUtils.nanoTime();
		}
	}
	
	@Override
	public void render() {
		//LIMPIO LA PANTALLA
		Gdx.gl.glClearColor(184/255f, 204/255f, 208/255f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //UPDATEO CAMARA
		camera.update();
		//UPDATEO INFO DEBUG
		getJugador1().setRyuTESTstring("PUERTAS: "+getPuertas().getEstadoPuerta());	
		batch.setProjectionMatrix(camera.combined);
		
		
//--------------------------------------------------------------------------------------------------
// BATCH
//--------------------------------------------------------------------------------------------------
		batch.begin();
		
		//DIBUJO FONDO
		batch.draw(bosque1, posicionbosque, 40);
		batch.draw(bosque1, posicionbosque+480, 40);
			
		//DIBUJO JUGADORES
		//PLAYER 2
		batch.draw(getJugador2().getRyuCurrentFrame(), getJugador2().getRyuX(), getJugador2().getRyuY());	
		
		/*//PLAYER 1	
		batch.draw(getJugador1().getRyuCurrentFrame(), getJugador1().getRyuX(), getJugador1().getRyuY()-32, 18, 0, 58, 58, 1, 1, 0, 0, 0, 58, 58, getJugador1().getRyuXScale(), false);
		//DEBUG TEST DRAW HITBOXES
		if(debug==1){
		  batch.draw(crosshair, getJugador1().getRyuX()-3, getJugador1().getRyuY()-3);
		  batch.setColor(1.0f, 1.0f, 1.0f,0.5f);	
		  batch.draw(pixelNegro,getJugador1().getRyuX(),getJugador1().getRyuY(),40.0f,3.0f,0,0,40,3,false,false);
		  batch.setColor(1.0f, 1.0f, 1.0f,1);
		}
		
		//DIBUJO ENEMIGOS
		for (NinjaEnemigo oneEnemy : arrayEnemy) {		
			
			if(oneEnemy.getEnemyXscale()==false){batch.draw(oneEnemy.getEnemyCurrentFrame(), oneEnemy.getEnemyX()+22, oneEnemy.getEnemyY()-32, 18, 0, 58, 58, 1, 1, 0, 0, 0, 58, 58, oneEnemy.getEnemyXscale(), false);}  
			if(oneEnemy.getEnemyXscale()==true){batch.draw(oneEnemy.getEnemyCurrentFrame(), oneEnemy.getEnemyX()-2, oneEnemy.getEnemyY()-32, 18, 0, 58, 58, 1, 1, 0, 0, 0, 58, 58, oneEnemy.getEnemyXscale(), false);}
			
			if(debug==1){
			  batch.draw(crosshair,oneEnemy.getEnemyX()-3,oneEnemy.getEnemyY()-3);
			  batch.setColor(255.0f, 255.0f, 1.0f,0.5f);	
			  batch.draw(pixelNegro,oneEnemy.getEnemyX(),oneEnemy.getEnemyY(),40.0f,3.0f,0,0,40,3,false,false);
			  batch.setColor(1.0f, 1.0f, 1.0f,1);
			  fuente.draw(batch,"CONDUCTA: " + oneEnemy.getEnemyConducta(), oneEnemy.getEnemyX(),oneEnemy.getEnemyY()+50);
			  fuente.draw(batch,"ESTADO: " + oneEnemy.getEnemyEstado(), oneEnemy.getEnemyX(),oneEnemy.getEnemyY()+40);
			  
			}


			if(oneEnemy.getEnemyEstado()=="Ataque" ||  oneEnemy.getEnemyEstado()=="BeingHit" ||  oneEnemy.getEnemyEstado()=="Fall" )
			{
			  fuente.draw(batch, oneEnemy.getEnemyCantidadHP(), 220, 220);
			  fuente.draw(batch, "ninja neme", 220, 230);
			}
		}*/
		
			
		
		//PLAYER 1	
		if(enemigosOnScreen==0){batch.draw(getJugador1().getRyuCurrentFrame(), getJugador1().getRyuX(), getJugador1().getRyuY()-32, 18, 0, 58, 58, 1, 1, 0, 0, 0, 58, 58, getJugador1().getRyuXScale(), false);}
				
		//DIBUJO ENEMIGOS
		for (NinjaEnemigo oneEnemy : arrayEnemy) {		

		if(getJugador1().getRyuY()<oneEnemy.getEnemyY()) {
		   if(oneEnemy.getEnemyXscale()==false){batch.draw(oneEnemy.getEnemyCurrentFrame(), oneEnemy.getEnemyX()+22, oneEnemy.getEnemyY()-32, 18, 0, 58, 58, 1, 1, 0, 0, 0, 58, 58, oneEnemy.getEnemyXscale(), false);}  
		  if(oneEnemy.getEnemyXscale()==true){batch.draw(oneEnemy.getEnemyCurrentFrame(), oneEnemy.getEnemyX()-2, oneEnemy.getEnemyY()-32, 18, 0, 58, 58, 1, 1, 0, 0, 0, 58, 58, oneEnemy.getEnemyXscale
		(), false);}
		  batch.draw(getJugador1().getRyuCurrentFrame(), getJugador1().getRyuX(), getJugador1().getRyuY()-32, 18, 0, 58, 58, 1, 1, 0, 0, 0, 58, 58, getJugador1().getRyuXScale(), false);
		}

		if(getJugador1().getRyuY()>oneEnemy.getEnemyY()) {
		   batch.draw(getJugador1().getRyuCurrentFrame(), getJugador1().getRyuX(), getJugador1().getRyuY()-32, 18, 0, 58, 58, 1, 1, 0, 0, 0, 58, 58, getJugador1().getRyuXScale(), false);
		   if(oneEnemy.getEnemyXscale()==false){batch.draw(oneEnemy.getEnemyCurrentFrame(), oneEnemy.getEnemyX()+22, oneEnemy.getEnemyY()-32, 18, 0, 58, 58, 1, 1, 0, 0, 0, 58, 58, oneEnemy.getEnemyXscale(), false);}  
		  if(oneEnemy.getEnemyXscale()==true){batch.draw(oneEnemy.getEnemyCurrentFrame(), oneEnemy.getEnemyX()-2, oneEnemy.getEnemyY()-32, 18, 0, 58, 58, 1, 1, 0, 0, 0, 58, 58, oneEnemy.getEnemyXscale
		(), false);}
		}

		//BARRA HP ENEMIGO
		  if(oneEnemy.getEnemyEstado()=="Ataque" ||  oneEnemy.getEnemyEstado()=="BeingHit" ||  oneEnemy.getEnemyEstado()=="Fall" )
		  {
		    fuente.draw(batch, oneEnemy.getEnemyCantidadHP(), 220, 220);
		    fuente.draw(batch, "ninja neme", 220, 230);
		  }
		}
		
		
		
		//DIBUJO HUD
		
		//BARRAS HP
		batch.draw(fondoHP, 0, 188);
		fuente.draw(batch, getJugador1().getRyuCantidadHP(), 10, 220);
		fuente.draw(batch, getJugador1().getRyuVidasString(), 10, 230);
		
		fuente.draw(batch,"TIME ", 145, 230);
		fuente.draw(batch, " " + getGameOver().getTimer(), 147, 220);
		
		//TEXTO DEBUG
		if(debug==1){
		  fuente.draw(batch,"ESTADO: " + getJugador1().getRyuEstado(), 10, 210);
		  fuente.draw(batch,"OCUPADO: " + jugador1Ocupado, 10, 200);
		  fuente.draw(batch,"COMBO: " + getJugador1().getRyuCombo(), 10, 190);
		  fuente.draw(batch,"FRAME: " + getJugador1().getRyuFrame()+ " / " + getJugador1().getRyuLastFrame(), 10, 180);
		  fuente.draw(batch,"Y: " + getJugador1().getRyuY(), 10, 170);
		 
		}

		
		//CARTEL GO
		if(enemigosOnScreen==0 & posicionbosque<=-640 && getRespawn().getParpadeoGo()==1) {batch.draw(letreroGo, 240, 130);}

		//PANTALLA DE TITULO
		if(titleScreen>0){
			batch.draw(swTitle, 0, 0);
			if(getRespawn().getParpadeoGo()==1){
				if(Gdx.app.getType() == ApplicationType.Desktop){fuente.draw(batch, " - Press X to Start -", 90, 40);}
				if(Gdx.app.getType() == ApplicationType.Android){fuente.draw(batch, " - Touch X to Start -", 90, 40);}
			}
		}
		
		//PUERTAS
		batch.draw(puertaIzq,getPuertas().getXIzq(),0);
		batch.draw(puertaDer,getPuertas().getXDer(),0);
		if(getPuertas().getDibujarLetrero()==1 && titleScreen!=1){
		  if(getPuertas().getLetreroPuerta()==1){batch.draw(letreroFight, 10, 8);} 
		  if(getPuertas().getLetreroPuerta()==4){
			  
			  if(getGameOver().getTics()==0){batch.draw(letreroContinue9, 10, 8);}
			  if(getGameOver().getTics()==1){batch.draw(letreroContinue8, 10, 8);} 
			  if(getGameOver().getTics()==2){batch.draw(letreroContinue7, 10, 8);} 
			  if(getGameOver().getTics()==3){batch.draw(letreroContinue6, 10, 8);} 
			  if(getGameOver().getTics()==4){batch.draw(letreroContinue5, 10, 8);} 
			  if(getGameOver().getTics()==5){batch.draw(letreroContinue4, 10, 8);} 
			  if(getGameOver().getTics()==6){batch.draw(letreroContinue3, 10, 8);} 
			  if(getGameOver().getTics()==7){batch.draw(letreroContinue2, 10, 8);} 
			  if(getGameOver().getTics()==8){batch.draw(letreroContinue1, 10, 8);}
			  if(getGameOver().getTics()==9){batch.draw(letreroContinue0, 10, 8);}
			  if(getGameOver().getTics()>9){batch.draw(letreroGameOver, 10, 8); } 
			  if(getGameOver().getTics()==10){gameOver=1; if(soundGong.isPlaying()){gameOver=1;}else{musicChasing.stop(); soundGong.play();}}
			  
			  if(getRespawn().getParpadeoGo()==1 && gameOver==0){
			  fuente.draw(batch, "credits: " + creditos, 120, 60);	  
			  if(Gdx.app.getType() == ApplicationType.Desktop){fuente.draw(batch, " - Press X to replay level -", 60, 50);}
			  if(Gdx.app.getType() == ApplicationType.Android){fuente.draw(batch, " - Touch X to replay level -", 60, 50);}		  
			  }
		  }
		  if(getPuertas().getLetreroPuerta()==2 && titleScreen!=1){ batch.draw(letreroGameOver, 10, 8); } 
		}
        if(debug==1){
         
         fuente.draw(batch,"RELOJ RESPAWN: " + getRespawn().getTics() + " / " + getRespawn().getEnemigoSpamea(), 10, 100);	
         fuente.draw(batch,"POSICION BOSQUE: " + posicionbosque, 10, 90);
         //fuente.draw(batch,"TICS: " + getPuertas().getTics(), 10, 80);
         fuente.draw(batch,"REPETICION: " + getPuertas().getRoundTics(), 10, 70);
         fuente.draw(batch,"ESTADO PUERTA: " + getPuertas().getEstadoPuerta(), 10, 60);
         //fuente.draw(batch,"DIBUJO LETRERO?: " + getPuertas().getDibujarLetrero(), 10, 70);
         //fuente.draw(batch,"LETRERO: " + getPuertas().getLetreroPuerta(), 10, 60);
         fuente.draw(batch,"CREDITOS: " + creditos, 10, 50);
         fuente.draw(batch,"TITLE SCREEN: " + titleScreen, 10, 40);
         fuente.draw(batch,"ANIM GAME OVER: " + getGameOver().getTics(), 10, 30);
         fuente.draw(batch,"GAME OVER: " + gameOver, 10, 20);
        }
		
		
		//STAGECLEAR
		if(stageClear==1) {
			batch.draw(letreroStageClear, 10, 60);
			if(getJugador1().getRyuScore()==0){
				getJugador1().setRyuScore(getJugador1().getRyuKills()+getJugador1().getRyuVidas()+getJugador1().getRyuHP()+getGameOver().getTimer());
				getJugador1().setRyuScore(getJugador1().getRyuScore()*100);
			}
			
			if(getPuertas().getEstadoPuerta()!=1){getPuertas().setEstadoPuerta(1);}
			 fuente.draw(batch,"ninja x " + getJugador1().getRyuKills() + " ----- " + getJugador1().getRyuKills() * 100 + " points", 60, 135);
			 fuente.draw(batch, "lives x " + getJugador1().getRyuVidas() + " ----- " + getJugador1().getRyuVidas() * 100 + " points", 60, 120);
			 fuente.draw(batch, "health x " + getJugador1().getRyuHP() + " ----- " + getJugador1().getRyuHP() * 100 + " points", 60, 105);
			 fuente.draw(batch, "time x " + getGameOver().getTimer() + " ----- " + getGameOver().getTimer() * 100 + " points", 60, 90);	
			 fuente.draw(batch, "total ----- " + getJugador1().getRyuScore() + " points", 60, 75);
			 if(getRespawn().getParpadeoGo()==1){
                  if(Gdx.app.getType() == ApplicationType.Desktop){fuente.draw(batch, " - Press X to replay level -", 60, 35);}
 				  if(Gdx.app.getType() == ApplicationType.Android){fuente.draw(batch, " - Touch X to replay level -", 60, 35);}
				 }
		}
		
		
		
	    //CONTROLES TOUCH
	    if(Gdx.app.getType() == ApplicationType.Android){
		   batch.setColor(1.0f, 1.0f, 1.0f,0.5f);	
	       batch.draw(androidPad, 0, 0);
	       batch.setColor(1.0f, 1.0f, 1.0f,1);	
	    }
	  	
		
		batch.end();
				
		
//---------------------------------------------------------------------------------------------------
//   CONTROLES TOUCH 
//---------------------------------------------------------------------------------------------------
	//   PLAYER 1
	//-----------------------------------------------------------------------------------------------
	//   BOTON ATAQUE
	//-----------------------------------------------------------------------------------------------
		if (Gdx.input.isTouched()) {
			Vector3 touchPos = new Vector3();
			touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(touchPos);
			//getJugador1().setRyuTESTstring("Touchy: x " + touchPos.x + " - y " + touchPos.y);		
				
			//BOTON ATAQUE
			//if(touchPos.x>267 && touchPos.y>31 && touchPos.x<298 && touchPos.y<62)
			if(touchPos.x>233 && touchPos.y>7 && touchPos.x<279 && touchPos.y<60)
			{
				animataque = MathUtils.random(1,2);
				if(getJugador1().getRyuEstado()=="Stance"){
			      if (animataque==1) {getJugador1().setRyuEstado("Punch");}
			      if (animataque==2) {getJugador1().setRyuEstado("Kick");}
			      getJugador1().setRyuLastFrame(4);
			      getJugador1().setRyuFrame(0);
				}
		  }		
		}
		//else {androidPad = androidPad01;}
	//---------------------------------------------------------------------------------------------------
	//   BOTON MENUES
	//---------------------------------------------------------------------------------------------------
		if (Gdx.input.isTouched()) {
		  Vector3 touchPos = new Vector3();
		  touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
	      camera.unproject(touchPos);
		//if(touchPos.x>40 && touchPos.y>20 && touchPos.x<280 && touchPos.y<60)
		if(touchPos.x>268 && touchPos.y>56 && touchPos.x<314 && touchPos.y<109)
		{
			if(gameOver==0){
				if(stageClear==1 || getJugador1().getRyuVidas() == 0 || titleScreen==1) {
		          getPuertas().setEstadoPuerta(1);
		          getPuertas().setTics(0);
		          getPuertas().setRoundTics(0);
		          if(getJugador1().getRyuVidas()==0){ getPuertas().setLetreroPuerta(0);}
		          if(titleScreen==1){ getPuertas().setLetreroPuerta(1);}      
		          //else{titleScreen=2;}
		          titleScreen=2;
	              musicUnexpected.stop();
	              musicChasing.stop();
		          create();
		          getJugador1().resetJugador();
		          getRespawn().setTics(0);
			   }}
		  }
		 }
		
		//else {androidPad = androidPad01;}
	//---------------------------------------------------------------------------------------------------
	// BOTON LEFT
	//---------------------------------------------------------------------------------------------------
		if (Gdx.input.isTouched()) {
		  Vector3 touchPos = new Vector3();
		  touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
	      camera.unproject(touchPos);	
		  if(touchPos.x>3 && touchPos.y>35 && touchPos.x<3+33 && touchPos.y<35+33) {
		    if(getJugador1().getRyuEstado()=="Stance" || getJugador1().getRyuEstado()=="Walk" || getJugador1().getRyuEstado()=="Run" ){
			  getJugador1().setRyuXScale(true); getJugador1().setRyuXScaleInt(-1);
			  getJugador1().setRyuEstado("Run");
			  getJugador1().setRyuLastFrame(6);
			  getJugador1().setRyuX(getJugador1().getRyuX() - 120 * Gdx.graphics.getDeltaTime());
			  jugador1Ocupado = 0;
         }
	   } }		
	   //else {androidPad = androidPad01;}
	//---------------------------------------------------------------------------------------------------
	// BOTON RIGHT
	//---------------------------------------------------------------------------------------------------
		if (Gdx.input.isTouched()) {
			  Vector3 touchPos = new Vector3();
			  touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
		      camera.unproject(touchPos);	
//			  if(touchPos.x>49 && touchPos.y>28 && touchPos.x<69 && touchPos.y<48)
		      if(touchPos.x>68 && touchPos.y>35 && touchPos.x<68+33 && touchPos.y<35+33){
			    if(getJugador1().getRyuEstado()=="Stance" || getJugador1().getRyuEstado()=="Walk" || getJugador1().getRyuEstado()=="Run" ){  
			    getJugador1().setRyuXScale(false); getJugador1().setRyuXScaleInt(1);
			    getJugador1().setRyuEstado("Run");
			    getJugador1().setRyuLastFrame(6);
			    getJugador1().setRyuX(getJugador1().getRyuX() + 120 * Gdx.graphics.getDeltaTime());
			    jugador1Ocupado = 0;				  
		      }
		   }  }		
		   //else {androidPad = androidPad01;}
	//---------------------------------------------------------------------------------------------------
	// BOTON DOWN
	//---------------------------------------------------------------------------------------------------
		if (Gdx.input.isTouched()) {
			  Vector3 touchPos = new Vector3();
			  touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
		      camera.unproject(touchPos);	
			  if(touchPos.x>30 && touchPos.y>3 && touchPos.x<30+33 && touchPos.y<3+33)
			  {
			   if(getJugador1().getRyuEstado()=="Stance" || getJugador1().getRyuEstado()=="Walk" || getJugador1().getRyuEstado()=="Run" ){  
			     getJugador1().setRyuEstado("Walk");
				 getJugador1().setRyuLastFrame(11);
				 getJugador1().setRyuY(getJugador1().getRyuY() - 40 * Gdx.graphics.getDeltaTime());
				 jugador1Ocupado = 0;
			    }
		      }
		    }		
		   //else {androidPad = androidPad01;}
	//---------------------------------------------------------------------------------------------------
	// BOTON UP
	//---------------------------------------------------------------------------------------------------
		if (Gdx.input.isTouched()) {
			  Vector3 touchPos = new Vector3();
			  touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
		      camera.unproject(touchPos);	
			  if(touchPos.x>30 && touchPos.y>77 && touchPos.x<30+33 && touchPos.y<77+33)
			  {
			      if(getJugador1().getRyuEstado()=="Stance" || getJugador1().getRyuEstado()=="Walk" || getJugador1().getRyuEstado()=="Run" ){		
			      getJugador1().setRyuEstado("Walk");
			      getJugador1().setRyuLastFrame(11);
			      getJugador1().setRyuY(getJugador1().getRyuY() + 40 * Gdx.graphics.getDeltaTime());
			      jugador1Ocupado = 0;
                }
			  }
		   }		
		  // else { androidPad = androidPad01; }
		
		
//---------------------------------------------------------------------------------------------------
//   CONTROLES TECLADO 
//---------------------------------------------------------------------------------------------------
	//   PLAYER 1
	//-----------------------------------------------------------------------------------------------
	//   BOTON LEFT
	//-----------------------------------------------------------------------------------------------
		if (Gdx.input.isKeyPressed(Keys.LEFT)) {
			if(getJugador1().getRyuEstado()=="Stance" || getJugador1().getRyuEstado()=="Walk" || getJugador1().getRyuEstado()=="Run" ){
			  getJugador1().setRyuXScale(true); getJugador1().setRyuXScaleInt(-1);
			  getJugador1().setRyuEstado("Run");
			  getJugador1().setRyuLastFrame(6);
			  getJugador1().setRyuX(getJugador1().getRyuX() - 120 * Gdx.graphics.getDeltaTime());
			  jugador1Ocupado = 0;
			}
		}
	//-----------------------------------------------------------------------------------------------
    //   BOTON RIGHT
	//-----------------------------------------------------------------------------------------------		
		if (Gdx.input.isKeyPressed(Keys.RIGHT)){
			if(getJugador1().getRyuEstado()=="Stance" || getJugador1().getRyuEstado()=="Walk" || getJugador1().getRyuEstado()=="Run" ){  
			  getJugador1().setRyuXScale(false); getJugador1().setRyuXScaleInt(1);
			  getJugador1().setRyuEstado("Run");
			  getJugador1().setRyuLastFrame(6);
			  getJugador1().setRyuX(getJugador1().getRyuX() + 120 * Gdx.graphics.getDeltaTime());
			  jugador1Ocupado = 0;				  
			}
		}
	//-----------------------------------------------------------------------------------------------
	//   BOTON DOWN
	//-----------------------------------------------------------------------------------------------	
		if (Gdx.input.isKeyPressed(Keys.DOWN)){
			if(getJugador1().getRyuEstado()=="Stance" || getJugador1().getRyuEstado()=="Walk" || getJugador1().getRyuEstado()=="Run" ){  
			  getJugador1().setRyuEstado("Walk");
			  getJugador1().setRyuLastFrame(11);
			  getJugador1().setRyuY(getJugador1().getRyuY() - 40 * Gdx.graphics.getDeltaTime());
			  jugador1Ocupado = 0;
		    }
		}
	//-----------------------------------------------------------------------------------------------
	//   BOTON UP
	//-----------------------------------------------------------------------------------------------		
		if (Gdx.input.isKeyPressed(Keys.UP)){
			if(getJugador1().getRyuEstado()=="Stance" || getJugador1().getRyuEstado()=="Walk" || getJugador1().getRyuEstado()=="Run" ){		
			  getJugador1().setRyuEstado("Walk");
			  getJugador1().setRyuLastFrame(11);
			  getJugador1().setRyuY(getJugador1().getRyuY() + 40 * Gdx.graphics.getDeltaTime());
			  jugador1Ocupado = 0;
			}
		}
	//-----------------------------------------------------------------------------------------------
	//   BOTON ATAQUE
	//-----------------------------------------------------------------------------------------------	
		if (Gdx.input.isKeyJustPressed(Keys.Z)){
			animataque = MathUtils.random(1,2);
			if(getJugador1().getRyuEstado()=="Stance"){
		      if (animataque==1) {getJugador1().setRyuEstado("Punch");}
		      if (animataque==2) {getJugador1().setRyuEstado("Kick");}
		      getJugador1().setRyuLastFrame(4);
		      getJugador1().setRyuFrame(0);
			}
		}
	//-----------------------------------------------------------------------------------------------
	//   BOTON MENU
	//-----------------------------------------------------------------------------------------------	
		if (Gdx.input.isKeyJustPressed(Keys.X)){
			if(gameOver==0){
			if(stageClear==1 || getJugador1().getRyuVidas() == 0 || titleScreen==1) {
	          getPuertas().setEstadoPuerta(1);
	          getPuertas().setTics(0);
	          getPuertas().setRoundTics(0);
	          if(getJugador1().getRyuVidas()==0){ getPuertas().setLetreroPuerta(0);}
	          if(titleScreen==1){ getPuertas().setLetreroPuerta(1);}      
	          //else{titleScreen=2;}
	          titleScreen=2;
              musicUnexpected.stop();
              musicChasing.stop();
	          create();
	          getJugador1().resetJugador();
	          getRespawn().setTics(0);
		   }}
		}
		
		//SOLO PARA ACOMODAR LOS FRAMES
		if(getJugador1().getRyuEstado()!="Hurt" && getJugador1().getRyuEstado()!="Dead") {
		 if (Gdx.input.isKeyJustPressed(Keys.LEFT)) {getJugador1().setRyuFrame(0);}    
		 if (Gdx.input.isKeyJustPressed(Keys.RIGHT)){getJugador1().setRyuFrame(0);}
		 if (Gdx.input.isKeyJustPressed(Keys.DOWN)){getJugador1().setRyuFrame(0);}
		 if (Gdx.input.isKeyJustPressed(Keys.UP)){getJugador1().setRyuFrame(0);}
		}

		
//-----------------------------------------------------------------------------------------------
//   COMANDOS DEBUG
//-----------------------------------------------------------------------------------------------
		if (Gdx.input.isKeyJustPressed(Keys.D) && debug==1){ getJugador1().setRyuVidas(0); }
		if (Gdx.input.isKeyJustPressed(Keys.K) && debug==1){ posicionbosque=posicionbosque - 200 * Gdx.graphics.getDeltaTime(); }
		if (Gdx.input.isKeyJustPressed(Keys.F1)){ debug=1; getJugador1().setRyuHP(200);}
		if (Gdx.input.isKeyJustPressed(Keys.F2) && debug==1){ debug=0; getJugador1().setRyuHP(9);}

		
//-----------------------------------------------------------------------------------------------
//   LIMITES MOVIENTO JUGADOR
//-----------------------------------------------------------------------------------------------
	//   PLAYER 1
	//-------------------------------------------------------------------------------------------
		if (getJugador1().getRyuX() < 0)
			getJugador1().setRyuX(0);
		
		if(posicionbosque>-640){
		  if (getJugador1().getRyuX() > 360 - 90)
			getJugador1().setRyuX(360 - 90);
		}
		if(posicionbosque<=-640){
		  if (getJugador1().getRyuX() > 360) { enemigosOnScreen=5; stageClear=1; }
		}
		if (getJugador1().getRyuY() < 50)
			getJugador1().setRyuY(50);
		if (getJugador1().getRyuY() > 190 - 64 && getJugador1().getRyuEstado()!="Hurt" && getJugador1().getRyuEstado()!="Dead" )
			getJugador1().setRyuY(190 - 64);

	//-----------------------------------------------------------------------------------------------
	//   PLAYER 2
	//-----------------------------------------------------------------------------------------------
		if (getJugador2().getRyuX() < 0)
			getJugador2().setRyuX(0);
		if (getJugador2().getRyuX() > 800 - 64)
			getJugador2().setRyuX(800 - 64);

		
//-----------------------------------------------------------------------------------------------
//   CAMINADO AUTOMATICO AL DERROTAR TODOS LOS ENEMIGOS
//-----------------------------------------------------------------------------------------------
	//   PLAYER 1
	//-------------------------------------------------------------------------------------------
	
		 if(enemigosOnScreen==0)
	  	  {
			if(posicionbosque>-640){ 	   
			 if(getJugador1().getRyuXScale()==false){getJugador1().setRyuEstado("AutoWalk");}
			 if(getJugador1().getRyuXScale()==true){getJugador1().setRyuEstado("ReverseWalk");}
			 getJugador1().setRyuLastFrame(11);
		     posicionbosque= posicionbosque - 30 * Gdx.graphics.getDeltaTime();
			}
			else{autoWalk=1;}
		  }
		  else
		  {
			if(getJugador1().getRyuEstado()=="AutoWalk" || getJugador1().getRyuEstado()=="ReverseWalk" ){getJugador1().setRyuEstado("Stance");}  
		  }
	
		 
//-----------------------------------------------------------------------------------------------
//   CONDUCTAS DEL ENEMIGO
//-----------------------------------------------------------------------------------------------		
	 //   CREACION DEL ENEMIGO
	 //-----------------------------------------------------------------------------------------------	 
		if ((TimeUtils.nanoTime() - lastEnemySpawn > ((1000000000/1)-(tiempo*1000000000/100))) && (tiempo < 120))
			if(enemigosOnScreen<3 && getPuertas().getEstadoPuerta()==0){ spawnEnemy(); }
		    Iterator<NinjaEnemigo> iter2 = arrayEnemy.iterator();
		while (iter2.hasNext()) {
			NinjaEnemigo oneEnemy = iter2.next();
	  //-----------------------------------------------------------------------------------------------
	  //   ENEMIGO CAYENDO
	  //-----------------------------------------------------------------------------------------------	
		        if (oneEnemy.getEnemyY() > 300 - oneEnemy.getAlturaCaida() && oneEnemy.getEnemyEstado()!="Fall" && oneEnemy.getEnemyEstado()!="Die" && oneEnemy.getEnemyYaSalto()==0){
		        	oneEnemy.setEnemyEstado("Salto");
		        	oneEnemy.setEnemyY(oneEnemy.getEnemyY() - 400 * Gdx.graphics.getDeltaTime());
	            }
		        else {
		          if (oneEnemy.getEnemyEstado()=="Salto") {
		           oneEnemy.setEnemyEstado("FinSalto");
		           oneEnemy.setEnemyY(oneEnemy.getEnemyY() - 0 * Gdx.graphics.getDeltaTime());
		          }
		//-----------------------------------------------------------------------------------------------
		//   ACERCARSE A PLAYER
	    //-----------------------------------------------------------------------------------------------
		          if (oneEnemy.getEnemyEstado()=="Stance" || oneEnemy.getEnemyEstado()=="Run") {
		        	if(getJugador1().getRyuX()<oneEnemy.getEnemyX()){ oneEnemy.setEnemyXscale(true); oneEnemy.setEnemyXscaleInt(-1);}  
		        	if(getJugador1().getRyuX()>oneEnemy.getEnemyX()){ oneEnemy.setEnemyXscale(false); oneEnemy.setEnemyXscaleInt(1);}
		        	
		            if(oneEnemy.getEnemyConducta()==1 && jugador1Ocupado==0){
		        	  if (getJugador1().getRyuX()+25 < oneEnemy.getEnemyX() && getJugador1().getRyuEstado()!="Hurt")
		              { 
		        	    oneEnemy.setEnemyX(oneEnemy.getEnemyX() - 35 * Gdx.graphics.getDeltaTime()); 
		                oneEnemy.setEnemyXscale(true);
		                oneEnemy.setEnemyXscaleInt(-1);
		                oneEnemy.setEnemyEstado("Run");
					    oneEnemy.setEnemyLastFrame(6);
		              }
		              if (getJugador1().getRyuX()-35 > oneEnemy.getEnemyX() && getJugador1().getRyuEstado()!="Hurt")
		              { 
		        	    oneEnemy.setEnemyX(oneEnemy.getEnemyX() + 35 * Gdx.graphics.getDeltaTime()); 
		        	    oneEnemy.setEnemyXscale(false);
		        	    oneEnemy.setEnemyXscaleInt(1);
		        	    oneEnemy.setEnemyEstado("Run");
					    oneEnemy.setEnemyLastFrame(6);
		              }	            
                      if (getJugador1().getRyuY() > oneEnemy.getEnemyY() && getJugador1().getRyuEstado()!="Hurt" && oneEnemy.getEnemyEstado()!="Fall" && oneEnemy.getEnemyEstado()!="Die")
			          { 
		        	    oneEnemy.setEnemyY(oneEnemy.getEnemyY() + 10 * Gdx.graphics.getDeltaTime()); 
		        	    oneEnemy.setEnemyEstado("Run");
		        	    oneEnemy.setEnemyLastFrame(6);
			          }  
		              if (getJugador1().getRyuY() < oneEnemy.getEnemyY() && getJugador1().getRyuEstado()!="Hurt" && oneEnemy.getEnemyEstado()!="Fall" && oneEnemy.getEnemyEstado()!="Die")
			          { 
		        	    oneEnemy.setEnemyY(oneEnemy.getEnemyY() - 10 * Gdx.graphics.getDeltaTime());
		        	    oneEnemy.setEnemyEstado("Run");
					    oneEnemy.setEnemyLastFrame(6);
		        	  }
		            }
		            if(oneEnemy.getEnemyConducta()==2){
			           if (getJugador1().getRyuX()+25 < oneEnemy.getEnemyX() && getJugador1().getRyuEstado()!="Hurt")
			             { 
			        	  oneEnemy.setEnemyX(oneEnemy.getEnemyX() - 0 * Gdx.graphics.getDeltaTime()); 
			              oneEnemy.setEnemyEstado("Stance");
						  oneEnemy.setEnemyLastFrame(6);
			             }
			           if (getJugador1().getRyuX()-25 > oneEnemy.getEnemyX() && getJugador1().getRyuEstado()!="Hurt")
			             { 
			        	  oneEnemy.setEnemyX(oneEnemy.getEnemyX() + 0 * Gdx.graphics.getDeltaTime()); 
			        	  oneEnemy.setEnemyEstado("Stance");
						  oneEnemy.setEnemyLastFrame(6);
			             }	            
			          }
		           }
		        }
				
				oneEnemy.getEnemyHitBox().y = oneEnemy.getEnemyY();
				oneEnemy.getEnemyHitBox().x = oneEnemy.getEnemyX()+((oneEnemy.getWidth()/2)-(oneEnemy.getEnemyHitBox().getWidth()/2));
			    
		//-----------------------------------------------------------------------------------------------
		//   ENEMIGOS SE DESTRUYEN EN PANTALLAS ESPECIALES
	    //-----------------------------------------------------------------------------------------------
			    if (stageClear==1){
			    	getJugador1().setRyuY(500);	
			    	iter2.remove();
			    	musicChasing.stop();
			    	musicUnexpected.play();			    	
		        }		    
			    if (titleScreen==1){
			    	iter2.remove();
			    	musicChasing.stop();
			    	musicUnexpected.stop();
		        }		    
			    if (oneEnemy.getEnemyVida() == -1){
			    	iter2.remove();
			    	getJugador1().setRyuKills(getJugador1().getRyuKills()+1);
			    	enemigosOnScreen = enemigosOnScreen-1;
		        }
				
			    
       //-----------------------------------------------------------------------------------------------
       //   PANTALLA GAME OVER
       //-----------------------------------------------------------------------------------------------

			    if (getJugador1().getRyuVidas() == 0)
			    {
			      getJugador1().setRyuY(500);
			      GameOver();
			    }
			    
			    if(getGameOver().getTics()==9 && getPuertas().getLetreroPuerta()==4)
			    {
			    getJugador1().setRyuY(500);	
			    GameOver();
			    }

			
//-----------------------------------------------------------------------------------------------
//   COLISIONES
//-----------------------------------------------------------------------------------------------			
	//  COLISION CON PLAYER 1
	//-----------------------------------------------------------------------------------------------	    
		  //  ENEMIGO DETECTA A JUGADOR Y ATACA    
		  //-----------------------------------------------------------------------------------------------
			    if (oneEnemy.getEnemyHitBox().overlaps(getJugador1().getRyuHitBox())) {
			    
			    	//if(oneEnemy.getEnemyX() - getJugador1().getRyuX()<22) {  oneEnemy.setEnemyX(oneEnemy.getEnemyX()-0); }
			    	//if(getJugador1().getRyuX() - oneEnemy.getEnemyX()<47) { oneEnemy.setEnemyX(oneEnemy.getEnemyX()+0); }
			    	
				if(oneEnemy.getEnemyEstado()=="Run")
				  {
					oneEnemy.setEnemyConducta(1);
					oneEnemy.setEnemyEstado("Ataque");
					oneEnemy.setEnemyFrame(0);
					oneEnemy.setEnemyLastFrame(23);
				  }
		  //-----------------------------------------------------------------------------------------------
		  //   ENEMIGO ATACA A JUGADOR
		  //-----------------------------------------------------------------------------------------------
				if(oneEnemy.getEnemyCombo()==1 && jugador1Ocupado==0)
				  {
					if(getJugador1().getRyuEstado()=="Stance" || getJugador1().getRyuEstado()=="Run" || getJugador1().getRyuEstado()=="Walk" ) {
					jugador1Ocupado=1;
					if(getJugador1().getRyuHP()>4) { getJugador1().setRyuEstado("Hurt"); getJugador1().setRyuLastFrame(27); }
					if(getJugador1().getRyuHP()<=3){ getJugador1().setRyuEstado("Dead"); getJugador1().setRyuLastFrame(100); }
					if(oneEnemy.getEnemyXscale()==false){getJugador1().setRyuXScale(true); getJugador1().setRyuXScaleInt(-1);}
					if(oneEnemy.getEnemyXscale()==true){getJugador1().setRyuXScale(false);getJugador1().setRyuXScaleInt(1);}
					getJugador1().setRyuFrame(0);
					}
				  }
		  //-----------------------------------------------------------------------------------------------
		  //   JUGADOR ATACA A ENEMIGO
		  //-----------------------------------------------------------------------------------------------
			    if(getJugador1().getRyuDamage()==1){
			      if(oneEnemy.getEnemyXscale()!=getJugador1().getRyuXScale()){
			    	if(getJugador1().getRyuCombo()<3){
				        oneEnemy.setEnemyEstado("BeingHit"); jugador1Ocupado=1;
				        if(getJugador1().getRyuXScale()==false){oneEnemy.setEnemyXscale(true); oneEnemy.setEnemyXscaleInt(-1);}
						if(getJugador1().getRyuXScale()==true){oneEnemy.setEnemyXscale(false); oneEnemy.setEnemyXscaleInt(1);}
				        oneEnemy.setEnemyFrame(0);
				        oneEnemy.setEnemyLastFrame(4);
				      }
				      if(getJugador1().getRyuCombo()==3){
					    if(oneEnemy.getEnemyVida()>0) { oneEnemy.setEnemyEstado("Fall"); jugador1Ocupado=0; }
					    if(oneEnemy.getEnemyVida()==0) { oneEnemy.setEnemyEstado("Die"); jugador1Ocupado=0; }
					    oneEnemy.setEnemyFrame(0);
					    oneEnemy.setEnemyLastFrame(27);
					  }
				    }

			    }
		    
			    ;	
		
			}
	//-----------------------------------------------------------------------------------------------
	//  COLISION CON JUGADOR 2
	//-----------------------------------------------------------------------------------------------
			if (oneEnemy.getEnemyHitBox().overlaps(getJugador2().getRyuHitBox())) {		
			    //oneEnemy.getDropSound().play();
			    iter2.remove();
			}
		}
	}

//-----------------------------------------------------------------------------------------------
//  MÉTODOS DEFAULT LIMPIEZA Y DEMÁS
//-----------------------------------------------------------------------------------------------	
	@Override
	public void dispose() {
		musicChasing.dispose();
    	musicGameStart.dispose();
	    musicUnexpected.dispose();
	    batch.dispose();
	    fuente.dispose();
	    bosque1.dispose();
	    swTitle.dispose();
	    fondoHP.dispose();
	    androidPad01.dispose();
	    androidPad02.dispose();
	    androidPad03.dispose();
	    androidPad.dispose();
	    letreroFight.dispose();
	    letreroGameOver.dispose();
	    letreroGo.dispose();
	    letreroStageClear.dispose();
	    puertaIzq.dispose();
	    puertaDer.dispose();
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

//-----------------------------------------------------------------------------------------
// MÉTODOS MIOS
//-----------------------------------------------------------------------------------------
	public Jugador getJugador2() { return jugador2; }
	public Jugador getJugador1() { return jugador1; }
	//-----------------------------------------------------------------------------------------
	public Respawn getRespawn() { return ControlRespawn; }
	public CambioPantalla getPuertas() { return Puertas; }
	public PantallaGameOver getGameOver() { return GameOver; }
	//-----------------------------------------------------------------------------------------
    public void GameOver() {
      getPuertas().setDibujarLetrero(1);		      
	  getPuertas().setTics(0);
	  if(creditos>0 && getPuertas().getEstadoPuerta()==0){
	    creditos=creditos-1;
	    getGameOver().setTics(0);
	    getPuertas().setEstadoPuerta(1);
	    getPuertas().setLetreroPuerta(4);
	   }
	  if(creditos<=0 && getPuertas().getEstadoPuerta()==0){
		gameOver=1;
		musicChasing.stop();
	    soundGong.play();
	    getPuertas().setEstadoPuerta(1);  
	    getPuertas().setLetreroPuerta(2);
	    titleScreen=-1;
	    getGameOver().setTimer(99);
	    creditos=3;
	   }   
    }
}
