package ar.edu.um.vj2015;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Player {
	private int score;
	private int lives;
	private BitmapFont livesFont;
	private BitmapFont scoreFont;
	private BitmapFont scoreFont2;
	
	public Player(){
		livesFont = new BitmapFont(Gdx.files.internal("vidas.fnt"), Gdx.files.internal("vidas.png"), false);
		scoreFont = new BitmapFont(Gdx.files.internal("puntaje.fnt"), Gdx.files.internal("puntaje.png"), false);
		scoreFont2 = new BitmapFont(Gdx.files.internal("puntaje2.fnt"), Gdx.files.internal("puntaje2.png"), false);
		score = 0;
		lives = 3;;
		
	}
	public void draw(SpriteBatch batch){
		livesFont.draw(batch,"LIVES "+ Integer.toString(lives), 650, 440 );
		scoreFont.draw(batch,"SCORE "+ Integer.toString(score), 10, 440 );
	}
	public void draw2(SpriteBatch batch){
		scoreFont2.draw(batch,"SCORE "+ Integer.toString(score), 320, 120 );
	
	}
	public void draw3(SpriteBatch batch){
		scoreFont2.draw(batch,"SCORE "+ Integer.toString(score), 10, 75 );
	}
	public int getScore(){
		return score;
	}
	public void AddScore(){
		this.score += 10;		
	}
	public int getLives() {
		return lives;
	}
	public void removeLives() {
		lives -= 1;
	}
	
	public void dispose(){
		livesFont.dispose();
		scoreFont.dispose();
	}
	
	

}
