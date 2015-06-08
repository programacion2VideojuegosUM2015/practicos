package ar.edu.um.vj2015.arcanoid;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;

public class PantallaPrincipal implements Screen {
	
	Arcanoid arcanoid;
	private Texture fondoInicio;
	public OrthographicCamera camara;
	
	public PantallaPrincipal(Arcanoid arcanoid) {
		this.arcanoid = arcanoid;
		camara = new OrthographicCamera();
		camara.setToOrtho(false, Constantes.PANTALLA_ANCHURA, Constantes.PANTALLA_ALTURA);
		fondoInicio = new Texture(Gdx.files.internal("pantallaInicio.jpg"));
	}

	@Override
	public void render(float delta) {
		
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		//Muestra un menú de inicio
		arcanoid.spriteBatch.begin();
		arcanoid.spriteBatch.draw(fondoInicio, 0, 0,Constantes.PANTALLA_ANCHURA,Constantes.PANTALLA_ALTURA);
		arcanoid.spriteBatch.end();
		
		//Si el usuario toca la pantalla se inicia la partida
		 
		if (Gdx.input.isTouched()) {
			arcanoid.setScreen(new PantallaJuego(arcanoid));
			dispose();
		}
		if (Gdx.input.isKeyPressed(Keys.ESCAPE)) {
			dispose();
			System.exit(0);
		}
		camara.update();
		arcanoid.spriteBatch.setProjectionMatrix(camara.combined);
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

