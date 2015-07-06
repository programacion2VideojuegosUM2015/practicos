package ar.edu.um.VJ2015;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;

public class Ganador implements Screen {
	
	private MataCorrupto mataCorrupto;
	private Texture fondoGanador;
	private OrthographicCamera camara;
	
	public Ganador(MataCorrupto mataCorrupto) {
		this.mataCorrupto = mataCorrupto;
		fondoGanador= new Texture(Gdx.files.internal("victoria.jpg"));
		camara = new OrthographicCamera();
	}

	@Override
	public void render(float delta) {
		
		Gdx.gl.glClearColor(0, 0.3f, 0.6f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		camara.update();
		
		mataCorrupto.spriteBatch.begin();
		mataCorrupto.spriteBatch.draw(fondoGanador, 0, 0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		mataCorrupto.spriteBatch.end();
		
		
		  //Si el usuario toca la pantalla se inicia la partida
		 
		if (Gdx.input.isTouched()){
			mataCorrupto.setScreen(new GameScreen(mataCorrupto));

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
		mataCorrupto.dispose();
	}


}
