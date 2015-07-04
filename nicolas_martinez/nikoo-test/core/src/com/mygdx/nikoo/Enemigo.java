package com.mygdx.nikoo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;

public class Enemigo extends Actor {
		
		private boolean animPlay = true;
		private float animTime = 0;
		
		private float tiempoInm = 0;
		
		public static String enDamage;
		
		  BitmapFont font;
		
		//Atlas de Bomberman
		private TextureAtlas enSheet;
		
		//Cae en paracaidas
		private Array<Sprite> enParacaida;
		private Animation Paracaida;
		//Infla
		private Array<Sprite> enInfla;
		private Animation Infla;
		//Dead
		private Array<Sprite> enDead;
		private Animation Dead;
		//Fly
		private Array<Sprite> enFly;
		private Animation Fly;
		//Fall
		private Array<Sprite> enFall;
		private Animation Fall;
		
		//Objetos
		
		//Globo
		private Array<Sprite> enGlo;
		private Animation Glo;
		//Paracaidas
		private Array<Sprite> enPara;
		private Animation Para;
		//Paracaidas roto
		private Array<Sprite> enPararoto;
		private Animation Pararoto;


		
		//Animacion Actual
		private Animation animacion;
		
		
		
		//colisiones
	    private Body cuerpo;
	    private Body sensor;
	    
	    //estados y movimientos
	    private int contactosconsuelo;
	    private int face = -1; //si está mirando hacia adelante o hacia atras
	    private float flyVelocity = 1f;
	    private float flyVelocityUp = 1f;
	    private float aceleracion = 0.4f;
	    private float aceleracionVertical = 1.5f;
	    private boolean stand = false;
	   
	    
		private boolean dead = false;
		private boolean ahogado = false;
		private int globos = 1;
		
		private int AI = 0;
		private float regresivo = 0;
		
		//controles
		private boolean boton1 = false;
		private boolean botonIzquierda = false;
		private boolean botonDerecha = false;
		
		
		
		//constructor -----------------------------------
	    public Enemigo(){
	    	
	    	

	        //setName("Enemigo");
	        
	    	setPosition(-100, 500);
	        setScale(1, 1);
	    	setHeight(12f);
	    	setWidth(6f); //radio de la colision
	    	setBounds(getX(), getY(), getWidth(), getHeight());
	    	
	    	setOrigin(getWidth()/2, 0);
	    	
	    	//crear animaciones de bomberman
	        playerAnim();
	        
	    	//crear colisiones de bomberman
	        playerCls();
	        
	    	
	    	
	    	//seleccionar la animacion adecuada inicial
	    	animacion = Fly;  	
	    		
			font = new BitmapFont();
	    	
	    	
	    	
	    }


		private void playerAnim() {
			
			//enemigo color 1
			enSheet = new TextureAtlas(Gdx.files.internal("enemy.txt"));

	        // Personaje
			
			//Infla
			enInfla = enSheet.createSprites("infla");
			Infla = new Animation(10/60f, enInfla);
			Infla.setPlayMode(Animation.PlayMode.LOOP);
			
			//Cae en paracaidas
			enParacaida = enSheet.createSprites("paracaida");
			Paracaida = new Animation(5/60f, enParacaida);
			Paracaida.setPlayMode(Animation.PlayMode.NORMAL);
			
			//Dead
			enDead = enSheet.createSprites("dead");
			Dead = new Animation(5/60f, enDead);
			Dead.setPlayMode(Animation.PlayMode.LOOP);
			
			//Fly
			enFly = enSheet.createSprites("fly");
			Fly = new Animation(5/60f, enFly);
			Fly.setPlayMode(Animation.PlayMode.LOOP);
			
			//Fall
			enFall = enSheet.createSprites("fall");
			Fall = new Animation(25/60f, enFall);
			Fall.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
			
	        // Objetos
			
			//Globo
			enGlo = enSheet.createSprites("glo");
			Glo = new Animation(25/60f, enGlo);
			Glo.setPlayMode(Animation.PlayMode.NORMAL);
			
			//Paracaidas
			enPara = enSheet.createSprites("para");
			Para = new Animation(5/60f, enPara);
			Para.setPlayMode(Animation.PlayMode.NORMAL);
			
			//Paracaidas roto
			enPararoto = enSheet.createSprites("pararoto");
			Pararoto = new Animation(5/60f, enPararoto);
			Pararoto.setPlayMode(Animation.PlayMode.NORMAL);
			
			
			
			
			

			
			
			
		}

