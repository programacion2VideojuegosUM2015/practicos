package ar.edu.um.vj2015.arcanoid;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;


public class ManejadorDeRecursos {
	private static HashMap<String,Texture> texturas = new HashMap<String, Texture>();
	private static HashMap<String,BitmapFont> fuentes  = new HashMap<String, BitmapFont>();
	private static HashMap<String,Sound> sonidos  = new HashMap<String,Sound >();
	private static HashMap<String,Music> musica  = new HashMap<String,Music >();
	
	
	
	//carga todos las texturas del juego
	public static void cargarTodasLasTexturas(){
		
		//imagenes
		ManejadorDeRecursos.cargarRecurso("bola",new Texture(Gdx.files.internal("bola.png")));
		ManejadorDeRecursos.cargarRecurso("fondo",new Texture(Gdx.files.internal("fondo.png")));
		ManejadorDeRecursos.cargarRecurso("nave",new Texture(Gdx.files.internal("barra.png")));
		ManejadorDeRecursos.cargarRecurso("amarillo",new Texture(Gdx.files.internal("amarillo.png")));
		ManejadorDeRecursos.cargarRecurso("azul",new Texture(Gdx.files.internal("azul.png")));
		ManejadorDeRecursos.cargarRecurso("blanco",new Texture(Gdx.files.internal("blanco.png")));
		ManejadorDeRecursos.cargarRecurso("gris",new Texture(Gdx.files.internal("gris.png")));
		ManejadorDeRecursos.cargarRecurso("negro",new Texture(Gdx.files.internal("negro.png")));
		ManejadorDeRecursos.cargarRecurso("purpura",new Texture(Gdx.files.internal("purpura.png")));
		ManejadorDeRecursos.cargarRecurso("rojo",new Texture(Gdx.files.internal("rojo.png")));
		ManejadorDeRecursos.cargarRecurso("verde",new Texture(Gdx.files.internal("verde.png")));
		ManejadorDeRecursos.cargarRecurso("fondoGameOver",new Texture(Gdx.files.internal("fondoGameOver.jpg")));
		ManejadorDeRecursos.cargarRecurso("fondoInicio",new Texture(Gdx.files.internal("pantallaInicio.jpg")));
		ManejadorDeRecursos.cargarRecurso("fondoJuego",new Texture(Gdx.files.internal("fondojuego.jpg")));
		ManejadorDeRecursos.cargarRecurso("fondoGanaste",new Texture(Gdx.files.internal("fondoGameGanaste.jpg")));
		//fuente
		ManejadorDeRecursos.cargarLetra("tipografia",new BitmapFont(Gdx.files.internal("fuente.fnt"),Gdx.files.internal("fuente.png"),false));
		//sonidos
		ManejadorDeRecursos.cargarSonido("rompiendo",Gdx.audio.newSound(Gdx.files.internal("rompiendo.mp3")));
		//musica
		ManejadorDeRecursos.cargarMusica("musicaFondo", Gdx.audio.newMusic(Gdx.files.internal("musicaFondo.mp3")));
		
	}
	
	
	//carga un recurso en memoria
	private static void cargarRecurso(String nombre, Texture textura) {
		texturas.put(nombre, textura);
	}
	private static void cargarLetra(String nombre, BitmapFont fuente) {
		fuentes.put(nombre, fuente);
	}
	private static void cargarSonido(String nombre, Sound sonido) {
		sonidos.put(nombre, sonido);
	}
	private static void cargarMusica(String nombre, Music tema) {
		musica.put(nombre, tema);
	}
	//se obtiene una textura de memoria
	public static Texture getTextura(String nombre) {
		
		return texturas.get(nombre);
	}
	public static BitmapFont getFuente(String nombre){
		
		return fuentes.get(nombre);
	}
	public static Sound getSonido(String nombre){
		
		return sonidos.get(nombre);
		
	}
	public static Music getMusica(String nombre){
		
		return musica.get(nombre);
		
	}
}
