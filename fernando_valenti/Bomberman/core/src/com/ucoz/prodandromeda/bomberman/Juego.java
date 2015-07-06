package com.ucoz.prodandromeda.bomberman;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ScreenViewport;


public class Juego implements Screen {
		  final Bomb game;
		  
	public static Sound pisada1;
	public static Sound pisada2;
	public static Sound bomba;
	public static Sound powerup;
	public static Sound muerte;
	public static Sound clear;
	public static Sound expl;
	
		  
	private Box2DDebugRenderer renderizardebug;
	
	
	
	public static ScreenViewport viewport;
	
    public static Stage stage;
    
    public Array<Body> cuerposmundo; // array que contiene todos los cuerpos creados en el mundo box2D
	
	//cargar el nivel
    GeneradorNivel genLvl = new GeneradorNivel();
    
    //cargar a bomberman
    Bomberman player ;
    
    //cargar el ladrillo
    Ladrillo lad ;
    
	public static int mapWidth; //celdas de ancho del mapa actual
    public static int mapHeight; // celdas de alto del mapa actual
    
    public Music b003;
		  
		  
	public Juego(final Bomb gam) {
		this.game = gam;
		
		renderizardebug = new Box2DDebugRenderer();	
		
		
		
		viewport = new ScreenViewport();

		//crear la vista y el nivel
		
		stage = new Stage(viewport);
		Gdx.input.setInputProcessor(stage);
		
		//Añadir a bomberman al nivel
		
		player = new Bomberman();
		stage.addActor(player);
		stage.setKeyboardFocus(player);
		
		//musicas
		b003 = Gdx.audio.newMusic(Gdx.files.internal("ost/003normal.mp3"));
		b003.setLooping(true);
		
		//sonidos
		pisada1 = Gdx.audio.newSound(Gdx.files.internal("sounds/1.wav"));
		pisada2 = Gdx.audio.newSound(Gdx.files.internal("sounds/2.wav"));
		bomba = Gdx.audio.newSound(Gdx.files.internal("sounds/3.wav"));
		powerup = Gdx.audio.newSound(Gdx.files.internal("sounds/4.wav"));
		muerte = Gdx.audio.newSound(Gdx.files.internal("sounds/5.wav"));
		clear = Gdx.audio.newSound(Gdx.files.internal("sounds/6.wav"));
		expl = Gdx.audio.newSound(Gdx.files.internal("sounds/7.wav"));
		
		ponerLadrillos();
					

		
	}
	
	private void ponerLadrillos() {
		
		
		for (int x = 0; x < GeneradorNivel.listaladrillos.size; x++) {
		
			lad = new Ladrillo();
			stage.addActor(lad);
			
			
			lad.setX(GeneradorNivel.listaladrillos.get(x).getPosX());
			lad.setY(GeneradorNivel.listaladrillos.get(x).getPosY());
			
		}
		
		
		
	}
	
	

