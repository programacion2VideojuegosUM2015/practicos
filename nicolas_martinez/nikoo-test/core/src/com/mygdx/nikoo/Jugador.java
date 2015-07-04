package com.mygdx.nikoo;

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
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;

public class Jugador extends Actor {
	
	private boolean animPlay = true;
	private float animTime = 0;
	private float tiempoInm = 0;
	private float tiempoAgua = 0;
	
	
	private float tiempoSndFly = 0;

	
	//Datos Partida
	private boolean gameOver = false;
	
	
	
	//Atlas de Bomberman
	private TextureAtlas balloonSheet;
	
	
	//Con dos globos
	//Stand
	private Array<Sprite> balloonStand2;
	private Animation Stand2;
	//Run
	private Array<Sprite> balloonRun2;
	private Animation Run2;
	//Slide
	private Array<Sprite> balloonSlide2;
	private Animation Slide2;
	//Fly
	private Array<Sprite> balloonFly2;
	private Animation Fly2;
	//Fall
	private Array<Sprite> balloonFall2;
	private Animation Fall2;
	
	//Con un globo
	//Stand
	private Array<Sprite> balloonStand1;
	private Animation Stand1;
	//Run
	private Array<Sprite> balloonRun1;
	private Animation Run1;
	//Slide
	private Array<Sprite> balloonSlide1;
	private Animation Slide1;
	//Fly
	private Array<Sprite> balloonFly1;
	private Animation Fly1;
	//Fall
	private Array<Sprite> balloonFall1;
	private Animation Fall1;
	
	//Para ambos
	//Dead
	private Array<Sprite> balloonDead;
	private Animation Dead;
	//Shock
	private Array<Sprite> balloonShock;
	private Animation Shock;
	
	
	//Animacion Actual
	private Animation animacion;
	
	//colisiones
    private Body cuerpo;
    private Body sensor;
    
    //estados y movimientos
    public static int numFootContacts;
    public static boolean damage = false;
    
	private boolean dead = false;
	private boolean ahogado = false;
	private boolean reviviendo = false;
    
    private int face = 1; //si está mirando hacia adelante o hacia atras
    private float flyVelocity = 2f;
    private float flyVelocityUp = 1.5f;
    private float runVelocity = 4f;
    private float runAcel = 2.3f;
    private float aceleracion = 1.2f;
    private float aceleracionVertical = 1.9f;
    private float inmunidadRevivir = 3f;
    private boolean stand = false;
    
    private int globos = 2;
    
    
    // para la IA de los enemigos
    
    public static float playerX = 0;
    public static float playerY = 0;
    
    //para que funcione con touch
    public static Vector3 touchpos;
    public static Vector2 screenpos;
	public static boolean touchup = false;
	public static boolean touchdown = false;
	public static boolean touchleft = false;
	public static boolean touchright = false;
   
	
	//controles
	private boolean boton1 = false;
	private boolean botonIzquierda = false;
	private boolean botonDerecha = false;
	
	
	
	//constructor -----------------------------------
    public Jugador(){
    	
    	

        setName("Jugador 1");
        
    	setPosition(24, 24);
        setScale(1, 1);
    	setHeight(12f);
    	setWidth(6f); //radio de la colision
    	setBounds(getX(), getY(), getWidth(), getHeight());
    	
    	setOrigin(getWidth()/2, 0);
    	
    	//crear animaciones del jugador
        playerAnim();
        
    	//crear colisiones del jugador
        playerCls();
        
    	
    	
    	//seleccionar la animacion adecuada inicial
    	animacion = Stand2;  	
    		
    	//pocicion de dedo en pantalla
    	touchpos = new Vector3();
    	
    	
    	
    	
    }


