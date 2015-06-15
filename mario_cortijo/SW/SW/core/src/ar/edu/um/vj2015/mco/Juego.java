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
	private SpriteBatch batch;
	private OrthographicCamera camera;
	private BitmapFont fuente;
	Array<NinjaEnemigo> arrayEnemy;
	long lastEnemySpawn;
	long tiempo;
	private Texture bosque1;
	private Texture swTitle;
	private int posicionbosque;
	private int animataque;
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
	private Texture puertaIzq;
	private Texture puertaDer;
	private int enemigosOnScreen;
	public int jugador1Ocupado;
	public int stageClear;
	public int firstTime = 0;
	static public int titleScreen = 1;
	
	
	static Jugador jugador2 = new Jugador();
	static Jugador jugador1 = new Jugador();
	static Respawn ControlRespawn = new Respawn();
	static CambioPantalla Puertas = new CambioPantalla();

	
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
		androidPad = new Texture(Gdx.files.internal("interfaz/androidPad01.png"));
		puertaIzq = new Texture(Gdx.files.internal("interfaz/puertaIzq.png"));
		puertaDer = new Texture(Gdx.files.internal("interfaz/puertaDer.png"));
		letreroFight = new Texture(Gdx.files.internal("interfaz/letreroFight.png"));
		letreroGameOver = new Texture(Gdx.files.internal("interfaz/letreroGameOver.png"));
		letreroGo = new Texture(Gdx.files.internal("interfaz/letreroGo.png"));
		letreroStageClear = new Texture(Gdx.files.internal("interfaz/letreroStageClear.png"));
		enemigosOnScreen = 0;
		jugador1Ocupado = 0;
		stageClear=0;
		// MUSICA SONIDO Y FUENTE
		musicChasing = Gdx.audio.newMusic(Gdx.files.internal("musica/chasing.mp3"));
		musicGameStart = Gdx.audio.newMusic(Gdx.files.internal("musica/gameStart.mp3"));
		musicUnexpected = Gdx.audio.newMusic(Gdx.files.internal("musica/unexpected.mp3"));	
		fuente = new BitmapFont(Gdx.files.internal("interfaz/yiear.fnt"),Gdx.files.internal("interfaz/yiear.png"), false);
		// REPRODUZCO LA MUSICA
		musicChasing.setLooping(true);
		musicUnexpected.setLooping(true);
		musicGameStart.setLooping(false);
		if(titleScreen==1){musicGameStart.play();}
		if(titleScreen!=1){musicChasing.play();}
		

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
		  oneEnemy.setDropTipo(MathUtils.random(0,800));
		  oneEnemy.setAlturaCaida(MathUtils.random(140,210));
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
		//PLAYER 1	
		batch.draw(getJugador1().getRyuCurrentFrame(), getJugador1().getRyuX(), getJugador1().getRyuY()-32, 18, 0, 58, 58, 1, 1, 0, 0, 0, 58, 58, getJugador1().getRyuXScale(), false);
		//DEBUG TEST DRAW HITBOXES
		//batch.setColor(1.0f, 1.0f, 1.0f,0.5f);	
		//batch.draw(pixelNegro, getJugador1().getRyuX(), getJugador1().getRyuY());
		//batch.setColor(1.0f, 1.0f, 1.0f,1);
		
		//DIBUJO ENEMIGOS
		for (NinjaEnemigo oneEnemy : arrayEnemy) {
			if(oneEnemy.getEnemyXscale()==false){batch.draw(oneEnemy.getNinjaCurrentFrame(), oneEnemy.getEnemyX()+22, oneEnemy.getEnemyY()-32, 18, 0, 58, 58, 1, 1, 0, 0, 0, 58, 58, oneEnemy.getEnemyXscale(), false);}  
			if(oneEnemy.getEnemyXscale()==true){batch.draw(oneEnemy.getNinjaCurrentFrame(), oneEnemy.getEnemyX()-2, oneEnemy.getEnemyY()-32, 18, 0, 58, 58, 1, 1, 0, 0, 0, 58, 58, oneEnemy.getEnemyXscale(), false);}
			//TEST DRAW HITBOXES
			//batch.setColor(1.0f, 1.0f, 1.0f,0.5f);
			//batch.draw(pixelNegro,oneEnemy.getEnemyX(), oneEnemy.getEnemyY());
			//batch.setColor(1.0f, 1.0f, 1.0f,1);
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
		//fuente.draw(batch, getJugador1().getRyuTESTstring(), 10, 240); 

		//CONTROLES TOUCH
		if(Gdx.app.getType() == ApplicationType.Android){
		  batch.draw(androidPad, 10, 8);
		}
		
		//CARTEL GO
		if(enemigosOnScreen==0 && getRespawn().getParpadeoGo()==1) {batch.draw(letreroGo, 240, 130);}

		//PANTALLA DE TITULO
		if(titleScreen>0){
			batch.draw(swTitle, 0, 0);
			if(getRespawn().getParpadeoGo()==1){
				if(Gdx.app.getType() == ApplicationType.Desktop){fuente.draw(batch, " - Press X to Start -", 90, 40);}
				if(Gdx.app.getType() == ApplicationType.Android){fuente.draw(batch, " - Touch here to Start -", 90, 40);}
			}
		}
		
		//PUERTAS
		batch.draw(puertaIzq,getPuertas().getXIzq(),0);
		batch.draw(puertaDer,getPuertas().getXDer(),0);
		if(getPuertas().getDibujarLetrero()==1){
		  if(getPuertas().getLetreroPuerta()==1){batch.draw(letreroFight, 10, 8);} 
		  if(getPuertas().getLetreroPuerta()==2){batch.draw(letreroGameOver, 10, 8); if(getRespawn().getParpadeoGo()==1){
			  if(Gdx.app.getType() == ApplicationType.Desktop){fuente.draw(batch, " - Press X to replay level -", 60, 50);}
			  if(Gdx.app.getType() == ApplicationType.Android){fuente.draw(batch, " - Touch here to replay level -", 60, 50);}		  
			  }
		  } 
		}
        
		//STAGECLEAR
		if(stageClear==1) {
			batch.draw(letreroStageClear, 10, 60);
			if(getJugador1().getRyuScore()==0){
				getJugador1().setRyuScore(getJugador1().getRyuKills()+getJugador1().getRyuVidas()+getJugador1().getRyuHP());
				getJugador1().setRyuScore(getJugador1().getRyuScore()*100);
			}
			
			if(getPuertas().getEstadoPuerta()!=1){getPuertas().setEstadoPuerta(1);}
			 fuente.draw(batch,"ninja x " + getJugador1().getRyuKills() + " ----- " + getJugador1().getRyuKills() * 100 + " points", 60, 130);
			 fuente.draw(batch, "lives x " + getJugador1().getRyuVidas() + " ----- " + getJugador1().getRyuVidas() * 100 + " points", 60, 110);
			 fuente.draw(batch, "health x " + getJugador1().getRyuHP() + " ----- " + getJugador1().getRyuHP() * 100 + " points", 60, 90);		 
			 fuente.draw(batch, "total ----- " + getJugador1().getRyuScore() + " points", 60, 70);
			 if(getRespawn().getParpadeoGo()==1){
                  if(Gdx.app.getType() == ApplicationType.Desktop){fuente.draw(batch, " - Press X to replay level -", 60, 35);}
 				  if(Gdx.app.getType() == ApplicationType.Android){fuente.draw(batch, " - Touch here to replay level -", 60, 35);}
				 }
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
			if(touchPos.x>267 && touchPos.y>31 && touchPos.x<298 && touchPos.y<62)
			{
			  animataque = MathUtils.random(1,2);
			  androidPad = androidPad02;
			  if(getJugador1().getRyuEstado()=="Stance"){
			    if (animataque==1) {getJugador1().setRyuEstado("Punch");}
			    if (animataque==2) {getJugador1().setRyuEstado("Kick");}
			    getJugador1().setRyuLastFrame(4);
			    getJugador1().setRyuFrame(0);
		    }
		  }		
		}
		else {androidPad = androidPad01;}
	//---------------------------------------------------------------------------------------------------
	//   BOTON MENUES
	//---------------------------------------------------------------------------------------------------
		if (Gdx.input.isTouched()) {
		  Vector3 touchPos = new Vector3();
		  touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
	      camera.unproject(touchPos);
		if(touchPos.x>40 && touchPos.y>20 && touchPos.x<280 && touchPos.y<60)
		{
		  if(stageClear==1 || getJugador1().getRyuVidas() == 0 || titleScreen==1) {
		    getPuertas().setEstadoPuerta(1);
		    getPuertas().setTics(0);
		    //if(getJugador1().getRyuVidas() == 0){titleScreen=1;}
		    //else{titleScreen=2;}
		    titleScreen=2;
	        musicUnexpected.stop();
	        musicChasing.stop();
		    create();
		    getJugador1().resetJugador();
		    getRespawn().setTics(0);
		  }
		 }
		}
		else {androidPad = androidPad01;}
	//---------------------------------------------------------------------------------------------------
	// BOTON LEFT
	//---------------------------------------------------------------------------------------------------
		if (Gdx.input.isTouched()) {
		  Vector3 touchPos = new Vector3();
		  touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
	      camera.unproject(touchPos);	
		  if(touchPos.x>10 && touchPos.y>28 && touchPos.x<30 && touchPos.y<48)
		  {
		    androidPad = androidPad03;
		    getJugador1().setRyuXScale(true); getJugador1().setRyuXScaleInt(-1);
		    getJugador1().setRyuEstado("Run");
		    getJugador1().setRyuLastFrame(6);
		    getJugador1().setRyuX(getJugador1().getRyuX() - 150 * Gdx.graphics.getDeltaTime());
		    jugador1Ocupado = 0;
		 }
	   }		
	   else {androidPad = androidPad01;}
	//---------------------------------------------------------------------------------------------------
	// BOTON RIGHT
	//---------------------------------------------------------------------------------------------------
		if (Gdx.input.isTouched()) {
			  Vector3 touchPos = new Vector3();
			  touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
		      camera.unproject(touchPos);	
			  if(touchPos.x>49 && touchPos.y>28 && touchPos.x<69 && touchPos.y<48)
			  {
				androidPad = androidPad03;
				getJugador1().setRyuXScale(false);	getJugador1().setRyuXScaleInt(1);
				getJugador1().setRyuEstado("Run");
				getJugador1().setRyuLastFrame(6);
				getJugador1().setRyuX(getJugador1().getRyuX() + 150 * Gdx.graphics.getDeltaTime());
				jugador1Ocupado = 0;
				
				//TEST AVANZAR NIVEL
				if(getJugador1().getRyuX()>160 && enemigosOnScreen==0)
			  	{
				  getJugador1().setRyuX(160);
				  if(posicionbosque>-640) { posicionbosque=posicionbosque-1;}	
				}
			  }
		   }		
		   else {androidPad = androidPad01;}
	//---------------------------------------------------------------------------------------------------
	// BOTON DOWN
	//---------------------------------------------------------------------------------------------------
		if (Gdx.input.isTouched()) {
			  Vector3 touchPos = new Vector3();
			  touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
		      camera.unproject(touchPos);	
			  if(touchPos.x>30 && touchPos.y>7 && touchPos.x<50 && touchPos.y<27)
			  {
				androidPad = androidPad03;
				getJugador1().setRyuEstado("Walk");
				getJugador1().setRyuLastFrame(11);
				getJugador1().setRyuY(getJugador1().getRyuY() - 40 * Gdx.graphics.getDeltaTime());
				jugador1Ocupado = 0;
			  }
		   }		
		   else {androidPad = androidPad01;}
	//---------------------------------------------------------------------------------------------------
	// BOTON UP
	//---------------------------------------------------------------------------------------------------
		if (Gdx.input.isTouched()) {
			  Vector3 touchPos = new Vector3();
			  touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
		      camera.unproject(touchPos);	
			  if(touchPos.x>30 && touchPos.y>48 && touchPos.x<50 && touchPos.y<58)
			  {
				androidPad = androidPad03;
				getJugador1().setRyuEstado("Walk");
				getJugador1().setRyuLastFrame(11);
				getJugador1().setRyuY(getJugador1().getRyuY() + 40 * Gdx.graphics.getDeltaTime());
				jugador1Ocupado = 0;
			  }
		   }		
		   else
		   {
			androidPad = androidPad01;			   
		   }
		
//---------------------------------------------------------------------------------------------------
//   CONTROLES TECLADO 
//---------------------------------------------------------------------------------------------------
	//   PLAYER 1
	//-----------------------------------------------------------------------------------------------
	//   BOTON LEFT
	//-----------------------------------------------------------------------------------------------
		if (Gdx.input.isKeyPressed(Keys.LEFT)) {
			if(getJugador1().getRyuEstado()!="Hurt" || getJugador1().getRyuEstado()!="Dead" ){
			  getJugador1().setRyuXScale(true); getJugador1().setRyuXScaleInt(-1);
			  getJugador1().setRyuEstado("Run");
			  getJugador1().setRyuLastFrame(6);
			  getJugador1().setRyuX(getJugador1().getRyuX() - 150 * Gdx.graphics.getDeltaTime());
			  jugador1Ocupado = 0;
			}
		}
	//-----------------------------------------------------------------------------------------------
    //   BOTON RIGHT
	//-----------------------------------------------------------------------------------------------		
		if (Gdx.input.isKeyPressed(Keys.RIGHT)){
			if(getJugador1().getRyuEstado()!="Hurt" || getJugador1().getRyuEstado()!="Dead" ){  
			  getJugador1().setRyuXScale(false); getJugador1().setRyuXScaleInt(1);
			  getJugador1().setRyuEstado("Run");
			  getJugador1().setRyuLastFrame(6);
			  getJugador1().setRyuX(getJugador1().getRyuX() + 150 * Gdx.graphics.getDeltaTime());
			  jugador1Ocupado = 0;			
			  //PARA AVANZAR EN EL NIVEL
			  if(getJugador1().getRyuX()>160 && enemigosOnScreen==0)
		  	  {
			    getJugador1().setRyuX(160);
			    if(posicionbosque>-640) { posicionbosque=posicionbosque-1;}	
			  }
			  
			}
		}
	//-----------------------------------------------------------------------------------------------
	//   BOTON DOWN
	//-----------------------------------------------------------------------------------------------	
		if (Gdx.input.isKeyPressed(Keys.DOWN)){
			if(getJugador1().getRyuEstado()!="Hurt" || getJugador1().getRyuEstado()!="Dead" ){  
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
			if(getJugador1().getRyuEstado()!="Hurt" || getJugador1().getRyuEstado()!="Dead" ){
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
	//   BOTON DOWN
	//-----------------------------------------------------------------------------------------------	
		if (Gdx.input.isKeyJustPressed(Keys.X)){
			if(stageClear==1 || getJugador1().getRyuVidas() == 0 || titleScreen==1) {
	         getPuertas().setEstadoPuerta(1);
	         getPuertas().setTics(0);
	         //if(getJugador1().getRyuVidas() == 0){titleScreen=1;}
	         //else{titleScreen=2;}
	         titleScreen=2;
             musicUnexpected.stop();
             musicChasing.stop();
	         create();
	         getJugador1().resetJugador();
	         getRespawn().setTics(0);
			}
		}
		
		//SOLO PARA ACOMODAR LOS FRAMES
		if (Gdx.input.isKeyJustPressed(Keys.LEFT)) {getJugador1().setRyuFrame(0);}    
		if (Gdx.input.isKeyJustPressed(Keys.RIGHT)){getJugador1().setRyuFrame(0);}
		if (Gdx.input.isKeyJustPressed(Keys.DOWN)){getJugador1().setRyuFrame(0);}
		if (Gdx.input.isKeyJustPressed(Keys.UP)){getJugador1().setRyuFrame(0);}

		
//-----------------------------------------------------------------------------------------------
//   COMANDOS DEBUG
//-----------------------------------------------------------------------------------------------
		//if (Gdx.input.isKeyJustPressed(Keys.D)){ getJugador1().setRyuVidas(0); }
		//if (Gdx.input.isKeyJustPressed(Keys.K)){ posicionbosque=-640; enemigosOnScreen=5; }

//-----------------------------------------------------------------------------------------------
//   LIMITES MOVIENTO JUGADOR
//-----------------------------------------------------------------------------------------------
	//   PLAYER 1
	//-------------------------------------------------------------------------------------------
		if (getJugador1().getRyuX() < 0)
			getJugador1().setRyuX(0);
		
		if(posicionbosque!=-640){
		  if (getJugador1().getRyuX() > 360 - 64)
			getJugador1().setRyuX(360 - 64);
		}
		if(posicionbosque==-640){
		  if (getJugador1().getRyuX() > 340) { enemigosOnScreen=5; stageClear=1; }
		}
		if (getJugador1().getRyuY() < 20)
			getJugador1().setRyuY(20);
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
//   CONDUCTAS DEL ENEMIGO
//-----------------------------------------------------------------------------------------------		
	 //   CREACION DEL ENEMIGO
	 //-----------------------------------------------------------------------------------------------	 
		if ((TimeUtils.nanoTime() - lastEnemySpawn > ((1000000000/1)-(tiempo*1000000000/100))) && (tiempo < 120))
			if(enemigosOnScreen<3){ spawnEnemy(); }
		    Iterator<NinjaEnemigo> iter2 = arrayEnemy.iterator();
		while (iter2.hasNext()) {
			NinjaEnemigo oneEnemy = iter2.next();
	  //-----------------------------------------------------------------------------------------------
	  //   ENEMIGO CAYENDO
	  //-----------------------------------------------------------------------------------------------	
		        if (oneEnemy.getEnemyY() > 300 - oneEnemy.getAlturaCaida() && oneEnemy.getEnemyEstado()!="Fall" && oneEnemy.getEnemyEstado()!="Die"){
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
		            if(oneEnemy.getEnemyConducta()==1 && jugador1Ocupado==0){
		        	  if (getJugador1().getRyuX()+25 < oneEnemy.getEnemyX() && getJugador1().getRyuEstado()!="Hurt")
		              { 
		        	    oneEnemy.setEnemyX(oneEnemy.getEnemyX() - 35 * Gdx.graphics.getDeltaTime()); 
		                oneEnemy.setEnemyXscale(true);
		                oneEnemy.setEnemyXscaleInt(-1);
		                oneEnemy.setEnemyEstado("Run");
					    oneEnemy.setEnemyLastFrame(6);
		              }
		              if (getJugador1().getRyuX()-25 > oneEnemy.getEnemyX() && getJugador1().getRyuEstado()!="Hurt")
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
				
				oneEnemy.getDropHitBox().y = oneEnemy.getEnemyY();
				oneEnemy.getDropHitBox().x = oneEnemy.getEnemyX()+((oneEnemy.getWidth()/2)-(oneEnemy.getDropHitBox().getWidth()/2));
			    
		//-----------------------------------------------------------------------------------------------
		//   ENEMIGOS SE DESTRUYEN EN PANTALLAS ESPECIALES
	    //-----------------------------------------------------------------------------------------------
			    if (stageClear==1){
			    	iter2.remove();
			    	musicChasing.stop();
			    	musicUnexpected.play();			    	
		        }		    
			    if (titleScreen==1){
			    	iter2.remove();	    	
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
			    { getPuertas().setEstadoPuerta(1); getPuertas().setLetreroPuerta(2); getPuertas().setTics(0);}
			
//-----------------------------------------------------------------------------------------------
//   COLISIONES
//-----------------------------------------------------------------------------------------------			
	//  COLISION CON PLAYER 1
	//-----------------------------------------------------------------------------------------------	    
		  //  ENEMIGO DETECTA A JUGADOR Y ATACA    
		  //-----------------------------------------------------------------------------------------------
			    if (oneEnemy.getDropHitBox().overlaps(getJugador1().getRyuHitBox())) {
				if(oneEnemy.getEnemyEstado()=="Run")
				  {
					oneEnemy.setEnemyEstado("Ataque");
					oneEnemy.setEnemyFrame(0);
					oneEnemy.setEnemyLastFrame(23);
				  }
		  //-----------------------------------------------------------------------------------------------
		  //   ENEMIGO ATACA A JUGADOR
		  //-----------------------------------------------------------------------------------------------
				if(oneEnemy.getEnemyCombo()==1 && getJugador1().getRyuEstado()=="Stance" && jugador1Ocupado==0)
				  {
					jugador1Ocupado=1;
					if(getJugador1().getRyuHP()>4) { getJugador1().setRyuEstado("Hurt"); getJugador1().setRyuLastFrame(27); }
					if(getJugador1().getRyuHP()<=3){ getJugador1().setRyuEstado("Dead"); getJugador1().setRyuLastFrame(40); }
					if(oneEnemy.getEnemyXscale()==false){getJugador1().setRyuXScale(true); getJugador1().setRyuXScaleInt(-1);}
					if(oneEnemy.getEnemyXscale()==true){getJugador1().setRyuXScale(false);getJugador1().setRyuXScaleInt(1);}
					getJugador1().setRyuFrame(0);
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
			if (oneEnemy.getDropHitBox().overlaps(getJugador2().getRyuHitBox())) {		
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
	//-----------------------------------------------------------------------------------------
	public int getJugador1Ocupado() { return jugador1Ocupado; }
	public void setJugador1Ocupado(int jugador1Ocupado) { this.jugador1Ocupado = jugador1Ocupado; }
	//------------------------------------------------------------------------------------------

}
