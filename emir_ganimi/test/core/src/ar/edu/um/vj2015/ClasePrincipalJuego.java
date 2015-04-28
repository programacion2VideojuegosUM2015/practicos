package ar.edu.um.vj2015;


import com.badlogic.gdx.Game;

public class ClasePrincipalJuego extends Game{
	public AbstractScreen GAMESCREEN;
	public GameScreen lluvia;
	//creamos nuestra pantalla
	@Override
	public void create() {
		GAMESCREEN = new GameScreen(this);
		setScreen(GAMESCREEN);
		
	}

}