		private void playerCls() {
		

			BodyDef colision = new BodyDef();
			colision.position.set(getX()/GameWorld.units,(getY()+getHeight()/2)/GameWorld.units);
			colision.type = BodyType.DynamicBody;	
			colision.fixedRotation = true;

	 		CircleShape circleShape = new CircleShape();
	 		circleShape.setRadius(getWidth()/1.5f / GameWorld.units);
			
	 		//parte superior del cuerpo
			FixtureDef fixDef = new FixtureDef();
			fixDef.shape = circleShape;
	        fixDef.density = 0;
	        fixDef.friction = 0f;
	        fixDef.restitution = 0.3f;
	        
	        fixDef.filter.categoryBits = GameWorld.BIT_ENEMIGOS;
			fixDef.filter.maskBits = GameWorld.BIT_PARED | GameWorld.BIT_LADRILLOS | GameWorld.BIT_JUGADOR ;
			fixDef.isSensor = false;
			
			cuerpo = GameWorld.mundo.createBody(colision);
			cuerpo.createFixture(fixDef).setUserData(getName());
			cuerpo.isBullet();
			
			//parte inferior del cuerpo
	        circleShape.setPosition(new Vector2(0,(-getWidth()/1.5f)/GameWorld.units ));
	        fixDef.density = 0;
	        fixDef.friction = 0f;
	        fixDef.restitution = 0.3f;
			fixDef.shape = circleShape;
			fixDef.isSensor = false;
	        fixDef.filter.categoryBits = GameWorld.BIT_ENEMIGOS;
			fixDef.filter.maskBits = GameWorld.BIT_PARED | GameWorld.BIT_LADRILLOS | GameWorld.BIT_JUGADOR ;
			cuerpo.createFixture(fixDef).setUserData(getName());
			//
			
			
			
			//Colision golobos
			circleShape.setRadius(getWidth() / GameWorld.units);
			circleShape.setPosition(new Vector2(0,((getHeight()/2)+4)/GameWorld.units ));
			
	        fixDef.density = 0;
	        fixDef.friction = 0f;
	        fixDef.restitution = 0.5f;
			fixDef.shape = circleShape;
			fixDef.isSensor = false;
	        fixDef.filter.categoryBits = GameWorld.BIT_ENEMIGOS;
			fixDef.filter.maskBits = GameWorld.BIT_PARED | GameWorld.BIT_LADRILLOS | GameWorld.BIT_JUGADOR ;
			cuerpo.createFixture(fixDef).setUserData("globos"+getName());
			//
			
			
			
			//Crear sensores 0
			
			BodyDef sen = new BodyDef();
			sen.position.set(getX()/GameWorld.units,(getY())/GameWorld.units);
			sen.type = BodyType.DynamicBody; 
			
			//Crear sensor 
			PolygonShape shape = new PolygonShape();
			shape.setAsBox((getWidth()/GameWorld.units),getHeight()/GameWorld.units/3, new Vector2(0,0), 0);   //ancho, alto ,(vector posicion x,y),angulo
			fixDef.shape = shape;
			fixDef.isSensor = true;
			fixDef.filter.categoryBits = GameWorld.BIT_SENSOR;
			fixDef.filter.maskBits = GameWorld.BIT_SENSOR ;
			
			sensor = GameWorld.mundo.createBody(sen);
			sensor.createFixture(fixDef).setUserData("hitenemigo");
			
			
			//Colision golobos 1
			circleShape.setRadius((getWidth()+1) / GameWorld.units);
			circleShape.setPosition(new Vector2(0,(getHeight())/GameWorld.units ));

			fixDef.shape = circleShape;
			fixDef.isSensor = true;
			fixDef.filter.categoryBits = GameWorld.BIT_SENSOR;
			fixDef.filter.maskBits = GameWorld.BIT_SENSOR ;
			sensor.createFixture(fixDef).setUserData("englobohit");
			
			
			//Crear sensor de los pies 2
			
			shape.setAsBox((getWidth()/GameWorld.units/2)-0.01f,0.005f,new Vector2(0,-(getHeight()/GameWorld.units/2)+0.01f), 0);   //ancho, alto ,(vector posicion x,y),angulo
			fixDef.shape = shape;
			fixDef.isSensor = true;
			fixDef.filter.categoryBits = GameWorld.BIT_SENSOR;
			fixDef.filter.maskBits = GameWorld.BIT_PARED ;
			sensor.createFixture(fixDef).setUserData("footen");
			//
			
			
            //Crear fixture ID del sensor 3
			
			shape.setAsBox(0,0,new Vector2(0,0), 0);   //ancho, alto ,(vector posicion x,y),angulo
			fixDef.shape = shape;
			fixDef.isSensor = true;
			fixDef.filter.categoryBits = GameWorld.BIT_ID;
			fixDef.filter.maskBits = GameWorld.BIT_ID ;
			sensor.createFixture(fixDef).setUserData("cosa");
			//
			
			
			
		}
		
