package ar.edu.um.vj2015;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Monster {
	private Texture texture;
	private Rectangle monstersOutline;
	private float x;
	private float y;
	
	public Monster(Texture texture, float x, float y) {
		super();
		this.texture = texture;
		this.x = x;
		this.y = y;
		monstersOutline = new Rectangle(x ,y ,texture.getWidth() ,texture.getHeight());
	}

	public Texture getTexture() {
		return texture;
	}

	public Rectangle getMonstersOutline() {
		return monstersOutline;
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}
	


}
