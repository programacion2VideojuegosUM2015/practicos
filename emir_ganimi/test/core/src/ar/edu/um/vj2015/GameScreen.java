package ar.edu.um.vj2015;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

//pantalla principal del juego
public class GameScreen extends AbstractScreen{
	
	private SpriteBatch batch;
	private Texture fondoPantalla;
	private float escala;
	private Bucket leftBucket;
	private Bucket rightBucket;
	private Drops drops;
	private Music rainMusic;
	private BitmapFont font;
	public int puntuacion;
	public int puntuacion2;
	
	public GameScreen(ClasePrincipalJuego main) {
		super(main);
	}
	public void show(){
		batch = new SpriteBatch();
		//aca se asigna los elementos graficos
		fondoPantalla = new Texture(Gdx.files.internal("fondo.png"));
		
		//agregamos la musica de fondo
		rainMusic = Gdx.audio.newMusic(Gdx.files.internal("rain.mp3"));
		rainMusic.setLooping(true);
		rainMusic.play();
		
		//aca creamos los objetos
		drops = new Drops();
		leftBucket = new KeyboardControlledBucket(400,20);
		rightBucket = new MouseControlledBucket(30,20);
		font = new BitmapFont(Gdx.files.internal("fuente.fnt"),Gdx.files.internal("fuente_0.tga"),false);
		puntuacion = 0;
		puntuacion2 = 0;
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		//aca actualizamos los valores
		leftBucket.update();
		rightBucket.update();
		drops.update();
		colisiones();
		
		//aca se los mostramos al usuario
		batch.begin();
		
		batch.draw(fondoPantalla, 0, 0, fondoPantalla.getWidth() / escala,fondoPantalla.getHeight() / escala);
		leftBucket.draw(batch);
		rightBucket.draw(batch);
		drops.draw(batch);
		
		font.draw(batch, "J1: "+ Integer.toString(puntuacion), 500, 350);
		font.draw(batch, "J2: "+ Integer.toString(puntuacion2), 500, 325);
		
		batch.end();
		
	}
	
	public void colisiones(){
		drops.detectCollision(leftBucket);
		drops.detectCollision(rightBucket);				
	}
	@Override
	public void resize(int width, int height) {
		//se le pasa anchura y altura de la imagen
		float widthImage = fondoPantalla.getWidth();
		float heightImage = fondoPantalla.getHeight();
		//se saca la proporcion
		float r = heightImage /widthImage;
		//aca se ajusta la imagen al dispositivo movil
		if(heightImage > height){
			heightImage = height;
			widthImage = heightImage /r;
		}
		if(widthImage > width){
			widthImage = width;
			heightImage = widthImage *r;
		}
		escala = width /widthImage;
		
	}
	@Override
	public void dispose() {
		rainMusic.dispose();
		fondoPantalla.dispose();
		batch.dispose();
		drops.dispose();
		
		leftBucket.dispose();
		rightBucket.dispose();
	}
	
		
		
	
}
