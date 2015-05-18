package ar.ed.um;

import com.badlogic.gdx.Game;


public class Arcanoid extends Game {
	public PantallaAbstracta pantallaJuego;
	public PantallaDelJuego juego;
	
	@Override
	public void create () {
		pantallaJuego = new PantallaAbstracta(this);
		setScreen(pantallaJuego);
	}

}