	private void playerAnim() {
		
		balloonSheet = new TextureAtlas(Gdx.files.internal("player.txt"));

        // Con dos globos
		
		//Stand
		balloonStand2 = balloonSheet.createSprites("2stand");
		Stand2 = new Animation(25/60f, balloonStand2);
		Stand2.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
		
		//Run
		balloonRun2 = balloonSheet.createSprites("2run");
		Run2 = new Animation(4/60f, balloonRun2);
		Run2.setPlayMode(Animation.PlayMode.LOOP);
		
		//Slide
		balloonSlide2 = balloonSheet.createSprites("2slide");
		Slide2 = new Animation(5/60f, balloonSlide2);
		Slide2.setPlayMode(Animation.PlayMode.NORMAL);
		
		//Fly
		balloonFly2 = balloonSheet.createSprites("2fly");
		Fly2 = new Animation(5/60f, balloonFly2);
		Fly2.setPlayMode(Animation.PlayMode.LOOP);
		
		//Fall
		balloonFall2 = balloonSheet.createSprites("2fall");
		Fall2 = new Animation(25/60f, balloonFall2);
		Fall2.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
		
        // Con un globos
		
		//Stand
		balloonStand1 = balloonSheet.createSprites("1stand");
		Stand1 = new Animation(25/60f, balloonStand1);
		Stand1.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
		
		//Run
		balloonRun1 = balloonSheet.createSprites("1run");
		Run1 = new Animation(4/60f, balloonRun1);
		Run1.setPlayMode(Animation.PlayMode.LOOP);
		
		//Slide
		balloonSlide1 = balloonSheet.createSprites("1slide");
		Slide1 = new Animation(5/60f, balloonSlide1);
		Slide1.setPlayMode(Animation.PlayMode.NORMAL);
		
		//Fly
		balloonFly1 = balloonSheet.createSprites("1fly");
		Fly1 = new Animation(5/60f, balloonFly1);
		Fly1.setPlayMode(Animation.PlayMode.LOOP);
		
		//Fall
		balloonFall1 = balloonSheet.createSprites("1fall");
		Fall1 = new Animation(25/60f, balloonFall1);
		Fall1.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);	
		
		//Dead
		balloonDead = balloonSheet.createSprites("dead");
		Dead = new Animation(5/60f, balloonDead);
		Dead.setPlayMode(Animation.PlayMode.LOOP);	
		
		//Shock
		balloonShock = balloonSheet.createSprites("shock");
		Shock = new Animation(5/60f, balloonShock);
		Shock.setPlayMode(Animation.PlayMode.LOOP);	
		
		
		
		
		
		

		
		
		
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
        fixDef.restitution = 0.2f;
        
        fixDef.filter.categoryBits = GameWorld.BIT_JUGADOR;
		fixDef.filter.maskBits = GameWorld.BIT_PARED | GameWorld.BIT_LADRILLOS | GameWorld.BIT_ENEMIGOS ;
		fixDef.isSensor = false;
		
		cuerpo = GameWorld.mundo.createBody(colision);
		cuerpo.createFixture(fixDef).setUserData("player");
		cuerpo.isBullet();
		
		//parte inferior del cuerpo
        circleShape.setPosition(new Vector2(0,(-getWidth()/1.5f)/GameWorld.units ));
        fixDef.density = 0;
        fixDef.friction = 2.6f;
        fixDef.restitution = 0.2f;
		fixDef.shape = circleShape;
		fixDef.isSensor = false;
        fixDef.filter.categoryBits = GameWorld.BIT_JUGADOR;
		fixDef.filter.maskBits = GameWorld.BIT_PARED | GameWorld.BIT_LADRILLOS | GameWorld.BIT_ENEMIGOS ;
		cuerpo.createFixture(fixDef).setUserData("player");
		//
		
		
		
		//Colision golobos
		circleShape.setRadius(getWidth() / GameWorld.units);
		circleShape.setPosition(new Vector2(0,((getHeight()/2)+4)/GameWorld.units ));
		
        fixDef.density = 0;
        fixDef.friction = 0f;
        fixDef.restitution = 0.6f;
		fixDef.shape = circleShape;
		fixDef.isSensor = false;
        fixDef.filter.categoryBits = GameWorld.BIT_JUGADOR;
		fixDef.filter.maskBits = GameWorld.BIT_PARED | GameWorld.BIT_LADRILLOS | GameWorld.BIT_ENEMIGOS ;
		cuerpo.createFixture(fixDef).setUserData("globos");
		//
		
		
		
