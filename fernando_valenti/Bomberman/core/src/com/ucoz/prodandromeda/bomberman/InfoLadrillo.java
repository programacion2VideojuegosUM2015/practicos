package com.ucoz.prodandromeda.bomberman;

public class InfoLadrillo {
	
	private float posX;
	private float posY;
	private boolean tienePuerta = false;
	private boolean tienePowerUp = false;
	
	public float getPosX() {
		return posX;
	}


	public void setPosX(float posX) {
		this.posX = posX;
	}


	public float getPosY() {
		return posY;
	}


	public void setPosY(float posY) {
		this.posY = posY;
	}


	public boolean isTienePuerta() {
		return tienePuerta;
	}


	public void setTienePuerta(boolean tienePuerta) {
		this.tienePuerta = tienePuerta;
	}


	public boolean isTienePowerUp() {
		return tienePowerUp;
	}


	public void setTienePowerUp(boolean tienePowerUp) {
		this.tienePowerUp = tienePowerUp;
	}
    

}


