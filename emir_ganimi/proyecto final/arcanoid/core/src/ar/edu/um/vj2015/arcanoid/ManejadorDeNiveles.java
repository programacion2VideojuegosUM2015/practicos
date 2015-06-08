package ar.edu.um.vj2015.arcanoid;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;

import ar.edu.um.vj2015.arcanoid.Ladrillo.TipoLadrillo;

public class ManejadorDeNiveles {
	private int nivelActual;
	private ManejadorDeSprite manejadorDeSprite;
	
	//constructor donde inicializamos el nivel en el que estamos actualmente
	public ManejadorDeNiveles(ManejadorDeSprite manejadorDeSprite){
		this.manejadorDeSprite = manejadorDeSprite;
		nivelActual = 1;
	}
	//metodo para dibujar el nivel en pantalla del juego
	public void cargarNivelActual() {
		FileHandle file = Gdx.files.internal("level" + nivelActual + ".txt");
		String informacionNivel = file.readString();
		//margenes donde debe empezar a colocar ladrillos
		int x = 2, y = 600;
		//convierte la filas del archivo en arreglos de string 
		String[] filas = informacionNivel.split("\n");
		Ladrillo ladrillo = null;
		//recorre el arreglo 
		for (String fila : filas) {
			String[] ladrilloIds = fila.split(",");
			for (String ladrilloId : ladrilloIds) {
				//if convierte los x del archivo en espacios en el juego
				if (ladrilloId.trim().equals("x")) {
					x += Constantes.LADRILLO_ANCHURA;
					continue;
				}
				ladrillo = new Ladrillo(getTexturaLadrillo(ladrilloId.trim()), x, y, TipoLadrillo.values()[Integer.valueOf(ladrilloId.trim())]);
				manejadorDeSprite.ladrillos.add(ladrillo);
				//separacion entre ladrillos
				x += 50;
			}
			x = 2;
			//separacion entre filas 
			y -= Constantes.LADRILLO_ANCHURA;
		}
	}
	//metodo para obtener la textura del ladrillo segun el tipo
	private Texture getTexturaLadrillo(String ladrilloId){
		TipoLadrillo tipo = TipoLadrillo.values()[Integer.valueOf(ladrilloId)];
		
		switch (tipo) {
		case AMARILLO:
			return ManejadorDeRecursos.getTextura("amarillo");
		case AZUL:
			return ManejadorDeRecursos.getTextura("azul");
		case BLANCO:
			return ManejadorDeRecursos.getTextura("blanco");
		case GRIS:
			return ManejadorDeRecursos.getTextura("gris");
		case NEGRO:
			return ManejadorDeRecursos.getTextura("negro");
		case PURPURA:
			return ManejadorDeRecursos.getTextura("purpura");
		case ROJO:
			return ManejadorDeRecursos.getTextura("rojo");
		case VERDE:
			return ManejadorDeRecursos.getTextura("verde");
		
		default:
			return null;
		}
	}
}