		//Crear sensores 
		
		BodyDef sen = new BodyDef();
		sen.position.set(getX()/GameWorld.units,(getY())/GameWorld.units);
		sen.type = BodyType.DynamicBody; 
		
		//Crear sensor 
		PolygonShape shape = new PolygonShape();
		shape.setAsBox((getWidth()/GameWorld.units/2),getHeight()/GameWorld.units/2, new Vector2(0,0), 0);   //ancho, alto ,(vector posicion x,y),angulo
		fixDef.shape = shape;
		fixDef.isSensor = true;
		fixDef.filter.categoryBits = GameWorld.BIT_SENSOR;
		fixDef.filter.maskBits = GameWorld.BIT_SENSOR;
		
		sensor = GameWorld.mundo.createBody(sen);
		sensor.createFixture(fixDef).setUserData("hit");
		
		//sensor Globos
		
		
		//Colision golobos
		circleShape.setRadius((getWidth()+1) / GameWorld.units);
		circleShape.setPosition(new Vector2(0,(getHeight())/GameWorld.units ));

		fixDef.shape = circleShape;
		fixDef.isSensor = true;
		fixDef.filter.categoryBits = GameWorld.BIT_SENSOR;
		fixDef.filter.maskBits = GameWorld.BIT_SENSOR ;
		sensor.createFixture(fixDef).setUserData("globohit");
		
		
		//Crear sensor de los pies
		
