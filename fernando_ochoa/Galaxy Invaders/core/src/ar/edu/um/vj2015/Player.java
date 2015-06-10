package ar.edu.um.vj2015;

public class Player {
	private int score;
	private int lives;
	public Player(){
		score = 0;
		lives = 3;
		
	}
	public int getScore(){
		return score;
	}
	public void addScore(int score){
		this.score += score;		
	}
	public int getLives() {
		return lives;
	}
	public void addLives(int lives) {
		this.lives += lives;
	}
	
	public void removeLives(int lives){
		this.lives -= lives;
	}

}
