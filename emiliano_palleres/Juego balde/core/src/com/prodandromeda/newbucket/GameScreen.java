package com.prodandromeda.newbucket;

import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
 
public class GameScreen implements Screen {
  final Drop game;
    
	Texture dropImageA;
	Texture dropImageB;
	Texture dropImageC;
	Texture dropImageD;
	Texture bucketImage;
	Texture p1w;
	Texture p2w;
	Texture gameover;
	Texture fondo;
	Texture fondo2;
	Sound dropSound;
	Sound dropSound2;
	Sound splashSound;
	Sound fallSound;
	Sound espSound;
	Music rainMusic;
	Music overMusic;
	OrthographicCamera camera;
	Array<Gota> gotascaen;
	Array<Balde> players;
	long lastDropTime;
	int dropsGathered1;
	int dropsGathered2;
	int totalscore1;
	int totalscore2;
	long cronometro = 0;
	long tiempo;
	
	
	

	
	public GameScreen(final Drop gam) {
		this.game = gam;
		
		cronometro = TimeUtils.nanoTime();
		

	
 
		// load the images for the droplet and the bucket, 64x64 pixels each
		fondo = new Texture(Gdx.files.internal("Game.png"));
		fondo2 = new Texture(Gdx.files.internal("Game2.png"));
		p1w = new Texture(Gdx.files.internal("p1win.png"));
		p2w = new Texture(Gdx.files.internal("p2win.png"));
		dropImageA = new Texture(Gdx.files.internal("GotaA.png"));
		dropImageB = new Texture(Gdx.files.internal("GotaB.png"));
		dropImageC = new Texture(Gdx.files.internal("GotaC.png"));
		dropImageD = new Texture(Gdx.files.internal("GotaD.png"));
		bucketImage = new Texture(Gdx.files.internal("bucket.png"));
		gameover = new Texture(Gdx.files.internal("gameover.png"));

 
		// load the drop sound effect and the rain background "music"
		dropSound = Gdx.audio.newSound(Gdx.files.internal("gotita1.mp3"));
		dropSound2 = Gdx.audio.newSound(Gdx.files.internal("gotita2.mp3"));
		splashSound = Gdx.audio.newSound(Gdx.files.internal("splash.mp3"));
		fallSound = Gdx.audio.newSound(Gdx.files.internal("gotaespcae.mp3"));
		espSound = Gdx.audio.newSound(Gdx.files.internal("gotaesp.mp3"));
		rainMusic = Gdx.audio.newMusic(Gdx.files.internal("musicaA.mp3"));
		overMusic = Gdx.audio.newMusic(Gdx.files.internal("over.mp3"));
		rainMusic.setLooping(true);
 
		// create the camera and the SpriteBatch
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);
 
		
		players = new Array<Balde>();
		
		
		if(MainMenuScreen.numeroJugadores == 1){
			Balde player = new Balde();
			player.setJugador(1);
			player.setX((Gdx.graphics.getWidth()/2)-32);
			players.add(player);	
			
			}
		
		if(MainMenuScreen.numeroJugadores == 2){
			Balde player = new Balde();
			player.setJugador(1);
			player.setX((Gdx.graphics.getWidth()/4)-32);
			players.add(player);
			}
		
		if(MainMenuScreen.numeroJugadores == 2){
			Balde player = new Balde();
			player.setJugador(2);
			player.setX(((Gdx.graphics.getWidth()/4)*3)-32);
			players.add(player);
			}
		
		
 
