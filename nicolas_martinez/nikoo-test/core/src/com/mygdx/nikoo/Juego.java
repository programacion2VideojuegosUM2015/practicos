package com.mygdx.nikoo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ScreenViewport;


public class Juego implements Screen {
		  final Balloon game;
		  
		  
	//efectos de sonido	  
	public static Sound pausa;
	public static Sound explota;
	public static Sound rayo;
	public static Sound fly;
	public static Sound muere;
	public static Sound splash;
	public static Sound tic;
	public static Sound chocan;
	public static Sound tocaburbuja;
	public static Sound land;
	public static Sound derrota;
	public static Sound burbuja;
	public static Sound pez;
	
	//musicas
	public static Music b001;
	public static Music b002;
	public static Music b003;
	public static Music b004;
	public static Music b005;
	public static Music b006;
	public static Music b007;
	public static Music b008;
	
	
		  
	private Box2DDebugRenderer renderizardebug;//renderizamos las colisiones
	
	private BitmapFont nes;//fuentes
	
	
	
	float time = 0;
	boolean clear = false;//pasar nivel
	boolean over = false;//perder todas las vidas
	
	public static int totalEnemigos = -1;
	
	
	public static ScreenViewport viewport;
	
    public static Stage stage;
    
    public Array<Body> cuerposmundo; // array que contiene todos los cuerpos creados en el mundo box2D
	
	//cargar el nivel
    GeneradorNivel genLvl = new GeneradorNivel();
    
    //cargar el jugador
    Jugador player ;
    
    //enemigos
    Enemigo enem ;
    
    
	public static int mapWidth; //celdas de ancho del mapa actual
    public static int mapHeight; // celdas de alto del mapa actual
    
    
		  
		  
	public Juego(final Balloon gam) {
		this.game = gam;
		
		renderizardebug = new Box2DDebugRenderer();	
		
		
		
		viewport = new ScreenViewport();

		//crear la vista y el nivel
		
		stage = new Stage(viewport);
		Gdx.input.setInputProcessor(stage);
		
		//Añadir al jugador al nivel
		
		player = new Jugador();
		stage.addActor(player);
		stage.setKeyboardFocus(player);
		
		
		ponerEnemigos();
		
		//musicas
		b001 = Gdx.audio.newMusic(Gdx.files.internal("ost/01-balloon-trip.mp3")); 
		b001.setLooping(true);
		
		b002 = Gdx.audio.newMusic(Gdx.files.internal("ost/02-game-start.mp3")); 
		b002.setLooping(false);
		
		b003 = Gdx.audio.newMusic(Gdx.files.internal("ost/03-parachutes-away.mp3")); 
		b003.setLooping(true);
		
		b004 = Gdx.audio.newMusic(Gdx.files.internal("ost/04-perfect-bonus.mp3")); 
		b004.setLooping(false);
		
		b005 = Gdx.audio.newMusic(Gdx.files.internal("ost/05-gulp.mp3")); 
		b005.setLooping(false);
		
		b006 = Gdx.audio.newMusic(Gdx.files.internal("ost/06-try-again.mp3")); 
		b006.setLooping(false);
		
		b007 = Gdx.audio.newMusic(Gdx.files.internal("ost/07-stage-clear.mp3")); 
		b007.setLooping(false);
		
		b008 = Gdx.audio.newMusic(Gdx.files.internal("ost/08-game-over.mp3")); 
		b008.setLooping(false);
		
		//sonidos
		pausa = Gdx.audio.newSound(Gdx.files.internal("sounds/01.mp3"));
		explota = Gdx.audio.newSound(Gdx.files.internal("sounds/02.mp3"));
		rayo = Gdx.audio.newSound(Gdx.files.internal("sounds/03.mp3"));
		fly = Gdx.audio.newSound(Gdx.files.internal("sounds/04.mp3"));
		muere = Gdx.audio.newSound(Gdx.files.internal("sounds/05.mp3"));
		splash = Gdx.audio.newSound(Gdx.files.internal("sounds/06.mp3"));
		tic = Gdx.audio.newSound(Gdx.files.internal("sounds/07.mp3"));
		chocan = Gdx.audio.newSound(Gdx.files.internal("sounds/08.mp3"));
		tocaburbuja = Gdx.audio.newSound(Gdx.files.internal("sounds/09.mp3"));
		land = Gdx.audio.newSound(Gdx.files.internal("sounds/10.mp3"));
		derrota = Gdx.audio.newSound(Gdx.files.internal("sounds/11.mp3"));
		burbuja = Gdx.audio.newSound(Gdx.files.internal("sounds/12.mp3"));
		pez = Gdx.audio.newSound(Gdx.files.internal("sounds/13.mp3"));
		

		//fuentes
		
		nes = new BitmapFont(Gdx.files.internal("nes.fnt"), Gdx.files.internal("nes.png"), false);
	}
	
	
	

