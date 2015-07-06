package ar.edu.um.vj2015;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

public class Resources {
	private Music mainSong;
	private Music gameSong;
	
	public Resources(){
		mainSong = Gdx.audio.newMusic(Gdx.files.internal("main.mp3"));
		gameSong = Gdx.audio.newMusic(Gdx.files.internal("game.mp3"));
	}

	public Music getMainSong() {
		return mainSong;
	}

	public Music getGameSong() {
		return gameSong;
	}
	

}
