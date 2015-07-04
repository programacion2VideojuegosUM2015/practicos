package com.mygdx.nikoo;

import java.util.Iterator;

import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.objects.CircleMapObject;
import com.badlogic.gdx.maps.objects.EllipseMapObject;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.maps.objects.PolylineMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.objects.TextureMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Ellipse;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.utils.Array;

public class GeneradorNivel {
	

    public static TiledMap mapa;
    
    
    //niveles-------------------------
    public static TiledMap nivel1;
    public static TiledMap nivel2;
    public static TiledMap nivel3;
    public static TiledMap nivel4;
    public static TiledMap nivel5;
    public static TiledMap nivel6;
    public static TiledMap nivel7;
    //--------------------------------
    
    
    public static TiledMap agua;
    
    
    private MapProperties propiedades;
    private OrthogonalTiledMapRenderer tileMapRenderer;
    private OrthogonalTiledMapRenderer aguaRenderer;
    
	private Body oTile;
    private float tileSize;
    BodyDef tile = new BodyDef();
    
    private TiledMapTileLayer solido;
    private TiledMapTileLayer en1;
    private MapLayer cls;
    
    
    //array de los enemigos
    public static Array<DatosEnemigos> listaenemigos;
    
    //cargar el ladrillo
    DatosEnemigos enemy ;
    
    
    
    //Constructor
    public GeneradorNivel(){
    	
    tileSize = GameWorld.ppt/GameWorld.units;	
    
    
    nivel1 = new TmxMapLoader().load("levels/lv01.tmx"); 
    nivel2 = new TmxMapLoader().load("levels/lv02.tmx"); 
    nivel3 = new TmxMapLoader().load("levels/lv03.tmx"); 
    nivel4 = new TmxMapLoader().load("levels/lv04.tmx"); 
    nivel5 = new TmxMapLoader().load("levels/lv05.tmx"); 
    nivel6 = new TmxMapLoader().load("levels/lv06.tmx"); 
    nivel7 = new TmxMapLoader().load("levels/lv07.tmx"); 
    
    if(mapa == null){
    mapa = nivel1;
    }
    
    
    tileMapRenderer = new OrthogonalTiledMapRenderer(mapa);
    
    agua = new TmxMapLoader().load("levels/agua.tmx");  
    aguaRenderer = new OrthogonalTiledMapRenderer(agua);
    
    
    
    propiedades = mapa.getProperties();
    	
    solido = (TiledMapTileLayer) mapa.getLayers().get("suelo");
    
    
    en1 = (TiledMapTileLayer) mapa.getLayers().get("en1");
    en1.setVisible(false);
    
    cls = (MapLayer) mapa.getLayers().get("cls");
    
    listaenemigos = new Array<DatosEnemigos>();
    listaenemigos.clear();
    
    crearEnemigos();
    
    crearColisiones();
    
    }
    
    //-------------------------------------------------------------------------------------


    private void crearEnemigos() {

 	   
 		//crear los enemigos
 	    
 	    for (int filas = 0; filas < en1.getHeight(); filas++){
         	for (int columnas = 0; columnas < en1.getWidth(); columnas++){
         		
         		//obtener celda
         		Cell celda = en1.getCell(columnas, filas);
         		
         		//asegurarse de que la celda exista
         		if(celda == null) continue;
         		if(celda.getTile() == null) continue;
         		
         		//crear un enemigo para cada celda ocupada
         		
         		enemy = new DatosEnemigos();	
         		
            	enemy.setPosX((columnas*GameWorld.ppt));  
            	enemy.setPosY((filas*GameWorld.ppt)+4);
            	enemy.setColor(1);
            	
            	listaenemigos.add(enemy);

         		
         		}
         	}
 	    
         		
 	        
		
	}


