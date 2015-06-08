package ar.edu.um.vj2015.arcanoid;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/**
 * @author: Emir L. Ganimi
 * @version: 1.0 08/06/2015/
 */ 
public class Arcanoid extends Game {
 
      
      public SpriteBatch spriteBatch;
      public OrthographicCamera camara;
      public BitmapFont fuente;
	
      
 
      @Override
      public void create() {
    	  	spriteBatch = new SpriteBatch();
            fuente = new BitmapFont();
            camara = new OrthographicCamera();
    		camara.setToOrtho(false, Constantes.PANTALLA_ANCHURA, Constantes.PANTALLA_ALTURA);
    		camara.update();
            setScreen(new PantallaPrincipal(this));

            
      }
      public void render(){
    	  super.render();
      }
      public void dispose(){
    	  spriteBatch.dispose();
    	  
	  }
}