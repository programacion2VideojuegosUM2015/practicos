package com.ucoz.prodandromeda.bomberman;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;

public class Ladrillo extends Actor {
	
	
	private boolean animPlay = true;
	private float animTime = 0;
	
	//Atlas del ladrillo
	private TextureAtlas ladSheet;
	
	//Normal
	private Array<Sprite> ladNormal;
	private Animation Normal;
	//Desaparece
	private Array<Sprite> ladDes;
	private Animation Des;
	//Animacion Actual
	private Animation animacion;
	
	
	//colisiones
    private Body ladrillo;
	
	

	//constructor -----------------------------------
    public Ladrillo(){
    	
        setName("Ladrillo");
        
    	setPosition(0, 0);
        setScale(1, 1);
    	setHeight(16);
    	setWidth(16);
    	setBounds(getX(), getY(), getWidth(), getHeight());
    	setOrigin(getWidth()/2, 0);
    	
    	//crear animaciones de bomberman
        ladAnim();
        
    	//crear colisiones de bomberman
        ladCls();
        
    	//seleccionar la animacion adecuada inicial
    	animacion = Normal; 
    	
    }


	private void ladCls() {
		
		BodyDef colision = new BodyDef();
		colision.position.set(getX()/GameWorld.units,(getY()+getHeight()/2)/GameWorld.units);
		colision.type = BodyType.StaticBody;
		colision.fixedRotation  = true;
		
		PolygonShape shape =new PolygonShape(); 
		shape.setAsBox(getWidth()/GameWorld.units/2,(getHeight()/GameWorld.units/2));

		FixtureDef fixDef = new FixtureDef();
		fixDef.shape = shape;
        
        fixDef.filter.categoryBits = GameWorld.BIT_LADRILLOS;
		fixDef.filter.maskBits = GameWorld.BIT_JUGADOR | GameWorld.BIT_ENEMIGOS;
		fixDef.isSensor = false;
		
		
		ladrillo = GameWorld.mundo.createBody(colision);
		ladrillo.createFixture(fixDef).setUserData("ladrillo");
		ladrillo.setGravityScale(0);
		ladrillo.isBullet();
		
	}


	private void ladAnim() {


		
		
		ladSheet = new TextureAtlas(Gdx.files.internal("ladrillos.txt"));


		//Camina hacia adelante
		ladNormal = ladSheet.createSprites("ladrillo");
		Normal = new Animation(5/60f, ladNormal);
		Normal.setPlayMode(Animation.PlayMode.LOOP);
		
		//Camina hacia abajo
		ladDes = ladSheet.createSprites("ladrilloexp");
		Des = new Animation(5/60f, ladDes);
		Des.setPlayMode(Animation.PlayMode.LOOP);
		
	}
    

    @Override
    public void draw(Batch batch, float parentAlpha) {
    	
    	
    	if(animPlay == true){
    	animTime += Gdx.graphics.getDeltaTime();
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
    	
    	//Poner el ladrillos en el lugar adecuado
        ladrillo.setTransform(getX()/GameWorld.units,(getY()+8)/GameWorld.units, 0);
        
        
    	
    }



    
    
    
}
