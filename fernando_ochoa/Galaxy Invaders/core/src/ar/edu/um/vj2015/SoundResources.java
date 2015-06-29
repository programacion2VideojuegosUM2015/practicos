package ar.edu.um.vj2015;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class SoundResources {
	private Music mainSong;
	private Music gameSong;
	private Sound shot;
	private Sound explosion;
	private Sound explosion2;
	
	public SoundResources(){
		mainSong = Gdx.audio.newMusic(Gdx.files.internal("main.mp3"));
		gameSong = Gdx.audio.newMusic(Gdx.files.internal("game.mp3"));
		shot = Gdx.audio.newSound(Gdx.files.internal("misil.mp3"));
		explosion = Gdx.audio.newSound(Gdx.files.internal("explosion.mp3"));
		explosion2 = Gdx.audio.newSound(Gdx.files.internal("explosion2.mp3"));
				
	}

	public Music getMainSong() {
		return mainSong;
	}

	public Music getGameSong() {
		return gameSong;
	}

	public Sound getShot() {
		return shot;
	}

	public Sound getExplosion() {
		return explosion;
	}

	public Sound getExplosion2() {
		return explosion2;
	}
	
	
	
	
	

}
