package ar.edu.um.VJ2015;



import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.TimeUtils;

public class GameScreen implements Screen {
	public MataCorrupto mataCorrupto;
	private Revolver revolver;
	private PoliticosAparecen politicos;
	private Balas balas;
	private Texture fondoJuego;
	private long cronometro = 0;
	private long tiempo;
	private Jugador jugador;
	
	
	//private Jugador jugador;
	private SpriteBatch batch;
	
	public GameScreen(MataCorrupto mataCorrupto) {
		this.mataCorrupto = mataCorrupto;
		fondoJuego = new Texture(Gdx.files.internal("fondoJuego.jpg"));
		politicos = new PoliticosAparecen();
		revolver = new Revolver(350,100);
		cronometro = TimeUtils.nanoTime();
		jugador = new Jugador();
		
	}

	@Override
	public void show() {
		batch = new SpriteBatch();
		
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		politicos.update(delta);
		revolver.update(delta);
		tiempo = (((TimeUtils.nanoTime() - cronometro )/1000000000));
		this.detectarColision();
		
		batch.begin();
		batch.draw(fondoJuego, 0, 0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		politicos.draw(batch);
		revolver.draw(batch);
		jugador.draw(batch);
		
		if(tiempo <= 60){
			mataCorrupto.fuente.draw(batch, "Tiempo: " + (60-tiempo) , 280, 670);
			}
		//si se acaba el tiempo y se junta 5000 de dinero se gana sino de pierde
		if((tiempo >= 61)&& jugador.getDinero() == 5000){
			mataCorrupto.setScreen(new Ganador(mataCorrupto));
		}else if((tiempo >= 61)&& jugador.getDinero() <5000){
			mataCorrupto.setScreen(new GameOver(mataCorrupto));
		}
		batch.end();
		
		
	}
	//metodo donde el juego detecta las colisiones
	public void detectarColision(){
		balas.colisionConPoliticos(politicos,jugador);
		politicos.colisionConArma(revolver, mataCorrupto);
	}
	@Override
	public void resize(int width, int height) {
	
		
	}

	@Override
	public void pause() {
		
		
	}

	@Override
	public void resume() {
	
	}

	@Override
	public void hide() {
	
		
	}

	@Override
	public void dispose() {
	
		
	}

}