	    @Override
	    public void draw(Batch batch, float parentAlpha) {
	    	
	    	
	    	if(animPlay == true){
	    	animTime += Gdx.graphics.getDeltaTime();
	    	}
	    	
	    	
	    	// para generar el sensor ID que sirve para reconocer con que enemigo colisiona el jugador
	    	if(getName() != null && sensor.getFixtureList().size > 3){
	    	sensor.getFixtureList().get(3).setUserData(getName());	
	    	}
	    	
	    	if(tiempoInm  > 0){
	    		tiempoInm  -= Gdx.graphics.getDeltaTime();
	        	}
	    	if (tiempoInm  <= 0){
	    		tiempoInm  = 0;
	    	}
	    	
			//regresivo inteligencia artificial
	    	if(regresivo  > 0){
	    		regresivo  -= Gdx.graphics.getDeltaTime();
	        	}
	    	if (regresivo  <= 0){
	    		regresivo  = 0;
	    	}
	    	
	    	
	    	//Cargar la animacion
	    	batch.draw(animacion.getKeyFrame(animTime,false), //frame
	    			   getX()-10.5f, getY()-2.5f, //pos
	    			   (animacion.getKeyFrame(animTime,false).getRegionWidth()/2)+0.5f, 0, //eje origen
	    			   animacion.getKeyFrame(animTime,false).getRegionWidth(),  //ancho
	    			   animacion.getKeyFrame(animTime,false).getRegionHeight(), //alto
	    			   getScaleX(), getScaleY(), getRotation()); //escala y rotacion
	    	
	    	

	    	if(Balloon.debugmode == true){ 
	    	font.draw(batch, getName() , getX()-30f, getY()+35f);
	    	}

	    	
	    }
	    
