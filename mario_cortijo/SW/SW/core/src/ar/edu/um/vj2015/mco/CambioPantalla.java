package ar.edu.um.vj2015.mco;

import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;

public class CambioPantalla {
	private int estadoPuerta = 1; //0 abierta - 1 cerrando - 2 cerrada - 3 abriendo.
	private int tics;
	private int xIzq;
	private int xDer;
	private int dibujarLetrero = 0;
	private int letreroPuerta = 0; //0 nada  - 1 start level - 2 game over - 3 stage clear

public CambioPantalla() {
	tics=0;
	xIzq = -3;
	xDer = 160;
 }

//-----------------------------------------------------------------------------------------
// MÉTODOS
//-----------------------------------------------------------------------------------------
public int getEstadoPuerta() { return estadoPuerta; }
public void setEstadoPuerta(int estadoPuerta) { this.estadoPuerta = estadoPuerta; }	
//-----------------------------------------------------------------------------------------
public int getTics() { return tics; }
public void setTics(int tics) { this.tics = tics; }	
//-----------------------------------------------------------------------------------------
public int getXIzq() { return xIzq; }
public void setXIzq(int xIzq) { this.xIzq = xIzq; }	
//-----------------------------------------------------------------------------------------
public int getXDer() { return xDer; }
public void setXDer(int xDer) { this.xDer = xDer; }	
//------------------------------------------------------------------------------------------
public int getLetreroPuerta() { return letreroPuerta; }
public void setLetreroPuerta(int letreroPuerta) { this.letreroPuerta = letreroPuerta; }	
//------------------------------------------------------------------------------------------
public int getDibujarLetrero() { return dibujarLetrero; }
public void setDibujarLetrero(int dibujarLetrero) { this.dibujarLetrero = dibujarLetrero; }	
//------------------------------------------------------------------------------------------

public void animacionPuerta() { 	 
  	 Timer.schedule(new Task(){
           @Override
           public void run() {
               tics++;
               if(tics > 30){                          	                 	 
                   tics = 0;
               }
               
               if(estadoPuerta==1){
            	if(xIzq<-3){xIzq++;}
            	if(xDer>160){xDer--;}
            	if(xIzq==-3 && xDer==160){estadoPuerta=2; tics=0; if(Juego.titleScreen==2){Juego.titleScreen=0;} }
               }
               
               if(estadoPuerta==2){
            	if(tics==3) {dibujarLetrero=1;}
            	if(tics==25) {dibujarLetrero=0;}
            	if(tics==30) {estadoPuerta=3;}
               }
               
               if(estadoPuerta==3){
            	 if(xIzq>-163){xIzq--;}
            	 if(xDer<320){xDer++;}
            	 if(xIzq==-163 && xDer==320){estadoPuerta=0; tics=0;}
               }
                             
            }
       }
       ,0,1/250.0f); 	 
	 }        
}
//-----------------------------------------------------------------------------------------
