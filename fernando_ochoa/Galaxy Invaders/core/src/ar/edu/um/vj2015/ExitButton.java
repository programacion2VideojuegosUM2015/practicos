package ar.edu.um.vj2015;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class ExitButton extends Button {

	public ExitButton(int x, int y) {
		super(x, y);
		buttonTexture =new Texture(Gdx.files.internal("botonsalir.png"));
	}

	@Override
	public void performance() {
		Gdx.app.exit();

	}

}