	private void ponerEnemigos() {
		
		
		for (int x = 0; x < GeneradorNivel.listaenemigos.size; x++) {
			
			enem = new Enemigo();
			stage.addActor(enem);
			
			enem.getCuerpo().setTransform(GeneradorNivel.listaenemigos.get(x).getPosX()/GameWorld.units, GeneradorNivel.listaenemigos.get(x).getPosY()/GameWorld.units, 0);
			enem.setName("Enemigo"+x);
		}
		
		totalEnemigos = GeneradorNivel.listaenemigos.size;
		
	}




	@Override
	public void render(float delta) {
		
	Gdx.gl.glClearColor(0f, 0f, 0f, 1);
	Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	
	// tell the camera to update its matrices.
	Balloon.camera.update();
	Balloon.camaramundo.update();
	Balloon.hudcam.update();
		
	game.batch.setProjectionMatrix(Balloon.hudcam.combined);
	game.bh.setProjectionMatrix(Balloon.camera.combined);
	
	
	//mapa de tiles (fondo)
	
	genLvl.getTileMapRenderer().setView(Balloon.camera);
	genLvl.getTileMapRenderer().render();	
		
	// stage
	
	stage.act(Gdx.graphics.getDeltaTime());
	stage.getViewport().setCamera(Balloon.camera);
	stage.draw();
	

	//agua
	
	genLvl.getAguaRenderer().setView(Balloon.camera);
	genLvl.getAguaRenderer().render();	
		
		
		

	
	
	
			
	//estado de la partida:hud
	game.batch.begin();
	
	
	//vidas
	nes.draw(game.batch, "Vidas: " + Balloon.vidas , 5, Balloon.SCREEN_HEIGHT-5);
	
	//puntos
	nes.draw(game.batch, "Puntos: " + Balloon.puntos , Balloon.SCREEN_WIDTH/2, Balloon.SCREEN_HEIGHT-5);
	
	//nivel
	nes.draw(game.batch, "Nivel: " + Balloon.nivel, 5, Balloon.SCREEN_HEIGHT-15);
	
	
	
	if(player.isGameOver()==true){
		
		nes.draw(game.batch, "Game Over" , (Balloon.SCREEN_WIDTH/2)-37, (Balloon.SCREEN_HEIGHT/2)+40);
		
	}
	

	
	
	game.batch.end();
	
	
	
	
	
	//activar o desactivar el debug de box 2D 
	if(Balloon.debugmode == false && (Gdx.input.isKeyJustPressed(Keys.C) && Gdx.input.isKeyPressed(Keys.CONTROL_LEFT) ) ){
		Balloon.debugmode = true;
	}
			
	else if(Balloon.debugmode == true && (Gdx.input.isKeyJustPressed(Keys.C) && Gdx.input.isKeyPressed(Keys.CONTROL_LEFT) ) ){
		Balloon.debugmode = false;
	}
	
		
	//Renderizar las colisiones y fisicas del box 2D 
	if(Balloon.debugmode == true){ 
	renderizardebug.render(GameWorld.mundo, Balloon.camaramundo.combined); 	

	}
			
	/*resetear nivel
	
	if(Gdx.input.isKeyJustPressed(Keys.R)){
		
		resetLevel();	
		
	}*/
	
	
	
	
	//nivel clear
	
	time += Gdx.graphics.getDeltaTime();
	
	if(totalEnemigos == 0 && clear == false){
		time = 0;	
		clear = true;
	}
	
	if(totalEnemigos == 0 && clear == true){
		
		if(time == 0){
			b007.play();	
		}
		
		
		
		if(time >= 2.5f){ 
		
		
		if(Balloon.nivel==1){
		GeneradorNivel.mapa = GeneradorNivel.nivel2;
		}
		
		if(Balloon.nivel==2){
		GeneradorNivel.mapa = GeneradorNivel.nivel3;
		}
		
		if(Balloon.nivel==3){
		GeneradorNivel.mapa = GeneradorNivel.nivel4;
		}
		
		if(Balloon.nivel==4){
		GeneradorNivel.mapa = GeneradorNivel.nivel5;
		}
		
		if(Balloon.nivel==5){
		GeneradorNivel.mapa = GeneradorNivel.nivel6;
		}
		
		if(Balloon.nivel==6){
		GeneradorNivel.mapa = GeneradorNivel.nivel7;
		}
		
		
		Balloon.nivel ++;	
		resetLevel();	
		}
		
	}
	
	
	
	
	//game over
	
	if(player.isGameOver()==true && over == false){
		time = 0;	
		over = true;
		b002.stop();
	}
	
	if(player.isGameOver()==true && over == true){
		
		if(time >= 10f){ 
			resetLevel();	
		}
		
	}
	
	
		
		
	// Que la camara siga al personaje
		
	mapWidth = genLvl.getPropiedades().get("width", Integer.class);
	mapHeight = genLvl.getPropiedades().get("height", Integer.class);
	    

	int tilePixelWidth = genLvl.getPropiedades().get("tilewidth", Integer.class);
	int tilePixelHeight = genLvl.getPropiedades().get("tileheight", Integer.class);

	int mapPixelWidth = mapWidth * tilePixelWidth;
	int mapPixelHeight = mapHeight * tilePixelHeight;

	float cameraHalfWidth = Balloon.camera.viewportWidth/2;
	float cameraHalfHeight = Balloon.camera.viewportHeight/2;
		
	//la camara sigue al personaje
		
	Balloon.camera.position.x = Math.round(player.getX() * tilePixelWidth) / tilePixelWidth;
	//Bomb.camera.position.y = Math.round((player.getY()+200) * tilePixelHeight) / tilePixelHeight;
		
	
	// limites de la camara
		
	Balloon.camera.position.x = MathUtils.clamp(Balloon.camera.position.x, cameraHalfWidth, mapPixelWidth - cameraHalfWidth);
	//Bomb.camera.position.y = MathUtils.clamp(Bomb.camera.position.y, cameraHalfHeight, mapPixelHeight - cameraHalfHeight);

	

	//que la camara del mundo sigan a la camara tambien		
	Balloon.camaramundo.position.set(Balloon.camera.position.x/GameWorld.units,Balloon.camera.position.y/GameWorld.units,Balloon.camera.position.z/GameWorld.units);
	
		
	
	
	//step del mundo (60 cuadros por segundo, 8 iteraciones de velocidad , 6 iteraciones de posicion)
	
	GameWorld.mundo.step(1/60f, 8, 6);
			
			
			
			

	}
	
	
	
	
	
	

