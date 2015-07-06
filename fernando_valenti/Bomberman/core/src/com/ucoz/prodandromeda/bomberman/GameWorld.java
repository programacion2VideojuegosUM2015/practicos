package com.ucoz.prodandromeda.bomberman;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.World;

public class GameWorld {

	
	
	public static World mundo;
	
	// Pixeles por tile
    public static final float ppt = 16f;
    public static final float units = 100f;
    
	//Tipos de colisiones con filtros
	
	public static final short BIT_JUGADOR = 2;
	public static final short BIT_PARED = 4;
	public static final short BIT_LADRILLOS = 8;
	public static final short BIT_ENEMIGOS = 16;
	public static final short BIT_SENSOR = 32;
	public static final short BIT_BOMBA = 64;
	 

    public GameWorld() {
    	
    	
    	
    	Box2D.init();
		
		mundo = new World(new Vector2(0, 0), true); // fuerza de gravedad de la tierra - 1 pixel = 1 metro.
														 // El valor boleano dice si los objetos pueden estar en reposo o no

		//mundo.setContactListener(new AdministradorColisiones());
		
    	
    }


}