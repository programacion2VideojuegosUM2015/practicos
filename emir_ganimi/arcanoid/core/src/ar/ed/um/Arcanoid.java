package ar.ed.um;

import com.badlogic.gdx.Game;


public class Arcanoid extends Game {
	public PantallaAbstracta pantallaJuego;
	
	@Override
	public void create () {
		pantallaJuego = new PantallaAbstracta(this);
		setScreen(pantallaJuego);
	}

}
