package ar.edu.um.vj2015;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Monster {
	private Texture texture;
	private Rectangle monstersOutline;
	
	public Monster(Texture texture, float x, float y) {
		this.texture = texture;
	
		monstersOutline = new Rectangle(x ,y ,texture.getWidth() ,texture.getHeight());
	}

	public Texture getTexture() {
		return texture;
	}
	

	public Rectangle getMonstersOutline() {
		return monstersOutline;
	}

	
	


}
