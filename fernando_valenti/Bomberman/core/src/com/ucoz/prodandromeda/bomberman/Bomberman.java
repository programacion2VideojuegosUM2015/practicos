package com.ucoz.prodandromeda.bomberman;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;

public class Bomberman extends Actor {
	
	private boolean animPlay = true;
	private float animTime = 0;
	private float pisada = 0;
	
	//Atlas de Bomberman
	private TextureAtlas bombSheet;
	
	//Right
	private Array<Sprite> bombRight;
	private Animation Right;
	//Up
	private Array<Sprite> bombUp;
	private Animation Up;
	//Down
	private Array<Sprite> bombDown;
	private Animation Down;
	//Dead
	private Array<Sprite> bombDead;
	private Animation Dead;
	
	
	//Animacion Actual
	private Animation animacion;
	
	//colisiones
    private Body cuerpo;
    private Body sensor;
    
    //estados y movimientos
    private int face = 1; //si está mirando hacia adelante o hacia atras
    private float runVelocity = 0.5f;
    
    //para que funcione con touch
    public static Vector3 touchpos;
    public static Vector2 screenpos;
	public static boolean touchup = false;
	public static boolean touchdown = false;
	public static boolean touchleft = false;
	public static boolean touchright = false;
   
	
	
	//constructor -----------------------------------
    public Bomberman(){
    	
    	

        setName("Bomberman");
        
    	setPosition(24, (16*11));
        setScale(1, 1);
    	setHeight(16f);
    	setWidth(7.1f); //radio de la colision
    	setBounds(getX(), getY(), getWidth(), getHeight());
    	
    	setOrigin(getWidth()/2, 0);
    	
    	//crear animaciones de bomberman
        playerAnim();
        
    	//crear colisiones de bomberman
        playerCls();
        
    	
    	
    	//seleccionar la animacion adecuada inicial
    	animacion = Right;  	
    		
    	//pocicion de dedo en pantalla
    	touchpos = new Vector3();
    	
    	
    	
    	
    }


	private void playerAnim() {
		
		bombSheet = new TextureAtlas(Gdx.files.internal("player.txt"));


		//Camina hacia adelante
		bombRight = bombSheet.createSprites("right");
		Right = new Animation(5/60f, bombRight);
		Right.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
		
		//Camina hacia abajo
		bombDown = bombSheet.createSprites("down");
		Down = new Animation(5/60f, bombDown);
		Down.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
		
		//Camina hacia arriba
		bombUp = bombSheet.createSprites("up");
		Up = new Animation(5/60f, bombUp);
		Up.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
		
		//Muere
		bombDead = bombSheet.createSprites("dead");
		Dead = new Animation(5/60f, bombDead);
		Dead.setPlayMode(Animation.PlayMode.NORMAL);
		
		
		
	}

	private void playerCls() {
	

		BodyDef colision = new BodyDef();
		colision.position.set(getX()/GameWorld.units,(getY()+getHeight()/2)/GameWorld.units);
		colision.type = BodyType.DynamicBody;	

 		CircleShape circleShape = new CircleShape();
 		circleShape.setRadius(getWidth() / GameWorld.units);
		

		FixtureDef fixDef = new FixtureDef();
		fixDef.shape = circleShape;
        fixDef.density = 1;
        fixDef.friction = 0;
        fixDef.restitution = 0;
        
        fixDef.filter.categoryBits = GameWorld.BIT_JUGADOR;
		fixDef.filter.maskBits = GameWorld.BIT_PARED | GameWorld.BIT_LADRILLOS | GameWorld.BIT_BOMBA ;
		fixDef.isSensor = false;
		
		
		
		cuerpo = GameWorld.mundo.createBody(colision);
		cuerpo.createFixture(fixDef).setUserData("player");
		cuerpo.setGravityScale(0);
		cuerpo.isBullet();
		
		//Crear sensores
		
		BodyDef sen = new BodyDef();
		sen.position.set(getX()/GameWorld.units,(getY()+getHeight())/GameWorld.units);
		sen.type = BodyType.DynamicBody; 
		
		//Crear sensor 
		circleShape.setRadius(getWidth() / GameWorld.units);
		fixDef.shape = circleShape;
		fixDef.isSensor = true;
		fixDef.filter.categoryBits = GameWorld.BIT_SENSOR;
		fixDef.filter.maskBits = GameWorld.BIT_ENEMIGOS ;
		
		sensor = GameWorld.mundo.createBody(sen);
		sensor.createFixture(fixDef).setUserData("hit");
		sensor.setGravityScale(0);
		
		
		//Crear sensor de los pies
		//shape.setAsBox((getWidth()/GameWorld.units/2)-0.02f,0.05f,new Vector2(0, -1.45f), 0);
		//fixDef.shape = shape;
		//fixDef.isSensor = true;
		//fixDef.filter.categoryBits = GameWorld.BIT_SENSOR;
		//fixDef.filter.maskBits = GameWorld.BIT_PISO | GameWorld.BIT_PLATAFORMA ;
		//sensor.createFixture(fixDef).setUserData("foot");
		//
		
	}
	