	    @Override
	    public void act(float delta) { 
	    
	    	
	    	//menos gravedad
	    	
	    	cuerpo.setGravityScale(0.5f);
	    	
	    //en el suelo o en el aire
	    	
	    if(contactosconsuelo > 0){
	    	stand = true;	
	    }
	    else{
	    	stand = false;
	    }
	    	
	    //repocicionar en la pantalla al salir
	    
	    if ( cuerpo.getPosition().x > (Balloon.SCREEN_WIDTH/GameWorld.units)+(getWidth()/2/GameWorld.units) ) {
	    	cuerpo.setTransform(0, cuerpo.getPosition().y, 0);
	    }
	    
	    if ( cuerpo.getPosition().x < (0/GameWorld.units)-(getWidth()/2/GameWorld.units) ) {
	    	cuerpo.setTransform(Balloon.SCREEN_WIDTH/GameWorld.units, cuerpo.getPosition().y, 0);
	    }
	    	
	    	
	    	

	    	
	    	//dañado
	    	if(getName() != null && enDamage != null){
	    	if(enDamage.equals(getName()) && tiempoInm == 0){
	    		tiempoInm = 0.75f;
	    		globos --;
	    		Juego.explota.play();
	    		
	    	}
	    	}
	    	
	    	
	    	
	    	
	    	    if(globos >= 1){
	        	movimientoLibre();
	        	}
	        	
	        	
	        	if(globos <= 0 && dead == false){
	        	destruirColision();	
	        	}
	        	
	        	
	        	if(globos <= 0 && dead == true){
	            pierdeVida();	
	            }
	        	
	        	if(getY()<= 0 && ahogado == false){
	        		
	            ahogado();	
	                
	            }	
	    	
	    	
	    	
	    	
	    	
	    	
	    	
	    	
	    	
	    	
	    
	        
	        
	    	

	        	
	    if(cuerpo != null && globos >= 1){	
	    	
	    //El actor se muestra en la pocicion de las coliciones:
	    setPosition(cuerpo.getPosition().x*GameWorld.units, (cuerpo.getPosition().y*GameWorld.units)-8);
	    //Los sensores se muestran en la pocicion del actor y las colisiones:
	    sensor.setTransform(cuerpo.getPosition().x,cuerpo.getPosition().y-getWidth()/2/GameWorld.units,0);
	    
	    }
	    	
	    }


		private void ahogado() {
			
			ahogado = true;
			globos --;
			globos --;
			globos --;
			Juego.muere.stop();
			Juego.splash.play();
			Juego.totalEnemigos --;
			
			if(dead == false){
		    destruirColision();	
		    }
			
			if(getY()>= -20){
		     setPosition(getX(), getY()-1.75f);
		    }
		
			
		}


		private void pierdeVida() {
			
			
	        //animacion caer
            if(animacion != Dead ){
            	animTime= 0;
                animacion = Dead;
                
                if(getY()> 4){
                Juego.derrota.play();
                }
                
            }
            
            if(getY()>= -20){
            setPosition(getX(), getY()-1.75f);
            }
            
			
		}


		private void destruirColision() {
			
			
			dead = true;
			
			Array<Fixture> fixjugador;
			fixjugador = new Array<Fixture>();
			fixjugador = cuerpo.getFixtureList();
			
			for (int x = 0; x < fixjugador.size; x++) {
				
				cuerpo.destroyFixture(fixjugador.get(x));;
				
			}
			
			Array<Fixture> fixsensor;
			fixsensor = new Array<Fixture>();
			fixsensor = sensor.getFixtureList();
			
			for (int x = 0; x < fixsensor.size; x++) {
				
				sensor.destroyFixture(fixsensor.get(x));;
				
			}
			
			GameWorld.mundo.destroyBody(cuerpo);
			GameWorld.mundo.destroyBody(sensor);
			
			
			
		}