		// create the raindrops array and spawn the first raindrop
		gotascaen = new Array<Gota>();
		spawnRaindrop();
		
	}
 
	private void spawnRaindrop() {
		Gota gotita = new Gota();
		gotita.setX(MathUtils.random(0, 800 - 64));
		gotita.setY(480);
		gotita.setWidth(64);
		gotita.setHeight(64);
		gotita.setTipo(MathUtils.random(0,835)); //valores por arriba de 800 dan la gota especial
		gotascaen.add(gotita);	
		
		lastDropTime = TimeUtils.nanoTime();
	}
 
	@Override
	public void render(float delta) {
		// clear the screen with a dark blue color. The
		// arguments to glClearColor are the red, green
		// blue and alpha component in the range [0,1]
		// of the color to be used to clear the screen.
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
 
		// tell the camera to update its matrices.
		camera.update();
		
		//if(tiempo >= 120){ tiempo = 120;}
		
		tiempo = (((TimeUtils.nanoTime() - cronometro )/1000000000));
 
		// tell the SpriteBatch to render in the
		// coordinate system specified by the camera.
		game.batch.setProjectionMatrix(camera.combined);
 
		// Comienza el batch
		game.batch.begin();
		
		//Dibuja el fondo
		if(MainMenuScreen.numeroJugadores == 1){
		game.batch.draw(fondo, 0, 0);
		}
		if(MainMenuScreen.numeroJugadores == 2){
		game.batch.draw(fondo2, 0, 0);	
		}
		//Score del jugador 1
		if(MainMenuScreen.numeroJugadores == 1){
		game.font.draw(game.batch, "Gotas Atrapadas: " + dropsGathered1, 20, 460);
		game.font.draw(game.batch, "Puntaje: " + totalscore1, 20, 440);
		}
		if(MainMenuScreen.numeroJugadores == 2){
		game.font.draw(game.batch, "Gotas Atrapadas Jugador Uno: " + dropsGathered1, 20, 460);
		game.font.draw(game.batch, "Puntaje del jugador Uno: " + totalscore1, 20, 440);
		}
		//Score del jugador 2
		if(MainMenuScreen.numeroJugadores == 2){
		game.font.draw(game.batch, "Gotas Atrapadas Jugador Dos: " + dropsGathered2, 420, 460);
		game.font.draw(game.batch, "Puntaje del jugador Dos: " + totalscore2, 420, 440);		
		}
		
		if(tiempo <= 120){
		game.font.draw(game.batch, "Tiempo: " + (120-tiempo) , 700, 460);
		}
		
		//Dice quien gana XD!!!!
		
		if(tiempo == 123){
		rainMusic.stop();
		overMusic.play();
		}
		
		
		if((tiempo >= 124)&& MainMenuScreen.numeroJugadores == 2){
			
			if(totalscore1 >= totalscore2){
			game.batch.draw(p1w, 0, 200);	
			}
			else{
			game.batch.draw(p2w, 0, 200);	
			}
		}
		
		if((tiempo >= 124)&& MainMenuScreen.numeroJugadores == 1){
			game.batch.draw(gameover, 0, 200);	
		}
		
		
		
		//dibuja uno o dos baldes dependiendo si hay uno o dos jugadores.
		game.batch.draw(bucketImage, players.get(0).getX(), players.get(0).getY()) ; 
		
		if(MainMenuScreen.numeroJugadores == 2){
		game.batch.draw(bucketImage, players.get(1).getX(), players.get(1).getY()) ;}
				

		
		for (Gota gotita : gotascaen) {
			if(gotita.getTipo()>=0 && gotita.getTipo()<=200){
			game.batch.draw(dropImageA, gotita.getX(), gotita.getY());
			}
			else if(gotita.getTipo()>=201 && gotita.getTipo()<=400){
			game.batch.draw(dropImageB, gotita.getX(), gotita.getY());
			}
			else if(gotita.getTipo()>=401 && gotita.getTipo()<=600){
			game.batch.draw(dropImageC, gotita.getX(), gotita.getY());
			}
			else if(gotita.getTipo()>=601 && gotita.getTipo()<=800){
			game.batch.draw(dropImageD, gotita.getX(), gotita.getY());
			}
			else{
				if(MathUtils.random(0,3)==0){
			    game.batch.draw(dropImageA, gotita.getX(), gotita.getY());
				}
				else if(MathUtils.random(0,3)==1){
				game.batch.draw(dropImageB, gotita.getX(), gotita.getY());
				}
				else if(MathUtils.random(0,3)==2){
				game.batch.draw(dropImageC, gotita.getX(), gotita.getY());
				}
				else{
				game.batch.draw(dropImageD, gotita.getX(), gotita.getY());
				}
			}
		}
		
		
		
		
		game.batch.end();
 
		// process user input para un solo jugador
		
		if(players.size == 1){
		
		if (Gdx.input.isTouched()) {
		Vector3 touchPos = new Vector3();
		touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
		camera.unproject(touchPos);
		players.get(0).setX(touchPos.x - 64 / 2);
		}
		if (Gdx.input.isKeyPressed(Keys.LEFT))
			players.get(0).setX(players.get(0).getX() - 350 * Gdx.graphics.getDeltaTime());
		if (Gdx.input.isKeyPressed(Keys.RIGHT))
			players.get(0).setX(players.get(0).getX() + 350 * Gdx.graphics.getDeltaTime());
 
		// make sure the bucket stays within the screen bounds
		if (players.get(0).getX() < 0)
			players.get(0).setX(0);
		if (players.get(0).getX() > 800 - 64)
			players.get(0).setX(800 - 64);
		
		players.get(0).getColision().y = players.get(0).getY();
		players.get(0).getColision().x = players.get(0).getX();
		
		}
		
		// comandos cuando hay dos jugadores
		
		if(players.size == 2){
			
			
			if (Gdx.input.isKeyPressed(Keys.A))
				players.get(0).setX(players.get(0).getX() - 350 * Gdx.graphics.getDeltaTime());
			if (Gdx.input.isKeyPressed(Keys.D))
				players.get(0).setX(players.get(0).getX() + 350 * Gdx.graphics.getDeltaTime());
			
			if (Gdx.input.isKeyPressed(Keys.LEFT))
				players.get(1).setX(players.get(1).getX() - 350 * Gdx.graphics.getDeltaTime());
			if (Gdx.input.isKeyPressed(Keys.RIGHT))
				players.get(1).setX(players.get(1).getX() + 350 * Gdx.graphics.getDeltaTime());
	 
			// make sure the bucket stays within the screen bounds
			if (players.get(0).getX() < 0)
				players.get(0).setX(0);
			if (players.get(0).getX() > 400 - 64)
				players.get(0).setX(400 - 64);
			
			if (players.get(1).getX() < 400)
				players.get(1).setX(400);
			if (players.get(1).getX() > 800 - 64)
				players.get(1).setX(800 - 64);
			
			//mantiene el rectangulo de colision sobre el sprite del balde
			
			players.get(0).getColision().y = players.get(0).getY();
			players.get(0).getColision().x = players.get(0).getX();
			
			players.get(1).getColision().y = players.get(1).getY();
			players.get(1).getColision().x = players.get(1).getX();
			
			}
		
		
		
		
 
		// crea nuevas gotas (inicia con 1 por segundo y aumenta al pasar el tiempo)
		if ((TimeUtils.nanoTime() - lastDropTime > ((1000000000/1)-(tiempo*1000000000/100))) && (tiempo < 120))
			spawnRaindrop();
 
		// move the raindrops, remove any that are beneath the bottom edge of
		// the screen or that hit the bucket. In the later case we play back
		// a sound effect as well.
		Iterator<Gota> iter = gotascaen.iterator();
		while (iter.hasNext()) {
			Gota gotita = iter.next();
				if(gotita.getTipo()>=0 && gotita.getTipo()<=200){
					gotita.setY(gotita.getY() - 200 * Gdx.graphics.getDeltaTime()) ;
				}
				else if(gotita.getTipo()>=201 && gotita.getTipo()<=400){
					gotita.setY(gotita.getY() - 300 * Gdx.graphics.getDeltaTime()) ;
				}
				else if(gotita.getTipo()>=401 && gotita.getTipo()<=600){
					gotita.setY(gotita.getY() - 400 * Gdx.graphics.getDeltaTime()) ;
				}
				else if(gotita.getTipo()>=601 && gotita.getTipo()<=800){
					gotita.setY(gotita.getY() - 500 * Gdx.graphics.getDeltaTime()) ;
				}
				else{
					gotita.setY(gotita.getY() - 600 * Gdx.graphics.getDeltaTime()) ;
				}
			gotita.getColision().y = gotita.getY();
			gotita.getColision().x = gotita.getX()+((gotita.getWidth()/2)-(gotita.getColision().getWidth()/2));
			
			if (gotita.getY() + 64 < 0){
				//splashSound.play(); //elimino este sonido porque es muy molesto al final
				iter.remove();
			    }
			
	    //gotas atrapadas por el jugador 1	
			
			if (gotita.getColision().overlaps(players.get(0).getColision())) {
				    dropsGathered1++;
				
				
				if(gotita.getTipo()>=0 && gotita.getTipo()<=200){
					totalscore1 ++;
					dropSound.play();
				}
				else if(gotita.getTipo()>=201 && gotita.getTipo()<=400){
					totalscore1 += 2;
					dropSound.play();
				}
				else if(gotita.getTipo()>=401 && gotita.getTipo()<=600){
					totalscore1 += 3;
					dropSound.play();
				}
				else if(gotita.getTipo()>=601 && gotita.getTipo()<=800){
					totalscore1 += 4;
					dropSound.play();
				}
				else{
					totalscore1 += 10;
					espSound.play();
				}
				
				iter.remove();
			}
				
		//gotas atrapadas por el jugador 2		
			
			if(players.size == 2){
				if (gotita.getColision().overlaps(players.get(1).getColision())) {
				    dropsGathered2++;
				
				
				if(gotita.getTipo()>=0 && gotita.getTipo()<=200){
					totalscore2 ++;
					dropSound.play();
				}
				else if(gotita.getTipo()>=201 && gotita.getTipo()<=400){
					totalscore2 += 2;
					dropSound.play();
				}
				else if(gotita.getTipo()>=401 && gotita.getTipo()<=600){
					totalscore2 += 3;
					dropSound.play();
				}
				else if(gotita.getTipo()>=601 && gotita.getTipo()<=800){
					totalscore2 += 4;
					dropSound.play();
				}
				else{
					totalscore2 += 10;
					espSound.play();
				}	
				
				
				
				iter.remove();
			}
			}	
		}
	}
 
	@Override
	public void resize(int width, int height) {
	}
 
	@Override
	public void show() {
		// start the playback of the background music
		// when the screen is shown
		rainMusic.play();
		
	
	}
 
	@Override
	public void hide() {
	}
 
	@Override
	public void pause() {
	}
 
	@Override
	public void resume() {
	}
 
	@Override
	public void dispose() {
		dropImageA.dispose();
		dropImageB.dispose();
		dropImageC.dispose();
		dropImageD.dispose();
		fondo.dispose();
		fondo2.dispose();
		gameover.dispose();
		p1w.dispose();
		p2w.dispose();
		bucketImage.dispose();
		dropSound.dispose();
		dropSound2.dispose();
		splashSound.dispose();
		espSound.dispose();
		fallSound.dispose();
		rainMusic.dispose();
		

		
	}
 
}