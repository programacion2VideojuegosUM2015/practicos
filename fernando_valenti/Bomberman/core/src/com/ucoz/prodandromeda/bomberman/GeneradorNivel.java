package com.ucoz.prodandromeda.bomberman;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.utils.Array;

public class GeneradorNivel {
	

    public static TiledMap mapa;
    private MapProperties propiedades;
    private OrthogonalTiledMapRenderer tileMapRenderer;
    
	private Body oTile;
    private float tileSize;
    BodyDef tile = new BodyDef();
    
    private TiledMapTileLayer solido;
    private TiledMapTileLayer ladrillo;
    
    public static Array<String> espacios;
    public static Array<InfoLadrillo> listaladrillos;
    
    public static int contador = 0;
    public String espacio = "nada";
     
   //cargar el ladrillo
    InfoLadrillo ladri ;

    
    
    //Constructor
    public GeneradorNivel(){
    	
    tileSize = GameWorld.ppt/GameWorld.units;		
    mapa = new TmxMapLoader().load("map.tmx"); 
    tileMapRenderer = new OrthogonalTiledMapRenderer(mapa);
    propiedades = mapa.getProperties();
    	
    solido = (TiledMapTileLayer) mapa.getLayers().get("solido");
    ladrillo = (TiledMapTileLayer) mapa.getLayers().get("ladrillo");
    	
    

    
    //crear bloques solidos
    crearCemento();
    
    
    listaladrillos = new Array<InfoLadrillo>();
    listaladrillos.clear();
    
    
    espacios = new Array<String>();
    espacios.clear();
    
    int ladrillos = 51;
    int enemigos = 6;
    int powerup = 1;
    int puerta = 1;
    int nada = 0;
    
    
    //añadir 51 ladrillos a la lista
    for (int var = 0; var < ladrillos; var++) {
    espacio = "ladrillo";	
    espacios.add(espacio);
    }
    
    //añadir ladrillo con powerup
    for (int var = 0; var < powerup; var++) {
    espacio = "powerup";	
    espacios.add(espacio);
    }
    
    //añadir ladrillo con puerta
    for (int var = 0; var < puerta; var++) {
    espacio = "puerta";	
    espacios.add(espacio);
    }
    
    //añadir 6 enemigos a la lista
    for (int var = 0; var < enemigos; var++) {
    espacio = "enemigo";	
    espacios.add(espacio);
    }
    
    nada = 246-ladrillos-enemigos-powerup-puerta;
    
    //añadir 53 ladrillos a la lista
    for (int var = 0; var < nada; var++) {
    espacio = "nada";	
    espacios.add(espacio);
    }
    
    espacios.shuffle();
    
    
    
    
    
    crearLadrillos();
    	
    }



	private void crearLadrillos() {
		
	
		
		
		
        
        int cont = 0; 
		
		
		for (int filas = 0; filas < ladrillo.getHeight(); filas++){
        	for (int columnas = 0; columnas < ladrillo.getWidth(); columnas++){
        		
        		//obtener celda
        		Cell celda = ladrillo.getCell(columnas, filas);
        		
        		//asegurarse de que la celda exista
        		if(celda == null) continue;
        		if(celda.getTile() == null) continue;
        		
        		//crear un ladrillo a cada celda ocupada que coincida con espacio de ladrillo
        		
        		if(espacios.get(cont)=="ladrillo"){
        			
        	    ladri = new InfoLadrillo();	
            		
            	ladri.setPosX((columnas*16)+8);  
            	ladri.setPosY((filas*16));
            	ladri.setTienePowerUp(false);
            	ladri.setTienePuerta(false);
            	
            	listaladrillos.add(ladri);
        		}
        		
        		if(espacios.get(cont)=="powerup"){
        			
            	    ladri = new InfoLadrillo();	
                		
            	    ladri.setPosX((columnas*16)+8);  
                	ladri.setPosY((filas*16));
                	ladri.setTienePowerUp(true);
                	ladri.setTienePuerta(false);
                	
                	listaladrillos.add(ladri);
            	}
        		
        		if(espacios.get(cont)=="puerta"){
        			
            	    ladri = new InfoLadrillo();	
                		
            	    ladri.setPosX((columnas*16)+8);  
                	ladri.setPosY((filas*16));
                	ladri.setTienePowerUp(false);
                	ladri.setTienePuerta(true);
                	
                	listaladrillos.add(ladri);
            	}
        		
        		if(cont < espacios.size){
        		cont ++;
        		}
        		
        		
        		
        		
        		
        	    
        		
        		
        	}		
        }	
		
		
		
	}



	private void crearCemento() {
		
		   
		//crear la colision de cada tile del suelo
	    BodyDef piso = new BodyDef();
	    FixtureDef fixDef = new FixtureDef();
	    piso.type = BodyType.StaticBody;
	    
	    
	    for (int filas = 0; filas < solido.getHeight(); filas++){
        	for (int columnas = 0; columnas < solido.getWidth(); columnas++){
        		
        		//obtener celda
        		Cell celda = solido.getCell(columnas, filas);
        		
        		//asegurarse de que la celda exista
        		if(celda == null) continue;
        		if(celda.getTile() == null) continue;
        		
        		//crear un cuerpo y fixture a cada celda ocupada 
        		
        		piso.position.set((columnas*tileSize)+8/GameWorld.units,(filas*tileSize)+8/GameWorld.units);
        		
        		PolygonShape shape =new PolygonShape(); 
        		shape.setAsBox(tileSize/2,tileSize/2);
        		
                fixDef.shape = shape;
        		
        		fixDef.filter.categoryBits = GameWorld.BIT_PARED;
        		fixDef.filter.maskBits = GameWorld.BIT_ENEMIGOS | GameWorld.BIT_SENSOR | GameWorld.BIT_JUGADOR;

        		oTile = GameWorld.mundo.createBody(piso);
        		oTile.createFixture(fixDef).setUserData("solido");
        		
        		}
        	}
	    
        		
	        
		
	}



	public MapProperties getPropiedades() {
		return propiedades;
	}



	public OrthogonalTiledMapRenderer getTileMapRenderer() {
		return tileMapRenderer;
	}
    
    
    
    
    
    
}
