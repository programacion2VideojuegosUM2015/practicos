package ar.edu.um.vj2015.arcanoid;




import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;





 
public class PantallaJuego implements Screen{
     
 
	public Arcanoid arcanoid;
    public Jugador jugador; 
    public ManejadorDeNiveles manejadorDeNiveles;
    public ManejadorDeSprite manejadorDeSprite;
    private Music musicaFondo;
	private OrthographicCamera camara;
	private Texture fondoPantalla;
	
    

    //constructor donde se inicializa los elementos de pantallaJuego y donde cargamos los recursos a la memoria ram
      public PantallaJuego(Arcanoid arcanoid){
            this.arcanoid = arcanoid;
            
            ManejadorDeRecursos.cargarTodasLasTexturas();
            
            manejadorDeSprite = new ManejadorDeSprite(arcanoid.spriteBatch);
            manejadorDeNiveles = new ManejadorDeNiveles(manejadorDeSprite);
            manejadorDeNiveles.cargarNivelActual();
            musicaFondo = ManejadorDeRecursos.getMusica("musicaFondo");
            musicaFondo.play();   
            fondoPantalla = ManejadorDeRecursos.getTextura("fondoJuego");
        	
            camara = new OrthographicCamera();
    		camara.setToOrtho(false, Constantes.PANTALLA_ANCHURA, Constantes.PANTALLA_ALTURA);
    		jugador = new Jugador();
      }
     
      @Override
      public void show() {
    	  
      }   
 
 
      @Override
      public void pause() {
     
      }


      @Override
      public void render(float dt) {
		
    	Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		//compara si la aplicacion se ejecuta en escritorio o en android
		if(Gdx.app.getType() == ApplicationType.Desktop){
			this.entradaEscritorio(dt);
		}else if(Gdx.app.getType() == ApplicationType.Android){
			this.entradaAndroid(dt);
		}
		//actualizamos 
		camara.update();
		this.juegoGanado();
		arcanoid.spriteBatch.setProjectionMatrix(camara.combined);
		manejadorDeSprite.update(dt,arcanoid,jugador);
		
		//renderiza el manejadorDeSprite, fondo de pantalla y fuente de jugador
		arcanoid.spriteBatch.begin();
		arcanoid.spriteBatch.draw(fondoPantalla, 0, 0, Constantes.PANTALLA_ANCHURA, Constantes.PANTALLA_ALTURA);
		jugador.draw(arcanoid.spriteBatch);
		arcanoid.spriteBatch.end();
		manejadorDeSprite.render();
      }
     
      //metodo para asignarle los botones en caso de que se ejecute en escritorio
      private void entradaEscritorio(float dt){
    	if(Gdx.input.isKeyPressed(Keys.LEFT)){
 			 manejadorDeSprite.nave.x -= 250 * dt;
 			
 		}
 		if(Gdx.input.isKeyPressed(Keys.RIGHT)){
 			manejadorDeSprite.nave.x += 250 * dt;
 			
 		}
 		if (Gdx.input.isKeyPressed(Keys.SPACE)) {
			manejadorDeSprite.bola.setBolaInicio(false);
		}
      }
      //metodo para asignarle coordenadas al tactil en caso de que se ejecute en android
      private void entradaAndroid(float dt){
    	  float coordenadaBola = manejadorDeSprite.bola.y;
    	  float coordenadaNave = manejadorDeSprite.nave.x;
    	  float coordenadaX = Gdx.input.getX(); 
    	  
    	  //si se toca la bola se inicia el juego
    	  if(Gdx.input.isTouched()){ 
    		 if(coordenadaX < coordenadaBola){
    			 manejadorDeSprite.bola.setBolaInicio(false);
    		  }
    	  }
    	  //si se toca la nave se puede mover
    	  if(Gdx.input.isTouched()){
    		  if(coordenadaNave>=coordenadaX - 5 && coordenadaNave<=coordenadaX + 5)
    			  coordenadaX = coordenadaNave;
    		  if(coordenadaX < coordenadaNave){
    			  manejadorDeSprite.nave.x -= 250 * dt;
    		  }
    		  if(coordenadaX > coordenadaNave){
    			  manejadorDeSprite.nave.x += 250 * dt;
    		  }
    	  }
    	  
      }
      //si el array de ladrillos queda vacio el juego se ah ganado
      public void juegoGanado(){
    	if(manejadorDeSprite.ladrillos.size == 0){
    		 arcanoid.setScreen(new PantallaGanador(arcanoid));
    	}
    	
      }  
      @Override
      public void resize(int width, int height) {
 
      }
 
      @Override
      public void resume() {
            
           
      }
 
	@Override
	public void hide() {
		
		
	}

	@Override
	public void dispose() {
		musicaFondo.dispose();
		
	}
}

