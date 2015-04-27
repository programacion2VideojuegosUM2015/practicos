package com.prodandromeda.newbucket;

import com.badlogic.gdx.math.Rectangle;

public class Gota {
	   
	private int tipo;
	private float x = 0;
	private float y = 480;
	private int width = 64;
	private int height = 64;
	
	private Rectangle colision;
	
	
	public Gota (){
	colision = new Rectangle (getX(), getY(), 32, 30);
	}
        
	public Rectangle getColision(){
		return colision;
	}

	
	public int getTipo() {
		return tipo;
	}
	public void setTipo(int tipo) {
		this.tipo = tipo;
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