    @Override
    public void draw(Batch batch, float parentAlpha) {
    	
    	
    	if(animPlay == true){
    	animTime += Gdx.graphics.getDeltaTime();
    	}
    	
    	if(pisada < 0.25){
        pisada += Gdx.graphics.getDeltaTime();
        }
    	if(pisada >= 0.25){
        pisada = 0;
        }
    	
    	//Cargar la animacion
    	batch.draw(animacion.getKeyFrame(animTime,false), //frame
    			   getX()-8, getY(), //pos
    			   animacion.getKeyFrame(animTime,false).getRegionWidth()/2, 0, //eje
    			   animacion.getKeyFrame(animTime,false).getRegionWidth(),  //ancho
    			   animacion.getKeyFrame(animTime,false).getRegionHeight(), //alto
    			   getScaleX(), getScaleY(), getRotation()); //escala y rotacion
    }
    
    @Override
    public void act(float delta) {
    	
    	
    //Touch screen
    	
    	
    	
    	screenpos = new Vector2(getX(), getY());
    	getStage().stageToScreenCoordinates(/*in/out*/screenpos);
    	
    	if (Gdx.input.isTouched()) {
			touchpos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			if (  ((Gdx.graphics.getHeight()-touchpos.y)*Bomb.SCREEN_HEIGHT)/Gdx.graphics.getHeight()  > getY() +8) {
			touchup = true;
			}
			else{
				
				touchup = false;
			}
			if (  ((Gdx.graphics.getHeight()-touchpos.y)*Bomb.SCREEN_HEIGHT)/Gdx.graphics.getHeight()  < getY() -8) {
			touchdown = true;
			}
			else{
				touchdown = false;
				
			}
			
			
			
			if (   (touchpos.x*Bomb.SCREEN_WIDTH)/Gdx.graphics.getWidth()   > (screenpos.x*Bomb.SCREEN_WIDTH)/Gdx.graphics.getWidth() +8) {
			touchright = true;
			}
			else{
				
				touchright = false;
			}
			if ( (touchpos.x*Bomb.SCREEN_WIDTH)/Gdx.graphics.getWidth()   < (screenpos.x*Bomb.SCREEN_WIDTH)/Gdx.graphics.getWidth() -8) {
			touchleft = true;
			}
			
			
			else{
				touchleft = false;
				
			}
    	}
    	else{
    		touchup = false;
			touchdown = false;
			touchright = false;
			touchleft = false;
    	}	
    	
    	
    	
    	
    	
    	
    //Movimientos del personaje
    	
        //turn Right
        if((Gdx.input.isKeyPressed(Keys.RIGHT) || touchright == true) && face == -1) {
        	setScaleX(1);
        	face = 1;
        }
        //turn Left
        if((Gdx.input.isKeyPressed(Keys.LEFT) || touchleft == true) && face == 1) {
        	setScaleX(-1);
        	face = -1;
        }
    	
    	//ADELANTE
        if((Gdx.input.isKeyPressed(Keys.RIGHT)|| touchright == true) && face == 1) {
          cuerpo.setLinearVelocity(runVelocity*face, 0);
        }	
    	//ATRAS
        else if((Gdx.input.isKeyPressed(Keys.LEFT)|| touchleft == true) && face == -1) {
          cuerpo.setLinearVelocity(runVelocity*face, 0);
          if(animacion != Right ){
            	animTime = 0;
          }
          animPlay = true;
          animacion = Right;
        }
        else{
          cuerpo.setLinearVelocity(0, cuerpo.getLinearVelocity().y);
        }
        
        //ARRIBA
        if(Gdx.input.isKeyPressed(Keys.UP)|| touchup == true) {
          cuerpo.setLinearVelocity(0, runVelocity);
        }
        //ABAJO
        else if(Gdx.input.isKeyPressed(Keys.DOWN)|| touchdown == true) {
          cuerpo.setLinearVelocity(0, -runVelocity);
        }
        else{
          cuerpo.setLinearVelocity(cuerpo.getLinearVelocity().x,0 );	
        }
        
        
        //poner las animaciones adecuadas
        if(cuerpo.getLinearVelocity().x != 0){
        	if(animacion != Right ){
              	animTime = 0;
              }
              animPlay = true;
              animacion = Right;
              if(pisada ==0){
            	  Juego.pisada1.play();  
              }
              
        }
        else if(cuerpo.getLinearVelocity().y > 0){
        	if(animacion != Up ){
              	animTime = 0;
              }
              animPlay = true;
              animacion = Up;
              if(pisada ==0){
            	  Juego.pisada2.play();  
              }
        	
        }
        else if(cuerpo.getLinearVelocity().y < 0){
            if(animacion != Down ){
              	animTime = 0;
              }
              animPlay = true;
              animacion = Down;
              if(pisada ==0){
            	  Juego.pisada2.play();  
              }
        }
        
    	
        if(cuerpo.getLinearVelocity().x == 0 && cuerpo.getLinearVelocity().y == 0){
        	animPlay = false;	
        }
    	
    	

    //El actor se muestra en la pocicion de las coliciones:
    setPosition(cuerpo.getPosition().x*GameWorld.units, (cuerpo.getPosition().y*GameWorld.units)-(getHeight()/2));
    //Los sensores se muestran en la pocicion del actor y las colisiones:
    sensor.setTransform(getX()/GameWorld.units,(getY()+(getHeight()/2))/GameWorld.units,0);
    
    	
    }
  
    
    
    
    
    
    
 }
    
    