	@Override
	public void render(float delta) {
		
	Gdx.gl.glClearColor(0.7f, 0.7f, 0.7f, 1);
	Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	
	// tell the camera to update its matrices.
	Bomb.camera.update();
	Bomb.camaramundo.update();
	Bomb.hudcam.update();
		
	game.batch.setProjectionMatrix(Bomb.hudcam.combined);
	game.bh.setProjectionMatrix(Bomb.camera.combined);
		
	//mapa de tiles
		
	genLvl.getTileMapRenderer().setView(Bomb.camera);
	genLvl.getTileMapRenderer().render();	
		
		
		
	stage.act(Gdx.graphics.getDeltaTime());
	stage.getViewport().setCamera(Bomb.camera);
	stage.draw();
	
	// texto
	
	if(Bomb.debugmode == true){ 
	game.batch.begin();
		
	game.font.draw(game.batch, "left " + Bomberman.touchleft + " right " + Bomberman.touchright + " up " + Bomberman.touchup +" Down "+ Bomberman.touchdown, 5, 240);
	game.font.draw(game.batch, "pos bomberman " + (int) player.getX() + "  " + (int) player.getY()  , 5, 220);	
	game.font.draw(game.batch, "pos bomberman screen x " + Bomberman.screenpos.x , 5, 200);	
	
	game.batch.end();
	}	
		
	
	
	
	//activar o desactivar el debug de box 2D 
	if(Bomb.debugmode == false && (Gdx.input.isKeyJustPressed(Keys.C) && Gdx.input.isKeyPressed(Keys.CONTROL_LEFT) ) ){
		Bomb.debugmode = true;
	}
			
	else if(Bomb.debugmode == true && (Gdx.input.isKeyJustPressed(Keys.C) && Gdx.input.isKeyPressed(Keys.CONTROL_LEFT) ) ){
		Bomb.debugmode = false;
	}
	
		
	//Renderizar las colisiones y fisicas del box 2D 
	if(Bomb.debugmode == true){ 
	renderizardebug.render(GameWorld.mundo, Bomb.camaramundo.combined); 	

	}
			
	//resetear nivel
	
	if(Gdx.input.isKeyJustPressed(Keys.R)){
		
		
		
		//destruye todos los cuerpos del mundo
		cuerposmundo = new Array<Body>();
		GameWorld.mundo.getBodies(cuerposmundo);
				

		for (int x = 0; x < cuerposmundo.size; x++) { 
		cuerposmundo.get(x).destroyFixture(cuerposmundo.get(x).getFixtureList().get(0));	
		GameWorld.mundo.destroyBody(cuerposmundo.get(x));
		}
		
		
		
		b003.stop();
		pisada1.stop();
		pisada2.stop();
		bomba.stop();
		powerup.stop();
		muerte.stop();
		clear.stop();
		expl.stop();
		
		game.setScreen(new Juego(game));
		
		
		
		
		
	}
		
		
	// Que la camara siga al personaje
		
	mapWidth = genLvl.getPropiedades().get("width", Integer.class);
	mapHeight = genLvl.getPropiedades().get("height", Integer.class);
	    

	int tilePixelWidth = genLvl.getPropiedades().get("tilewidth", Integer.class);
	int tilePixelHeight = genLvl.getPropiedades().get("tileheight", Integer.class);

	int mapPixelWidth = mapWidth * tilePixelWidth;
	int mapPixelHeight = mapHeight * tilePixelHeight;

	float cameraHalfWidth = Bomb.camera.viewportWidth/2;
	float cameraHalfHeight = Bomb.camera.viewportHeight/2;
		
	//la camara sigue al personaje
		
	Bomb.camera.position.x = Math.round(player.getX() * tilePixelWidth) / tilePixelWidth;
	//Bomb.camera.position.y = Math.round((player.getY()+200) * tilePixelHeight) / tilePixelHeight;
		
	
	// limites de la camara
		
	Bomb.camera.position.x = MathUtils.clamp(Bomb.camera.position.x, cameraHalfWidth, mapPixelWidth - cameraHalfWidth);
	//Bomb.camera.position.y = MathUtils.clamp(Bomb.camera.position.y, cameraHalfHeight, mapPixelHeight - cameraHalfHeight);

	

	//que la camara del mundo sigan a la camara tambien		
	Bomb.camaramundo.position.set(Bomb.camera.position.x/GameWorld.units,Bomb.camera.position.y/GameWorld.units,Bomb.camera.position.z/GameWorld.units);
	
		
	
	
	//step del mundo (60 cuadros por segundo, 8 iteraciones de velocidad , 6 iteraciones de posicion)
	
	GameWorld.mundo.step(1/60f, 8, 6);
			
			
			
			

	}
	
	
	
	
	
	

	@Override
	public void show() {
	
		b003.play();

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
		
		pisada1.dispose();
		pisada2.dispose();
		bomba.dispose();
		powerup.dispose();
		muerte.dispose();
		clear.dispose();
		expl.dispose();
		b003.dispose();
		
		
		

	}

}