	private void resetLevel() {
		
		
		//destruye todos los cuerpos del mundo
				cuerposmundo = new Array<Body>();
				GameWorld.mundo.getBodies(cuerposmundo);
						

				for (int x = 0; x < cuerposmundo.size; x++) { 
					
				//fixtures = colisiones (detectar)	
				Array<Fixture> fixjugador;
				fixjugador = new Array<Fixture>();
				fixjugador = cuerposmundo.get(x).getFixtureList();
				
				for (int y = 0; y < fixjugador.size; y++) {
					
					cuerposmundo.get(x).destroyFixture(fixjugador.get(y));
					
				}
				
				
				GameWorld.mundo.destroyBody(cuerposmundo.get(x));
				}
				
				
				
				b002.stop();
				
				if(over == false){
					game.setScreen(new Juego(game));	
				}
				else{
					
					Balloon.puntos = 0;
					Balloon.nivel = 1;
					Balloon.vidas = 3;
					GeneradorNivel.mapa = GeneradorNivel.nivel1;
					
					game.setScreen(new TitleScreen(game));
					
					
					
					
				}
					
				
				
				
		
	}




	@Override
	public void show() {
	
		b002.play();

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

	@Override
	public void hide() {
		

	}

	@Override
	public void dispose() {
		

		b002.dispose();
		
		pausa.dispose();
		explota.dispose();
		rayo.dispose();
		fly.dispose();
		muere.dispose();
		splash.dispose();
		tic.dispose();
		chocan.dispose();
		tocaburbuja.dispose();
		land.dispose();
		derrota.dispose();
		burbuja.dispose();
		pez.dispose();
		
		
		

	}

}
