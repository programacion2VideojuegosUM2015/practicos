package ar.edu.um.vj2015;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;


public class Galaxy extends Game {
	public OrthographicCamera camera;
	

	
	@Override
	public void create () {
		camera = new OrthographicCamera();
		camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camera.update();
		Screens.game = this;
		Screens.gameScreen = new GameScreen(this);
		Screens.mainScreen = new MainScreen(this);
		Screens.loserScreen = new LoserScreen(this);
		Screens.winnerScreen = new WinnerScreen(this);
		setScreen(Screens.mainScreen);
	}

}
