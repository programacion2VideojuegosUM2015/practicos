package ar.edu.um.vj2015;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class PlayButton extends Button {

	public PlayButton(int x, int y) {
		super(x, y);
		buttonTexture =new Texture(Gdx.files.internal("botonjugar.png"));
		
	}

	@Override
	public void performance() {
		
		Screens.game.setScreen(Screens.gameScreen);

	}

}
