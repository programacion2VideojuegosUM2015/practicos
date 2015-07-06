package ar.edu.um.VJ2015;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;

public class PantallaPrincipal implements Screen {
	
	MataCorrupto mataCorrupto;
	private Texture fondoInicio;
	public OrthographicCamera camara;
	
	public PantallaPrincipal(MataCorrupto mataCorrupto) {
		this.mataCorrupto = mataCorrupto;
		camara = new OrthographicCamera();
		fondoInicio = new Texture(Gdx.files.internal("FondoInicio.jpg"));
	}

	@Override
	public void render(float delta) {
		
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		//Muestra un menú de inicio
		mataCorrupto.spriteBatch.begin();
		mataCorrupto.spriteBatch.draw(fondoInicio, 0, 0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		mataCorrupto.spriteBatch.end();
		
		//Si el usuario toca la pantalla se inicia la partida
		 
		if (Gdx.input.isTouched()) {
			mataCorrupto.setScreen(new GameScreen(mataCorrupto));
			dispose();
		}
		if (Gdx.input.isKeyPressed(Keys.ESCAPE)) {
			dispose();
			System.exit(0);
		}
		camara.update();
			
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void show() {
	}

	@Override
	public void hide() {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {
	}
}

