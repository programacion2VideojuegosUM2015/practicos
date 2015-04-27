package com.prodandromeda.newbucket;

import com.badlogic.gdx.math.Rectangle;

public class Balde {
	   
	private int jugador;
	private float x = 0;
	private float y = 20;
	private int width = 64;
	private int height = 64;
	
	private Rectangle colision;
	
	
	public Balde (){
	colision = new Rectangle (getX(), getY(), getWidth(), getHeight());
	}
        
	public Rectangle getColision(){
		return colision;
	}

	
	public int getJugador() {
		return jugador;
	}
	public void setJugador(int jugador) {
		this.jugador = jugador;
	}
	
	public float getX() {
		return x;
	}
	public void setX(float x) {
		this.x = x;
	}
	
	public float getY() {
		return y;
	}
	public void setY(float y) {
		this.y = y;
	}
	
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	

	
	


}