	private void crearColisiones() {
	     	
	  Array<Body> slopes = new Array<Body>();
	  FixtureDef fixDef = new FixtureDef();
	     	
	 		
	  MapObjects objects = cls.getObjects();
	  Iterator<MapObject> objectIt = objects.iterator();
	 			
	  while(objectIt.hasNext()) {
	 	MapObject object = objectIt.next();
	 			
	 	if (object instanceof TextureMapObject){
	 	  continue;
	 	}
	 			
	 	Shape shape;
	 	BodyDef bodyDef = new BodyDef();
	 	bodyDef.type = BodyDef.BodyType.StaticBody;
	 			
	 	if (object instanceof RectangleMapObject) {
	 	    RectangleMapObject rectangle = (RectangleMapObject)object;
	 	    shape = getRectangle(rectangle);
	 	}
	 	else if (object instanceof PolygonMapObject) {
	 		shape = getPolygon((PolygonMapObject)object);
	 	}
	 	else if (object instanceof PolylineMapObject) {
	 		shape = getPolyline((PolylineMapObject)object);
	 	}
	 	else if (object instanceof EllipseMapObject) {
	 		shape = getEllipse((EllipseMapObject)object);
	 	}
	 	else if (object instanceof CircleMapObject) {
	 		shape = getCircle((CircleMapObject)object);
	 	}
	 	else {
	 		continue;
	 	}
	 			
	 			
	 	fixDef.shape = shape;
	 	fixDef.filter.categoryBits = GameWorld.BIT_PARED;
	    fixDef.filter.maskBits = GameWorld.BIT_JUGADOR | GameWorld.BIT_ENEMIGOS | GameWorld.BIT_SENSOR;
	     		

	 	Body suelo = GameWorld.mundo.createBody(bodyDef);
	 	suelo.createFixture(fixDef).setUserData("cls");
	 			
	 	slopes.add(suelo);
	 			
	 	shape.dispose();
	    }
   }	
	
	
	 ///////////////////////////////////////////////////////////////////////////////
	 // Metodos que crean los objetos del mapa                                    //    
	 ///////////////////////////////////////////////////////////////////////////////    
	     
	     private Shape getRectangle(RectangleMapObject rectangleObject) {
	 		Rectangle rectangle = rectangleObject.getRectangle();
	 		PolygonShape polygon = new PolygonShape();
	 		Vector2 size = new Vector2((rectangle.x + rectangle.width * 0.5f) / GameWorld.units,
	 		                           (rectangle.y + rectangle.height * 0.5f ) / GameWorld.units);
	 		polygon.setAsBox(rectangle.width * 0.5f / GameWorld.units,
	 		                 rectangle.height * 0.5f / GameWorld.units,
	 		                 size,
	 		                 0.0f);
	 		return polygon;
	 	}
	 	
	 	
	 	private Shape getCircle(CircleMapObject circleObject) {
	 		Circle circle = circleObject.getCircle();
	 		CircleShape circleShape = new CircleShape();
	 		circleShape.setRadius(circle.radius / GameWorld.units);
	 		circleShape.setPosition(new Vector2(circle.x / GameWorld.units, circle.y / GameWorld.units));
	 		return circleShape;
	 	}
	 	
	 	private Shape getEllipse(EllipseMapObject ellipseObject) {
	 		Ellipse ellipse = ellipseObject.getEllipse();
	 		CircleShape ellipseShape = new CircleShape();
	 		ellipseShape.setRadius((ellipse.width/2) / GameWorld.units);
	 		ellipseShape.setPosition(new Vector2(ellipse.x / GameWorld.units, ellipse.y / GameWorld.units));

	 		return ellipseShape;
	 	}
	 	
	 	private Shape getPolygon(PolygonMapObject polygonObject) {
	 		PolygonShape polygon = new PolygonShape();
	 		float[] vertices = polygonObject.getPolygon().getTransformedVertices();
	 		
	 		float[] worldVertices = new float[vertices.length];
	 		
	 		for (int i = 0; i < vertices.length; ++i) {
	 		    worldVertices[i] = vertices[i] / GameWorld.units;
	 		}
	 		
	 		polygon.set(worldVertices);
	 		return polygon;
	 	}
	 	
	 	private Shape getPolyline(PolylineMapObject polylineObject) {
	 		float[] vertices = polylineObject.getPolyline().getTransformedVertices();
	 		Vector2[] worldVertices = new Vector2[vertices.length / 2];
	 		
	 		for (int i = 0; i < vertices.length / 2; ++i) {
	 		    worldVertices[i] = new Vector2();
	 		    worldVertices[i].x = vertices[i * 2] / GameWorld.units;
	 		    worldVertices[i].y = vertices[i * 2 + 1] / GameWorld.units;
	 		}
	 		
	 		ChainShape chain = new ChainShape(); 
	 		chain.createChain(worldVertices);
	 		return chain;
	 	}
	 ///////////////////////////////////////////////////////////////////////////////    



	public MapProperties getPropiedades() {
		return propiedades;
	}



	public OrthogonalTiledMapRenderer getTileMapRenderer() {
		return tileMapRenderer;
	}

	public OrthogonalTiledMapRenderer getAguaRenderer() {
		return aguaRenderer;
	}
    
    
    
    
    
    
}
