package com.mygdx.test;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class Clase extends ApplicationAdapter {
	
	SpriteBatch batch;
	
	private World mundo;
	private Box2DDebugRenderer debugRenderer;
	private OrthographicCamera camaramundo;
	
	public static final int SCREEN_WIDTH = 800;
	public static final int SCREEN_HEIGHT = 600;
	
	public static float units = 100f;
	
	private Body cuerpo;
	private Body piso;
	private Body caja;
	
	public static final short BIT_PELOTA = 2;
	public static final short BIT_CAJA = 4;
	public static final short BIT_PISO = 8;

	
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		
		Box2D.init();
		mundo = new World(new Vector2(0, -9.8f), true);
		debugRenderer = new Box2DDebugRenderer();
		
		//crear la camara del mundo box2d
		camaramundo = new OrthographicCamera();
		camaramundo.setToOrtho(false, SCREEN_WIDTH/units, SCREEN_HEIGHT/units);
		


		//OBJETO PISO
		BodyDef cuerpoPiso = new BodyDef();
		cuerpoPiso.type = BodyType.StaticBody;
		//creamos una línea que representa el suelo, del ancho de la pantalla
		EdgeShape linea = new EdgeShape();
		linea.set(0, 20/units, SCREEN_WIDTH/units, 20/units); 	
		//creamos el fixture
		FixtureDef fixDef=new FixtureDef();
		fixDef.shape=linea;
		fixDef.filter.categoryBits = BIT_PISO;
		fixDef.filter.maskBits = BIT_PELOTA | BIT_CAJA;
		//lo añadimos al mundo
		piso = mundo.createBody(cuerpoPiso);
		piso.createFixture(fixDef).setUserData("piso"); // añadimos el fixture al objeto
		
		//PAREDES
		cuerpoPiso.type = BodyType.StaticBody;
		//creamos una línea que representa la pared izquierda, del alto de la pantalla
		linea.set(0, 0, 0 , SCREEN_HEIGHT*2/units); 	
		//creamos el fixture
		fixDef.shape=linea;
		fixDef.filter.categoryBits = BIT_PISO;
		fixDef.filter.maskBits = BIT_PELOTA | BIT_CAJA;
		//lo añadimos al mundo
		piso = mundo.createBody(cuerpoPiso);
		piso.createFixture(fixDef).setUserData("pared"); // añadimos el fixture al objeto
		
		cuerpoPiso.type = BodyType.StaticBody;
		//creamos una línea que representa la pared derecha, del alto de la pantalla
		linea.set(SCREEN_WIDTH/units, 0, SCREEN_WIDTH/units , SCREEN_HEIGHT*2/units); 	
		//creamos el fixture
		fixDef.shape=linea;
		fixDef.filter.categoryBits = BIT_PISO;
		fixDef.filter.maskBits = BIT_PELOTA | BIT_CAJA;
		//lo añadimos al mundo
		piso = mundo.createBody(cuerpoPiso);
		piso.createFixture(fixDef).setUserData("pared"); // añadimos el fixture al objeto


		//OBJETO CAJA GIRATORIA
		BodyDef cuerpoCaja = new BodyDef();
		cuerpoCaja.type = BodyType.KinematicBody;
		cuerpoCaja.position.set(SCREEN_WIDTH/2/units, SCREEN_HEIGHT/4/units); 
		//creamos un rectángulo que representa la caja giratoria
		PolygonShape rectangulo = new PolygonShape();
		rectangulo.setAsBox(100/units, 10/units); 
		//creamos el fixture
		fixDef.shape=rectangulo;
		fixDef.filter.categoryBits = BIT_CAJA;
		fixDef.filter.maskBits = BIT_PELOTA;
		//lo añadimos al mundo
		caja = mundo.createBody(cuerpoCaja);
		caja.createFixture(fixDef).setUserData("caja"); // añadimos el fixture al objeto
		
		
		//es importante hacer dispose de los shapes despues de usarlos
		rectangulo.dispose();
		linea.dispose();
		
	}
	
	private void crearPelota() {
		//OBJETO PELOTA
		BodyDef cuerpoPelota = new BodyDef();
		cuerpoPelota.position.set(SCREEN_WIDTH/2/units, SCREEN_HEIGHT/units); 
		cuerpoPelota.type = BodyType.DynamicBody;
		
		CircleShape circulo = new CircleShape();
		circulo.setRadius(25/units); //un radio de 25 pixeles de la pantalla
		
		FixtureDef fixDef=new FixtureDef();
		fixDef.shape=circulo;
		//Propiedades opcionales
		fixDef.density = 30f; 
		fixDef.friction = 5f;
		fixDef.restitution = 0.5f;
		//propiedades mas opcionales 
		fixDef.filter.categoryBits = BIT_PELOTA;
		fixDef.filter.maskBits = BIT_PISO | BIT_CAJA | BIT_PELOTA;
		fixDef.isSensor = false;
		
		cuerpo = mundo.createBody(cuerpoPelota);
		cuerpo.createFixture(fixDef).setUserData("pelota"); // añadimos el fixture al objeto
		//parámetros opcionales:
		//cuerpo.setGravityScale(1); // escala de la gravedad que lo afecta
		//cuerpo.isBullet(); //si es un cuerpo muy pequeño que viaja muy rápido
		
		
		//es importante hacer dispose de los shapes despues de usarlos
		circulo.dispose();

	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		debugRenderer.render(mundo, camaramundo.combined);
		
		
		//Movimiento rotatorio de la caja
		caja.setAngularVelocity((float) Math.toRadians(360));
		
		
		//cada vez que hacemos click, se crea una nueva pelota:
		if(Gdx.input.justTouched()){
			 crearPelota();
		}
		
		
		
		mundo.step(1/60f, 8, 6);
	}


}