		private void movimientoLibre() {
			
			

			float tiempoInactivo = 1.75f;
	    	
	    	
	    	//inteligencia artificial, seleccion aleatoria
	    	
	    	if(regresivo == 0 ){
	    		regresivo = 4; // segundos que tarda en cambiar de accion
	    		AI = MathUtils.random(1,6);	
	    		
	    	}
	    	

	    	
	    	// persigue al personaje
	    	if(AI == 1 && regresivo >= tiempoInactivo){
	    		
	    		
	    		if(Jugador.playerX > getX()){	
	    			botonDerecha = true;	
	    		}
	    		else{
	    			botonDerecha = false;
	    		}
	    		
	    		if(Jugador.playerX < getX()){	
	    			botonIzquierda = true;	
	    		}
	    		else{
	    			botonIzquierda = false;
	    		}
	    		
	    		
	    		if(Jugador.playerY > getY() && getY() < 180){	
	    			boton1 = true;	
	    		}
	    		else{
	    			boton1 = false;
	    		}	
	    		
	    	}
	    	
	    	// se aleja
	    	if(AI == 2 && regresivo >= tiempoInactivo){
	    		
	    		
	    		if(Jugador.playerX < getX()){	
	    			botonDerecha = true;	
	    		}
	    		else{
	    			botonDerecha = false;
	    		}
	    		
	    		if(Jugador.playerX > getX()){	
	    			botonIzquierda = true;	
	    		}
	    		else{
	    			botonIzquierda = false;
	    		}
	    		
	    		
	    		if(Jugador.playerY < getY() && getY() < 180){	
	    			boton1 = true;	
	    		}
	    		else{
	    			boton1 = false;
	    		}	
	    		
	    	}
	    	
	    	// sube y derecha
	    	if(AI == 3 && regresivo >= tiempoInactivo){
	    		

	    			botonDerecha = true;	
	    			boton1 = true;	
	    	}
	    	
	    	// sube y izquierda
	    	if(AI == 4 && regresivo >= tiempoInactivo){
	    		

	    			botonIzquierda = true;	
	    			boton1 = true;	
	    	}
	    	
	    	
	    	// bajar Iaquierda
	    	if(AI == 5 && regresivo >= tiempoInactivo){
	    		

	    			botonIzquierda = true;	
	    			boton1 = false;	
	    	}
	    	
	    	// bajar derecha
	    	if(AI == 6 && regresivo >= tiempoInactivo){
	    		

	    			botonDerecha = true;	
	    			boton1 = false;	
	    	}
	    	
	    	if(getY() < 30){
	    		boton1 = true;	
	    	}
	    	
	    	if(getY() > 184){
	    		boton1 = false;	
	    	}
	    	
	    	
	        //fenar en caida rapida
	        
	        if(getY() < 30) {
	        	if(cuerpo.getLinearVelocity().y < 0){
	        		cuerpo.setLinearVelocity(cuerpo.getLinearVelocity().x, cuerpo.getLinearVelocity().y*0.5f);
	            }
	        	
	        }
			
			
			
			
			//Movimientos del personaje
	    	
	        if( face == -1) {
	        	setScaleX(-1);
	        }
	        if( face == 1) {
	        	setScaleX(1);
	        }
	    	
	    	
	        //turn Right
	        if( botonDerecha==true && face == -1) {
	        	face = 1;
	        }
	        //turn Left
	        if( botonIzquierda==true && face == 1) {
	        	face = -1;
	        }
	    	
	        
	        
	    	//Movimientos horizontales
	        //derecha
	        if( botonDerecha==true && face == 1 ) {
	        	if(cuerpo.getLinearVelocity().x < flyVelocity ){
	            cuerpo.applyForceToCenter(aceleracion*face, 0, true);
	        	}
	        }
	        //izquierda
	        else if( botonIzquierda==true && face == -1 ) {
	        	if(cuerpo.getLinearVelocity().x > -flyVelocity ){
	            cuerpo.applyForceToCenter(aceleracion*face, 0, true);
	            }
	        }
	        else{
	        	animPlay = true;
	        }
	        
	        
	        
	        
	        //SUBIR
	        
	        if(boton1 == true) {
	        	if(cuerpo.getLinearVelocity().y < flyVelocityUp ){
	                cuerpo.applyForceToCenter(0, aceleracionVertical, true);
	                }
	        	
	        }
	        
	        //STAND
	        
	        if(stand == true && boton1 == false) {
	          cuerpo.setLinearVelocity(cuerpo.getLinearVelocity().x, 0);
	        }
	        
	        
	        
	        //Animaciones
	        if(stand == true && cuerpo.getLinearVelocity().x ==0 &&cuerpo.getLinearVelocity().y ==0) {
	            if(animacion != Infla){
	            	animTime= 0;
	            	animacion = Infla;
	            }
	        }
	        else if ((cuerpo.getLinearVelocity().y !=0 ) && boton1 == true){
	        	if(animacion != Fly){
	        		animTime= 0;
	            	animacion = Fly;
	            }
	        }
	        
	        else if (stand == false && boton1 == false){
	        	if(animacion != Fall){
	        		animTime= 0;
	            	animacion = Fall;
	            }
	        }
	        
			
		}


		public boolean isStand() {
			return stand;
		}


		public Body getCuerpo() {
			return cuerpo;
		}


		public int getAI() {
			return AI;
		}


		public float getTiempoInm() {
			return tiempoInm;
		}


		
		
	  
	    
	    
	    
	    
	    
	    
	 }
	    
	    
