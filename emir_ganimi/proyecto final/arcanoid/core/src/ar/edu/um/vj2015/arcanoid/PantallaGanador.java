package ar.edu.um.vj2015.arcanoid;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;

public class PantallaGanador implements Screen {
	private Arcanoid arcanoid;
	private Texture fondoGanador;
	private OrthographicCamera camara;
	
	public PantallaGanador(Arcanoid arcanoid) {
		this.arcanoid = arcanoid;
		fondoGanador = ManejadorDeRecursos.getTextura("fondoGanaste");
		camara = new OrthographicCamera();
		camara.setToOrtho(false,Constantes.PANTALLA_ANCHURA, Constantes.PANTALLA_ALTURA);
	}

	@Override
	public void render(float delta) {
		
		Gdx.gl.glClearColor(0, 0.3f, 0.6f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		camara.update();
		arcanoid.spriteBatch.setProjectionMatrix(camara.combined);
		
		arcanoid.spriteBatch.begin();
		arcanoid.spriteBatch.draw(fondoGanador, 0, 0,Constantes.PANTALLA_ANCHURA,Constantes.PANTALLA_ALTURA);
		arcanoid.spriteBatch.end();
		
		
		  //Si el usuario toca la pantalla se inicia la partida
		 
		if (Gdx.input.isTouched()){
			dispose();
			System.exit(0);
		}
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
		arcanoid.dispose();
	}
}


