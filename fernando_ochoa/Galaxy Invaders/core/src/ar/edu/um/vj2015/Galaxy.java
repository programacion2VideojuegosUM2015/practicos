package ar.edu.um.vj2015;


import com.badlogic.gdx.Game;


public class Galaxy extends Game {
	
public AbstractScreen gameScreen;
	
	@Override
	public void create () {
		gameScreen = new GameScreen(this);
		setScreen(gameScreen);
	}

}
