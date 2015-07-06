package ar.edu.um.VJ2015;




import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MataCorrupto extends Game {
	 
    
    public SpriteBatch spriteBatch;
    public OrthographicCamera camara;
    public BitmapFont fuente;
	
    

    @Override
    public void create() {
  	  	spriteBatch = new SpriteBatch();
        fuente = new BitmapFont();
        camara = new OrthographicCamera();
  		camara.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
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