		shape.setAsBox((getWidth()/GameWorld.units/2)-0.01f,0.005f,new Vector2(0,-(getHeight()/GameWorld.units/2)+0.01f), 0);   //ancho, alto ,(vector posicion x,y),angulo
		fixDef.shape = shape;
		fixDef.isSensor = true;
		fixDef.filter.categoryBits = GameWorld.BIT_SENSOR;
		fixDef.filter.maskBits = GameWorld.BIT_PARED ;
		sensor.createFixture(fixDef).setUserData("foot");
		//
		
	}
	
    @Override
    public void draw(Batch batch, float parentAlpha) {
    	
    	
    	if(animPlay == true){
    	animTime += Gdx.graphics.getDeltaTime();
    	}
    	
    	
    	if(tiempoInm  > 0){
    		tiempoInm  -= Gdx.graphics.getDeltaTime();
        	}
    	if (tiempoInm  <= 0){
    		tiempoInm  = 0;
    	}
    	
    	
    	if(tiempoAgua  > 0){
    		tiempoAgua  -= Gdx.graphics.getDeltaTime();
        	}
    	if (tiempoAgua  <= 0){
    		tiempoAgua  = 0;
    	}
    	
    	
    	if(tiempoSndFly  > 0){
    		tiempoSndFly  -= Gdx.graphics.getDeltaTime();//tiempo que pasa en segundos
        	}
    	if (tiempoSndFly  <= 0){
    		tiempoSndFly  = 0;
    	}
    	
    	
    	
    	//Cargar la animacion
    	batch.draw(animacion.getKeyFrame(animTime,false), //frame
    			   getX()-10.5f, getY()-2.5f, //pos
    			   (animacion.getKeyFrame(animTime,false).getRegionWidth()/2)+0.5f, 0, //eje origen
    			   animacion.getKeyFrame(animTime,false).getRegionWidth(),  //ancho
    			   animacion.getKeyFrame(animTime,false).getRegionHeight(), //alto
    			   getScaleX(), getScaleY(), getRotation()); //escala y rotacion
    }
    
    @Override
    public void act(float delta) {
    	
    	
    //en el suelo o en el aire
    	
    if(numFootContacts > 0){
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
    	
    	
    //para la AI
    
    playerX = getX();
    playerY = getY();
    	
    //Touch screen
    	
    	
    	
    	screenpos = new Vector2(getX(), getY());
    	getStage().stageToScreenCoordinates(/*in/out*/screenpos);
    	
    	
    	
    	if (Gdx.input.isTouched()) {
			touchpos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			if (  ((Gdx.graphics.getHeight()-touchpos.y)*Balloon.SCREEN_HEIGHT)/Gdx.graphics.getHeight()  > getY() +8) {
			touchup = true;
			}
			else{
				
				touchup = false;
			}
			if (  ((Gdx.graphics.getHeight()-touchpos.y)*Balloon.SCREEN_HEIGHT)/Gdx.graphics.getHeight()  < getY() -8) {
			touchdown = true;
			}
			else{
				touchdown = false;
				
			}
			
			
			
			if (   (touchpos.x*Balloon.SCREEN_WIDTH)/Gdx.graphics.getWidth()   > (screenpos.x*Balloon.SCREEN_WIDTH)/Gdx.graphics.getWidth() +8) {
			touchright = true;
			}
			else{
				
				touchright = false;
			}
			if ( (touchpos.x*Balloon.SCREEN_WIDTH)/Gdx.graphics.getWidth()   < (screenpos.x*Balloon.SCREEN_WIDTH)/Gdx.graphics.getWidth() -8) {
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
    	
    	
    //Botones	
    	
    	
    	//boton 1
    	if(Gdx.input.isKeyPressed(Keys.D)){
    		boton1 = true;
    	}
    	else if(touchup == true){
    		boton1 = true;
    	}
    	else{
    		boton1= false;
    	}
    	
    	
    	//izquierda
    	if(Gdx.input.isKeyPressed(Keys.LEFT)){
    		botonIzquierda = true;
    	}
    	else if(touchleft == true){
    		botonIzquierda = true;
    	}
    	else{
    		botonIzquierda= false;
    	}
    	
    	
    	
    	//derecha
    	if(Gdx.input.isKeyPressed(Keys.RIGHT)){
    		botonDerecha = true;
    	}
    	else if(touchright == true){
    		botonDerecha = true;
    	}
    	else{
    		botonDerecha= false;
    	}
    	
    	
       // Se rompe un globo
    	
    	
    	if(damage == true && tiempoInm == 0){
    		tiempoInm = 0.75f;
    		globos --;
    		Juego.explota.play();
    		
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
    	
    	
    	if(tiempoAgua == 0 && ahogado == true && reviviendo == false && Balloon.vidas > 0){
    		
            revivir();	
                
        }
    	
    	
    	if(tiempoAgua == 0 && ahogado == true && reviviendo == false && Balloon.vidas <= 0){
    		
            gameOver();	
                
        }
    	
    	
    	
    	
    	
       
        
        
    	

    //El actor se muestra en la pocicion de las coliciones:
    if(cuerpo != null && globos >= 1){	
    setPosition(cuerpo.getPosition().x*GameWorld.units, (cuerpo.getPosition().y*GameWorld.units)-8);
    
    //Los sensores se muestran en la pocicion del actor y las colisiones:
    sensor.setTransform(cuerpo.getPosition().x,cuerpo.getPosition().y-getWidth()/2/GameWorld.units,0);
    }
    	
    }


	private void gameOver() {
		
		reviviendo = true;
		Juego.b008.play();
		
		gameOver = true;

		
	}


	private void revivir() {
		
		reviviendo = true;
		animacion = Stand2;
		setPosition(24, 24);
		
		
		Juego.b006.play();
		globos = 2;
		dead = false;
		ahogado = false;
		reviviendo = false;
		face = 1;

		Balloon.vidas --;
           
		//crear colisiones del jugador
        playerCls();
		
		
		
	}


	private void ahogado() {
		
		tiempoAgua = 2;
		ahogado = true;
		globos --;
		globos --;
		globos --;
		Juego.muere.stop();
		Juego.splash.play();
		if(dead == false){
	    destruirColision();	
	    }
	
		
	}


	private void pierdeVida() {
		
        //animacion caer
            if(animacion != Dead ){
            	animTime= 0;
                animacion = Dead;
                
                if(getY()> 4){
                Juego.muere.play();
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
        
        if(stand == true){
        //derecha
        if( (botonDerecha==true && face == 1 && boton1 == true) || (botonDerecha==true && face == 1 && stand == true)) {
        	if(cuerpo.getLinearVelocity().x < runVelocity ){
            cuerpo.applyForceToCenter(runAcel*face, 0, true);
        	}
        }
        //izquierda
        else if( (botonIzquierda==true && face == -1 && boton1 == true) || (botonIzquierda==true && face == -1 && stand == true)) {
        	if(cuerpo.getLinearVelocity().x > -runVelocity ){
            cuerpo.applyForceToCenter(runAcel*face, 0, true);
            }
        }
       
        }
        
        // en el aire
        else{
        	
        	//derecha
            if( (botonDerecha==true && face == 1 && boton1 == true) || (botonDerecha==true && face == 1 && stand == true)) {
            	if(cuerpo.getLinearVelocity().x < flyVelocity ){
                cuerpo.applyForceToCenter(aceleracion*face, 0, true);
            	}
            }
            //izquierda
            else if( (botonIzquierda==true && face == -1 && boton1 == true) || (botonIzquierda==true && face == -1 && stand == true)) {
            	if(cuerpo.getLinearVelocity().x > -flyVelocity ){
                cuerpo.applyForceToCenter(aceleracion*face, 0, true);
                }
            }	
        	
        }
        
        
        
        
        //SUBIR
        
        if(boton1 == true && getY() < 200) {
        	if(cuerpo.getLinearVelocity().y < flyVelocityUp ){
                cuerpo.applyForceToCenter(0, aceleracionVertical, true);
                }
        	
        }
        
        //STAND
        
        if(stand == true && boton1 == false) {
          cuerpo.setLinearVelocity(cuerpo.getLinearVelocity().x, 0);
        }
        
        
        
        //Animaciones
        
        //stand
        if(stand == true && cuerpo.getLinearVelocity().x ==0 &&cuerpo.getLinearVelocity().y ==0) {
            if((animacion != Stand2 && animacion != Stand1) || (animacion == Stand2 && globos != 2)){
            	animTime= 0;
            	
            	if(globos == 2){
            	animacion = Stand2;
            	}
            	else if(globos == 1){
                animacion = Stand1;
                }
            }
        }
        
        //derrape
        else if (stand == true && cuerpo.getLinearVelocity().x !=0 && botonIzquierda == false && botonDerecha == false){
        	
            		animTime= 0;
            		if(globos == 2){
                	animacion = Slide2;
            		}
            		else if(globos == 1){
            		animacion = Slide1;	
            		}
        	
        }
        
        //correr
        else if (stand == true && cuerpo.getLinearVelocity().x !=0 && (botonIzquierda == true || botonDerecha == true)){
        	if((animacion != Run2 && animacion != Run1) || (animacion == Run2 && globos != 2)){
        		animTime= 0;
        		if(globos == 2){
            	animacion = Run2;
        		}
        		else if(globos == 1){
        		animacion = Run1;	
        		}
            }
        }
        
        //volar elevarse
        else if ((stand == false || cuerpo.getLinearVelocity().y !=0 ) && boton1 == true){
        	
        	if(tiempoSndFly == 0){
        	Juego.fly.play();
        	tiempoSndFly= 0.20f;
        	}
        	
        	if((animacion != Fly2 && animacion != Fly1) || (animacion == Fly2 && globos != 2)){
        		animTime= 0;
        		
        		
        		if(globos == 2){
            	animacion = Fly2;
        		}
        		else if(globos == 1){
        		animacion = Fly1;	
        		}
            }
        }
        
        
        // volar caer
        else if (stand == false && boton1 == false){
        	if((animacion != Fall2 && animacion != Fall1) || (animacion == Fall2 && globos != 2)){
        		animTime= 0;
        		if(globos == 2){
            	animacion = Fall2;
        		}
        		else if(globos == 1){
        		animacion = Fall1;	
        		}
            }
        }
        
		
		
		
		
	}


	public boolean isStand() {
		return stand;
	}


	public int getGlobos() {
		return globos;
	}


	public boolean isGameOver() {
		return gameOver;
	}
  
    
    
    
    
    
    
 }
    
    


