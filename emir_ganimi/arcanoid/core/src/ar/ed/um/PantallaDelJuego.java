package ar.ed.um;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class PantallaDelJuego extends PantallaAbstracta {
	
	private SpriteBatch batch;
	private Texture fondoPantalla;
	private Nave nave;
	
	public PantallaDelJuego(Arcanoid arcanoid) {
		super(arcanoid);
	}

	@Override
	public void show() {
		batch = new SpriteBatch();
		fondoPantalla = new Texture(Gdx.files.internal("fondo.jpg"));
		nave = new Nave(30,20);
	}
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT );
		nave.update();
		//aca se dibujan los objetos en la pantalla
		batch.begin();
		batch.draw(fondoPantalla, 0, 0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		nave.draw(batch);
		
		batch.end();
		
	}
	@Override
	public void dispose() {
		nave.dispose();
	}
}
