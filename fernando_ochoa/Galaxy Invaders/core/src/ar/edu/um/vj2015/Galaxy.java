package ar.edu.um.vj2015;


import com.badlogic.gdx.Game;


public class Galaxy extends Game {
	

	
	@Override
	public void create () {
		Screens.game = this;
		Screens.gameScreen = new GameScreen(this);
		Screens.mainScreen = new MainScreen(this);
		Screens.loserScreen = new LoserScreen(this);
		Screens.winnerScreen = new WinnerScreen(this);
		setScreen(Screens.mainScreen);
	}

}
